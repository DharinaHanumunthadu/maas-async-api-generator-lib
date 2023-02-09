package com.solace.maas.asyncapigeneratorlib.model;

import com.google.common.collect.ImmutableMap;
import com.solace.maas.asyncapigeneratorlib.model.constant.SchemaContentType;
import com.solace.maas.asyncapigeneratorlib.model.constant.SchemaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

import static com.solace.maas.asyncapigeneratorlib.model.constant.SchemaContentType.BINARY;
import static com.solace.maas.asyncapigeneratorlib.model.constant.SchemaContentType.JSON;
import static com.solace.maas.asyncapigeneratorlib.model.constant.SchemaContentType.TEXT;
import static com.solace.maas.asyncapigeneratorlib.model.constant.SchemaContentType.XML;
import static com.solace.maas.asyncapigeneratorlib.model.constant.SchemaType.AVRO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AsyncApiMessage extends AsyncApiComponent {

    public static final Map<SchemaContentType, String> MEDIA_TYPE_LOOKUP_MAP = ImmutableMap.<SchemaContentType, String>builder()
            .put(BINARY, "application/octet-stream")
            .put(JSON, "application/json")
            .put(XML, "application/xml")
            .put(TEXT, "text/plain")
            .build();

    private String description;
    private SchemaContentType schemaPayloadContentType;
    private SchemaType schemaType;
    private String payloadPrimitiveType;
    private boolean primitivePayload;
    private String payloadSchemaName;
    private String literalContentPayload; // for AVRO schema content, which can't go in the schemas section.

    public String getSchemaFormat() {
        if (schemaPayloadContentType == null) {
            return null;
        }
        if (AVRO == schemaType && schemaPayloadContentType == JSON) {
            return "application/vnd.apache.avro+json;version=1.9.0";
        } else if (primitivePayload || schemaPayloadContentType == JSON) {
            return "application/vnd.aai.asyncapi+json;version=2.0.0";
        } else {
            return "application/vnd.aai.asyncapi;version=2.0.0";
        }
    }

    public String getContentTypeString() {
        if (schemaPayloadContentType == null) {
            return null;
        }
        if (AVRO == schemaType && JSON == schemaPayloadContentType) {
            return "application/vnd.apache.avro+json";
        } else {
            String contentType = MEDIA_TYPE_LOOKUP_MAP.get(schemaPayloadContentType);
            if (contentType == null) {
                // this is a 500 case, means we forgot to map a new schema content type to a media type
                throw new IllegalStateException(String.format("Unable to map schema content type [%s] to media type", schemaPayloadContentType));
            }
            return contentType;
        }
    }
}
