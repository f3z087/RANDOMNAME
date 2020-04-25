package edu.metrostate.ics372_assignment3.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import edu.metrostate.ics372_assignment3.R;
import edu.metrostate.ics372_assignment3.model.PrintShipments;
import edu.metrostate.ics372_assignment3.model.Shipment;
import edu.metrostate.ics372_assignment3.model.WarehouseRepository;

public class ViewShipFromWarehouse extends AppCompatActivity {
    private Button viewButton;
    private EditText warehouseIDText;
    private ListView shipmentsListView;
    private Button printToFileButton;
    private Dialog warehouseDNEErr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ship_for_warehouse);

        //locate view button in activity_view_ship_for_warehouse.xml
        viewButton = (Button) findViewById(R.id.viewButton);
        //capture viewButton clicks
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the warehouse ID from the warehouseIDText field
                warehouseIDText = findViewById(R.id.warehouseIDText);
                String warehouseID = warehouseIDText.getText().toString();
                //find the warehouse in the warehouse repository
                //if the warehouse exists get its list of shipments
                if(WarehouseRepository.getInstance().warehouseExists(warehouseID)){
                    //display the list of shipments in the shipmentsListView
                    shipmentsListView = findViewById(R.id.shipmentsListView);
                    ArrayAdapter<Shipment> adapter = new ArrayAdapter<Shipment>
                            (ViewShipFromWarehouse.this, android.R.layout.simple_list_item_1,
                                    WarehouseRepository.getInstance().getWarehouse(warehouseID).getListOfShipments());
                    shipmentsListView.setAdapter(adapter);
                }else{
                    //if the warehouse does not exist then display a message that the warehouse ID is invalid
                    warehouseDNEErr= new AlertDialog.Builder(ViewShipFromWarehouse.this)
                            .setMessage("Warehouse does not exist.").show();

                }

            }
        });

        //locate print to file button in activity_view_ship_for_warehouse.xml
        printToFileButton = (Button) findViewById(R.id.printToFileButton);
        //capture printToFileButton clicks
        printToFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Prints the entered warehouse's shipments to the SDCard
                try {
                    PrintShipments.printReceivedShipments(warehouseIDText.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
