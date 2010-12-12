package edu.uma.motecloud.apps.pachube;
import edu.uma.motecloud.apps.common.VariableID;
import MoteCloud.SampleTopic;
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
		int feedId = 13745;
		String appKey = "6f29c0164d48d3874f08e77285bc20c83b05d1d6095a89a1fed3327eef84ab4a";

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
			sampleTopicST.addListener(new NewSampleListener(
					new VariableID[] {
		               new VariableID("temperature:2",2,"",0,feedId,appKey),
		               new VariableID("humidity:2",2,"",1,feedId,appKey),
		               new VariableID("light:2",2,"",2,feedId,appKey)
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
	
	/** NOT USED, IT IS ONLY A CODE SAMPLE OF HOW TO CREATE A FEED FROM JAVA CODE **/
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