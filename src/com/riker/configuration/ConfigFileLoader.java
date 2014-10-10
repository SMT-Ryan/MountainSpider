package com.riker.configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.riker.user_communication.Messages;
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
	String confilFilePath = null;

	/**
	 * This constructor makes an instance of the ConfigFileLoader class.
	 * @param mg 
	 */
	public ConfigFileLoader() {

	}

	/**
	 * locates the configuration file in the scripts folder, and loads the 
	 * file as a hash map
	 * @param mg 
	 */
	public void configData(Messages mg){

		try{
			int separator = 0;
			BufferedReader br = new BufferedReader(
					new FileReader(confilFilePath));
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
		}

	}

	/**
	 * returns the hash map of keys and values loaded from the file loader.
	 * @return a hash map
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * sets the hash map of keys and values
	 * @param proprties the altered hash map
	 */
	public void setProperties(Map<String, String> proprties) {
		this.properties = proprties;
	}

	/**
	 * @param CONFIG_FILE_PATH
	 */
	public void setConfigFilePath(String CONFIG_FILE_PATH) {
		confilFilePath = CONFIG_FILE_PATH;
	}
}
