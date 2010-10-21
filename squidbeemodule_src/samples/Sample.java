package samples;

/**
 *
 * @author JA Dianes
 */
public abstract class Sample {
    public int moteID;
    public int nsample;
    public String key;

    public Sample(int moteID, int nsample, String key) {
        this.moteID = moteID;
        this.nsample = nsample;
        this.key = key;
    }

    public abstract Object getValue();

}
