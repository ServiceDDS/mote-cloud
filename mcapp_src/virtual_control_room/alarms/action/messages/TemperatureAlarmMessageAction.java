/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.alarms.action.messages;

import samples.Sample;
import virtual_control_room.GUI;

/**
 *
 * @author JA Dianes
 */
public class TemperatureAlarmMessageAction extends AlarmMessageAction {

    public TemperatureAlarmMessageAction(GUI gui) {
        super("ALAMR: Temperature is ", gui);
    }

    public void run() {
        Sample sample = (Sample) event;
        gui.showAlarmMessage(message+((Double)sample.getValue()).doubleValue()
                + " in mote "+sample.moteID+"!");
    }


}
