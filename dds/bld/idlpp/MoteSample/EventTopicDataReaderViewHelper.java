package MoteSample;

import org.opensplice.dds.dcps.Utilities;

public final class EventTopicDataReaderViewHelper
{

    public static MoteSample.EventTopicDataReaderView narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.EventTopicDataReaderView) {
            return (MoteSample.EventTopicDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MoteSample.EventTopicDataReaderView unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.EventTopicDataReaderView) {
            return (MoteSample.EventTopicDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
