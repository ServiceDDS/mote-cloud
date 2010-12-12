package edu.uma.motecloud.apps.common;

import Pachube.Feed;
import Pachube.Pachube;
import Pachube.PachubeException;

public class VariableStream {
	String variableName;
	int moteID;
	String location;
	public int stream;
	public Pachube pachube;
	String appKey;
	int feedId;
	public Feed feed;
	
	public VariableStream(String vn, int mid, String l, int s, int f, String ak) throws PachubeException {
		this.variableName = vn;
		this.moteID = mid;
		this.location = l;
		this.stream = s;
		this.pachube = new Pachube(ak);
		this.appKey = ak;
		this.feed = this.pachube.getFeed(f);
		this.feedId = f;
	}
	public VariableStream(String vn, int mid, String l) {
		this.variableName = vn;
		this.moteID = mid;
		this.location = l;
	}

	public void updateFeed(double v) {
		System.out.println("VariableStream.updateFeed(): " +
				"AK="+this.appKey+
				" F="+this.feedId+
				" ST="+this.stream+
				" V="+v);
		feed.updateDatastream(this.stream, v);
		System.out.println("VariableStream.updateFeed(): stream updated!");		
	}

	public String toString() {
		return variableName+"."+moteID+"/"+location;
	}
}
