
package com.riker.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


/****************************************************************************
 * <b>Title</b>: FileManager.java <p/>
 * <b>Project</b>: WebCrescendo <p/>
 * <b>Description: </b> Takes data and saves it to a file
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 9, 2014<p/>
 * @updates:
 ****************************************************************************/
public class FileManager {


	private String outputName = null;
	private String fileLocation = null;



	/**
	 * empty constructor that produces and instance of FileManager
	 */
	public FileManager() {

	}

	public void saveFile(byte[] data) throws FileNotFoundException{

		String dataString = new String(data);
		
		try (PrintStream out = new PrintStream
				(new FileOutputStream(outputName))) {
			out.print(dataString);
		} 
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return outputName;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String outputName) {
		this.outputName = outputName;
	}

	/**
	 * @return the fileLocation
	 */
	public String getFileLocation() {
		return fileLocation;
	}

	/**
	 * @param fileLocation the fileLocation to set
	 */
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
}