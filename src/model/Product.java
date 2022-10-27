package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model class for Product that can contain parts.
 *
 * @author Miguel Guzman
 */

public class Product {
	private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
	private int id;
	private String name;
	private double price;
	private int stock;
	private int min;
	private int max;

	public Product(int id, String name, double price, int stock, int min, int max) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.min = min;
		this.max = max;
	}

	/**
	 * @param id sets the id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param name sets the name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param price sets the price.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @param stock sets the stock.
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @param min sets the min.
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * @param max sets the max.
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * @return the id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the stock.
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @return the min.
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @return the max.
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param part adds a part to the associated parts list.
	 */
	public void addAssociatedPart(Part part) {
		associatedParts.add(part);
	}

	/**
	 * @param selectedAssociatedPart deletes a part from the associated parts list.
	 * @return true if the part was deleted.
	 */
	public boolean deletedAssociatedPart(Part selectedAssociatedPart) {
		return associatedParts.remove(selectedAssociatedPart);
	}

	/**
	 * @return the associated parts list.
	 */
	public ObservableList<Part> getAllAssocaitedParts() {
		return associatedParts;
	}
}
