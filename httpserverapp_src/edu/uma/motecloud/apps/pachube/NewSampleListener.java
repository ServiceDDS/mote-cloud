package edu.uma.motecloud.apps.pachube;

import java.util.Hashtable;

import edu.uma.motecloud.apps.common.VariableID;

import MoteSample.SampleTopic;
import Pachube.Feed;
import Pachube.Pachube;
import Pachube.PachubeException;
import ServiceDDS.servicetopic.ServiceTopic;
import ServiceDDS.servicetopic.ServiceTopicListener;

public class NewSampleListener implements ServiceTopicListener {

	int feed;
	int stream;
	int moteID;
	Hashtable<String,VariableID> variables = new Hashtable<String,VariableID>(); 
	
	public NewSampleListener(int feed, int stream, VariableID[] vars) {
		System.out.println("NewSampleListener: constructor");
		if (vars!= null) for (int i=0; i<vars.length;i++) {
			this.variables.put(vars[i].toString(), vars[i]);
		}
		this.stream = stream;
		this.feed = feed;
	}
	
	public void updateFeed(int i, double v) {
		try {
			Pachube p = new Pachube("6f29c0164d48d3874f08e77285bc20c83b05d1d6095a89a1fed3327eef84ab4a");
			Feed f = p.getFeed(this.feed);
			f.updateDatastream(i, v);
		} catch (PachubeException e) {
			System.out.println(e.errorMessage);
		}		
		
	}

	@Override
	public void on_data_available(ServiceTopic serviceTopic) {
	      //System.out.println("NewSampleListener.on_data_available");
	        Object[] data = serviceTopic.take();
	        for (int i=0; i<data.length; i++) {
	            SampleTopic newSample = (SampleTopic)data[i];
	            VariableID id = new VariableID(newSample.variableName,newSample.moteID,newSample.location);
	            //System.out.println("NewSampleListener.on_data_available: varID="+id);
	            if (this.variables.containsKey(id.toString())) {
	            	double v = new Double(newSample.data).doubleValue();
	            	updateFeed(this.variables.get(id.toString()).stream,v);
	            	//System.out.println("NewSampleListener.on_data_available: showing sample: "+newSample.sample+" with value "+v);
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
