package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
	ObservableList<Part> allParts = FXCollections.observableArrayList();
	ObservableList<Product> allProducts = FXCollections.observableArrayList();

	/**
	 * @param newPart adds a part to the allParts list.
	 */
	public void addPart(Part newPart) {
		allParts.add(newPart);
	}

	/**
	 * @param newProduct adds a product to the allProducts list.
	 */
	public void addProduct(Product newProduct) {
		allProducts.add(newProduct);
	}

	/**
	 * @param partId searches for a part in the allParts list by id.
	 * @return a part with matching part ID, else return null.
	 */
	public Part lookupPart(int partId) {
		for (Part part : allParts) {
			if (part.getId() == partId) return part;
		}
		return null;
	}

	/**
	 * @param productId searches for a product in the allProducts list by id.
	 * @return a product with matching product ID, else return null.
	 */
	public Product lookupProduct(int productId) {
		for (Product product : allProducts) {
			if (product.getId() == productId) return product;
		}
		return null;
	}

	/**
	 * @param partName searches for a part in the allParts list by name.
	 * @return list of parts that match text in search.
	 */
	public ObservableList<Part> lookupPart(String partName) {
		ObservableList<Part> matchingPartsList = FXCollections.observableArrayList();
		for (Part part : allParts) {
			if (part.getName().equals(partName)) matchingPartsList.add(part);
		}
		return matchingPartsList;
	}

	/**
	 * @param productName searches for a product in the allProducts list by name.
	 * @return list of returns that match text in search.
	 */
	public ObservableList<Product> lookupProduct(String productName) {
		ObservableList<Product> matchingProductsList = FXCollections.observableArrayList();
		for (Product product : allProducts) {
			if (product.getName().equals(productName)) matchingProductsList.add(product);
		}
		return matchingProductsList;
	}

	/**
	 * @param index updates an existing part with matching index.
	 * @param selectedPart updates a part in the allParts list.
	 */
	public void updatePart(int index, Part selectedPart) {
		allParts.set(index, selectedPart);
	}

	/**
	 * @param index updates an existing product with matching index.
	 * @param newProduct updates a product in the allProducts list.
	 */
	public void updateProduct(int index, Product newProduct) {
		allProducts.set(index, newProduct);
	}

	/**
	 * @param selectedPart deletes a part from the allParts list.
	 * @return true if part is deleted, else return false.
	 */
	public boolean deletePart(Part selectedPart) {
		if (allParts.contains(selectedPart)) {
			allParts.remove(selectedPart);
			return true;
		} else return false;
	}

	/**
	 * @param selectedProduct deletes a product from the allProducts list.
	 * @return true if product is deleted, else return false.
	 */
	public boolean deleteProduct(Product selectedProduct) {
		if (allProducts.contains(selectedProduct)) {
			allProducts.remove(selectedProduct);
			return true;
		} else return false;
	}

	/**
	 * @return the allParts list.
	 */
	public ObservableList<Part> getAllParts() {
		return allParts;
	}

	/**
	 * @return the allProducts list.
	 */
	public ObservableList<Product> getAllProducts() {
		return allProducts;
	}
}
