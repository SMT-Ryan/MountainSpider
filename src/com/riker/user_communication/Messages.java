package com.riker.user_communication;

import java.util.HashMap;
import java.util.Map;

/****************************************************************************
 * <b>Title</b>: Messages.java <p/>
 * <b>Project</b>: Mountain Spider <p/>
 * <b>Description: </b> This class houses some messages displayed to the 
 * user.  everything in this class would be replaced with log4j logging.  
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
	public final int CONNECTED = 4;
	public final int COMPLETE = 5;
	public final int PRIMARY_TARGET = 6;
	public final int SECONDARY_TARGET = 7;
	public final int FILE_NOT_FOUND_ERROR = 1100;
	public final int NULL_ENCOUNTERED = 1101;
	public final int CONNECTION_ERROR = 1102;
	/**
	 * This constructor generates an instances of Messages class.
	 */
	public Messages() {
	}

	/**
	 * This class manages and displays the map of messages to be displayed for
	 *  the user.
	 * @param key value to be check against the map
	 * @return a string associated with the searched for key, returns an error 
	 * message if key is not found
	 */
	public String displayMessages(int key){
		Map<Integer, String> message = new HashMap<Integer, String>();
		message.put(FILE_NOT_FOUND_ERROR, "Configureation file not found \n No "
				+ "Connection possible");
		message.put(WELCOME, "Thanks for using Mountian Spider 1.0 \n"
				+ "Copyright 2014 Silicon Mountian \n"
				+ "Code by Ryan Riker \n"
				+ "If everything appears in order we will be web crawling "
				+ "shortly.");
		message.put(NULL_ENCOUNTERED, "Unexpected null value");
		message.put(SAVING, "Saving a file to the local machine.");
		message.put(PARSING, "Parsing for secondary targets");
		message.put(CONNECTED, "Mountian spider has a connection");
		message.put(COMPLETE, "Mountian spider has completed its task \n"
				+ "The files are saved in the location specified in the "
				+ "configuration file ");
		message.put(PRIMARY_TARGET, "Attempting to connect to primary web "
				+ "target");
		message.put(SECONDARY_TARGET, "attempting to connect to secondary web "
				+ "target");
		message.put(CONNECTION_ERROR, "attempting to connect has failed");

		if (message.get(key) != null){
			return message.get(key);
		}else{
			return new String ("something has gone horribly wrong inside "
					+ "message class at if statment");
		}

	}


}
