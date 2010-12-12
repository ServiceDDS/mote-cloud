package edu.uma.motecloud.apps.pachube;

import java.util.Hashtable;

import MoteCloud.SampleTopic;
import ServiceDDS.servicetopic.ServiceTopic;
import ServiceDDS.servicetopic.ServiceTopicListener;
import edu.uma.motecloud.apps.common.VariableStream;

public class NewSampleListener implements ServiceTopicListener {

	Hashtable<String,VariableStream> variables = new Hashtable<String,VariableStream>(); 
	
	public NewSampleListener(VariableStream[] vars) {
		System.out.println("NewSampleListener: constructor");
		if (vars!= null) for (int i=0; i<vars.length;i++) {
			this.variables.put(vars[i].toString(), vars[i]);
			System.out.println("NewSampleListener: added <"+vars[i].toString()+">");
		}
	}
	
	@Override
	public void on_data_available(ServiceTopic serviceTopic) {
	      System.out.println("NewSampleListener.on_data_available");
	        Object[] data = serviceTopic.take();
	        for (int i=0; i<data.length; i++) {
	            SampleTopic newSample = (SampleTopic)data[i];
	            VariableStream id = new VariableStream(newSample.variableName,newSample.moteID,newSample.location);
	           System.out.println("NewSampleListener.on_data_available: varID=<"+id.toString()+">");
	            if (this.variables.containsKey(id.toString())) {
	            	id = variables.get(id.toString());
	            	double v = new Double(newSample.data).doubleValue();
	            	System.out.println("NewSampleListener.on_data_available: showing sample: "+newSample.sample+" with value "+v+"...");	            	
	            	id.updateFeed(v);
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
