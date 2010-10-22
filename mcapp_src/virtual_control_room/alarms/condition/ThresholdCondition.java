/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.alarms.condition;

/**
 *
 * @author JA Dianes
 */
public abstract class ThresholdCondition extends Condition {

    double threshold;
    String key;
    public ThresholdCondition(double t, String key) {
        this.threshold = t;
        this.key = key;
    }

}
