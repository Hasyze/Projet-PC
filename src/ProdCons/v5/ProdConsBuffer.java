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
	int get, put;
	boolean termine;

	ProdConsBuffer(int bufferSz) {
		this.bufferSz = bufferSz;
		buffer = new Message[bufferSz];
		// for(int i=0; i<bufferSz; i++)
		// buffer[i] = new Message(i);
		nempty = bufferSz;
		get = 0;
		put = 0;
		notFull = new Semaphore(bufferSz,true);
		notEmpty = new Semaphore(0,true);
		mutex = new Semaphore(1,true);
		termine = false;
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
		nempty += 1;
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

	public void Finished() {
		termine = true;
	}

	public boolean isFinished() {
		return termine;
	}
}
