package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.AsyncApiOperation;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiBindingProtocol;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiOperationBinding;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiRESTOperationBinding;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class RESTOperationBindingsGenerator implements OperationBindingsGenerator {

    @Override
    public JSONObject generateOperationBindingNode(AsyncApiOperation.OperationType operationType, AsyncApiOperationBinding operationBinding) {
        AsyncApiRESTOperationBinding asyncApiRESTOperationBinding = (AsyncApiRESTOperationBinding) operationBinding;
        JSONObject bindingNode = new JSONObject();
        bindingNode.put("bindingVersion", asyncApiRESTOperationBinding.getBindingVersion());
        bindingNode.put("type", asyncApiRESTOperationBinding.getType());
        bindingNode.put("method", asyncApiRESTOperationBinding.getMethod());
        return bindingNode;
    }

    @Override
    public AsyncApiBindingProtocol getBindingProtocol() {
        return AsyncApiBindingProtocol.rest;
    }
}
