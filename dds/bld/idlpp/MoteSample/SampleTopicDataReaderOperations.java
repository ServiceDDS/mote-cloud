package MoteSample;

public interface SampleTopicDataReaderOperations extends
    DDS.DataReaderOperations
{

    int read(
            MoteSample.SampleTopicSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take(
            MoteSample.SampleTopicSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_w_condition(
            MoteSample.SampleTopicSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            DDS.ReadCondition a_condition);

    int take_w_condition(
            MoteSample.SampleTopicSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            DDS.ReadCondition a_condition);

    int read_next_sample(
            MoteSample.SampleTopicHolder received_data, 
            DDS.SampleInfoHolder sample_info);

    int take_next_sample(
            MoteSample.SampleTopicHolder received_data, 
            DDS.SampleInfoHolder sample_info);

    int read_instance(
            MoteSample.SampleTopicSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples,
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take_instance(
            MoteSample.SampleTopicSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_next_instance(
            MoteSample.SampleTopicSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take_next_instance(
            MoteSample.SampleTopicSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_next_instance_w_condition(
            MoteSample.SampleTopicSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            DDS.ReadCondition a_condition);

    int take_next_instance_w_condition(
            MoteSample.SampleTopicSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            DDS.ReadCondition a_condition);

    int return_loan(
            MoteSample.SampleTopicSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq);

    int get_key_value(
            MoteSample.SampleTopicHolder key_holder, 
            long handle);
    
    long lookup_instance(
            MoteSample.SampleTopic instance);

}
