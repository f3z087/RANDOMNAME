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
     * Default no argument constructor, initializes the list of shipments to be made into an JSON file
     */
    public PrintShipments(List<Shipment> listOfShipments) {
        this.listOfShipments = listOfShipments;
    }

    /**
     * NEEDS TO BE REWRITTEN TO WORK AS A CLASS - NEED TO FIGURE OUT WHERE TO PUT THE PRINTED FILE
     * Prints the listOfShipments to a json file.
     */
    public void printReceivedShipments() throws IOException {

        String savePath = System.getProperty("user.dir");

        JSONObject shipmentObject = new JSONObject();
        JSONObject warehouseContents = new JSONObject();

        warehouseContents.put("warehouse_id", this.listOfShipments.get(0).getWarehouseID());
        warehouseContents.put("shipment_method", this.listOfShipments.get(0).getShipMethod());
        warehouseContents.put("shipment_id", this.listOfShipments.get(0).getShipmentID());
        warehouseContents.put("weight", this.listOfShipments.get(0).getWeight());
        warehouseContents.put("receipt_date", this.listOfShipments.get(0).getReceiptDate());

        JSONArray shipmentData = new JSONArray();
        shipmentData.add(warehouseContents);

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
