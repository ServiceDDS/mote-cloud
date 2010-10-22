/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package remote_alert_sender;

/**
 *
 * @author JA Dianes
 */
public class RemoteAlertSender {
    Beholder beholder;
    public RemoteAlertSender() {
        beholder = new Beholder();
    }

    public void alertReceived(Object event) {
        Thread newThread = new Thread(new SMSAlertSender("670548383"));
        newThread.start();
    }
}
