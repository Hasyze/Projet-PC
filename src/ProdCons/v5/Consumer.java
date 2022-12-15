package ProdCons.v5;

import java.util.Random;

public class Consumer extends Thread {

	private int id;
	ProdConsBuffer buff;
	private int consTime;

	public Consumer(int id, ProdConsBuffer buff, int consTime) {
		this.id = id;
		this.buff = buff;
		this.consTime = consTime;
		this.start();
	}

	public void run() {
		Random r = new Random();
		int k = r.nextInt(1, 4);
		while (!buff.isDone()) {
			try {
				if (k == 1) {
					Message val = buff.get();
					 System.out.println("Consommateur #" + this.id + " prend: " + val.id());
				} else {
					Message[] msg = buff.get(k);
					System.out.println("Consommateur #" + this.id + " prend plusieurs msg !!!!!!");
					for (int i = 0; i < msg.length; i++) {
						System.out.println("Consommateur #" + this.id + " prend: " + msg[i].id());
					}
				}
				sleep(consTime * 1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("No more messages!!");
	}

}
