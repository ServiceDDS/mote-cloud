package MoteSample;

import org.opensplice.dds.dcps.Utilities;

public final class EventTopicDataWriterHelper
{

    public static MoteSample.EventTopicDataWriter narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.EventTopicDataWriter) {
            return (MoteSample.EventTopicDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MoteSample.EventTopicDataWriter unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.EventTopicDataWriter) {
            return (MoteSample.EventTopicDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
