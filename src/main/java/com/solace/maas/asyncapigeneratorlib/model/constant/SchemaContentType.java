package com.solace.maas.asyncapigeneratorlib.model.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum SchemaContentType {
    JSON("json", true),
    XML("xml"),
    BINARY("binary", true),
    TEXT("Text"); //currently only used for asyncapi schema primitive types

    private final String label;
    private boolean asyncApiSupported;
    private static final Map<String, SchemaContentType> BY_LABEL = new HashMap<>();

    static {
        for (SchemaContentType schemaContentType : SchemaContentType.values()) {
            BY_LABEL.put(schemaContentType.label, schemaContentType);
        }
    }

    public static final String ASYNC_API_SUPPORTED_SCHEMA_CONTENT_TYPES
            = Arrays.stream(SchemaContentType.values()).filter(SchemaContentType::isAsyncApiSupported)
            .map(SchemaContentType::getLabel).collect(Collectors.joining(", "));

    SchemaContentType(String label) {
        this.label = label;
    }

    SchemaContentType(String label, boolean asyncApiSupported) {
        this.label = label;
        this.asyncApiSupported = asyncApiSupported;
    }

    public static SchemaContentType getByLabel(String schemaContentTypeLabel) {
        return BY_LABEL.get(schemaContentTypeLabel);
    }
}
