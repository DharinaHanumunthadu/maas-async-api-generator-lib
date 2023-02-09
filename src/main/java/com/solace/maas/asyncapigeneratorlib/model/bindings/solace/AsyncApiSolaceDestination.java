package com.solace.maas.asyncapigeneratorlib.model.bindings.solace;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AsyncApiSolaceDestination {

    private DeliveryMode deliveryMode;
    private DestinationType destinationType;
    private QueueDestination queueDestination;
    private TopicDestination topicDestination;

    public enum DeliveryMode {
        persistent, direct
    }

    public enum DestinationType {
        queue, topic
    }

    public enum AccessType {
        exclusive, nonexclusive
    }

    @Data
    @Builder
    public static class QueueDestination {
        private String name;
        private AccessType accessType;
        private String maxTtl;
        private String maxMsgSpoolUsg;
        private List<String> topicSubscriptions;
    }

    @Data
    @Builder
    public static class TopicDestination {
        private String name;
        private List<String> topicSubscriptions;
    }
}
