package ProdCons.v1;

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
		while (true) {
			try {
				Message val = buff.get();
				System.out.println("Consommateur #" + this.id + " prend: " + val.id());
				sleep(consTime * 1000);
			} catch (InterruptedException e) {
				
			}
		}
	}

}
