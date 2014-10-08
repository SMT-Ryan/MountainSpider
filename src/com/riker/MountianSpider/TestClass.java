
package com.riker.MountianSpider;

public class TestClass {

	
	public TestClass() {
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		byte[] data = null;
		String targetDomainName = null;
		String targetUri = null;
		String targetProtocol = null;
		
		//sets the test data to example.com
		targetProtocol = "http://";
		//targetDomainName = "www.stantec.com";
		targetDomainName = "www.example.com";
		//targetUri = "/our-work.html";
		targetUri = "";

		System.out.println("started program \n");
		ConnectionManager cm = new ConnectionManager();
		data = cm.getData(cm.connectTargetWebsite(targetDomainName, targetUri, 
					targetProtocol));
		
		String dataString = new String(data);
		System.out.print(dataString);
		
		cm.printHeader(cm.getMap());
		
	}

}
