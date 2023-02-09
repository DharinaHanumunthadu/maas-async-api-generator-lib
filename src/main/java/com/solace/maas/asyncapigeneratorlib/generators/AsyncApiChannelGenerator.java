package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.AsyncApiChannel;
import com.solace.maas.asyncapigeneratorlib.model.AsyncApiChannelParameter;
import com.solace.maas.asyncapigeneratorlib.model.AsyncApiMessage;
import com.solace.maas.asyncapigeneratorlib.model.AsyncApiOperation;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiBindingProtocol;
import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiOperationBinding;
import com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiDisplayExtensionOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.ENUM_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.ENUM_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.ENUM_STATE_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.ENUM_STATE_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.ENUM_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.ENUM_VERSION_DISPLAY_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.ENUM_VERSION_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.PARAMETER_NAME;

@Slf4j
@Component
public class AsyncApiChannelGenerator extends AsyncApiFieldExtensionHelper {

    private final OperationBindingsGenerators operationBindingsGenerators;

    public AsyncApiChannelGenerator(OperationBindingsGenerators operationBindingsGenerators) {
        super();
        this.operationBindingsGenerators = operationBindingsGenerators;
    }

    public String getChannelName(AsyncApiChannel channel) {
        // We need to encode the channelName to handle spaces, but unfortunately this also encodes braces and #.
        // So we have to decode just the braces and # too.
        String channelName = UriUtils.encodePath(channel.getName(), StandardCharsets.UTF_8);
        channelName = channelName.replace("%7B", "{");
        channelName = channelName.replace("%7D", "}");
        channelName = channelName.replace("%23", "#");

        return channelName;
    }

    public JSONObject createChannelNode(AsyncApiChannel channel, AsyncApiDisplayExtensionOptions includedExtensions) {
        JSONObject channelNode = new JSONObject();

        List<AsyncApiChannelParameter> parameters = channel.getParameters();
        if (!ObjectUtils.isEmpty(parameters)) {
            channelNode.put("parameters", createParametersNode(parameters, includedExtensions));
        }

        addOperationToChannel(channelNode, channel.getPublishOperation());
        addOperationToChannel(channelNode, channel.getSubscribeOperation());

        return channelNode;
    }

    private JSONObject createParametersNode(List<AsyncApiChannelParameter> parameters, AsyncApiDisplayExtensionOptions includedExtensions) {
        JSONObject parametersNode = new JSONObject();
        for (AsyncApiChannelParameter parameter : parameters) {

            JSONObject parameterNode = new JSONObject();

            addToJsonObject(parameterNode, ENUM_VERSION.getValue(), parameter.getEnumVersion(), includedExtensions);
            addToJsonObject(parameterNode, ENUM_VERSION_ID.getValue(), parameter.getEnumVersionId(), includedExtensions);
            addToJsonObject(parameterNode, ENUM_ID.getValue(), parameter.getParentEnumId(), includedExtensions);
            addToJsonObject(parameterNode, ENUM_NAME.getValue(), parameter.getParentEnumName(), includedExtensions);
            addToJsonObject(parameterNode, ENUM_STATE_ID.getValue(), parameter.getEnumVersionStateId(), includedExtensions);
            addToJsonObject(parameterNode, ENUM_STATE_NAME.getValue(), parameter.getStateName(), includedExtensions);
            addToJsonObject(parameterNode, ENUM_VERSION_DISPLAY_NAME.getValue(), parameter.getEnumVersionDisplayName(), includedExtensions);
            addToJsonObject(parameterNode, PARAMETER_NAME.getValue(), parameter.getAddressNodeName(), includedExtensions);

            parametersNode.put(parameter.getDisplayName(), parameterNode);

            JSONObject schemaNode = new JSONObject();
            schemaNode.put("type", "string");
            parameterNode.put("schema", schemaNode);
            AsyncApiChannelParameter.AsyncApiChannelParameterSchema parameterSchema = parameter.getAsyncApiChannelParameterSchema();
            if (parameterSchema != null) {
                List<String> enumValues = parameterSchema.getEnumValues();
                if (!ObjectUtils.isEmpty(enumValues)) {
                    schemaNode.put("enum", enumValues);
                } else if (StringUtils.isNotBlank(parameterSchema.getPattern())) {
                    schemaNode.put("pattern", parameterSchema.getPattern());
                }
            }
        }
        return parametersNode;
    }

    private void addOperationToChannel(JSONObject channelNode, AsyncApiOperation operation) {
        if (operation == null) {
            return;
        }
        Set<AsyncApiMessage> messageSet = operation.getMessages();
        if (ObjectUtils.isEmpty(messageSet)) {
            return;
        }
        AsyncApiMessage[] messages = messageSet.toArray(new AsyncApiMessage[0]);
        Arrays.sort(messages, Comparator.comparing(AsyncApiMessage::getParentName));

        JSONObject operationNode = new JSONObject();
        channelNode.put(operation.getType().name(), operationNode);

        int numMessages = messages.length;
        JSONObject messageNode = new JSONObject();
        operationNode.put("message", messageNode);

        if (numMessages == 1) {
            AsyncApiMessage message = messages[0];
            messageNode.put("$ref", "#/components/messages/" + message.getComponentDisplayName());
        } else {
            JSONArray oneOfMessageNode = new JSONArray();
            messageNode.put("oneOf", oneOfMessageNode);

            for (int i = 0; i < numMessages; i++) {
                AsyncApiMessage message = messages[i];
                oneOfMessageNode.put(i, new JSONObject().put("$ref", "#/components/messages/" + message.getComponentDisplayName()));
            }
        }
        operationNode.put("bindings", getOperationBindings(operation));
    }

    private JSONObject getOperationBindings(AsyncApiOperation operation) {
        Map<AsyncApiBindingProtocol, AsyncApiOperationBinding> bindingByAsyncApiProtocol = operation.getBindingByAsyncApiProtocol();
        if (MapUtils.isEmpty(bindingByAsyncApiProtocol)) {
            return null;
        }
        JSONObject operationBindingNodes = new JSONObject();
        for (Map.Entry<AsyncApiBindingProtocol, AsyncApiOperationBinding> operationBindingPerProtocol : bindingByAsyncApiProtocol.entrySet()) {
            OperationBindingsGenerator operationBindingsGenerator = operationBindingsGenerators.getBindingGenerator(operationBindingPerProtocol.getKey());
            JSONObject bindingJsonObject = operationBindingsGenerator.generateOperationBindingNode(operation.getType(), operationBindingPerProtocol.getValue());
            operationBindingNodes.put(operationBindingPerProtocol.getKey().name(), bindingJsonObject);
        }
        return operationBindingNodes;
    }
}
