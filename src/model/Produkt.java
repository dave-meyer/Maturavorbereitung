package model;

/**
 * Aufgabenstellung in 2.1 (bereits vorhanden)
 *
 */
public class Produkt {

	int id;
	String name;
	String amount;
	Shop shop;
	
	public Produkt() {	}
	
	public Produkt(int id, String name, String amount, Shop shop) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.shop = shop;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}