package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.AsyncApiOperation;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiBindingProtocol;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiOperationBinding;
import com.solace.maas.asyncapigeneratorlib.model.bindings.kafka.AsyncApiKafkaOperationBinding;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class KafkaOperationBindingsGenerator implements OperationBindingsGenerator {

    @Override
    public JSONObject generateOperationBindingNode(AsyncApiOperation.OperationType operationType, AsyncApiOperationBinding operationBinding) {
        AsyncApiKafkaOperationBinding asyncApiKafkaOperationBinding = (AsyncApiKafkaOperationBinding) operationBinding;
        JSONObject bindingNode = new JSONObject();
        bindingNode.put("bindingVersion", asyncApiKafkaOperationBinding.getBindingVersion());
        bindingNode.put("clientId", asyncApiKafkaOperationBinding.getClientIdSchema());
        return bindingNode;
    }

    @Override
    public AsyncApiBindingProtocol getBindingProtocol() {
        return AsyncApiBindingProtocol.kafka;
    }
}
