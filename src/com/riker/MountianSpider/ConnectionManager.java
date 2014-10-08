package com.riker.MountianSpider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

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

	private URL targetUrl = null;
	private Map<String, List<String>> map = null;
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
			
			//sets the header map
			setHeader(targetUrl, map);
			
			InputStream in = targetUrl.openStream();
			return in;
		}catch (IOException ex) {
			//TODO error message
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * This method returns the locations data in the form of a byte array.
	 * 
	 * @param in an active input stream from the target.
	 * @return a byte array containing bit encoded data from the web source.
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
			//TODO error message
			ex.printStackTrace();
		}
		return baos.toByteArray();
	}

	/**
	 * 
	 * @param targetUrl 
	 * @param map 
	 * @param in
	 * @return 
	 * @throws IOException 
	 */
	public Map<String, List<String>> setHeader(URL targetUrl, 
			Map<String, List<String>> map ) throws IOException{
		
		URLConnection uc = targetUrl.openConnection();
		
		this.map = uc.getHeaderFields();
		map = this.map;
		
		return map;
	}


	public void printHeader(Map<String, List<String>> map) {
		System.out.println("Printing Response Header...\n");
		 
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() 
                    + " ,Value : " + entry.getValue());
		}
	}

	/**
	 * @return the targetUrl
	 */
	public URL getTargetUrl() {
		return targetUrl;
	}

	/**
	 * @param targetUrl the targetUrl to set
	 */
	public void setTargetUrl(URL targetUrl) {
		this.targetUrl = targetUrl;
	}

	/**
	 * @return the map
	 */
	public Map<String, List<String>> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, List<String>> map) {
		this.map = map;
	}
			
}