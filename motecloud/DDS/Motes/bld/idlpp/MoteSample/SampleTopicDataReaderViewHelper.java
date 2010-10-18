package MoteSample;

import org.opensplice.dds.dcps.Utilities;

public final class SampleTopicDataReaderViewHelper
{

    public static MoteSample.SampleTopicDataReaderView narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.SampleTopicDataReaderView) {
            return (MoteSample.SampleTopicDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MoteSample.SampleTopicDataReaderView unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MoteSample.SampleTopicDataReaderView) {
            return (MoteSample.SampleTopicDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
