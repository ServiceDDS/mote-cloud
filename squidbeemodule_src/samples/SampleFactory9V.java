package samples;

/**
 *
 * @author JA Dianes
 */
public class SampleFactory9V extends SampleFactory {

    public Sample createSample(int moteID, int nsample, String key, int sample) {
        Sample value;
        if (key.compareTo("data0") == 0) { // Light sensor
            value = new DoubleSample(moteID, nsample, "light:"+moteID,
                    (100 / 950) * sample
                    );
        } else if (key.compareTo("data1") == 0) { // Humidity sensor
            value = new DoubleSample(moteID, nsample, "humidity:"+moteID,
                    (sample * 5 / 1024.0) * 32.25 - 25.81
                    );
        } else if (key.compareTo("data2") == 0) {  // Temperature sensor
            value = new DoubleSample(moteID, nsample, "temperature:"+moteID,
                    ((sample * 625.0 / 256.0) - 160.0) / 9.0
                    );
        } else {
            value = null;
        }

        return value;
    }
}
