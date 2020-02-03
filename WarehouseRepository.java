package edu.metrostate.ics372.snowywhitemn;

import java.util.HashMap;
import java.util.Map;

public class WarehouseRepository {

	private Map<String, Warehouse> whRepo = new HashMap<>();
	private static WarehouseRepository singleton;

	private WarehouseRepository() {

	}

	public static WarehouseRepository getInstance() {
		if (singleton == null) {
			singleton = new WarehouseRepository();
		}
		return singleton;
	}

	public Warehouse getWarehouse(String warehouseId) {

		if (whRepo.containsKey(warehouseId)) {
			return whRepo.get(warehouseId);
		} else {
			Warehouse warehouse = new Warehouse(warehouseId);
			// add new warehouse to map here
			whRepo.put(warehouseId, warehouse);
			return warehouse;
		}

	}

}
