
package com.riker.MountianSpider;

import java.util.List;

public class MountianSpider {

	/**
	 * Empty constructor to build an instance
	 */
	public MountianSpider() {

	}

	/**
	 * This method contains the instance of Mountain spider and calls the 
	 * process function 
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
		byte[] data = null;
		String targetHost = null;
		String targetFilePath = null;
		String targetProtocol = null;
		String targetCode = null;
		String targetCodeEnd = null;
		String savePath = null;
		String saveExtension = null;

		//loads config file and sets up setters for filling data
		ConfigFileLoader cfl = new ConfigFileLoader();
		cfl.configData();

		//setting configuration variables to spider variables
		targetProtocol = cfl.getTargetProtocol();
		targetHost = cfl.getTargetHost();
		targetFilePath = cfl.getTargetFilePath();
		targetCode = cfl.getTargetCode();
		targetCodeEnd = cfl.getTargetCodeEnd();
		savePath = cfl.getSavePath();
		saveExtension = cfl.getSaveExtension();

		//TODO remove
		System.out.println("target website from config file" + targetProtocol + targetHost + targetFilePath);
		
		//Connects to target website and loads byte array data
		ConnectionManager cm = new ConnectionManager();
		data = cm.getData(cm.connectTargetWebsite(targetHost, targetFilePath, 
				targetProtocol));
		
		//TODO remove 
		System.out.println("byet stream lenth: " + data.length);
		
		//parses the byte array for the target code
		DataParser dp = new DataParser(data, targetCode, targetCodeEnd);
		List<String> secondaryFilePath = dp.ParseForTarget();

		//iterate over list for new targets
		for (int i =0; i < secondaryFilePath.size(); i++ ) {
			
			targetFilePath = secondaryFilePath.get(i);
			//connect to new target
			data = cm.getData(cm.connectTargetWebsite(targetHost, targetFilePath, 
					targetProtocol));
			
			//TODO write HTML to new file
			
			//TODO remove code
			System.out.println(secondaryFilePath.get(i));
			System.out.println("write to file BOOOOOM");
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
			

	}
}
