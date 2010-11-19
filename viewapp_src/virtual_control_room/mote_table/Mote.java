/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.mote_table;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 *
 * @author JA Dianes
 */
public class Mote {

    public String moteId;
    public Hashtable<String,Sensor> sensors = new Hashtable();

    public Mote(String id) {
        this.moteId = id;
    }

    public String toString() {
        return moteId;
    }
}
