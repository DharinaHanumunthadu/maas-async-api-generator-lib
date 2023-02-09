package com.solace.maas.asyncapigeneratorlib.model;

import com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiVersion;
import com.solace.maas.asyncapigeneratorlib.model.servers.AsyncApiServers;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class AsyncApiSpec {
    private AsyncApiInfo info;
    private AsyncApiServers servers;
    private Collection<AsyncApiChannel> channels;
    private Collection<AsyncApiMessage> messages;
    private Collection<AsyncApiSchema> schemas;
    private AsyncApiVersion asyncApiVersion;
}
