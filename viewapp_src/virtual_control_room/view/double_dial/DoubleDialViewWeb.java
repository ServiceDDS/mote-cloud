/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package virtual_control_room.view.double_dial;

import java.util.Collection;
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
import org.sddsxmpp.commands.packetextensions.SDDSPacketExtensionProvider;
import org.sddsxmpp.commands.packetextensions.SamplePacketExtension;
import org.sddsxmpp.commands.packetextensions.SamplePacketExtensionProvider;
import org.sddsxmpp.commands.packetextensions.SamplefieldPacketExtension;
import org.sddsxmpp.commands.packetextensions.SamplefieldPacketExtensionProvider;
import samples.DoubleSample;
import samples.Sample;

/**
 *
 * @author JA Dianes
 */
public class DoubleDialViewWeb extends DoubleDialView implements PacketListener {

    private String user;
    private String password;
    private String server;
    private XMPPConnection con;
    private String serverJID="statellite-w7";

    public DoubleDialViewWeb(int moteID1, int moteID2, String name1, String name2,
                        String server, String user, String password) {
        super(moteID1, moteID2, name2, name2);
         try {
            this.user = user;
            this.password = password;
            this.server = server;
            // Register packet extension providers ???
            ProviderManager.getInstance().addExtensionProvider("sdds", "sddsopenfire", new SDDSPacketExtensionProvider());
            ProviderManager.getInstance().addExtensionProvider("sample", "sddsopenfire", new SamplePacketExtensionProvider());
            ProviderManager.getInstance().addExtensionProvider("samplefield", "sddsopenfire", new SamplefieldPacketExtensionProvider());

            // Connect to the server
            this.con = new XMPPConnection(server);
            con.connect();
            con.addPacketListener(this, null);
            // Most servers require you to login before performing other tasks.
            con.login(user, password);
//            System.out.println("WebChatter.start: connection LOGGED with " + con.getConnectionID());

            // Join ServiceDDS group HABITAT
            MyJoinCommandIQ joinCommand = new MyJoinCommandIQ("HABITAT");
            joinCommand.setTo("serviceddsopenfire."+this.serverJID);
            joinCommand.setFrom(this.user+"@"+this.serverJID);
            System.out.println(joinCommand.toXML());
            con.sendPacket(joinCommand);

            // Send New command
            MyNewCommandIQ newCommand = new MyNewCommandIQ(
                                    "MoteSample.SampleTopic",
                                    "SampleTopic");
            newCommand.setTo("serviceddsopenfire."+this.serverJID);
            newCommand.setFrom(this.user+"@"+this.serverJID);
            System.out.println(newCommand.toXML());
            con.sendPacket(newCommand);

            // Listen command
            MyListenCommandIQ listenCommand = new MyListenCommandIQ(
                            "SampleTopic");
            listenCommand.setTo("serviceddsopenfire."+this.serverJID);
            listenCommand.setFrom(this.user+"@"+this.serverJID);
            System.out.println(listenCommand.toXML());
            con.sendPacket(listenCommand);

        } catch (XMPPException ex) {
            Logger.getLogger(DoubleDialViewWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void processPacket(Packet packet) {
//        System.out.println("DoubleDialViewWeb.processPacket()");
        if (packet instanceof Message) {
//            System.out.println("DoubleDialViewWeb.processPacket(): Message packet received "+packet.toXML());
            Message msg = (Message)packet;
            SamplePacketExtension samplePE = (SamplePacketExtension) msg.getExtension("sample","sddsopenfire");
            if (samplePE != null) {
//               System.out.println("DoubleDialViewWeb.processPacket(): found sample extension "+samplePE.toXML());
                Collection<PacketExtension> allExtensions = msg.getExtensions();
                Iterator<PacketExtension> it = allExtensions.iterator();
                it.next(); // Skip SDDS p.e.
                it.next(); // Skip sample p.e.
                SamplefieldPacketExtension moteIDPE = (SamplefieldPacketExtension) it.next();
//                System.out.println("DoubleDialViewWeb.processPacket(): found sampelfield extension "+moteIDPE.toXML());
                SamplefieldPacketExtension nsamplePE = (SamplefieldPacketExtension) it.next();
//                System.out.println("DoubleDialViewWeb.processPacket(): found sampelfield extension "+nsamplePE.toXML());
                SamplefieldPacketExtension vblePE = (SamplefieldPacketExtension) it.next();
//                System.out.println("DoubleDialViewWeb.processPacket(): found sampelfield extension "+vblePE.toXML());
                SamplefieldPacketExtension valuePE = (SamplefieldPacketExtension) it.next();
//                System.out.println("DoubleDialViewWeb.processPacket(): found sampelfield extension "+valuePE.toXML());
                Sample s = new DoubleSample(
                        Integer.parseInt(moteIDPE.value),
                        Integer.parseInt(nsamplePE.value),
                        vblePE.value,
                        Double.parseDouble(valuePE.value)
                        );
            if (((this.moteID1 == s.moteID) || (this.moteID2 == s.moteID))
             && ((s.key.compareTo(this.variable1)==0) || (s.key.compareTo(this.variable2)==0))) {
                    this.showSample(s);
                }
            }
        } else {
            System.err.println("DoubleDialViewWeb.processPacket(): no Message packet arrived!!");
        }
    }

}
