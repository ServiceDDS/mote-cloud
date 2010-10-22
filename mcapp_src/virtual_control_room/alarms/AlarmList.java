/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.alarms;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author JA Dianes
 */
public class AlarmList {

    LinkedList alarms = new LinkedList();

    public void addAlarm(Alarm a) {
        alarms.add(a);
    }

    public void test(Object event) {
        Iterator it = alarms.iterator();
        while (it.hasNext()) {
            ((Alarm)it.next()).test(event);
        }
    }
    
}
