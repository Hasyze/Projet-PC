package ProdCons.v5;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Random;

public class TestProdCons {

	public static void main(String[] args) throws InterruptedException, InvalidPropertiesFormatException, IOException {

		Properties properties = new Properties();
		properties.loadFromXML(TestProdCons.class.getClassLoader().getResourceAsStream("options.xml"));
		int nProd = Integer.parseInt(properties.getProperty("nProd"));
		int nCons = Integer.parseInt(properties.getProperty("nCons"));
		int bufSz = Integer.parseInt(properties.getProperty("bufSz"));
		int prodTime = Integer.parseInt(properties.getProperty("prodTime"));
		int consTime = Integer.parseInt(properties.getProperty("consTime"));
		int minProd = Integer.parseInt(properties.getProperty("minProd"));
		int maxProd = Integer.parseInt(properties.getProperty("maxProd"));

		Random r = new Random();

		ProdConsBuffer buff = new ProdConsBuffer(bufSz);

		Producer[] Prods = new Producer[nProd];

		for (int i = 0; i < nProd; i++) {
			int nmsg = r.nextInt(minProd, maxProd);
			Prods[i] = new Producer(buff, prodTime, nmsg);
		}

		Consumer[] Cons = new Consumer[nCons];

		for (int i = 0; i < nCons; i++) {
			Cons[i] = new Consumer(buff, consTime);
		}

		for (int i = 0; i < nProd; i++) {
			Prods[i].join();
		}

		buff.done = true;

		for (int i = 0; i < nCons; i++) {
			Cons[i].interrupt();
		}

	}

}
