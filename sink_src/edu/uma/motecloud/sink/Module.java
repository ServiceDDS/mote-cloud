package edu.uma.motecloud.sink;

import MoteSample.SampleTopic;
import ServiceDDS.Peer;
import ServiceDDS.exception.ImpossibleToCreateDDSTopic;
import ServiceDDS.servicetopic.WriterServiceTopic;
import edu.uma.motecloud.sink.exceptions.ImpossibleToRegisterModule;
import edu.uma.motecloud.sink.exceptions.ModuleNotInitializedException;

/**
 * Extend this class to define new Modules.
 * Do not define new constructors with arguments. Instead, put in the setup method all the initializations.
 * This method is called from within the module initializer.
 * @author Jose Angel Dianes
 * @date 10/21/2010
 * @version 0.1a
 *
 */
public abstract class Module implements Runnable {

	public static String location;
	public static String wsnID;	
	private static WriterServiceTopic sampleTopicST;
	private static Peer peer;
	private static SampleTopic topicData;
	private static boolean initialized = false;
	
	@Override
	/**
	 * This task should be redefined by the module designer. This is where the data from he WSN is taken
	 * and the new samples are written using the newSample method in the module.
	 */
	public abstract void run();

	public void initialize() {
		try {
			// Register the module
			peer = Sink.registerModule(wsnID,location);
			// Create the service topic
			topicData = new SampleTopic();
//        	QoSParameters serviceTopicQoS = new QoSParameters();
//        	serviceTopicQoS.setDeadline(0,0);
//        	serviceTopicQoS.setHistory(QoSParameters.KEEP_LAST_HISTORY_QOS, history);			
			sampleTopicST = peer.newWriterServiceTopic(topicData, "SampleTopic", null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImpossibleToRegisterModule e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImpossibleToCreateDDSTopic e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		initialized = true;
		System.out.println("Module: module "+wsnID+"@"+location+" initialized...");		
	}
	
	/**
	 * 
	 * @param moteID
	 * @param sample
	 * @param var
	 * @param data
	 */
	public void newSample(int moteID, int sample, String var, String data) throws ModuleNotInitializedException {
	    if (!initialized) throw new ModuleNotInitializedException();
		topicData.data = data;
		topicData.moteID = moteID;
		topicData.sample = sample;
		topicData.variableName = var;
		sampleTopicST.write(topicData);
		System.out.println("Module: module new sample at "+wsnID+"@"+location+": ");
		System.out.println(" data = "+topicData.data);
		System.out.println(" mode_id = "+topicData.moteID);
		System.out.println(" sample_num = "+topicData.sample);
		System.out.println(" variable = "+topicData.variableName);
	}
	
	
	/**
	 * This method can be redefined in order to process the calling arguments sent to the main
	 * function of the module.
	 * @param args
	 */
	public abstract void setup(String[] args);	
	
	
}
