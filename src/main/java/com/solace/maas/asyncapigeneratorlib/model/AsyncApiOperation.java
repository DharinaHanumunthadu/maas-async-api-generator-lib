package com.solace.maas.asyncapigeneratorlib.model;

import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiBindingProtocol;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiOperationBinding;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Builder
@Data
public class AsyncApiOperation {

    private OperationType type;
    private final Set<AsyncApiMessage> messages = new HashSet<>();
    private Map<AsyncApiBindingProtocol, AsyncApiOperationBinding> bindingByAsyncApiProtocol;

    public void addMessage(AsyncApiMessage message) {
        messages.add(message);
    }

    public enum OperationType {
        publish, subscribe
    }
}
