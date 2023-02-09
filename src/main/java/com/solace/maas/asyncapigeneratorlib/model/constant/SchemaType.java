package com.solace.maas.asyncapigeneratorlib.model.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
public enum SchemaType {
    JSON_SCHEMA("jsonSchema",
            Collections.unmodifiableList(Arrays.asList(SchemaContentType.JSON.getLabel()))),

    XSD("xsd",
            Collections.unmodifiableList(Arrays.asList(SchemaContentType.XML.getLabel()))),

    DTD("dtd",
            Collections.unmodifiableList(Arrays.asList(SchemaContentType.XML.getLabel()))),

    AVRO("avro",
            Collections.unmodifiableList(Arrays.asList(
                    SchemaContentType.JSON.getLabel(),
                    SchemaContentType.BINARY.getLabel()
            )));

    private final String label;
    private final List<String> associatedContentTypes;
    private static final Map<String, SchemaType> BY_LABEL = new HashMap<>();

    static {
        for (SchemaType schemaType : SchemaType.values()) {
            BY_LABEL.put(schemaType.label, schemaType);
        }
    }

    SchemaType(String label, List<String> associatedContentTypes) {
        this.label = label;
        this.associatedContentTypes = associatedContentTypes;
    }

    public static boolean isValidSchemaType(String schemaTypeLabel) {
        return BY_LABEL.get(schemaTypeLabel) != null;
    }

    public static SchemaType getByLabel(String schemaTypeLabel) {
        return BY_LABEL.get(schemaTypeLabel);
    }

    public static Set<String> getLabels() {
        return BY_LABEL.keySet();
    }
}
