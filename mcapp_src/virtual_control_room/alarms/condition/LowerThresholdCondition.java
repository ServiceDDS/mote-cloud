/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.alarms.condition;

import samples.Sample;


/**
 *
 * @author JA Dianes
 */
public class LowerThresholdCondition extends ThresholdCondition {


    public LowerThresholdCondition(double t, String key) {
        super(t,key);
    }

    @Override
    public boolean test(Object event) {
        boolean test = false;
        Sample sample = (Sample)event;
        if (sample.key.compareToIgnoreCase(key) > 0) {
            test = ((Double)sample.getValue()).doubleValue() < this.threshold;
                  //System.out.println("LowerThresholdCondition.test(): comparing with "+sample.getValue());

        }
        return test;
    }

}
