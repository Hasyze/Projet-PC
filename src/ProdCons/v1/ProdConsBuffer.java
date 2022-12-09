 package ProdCons.v1;

import java.util.concurrent.Semaphore;

public class ProdConsBuffer implements IProdConsBuffer {

	int bufferSz;
	private Message[] buffer;
	int nfull = 0, nempty;
	int totmsg=0;

	ProdConsBuffer(int bufferSz) {
		this.bufferSz = bufferSz;
		buffer = new Message[bufferSz];
		// for(int i=0; i<bufferSz; i++)
		// buffer[i] = new Message(i);
		nempty = bufferSz;
	}

	@Override
	public synchronized void put(Message m) throws InterruptedException {
		while (nempty <= 0 && nfull >= bufferSz) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buffer[nfull++] = m;
		nempty--;
		totmsg++;
		notifyAll();
	}

	@Override
	public synchronized Message get() throws InterruptedException {
		while (nfull <= 0 && nempty >= bufferSz) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Message msg = buffer[--nfull];
		nempty++;
		notifyAll();
		return msg;
	}

	@Override
	public int nmsg() {
		return nfull;
	}

	@Override
	public int totmsg() {
		return totmsg;
	}
}
