/**
 * 
 */
package com.riker.MountianSpider;

import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * <b>Title</b>: DataParser.java <p/>
 * <b>Project</b>: WebCrescendo <p/>
 * <b>Description: </b> This method will search the incoming byte array for 
 * parent class designated string or char patterns
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 8, 2014<p/>
 * @updates:
 ****************************************************************************/
public class DataParser {

	private byte[] data = null;
	private String target = null;
	private String targetEnd = null;
	List<String> secondaryTargetFilePath = new ArrayList<String>();


	/**
	 * creates an instance of the data parser via empty constructor
	 */
	public DataParser() {

	}

	/**
	 * Creates an instance of the data parser with data being automatically set
	 * 
	 * @param data is set to the byte array at construction.
	 * @param target is set to the target string at construction.
	 * @param targetEnd
	 */
	public DataParser(byte[] data , String target, String targetEnd) {
		this.setData(data);
		this.setTarget(target);
		this.setTargetEnd(targetEnd);

	}

	/**
	 * This method checks the byte array from the parent class for the required 
	 * starting and ending string.  The substrings are added to a list.   
	 */
	public void ParseForTarget(){

		String dataString = new String(data);
		int i = 0;
		int t = 0;

		while (true) {

			t = dataString.indexOf(this.target, i);
			int start = t + this.target.length();
			if (t == -1) {
				break;
			}
			int end = dataString.indexOf(this.targetEnd, start);
			secondaryTargetFilePath.add(dataString.substring(start, end));
			i = end + 1;  
		}
	}

	/**
	 * This method sets both the data and the target to null
	 */
	public void clear(){
		this.setData(null);
		this.setTarget(null);

	}

	/**
	 * gets existing byte array of data
	 * 
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}


	/**
	 * sets existing byte array of data
	 * 
	 * @param data the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}


	/**
	 * gets current string target
	 * 
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}


	/**
	 * sets current string target
	 * 
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return the targetEnd
	 */
	public String getTargetEnd() {
		return targetEnd;
	}

	/**
	 * @param targetEnd the targetEnd to set
	 */
	public void setTargetEnd(String targetEnd) {
		this.targetEnd = targetEnd;
	}



}
