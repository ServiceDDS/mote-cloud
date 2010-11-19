package edu.uma.motecloud.apps.pachube;
import edu.uma.motecloud.apps.common.VariableID;
import MoteSample.SampleTopic;
import Pachube.Data; 
import Pachube.Feed; 
import Pachube.Pachube; 
import Pachube.PachubeException;   
import ServiceDDS.Group;
import ServiceDDS.Peer;
import ServiceDDS.exception.ImpossibleToCreateDDSDomainParticipant;
import ServiceDDS.exception.ImpossibleToCreateDDSTopic;
import ServiceDDS.servicetopic.ReaderServiceTopic;

public class PachubeApp {  
	public static void main(String arsg[]) throws InterruptedException{
		// Register the module
		Peer peer;
		SampleTopic topicData;
		//NewSampleListener nsl = new NewSampleListener(12777,1,"temperature:1");
		try {
			peer = new Peer("PachubeApp");
			peer.joinGroup(new Group("MoteCloud"));
			System.out.println("PachubeApp: Peer done");
			// Create the service topic
			topicData = new SampleTopic();
//	    	QoSParameters serviceTopicQoS = new QoSParameters();
//	    	serviceTopicQoS.setDeadline(0,0);
//	    	serviceTopicQoS.setHistory(QoSParameters.KEEP_LAST_HISTORY_QOS, history);			
			ReaderServiceTopic sampleTopicST = peer.newReaderServiceTopic(topicData, "SampleTopic", null);
			System.out.println("PachubeApp: ServiceTopic done");
			sampleTopicST.addListener(new NewSampleListener(12777,1,
					new VariableID[] {
		               new VariableID("temperature:1",1,"",0,12777),
		               new VariableID("humidity:1",1,"",1,12777),
		               new VariableID("light:1",1,"",2,12777)
		               }));
			System.out.println("PachubeApp: Listeners added");
		} catch (ImpossibleToCreateDDSDomainParticipant e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImpossibleToCreateDDSTopic e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) { 
			Thread.sleep(1000);
		}
		
	}
	
	public static int createFeed() {
		try {                     
			//Creates a Pachube object authenicated using the provided API KEY
			Pachube p = new Pachube("6f29c0164d48d3874f08e77285bc20c83b05d1d6095a89a1fed3327eef84ab4a");
			Feed f = new Feed();
			f.setTitle("MoteClouod.PachubeApp337");
			Data a = new Data();
			a.setId(0);
			a.setMaxValue(50d);
			a.setMinValue(0d);
			a.setTag("temperature");
			a.setValue(0d);
			f.addData(a);
			Feed g = p.createFeed(f);
			//The Feed 'f' is does not represent the feed on pachube any
			// Changes made to this object will not alter the online feed.
			System.out.println("The id of the new feed is:");
			System.out.println(g.getId());
			return g.getId();
		} catch (PachubeException e) {
			//If an exception occurs it will print the error message from the failed
			// HTTP command
			System.out.println(e.errorMessage);
		}
		return -1;		
	}

}