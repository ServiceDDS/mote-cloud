package MoteSample;

import org.opensplice.dds.dcps.Utilities;

public final class SampleTopicDataReaderHelper
{

    public static MoteSample.SampleTopicDataReader narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.SampleTopicDataReader) {
            return (MoteSample.SampleTopicDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MoteSample.SampleTopicDataReader unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.SampleTopicDataReader) {
            return (MoteSample.SampleTopicDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
