package com.solace.maas.asyncapigeneratorlib.model.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AsyncApiMQTTOperationBinding extends AsyncApiOperationBinding {

    private int qos;
    private static final String CURRENT_VERSION = "0.1.0";

    @Override
    public String getBindingVersion() {
        return CURRENT_VERSION;
    }
}
