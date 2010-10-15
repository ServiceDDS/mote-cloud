package MoteSample;

import org.opensplice.dds.dcps.Utilities;

public final class SampleTopicDataWriterHelper
{

    public static MoteSample.SampleTopicDataWriter narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.SampleTopicDataWriter) {
            return (MoteSample.SampleTopicDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MoteSample.SampleTopicDataWriter unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.SampleTopicDataWriter) {
            return (MoteSample.SampleTopicDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
