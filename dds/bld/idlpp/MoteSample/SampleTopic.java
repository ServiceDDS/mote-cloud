package MoteSample;

public final class SampleTopic {

    public int moteID;
    public java.lang.String wsnID = "";
    public java.lang.String location = "";
    public int sample;
    public java.lang.String variableName = "";
    public java.lang.String data = "";

    public SampleTopic() {
    }

    public SampleTopic(
        int _moteID,
        java.lang.String _wsnID,
        java.lang.String _location,
        int _sample,
        java.lang.String _variableName,
        java.lang.String _data)
    {
        moteID = _moteID;
        wsnID = _wsnID;
        location = _location;
        sample = _sample;
        variableName = _variableName;
        data = _data;
    }

}
