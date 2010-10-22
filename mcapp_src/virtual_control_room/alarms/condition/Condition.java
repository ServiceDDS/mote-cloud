/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.alarms.condition;

/**
 *
 * @author JA Dianes
 */
public abstract class Condition {

    public abstract boolean test(Object event);
    
}
