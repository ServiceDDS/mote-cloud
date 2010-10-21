package data_structures;

import java.util.LinkedList;
import java.util.List;

import samples.Sample;

/**
 *
 * @author JA Dianes
 */
public class SampleSet {

    LinkedList samples = new LinkedList();

    public void addSample(Sample s) {
        samples.add(s);
    }

    public List getSamples() {
        return samples;
    }

}
