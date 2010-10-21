import ServiceDDS.Peer;
import ServiceDDS.QoSParameters;
import ServiceDDS.servicetopic.ServiceTopic;
import ServiceDDS.servicetopic.WriterServiceTopic;
import data_structures.SampleSet;
import data_structures.SampleStreamReader;
import edu.uma.motecloud.sink.Module;
import edu.uma.motecloud.sink.exceptions.ModuleNotInitializedException;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.html.HTMLDocument.Iterator;

import samples.Sample;

/**
 * 
 * @author Jose Angel Dianes
 * @version 0.1a
 * @date 10/15/2010
 */
public class SquidBeeModule extends Module {

	public static SerialPort port;
    BufferedInputStream input;

    protected void finalize() throws Throwable {
        //  input.close();
        port.close();
        super.finalize();
    }

	public void setup(String[] arg0) {
		System.out.println("SquidBeeModule.setup()");
        try {
            port = (SerialPort) CommPortIdentifier.getPortIdentifier(arg0[0]).open("NMEAPrueba", 3000);
            port.setSerialPortParams(
            		new Integer(arg0[1]).intValue(), 
            		SerialPort.DATABITS_8, 
            		SerialPort.STOPBITS_1, 
            		SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(SquidBeeModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPortException ex) {
            Logger.getLogger(SquidBeeModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PortInUseException ex) {
            Logger.getLogger(SquidBeeModule.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	public void run() {
		System.out.println("SquidBeeModule.run()");
        int available=0;
        byte[] data;

        try {
            input = new BufferedInputStream(port.getInputStream());
            String fullStream = new String();
            while (available<=100) {
                if (input.available() > 0) {
                    data = new byte[input.available()];
                    available = input.read(data,0,data.length);
                    String newStream = new String(data);
                    if (newStream.contains(System.getProperty("line.separator"))) {
                        fullStream = fullStream.concat(newStream.substring(0, newStream.indexOf(System.getProperty("line.separator"))));
                        writeInputToSample(fullStream);
                        if (newStream.length()
                                >
                            (newStream.lastIndexOf(System.getProperty("line.separator"))
                            +System.getProperty("line.separator").length())
                            ) {
                          //System.out.println("Beholder.run(): cadena final contiene restos "+newStream);
                            fullStream = new String(
                                    newStream.substring(
                                        newStream.indexOf(System.getProperty("line.separator"))
                                        +System.getProperty("line.separator").length()
                                    )
                            );
                        } else {
                            fullStream = new String();
                        }
                    } else {
                        fullStream = fullStream.concat(newStream);
                    }

                    System.out.println(newStream);
                }
            }
            input.close();
            port.close();
        } catch (IOException ex) {
            Logger.getLogger(SquidBeeModule.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
    private void writeInputToSample(String data) {
    	System.out.println("SquidBeeModule.writeInputToSample(): writing input = "+data);
    	SampleSet ss = SampleStreamReader.getSamples(data);
    	ListIterator it = ss.getSamples().listIterator();
    	while (it.hasNext()) {
    		Sample s = (Sample)it.next();
    		try {
				this.newSample(s.moteID, s.nsample, s.key, ""+s.getValue().toString());
				System.out.println("SquidBeeModule.writeInputToSample: "+
						s.moteID+" "+
						s.nsample+" "+
						s.key+" "+
						s.getValue().toString());
			} catch (ModuleNotInitializedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }	
}
