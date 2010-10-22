/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.alarms;

import MoteSample.SampleTopic;
import ServiceDDS.Peer;
import ServiceDDS.exception.ImpossibleToCreateDDSTopic;
import ServiceDDS.servicetopic.ServiceTopic;
import ServiceDDS.servicetopic.ServiceTopicListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import remote_alert_sender.Beholder;
import remote_alert_sender.RemoteAlertSender;
import samples.DoubleSample;
import samples.Sample;
import virtual_control_room.GUI;
import virtual_control_room.alarms.action.Action;
import virtual_control_room.alarms.action.messages.TemperatureAlarmMessageAction;
import virtual_control_room.alarms.action.remote_alerts.RemoteAlertAction;
import virtual_control_room.alarms.condition.Condition;
import virtual_control_room.alarms.condition.LowerThresholdCondition;

/**
 *
 * @author JA Dianes
 */
public class TemperatureThresholdAlarm extends Alarm implements ServiceTopicListener {

    ServiceTopic sampleServiceTopic;

    public TemperatureThresholdAlarm(Peer peer, double t, GUI gui, RemoteAlertSender ras) {
        super(new Condition[] {new LowerThresholdCondition(t,"temperature")},
              new Action[] {
                  new TemperatureAlarmMessageAction(gui),
                  new RemoteAlertAction(ras)
              }
        );

        try {
            this.sampleServiceTopic = peer.newReaderServiceTopic(new SampleTopic(), "SampleTopic",null);
            this.sampleServiceTopic.addListener(this);
        } catch (ImpossibleToCreateDDSTopic ex) {
            Logger.getLogger(TemperatureThresholdAlarm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Beholder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Beholder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Beholder.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void on_data_available(ServiceTopic serviceTopic) {
        Object[] data = serviceTopic.take();
        for (int i=0; i<data.length; i++) {
            SampleTopic newSample = (SampleTopic)data[i];
//          System.out.println("TemperatureThresholdAlarm.on_data_available: new sample received from"
//                  +" mote: "+newSample.moteID
//                  +" variable "+newSample.variableName
//                  +" value "+newSample.data);
            Sample s = new DoubleSample(
                    newSample.moteID,
                    newSample.sample,
                    newSample.variableName,
                    new Double(newSample.data).doubleValue());
            this.test(s);
        }
    }

    public void on_requested_deadline_missed(ServiceTopic st) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void on_sample_lost(ServiceTopic st) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
