package ProdCons.v5;

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
	
	public  void run() {
		//Message val = new Message (-1) ;
       while(!buff.isFinished()) {
            try {
				Message val = buff.get();
				System.out.println("Consommateur #" + this.id + " prend: " + val.id());
				sleep(consTime*1000);
			} catch (InterruptedException e) {
				/*try {
					this.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
        }
       System.out.println("No more messages!!");
	}
	
}
