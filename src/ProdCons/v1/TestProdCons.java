package ProdCons.v1;


public class TestProdCons {

	public static void main(String[] args) throws InterruptedException {
	      
	      
	      int nProd = 3;
	      int nCons = 3;
	      int bufSz = 1;
	      int prodTime =100;
	      int consTime =100;
	      int minProd = 5;
	      int maxProd= 10;
	      
	      ProdConsBuffer buff = new ProdConsBuffer(bufSz);
	      
	      Producer[] Prods = new Producer[nProd];
	      Consumer[] Cons = new Consumer[nCons];
	      
	      for (int i = 0; i < nProd; i++){
				Prods[i] = new Producer(i,buff,prodTime,minProd,maxProd);
			}
	      
	      for (int i = 0; i < nCons; i++){
				Cons[i] = new Consumer(i, buff,consTime);
			}
	      
			System.out.println("Fin");
			
	   }
	
}
