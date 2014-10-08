package com.riker.MountianSpider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
	private Map<String, List<String>> headerMap = null;
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
			headerMap = setHeader(targetUrl, headerMap);
			
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
	 * This method calls an instance of URLConnection to the target web location
	 * the method than loads a map with the header into a map.  
	 * 
	 * @param targetUrl the url object for the target web location
	 * 
	 * @param map current header map
	 * @return a full map of header data
	 * @throws IOException 
	 */
	public Map<String, List<String>> setHeader(URL targetUrl, 
			Map<String, List<String>> headerMap ) throws IOException{
		
		URLConnection uc = (HttpURLConnection)targetUrl.openConnection();
		uc.addRequestProperty("User-Agent","Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:32.0) Gecko/20100101 Firefox/32.0");
		uc.connect();
		headerMap = uc.getHeaderFields();
		
		return headerMap;
	}

/**
 * This method displays the header data stored in the headerMap
 * 
 * @param headerMap current header map
 */
	public void printHeader(Map<String, List<String>> headerMap) {
		System.out.println("Printing Header*********************\n");
		 
		for (Map.Entry<String, List<String>> entry : headerMap.entrySet()) {
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
		return headerMap;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, List<String>> map) {
		this.headerMap = map;
	}
			
}