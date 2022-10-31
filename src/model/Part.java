package model;
/**
 * Supplied class Part.java
 */

/**
 * The type Part.
 *
 * @author Miguel Guzman
 */
public abstract class Part {
	/**
	 * The Id.
	 */
	private int id;
	/**
	 * The Name.
	 */
	private String name;
	/**
	 * The Price.
	 */
	private double price;
	/**
	 * The Stock.
	 */
	private int stock;
	/**
	 * The Min.
	 */
	private int min;
	/**
	 * The Max.
	 */
	private int max;

	/**
	 * Instantiates a new Part.
	 *
	 * @param id    the id.
	 * @param name  the name.
	 * @param price the price.
	 * @param stock the stock.
	 * @param min   the min.
	 * @param max   the max.
	 */
	public Part(int id, String name, double price, int stock, int min, int max) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.min = min;
		this.max = max;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets price.
	 *
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets stock.
	 *
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Sets stock.
	 *
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * Gets min.
	 *
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Sets min.
	 *
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * Gets max.
	 *
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * Sets max.
	 *
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

}