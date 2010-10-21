package data_structures;

import java.util.StringTokenizer;

import samples.Sample;
import samples.SampleFactory5V;

/**
 *
 * @author JA Dianes
 */
public class SampleStreamReader {

    public static SampleSet getSamples(String stream) {
      System.out.println("SampleStreamReader.getValues(): "+stream);
        stream = stream.substring(stream.indexOf("@"));
        SampleSet samples = new SampleSet();
        StringTokenizer stk = new StringTokenizer(stream, "ÿþàÀ#"+System.getProperty("line.separator"));
        if (stk.hasMoreElements()) {
            String sampleString = stk.nextToken();
          System.out.println("STK Token: "+sampleString);
            StringTokenizer sstk = new StringTokenizer(sampleString, "|");
            int moteID = new Integer(new Integer(sstk.nextToken("@|")));
          System.out.println("SSTK Token: moteID: "+moteID);
            int sample = new Integer(sstk.nextToken("@|")).intValue();
          System.out.println("SSTK Token: sample: "+sample);
            while (sstk.hasMoreElements()) {
                String key = sstk.nextToken("-|");
                Integer value = new Integer(sstk.nextToken("-|"));
                Sample newSample = (new SampleFactory5V()).createSample(moteID, sample, key, value.intValue());
              System.out.println("SSTK Token: key: "+newSample.key);
              System.out.println("SSTK Token: value: "+newSample.getValue());
                samples.addSample(newSample);
            }
            
        }


        return samples;
    }
}
