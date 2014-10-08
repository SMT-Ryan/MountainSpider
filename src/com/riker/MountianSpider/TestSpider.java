
package com.riker.MountianSpider;

public class TestSpider {

	
	public TestSpider() {
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		byte[] data = null;
		String targetDomainName = null;
		String targetUri = null;
		String targetProtocol = null;
		String targetCode = null;
				
		
		//TODO sets the test data to example.com replace with config file method
		targetProtocol = "http://";
		targetDomainName = "www.whatsmyuseragent.com";
		targetUri = "";
		targetCode = "\"lvl1\" href=";

		//TODO might also want a message class again
		System.out.println("started program \n");
		
		ConnectionManager cm = new ConnectionManager();
		data = cm.getData(cm.connectTargetWebsite(targetDomainName, targetUri, 
					targetProtocol));
		
		//parses the byte array for the target code
		DataParser(data, targetCode);
		//send byte array to parse for reduction
		
		String dataString = new String(data);
		System.out.print(dataString);
		
		cm.printHeader(cm.getMap());
		
	}




}
