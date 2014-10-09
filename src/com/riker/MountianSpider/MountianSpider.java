
package com.riker.MountianSpider;

import java.util.List;

public class MountianSpider {

	private byte[] data = null;
	private String targetHost = null;
	private String targetFilePath = null;
	private String targetProtocol = null;
	private String targetCode = null;
	private String targetCodeEnd = null;
	private String savePath = null;
	private String saveExtension = null;

	Messages mg = new Messages();

	/**
	 * Empty constructor to build an instance
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
			ConnectionManager cm = new ConnectionManager();
			data = cm.getData(cm.connectTargetWebsite(targetHost, targetFilePath, 
					targetProtocol, mg));
			System.out.println(mg.displayMessages(mg.PRIMARY_TARGET));

			//parses the byte array for the target code
			DataParser dp = new DataParser(data, targetCode, targetCodeEnd);
			List<String> secondaryFilePath = dp.ParseForTarget(mg);

			//iterates over secondary file list and saves data 
			saveSecondaryTargetFiles(secondaryFilePath, cm);

		}catch (NullPointerException np){
			mg.displayMessages(mg.NULL_ENCOUNTERED);
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
			ConnectionManager cm) {

		//iterate over list for new targets
		for (int i =0; i < secondaryFilePath.size(); i++ ) {

			targetFilePath = secondaryFilePath.get(i);
			//connect to new target
			System.out.println(mg.displayMessages(mg.SECONDARY_TARGET));
			data = cm.getData(cm.connectTargetWebsite(targetHost, targetFilePath, 
					targetProtocol, mg));

			//saving message
			System.out.println(mg.displayMessages(mg.SAVING));
			
			FileManager fm = new FileManager();

			//checks for home or root links name and assigns default
			if (secondaryFilePath.get(i).equals("/")){
				fm.setName("home");
			}else{
				fm.setName(secondaryFilePath.get(i));
			}

			fm.setExtension(saveExtension);
			fm.setPath(savePath);
			fm.saveFile(data);
		}

		System.out.println(mg.displayMessages(mg.COMPLETE));
		
	}

	/**
	 * This method loads the search parameters from the config file into the 
	 * @param mg2 
	 * @return
	 */
	private ConfigFileLoader loadSearchParameter() {
		//loads config file and sets up setters for filling data
		ConfigFileLoader cfl = new ConfigFileLoader();
		cfl.configData(mg);

		//setting configuration variables to spider variables

		targetProtocol = cfl.getTargetProtocol();
		targetHost = cfl.getTargetHost();
		targetFilePath = cfl.getTargetFilePath();
		targetCode = cfl.getTargetCode();
		targetCodeEnd = cfl.getTargetCodeEnd();
		savePath = cfl.getSavePath();
		saveExtension = cfl.getSaveExtension();

		return cfl;
	}
}
