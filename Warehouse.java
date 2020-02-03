package edu.metrostate.ics372.snowywhitemn;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a warehouse
 * 
 * @author Victoria White
 *
 */
public class Warehouse {
	private String warehouseId;
	private boolean freightReceipt;
	private List<Shipment> listOfShipments;

	/**
	 * Constructor to create a new warehouse object.
	 * 
	 * @param warehouseId
	 */
	public Warehouse(String warehouseId) {

		this.warehouseId = warehouseId;
		// initialize the list of shipments
		listOfShipments = new ArrayList<>();
		// enable freight receipt
		enableFreightReceipt();

	}

	/**
	 * Get warehouse ID of the warehouse.
	 * 
	 * @return warehouseId
	 */
	public String getWarehouseId() {
		return warehouseId;
	}

	/**
	 * Returns true or false depending on the value of freightReceipt.
	 * 
	 * @return true or false
	 */
	public boolean isFreightReceipt() {
		return freightReceipt;
	}

	/**
	 * Sets the freight receipt boolean value based on if the warehouse is currently
	 * accepting shipments.
	 * 
	 * @param freightReceipt
	 */
	public void setFreightReceipt(boolean freightReceipt) {
		this.freightReceipt = freightReceipt;
	}

	/**
	 * Sets freight receipt value to true, indicating that the warehouse can
	 * received more shipments.
	 */
	public void enableFreightReceipt() {
		setFreightReceipt(true);

	}

	/**
	 * Sets freight receipt value to false, indicating that the warehouse is not
	 * able to receive anymore shipments.
	 */
	public void endFreightReceipt() {
		setFreightReceipt(false);

	}

	/**
	 * Prints the listOfShipments to a json file.
	 */
	public void printReceivedShipments(List<Shipment> shipments) {

	}

	/**
	 * Adds incoming shipment into listOfShipments
	 * 
	 * @param shipment
	 */
	public void addIncomingShipment(Shipment shipment) {
		// add shipment to list of shipments
		listOfShipments.add(shipment);

	}

}
