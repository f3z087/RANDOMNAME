package edu.metrostate.ics372.RANDOMNAME.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// State save for the program to ensure shipments are saved upon entry, with no need to load them every time the program is started
public class StateSave {

    public static String savePath = System.getProperty("user.dir");

    public static void save(){

        // HashMap is used to store the list of warehouses currently in the system
        Map<String, Warehouse> warehouseMap = new HashMap<String, Warehouse>();

        warehouseMap = WarehouseRepository.getInstance().getWhRepo();

        JSONObject shipmentObject = new JSONObject();
        JSONArray shipmentData = new JSONArray();

        // The function loops through each warehouse, grabbing its ID, then grabbing the shipments, finally adding them to a JSONObject to be printed to the
        // save file, StateSave.json
        for(Map.Entry<String, Warehouse> entry : warehouseMap.entrySet()){
            //entry.getValue().getWarehouseID());
            List<Shipment> shipments = WarehouseRepository.getInstance().getWarehouse(entry.getValue().getWarehouseID()).getListOfShipments();

            for(int i = 0; i < shipments.size(); i++) {

                JSONObject warehouseContents = new JSONObject();

                warehouseContents.put("warehouse_id", shipments.get(i).getWarehouseID());
                warehouseContents.put("shipment_method", shipments.get(i).getShipMethod());
                warehouseContents.put("shipment_id", shipments.get(i).getShipmentID());
                warehouseContents.put("weight", shipments.get(i).getWeight());

                // Change date back to milliseconds

                long timeInMS = shipments.get(i).getReceiptDate().getTime();
                warehouseContents.put("receipt_date", timeInMS);

                shipmentData.add(warehouseContents);
            }
            shipmentObject.put("warehouse_contents", shipmentData);


        }

        // write json file
        try (FileWriter file = new FileWriter(savePath.toString() + "/StateSave.json")) {
            file.write(shipmentObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}










