package edu.metrostate.ics372.RANDOMNAME.view;

import edu.metrostate.ics372.RANDOMNAME.model.ChooseFile;
import edu.metrostate.ics372.RANDOMNAME.model.ImportShipmentsJSON;
import edu.metrostate.ics372.RANDOMNAME.model.ImportShipmentsXML;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Controller class for AddShipmentFromFile form.
 *
 * @author RANDOMNAME Group Project (https://github.com/f3z087/RANDOMNAME)
 */
public class AddShipmentFromFile extends JPanel {
    private JButton JSONFileButton;
    private JButton XMLFileButton;
    private JPanel addShipmentFromFileForm;

    /**
     * No argument constructor, enables all the button listeners.
     */
    public AddShipmentFromFile() {
        JFrame frame = new JFrame("AddShipmentFromFile");
        frame.setContentPane(addShipmentFromFileForm);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        JSONFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseFile cf = new ChooseFile();
                try {
                    cf.openFile();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File not found.");
                }
                //use ImportShipmentsJSON to import all shipments and add them to the specific warehouse listOfShipments
                ImportShipmentsJSON.readInShipments(cf.getFile().getPath());
                JOptionPane.showMessageDialog(null, "Shipments added to warehouses.");

            }
        });
        XMLFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ChooseFile cf = new ChooseFile();
                try {
                    cf.openFile();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "File not found.");
                }

                //use ImportShipmentsXML to import all shipments and add them to the specific warehouse listOfShipments
		        ImportShipmentsXML.readInShipmentsXML(cf.getFile().getPath());
                JOptionPane.showMessageDialog(null, "Shipments added to warehouses.");


            }
        });
    }

}
