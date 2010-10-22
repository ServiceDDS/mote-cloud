/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.alarms;

import virtual_control_room.alarms.action.Action;
import virtual_control_room.alarms.condition.Condition;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author JA Dianes
 */
public class Alarm {

    LinkedList conditions;
    LinkedList actions;

    public Alarm(Condition[] conditions, Action[] actions) {
        this.conditions = new LinkedList();
        for (int i=0; i<conditions.length; i++) {
            this.conditions.add(conditions[i]);
        }
        this.actions = new LinkedList();
        for (int i=0; i<actions.length; i++) {
            this.actions.add(actions[i]);
        }
    }

    public boolean test(Object event) {
        boolean test = true;
        // Test conditions
        Iterator it = conditions.iterator();
        while (test && (it.hasNext())) {
            test = test && ((Condition)it.next()).test(event);
        }

        if (test) {
            // Run actions
            it = actions.iterator();
            while (it.hasNext()) {
                Action newAction = (Action)it.next();
                newAction.event = event;
                Thread newActionThread = new Thread(newAction);
                newActionThread.start();
            }
        }
        return test;
    }
}
