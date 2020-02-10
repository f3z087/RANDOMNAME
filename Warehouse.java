/**
 * warehouse object class
 * methods for warehouse objects
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;
import org.javatuples.*; 

public class Warehouse {

	private Map<String, Warehouse> warehouseMap = new HashMap<>();
	private String warehouseID;
	private boolean canAdd;
	private boolean hasEnded = false;
	static List<Triplet<String, String, Number>> shipIDAndWeight = new ArrayList<>();

	/**
	 * constructor for existing warehouses
	 * store shipmentID and weight with warehouseID
	 * @param warehouseID
	 * @param shipmentID
	 * @param weight
	 */
	public Warehouse(String warehouseID, String shipmentID, Number weight) {
		this.warehouseID = warehouseID;
		shipIDAndWeight.add(new Triplet<String, String, Number> (warehouseID, shipmentID, weight));
		warehouseMap.put(warehouseID, this);
	}

	/**
	 * show associate shipmentID and weight with warehouseID
	 * @return set of pairs <shipmentID, weight>
	 */
	public Set<Pair<String, Number>> getWarehouseContents(){
		Set<Pair<String, Number>> contents = new LinkedHashSet<>();
		for (int i = 0; i < shipIDAndWeight.size(); i++){
			if (shipIDAndWeight.get(i).getValue0().equals(this.warehouseID)){
				contents.add(new Pair<String, Number> (shipIDAndWeight.get(i).getValue1(), shipIDAndWeight.get(i).getValue2()));
			}
		}
		return contents;
	}
	
	/**
	 * keep records for a warehouse that has ended freight receipt
	 * @return whether warehouse has ended freight receipt or not
	 */
	public String getWarehouseStatus(){
		String output = "";
		if(this.hasEnded == true){
			output += "Warehouse has ended freight receipt.";
		}
		else{
			output += "Warehouse has not ended freight receipt.";
		}
		return output;
	}

	/**
	 * getter method for warehouseID
	 * @return warehouseID
	 */
	public String getWarehouseID() {return this.warehouseID;}
	
	/**
	 * check if warehouse has ended freight receipt
	 * @return status of warehouse receipt
	 */
	public boolean getHasEnded() {return this.hasEnded;}

	/**
	 * enable freight receipt for additional shipments
	 * cannot enable if already ended
	 * @return
	 */
	public boolean enableFreightReceipt(){
		if (this.hasEnded == true){
			System.out.println("Cannot enable since it has ended.");
		}
		else {
			this.canAdd = true;
			System.out.println(this.warehouseID + " warehouse has enabled freight receipt.");
		}
		return this.hasEnded;
	}

	/**
	 * forbid adding more shipments to warehouse
	 */
	public void endFreightReceipt(){
		this.hasEnded = true;
		System.out.println(this.warehouseID + " warehouse has ended freight receipt.");
	}

	/**
	 * add shipment for warehouse
	 * can only add if warehouse is enabled
	 * @param shipmentAdd input shipment
	 */
	public void addIncomingShipment(Shipment shipmentAdd){
		if (this.canAdd == true && this.warehouseID.equals(shipmentAdd.getWarehouseID())){
			shipIDAndWeight.add(new Triplet<String, String, Number> (this.warehouseID, shipmentAdd.getShipmentID(), shipmentAdd.getWeight()));
			System.out.println("Added successfully.");
		}
		else {
			System.out.println("Cannot add. Warehouse has not been enabled or has ended.");
		}
	}

	/**
	 * prints warehouse ID and associated shipmentID and weight
	 */
	@Override
	public String toString(){
		String output = "";
		output += "Warehouse: " + this.warehouseID + " contains (format: shipmentID=weight): \t" + this.getWarehouseContents() + "\t" + this.getWarehouseStatus() + "\n";
		return output;
	}

}
