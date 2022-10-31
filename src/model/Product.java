package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model class for Product that can contain parts.
 *
 * @author Miguel Guzman
 */
public class Product {
	/**
	 * The Associated parts.
	 */
	private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
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
	 * Instantiates a new Product.
	 *
	 * @param id    the id.
	 * @param name  the name.
	 * @param price the price.
	 * @param stock the stock.
	 * @param min   the min.
	 * @param max   the max.
	 */
	public Product(int id, String name, double price, int stock, int min, int max) {
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
	 * @return the id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id sets the id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets name.
	 *
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name sets the name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets price.
	 *
	 * @return the price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets price.
	 *
	 * @param price sets the price.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets stock.
	 *
	 * @return the stock.
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Sets stock.
	 *
	 * @param stock sets the stock.
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * Gets min.
	 *
	 * @return the min.
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Sets min.
	 *
	 * @param min sets the min.
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * Gets max.
	 *
	 * @return the max.
	 */
	public int getMax() {
		return max;
	}

	/**
	 * Sets max.
	 *
	 * @param max sets the max.
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * Add associated part.
	 *
	 * @param part adds a part to the associated parts list.
	 */
	public void addAssociatedPart(Part part) {
		associatedParts.add(part);
	}

	/**
	 * Deleted associated part.
	 *
	 * @param selectedAssociatedPart deletes a part from the associated parts list.
	 * @return true if the part was deleted.
	 */
	public boolean deletedAssociatedPart(Part selectedAssociatedPart) {
		return associatedParts.remove(selectedAssociatedPart);
	}

	/**
	 * Gets all associated parts.
	 *
	 * @return the associated parts list.
	 */
	public ObservableList<Part> getAllAssociatedParts() {
		return associatedParts;
	}
}
