import java.sql.Timestamp;    
import java.util.Date;

public class Shipment {

	private String warehouseID;
	private String shipmentMethod;
	private String shipmentID;
	private long weight;
	private long receiptDate;

	public Shipment(String warehouseID, String shipmentMethod, String shipmentID, long weight, long receiptDate){
		this.warehouseID = warehouseID;
		this.shipmentMethod = shipmentMethod;
		this.shipmentID = shipmentID;
		this.weight = weight;
		this.receiptDate = receiptDate;
	}

	public String getWarehouseID(){return this.warehouseID;}
	public String getShipmentMethod(){return this.shipmentMethod;}
	public String getShipmentID(){return this.shipmentID;}
	public long getweight(){return this.weight;}
	public Date getDate(){
		Timestamp ts = new Timestamp(this.receiptDate);  
		Date date = ts;  
		return date; 
	}

}
