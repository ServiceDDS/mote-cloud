/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.alarms.action.remote_alerts;

import remote_alert_sender.RemoteAlertSender;
import samples.Sample;
import virtual_control_room.alarms.action.Action;

/**
 *
 * @author JA Dianes
 */
public class RemoteAlertAction extends Action {

    RemoteAlertSender ras;

    public RemoteAlertAction(RemoteAlertSender ras) {
        this.ras = ras;
    }

    public void run() {
        Sample sample = (Sample) event;
        ras.alertReceived(sample);
    }


}
