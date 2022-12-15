package ProdCons.v3;

import java.util.concurrent.Semaphore;

public class ProdConsBuffer implements IProdConsBuffer {

	int bufferSz;
	Message[] buffer;
	int totmsg = 0;
	Semaphore notFull;
	Semaphore notEmpty;
	Semaphore mutexIn, mutexOut;
	int get, put;
	boolean done;

	ProdConsBuffer(int bufferSz) {
		this.bufferSz = bufferSz;
		buffer = new Message[bufferSz];
		get = 0;
		put = 0;
		notFull = new Semaphore(bufferSz, true);
		notEmpty = new Semaphore(0, true);
		mutexIn = new Semaphore(1, true);
		mutexOut = new Semaphore(1, true);
		done = false;
	}

	@Override
	public void put(Message m) throws InterruptedException {
		notFull.acquire();
		mutexIn.acquire();
		buffer[put % bufferSz] = m;
		put++;
		totmsg++;
		mutexIn.release();
		notEmpty.release();
	}

	@Override
	public Message get() throws InterruptedException {
		notEmpty.acquire();
		mutexOut.acquire();
		Message msg;
		msg = buffer[get % bufferSz];
		if (msg != null)
			get++;
		mutexOut.release();
		notFull.release();
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
