package MoteSample;

public final class SampleTopic {

    public int moteID;
    public int sample;
    public java.lang.String variableName = "";
    public java.lang.String data = "";

    public SampleTopic() {
    }

    public SampleTopic(
        int _moteID,
        int _sample,
        java.lang.String _variableName,
        java.lang.String _data)
    {
        moteID = _moteID;
        sample = _sample;
        variableName = _variableName;
        data = _data;
    }

}
