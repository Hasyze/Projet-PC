package ProdCons.v2;

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
				Message val = buff.get();
				sleep(consTime);
			} catch (InterruptedException e) {

			}
		}
	}

}
