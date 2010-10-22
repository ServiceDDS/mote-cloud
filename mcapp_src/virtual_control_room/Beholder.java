/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room;

import MoteSample.SampleTopic;
import ServiceDDS.QoSParameters;
import ServiceDDS.exception.ImpossibleToCreateDDSTopic;
import ServiceDDS.servicetopic.ServiceTopic;
import java.util.logging.Level;
import java.util.logging.Logger;
import ServiceDDS.servicetopic.ServiceTopicListener;
import samples.DoubleSample;
import samples.Sample;
import samples.SampleFactory5V;

/**
 *
 * @author JA Dianes
 */
public class Beholder implements ServiceTopicListener {

    VCR vcr;
    ParticipantMotesJFrame participantsView;

    ServiceTopic moteServiceTopic;
    SampleFactory5V sampleFactory5V = new SampleFactory5V();

    public Beholder(VCR vcr) {
        this.vcr = vcr;
        this.participantsView = new ParticipantMotesJFrame();

        try {
            QoSParameters params = new QoSParameters();
            params.setDeadline(30, 0);
            params.setHistory(QoSParameters.KEEP_LAST_HISTORY_QOS, 3);
            this.moteServiceTopic =
                    vcr.myPeer.newReaderServiceTopic(
                        new SampleTopic(),
                        "SampleTopic",
                        params
                    );
            this.moteServiceTopic.addListener(this);
        } catch (ImpossibleToCreateDDSTopic ex) {
            Logger.getLogger(Beholder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Beholder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Beholder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Beholder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void newSample(Sample sample) {
        this.participantsView.newSample(sample);
    }

    public void on_data_available(ServiceTopic serviceTopic) {
        Object[] data = serviceTopic.take();
        for (int i=0; i<data.length; i++) {
            SampleTopic newSample = (SampleTopic)data[i];
            Sample s = new DoubleSample(
                    newSample.moteID,
                    newSample.sample,
                    newSample.variableName,
                    new Double(newSample.data).doubleValue());
            this.newSample(s);
        }
    }

    public void on_requested_deadline_missed(ServiceTopic st) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void on_sample_lost(ServiceTopic st) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void showParticipansView() {
        this.participantsView.setVisible(true);
    }

}
