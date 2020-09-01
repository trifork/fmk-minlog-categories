package dk.medicinkortet.minlog.categories;

import java.util.Map;
import java.util.Set;

public class ActionMap {
	
	final Map<String, String> actionMap;
	final Set<String> ignoredActions;
	
	public Map<String, String> getActionMap() {
		return actionMap;
	}
	
	public Set<String> getIgnoredActions() {
		return ignoredActions;
	}
	
	public ActionMap(Map<String, String> actionMap,
			Set<String> ignoredActions) {
		super();
		this.actionMap = actionMap;
		this.ignoredActions = ignoredActions;
	}
	
}
