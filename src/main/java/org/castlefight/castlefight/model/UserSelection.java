package org.castlefight.castlefight.model;

public class UserSelection {
	private String action;
	private String details;
	public UserSelection() {
		super();
	}
	public UserSelection(String action, String details) {
		super();
		this.action = action;
		this.details = details;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "UserSelection [action=" + action + ", details=" + details + "]";
	}
	
}
