/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InstantTableView.java
 *
 * Created on 18-oct-2009, 16:19:10
 */

package virtual_control_room.view.instant_table;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.ProviderManager;
import org.sddsxmpp.commands.MyJoinCommandIQ;
import org.sddsxmpp.commands.MyListenCommandIQ;
import org.sddsxmpp.commands.MyNewCommandIQ;
import org.sddsxmpp.commands.packetextensions.*;
import samples.DoubleSample;
import samples.Sample;

/**
 *
 * @author JA Dianes
 */
public class InstantTableViewWeb extends InstantTableView implements
        PacketListener {

    String user;
    String password;
    String url;
    XMPPConnection con;
    private String serverJID;
    
    /** Creates new form InstantTableView */
    public InstantTableViewWeb(Hashtable<String,XMPPConnection> conTable,
            int moteID, String url, String serverJID, String user, String password) {
        super(moteID);
        try {
            this.user = user;
            this.password = password;
            this.url = url;
            this.serverJID = serverJID;
            // Register packet extension providers ???
            ProviderManager.getInstance().addExtensionProvider("sdds", "sddsopenfire", new SDDSPacketExtensionProvider());
            ProviderManager.getInstance().addExtensionProvider("sample", "sddsopenfire", new SamplePacketExtensionProvider());
            ProviderManager.getInstance().addExtensionProvider("samplefield", "sddsopenfire", new SamplefieldPacketExtensionProvider());

             // Connect to the server
            if (!conTable.containsKey(url)) {
               this.con = new XMPPConnection(this.url);
               con.connect();
               conTable.put(url, this.con);
            } else {
                this.con = conTable.get(url);
            }
            con.addPacketListener(this, null);
            // Most servers require you to login before performing other tasks.
            if (!con.isAuthenticated()) con.login(user, password);
            
//            System.out.println("WebChatter.start: connection LOGGED with " + con.getConnectionID());

            MyJoinCommandIQ joinCommand = new MyJoinCommandIQ("MoteCloud");
            joinCommand.setTo("serviceddsopenfire."+this.serverJID);
            joinCommand.setFrom(this.user+"@"+this.serverJID);
            System.out.println(joinCommand.toXML());
            con.sendPacket(joinCommand);

            MyNewCommandIQ newCommand = new MyNewCommandIQ(
                                    "MoteCloud.SampleTopic",
                                    "SampleTopic");
            newCommand.setTo("serviceddsopenfire."+this.serverJID);
            newCommand.setFrom(this.user+"@"+this.serverJID);
            System.out.println(newCommand.toXML());
            con.sendPacket(newCommand);

            MyListenCommandIQ listenCommand = new MyListenCommandIQ(
                            "SampleTopic");
            listenCommand.setTo("serviceddsopenfire."+this.serverJID);
            listenCommand.setFrom(this.user+"@"+this.serverJID);
            System.out.println(listenCommand.toXML());
            con.sendPacket(listenCommand);

        } catch (XMPPException ex) {
            Logger.getLogger(InstantTableViewWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void processPacket(Packet packet) {
//        System.out.println("InstantTableView.processPacket()");
        if (packet instanceof Message) {
//            System.out.println("InstantTableView.processPacket(): Message packet received "+packet.toXML());
            Message msg = (Message)packet;
            SamplePacketExtension samplePE = (SamplePacketExtension) msg.getExtension("sample","sddsopenfire");
            if (samplePE != null) {
//               System.out.println("InstantTableView.processPacket(): found sample extension "+samplePE.toXML());
                Collection<PacketExtension> allExtensions = msg.getExtensions();
                Iterator<PacketExtension> it = allExtensions.iterator();
                it.next(); // Skip SDDS p.e.
                it.next(); // Skip sample p.e.
                SamplefieldPacketExtension moteIDPE = (SamplefieldPacketExtension) it.next();
//                System.out.println("EvolutionLineChartViewWeb.processPacket(): found sampelfield extension moteID = "+moteIDPE.toXML());
                SamplefieldPacketExtension wsnIDPE = (SamplefieldPacketExtension) it.next();
//                System.out.println("EvolutionLineChartViewWeb.processPacket(): found sampelfield extension wsnID "+wsnIDPE.toXML());
                SamplefieldPacketExtension locationPE = (SamplefieldPacketExtension) it.next();
//                System.out.println("EvolutionLineChartViewWeb.processPacket(): found sampelfield extension location "+locationPE.toXML());
                SamplefieldPacketExtension nsamplePE = (SamplefieldPacketExtension) it.next();
//                System.out.println("EvolutionLineChartViewWeb.processPacket(): found sampelfield extension "+nsamplePE.toXML());
                SamplefieldPacketExtension vblePE = (SamplefieldPacketExtension) it.next();
//                System.out.println("EvolutionLineChartViewWeb.processPacket(): found sampelfield extension "+vblePE.toXML());
                SamplefieldPacketExtension valuePE = (SamplefieldPacketExtension) it.next();
//                System.out.println("EvolutionLineChartViewWeb.processPacket(): found sampelfield extension "+valuePE.toXML());
                Sample s = new DoubleSample(
                        Integer.parseInt(moteIDPE.value),
                        Integer.parseInt(nsamplePE.value),
                        vblePE.value,
                        Double.parseDouble(valuePE.value)
                        );
                if (s.moteID==this.moteID) this.showSample(s);
            }
        } else {
            System.err.println("InstantTableWebView.processPacket(): no Message packet arrived!!");
        }
    }
}
