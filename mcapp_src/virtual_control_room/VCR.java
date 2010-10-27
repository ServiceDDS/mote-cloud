/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room;

import ServiceDDS.Group;
import ServiceDDS.Peer;
import ServiceDDS.exception.ImpossibleToCreateDDSDomainParticipant;
import java.util.logging.Level;
import java.util.logging.Logger;
import virtual_control_room.exceptions.VCRExceptionUnnableToConnectToHabitatLAN;

/**
 *
 * @author JA Dianes
 */
public class VCR {

    GUI gui;
    Beholder beholder;

//    SampleIndex sampleList; // We store samples, but it seems useless in the current application...
    Peer myPeer;
    private boolean connectedToHabitat = false;

    public VCR(GUI gui) {
        this.gui = gui;
//        this.sampleList = new SampleIndex();
    }

//    public void newValues(SampleSet vs) {
//        Iterator it = vs.getSamples().iterator();
//        while (it.hasNext()) {
//            this.newSample((Sample)it.next());
//        }
//    }

//    public void newSample(Sample s) {
//
////        sampleList.newSample(s);
////      System.out.println("VCR.newSample: sample = "+s);
////        this.gui.showSample(s);
//        //this.alarmList.test(s);
//    }

    public void command(String string) {
//        this.serialMonitor.command(string);
    }

    public void connectToHabitat() throws VCRExceptionUnnableToConnectToHabitatLAN {
        // OJO: TENEMOS QUE COMPLETAR TEMA EXCEPTIONS!!!
        this.connectedToHabitat = true;
        try {
            this.myPeer = new Peer("VCR");
            this.myPeer.joinGroup(new Group("MoteCloud"));
            this.beholder = new Beholder(this);
        } catch (ImpossibleToCreateDDSDomainParticipant ex) {
            Logger.getLogger(VCR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean connectedToHabitat() {
        return connectedToHabitat;
    }
}
