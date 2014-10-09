/**
 * 
 */
package com.riker.MountianSpider;

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


	private String name = null;
	private String extension = null;
	private String path = null;


	/**
	 * empty constructor that produces and instance of FileManager
	 */
	public FileManager() {

	}

	public void saveFile(byte[] data){
		
		String dataString = new String(data);
		
		try (PrintStream out = new PrintStream(new FileOutputStream(path + name + "." + extension))) {
			out.print(dataString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

}
