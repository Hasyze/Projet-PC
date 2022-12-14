package ProdCons.v2;

public class Producer  extends Thread {
	
	private int id;
	 ProdConsBuffer buff;
	private int prodTime;
	private int nmsg;
	
	public Producer(int id, ProdConsBuffer buff, int prodTime, int nmsg ) {
		this.id = id;
		this.buff = buff;
		this.prodTime = prodTime;
		this.nmsg =  nmsg;
		this.start();  
	}
	
	public void run() {
			for (int i = 0; i < this.nmsg; i++) {
				Message m = new Message(i);
			       try {
					buff.put(m);
					System.out.println("Producteur #" + this.id  + " met : " + i);
					sleep(prodTime*1000);
				} catch (InterruptedException e1) {
					
				}
			       	      
			}
	}
}
