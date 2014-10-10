package com.riker.connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import com.riker.user_communication.Messages;

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
	public static final String USER_AGENT_INTERNET_EXPLORER = 
			"Mozilla/5.0 (Windows; U; Windows NT 5.1; "
					+ "en-US; rv:1.9.2.3) Gecko/20100401";
	public static final String USER_AGENT_PROPERTY_NAME = "http.agent";

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
	 * @param mg the message map used to communicate with the user
	 * @return This method will return the input stream if the connection was 
	 * successful. This method will return null if the input stream is not 
	 * successful. 
	 */
	public InputStream connectTargetWebsite(String targetDomainName, 
			String targetUri, String targetProtocol, Messages mg){

		//opens the target URL
		try{

			if (targetProtocol == null || targetDomainName == null || 
					targetUri== null){
				System.out.println(mg.displayMessages(mg.NULL_ENCOUNTERED));
			}else{			
				URL targetUrl = new URL(targetProtocol + targetDomainName 
						+ targetUri);

				//sets the header map
				headerMap = setHeader(targetUrl, headerMap);

				InputStream in = targetUrl.openStream();
				System.out.println(mg.displayMessages(mg.CONNECTED));
				return in;
			}

		}catch (IOException ex) {
			System.out.println(mg.displayMessages(mg.CONNECTION_ERROR));
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
	public byte[] getData(InputStream in, Messages mg) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int t;

		try{
			while (( t=in.read()) != -1) {
				baos.write(t);
			}
		}catch (IOException ex){
			System.out.println(mg.displayMessages(mg.CONNECTION_ERROR));
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
		try {
			System.setProperty(USER_AGENT_PROPERTY_NAME, 
					USER_AGENT_INTERNET_EXPLORER);
			HttpURLConnection uc = (HttpURLConnection) 
					targetUrl.openConnection();
			uc.setDoInput(true);
			uc.setDoOutput(true);
			uc.setInstanceFollowRedirects(false);
			headerMap = uc.getHeaderFields();

			uc.connect();
			return headerMap;
		}catch ( UnknownHostException ex){
			//TODO error message
			return headerMap;
		}
	}

	/**
	 * This method displays the http GET header data stored in the headerMap
	 * 
	 * @param headerMap current header map
	 */
	public void printHeader(Map<String, List<String>> headerMap) {

		for (Map.Entry<String, List<String>> entry : headerMap.entrySet()) {
			System.out.println("Key : " + entry.getKey() 
					+ " ,Value : " + entry.getValue());
		}
	}

	/**
	 * This method returns the target Url object
	 * 
	 * @return the targetUrl 
	 */
	public URL getTargetUrl() {
		return targetUrl;
	}

	/**
	 * This method sets the target Url object
	 * 
	 * @param targetUrl the targetUrl to set
	 */
	public void setTargetUrl(URL targetUrl) {
		this.targetUrl = targetUrl;
	}

	/**
	 * This method returns the existing map
	 * 
	 * @return the map
	 */
	public Map<String, List<String>> getMap() {
		return headerMap;
	}

	/**
	 * This method changes the contents of the existing map
	 * 
	 * @param map the map to set
	 */
	public void setMap(Map<String, List<String>> map) {
		this.headerMap = map;
	}

}