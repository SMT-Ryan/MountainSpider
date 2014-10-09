
package com.riker.MountianSpider;

public class TestSpider {

	/**
	 * 
	 */
	public TestSpider() {
		
	}

/**
 * 
 * @param args
 */
	public static void main(String[] args) {
		byte[] data = null;
		String targetHost = null;
		String targetFilePath = null;
		String targetProtocol = null;
		String targetCode = null;
		String targetCodeEnd = null;
		
		ConfigFileLoader cfl = new ConfigFileLoader();
		cfl.configData();
		
		//setting configuration variables
		targetProtocol = cfl.getTargetProtocol();
		targetHost = cfl.getTargetHost();
		targetFilePath = cfl.getTargetFilePath();
		targetCode = cfl.getTargetCode();
		targetCodeEnd = cfl.getTargetCodeEnd();
		
		//TODO remove testing code
		System.out.println(targetProtocol);
		System.out.println(targetHost);
		System.out.println(targetFilePath);
		System.out.println(targetCode);
		System.out.println(targetCodeEnd);

		//TODO might also want a message class again
		System.out.println("started program \n");
		
		ConnectionManager cm = new ConnectionManager();
		data = cm.getData(cm.connectTargetWebsite(targetHost, targetFilePath, 
					targetProtocol));
		
		//parses the byte array for the target code
		DataParser dp = new DataParser(data, targetCode, targetCodeEnd);
		dp.ParseForTarget();

		//iter over list for new targets
		
		//write html out to a new file
		
	}




}
