package dk.medicinkortet.minlog.categories;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;


/***
 * Read a semicolon separated file containing action mappings for MinLog registration usage
 * 
 * # <some comment>
 * <method name>;<minlog category name>
 * ...
 * <method name to ignore>
 * 
 * Example:
 *  # Some comment
 * 	Hent lægemiddelordination;Opslag på medicinoplysninger
 * 	Hent lægemiddelordinationversioner;Opslag på medicinoplysninger
 * 	Hent medicinkort;Opslag på medicinoplysninger
 *  # Actions to ignore
 *  Menu opsætning
 *   
 * @author chj
 *
 */
public class ActionMapReader {

	private static Logger logger = Logger.getLogger(ActionMapReader.class);
	
	public static ActionMap getActionMap() throws IOException {
		return getActionMap("MinLogActionMap.csv");
	}
	
	public static ActionMap getActionMap(String mappingFilename) throws IOException {
		
		Map<String, String> includedActionsMap = new HashMap<>(); 
		Set<String> ignoredActions = new HashSet<String>();
		BufferedReader br = null;
		String line = "";
		
		try {
			InputStream is = ActionMapReader.class.getClassLoader().getResourceAsStream(mappingFilename);
			if(is == null) {
				throw new IOException("Unable to read actionmap file " + mappingFilename);
			}
			br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

			while ((line = br.readLine()) != null) {
	 
				if(!line.startsWith("#")) {	// Comment lines starts with #
					String[] actionMapLine = line.split(";");
					if(actionMapLine.length == 2) {
						String action = actionMapLine[0];
						if(includedActionsMap.containsKey(action)) {
							logger.error("Duplicate key in actionmap-file: " + action);
						}
						else {
							includedActionsMap.put(action, actionMapLine[1]);
						}
					}
					else if(actionMapLine.length == 1) {
						// Not included in minlogs, to be ignored, but keep them in the actionMap to avoid error logs
						ignoredActions.add(actionMapLine[0]);
					}
					else {
						logger.error("Error in actionmap-file: " + actionMapLine);
					}
				}
			}
		} finally {
			if (br != null) {
				br.close();
			} 
		}
			
		return new ActionMap(includedActionsMap, ignoredActions);
	}
	
	private static boolean isAdminAction(String actionName) {
		return "Administrative ændringer".equalsIgnoreCase(actionName) || "Administratoropslag".equalsIgnoreCase(actionName);
	}
}
