/**
 * Driver class
 * gets and parse json file
 * create warehouse and shipments objects
 * prompts user for adding shipments or ending freight receipt
 * writes and exports all shipments from warehouse
 * 
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

public class Driver 
{ 
	public static void main(String[] args) throws Exception  
	{ 
		/**
		 * choose file
		 */
		System.out.println("Choose a file");
		ChooseFile cf = new ChooseFile();
		cf.openFile();
		System.out.println("File chosen: " + cf.getFile());

		/**
		 * warehouse and shipment collection
		 */
		List<Warehouse> warehouseArr = new ArrayList<>();
		List<Shipment> shipmentArr = new ArrayList<>();

		/**
		 * start of json parsing
		 */
		Object obj = new JSONParser().parse(new FileReader(cf.getFile())); 
		JSONObject jsonObj = (JSONObject) obj; 
		JSONArray jsonArr = (JSONArray) jsonObj.get("warehouse_contents");

		/**
		 * getting all information from JSON file
		 * assigning them to their respective values
		 * creating Warehouse and Shipment arrays with values
		 */
		for (int i = 0; i < jsonArr.size(); i++ ){
			JSONObject content = (JSONObject) jsonArr.get(i);
			String warehouseID = (String) content.get("warehouse_id"); 
			String shipmentMethod = (String) content.get("shipment_method"); 
			String shipmentID = (String) content.get("shipment_id"); 
			long weight = (long) content.get("weight"); 
			long receiptDate = (long) content.get("receipt_date");
			warehouseArr.add(new Warehouse(warehouseID, shipmentID, weight));
			shipmentArr.add(new Shipment(shipmentID, warehouseID, shipmentMethod, weight, receiptDate));
		}

		/**
		 * display all current shipments and metadata
		 */
		System.out.println("\nCurrent shipments: \n");
		for(int i = 0; i < shipmentArr.size(); i++){
			System.out.println(shipmentArr.get(i));
			System.out.println("-----------------------------------");
		}

		/**
		 * getting unique warehouse IDs
		 */
		Set<String> warehouseIDSet = new LinkedHashSet<>();
		for(int i = 0; i < warehouseArr.size(); i++){
			warehouseIDSet.add(warehouseArr.get(i).getWarehouseID());
		}

		/**
		 * display all current warehouses and its contents
		 */
		System.out.println("\nCurrent warehouses: \n");
		for(int i = 0; i < warehouseIDSet.size(); i++){
			Iterator<String> it = warehouseIDSet.iterator(); 
			while (it.hasNext()){ 
				if(warehouseArr.get(i).getWarehouseID().equals(it.next()))
					System.out.println(warehouseArr.get(i).toString());
			}
		}

		System.out.println("------------------------------------------------------");

		Scanner scan = new Scanner(System.in);
		boolean quit = false;
		String userInput;
		String inputWarehouseID = null;

		/**
		 * choose to add shipments, end receipt, or exit
		 */
		outerloop0:
			while(!quit){
				outerloop00:
					while(!quit){
						System.out.println("Do you want to add shipments or end receipt? (add/end) <exit> to exit");
						userInput = scan.nextLine();
						while(!quit){
							if(userInput.equalsIgnoreCase("exit")){
								System.out.println("Exiting.");
								break outerloop0;
							}
							/**
							 * prompts user to add incoming shipments
							 */
							else if(userInput.equalsIgnoreCase("add")){
								outerloop1:
									while(!quit){
										outerloop2:
											while(!quit){
												System.out.println("Here to add a shipment? (yes/no)");
												userInput = scan.nextLine();
												while(!quit){
													if(userInput.equalsIgnoreCase("no")){
														System.out.println("Exiting adding.");
														break outerloop1;
													}
													else if(!userInput.equalsIgnoreCase("yes")){
														System.out.println("Invalid input. Try again.");
														break;
													}
													else if(userInput.equalsIgnoreCase("yes")){
														break outerloop2;
													}
												}
											}
							outerloop3:
								while(!quit){
									System.out.println("Choose and enable a warehouse. (Enter warehouseID)");	
									userInput = scan.nextLine();
									outerloop30:
										while(!quit){
											Iterator<String> it = warehouseIDSet.iterator(); 
											while (it.hasNext()){
												if(userInput.equals(it.next())){
													System.out.println("New entry for... " + userInput);
													inputWarehouseID = userInput;
													for(int i = 0; i < warehouseArr.size(); i++){
														if(warehouseArr.get(i).getWarehouseID().equals(inputWarehouseID)){
															warehouseArr.get(i).enableFreightReceipt();
															if(warehouseArr.get(i).getHasEnded() == true){
																break outerloop30;
															}
														}
													}
													break outerloop3;
												}
											}
											System.out.println("Invalid input. Try again.");
											break;
										}
								}
											outerloop4:
												while(!quit){
													String warehouseID = inputWarehouseID;

													System.out.println("Enter shipment method (air/rail/ship/truck");	
													userInput = scan.nextLine();
													String shipmentMethod = userInput;

													System.out.println("Enter shipment ID");
													userInput = scan.nextLine();
													String shipmentID = userInput;

													System.out.println("Enter weight (number only)");
													userInput = scan.nextLine();
													long weight = Long.parseLong(userInput);

													Date date = new Date();
													long receiptDate = date.getTime();

													for(int i = 0; i < warehouseArr.size(); i++){
														if(warehouseArr.get(i).getWarehouseID().equals(warehouseID)){
															warehouseArr.get(i).addIncomingShipment(new Shipment(shipmentID, warehouseID, shipmentMethod, weight, receiptDate));
															warehouseArr.add(new Warehouse(warehouseID, shipmentID, weight));
															shipmentArr.add(new Shipment(shipmentID, warehouseID, shipmentMethod, weight, receiptDate));
															break;
														}
													}
													break outerloop4;
												}
									}
							break;
							}
							/**
							 * prompts user to end freight receipt
							 */
							else if(userInput.equalsIgnoreCase("end")){
								outerloop5:
									while(!quit){
										outerloop6:
											while(!quit){
												System.out.println("Here to end freight receipt? (yes/no)");
												userInput = scan.nextLine();
												while(!quit){
													if(userInput.equalsIgnoreCase("no")){
														System.out.println("Exiting end freight receipt.");
														break outerloop5;
													}
													else if(!userInput.equalsIgnoreCase("yes")){
														System.out.println("Invalid input. Try again.");
														break;
													}
													else if(userInput.equalsIgnoreCase("yes")){
														break outerloop6;
													}
												}
											}
							outerloop7:
								while(!quit){
									while(!quit){
										System.out.println("Choose a warehouse to end freight receipt. (Enter warehouseID)");
										userInput = scan.nextLine();
										while(!quit){
											Iterator<String> it = warehouseIDSet.iterator(); 
											while (it.hasNext()){
												if(userInput.equals(it.next())){
													System.out.println("Ending freight receipt for " + userInput);
													for(int i = 0; i < warehouseArr.size(); i++){
														if(warehouseArr.get(i).getWarehouseID().equals(userInput)){
															warehouseArr.get(i).endFreightReceipt();
														}
													}
													break outerloop7;
												}
											}
											System.out.println("Invalid input. Try again.");
											break;
										}
									}
								}
									}
							break outerloop00;
							}
							else{
								System.out.println("Invalid input. Try again.");
								break;
							}
						}
					}
			}
		scan.close();

		System.out.println("------------------------------------------------------");

		/**
		 * display updated shipments and metadata
		 */
		System.out.println("\nUpdated shipments: \n");
		for(int i = 0; i < shipmentArr.size(); i++){
			System.out.println(shipmentArr.get(i));
			System.out.println("-----------------------------------");
		}

		/**
		 * display updated warehouses and its contents
		 */
		System.out.println("\nUpdated warehouses: \n");
		for(int i = 0; i < warehouseIDSet.size(); i++){
			Iterator<String> it = warehouseIDSet.iterator(); 
			while (it.hasNext()){ 
				if(warehouseArr.get(i).getWarehouseID().equals(it.next()))
					System.out.println(warehouseArr.get(i).toString());
			}
		}

		/**
		 * start writing json file process
		 */
		JSONObject jsonObjOut = new JSONObject();
		JSONArray jsonArrOut = new JSONArray();

		/**
		 * creating warehouse ID json array
		 */
		Iterator<String> it = warehouseIDSet.iterator();
		while (it.hasNext()){ 
			jsonArrOut.add(it.next());
		}

		/**
		 * creating json array
		 * adding shipments with matching warehouse IDs in a json array
		 * assigning them to correct warehouses
		 */
		Map warehouseContentsMap = new LinkedHashMap();
		for(int j = 0; j < jsonArrOut.size(); j++){
			JSONArray tempArr = new JSONArray();
			for(int i = 0; i < shipmentArr.size(); i++){
				if(jsonArrOut.get(j).equals(shipmentArr.get(i).getWarehouseID())){
					warehouseContentsMap = new LinkedHashMap(4);
					warehouseContentsMap.put("shipment_id", shipmentArr.get(i).getShipmentID());
					warehouseContentsMap.put("shipment_method", shipmentArr.get(i).getShipmentMethod());
					warehouseContentsMap.put("weight", shipmentArr.get(i).getWeight());
					warehouseContentsMap.put("receipt_date", shipmentArr.get(i).getDateInMS());
					tempArr.add(warehouseContentsMap);
				}
			}
			jsonObjOut.put(jsonArrOut.get(j), tempArr);
		}
		jsonObjOut.put("warehouses", jsonArrOut);

		/**
		 * export all shipments to warehouse in single json file
		 */
		try{
			PrintWriter warehouseShipmentOut = new PrintWriter(cf.getDirectory()+"\\warehouseShipmentsOutput.json");
			warehouseShipmentOut.write(jsonObjOut.toJSONString());
			System.out.println("JSON file created");

			warehouseShipmentOut.flush();
			warehouseShipmentOut.close();
		}
		catch(FileNotFoundException e){
			System.out.println("JSON file not found");
		}
		catch(Exception e){
			System.out.println("JSON file failed");
		}
	}
}


