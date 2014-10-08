/**
 * 
 */
package com.riker.MountianSpider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/****************************************************************************
 * <b>Title</b>: ConfigFileLoader.java <p/>
 * <b>Project</b>: Mountain Spider <p/>
 * <b>Description: </b> This method will connect with a local file and load 
 * configuration data into the parent class.
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 8, 2014<p/>
 * @updates:
 ****************************************************************************/
public class ConfigFileLoader {

	Map<String, String> properties = new HashMap<String, String>();

	/**
	 * empty constructor
	 */
	public ConfigFileLoader() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * locates the config file in the scripts folder, and loads the file as a hash map
	 */
	public void configData(){

		try{
			int separator = 0;
			BufferedReader br = new BufferedReader(new FileReader(
					"scripts/MountainSpider.Property"));
			String line = br.readLine();

			while (line != null) {
				separator = line.indexOf("='");
				if (separator != 0 && separator != -1){
					//filling the property map
					properties.put(line.substring(0, separator), 
							line.substring(separator + 2));
				}
				line = br.readLine();
			}
			br.close();
		}catch (IOException e){
			//TODO error message
			System.out.println("file not found");
		}
		
	}

	/**
	 * @return the properties
	 */
	public Map<String, String> getProprties() {
		return properties;
	}

	/**
	 * @param proprties the properties to set
	 */
	public void setProprties(Map<String, String> proprties) {
		this.properties = proprties;
	}
}
