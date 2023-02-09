package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.AsyncApiOperation;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiBindingProtocol;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiOperationBinding;
import com.solace.maas.asyncapigeneratorlib.model.bindings.solace.AsyncApiSolaceDestination;
import com.solace.maas.asyncapigeneratorlib.model.bindings.solace.AsyncApiSolaceOperationBinding;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SolaceOperationBindingsGenerator implements OperationBindingsGenerator {

    @Override
    public JSONObject generateOperationBindingNode(AsyncApiOperation.OperationType operationType, AsyncApiOperationBinding operationBinding) {
        AsyncApiSolaceOperationBinding asyncApiSolaceOperationBinding = (AsyncApiSolaceOperationBinding) operationBinding;
        JSONObject bindingNode = new JSONObject();
        bindingNode.put("bindingVersion", asyncApiSolaceOperationBinding.getBindingVersion());
        bindingNode.put("destinations", getDestinations(operationType, asyncApiSolaceOperationBinding));
        return bindingNode;
    }

    @Override
    public AsyncApiBindingProtocol getBindingProtocol() {
        return AsyncApiBindingProtocol.solace;
    }

    private JSONArray getDestinations(AsyncApiOperation.OperationType operationType, AsyncApiSolaceOperationBinding asyncApiSolaceOperationBinding) {
        JSONArray destinationNodes = new JSONArray();
        for (AsyncApiSolaceDestination destination : asyncApiSolaceOperationBinding.getDestinations()) {
            JSONObject destinationNode = new JSONObject();
            if (AsyncApiOperation.OperationType.subscribe.equals(operationType)) {
                destinationNode.put("deliveryMode", destination.getDeliveryMode());
            } else if (AsyncApiOperation.OperationType.publish.equals(operationType)) {
                destinationNode.put("destinationType", destination.getDestinationType());
                if (destination.getQueueDestination() != null) {
                    destinationNode.put("queue", buildQueueNode(destination));
                } else {
                    destinationNode.put("topic", buildTopicNode(destination));
                }
            }
            destinationNodes.put(destinationNode);
        }
        return destinationNodes;
    }

    private JSONObject buildQueueNode(AsyncApiSolaceDestination destination) {
        JSONObject queueNode = new JSONObject();
        queueNode.put("name", destination.getQueueDestination().getName());
        queueNode.put("topicSubscriptions", destination.getQueueDestination().getTopicSubscriptions());
        queueNode.put("accessType", destination.getQueueDestination().getAccessType());
        queueNode.put("maxTtl", destination.getQueueDestination().getMaxTtl());
        queueNode.put("maxMsgSpoolUsage", destination.getQueueDestination().getMaxMsgSpoolUsg());
        return queueNode;
    }

    private JSONObject buildTopicNode(AsyncApiSolaceDestination destination) {
        JSONObject topicNode = new JSONObject();
        topicNode.put("name", destination.getTopicDestination().getName());
        topicNode.put("topicSubscriptions", destination.getTopicDestination().getTopicSubscriptions());
        return topicNode;
    }
}
