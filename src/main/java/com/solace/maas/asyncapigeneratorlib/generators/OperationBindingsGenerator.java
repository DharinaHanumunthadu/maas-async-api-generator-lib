package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.AsyncApiOperation;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiBindingProtocol;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiOperationBinding;
import org.json.JSONObject;

public interface OperationBindingsGenerator {
    JSONObject generateOperationBindingNode(AsyncApiOperation.OperationType operationType, AsyncApiOperationBinding operationBinding);

    AsyncApiBindingProtocol getBindingProtocol();
}
