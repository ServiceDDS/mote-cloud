package edu.uma.motecloud.apps.common;

public class VariableID {
	String variableName;
	int moteID;
	String location;
	public int stream;
	public int feed;
	
	public VariableID(String vn, int mid, String l, int s, int f) {
		this.variableName = vn;
		this.moteID = mid;
		this.location = l;
		this.stream = s;
		this.feed = f;
	}
	public VariableID(String vn, int mid, String l) {
		this.variableName = vn;
		this.moteID = mid;
		this.location = l;
	}
	
	public String toString() {
		return variableName+"."+moteID+"/"+location;
	}
}
