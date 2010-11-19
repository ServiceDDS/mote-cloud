/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.mote_table;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author JA Dianes
 */
public class MoteTable {

    Hashtable<String,Mote> table = new Hashtable();
    LinkedList<MoteTableObserver> observers = new LinkedList();

    public void newMote(Mote mote) {
        System.out.println("MoteTable.newMote(): added mote "+mote.moteId);
        table.put(mote.moteId, mote);
        notifyObservers(mote);
    }

    public void newMoteSensor(String moteId, Sensor sensor) {
        System.out.println("MoteTable.newMoteSensor(): looking for mote "+moteId);
        Mote m = table.get(moteId);
        if (m==null) {
            System.out.println("MoteTable.newMoteSensor(): mote null!!!");
        }
        if (!m.sensors.containsKey(sensor.variableName)) {
           System.out.println("MoteTable.newMoteSensor(): added "+sensor.variableName+" "+moteId);
            m.sensors.put(sensor.variableName, sensor);
        } else {
            System.out.println("MoteTable.newMoteSensor(): mote "+moteId+" already contains "+sensor.variableName);
        };
        notifyObservers(m);
    }

    public void addObserver(MoteTableObserver o) {
        observers.add(o);
    }

    public boolean containsMote(String moteId) {
        return table.containsKey(moteId);
    }

    public boolean containsMoteSensor(String moteID, String variable) {
        return (table.containsKey(moteID) && table.get(moteID).sensors.containsKey(variable));
    }

    private void notifyObservers(Mote mote) {
        System.out.println("MoteTable.notifyObservers()");
        Iterator<MoteTableObserver> it = this.observers.iterator();
        while (it.hasNext()) {
            MoteTableObserver newObserver = it.next();
            newObserver.tableChanged(mote);
        }
    }

    public Collection<Mote> values() {
        return this.table.values();
    }
    
}
