package model;

/**
 * Aufgabenstellung in 2.1 (bereits vorhanden)
 *
 */
public class Shop {

	int id;
	String name;
	
	public Shop() {	}
	
	public Shop(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}