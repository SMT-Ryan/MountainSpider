/**
 * 
 */
package com.riker.MountianSpider;

import java.util.HashMap;
import java.util.Map;

/****************************************************************************
 * <b>Title</b>: Messages.java <p/>
 * <b>Project</b>: Mountain Spider <p/>
 * <b>Description: </b> This class houses all the messages displayed to the 
 * user.  
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 9, 2014<p/>
 * @updates:
 ****************************************************************************/
public class Messages {
	public final int WELCOME = 1;
	public final int SAVING = 2;
	public final int PARSING = 3;
	public final int FILE_NOT_FOUND_ERROR = 1100;
	public final int NULL_ENCOUNTERED = 1101;
	/**
	 * This constructor generates an instances of Messages class.
	 */
	public Messages() {
	}

	/**
	 * put something here
	 * @param key 
	 * @return 
	 */
	public String displayMessages(int key){
		Map<Integer, String> message = new HashMap<Integer, String>();
		message.put(FILE_NOT_FOUND_ERROR, "Configureation file not found \n No "
				+ "Connection possible");
		message.put(WELCOME, "this is the welcome message yo");
		message.put(NULL_ENCOUNTERED, "Unexpected null value");
		message.put(SAVING, "Saving a file to the local machine.");
		message.put(PARSING, "Parsing for secondary targets");
		
		if (message.get(key) != null){
			return message.get(key);
		}else{
			return new String ("something has gone wrong inside message classs at if statment");
		}
		
	}
	
	
}
