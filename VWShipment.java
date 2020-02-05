package edu.metrostate.ics372.snowywhitemn;

import java.util.Date;

/**
 * Shipment class represents one shipment.
 * 
 * @author RANDOMNAME Group (https://github.com/f3z087/RANDOMNAME)
 *
 */
public class VWShipment {

	private String warehouseId;
	private ShipmentMethod shipmentMethod;
	private String shipmentId;
	private Long weight;
	// would this be for the warehouse
	Date receiptDate;

	/**
	 * Shipment methods that can be used to send a shipment to a warehouse
	 * 
	 * @author RANDOMNAME Group (https://github.com/f3z087/RANDOMNAME)
	 *
	 */
	public enum ShipmentMethod {
		air, truck, ship, rail;

	}

	/**
	 * Constructor to create new shipment. Receipt date is set to null intentionally
	 * and is not required to create a shipment since the expected receipt date can
	 * be different than the actual receipt date.
	 */
	public VWShipment(String warehouseId, ShipmentMethod shipmentMethod, String shipmentId, Long weight) {

		this.warehouseId = warehouseId;
		this.shipmentMethod = shipmentMethod;
		this.shipmentId = shipmentId;
		this.weight = weight;
		this.receiptDate = null;
	}

	/**
	 * Gets the receiving warehouse Id of the shipment.
	 * 
	 * @return warehouse Id
	 */
	public String getWarehouseId() {
		return warehouseId;
	}

	/**
	 * Sets the receiving warehouse Id for the shipment
	 * 
	 * @param warehouseId
	 */
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**
	 * Gets the shipment method for a shipment.
	 * 
	 * @return shipment method
	 */
	public ShipmentMethod getShipmentMethod() {
		return shipmentMethod;
	}

	/**
	 * Sets the shipment method of the shipment.
	 * 
	 * @param shipmentMethod
	 */
	public void setShipmentMethod(String shipmentMethod) {

		this.shipmentMethod = ShipmentMethod.valueOf(shipmentMethod);
	}

	/**
	 * Gets the shipment Id of the shipment object.
	 * 
	 * @return shipment Id
	 */
	public String getShipmentId() {
		return shipmentId;
	}

	/**
	 * Sets the shipment method of the shipment
	 * 
	 * @param shipmentId
	 */
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	/**
	 * Gets the weight of the shipment.
	 * 
	 * @return weight
	 */
	public Long getWeight() {
		return weight;
	}

	/**
	 * Sets the weight of the shipment.
	 * 
	 * @param weight
	 */
	public void setWeight(Long weight) {
		this.weight = weight;
	}

	/**
	 * Gets the receipt date of the shipment.
	 * 
	 * @return receipt date
	 */
	public Date getReceiptDate() {
		return receiptDate;
	}

	/**
	 * Sets the receipt date of the shipment.
	 * 
	 * @param receiptDate
	 */
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	@Override
	public String toString() {
		return "Shipment [warehouseId=" + warehouseId + ", shipmentMethod=" + shipmentMethod + ", shipmentId="
				+ shipmentId + ", weight=" + weight + ", receiptDate=" + receiptDate + "]";
	}

}
