
package com.riker.spider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.riker.configuration.ConfigFileLoader;
import com.riker.connection.ConnectionManager;
import com.riker.file.FileManager;
import com.riker.logger.LoggerShare;
import com.riker.parser.DataParser;
import com.riker.user_communication.Messages;


/****************************************************************************
 * <b>Title</b>: MountianSpider.java <p/>
 * <b>Project</b>: MountianSpider <p/>
 * <b>Description: </b> This class contains the main method and contains control 
 * of the process, that lets allow Mountain Spider to connect with a web 
 * source, and parse for target links, if found the targets are connected and 
 * stored on the local drive.  
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 10, 2014<p/>
 * @updates:
 ****************************************************************************/
public class MountianSpider extends LoggerShare {

	private byte[] data = null;
	private String targetHost = null;
	private String targetFilePath = null;
	private String targetProtocol = null;
	private String targetCode = null;
	private String targetCodeEnd = null;
	private String savePath = null;
	private String saveExtension = null;
	private final String CONFIG_FILE_PATH = "scripts/MountainSpider.Property";
	private String KEY_SEPARATOR = "='";
	private final String TARGET_PROTOCOL= "targetProtocol";
	private final String TARGET_HOST ="targetHost";
	private final String TARGET_FILE_PATH ="targetFilePath";
	private final String TARGET_SEARCH_CODE = "targetCode";
	private final String TARGET_END_SEARCH_CODE = "targetCodeEnd";
	private final String SAVE_PATH ="savePath";
	private final String SAVE_EXTENSION ="saveExtension";


	Messages mg = new Messages();

	/**
	 * Empty constructor to create an instance
	 */
	public MountianSpider() {

	}

	/**
	 * This method contains the instance of Mountain spider and calls the 
	 * process function 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		MountianSpider testSpider = new MountianSpider();
		testSpider.process();
	}

	/**
	 * This method controls the process related to connecting to a target URL 
	 * and linking to attached secondary targets.  input is loaded via 
	 * configuration file.
	 */
	private void process() {


		System.out.println(mg.displayMessages(mg.WELCOME));

		//Loads config file and sets search parameters
		loadSearchParameter();

		try{
			//Connects to target website and loads byte array data
			ConnectionManager MtSdCon = new ConnectionManager();

			StringBuilder sb = new StringBuilder();
			sb.append(targetProtocol);
			sb.append(targetHost);
			if (!targetFilePath.isEmpty()){
				sb.append("/"+targetFilePath);
			}

			log.debug("string builders value is: " + sb.toString());
			data = MtSdCon.getData(MtSdCon.connectTargetWebsite(sb.toString()));


			System.out.println(mg.displayMessages(mg.PRIMARY_TARGET));

			//parses the byte array for the target code
			DataParser dp = new DataParser(data, targetCode, targetCodeEnd);
			List<String> secondaryFilePath = dp.ParseForTarget();

			//iterates over secondary file list and saves data 
			saveSecondaryTargetFiles(secondaryFilePath, MtSdCon);

		}catch (NullPointerException | IOException np){
			log.error("An error has occured in the process method please check"
					+ " the config file for correct \n "
					+ "input, the web target or the config file itself is not "
					+ "found or non existant");
			np.printStackTrace();
		}
	}   

	/**
	 * This method iterates over the list of secondary targets and writes out
	 * the contents to a file on the local machine.  
	 * 
	 * @param secondaryFilePath The list of secondary targets
	 * @param cm the connection manager
	 * 
	 */
	private void saveSecondaryTargetFiles(List<String> secondaryFilePath, 
			ConnectionManager MtSdCon) {

		//iterate over list for new targets
		for (int i =0; i < secondaryFilePath.size(); i++ ) {
			String name = null;

			targetFilePath = secondaryFilePath.get(i);
			log.debug("secondary target path value is: " + targetFilePath);
			//connect to new target
			System.out.println(mg.displayMessages(mg.SECONDARY_TARGET));

			StringBuilder targetStB = new StringBuilder();
			targetStB.append(targetProtocol);
			targetStB.append(targetHost);

			if (!targetFilePath.isEmpty()){
				targetStB.append(targetFilePath);
			}

			log.debug("string builders value is: " + targetStB.toString());
			try {
				data = MtSdCon.getData(MtSdCon.
						connectTargetWebsite(targetStB.toString()));
			} catch (IOException e1) {
				log.error("An error has occured in the secondary file loop");
				e1.printStackTrace();
			}


			//saving message
			System.out.println(mg.displayMessages(mg.SAVING));

			FileManager fm = new FileManager();

			//checks for home or root links name and assigns default
			if (secondaryFilePath.get(i).equals("/")){
				name = "/home";
				log.debug("Link to the root \"/\" was encountered, the file "
						+ "will be named home by default");
			}else{
				name = secondaryFilePath.get(i);
			}

			StringBuilder saveStB = new StringBuilder();
			saveStB.append(savePath);
			saveStB.append(name);
			saveStB.append(".");
			saveStB.append(saveExtension);

			fm.setName(saveStB.toString());
			log.debug("string builders value is: " + saveStB.toString());
			try {
				fm.saveFile(data);
				fm.setFileLocation(savePath);
				log.debug("save path value is: " + savePath);
			} catch (FileNotFoundException e) {
				File dir = new File(savePath);
				dir.mkdir();
				i--;
				log.debug("the save file was missing and has been created "
						+ "reducing the counter by one and restarting loop");
			}


		}

		System.out.println(mg.displayMessages(mg.COMPLETE));

	}

	/**
	 * This method loads the search parameters from the config file into the 
	 */
	private void loadSearchParameter() {
		//loads config file and sets up setters for filling data
		ConfigFileLoader cfl = new ConfigFileLoader();
		cfl.setConfigFilePath(CONFIG_FILE_PATH);

		try {
			cfl.configData(KEY_SEPARATOR);
		} catch (IOException e) {
			log.error("An error has occured, the file isnt found or the file"
					+ " isnt readable.");
			e.printStackTrace();
		}

		//setting configuration variables to spider variables

		targetProtocol = cfl.getProperties().get(TARGET_PROTOCOL);
		log.debug("target protocol is: " + targetProtocol);

		targetHost = cfl.getProperties().get(TARGET_HOST);
		log.debug("target host is: " + targetHost);

		targetFilePath = cfl.getProperties().get(TARGET_FILE_PATH);
		log.debug("target file path is: " + targetFilePath);

		targetCode = cfl.getProperties().get(TARGET_SEARCH_CODE);
		log.debug("target file path is: " + targetCode);

		targetCodeEnd = cfl.getProperties().get(TARGET_END_SEARCH_CODE);
		log.debug("target file path is: " + targetCodeEnd);

		savePath = cfl.getProperties().get(SAVE_PATH);
		log.debug("target file path is: " + savePath);

		saveExtension = cfl.getProperties().get(SAVE_EXTENSION);
		log.debug("target file path is: " + saveExtension);

		if (targetProtocol== null || targetHost== null ||
				targetFilePath == null){
			log.error("missing data error");
		}

		if (targetCode== null || targetCodeEnd== null ||
				savePath == null || saveExtension == null){
			log.error("missing data error");
		}

	}
}
