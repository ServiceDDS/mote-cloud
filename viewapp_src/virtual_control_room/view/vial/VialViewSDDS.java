/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.view.vial;

import MoteSample.SampleTopic;
import ServiceDDS.Peer;
import ServiceDDS.QoSParameters;
import ServiceDDS.exception.ImpossibleToCreateDDSTopic;
import ServiceDDS.servicetopic.ServiceTopic;
import ServiceDDS.servicetopic.ServiceTopicListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import samples.DoubleSample;
import samples.Sample;

/**
 *
 * @author JA Dianes
 */
public class VialViewSDDS extends VialView implements
        ServiceTopicListener {

    ServiceTopic sampleServiceTopic;

    public VialViewSDDS(Peer peer, int moteID, String name, float displayHeight, float displayWidth, double value) throws ImpossibleToCreateDDSTopic {
        super(moteID, name, displayHeight, displayWidth, value);

        try {
//            QoSParameters params = new QoSParameters();
//            params.setDeadline(30, 0);
//            params.setHistory(QoSParameters.KEEP_LAST_HISTORY_QOS, 3);
            this.sampleServiceTopic = peer.newReaderServiceTopic(
                    new SampleTopic(),
                    "SampleTopic",
                    null);
            this.sampleServiceTopic.addListener(this);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VialViewSDDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(VialViewSDDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(VialViewSDDS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void on_data_available(ServiceTopic serviceTopic) {
      System.out.println("ThermometerView.on_data_available");
        Object[] data = serviceTopic.take();
        for (int i=0; i<data.length; i++) {
            SampleTopic newSample = (SampleTopic)data[i];
            if ((this.moteID == newSample.moteID)
             && (newSample.variableName.compareTo(this.variable)==0)) {
                Sample s = new DoubleSample(
                    newSample.moteID,
                    newSample.sample,
                    newSample.variableName,
                    new Double(newSample.data).doubleValue());
                System.out.println("ThermometerView.on_data_available: showing sample...");
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
