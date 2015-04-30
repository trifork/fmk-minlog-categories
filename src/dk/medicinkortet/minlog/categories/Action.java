package dk.medicinkortet.minlog.categories;

public class Action {
	
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
