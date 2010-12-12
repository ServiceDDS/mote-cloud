package edu.uma.motecloud.apps.pachube;

import java.util.Hashtable;

import edu.uma.motecloud.apps.common.VariableID;

import MoteCloud.SampleTopic;
import Pachube.Feed;
import Pachube.Pachube;
import Pachube.PachubeException;
import ServiceDDS.servicetopic.ServiceTopic;
import ServiceDDS.servicetopic.ServiceTopicListener;

public class NewSampleListener implements ServiceTopicListener {

	int feed;
	String APPKey;
	Hashtable<String,VariableID> variables = new Hashtable<String,VariableID>(); 
	
	public NewSampleListener(int feed, String APPKey, VariableID[] vars) {
		System.out.println("NewSampleListener: constructor");
		if (vars!= null) for (int i=0; i<vars.length;i++) {
			this.variables.put(vars[i].toString(), vars[i]);
			System.out.println("NewSampleListener: added <"+vars[i].toString()+">");
		}
		this.feed = feed;
		this.APPKey = APPKey;
	}
	
	public void updateFeed(int i, double v) {
		try {
			Pachube p = new Pachube(this.APPKey);
			System.out.println("NewSampleListener.updateFeed(): got Pachube object...");
			Feed f = p.getFeed(this.feed);
			System.out.println("NewSampleListener.updateFeed(): got Feed object...");
			f.updateDatastream(i, v);
			System.out.println("NewSampleListener.updateFeed(): stream updated!");
		} catch (PachubeException e) {
			e.printStackTrace();
		}		
		
	}

	@Override
	public void on_data_available(ServiceTopic serviceTopic) {
	      System.out.println("NewSampleListener.on_data_available");
	        Object[] data = serviceTopic.take();
	        for (int i=0; i<data.length; i++) {
	            SampleTopic newSample = (SampleTopic)data[i];
	            VariableID id = new VariableID(newSample.variableName,newSample.moteID,newSample.location);
	           System.out.println("NewSampleListener.on_data_available: varID=<"+id.toString()+">");
	            if (this.variables.containsKey(id.toString())) {
	            	double v = new Double(newSample.data).doubleValue();
	            	System.out.println("NewSampleListener.on_data_available: showing sample: "+newSample.sample+" with value "+v+"...");	            	
	            	updateFeed(this.variables.get(id.toString()).stream,v);
	            	System.out.println("NewSampleListener.on_data_available: done!");
	            	
	            }
	        }				
	}

	@Override
	public void on_requested_deadline_missed(ServiceTopic arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void on_sample_lost(ServiceTopic arg0) {
		// TODO Auto-generated method stub
		
	}	
}
