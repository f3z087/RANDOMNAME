package edu.metrostate.ics372.snowywhitemn;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

/**
 * Class represents a warehouse
 * 
 * @author Victoria White
 *
 */
public class VWWarehouse {
	private String warehouseId;
	private boolean freightReceipt;
	private List<VWShipment> listOfShipments;

	/**
	 * Constructor to create a new warehouse object.
	 * 
	 * @param warehouseId
	 */
	public VWWarehouse(String warehouseId) {

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
	 * Sets freight receipt value to true, indicating that the warehouse can
	 * received more shipments.
	 */
	public void enableFreightReceipt() {
		freightReceipt = true;

	}

	/**
	 * Sets freight receipt value to false, indicating that the warehouse is not
	 * able to receive anymore shipments.
	 */
	public void endFreightReceipt() {
		freightReceipt = false;

	}

	/**
	 * Prints the listOfShipments to a json file.
	 */
	public void printReceivedShipments(String filePath) {

		JSONArray warehouseContents = new JSONArray();
		// add shipments to the list
		warehouseContents.addAll(this.listOfShipments);

		// write json file
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(warehouseContents.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Adds incoming shipment into listOfShipments
	 * 
	 * @param shipment
	 */
	public void addIncomingShipment(VWShipment shipment) {
		// if freight receipt is allowed then add the shipment
		if (this.freightReceipt == true) {
			// add shipment to list of shipments
			listOfShipments.add(shipment);
		} else {
			System.out.println("Freight Receipt is not enabled for this warehouse" + shipment.getWarehouseId() + "/n"
					+ "Shipment was not added" + shipment.getShipmentId());
		}

	}

}
