
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

			data = MtSdCon.getData(MtSdCon.connectTargetWebsite(targetHost, 
					targetFilePath, targetProtocol));


			System.out.println(mg.displayMessages(mg.PRIMARY_TARGET));

			//parses the byte array for the target code
			DataParser dp = new DataParser(data, targetCode, targetCodeEnd);
			List<String> secondaryFilePath = dp.ParseForTarget();

			//iterates over secondary file list and saves data 
			saveSecondaryTargetFiles(secondaryFilePath, MtSdCon);

		}catch (NullPointerException | IOException np){
			log.error("An error has occured in the process method");
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
			//connect to new target
			System.out.println(mg.displayMessages(mg.SECONDARY_TARGET));

			try {
				data = MtSdCon.getData(MtSdCon.connectTargetWebsite(targetHost, 
						targetFilePath, targetProtocol));
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

			StringBuilder sb = new StringBuilder();
			sb.append(savePath);
			sb.append(name);
			sb.append(".");
			sb.append(saveExtension);

			fm.setName(sb.toString());
			log.debug("string builders value is: " + sb.toString());
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
			System.out.println(mg.displayMessages(mg.FILE_NOT_FOUND_ERROR));
			e.printStackTrace();
		}

		//setting configuration variables to spider variables
		if (cfl.getProperties().containsKey("targetProtocol")){
			targetProtocol = cfl.getProperties().get("targetProtocol");
			log.debug("target protocol is: " + targetProtocol);
		}else {
			log.error("target protocol not found throw missing data exception");
		}

		if (cfl.getProperties().containsKey("targetHost")){
			targetHost = cfl.getProperties().get("targetHost");
			log.debug("target host is: " + targetHost);
		}else {
			log.error("target host not found throw missing data exception");
		}

		if (cfl.getProperties().containsKey("targetFilePath")){
			targetFilePath = cfl.getProperties().get("targetFilePath");
			log.debug("target file path is: " + targetFilePath);
		}else {
			log.error("target host not found throw missing data exception");
		}

		if (cfl.getProperties().containsKey("targetCode")){
			targetCode = cfl.getProperties().get("targetCode");
			log.debug("target file path is: " + targetCode);
		}else {
			log.error("target search code not found throw missing data "
					+ "exception");
		}

		if (cfl.getProperties().containsKey("targetCodeEnd")){
			targetCodeEnd = cfl.getProperties().get("targetCodeEnd");
			log.debug("target file path is: " + targetCodeEnd);
		}else {
			log.error("target end search code not found throw missing data "
					+ "exception");
		}

		if (cfl.getProperties().containsKey("savePath")){
			savePath = cfl.getProperties().get("savePath");
			log.debug("target file path is: " + savePath);
		}else {
			log.error("target end search code not found throw missing data "
					+ "exception");
		}

		if (cfl.getProperties().containsKey("saveExtension")){
			saveExtension = cfl.getProperties().get("saveExtension");
			log.debug("target file path is: " + saveExtension);
		}else {
			log.error("target end search code not found throw missing data "
					+ "exception");
		}


	}
}
