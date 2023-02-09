package com.solace.maas.asyncapigeneratorlib.model.servers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AsyncApiServer {

    private String url;
    private String protocol;
    private String protocolVersion;
    private String description;
    private Map<String, AsyncApiServerVariable> variables;

    private Map<String, Object> bindings;


}
