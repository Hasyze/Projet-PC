package ProdCons.v2;

import java.util.Random;

public class TestProdCons {

	public static void main(String[] args) throws InterruptedException {
	      
	      
	      int nProd = 3;
	      int nCons = 3;
	      int bufSz = 2;
	      int prodTime =1;
	      int consTime =1;
	      int minProd = 5;
	      int maxProd= 10;
	      
	      Random r = new Random();
	      
	      
	      ProdConsBuffer buff = new ProdConsBuffer(bufSz);
	      
	      Producer[] Prods = new Producer[nProd];
	     
	      
	      for (int i = 0; i < nProd; i++){
	    	  	int nmsg = r.nextInt(minProd, maxProd);
				Prods[i] = new Producer(i,buff,prodTime,nmsg);
			}
	      
	     
	      Consumer[] Cons = new Consumer[nCons];
	     
	      for (int i = 0; i < nCons; i++){
				Cons[i] = new Consumer(i, buff,consTime);
			}
	      
	      for (int i = 0; i < nProd; i++){
				Prods[i].join();
			}
	      
	      buff.done= true;
	      
	      for (int i = 0; i < nCons; i++){
				Cons[i].interrupt();;
			}
	   }
	
}
