package ProdCons;

public class Consumer extends Thread {

	private int id;
	private ProdConsBuffer buff;

	public Consumer(int id, ProdConsBuffer buff) {
		this.id = id;
		this.buff = buff;
		this.start();  
	}
	
	public void run() {
		Message val = new Message (-1) ;
        for (int i = 0; i < buff.bufferSz; i++) {
            try {
				val = buff.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Consommateur #" + 
                  this.id + " prend: " + val.id());
        }
	}
	
}
