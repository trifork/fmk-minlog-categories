package dk.medicinkortet.minlog.categories;

import java.util.Map;
import java.util.Set;

public class ActionMap {
	
	final Map<Action, String> actionMap;
	final Set<String> ignoredActions;
	
	public Map<Action, String> getActionMap() {
		return actionMap;
	}
	
	public Set<String> getIgnoredActions() {
		return ignoredActions;
	}
	
	public ActionMap(Map<Action, String> actionMap,
			Set<String> ignoredActions) {
		super();
		this.actionMap = actionMap;
		this.ignoredActions = ignoredActions;
	}
	
}
