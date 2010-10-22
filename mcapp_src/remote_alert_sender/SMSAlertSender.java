/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package remote_alert_sender;

/**
 *
 * @author JA Dianes
 */
public class SMSAlertSender implements Runnable {

    String number;
    public SMSAlertSender(String number) {
        this.number = number;
    }

    public void run() {
        // Sends SMS
        System.out.println("SMSAlertSender.run(): sending SMS to "+number);
    }

}
