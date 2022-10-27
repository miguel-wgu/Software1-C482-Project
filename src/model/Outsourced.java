package model;

public class Outsourced extends Part{
	private String companyName;

	public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
		super(id, name, price, stock, min, max);
		this.companyName = companyName;
	}

	/**
	 * @param companyName sets the companyName.
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return returns the companyName.
	 */
	public String getCompanyName() {
		return companyName;
	}
}
