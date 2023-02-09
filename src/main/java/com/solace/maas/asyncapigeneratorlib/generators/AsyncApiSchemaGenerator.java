package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.AsyncApiSchema;
import com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiDisplayExtensionOptions;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.SCHEMA_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.SCHEMA_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.SCHEMA_STATE_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.SCHEMA_STATE_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.SCHEMA_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.SCHEMA_VERSION_DISPLAY_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.SCHEMA_VERSION_ID;

@Slf4j
@Component
public class AsyncApiSchemaGenerator extends AsyncApiFieldExtensionHelper {

    public JSONObject addSchema(AsyncApiSchema schema, AsyncApiDisplayExtensionOptions includedExtensions) {
        JSONObject schemaObject = createJsonObjectFromContent(schema.getComponentDisplayName(), schema.getContent());
        addSchemaSpecificationExtensions(schemaObject, schema, includedExtensions);
        return schemaObject;
    }

    private void addSchemaSpecificationExtensions(JSONObject schemaObject, AsyncApiSchema schema, AsyncApiDisplayExtensionOptions includedExtensions) {

        addToJsonObject(schemaObject, SCHEMA_VERSION.getValue(), schema.getVersion(), includedExtensions);
        addToJsonObject(schemaObject, SCHEMA_NAME.getValue(), schema.getParentName(), includedExtensions);
        addToJsonObject(schemaObject, SCHEMA_VERSION_ID.getValue(), schema.getVersionId(), includedExtensions);
        addToJsonObject(schemaObject, SCHEMA_VERSION_DISPLAY_NAME.getValue(), schema.getVersionDisplayName(), includedExtensions);
        addToJsonObject(schemaObject, SCHEMA_STATE_ID.getValue(), schema.getStateId(), includedExtensions);
        addToJsonObject(schemaObject, SCHEMA_ID.getValue(), schema.getParentId(), includedExtensions);
        addToJsonObject(schemaObject, SCHEMA_STATE_NAME.getValue(), schema.getStateName(), includedExtensions);
    }
}
