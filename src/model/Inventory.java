package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory class that contains all parts and products.
 */
public class Inventory {
	/**
	 * List that holds all parts.
	 */
	private static ObservableList<Part> allParts = FXCollections.observableArrayList();
	/**
	 * List that holds all products.
	 */
	private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

	private Inventory() {
	}

	/**
	 * Add part.
	 *
	 * @param newPart adds a part to the allParts list.
	 */
	public static void addPart(Part newPart) {
		allParts.add(newPart);
	}

	/**
	 * Add product.
	 *
	 * @param newProduct adds a product to the allProducts list.
	 */
	public static void addProduct(Product newProduct) {
		allProducts.add(newProduct);
	}

	/**
	 * Lookup part.
	 *
	 * @param partId searches for a part in the allParts list by id.
	 * @return a part with matching part ID, else return null.
	 */
	public static Part lookupPart(int partId) {
		for (Part part : allParts) {
			if (part.getId() == partId) return part;
		}
		return null;
	}

	/**
	 * Lookup product.
	 *
	 * @param productId searches for a product in the allProducts list by id.
	 * @return a product with matching product ID, else return null.
	 */
	public static Product lookupProduct(int productId) {
		for (Product product : allProducts) {
			if (product.getId() == productId) return product;
		}
		return null;
	}

	/**
	 * Lookup part observable list.
	 *
	 * @param partName searches for a part in the allParts list by name.
	 * @return list of parts that match text in search.
	 */
	public static ObservableList<Part> lookupPart(String partName) {
		ObservableList<Part> matchingPartsList = FXCollections.observableArrayList();
		for (Part part : allParts) {
			if (part.getName().equals(partName)) matchingPartsList.add(part);
		}
		return matchingPartsList;
	}

	/**
	 * Lookup product observable list.
	 *
	 * @param productName searches for a product in the allProducts list by name.
	 * @return list of returns that match text in search.
	 */
	public static ObservableList<Product> lookupProduct(String productName) {
		ObservableList<Product> matchingProductsList = FXCollections.observableArrayList();
		for (Product product : allProducts) {
			if (product.getName().equals(productName)) matchingProductsList.add(product);
		}
		return matchingProductsList;
	}

	/**
	 * Update part.
	 *
	 * @param index        updates an existing part with matching index.
	 * @param selectedPart updates a part in the allParts list.
	 */
	public static void updatePart(int index, Part selectedPart) {
		allParts.set(index, selectedPart);
	}

	/**
	 * Update product.
	 *
	 * @param index      updates an existing product with matching index.
	 * @param newProduct updates a product in the allProducts list.
	 */
	public static void updateProduct(int index, Product newProduct) {
		allProducts.set(index, newProduct);
	}

	/**
	 * Delete part boolean.
	 *
	 * @param selectedPart deletes a part from the allParts list.
	 * @return true if part is deleted, else return false.
	 */
	public static boolean deletePart(Part selectedPart) {
		if (allParts.contains(selectedPart)) {
			allParts.remove(selectedPart);
			return true;
		} else return false;
	}

	/**
	 * Delete product boolean.
	 *
	 * @param selectedProduct deletes a product from the allProducts list.
	 * @return true if product is deleted, else return false.
	 */
	public static boolean deleteProduct(Product selectedProduct) {
		if (allProducts.contains(selectedProduct)) {
			allProducts.remove(selectedProduct);
			return true;
		} else return false;
	}

	/**
	 * Gets all parts.
	 *
	 * @return the allParts list.
	 */
	public static ObservableList<Part> getAllParts() {
		return allParts;
	}

	/**
	 * Gets all products.
	 *
	 * @return the allProducts list.
	 */
	public static ObservableList<Product> getAllProducts() {
		return allProducts;
	}
}
