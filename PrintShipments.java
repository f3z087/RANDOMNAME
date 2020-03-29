package edu.metrostate.ics372.RANDOMNAME.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Prints the list of shipments for a warehouse.
 *
 * @author RANDOMNAME Group (https://github.com/f3z087/RANDOMNAME)
 */
public class PrintShipments {
    private List<Shipment> listOfShipments;

    /**
     * Initializes the list of shipments to be made into an JSON file
     */
    public PrintShipments(List<Shipment> listOfShipments) {
        this.listOfShipments = listOfShipments;
    }

    // Method used to print shipments to JSON File
    public void printReceivedShipments() throws IOException {

        //Project directory is used as the path to save to
        String savePath = System.getProperty("user.dir");

        JSONObject shipmentObject = new JSONObject();

        JSONArray shipmentData = new JSONArray();

        // Method loops through the list of shipments provided for a given warehouse, printing them to a JSON file with a corresponding name
        for(int i = 0; i < this.listOfShipments.size(); i++) {

            JSONObject warehouseContents = new JSONObject();

            warehouseContents.put("warehouse_id", this.listOfShipments.get(i).getWarehouseID());
            warehouseContents.put("shipment_method", this.listOfShipments.get(i).getShipMethod());
            warehouseContents.put("shipment_id", this.listOfShipments.get(i).getShipmentID());
            warehouseContents.put("weight", this.listOfShipments.get(i).getWeight());

            // Change date back to milliseconds

            long timeInMS = this.listOfShipments.get(i).getReceiptDate().getTime();
            warehouseContents.put("receipt_date", timeInMS);

            shipmentData.add(warehouseContents);
        }
        shipmentObject.put("warehouse_contents", shipmentData);

        // write json file
        try (FileWriter file = new FileWriter(savePath.toString() + "/Warehouse" + this.listOfShipments.get(0).getWarehouseID().toString() + ".json")) {
            file.write(shipmentObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
