package dk.medicinkortet.minlog.categories;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


/***
 * Read a semicolon separated file containing action mappings for MinLog registration usage
 * Example:
 * 	Hent lægemiddelordination;Opslag på medicinoplysninger
 * 	Hent lægemiddelordinationversioner;Opslag på medicinoplysninger
 * 	Hent medicinkort;Opslag på medicinoplysninger
 * 
 * @author chj
 *
 */
public class ActionMapReader {

	private static Logger logger = Logger.getLogger(ActionMapReader.class);
	
	public static class Action {
		
		private final String actionName;
		private final boolean isAdminAction;
		
		public Action(String actionName, boolean isAdminAction) {
			super();
			this.actionName = actionName;
			this.isAdminAction = isAdminAction;
		}
		public String getActionName() {
			return actionName;
		}

		public boolean getIsAdminAction() {
			return isAdminAction;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((actionName == null) ? 0 : actionName.hashCode());
			result = prime * result + (isAdminAction ? 1231 : 1237);
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Action))
				return false;
			Action other = (Action) obj;
			if (actionName == null) {
				if (other.actionName != null)
					return false;
			} else if (!actionName.equals(other.actionName))
				return false;
			if (isAdminAction != other.isAdminAction)
				return false;
			return true;
		}
		
		
	}
	
	public static Map<Action, String> getActionMap() throws IOException {
		return getActionMap("MinLogActionMap.csv");
	}
	
	public static Map<Action, String> getActionMap(String mappingFilename) throws IOException {
		
		Map<Action, String> actionMap = new HashMap<Action, String>(); 
		BufferedReader br = null;
		String line = "";
		
		try {
			br = new BufferedReader(new InputStreamReader(ActionMapReader.class.getClassLoader().getResourceAsStream(mappingFilename), Charset.forName("UTF-8")));

			while ((line = br.readLine()) != null) {
	 
				if(!line.startsWith("#")) {	// Comment lines starts with #
					String[] actionMapLine = line.split(";");
					Action action = new ActionMapReader.Action(actionMapLine[0], isAdminAction(actionMapLine[1]));
					if(actionMap.containsKey(action)) {
						logger.error("Duplicate key in actionmap-file: " + action.getActionName() + " (admin = " + action.getIsAdminAction() + ")");
					}
					else {
						actionMap.put(action, actionMapLine[1]);
					}
				}
			}
		} finally {
			if (br != null) {
				br.close();
			} 
		}
			
		return actionMap;
	}
	
	private static boolean isAdminAction(String actionName) {
		return "Administrative ændringer".equalsIgnoreCase(actionName) || "Administratoropslag".equalsIgnoreCase(actionName);
	}
}
