package edu.metrostate.ics372.RANDOMNAME.view;

import edu.metrostate.ics372.RANDOMNAME.model.ImportShipmentsJSON;
import edu.metrostate.ics372.RANDOMNAME.model.StateSave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Main form class.
 *
 * @author RANDOMNAME Group Project (https://github.com/f3z087/RANDOMNAME)
 */
public class TransportationAdmin {
    private JButton exitButton;
    private JButton addShipmentFromFileButton;
    private JButton addShipmentManuallyButton;
    private JButton viewShipmentsForWarehouseButton;
    private JButton viewWarehousesButton;
    private JButton enableDisableFreightReceiptButton;
    private JButton addWarehouseButton;
    private JPanel transportAdminForm;

    /**
     * No argument constructor, enables the listeners for the buttons.
     */
    public TransportationAdmin() {

        addWarehouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddWarehouse();
            }
        });

        // The exit button initiates the State Save function, as well as closes the program, letting the user know the shipments added have been saved
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StateSave.save();

                try {
                    JOptionPane.showMessageDialog(null, "Shipments Saved.");
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                System.exit(0);

            }
        });
        viewWarehousesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewWarehouses();
            }
        });
        viewShipmentsForWarehouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewShipmentsForWarehouse();
            }
        });
        addShipmentManuallyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddShipmentManually();
            }
        });
        addShipmentFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddShipmentFromFile();
            }
        });
        enableDisableFreightReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EnableDisableFreightReceipt();
            }
        });
    }

    public static void main(String[] args) {

        // Initial load of saved shipments from previous sessions //
        File savedFile = new File("StateSave.json");
        if(savedFile.exists()) {
            ImportShipmentsJSON.readInShipments("StateSave.json");
        }
        // Initial load of saved shipments from previous sessions //

        JFrame frame = new JFrame("TransportationAdmin");
        frame.setContentPane(new TransportationAdmin().transportAdminForm);
        frame.setUndecorated(true);  //The Window's close button has been hidden to force use of the 'Exit' button
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
