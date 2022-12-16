package ProdCons.v6;

public class Producer extends Thread {

	ProdConsBuffer buff;
	private int prodTime;
	private int nmsg;

	public Producer(ProdConsBuffer buff, int prodTime, int nmsg) {
		this.buff = buff;
		this.prodTime = prodTime;
		this.nmsg = nmsg;
		this.start();
	}

	public void run() {
		//for (int i = 0; i < this.nmsg; i++) {
			Message m = new Message(nmsg);
			try {
				buff.put(m,10);
				sleep(prodTime * 1000);
			} catch (InterruptedException e1) {

			}

		//}
	}
}
