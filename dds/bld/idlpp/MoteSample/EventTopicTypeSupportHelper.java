package MoteSample;

import org.opensplice.dds.dcps.Utilities;

public final class EventTopicTypeSupportHelper
{

    public static MoteSample.EventTopicTypeSupport narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.EventTopicTypeSupport) {
            return (MoteSample.EventTopicTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MoteSample.EventTopicTypeSupport unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.EventTopicTypeSupport) {
            return (MoteSample.EventTopicTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
