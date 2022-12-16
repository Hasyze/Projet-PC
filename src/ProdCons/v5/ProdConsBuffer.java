package ProdCons.v5;

import java.util.concurrent.Semaphore;

public class ProdConsBuffer implements IProdConsBuffer {

	int bufferSz;
	Message[] buffer;
	int nfull = 0, nempty;
	int totmsg = 0;
	Semaphore notFull;
	Semaphore notEmpty;
	Semaphore mutex;
	Semaphore ks;
	int get, put;
	boolean done;

	ProdConsBuffer(int bufferSz) {
		this.bufferSz = bufferSz;
		buffer = new Message[bufferSz];
		nempty = bufferSz;
		get = 0;
		put = 0;
		notFull = new Semaphore(bufferSz, true);
		notEmpty = new Semaphore(0, true);
		mutex = new Semaphore(1, true);
		ks = new Semaphore(1, true);
		done = false;
	}

	@Override
	public void put(Message m) throws InterruptedException {
		notFull.acquire();
		mutex.acquire();
		buffer[put % bufferSz] = m;
		String name = Thread.currentThread().getName();
		System.out.println("Producteur " + name.charAt(name.length() - 1) + " met : " + m.id());
		put++;
		totmsg++;
		mutex.release();
		notEmpty.release();
	}

	@Override
	public Message get() throws InterruptedException {
		notEmpty.acquire();
		mutex.acquire();
		Message msg = buffer[get % bufferSz];
		String name = Thread.currentThread().getName();
		System.out.println("Consommateur " + name.charAt(name.length() - 1) + " prend: " + msg.id());
		if (msg != null)
			get++;
		mutex.release();
		notFull.release();
		return msg;
	}

	@Override
	public Message[] get(int k) throws InterruptedException {
		ks.acquire();
		Message[] M = new Message[k];
		String name = Thread.currentThread().getName();
		System.out.print("Consommateur " + name.charAt(name.length() - 1) + " prend " + k + " messages: \n[ ");
		for (int i = 0; i < k; i++) {
			try {
				notEmpty.acquire();
				mutex.acquire();
				M[i] = buffer[get % bufferSz];
				System.out.print(M[i].id() + " ;");
				if (M[i] != null)
					get++;
				mutex.release();
				notFull.release();
			} catch (InterruptedException e) {
				System.out.println("Des messages restants !!!");
			}
		}
		System.out.println("]");
		ks.release();
		return M;
	}

	@Override
	public int nmsg() {
		return put - get;
	}

	@Override
	public int totmsg() {
		return totmsg;
	}

	public void Done() {
		done = true;
	}

	public boolean isDone() {
		return done;
	}

}
