import java.util.HashMap;
import java.util.Map;

public class Warehouse {

	private Map<String, Warehouse> repo = new HashMap<>();
	private String warehouseID;
	private boolean canAdd;
	
	public Warehouse(String warehouseID) {
		this.warehouseID = warehouseID;
	}
	
	public Warehouse getWarehouse(String warehouseID) {
		if(repo.containsKey(warehouseID)) {
			return repo.get(warehouseID);
		} else {
			Warehouse warehouse = new Warehouse(warehouseID);
			repo.put(warehouseID, warehouse);
			return warehouse;
		}
	}
	
	public boolean enableFreightReceipt(){
		canAdd = true;
		return canAdd;
	}
	
	public boolean endFreightReceipt(){
		canAdd = false;
		return canAdd;
	}
	
	public void addIncomingShipment(Object obj){
		if (canAdd = false){
			System.out.println("Cannot add to warehouse.");
		}
		else {
			//add shipping info from Shipment to warehouse
		}
	}
	
	

}
