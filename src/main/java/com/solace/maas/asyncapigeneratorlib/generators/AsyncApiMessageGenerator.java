package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.AsyncApiMessage;
import com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiDisplayExtensionOptions;
import com.solace.maas.asyncapigeneratorlib.model.constant.SchemaContentType;
import com.solace.maas.asyncapigeneratorlib.model.constant.SchemaType;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_STATE_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_STATE_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_VERSION_DISPLAY_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_VERSION_ID;

@Slf4j
@Component
public class AsyncApiMessageGenerator extends AsyncApiFieldExtensionHelper {
    public JSONObject addMessage(AsyncApiMessage message, AsyncApiDisplayExtensionOptions includedExtensions) {
        JSONObject messageObject = new JSONObject();

        messageObject.put("description", message.getDescription());
        messageObject.put("schemaFormat", message.getSchemaFormat());

        String contentTypeString = message.getContentTypeString();
        if (contentTypeString != null) {
            messageObject.put("contentType", contentTypeString);
        }

        Optional<JSONObject> payloadObject = buildPayload(message);
        payloadObject.ifPresent(jsonObject -> messageObject.put("payload", jsonObject));

        addMessageSpecificationExtensions(messageObject, message, includedExtensions);

        return messageObject;
    }

    private void addMessageSpecificationExtensions(JSONObject messageObject, AsyncApiMessage message, AsyncApiDisplayExtensionOptions includedExtensions) {

        addToJsonObject(messageObject, EVENT_VERSION.getValue(), message.getVersion(), includedExtensions);
        addToJsonObject(messageObject, EVENT_NAME.getValue(), message.getParentName(), includedExtensions);
        addToJsonObject(messageObject, EVENT_VERSION_ID.getValue(), message.getVersionId(), includedExtensions);
        addToJsonObject(messageObject, EVENT_VERSION_DISPLAY_NAME.getValue(), message.getVersionDisplayName(), includedExtensions);
        addToJsonObject(messageObject, EVENT_STATE_ID.getValue(), message.getStateId(), includedExtensions);
        addToJsonObject(messageObject, EVENT_ID.getValue(), message.getParentId(), includedExtensions);
        addToJsonObject(messageObject, EVENT_STATE_NAME.getValue(), message.getStateName(), includedExtensions);
    }

    private Optional<JSONObject> buildPayload(AsyncApiMessage message) {
        JSONObject payloadObject = null;
        if (message.isPrimitivePayload()) {
            payloadObject = new JSONObject();
            payloadObject.put("type", message.getPayloadPrimitiveType());
        } else if (message.getLiteralContentPayload() != null) {
            if (message.getSchemaType() == SchemaType.AVRO) { // AVRO can't go in the schemas node.
                payloadObject = createJsonObjectFromContent(message.getComponentDisplayName(),
                        message.getLiteralContentPayload());
            } else {
                payloadObject = new JSONObject();
                payloadObject.put("content", message.getLiteralContentPayload());
            }
        } else if (message.getSchemaPayloadContentType() == SchemaContentType.JSON) {
            // for json schemas we support only $ref as a content of payload object, no custom objects as of now
            // the $ref field will be removed in Async 3.0
            payloadObject = new JSONObject().put("$ref", "#/components/schemas/" + message.getPayloadSchemaName());
        }
        return Optional.ofNullable(payloadObject);
    }
}
