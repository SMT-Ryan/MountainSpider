package Logger;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/****************************************************************************
 * <b>Title</b>: loggerShare.java <p/>
 * <b>Project</b>: Mountain Spider <p/>
 * <b>Description: </b> This abstract class holds the logger for child classes.
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 10, 2014<p/>
 * @updates:
 ****************************************************************************/
public abstract class LoggerShare {

	public final Logger log = Logger.getLogger(this.getClass());
	final static String LOGGER_PATH = "scripts/log4j.properties";
	
	/**
	 * This class is an abstract class that holds the logger for child classes.
	 */
	public LoggerShare() {
		PropertyConfigurator.configure(LOGGER_PATH);
	}

}
