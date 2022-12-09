package ProdCons.v1;

public class Producer  extends Thread {
	
	private int id;
	private ProdConsBuffer buff;
	private int prodTime;
	private int nmsg;
	
	public Producer(int id, ProdConsBuffer buff, int prodTime, int minProd, int maxProd ) {
		this.id = id;
		this.buff = buff;
		this.prodTime = prodTime;
		this.nmsg =  (minProd + ( (int) Math.random() %maxProd));
		this.start();  
	}
	
	public void run() {
		for (int i = 0; i < nmsg; i++) {
		       try {
				buff.put(new Message(i));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		       System.out.println("Producteur #" + this.id 
		                        + " met : " + i);
		      try { sleep(prodTime);}
		       catch (InterruptedException e) { }
		       }
		    }
}
