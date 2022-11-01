package model;

/**
 * InHouse class.
 */
public class InHouse extends Part {
	/**
	 * The Machine id.
	 */
	private int machineId;

	/**
	 * Instantiates a new In house.
	 *
	 * @param id        the id.
	 * @param name      the name.
	 * @param price     the price.
	 * @param stock     the stock.
	 * @param min       the min.
	 * @param max       the max.
	 * @param machineId the machine id.
	 */
	public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
		super(id, name, price, stock, min, max);
		this.machineId = machineId;
	}

	/**
	 * Gets machine id.
	 *
	 * @return returns the machineId.
	 */
	public int getMachineId() {
		return machineId;
	}

	/**
	 * Sets machine id.
	 *
	 * @param machineId sets the machineId.
	 */
	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
}