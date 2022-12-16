package ProdCons.v6;

import java.util.Random;

public class Consumer extends Thread {

	ProdConsBuffer buff;
	private int consTime;

	public Consumer(ProdConsBuffer buff, int consTime) {
		this.buff = buff;
		this.consTime = consTime;
		this.start();
	}

	public void run() {
		while (!buff.isDone()) {
			int k = 5;
			try {
				Message[] msg = buff.get(k);
				sleep(consTime * 1000);
			} catch (InterruptedException e) {
			}
		}
	}

}
