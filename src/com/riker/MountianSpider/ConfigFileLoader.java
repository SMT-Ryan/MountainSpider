/**
 * 
 */
package com.riker.MountianSpider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	private final String TARGET_PROTOCOL = "targetProtocol";
	private final String TARGET_PORT = "targetPort";
	private final String TARGET_HOST = "targetHost";
	private final String TARGET_FILE_PATH = "targetFilePath";
	private final String TARGET_SEARCH_CODE = "targetCode";
	private final String TARGET_SEARCH_CODE_DELIMITER = "targetCodeEnd";

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
	 * iterates over the map until the key matches and returns the value
	 * @return value of key
	 */
	public String searchMap(String searchKey){
		
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			if (searchKey.equals(entry.getKey())){
				return entry.getValue();
			}
		}
		return null;
	}
	
	/**
	 * returns the map value of target file path.
	 */
	public String getTargetCodeEnd(){
		 
		return searchMap(TARGET_SEARCH_CODE_DELIMITER);
	}
	
	/**
	 * returns the map value of target file path.
	 */
	public String getTargetCode(){
		 
		return searchMap(TARGET_SEARCH_CODE);
	}
	
	/**
	 * returns the map value of target file path.
	 */
	public String getTargetFilePath(){
		 
		return searchMap(TARGET_FILE_PATH);
	}
	
	/**
	 * returns the map value of target host.
	 */
	public String getTargetHost(){
		 
		return searchMap(TARGET_HOST);
	}
	
	
	/**
	 * returns the map value of target port.
	 */
	public String getTargetPort(){
		 
		return searchMap(TARGET_PORT);
	}
	
	/**
	 * returns the map value of target protocol.
	 */
	public String getTargetProtocol(){
		return searchMap(TARGET_PROTOCOL);
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
