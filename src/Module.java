
import exceptions.ModuleNotInitializedException;
import ServiceDDS.Peer;
import ServiceDDS.servicetopic.WriterServiceTopic;
import MoteSample.SampleTopic;


public abstract class Module {
	
	public String location;
	public String wsnID;
	
	private WriterServiceTopic sampleTopicST;
	private Peer peer;
	private SampleTopic topicData;
	private boolean initialized = false;
	/**
	 * @param args
	 */
	public void main(String[] args) {
		// Take args to commit the module using the Sink
		location = args[0];
		wsnID = args[1];
		initialize();
		/* Start the Runnable task */		
		moduleTask();
	}

	public void initialize() {
		Sink.registerModule(this);
		// Create the service-topic
		try {
			this.topicData = new SampleTopic();
			peer.newWriterServiceTopic(this.topicData, null, null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.initialized = true;
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
		this.topicData.data = data;
		this.topicData.moteID = moteID;
		this.topicData.sample = sample;
		this.topicData.variableName = var;
		this.sampleTopicST.write(this.topicData);
	}
	
	/**
	 * This task should be redefined by the module designer. This is where the data from he WSN is taken
	 * and the new samples are written using the newSample method in the module.
	 */
	public abstract void moduleTask();
	
}
