/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.statistics;

/**
 *
 * @author JA Dianes
 */
public class Statistic {
    public Statistic(String variable) {
        this.variable = variable;
        numSamples = 0;
        average = 0.0;
    }
    String variable;
    public int numSamples;
    public double average;
    
}
