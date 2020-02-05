package edu.metrostate.ics372.snowywhitemn;

import java.util.HashMap;
import java.util.Map;

public class VWWarehouseRepository {

	private Map<String, VWWarehouse> whRepo = new HashMap<>();
	private static VWWarehouseRepository singleton;

	private VWWarehouseRepository() {

	}

	public static VWWarehouseRepository getInstance() {
		if (singleton == null) {
			singleton = new VWWarehouseRepository();
		}
		return singleton;
	}

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
