package MoteSample;

import org.opensplice.dds.dcps.Utilities;

public final class SampleTopicTypeSupportHelper
{

    public static MoteSample.SampleTopicTypeSupport narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.SampleTopicTypeSupport) {
            return (MoteSample.SampleTopicTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MoteSample.SampleTopicTypeSupport unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.SampleTopicTypeSupport) {
            return (MoteSample.SampleTopicTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
