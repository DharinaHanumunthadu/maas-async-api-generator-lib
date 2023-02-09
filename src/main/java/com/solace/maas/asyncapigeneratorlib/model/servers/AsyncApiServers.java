package com.solace.maas.asyncapigeneratorlib.model.servers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AsyncApiServers {

    private Map<String, Set<AsyncApiServer>> servers;

}
