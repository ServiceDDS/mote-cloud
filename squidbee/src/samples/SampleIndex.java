package samples;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 *
 * @author JA Dianes
 */
public class SampleIndex {

    Hashtable samples = new Hashtable();

    public void newSample(Sample s) {
        if (samples.containsKey(s.moteID)) {
            LinkedList sampleList = (LinkedList) samples.get(s.moteID);
            sampleList.add(s);
        } else {
            LinkedList sampleList = new LinkedList();
            sampleList.add(s);
            samples.put(s.moteID, sampleList);
        }
    }
}
