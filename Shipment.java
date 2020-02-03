package edu.metrostate.ics372.snowywhitemn;

import java.util.Date;

/**
 * Shipment class represents one shipment.
 * 
 * @author Victoria White ww6684ni
 *
 */
public class Shipment {

	private String warehouseId;
	private ShipmentMethod shipmentMethod;
	private String shipmentId;
	private Long weight;
	// would this be for the warehouse
	Date receiptDate;

	/**
	 * Shipment methods that can be used to send a shipment to a warehouse
	 * 
	 * @author Victoria White
	 *
	 */
	public enum ShipmentMethod {
		air, truck, ship, rail;
		// enum class has a name() method and a toString() method.
		// enum class has a valueOf method to get all the values of this class
		// when reading in from the json file do toLowerCase

	}

	/**
	 * Constructor to create new shipment.
	 */
	public Shipment(String warehouseId, ShipmentMethod shipmentMethod, String shipmentId, Long weight) {

		this.warehouseId = warehouseId;
		this.shipmentMethod = shipmentMethod;
		this.shipmentId = shipmentId;
		this.weight = weight;
		this.receiptDate = null;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public ShipmentMethod getShipmentMethod() {
		return shipmentMethod;
	}

	public void setShipmentMethod(String shipmentMethod) {

		this.shipmentMethod = ShipmentMethod.valueOf(shipmentMethod);
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	@Override
	public String toString() {
		return "Shipment [warehouseId=" + warehouseId + ", shipmentMethod=" + shipmentMethod + ", shipmentId="
				+ shipmentId + ", weight=" + weight + ", receiptDate=" + receiptDate + "]";
	}

}
