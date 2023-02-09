package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiDisplayExtensionOptions;
import com.solace.maas.asyncapigeneratorlib.model.servers.AsyncApiServer;
import com.solace.maas.asyncapigeneratorlib.model.servers.AsyncApiServers;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class AsyncApiServerGenerator extends AsyncApiFieldExtensionHelper {

    public void addServers(AsyncApiServers servers, JSONObject root, AsyncApiDisplayExtensionOptions includedExtensions) {
        Map<String, Set<AsyncApiServer>> commonServer = servers.getServers();
        JSONObject serversNode = root.getJSONObject("servers");
        for (Map.Entry<String, Set<AsyncApiServer>> messagingService : commonServer.entrySet()) {

            // for each messaging service, create a server node per connection (suffixed with protocol)
            messagingService.getValue().forEach(messagingServiceConnection -> {
                JSONObject serverNode = new JSONObject();
                addToJsonObject(serverNode, "url", messagingServiceConnection.getUrl(), includedExtensions);
                addToJsonObject(serverNode, "protocol", messagingServiceConnection.getProtocol(), includedExtensions);
                addToJsonObject(serverNode, "protocolVersion", messagingServiceConnection.getProtocolVersion(), includedExtensions);
                serverNode.put("variables", new JSONObject().put(
                        "port",
                        new JSONObject().put("enum", messagingServiceConnection.getVariables().get("port").getEnumerations())
                ));

                serverNode.put("bindings", messagingServiceConnection.getBindings());
                serversNode.put(messagingService.getKey(), serverNode);
            });
        }

    }
}
