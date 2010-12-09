package MoteSample;

public interface EventTopicDataWriterOperations extends
    DDS.DataWriterOperations
{

    long register_instance(
            MoteSample.EventTopic instance_data);

    long register_instance_w_timestamp(
            MoteSample.EventTopic instance_data, 
            DDS.Time_t source_timestamp);

    int unregister_instance(
            MoteSample.EventTopic instance_data, 
            long handle);

    int unregister_instance_w_timestamp(
            MoteSample.EventTopic instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int write(
            MoteSample.EventTopic instance_data, 
            long handle);

    int write_w_timestamp(
            MoteSample.EventTopic instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int dispose(
            MoteSample.EventTopic instance_data, 
            long instance_handle);

    int dispose_w_timestamp(
            MoteSample.EventTopic instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);
    
    int writedispose(
            MoteSample.EventTopic instance_data, 
            long instance_handle);

    int writedispose_w_timestamp(
            MoteSample.EventTopic instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);

    int get_key_value(
            MoteSample.EventTopicHolder key_holder, 
            long handle);
    
    long lookup_instance(
            MoteSample.EventTopic instance_data);

}
