package model;

/**
 * Outsourced class.
 */
public class Outsourced extends Part {
	/**
	 * The Company name.
	 */
	private String companyName;

	/**
	 * Instantiates a new Outsourced.
	 *
	 * @param id          the id.
	 * @param name        the name.
	 * @param price       the price.
	 * @param stock       the stock.
	 * @param min         the min.
	 * @param max         the max.
	 * @param companyName the company name.
	 */
	public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
		super(id, name, price, stock, min, max);
		this.companyName = companyName;
	}

	/**
	 * Gets company name.
	 *
	 * @return returns the companyName.
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets company name.
	 *
	 * @param companyName sets the companyName.
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
