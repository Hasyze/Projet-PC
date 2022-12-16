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
			try {
				Message msg = buff.get();
				sleep(consTime);
				msg.get.release();
				msg.getArray.acquire();
			} catch (InterruptedException e) {
			}
		}
	}

}
