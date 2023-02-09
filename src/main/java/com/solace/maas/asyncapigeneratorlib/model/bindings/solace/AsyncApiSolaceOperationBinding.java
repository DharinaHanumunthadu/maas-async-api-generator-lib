package com.solace.maas.asyncapigeneratorlib.model.bindings.solace;

import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiOperationBinding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AsyncApiSolaceOperationBinding extends AsyncApiOperationBinding {
    private static final String CURRENT_VERSION = "0.3.0";
    private List<AsyncApiSolaceDestination> destinations = new ArrayList<>();

    public void addDestination(AsyncApiSolaceDestination destination) {
        destinations.add(destination);
    }

    @Override
    public String getBindingVersion() {
        return CURRENT_VERSION;
    }
}
