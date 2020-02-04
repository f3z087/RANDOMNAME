/**
 * Choose file from directory
 * @author Kenji Thao
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class ChooseFile  {

	private JFileChooser fileChooser = new JFileChooser();
	private File inputDirectory;
	private java.io.File inputFile;

	/**
	 * choose file
	 * save directory of file
	 * @throws Exception
	 */
	public void openFile() throws FileNotFoundException{

		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			inputFile = fileChooser.getSelectedFile();
			inputDirectory = fileChooser.getCurrentDirectory();
		}
		else{System.out.println("No file selected");}
	}

	/**
	 * getter methods
	 * @return their respective values
	 */
	public File getFile(){return this.inputFile;}
	public String getDirectory(){return this.inputDirectory.getPath();}

}
