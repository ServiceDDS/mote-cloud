/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.view.double_dial;

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
public class DoubleDialViewSDDS extends DoubleDialView implements ServiceTopicListener {

    ServiceTopic sampleServiceTopic;

    public DoubleDialViewSDDS(int moteID1, int moteID2, String name1, String name2, Peer peer) {
        super(moteID1, moteID2, name1, name2);
        try {
//            QoSParameters params = new QoSParameters();
//            params.setDeadline(30, 0);
//            params.setHistory(QoSParameters.KEEP_LAST_HISTORY_QOS, 3);
            this.sampleServiceTopic = peer.newReaderServiceTopic(
                    new SampleTopic(),
                    "SampleTopic",
                    null);
            this.sampleServiceTopic.addListener(this);
        } catch (ImpossibleToCreateDDSTopic ex) {
            Logger.getLogger(DoubleDialViewSDDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoubleDialViewSDDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DoubleDialViewSDDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DoubleDialViewSDDS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void on_data_available(ServiceTopic st) {
      System.out.println("DoubleDialViewSDDS.on_data_available");
        Object[] data = st.take();
        for (int i=0; i<data.length; i++) {
            SampleTopic newSample = (SampleTopic)data[i];
            if (((this.moteID1 == newSample.moteID) || (this.moteID2 == newSample.moteID))
             && ((newSample.variableName.compareTo(this.variable1)==0) || (newSample.variableName.compareTo(this.variable2)==0))) {
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
