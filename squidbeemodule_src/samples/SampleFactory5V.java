package samples;


/**
 *
 * @author JA Dianes
 */
public class SampleFactory5V extends SampleFactory {

    public Sample createSample(int moteID, int nsample, String key, int sample) {
      //System.out.println("SampleFactory5V.createSample... ");
        Sample res;
        if (key.compareTo("data0") == 0) { // Light sensor
            res = new DoubleSample(moteID, nsample, "light:"+moteID,
                    (100.0 / 950.0) * sample
                    );
        } else if (key.compareTo("data1") == 0) { // Humidity sensor
            res = new DoubleSample(moteID, nsample, "humidity:"+moteID,
                    ((sample * 5.0) / 1024.0) * 32.25 - 25.81
                    );
        } else if (key.compareTo("data2") == 0) {  // Temperature sensor
            res = new DoubleSample(moteID, nsample, "temperature:"+moteID,
                    (((sample * 5.0 )/ 10.24) - 50.0)
                    );
        } else {
            res = null;
        }

        return res;
    }
}
