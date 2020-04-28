package edu.metrostate.ics372_assignment3.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import edu.metrostate.ics372_assignment3.R;
import edu.metrostate.ics372_assignment3.model.ImportShipmentsJSON;
import edu.metrostate.ics372_assignment3.model.ImportShipmentsXML;

public class AddShipFromFile extends AppCompatActivity {
    private final int CHOOSE_FILE = 1;
    private Uri fileUri;
    private String fileType;
    private String filePath;
    private Button chooseFileButton;
    String[] mimeTypes = {"application/json", "text/xml"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the view from activity_add_shipment_from_file
        setContentView(R.layout.activity_add_shipment_from_file);

        chooseFileButton = (Button) findViewById(R.id.chooseFileButton);

        chooseFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Allows user to select a file. Only xml and json files are selectable.
               Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
               intent.addCategory(Intent.CATEGORY_OPENABLE);
               intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes );
               intent.setType("*/*");

               startActivityForResult(intent, CHOOSE_FILE);

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int request, int result, Intent data)
    {
        //gets data from the selected file including the file type and filepath.
        switch(request){
            case CHOOSE_FILE:
                if (result == -1)
                {
                    fileUri = data.getData();
                    fileType = getContentResolver().getType(fileUri);
                    filePath = fileUri.getPath();
                }

                break;
        }

        final AlertDialog.Builder builder = new  AlertDialog.Builder(this);
        builder.setMessage("Import Successful");
        AlertDialog success = builder.create();

        builder.setMessage("Import Failed: Incorrect File Type");
        AlertDialog failed = builder.create();

        //checks whether a file is json or xml and sends the filepath to the respective class for
        // importing
        if (fileType.equalsIgnoreCase("application/json"))
        {
            ImportShipmentsJSON json = new ImportShipmentsJSON();
            json.readInShipments(filePath);

            success.show();
        }
        else if (fileType.equalsIgnoreCase("text/xml"))
        {
            ImportShipmentsXML xml = new ImportShipmentsXML();
            xml.readInShipmentsXML(filePath);

            success.show();
        }
        else
        {
            failed.show();
        }

    }
}

