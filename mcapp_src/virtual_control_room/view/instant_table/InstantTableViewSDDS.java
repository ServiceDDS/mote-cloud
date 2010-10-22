/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InstantTableView.java
 *
 * Created on 18-oct-2009, 16:19:10
 */

package virtual_control_room.view.instant_table;

import MoteSample.SampleTopic;
import ServiceDDS.Peer;
import ServiceDDS.QoSParameters;
import ServiceDDS.exception.ImpossibleToCreateDDSTopic;
import ServiceDDS.servicetopic.ServiceTopic;
import ServiceDDS.servicetopic.ServiceTopicListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import remote_alert_sender.Beholder;
import samples.DoubleSample;
import samples.Sample;

/**
 *
 * @author JA Dianes
 */
public class InstantTableViewSDDS extends InstantTableView implements
        ServiceTopicListener {

    ServiceTopic sampleServiceTopic;
    
    /** Creates new form InstantTableView */
    public InstantTableViewSDDS(Peer peer, int moteID) {
        super(moteID);
        try {
//            QoSParameters params = new QoSParameters();
//            params.setDeadline(0, 0);
//            params.setHistory(QoSParameters.KEEP_LAST_HISTORY_QOS, 3);
            this.sampleServiceTopic = peer.newReaderServiceTopic(
                    new SampleTopic(),
                    "SampleTopic",
                    null);
            this.sampleServiceTopic.addListener(this);
        } catch (ImpossibleToCreateDDSTopic ex) {
            Logger.getLogger(InstantTableViewSDDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Beholder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Beholder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Beholder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void on_data_available(ServiceTopic serviceTopic) {
      System.out.println("InstantTableView.on_data_available()");
        Object[] data = serviceTopic.take();
        for (int i=0; i<data.length; i++) {
            SampleTopic newSample = (SampleTopic)data[i];
            if (this.moteID == newSample.moteID) {
                Sample s = new DoubleSample(
                    newSample.moteID,
                    newSample.sample,
                    newSample.variableName,
                    new Double(newSample.data).doubleValue());
                this.showSample(s);
            }
        }
    }

    public void on_requested_deadline_missed(ServiceTopic st) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void on_sample_lost(ServiceTopic st) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
