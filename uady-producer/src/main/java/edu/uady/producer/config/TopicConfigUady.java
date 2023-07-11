package edu.uady.producer.config;



import java.util.HashMap;
import java.util.Map;

@Configuration
public class TopicConfigUady {

    @Bean
    public NewTopic topicCreate() {
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE );
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "90000000000");
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "90000000000");
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "300");
        return TopicBuilder.name("uady-topic")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }
}
