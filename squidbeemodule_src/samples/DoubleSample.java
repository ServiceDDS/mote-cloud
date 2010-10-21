package samples;

/**
 *
 * @author JA Dianes
 */
public class DoubleSample extends Sample {

    double value;

    public DoubleSample(int moteID, int nsample, String key, double value) {
        super(moteID, nsample, key);
        this.value = value;
    }

    @Override
    public Object getValue() {
        return new Double(value);
    }
}
