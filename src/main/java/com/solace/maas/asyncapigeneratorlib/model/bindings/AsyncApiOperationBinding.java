package com.solace.maas.asyncapigeneratorlib.model.bindings;

public abstract class AsyncApiOperationBinding {
    private static final String LATEST_BINDING_VERSION = "latest";

    public String getBindingVersion() {
        return LATEST_BINDING_VERSION;
    }
}
