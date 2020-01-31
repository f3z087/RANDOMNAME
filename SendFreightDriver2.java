package edu.metrostate.ics372.snowywhitemn;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.metrostate.ics372.snowywhitemn.Shipment.ShipmentMethod;

/**
 * Using j simple
 * 
 * @author vwhite
 *
 */
public class SendFreightDriver2 {

	public static void main(String[] args) {

		List<Shipment> shipments = new ArrayList<>();

		/*
		 * Scanner reader = new Scanner(System.in); System.out.
		 * println("Enter new shipments manually or from a file? (manually / file)");
		 * String answer = reader.nextLine();
		 * 
		 * if (answer.toLowerCase().equals("manually")) { // prompts to create shipment
		 * by setting shipment variables // will have to add to specified warehouse list
		 * } else if (answer.toLowerCase().equals("file")) {
		 * 
		 * // prompt to enter file path of file to read in // check if valid file path
		 * // will have to shipments to specified warehouses // will be asking for local
		 * file path from user String filePath =
		 * SendFreightDriver.class.getResource("example.json").getPath(); shipments =
		 * readInShipments(filePath);
		 * 
		 * }
		 */
		String filePath = SendFreightDriver2.class.getResource("example.json").getPath();
		shipments = readInShipments(filePath);

	}

	/**
	 * @param filePath
	 * @return
	 */
	public static List<Shipment> readInShipments(String filePath) {
		List<Shipment> shipments = new ArrayList<>();

		// https://www.javaguides.net/2019/07/jsonsimple-tutorial-read-and-write-json-in-java.html

		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(filePath)) {

			try {
				// read JSON file
				Object obj = jsonParser.parse(reader);

				// convert object to JSONObject
				JSONObject jsonObject = (JSONObject) obj;

				JSONArray arrayOfShipments = (JSONArray) jsonObject.get("warehouse_contents");

				// check size if more than 0, there is objects inside the array
				System.out.println(arrayOfShipments.size());

				// create shipment objects
				Iterator i = arrayOfShipments.iterator();
				while (i.hasNext()) {

					JSONObject innerObject = (JSONObject) i.next();
					int warehouseId = Integer.parseInt((String) innerObject.get("warehouse_id"));
					System.out.println(warehouseId);

					ShipmentMethod shipmentMethod = ShipmentMethod.valueOf((String) innerObject.get("shipment_method"));
					System.out.println(shipmentMethod);

					String shipmentId = (String) innerObject.get("shipment_id");
					System.out.println(shipmentId);

					var tempWeight = innerObject.get("weight");
					System.out.println(tempWeight);

					Long receiptDate = (Long) innerObject.get("receipt_date");
					System.out.println(receiptDate);

					Date currDate = new Date(receiptDate);

					Long weight = (long) 0.00;
					if (tempWeight instanceof Double) {
						weight = ((Double) tempWeight).longValue();
					} else if (tempWeight instanceof Long) {
						weight = (Long) tempWeight;
					}

					Shipment shipment = new Shipment(warehouseId, shipmentMethod, shipmentId, weight);

					if (shipment.getWeight() != 0.00) {
						shipment.setReceiptDate(currDate);
						shipments.add(shipment);
					}

					System.out.println(shipment);

				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return shipments;

	}

}
