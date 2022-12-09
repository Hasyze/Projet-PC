package ProdCons;

public class TestProdCons {

	public static void main(String[] args) {
	      ProdConsBuffer buff = new ProdConsBuffer(5);
	      Producer p1 = new Producer(1,buff);
	      Producer p2 = new Producer(2,buff);
	      Consumer c1 = new Consumer(1, buff);
	      Consumer c2 = new Consumer(2, buff);
	   }
	
}
