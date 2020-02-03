// Java program to read JSON from a file 
  
import java.io.FileReader; 
import java.util.Iterator;
import java.util.Map;
import java.sql.Timestamp;    
import java.util.Date;
import java.util.HashMap;

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
		
        // parsing file "JSONExample.json" 
        Object obj = new JSONParser().parse(new FileReader(cf.getFile())); 
          
        // typecasting obj to JSONObject 
        JSONObject jo = (JSONObject) obj; 
          
        // getting firstName and lastName 
//        String warehouse_id = (String) jo.get("warehouse_id"); 
//        String shipment_method = (String) jo.get("shipment_method"); 
//        String shipment_id = (String) jo.get("shipment_id");
//        long weight = (long) jo.get("weight");
//        long receipt_date = (long) jo.get("receipt_date");
//        
//        /**
//         * convert long to date format
//         */
//        Timestamp ts = new Timestamp(receipt_date);  
//        Date date = ts;  
//        System.out.println(date);   
//          
//        System.out.println(warehouse_id); 
//        System.out.println(shipment_method); 
//        System.out.println(shipment_id); 
//        System.out.println(weight); 
//        System.out.println(receipt_date); 
          
        
        // map instantiation
        Map map = new HashMap<>(); 
          
        // iterating Map 
        Iterator<Map.Entry> itr1 = map.entrySet().iterator(); 
//        while (itr1.hasNext()) { 
//            Map.Entry pair = itr1.next(); 
//            System.out.println(pair.getKey() + " : " + pair.getValue()); 
//        } 
        
        // getting phoneNumbers 
        JSONArray ja = (JSONArray) jo.get("warehouse_contents"); 
          
        // iterating phoneNumbers 
        Iterator itr2 = ja.iterator(); 
          
        while (itr2.hasNext())  
        { 
            itr1 = ((Map) itr2.next()).entrySet().iterator(); 
            while (itr1.hasNext()) { 
                Map.Entry pair = itr1.next(); 
                System.out.println(pair.getKey() + " : " + pair.getValue()); 
            } 
        } 
    }

} 