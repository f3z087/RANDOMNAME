package edu.metrostate.ics372_assignment3.model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    /**
     * Method used to print shipments to JSON file
     */
    public static void printReceivedShipments(String warehouseID) throws IOException {

        //print the list of the warehouses shipments to a file on the device

        List<Shipment> listOfShipments = WarehouseRepository.getInstance().getWarehouse(warehouseID).getListOfShipments();

        //SDCard directory is used as the path to save to


        //File file = new File(dir, "Warehouse" + warehouseIDText.getText().toString() + ".json")

        JSONObject shipmentObject = new JSONObject();

        JSONArray shipmentData = new JSONArray();

        // Method loops through the list of shipments provided for a given warehouse, printing them to a JSON file with a corresponding name
        for (Shipment listOfShipment : listOfShipments) {

            JSONObject warehouseContents = new JSONObject();

            warehouseContents.put("warehouse_id", listOfShipment.getWarehouseID());
            warehouseContents.put("shipment_method", listOfShipment.getShipMethod());
            warehouseContents.put("shipment_id", listOfShipment.getShipmentID());
            warehouseContents.put("weight", listOfShipment.getWeight());

            // Change date back to milliseconds

            long timeInMS = listOfShipment.getReceiptDate().getTime();
            warehouseContents.put("receipt_date", timeInMS);

            shipmentData.add(warehouseContents);
        }
        shipmentObject.put("warehouse_contents", shipmentData);

        // write json file
        try {
            File sdcard = new File("sdcard");
            File dir = new File(sdcard.getAbsolutePath() + "/WarehouseFiles/");
            dir.mkdirs();
            FileWriter file = new FileWriter(dir.toString() + "/Warehouse" + warehouseID + ".json");
            file.write(shipmentObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
