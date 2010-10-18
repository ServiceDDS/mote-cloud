package MoteSample;

public interface SampleTopicDataWriterOperations extends
    DDS.DataWriterOperations
{

    long register_instance(
            MoteSample.SampleTopic instance_data);

    long register_instance_w_timestamp(
            MoteSample.SampleTopic instance_data, 
            DDS.Time_t source_timestamp);

    int unregister_instance(
            MoteSample.SampleTopic instance_data, 
            long handle);

    int unregister_instance_w_timestamp(
            MoteSample.SampleTopic instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int write(
            MoteSample.SampleTopic instance_data, 
            long handle);

    int write_w_timestamp(
            MoteSample.SampleTopic instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int dispose(
            MoteSample.SampleTopic instance_data, 
            long instance_handle);

    int dispose_w_timestamp(
            MoteSample.SampleTopic instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);
    
    int writedispose(
            MoteSample.SampleTopic instance_data, 
            long instance_handle);

    int writedispose_w_timestamp(
            MoteSample.SampleTopic instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);

    int get_key_value(
            MoteSample.SampleTopicHolder key_holder, 
            long handle);
    
    long lookup_instance(
            MoteSample.SampleTopic instance_data);

}
