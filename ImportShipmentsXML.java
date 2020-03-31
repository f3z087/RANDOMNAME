package edu.metrostate.ics372.RANDOMNAME.model;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Fulfill New Feature #3 on Group 2 Assignment sheet. This class should take the XML shipments and convert them to
 * shipment objects. Similar to what we did when we import shipments from a json file.
 * @author RANDOMNAME Group (https://github.com/f3z087/RANDOMNAME)
 */

// https://howtodoinjava.com/xml/read-xml-dom-parser-example/

public class ImportShipmentsXML {

    public static void readInShipmentsXML(String filePath) {

        List<Shipment> shipments = new ArrayList();

        String fp = filePath;

        try {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(fp));

        Element root = document.getDocumentElement();

        NodeList warehouses = document.getElementsByTagName("Warehouse");

            String warehouseId;
            String warehouseName;
            String shipmentId;
            String shipmentMethod;
            double weight = 0.00;
            Long shipmentReceiptDate;


        // Iterate through warehouses
        for (int i = 0; i < warehouses.getLength(); i++){

            Element warehouse = (Element)warehouses.item(i);
            warehouseId = warehouse.getAttribute("id");
            warehouseName = warehouse.getAttribute("name");

            // Get a list of shipments to iterate through
            NodeList shipmentElements = warehouse.getElementsByTagName("Shipment");

            System.out.println("Warehouse ID: " + warehouseId);
            System.out.println("Warehouse Name: " + warehouseName);

            // Iterate through shipments and grab characteristics
            for(int j = 0; j < shipmentElements.getLength(); j++){

                Node shipNode = shipmentElements.item(j);

                Element shipElement = (Element) shipNode;

                if(shipElement.getAttribute("id").isEmpty()) {
                    shipmentId = "";
                } else
                    shipmentId = shipElement.getAttribute("id");

                if(shipElement.getAttribute("type").isEmpty()) {
                    shipmentMethod = "";
                } else
                    shipmentMethod = shipElement.getAttribute("type").toLowerCase();

                if(shipElement.getElementsByTagName("Weight").item(0) == null) {
                    weight = 0.00;
                } else
                    weight = Double.parseDouble(shipElement.getElementsByTagName("Weight").item(0).getTextContent());

                if(shipElement.getElementsByTagName("ReceiptDate").item(0) == null) {
                    shipmentReceiptDate = Instant.now().toEpochMilli();
                } else
                    shipmentReceiptDate = Long.parseLong(shipElement.getElementsByTagName("ReceiptDate").item(0).getTextContent());

                // Convert shipment to millisecond format
                Date receiptDate = new Date(shipmentReceiptDate);

                // Create shipment object
                Shipment shipment = new Shipment(warehouseId, shipmentMethod, shipmentId, weight, receiptDate);

                // use this as a check to make sure it is a valid shipment
                if (shipment.getReceiptDate() != null) {
                    // add shipment to List
                    shipments.add(shipment);
                }

                System.out.println();
                System.out.println("Shipment ID: " + shipmentId);
                System.out.println("Shipment Method: " + shipmentMethod);
                System.out.println("Weight: " + weight);
                System.out.println("Date: " + shipmentReceiptDate);


            }
        }

        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // This adds the shipments to warehouses
        for (Shipment shipment : shipments) {

            // get the warehouse if it exists
            if(WarehouseRepository.getInstance().warehouseExists(shipment.getWarehouseID())){
                Warehouse warehouse = WarehouseRepository.getInstance().getWarehouse(shipment.getWarehouseID());
                warehouse.addIncomingShipment(shipment);
            }else{

                WarehouseRepository.getInstance().addWarehouse(shipment.getWarehouseID(), null);
                Warehouse warehouse = WarehouseRepository.getInstance().getWarehouse(shipment.getWarehouseID());
                warehouse.addIncomingShipment(shipment);

            }

        }

    }



}
