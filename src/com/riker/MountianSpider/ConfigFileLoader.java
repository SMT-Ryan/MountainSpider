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
	private final String TARGET_PROTOCOL = "targetProtocol";
	private final String TARGET_PORT = "targetPort";
	private final String TARGET_HOST = "targetHost";
	private final String TARGET_FILE_PATH = "targetFilePath";
	private final String TARGET_SEARCH_CODE = "targetCode";
	private final String TARGET_SEARCH_CODE_DELIMITER = "targetCodeEnd";
	private final String SAVE_PATH = "savePath";
	private final String SAVE_EXTENSION = "saveExtension";

	/**
	 * This constructor makes an instance of the ConfigFileLoader class.
	 * @param mg 
	 */
	public ConfigFileLoader() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * locates the configuration file in the scripts folder, and loads the 
	 * file as a hash map
	 * @param mg 
	 */
	public void configData(Messages mg){

		try{
			int separator = 0;
			BufferedReader br = new BufferedReader(new FileReader(
					"scripts/MountainSpider.Property"));
			String line = br.readLine();

			//iterate over each line in the config file and load the has map
			//with keys from the left side of the =' and values from the right 
			//side.
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
			
			System.out.println(mg.displayMessages(mg.FILE_NOT_FOUND_ERROR));
			//TODO error message

		}

	}

	/**
	 * This method iterates over the map until the key matches and returns 
	 * the value
	 * @return string value associated with the searched for key, or null
	 * if the key is not found.
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
	 * returns the string value of map location associated with the SAVE 
	 * EXTENSION key.
	 * @return string
	 */
	public String getSaveExtension(){

		return searchMap(SAVE_EXTENSION);
	}

	/**
	 * returns the string value of map location associated with the SAVE PATH 
	 * key.
	 * @return a string
	 */
	public String getSavePath(){

		return searchMap(SAVE_PATH);
	}

	/**
	 * returns the string value of map location associated with the TARGET 
	 * SEARCH CODE DELIMITER key.
	 * @return a string
	 */
	public String getTargetCodeEnd(){

		return searchMap(TARGET_SEARCH_CODE_DELIMITER);
	}

	/**
	 * returns the string value of map location associated with the TARGET 
	 * SEARCH CODE key.
	 * @return a string
	 */
	public String getTargetCode(){

		return searchMap(TARGET_SEARCH_CODE);
	}

	/**
	 * returns the string value of map location associated with the TARGET 
	 * FILE PATH key.
	 * @return a string
	 */
	public String getTargetFilePath(){

		return searchMap(TARGET_FILE_PATH);
	}

	/**
	 * returns the string value of map location associated with the TARGET 
	 * HOST key.
	 * @return a string
	 */
	public String getTargetHost(){

		return searchMap(TARGET_HOST);
	}


	/**
	 * returns the string value of map location associated with the TARGET 
	 * port key.
	 * @return a string
	 */
	public String getTargetPort(){

		return searchMap(TARGET_PORT);
	}

	/**
	 * returns the string value of map location associated with the TARGET 
	 * PROTOCOL key.
	 * @return a string
	 */
	public String getTargetProtocol(){
		return searchMap(TARGET_PROTOCOL);
	}

	/**
	 * returns the hash map of keys and values loaded from the file loader.
	 * @return a hash map
	 */
	public Map<String, String> getProprties() {
		return properties;
	}

	/**
	 * sets the hash map of keys and values
	 * @param proprties the altered hash map
	 */
	public void setProprties(Map<String, String> proprties) {
		this.properties = proprties;
	}
}
