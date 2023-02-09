package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiBindingProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

@Component
public class OperationBindingsGenerators {

    private final Map<AsyncApiBindingProtocol, OperationBindingsGenerator> bindingProtocolToGeneratorMap;

    @Autowired
    public OperationBindingsGenerators(Set<OperationBindingsGenerator> operationBindingsGeneratorSet) {
        bindingProtocolToGeneratorMap = new EnumMap<>(AsyncApiBindingProtocol.class);
        operationBindingsGeneratorSet.forEach(bindingGenerator ->
                bindingProtocolToGeneratorMap.put(bindingGenerator.getBindingProtocol(), bindingGenerator));
    }

    public OperationBindingsGenerator getBindingGenerator(AsyncApiBindingProtocol asyncApiBindingProtocol) {
        return bindingProtocolToGeneratorMap.get(asyncApiBindingProtocol);
    }
}
