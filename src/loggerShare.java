import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



/****************************************************************************
 * <b>Title</b>: loggerShare.java <p/>
 * <b>Project</b>: WebCrescendo <p/>
 * <b>Description: </b> Put Something Here
 * <p/>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Ryan Riker
 * @version 2.0
 * @since Oct 10, 2014<p/>
 * @updates:
 ****************************************************************************/
public abstract class loggerShare {

	public final Logger log = Logger.getLogger(this.getClass());
	final static String LOGGER_PATH = "scripts/log4j.properties";
	
	public loggerShare() {
		PropertyConfigurator.configure(LOGGER_PATH);
	}

}
