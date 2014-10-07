package com.riker.MountianSpider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/****************************************************************************
 * <b>Title</b>: ConnectionManager.java <p/>
 * <b>Project</b>: Mountain Spider <p/>
 * <b>Description: </b> Connects to a target website and requests data by 
 * inputStream and outputStream.  
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 6, 2014<p/>
 * @updates:
 ****************************************************************************/
public class ConnectionManager {

	/**
	 * Constructs an instance of ConnectionManager.
	 */
	public ConnectionManager() {

	}

	/** 
	 * This method will use a java URL object to connect with the target URL. 
	 * 
	 * @param targetDomainName The target web location domain name.  
	 * @param targetUri The target location may need more then a simple domain 
	 * 		name to fine its target.
	 * @param targetProtocol The target locations protocol.
	 * @return This method will return the input stream if the connection was 
	 * successful. This method will return null if the input stream is not 
	 * successful. 
	 */
	public InputStream connectTargetWebsite(String targetDomainName, String targetUri, 
			String targetProtocol){
		
		//opens the target URL
		try{
			URL targetUrl = new URL(targetProtocol + targetDomainName 
					+ targetUri);
			InputStream in = targetUrl.openStream();
			return in;
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	
		return null;
	}

	/**
	 * @param in 
	 * @return 
	 * 
	 */
	public byte[] getData(InputStream in) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int t;
		
		try{
			while (( t=in.read()) != -1) {
				baos.write(t);
			}
		}catch (IOException ex){
			ex.printStackTrace();
		}
		
		return baos.toByteArray();

	}


}
