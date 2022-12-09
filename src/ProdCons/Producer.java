package ProdCons;

public class Producer  extends Thread {
	
	private int id;
	private ProdConsBuffer buff;
	
	public Producer(int id, ProdConsBuffer buff) {
		this.id = id;
		this.buff = buff;
		this.start();  
	}
	
	public void run() {
		for (int i = 0; i < buff.bufferSz; i++) {
		       try {
				buff.put(new Message(i));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		       System.out.println("Producteur #" + this.id 
		                        + " met : " + i);
		      try { sleep(100);}
		       catch (InterruptedException e) { }
		       }
		    }
}
