package com.solace.maas.asyncapigeneratorlib.model.bindings.kafka;

import com.solace.maas.asyncapigeneratorlib.model.bindings.AsyncApiOperationBinding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AsyncApiKafkaOperationBinding extends AsyncApiOperationBinding {
    private static final String CURRENT_VERSION = "0.3.0";
    private Set<String> groupIds = new HashSet<>(); // Schema type: Id of the consumer group.
    private Set<String> clientIds = new HashSet<>(); // Schema type: Id of the consumer(s) inside one or more consumer groups.

    public JSONObject getClientIdSchema() {
        return new MinimalJsonSchema(clientIds).toJSONObject();
    }

    // This is for the Kafka AsyncAPI Binding which represents values as schemas that look like this:
    /*
    "clientId": {
       "type": "string",
       "enum": ["myClientId"]
    } */
    public static class MinimalJsonSchema {
        private final JSONArray enumValuesString = new JSONArray();

        public MinimalJsonSchema(Set<String> enumValues) {
            if (enumValues != null) {
                enumValues.forEach(enumValuesString::put);
            }
        }

        public JSONObject toJSONObject() {
            return new JSONObject()
                    .put("type", "string")
                    .put("enum", enumValuesString);
        }
    }

    @Override
    public String getBindingVersion() {
        return CURRENT_VERSION;
    }
}
