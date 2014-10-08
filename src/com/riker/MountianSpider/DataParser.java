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
	private String targetEnd = null;


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
	public DataParser(byte[] data , String target, String targetEnd ) {
		this.setData(data);
		this.setTarget(target);
		this.setTargetEnd(targetEnd);

	}

	/**
	 * 
	 */
	public void ParseForTarget(){

		String dataString = new String(data);
		int i = 0;
		int t = 0;

		//TODO remove testing code
		System.out.println("***VV****mem dump*********");
		System.out.println(dataString);
		System.out.println("****^^***mem dump*********");
		
		while (true) {
			
			t = dataString.indexOf(this.target, i);
			int start = t + this.target.length();
			
			if (t == -1) {
				System.out.println("break");
				break;
			}
			int end = dataString.indexOf("\"", start);
			//TODO remove testing code
			System.out.println(dataString.substring(start, end) );
			
			i = end + 1;  // advance i to start the next iteration
			System.out.println(i);			
		}
	}

	/**
	 * 
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
