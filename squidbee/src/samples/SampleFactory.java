package samples;

/**
 *
 * @author JA Dianes
 */
public abstract class SampleFactory {

    public abstract Sample createSample(int moteID, int nsample, String key, int sample);

}
