package org.castlefight.castlefight.model;

import java.util.Map;

public class GameCommand {
	private String kind;
	private String code;
	private Map<String, String> data;
	
	public GameCommand() {
		super();
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "GameCommand [kind=" + kind + ", code=" + code + ", data=" + data + "]";
	}
	
}
