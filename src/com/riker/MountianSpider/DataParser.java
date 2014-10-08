/**
 * 
 */
package com.riker.MountianSpider;

/****************************************************************************
 * <b>Title</b>: DataParser.java <p/>
 * <b>Project</b>: WebCrescendo <p/>
 * <b>Description: </b> This method will search the incoming byte array for 
 * spider designed string or char patterns
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
	
	
	/**
	 * 
	 */
	public DataParser() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}


	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}


	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	
	
}
