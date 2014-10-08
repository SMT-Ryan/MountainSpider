
package com.riker.MountianSpider;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestSpider {

	
	public TestSpider() {
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		byte[] data = null;
		String targetHost = null;
		String targetFilePath = null;
		String targetProtocol = null;
		String targetCode = null;
		String targetCodeEnd = null;
		
		//TODO sets the test data to example.com replace with config file method
		targetProtocol = "http://";
		targetHost = "www.siliconmtn.com/";
		targetFilePath = "";
		targetCode = "l1\" href=\"";
		targetCodeEnd = "\"";
		
		ConfigFileLoader cfl = new ConfigFileLoader();
		cfl.configData();

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
