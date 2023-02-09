package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.AsyncApiOperation;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiBindingProtocol;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiMQTTOperationBinding;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiOperationBinding;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MQTTOperationBindingsGenerator implements OperationBindingsGenerator {

    @Override
    public JSONObject generateOperationBindingNode(AsyncApiOperation.OperationType operationType, AsyncApiOperationBinding operationBinding) {
        AsyncApiMQTTOperationBinding asyncApiMQTTOperationBinding = (AsyncApiMQTTOperationBinding) operationBinding;
        JSONObject bindingNode = new JSONObject();
        bindingNode.put("bindingVersion", asyncApiMQTTOperationBinding.getBindingVersion());
        bindingNode.put("qos", asyncApiMQTTOperationBinding.getQos());
        return bindingNode;
    }

    @Override
    public AsyncApiBindingProtocol getBindingProtocol() {
        return AsyncApiBindingProtocol.mqtt;
    }

}
