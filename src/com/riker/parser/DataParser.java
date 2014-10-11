package com.riker.parser;

import java.util.ArrayList;
import java.util.List;

import com.riker.logger.LoggerShare;

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
public class DataParser extends LoggerShare {

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
	 * @param targetEnd ends the sub string during parsing.
	 */
	public DataParser(byte[] data , String target, String targetEnd) {
		this.setData(data);
		this.setTarget(target);
		this.setTargetEnd(targetEnd);

	}

	/**
	 * This method checks the byte array from the parent class for the required 
	 * starting and ending string.  The substrings are added to a list.  
	 *  
	 * @param mg 
	 * @return the list of strings as searched for targets.
	 */
	public List<String> ParseForTarget() {

		String dataString = new String(data);

		log.info("Started parsing");

		int i = 0;
		int t = 0;
		log.debug("parsing loop is about to begin");
		while (true) {

			t = dataString.indexOf(this.target, i);
			int start = t + this.target.length();
			if (t == -1) {
				log.debug("parsing loop breaks");
				break;
			}
			int end = dataString.indexOf(this.targetEnd, start);
			secondaryTargetFilePath.add(dataString.substring(start, end));

			i = end + 1;  
		}
		log.debug("parsing loop is complete");
		return secondaryTargetFilePath;
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
	 * returns the current value in targetEnd variable
	 * @return the targetEnd
	 */
	public String getTargetEnd() {
		return targetEnd;
	}

	/**
	 * sets the targetEnd variable
	 * @param targetEnd the targetEnd to set
	 */
	public void setTargetEnd(String targetEnd) {
		this.targetEnd = targetEnd;
	}



}
