/**
 * shipment object class
 * methods for shipment objects
 */
import java.sql.Timestamp;    
import java.util.Date;

public class Shipment {

	private String shipmentID;
	private String warehouseID;
	private String shipmentMethod;
	private Number weight;
	private long receiptDate;

	/**
	 * constructor for shipment object
	 * @param shipmentID
	 * @param warehouseID
	 * @param shipmentMethod
	 * @param weight
	 * @param receiptDate
	 */
	public Shipment(String shipmentID, String warehouseID, String shipmentMethod, Number weight, long receiptDate){
		this.shipmentID = shipmentID;
		this.warehouseID = warehouseID;
		this.shipmentMethod = shipmentMethod;
		this.weight = weight;
		this.receiptDate = receiptDate;
	}
	
	/**
	 *support 4 different types of shipping methods
	 */
	public enum ShipmentMethod {
		air, truck, ship, rail;
	}

	/**
	 * Getter methods
	 */
	public String getShipmentID(){return this.shipmentID;}
	public String getWarehouseID(){return this.warehouseID;}
	public String getShipmentMethod(){return this.shipmentMethod;}
	public Number getWeight(){return this.weight;}
	public long getDateInMS(){return this.receiptDate;}
	public Date getDate(){
		Timestamp ts = new Timestamp(this.receiptDate);  
		Date date = ts;  
		return date; 
	}

	/**
	 * displays all contents in shipment
	 */
	@Override
	public String toString(){
		String output = "";
		output += "Shipment ID: " + this.getShipmentID() + "\nWarehouse ID: " + this.getWarehouseID() + 
				"\nShipment method: " + this.getShipmentMethod() + "\nWeight: " + this.getWeight() + 
				"\nDate: " + this.getDate() + "\n";
		return output;
	}

}
