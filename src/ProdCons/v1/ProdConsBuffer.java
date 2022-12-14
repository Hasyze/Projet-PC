 package ProdCons.v1;



public class ProdConsBuffer implements IProdConsBuffer {

	int bufferSz;
	Message[] buffer;
	int totmsg=0;
	int get , put;

	ProdConsBuffer(int bufferSz) {
		this.bufferSz = bufferSz;
		buffer = new Message[bufferSz];
		get = 0; 
		put = 0;
	}

	@Override
	public synchronized void put(Message m) throws InterruptedException {
		while (nmsg() >= bufferSz) {
			wait();
		}
		buffer[put%bufferSz] = m;
		put ++;
		totmsg++;
		notifyAll();
	}

	@Override
	public synchronized Message get() throws InterruptedException {
		while (nmsg() <= 0) {
			wait();
		}
		Message msg = buffer[get%bufferSz];
		if(msg !=null)
			get ++;
		notifyAll();
		return msg;
	}

	@Override
	public synchronized int nmsg() {
		return put-get;
	}

	@Override
	public int totmsg() {
		return totmsg;
	}
}
