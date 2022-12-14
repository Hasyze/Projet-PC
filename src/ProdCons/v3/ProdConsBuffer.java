package ProdCons.v3;

import java.util.concurrent.Semaphore;

public class ProdConsBuffer implements IProdConsBuffer {

	int bufferSz;
	Message[] buffer;
	int totmsg = 0;
	Semaphore notFull;
	Semaphore notEmpty;
	Semaphore mutex;
	int get, put;
	boolean done;

	ProdConsBuffer(int bufferSz) {
		this.bufferSz = bufferSz;
		buffer = new Message[bufferSz];
		get = 0;
		put = 0;
		notFull = new Semaphore(bufferSz,true);
		notEmpty = new Semaphore(0,true);
		mutex = new Semaphore(1,true);
		done = false;
	}

	@Override
	public void put(Message m) throws InterruptedException {
		mutex.acquire();
		notFull.acquire();
		buffer[put % bufferSz] = m;
		put++;
		totmsg++;
		notEmpty.release();
		mutex.acquire();
	}

	@Override
	public Message get() throws InterruptedException {
		mutex.release();
		notEmpty.acquire();
		Message msg = buffer[get % bufferSz];
		if (msg != null)
			get++;
		notFull.release();
		mutex.release();
		return msg;
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
