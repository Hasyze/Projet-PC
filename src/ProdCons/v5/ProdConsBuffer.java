package ProdCons.v5;

import java.util.concurrent.Semaphore;

public class ProdConsBuffer implements IProdConsBuffer {

	int bufferSz;
	Message[] buffer;
	int nfull = 0, nempty;
	int totmsg = 0;
	Semaphore mutex;
	int get, put;
	boolean done;

	ProdConsBuffer(int bufferSz) {
		this.bufferSz = bufferSz;
		buffer = new Message[bufferSz];
		nempty = bufferSz;
		get = 0;
		put = 0;
		mutex = new Semaphore(1,true);
		done = false;
	}

	@Override
	public void put(Message m) throws InterruptedException {
		mutex.acquire();
		synchronized(this) {
			while (nmsg() >= bufferSz) {
				wait();
			}
			buffer[put % bufferSz] = m;
			put++;
			totmsg++;
			notifyAll();
		}
		mutex.acquire();
	}

	@Override
	public Message get() throws InterruptedException {
		mutex.release();
		Message msg;
		synchronized(this){
			while (nmsg() <= 0) {
				wait();
			}
			msg = buffer[get % bufferSz];
			if (msg != null)
				get++;
			nempty += 1;
			notifyAll();
		}
		mutex.release();
		return msg;
	}
	
	@Override
	public Message[] get(int k) throws InterruptedException {
		Message[] M = new Message[k];
		synchronized(this) {
			for (int i = 0 ; i <k ; i++) {
				while (nmsg() <= 0) {
					wait();
				}
				M[i] = buffer[get%bufferSz];
				if(M[i] !=null)
					get ++;
				notifyAll();
			}
		}
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
