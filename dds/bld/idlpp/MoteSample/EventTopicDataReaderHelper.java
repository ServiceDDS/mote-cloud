package MoteSample;

import org.opensplice.dds.dcps.Utilities;

public final class EventTopicDataReaderHelper
{

    public static MoteSample.EventTopicDataReader narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.EventTopicDataReader) {
            return (MoteSample.EventTopicDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MoteSample.EventTopicDataReader unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.EventTopicDataReader) {
            return (MoteSample.EventTopicDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
