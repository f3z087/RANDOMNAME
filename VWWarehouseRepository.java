package edu.metrostate.ics372.snowywhitemn;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class to manage the different warehouse objects.
 * 
 * @author RANDOMNAME Group Project (https://github.com/f3z087/RANDOMNAME)
 *
 */
public class VWWarehouseRepository {

	private Map<String, VWWarehouse> whRepo = new HashMap<>();
	private static VWWarehouseRepository singleton;

	private VWWarehouseRepository() {

	}

	/**
	 * Get and instance of the warehouse repository. Will create a new instance if
	 * one has not already been created.
	 * 
	 * @return warehouse repository instance
	 */
	public static VWWarehouseRepository getInstance() {
		if (singleton == null) {
			singleton = new VWWarehouseRepository();
		}
		return singleton;
	}

	/**
	 * Gets the specified warehouse object from the warehouse repository map.
	 * 
	 * @param warehouseId
	 * @return warehouse object
	 */
	public VWWarehouse getWarehouse(String warehouseId) {

		if (whRepo.containsKey(warehouseId)) {
			return whRepo.get(warehouseId);
		} else {
			VWWarehouse warehouse = new VWWarehouse(warehouseId);
			// add new warehouse to map here
			whRepo.put(warehouseId, warehouse);
			return warehouse;
		}

	}

}
