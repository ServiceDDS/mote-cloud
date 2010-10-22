/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.alarms.action.messages;

import virtual_control_room.alarms.action.*;
import virtual_control_room.GUI;

/**
 *
 * @author JA Dianes
 */
public abstract class AlarmMessageAction extends Action {

    String message;
    GUI gui;

    public AlarmMessageAction(String message, GUI gui) {
        this.message = message;
        this.gui = gui;
    }

}
