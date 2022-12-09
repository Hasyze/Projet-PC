 package ProdCons.v1;



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
		if (nempty <= 0) {
			throw new InterruptedException();
		}
		buffer[nfull] = m;
		nempty -= 1;
		nfull += 1;//% bufferSz;
		totmsg++;
		notifyAll();
	}

	@Override
	public synchronized Message get() throws InterruptedException {
		if (nfull <= 0) {
			throw new InterruptedException();
		}
		nfull -= 1;
		Message msg = buffer[nfull];
		nempty += 1;
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
