package app.team21.risk.views;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Last Updated on: 29/11/2018, Thursday 
 * File Select Dialog class to select the map file.
 * 
 * @author Yash Sheth
 * @version 3.0.0
 */
public class FileSelectDialog extends JFileChooser {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * This constructor allows to select the map file and load the file location.
     */
    public FileSelectDialog() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SER FILES", "ser");
        setFileFilter(filter);
        setDialogTitle("Select a map file");
        setCurrentDirectory(new File(System.getProperty("user.home")));
    }
}
