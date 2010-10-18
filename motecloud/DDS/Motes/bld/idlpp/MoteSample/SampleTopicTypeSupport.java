package MoteSample;

import org.opensplice.dds.dcps.Utilities;

public class SampleTopicTypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements SampleTopicTypeSupportOperations
{
    private static java.lang.String idl_type_name = "MoteSample::SampleTopic";
    private static java.lang.String idl_key_list = "moteID";

    private long copyCache;

    public SampleTopicTypeSupport()
    {
        super("MoteSample/SampleTopicDataReaderImpl",
            "MoteSample/SampleTopicDataReaderViewImpl",
            "MoteSample/SampleTopicDataWriterImpl",
            "(LMoteSample/SampleTopicTypeSupport;)V",
            "null",
            "null");

        int success = 0;

        try {
            success = org.opensplice.dds.dcps.FooTypeSupportImpl.Alloc(
                    this,
                    idl_type_name,
                    idl_key_list,
                    MoteSample.SampleTopicMetaHolder.metaDescriptor);
        } catch (UnsatisfiedLinkError ule) {
            /*
             * JNI library is not loaded if no instance of the
             * DomainParticipantFactory exists.
             */
            DDS.DomainParticipantFactory f = DDS.DomainParticipantFactory.get_instance();

            if (f != null) {
                success = org.opensplice.dds.dcps.FooTypeSupportImpl.Alloc(
                        this,
                        idl_type_name,
                        idl_key_list,
                        MoteSample.SampleTopicMetaHolder.metaDescriptor);
            }
        }
        if (success == 0) {
            throw Utilities.createException(
                    Utilities.EXCEPTION_TYPE_NO_MEMORY,
                    "Could not allocate SampleTopicTypeSupport." );
        }
    }

    protected void finalize()
    {
        org.opensplice.dds.dcps.FooTypeSupportImpl.Free(this);
    }

    public long get_copyCache()
    {
        return copyCache;
    }

    public int register_type(
            DDS.DomainParticipant participant,
            java.lang.String type_name)
    {
        return org.opensplice.dds.dcps.FooTypeSupportImpl.registerType(
                this,
                participant,
                type_name);
    }

    public String get_type_name()
    {
        return idl_type_name;
    }

}
