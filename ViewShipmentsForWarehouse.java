package edu.metrostate.ics372.RANDOMNAME.view;

import edu.metrostate.ics372.RANDOMNAME.model.PrintShipments;
import edu.metrostate.ics372.RANDOMNAME.model.Shipment;
import edu.metrostate.ics372.RANDOMNAME.model.Warehouse;
import edu.metrostate.ics372.RANDOMNAME.model.WarehouseRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * Controller class for the ViewShipmentsForWarehouse form
 * @author  RANDOMNAME Group Project (https://github.com/f3z087/RANDOMNAME)
 */
public class ViewShipmentsForWarehouse extends JPanel{
    private JButton printToFileButton;
    private JButton viewButton;
    private JPanel viewShipForWareForm;
    private JTextField warehouseIDText;
    private JTextArea listOfShipTextArea;

    /**
     * No argument constructor, contains button listeners and events.
     */
    public ViewShipmentsForWarehouse() {
        JFrame frame = new JFrame("ViewShipmentsForWarehouse");
        frame.setContentPane(viewShipForWareForm);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get text from warehouseIDText field
                String warehouseID = warehouseIDText.getText();
                //get warehouse object from whRepo with indicated warehouseID
                if (WarehouseRepository.getInstance().warehouseExists(warehouseID)) {
                    //get the list of shipments of this warehouseID
                    List<Shipment> shipments = WarehouseRepository.getInstance().getWarehouse(warehouseID).getListOfShipments();
                    //display the list of shipments in the listOfShipTextArea
                    for(Shipment shipment : shipments){
                        listOfShipTextArea.append(shipment.toString());
                        listOfShipTextArea.append("\n");
                    }

                } else {
                    //display message warehouse does not exist
                    JOptionPane.showMessageDialog(null, "Warehouse does not exist.");
                }


            }
        });
        printToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //use the text in the warehouseIDText field
                String warehouseID = warehouseIDText.getText();
                //get the warehouse object from whRepo with indicated warehouseID
                if (WarehouseRepository.getInstance().warehouseExists(warehouseID)) {
                    //get the list of shipments for the warehouse
                    List shipments = WarehouseRepository.getInstance().getWarehouse(warehouseID).getListOfShipments();
                    //and print the list to a file PrintShipments class**
                    PrintShipments ps = new PrintShipments(shipments);
                    try {
                        ps.printReceivedShipments();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    //display message warehouse does not exist
                    JOptionPane.showMessageDialog(null, "Warehouse does not exist.");
                }


            }
        });
    }

}
