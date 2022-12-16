package ProdCons.v6;

import java.util.Random;

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
		for (int i = 0; i < nmsg; i++) {
			int n = 3;
			Message m = new Message(nmsg);
			try {
				buff.put(m, n);
				sleep(prodTime);
			} catch (InterruptedException e1) {

			}

		}
	}
}
