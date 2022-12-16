package ProdCons.v6;

import java.util.concurrent.Semaphore;

public class Message {
	private int id;
	Semaphore get;
	Semaphore getArray;

	public Message(int id) {
		this.id = id;
		get = new Semaphore(0, true);
		getArray = new Semaphore(0, true);
	}

	public int id() {
		return id;
	}
}
