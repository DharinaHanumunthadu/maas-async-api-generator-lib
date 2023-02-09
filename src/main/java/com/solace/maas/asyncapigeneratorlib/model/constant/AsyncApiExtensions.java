package com.solace.maas.asyncapigeneratorlib.model.constant;

public enum AsyncApiExtensions {

    SCHEMA_VERSION("x-ep-schema-version"),
    SCHEMA_NAME("x-ep-schema-name"),
    SCHEMA_ID("x-ep-schema-id"),
    SCHEMA_VERSION_ID("x-ep-schema-version-id"),
    SCHEMA_VERSION_DISPLAY_NAME("x-ep-schema-version-displayname"),
    SCHEMA_STATE_ID("x-ep-schema-state-id"),
    SCHEMA_STATE_NAME("x-ep-schema-state-name"),

    ENUM_VERSION("x-ep-enum-version"),
    ENUM_VERSION_ID("x-ep-enum-version-id"),
    ENUM_ID("x-ep-enum-id"),
    ENUM_NAME("x-ep-enum-name"),
    ENUM_STATE_ID("x-ep-enum-state-id"),
    ENUM_STATE_NAME("x-ep-enum-state-name"),
    ENUM_VERSION_DISPLAY_NAME("x-ep-enum-version-displayname"),
    PARAMETER_NAME("x-ep-parameter-name"),

    EP_DISPLAY_NAME("x-ep-displayname"),
    EP_STATE_ID("x-ep-state-id"),
    EP_STATE_NAME("x-ep-state-name"),

    APPLICATION_DOMAIN_ID("x-ep-application-domain-id"),
    APPLICATION_DOMAIN_NAME("x-ep-application-domain-name"),
    APPLICATION_VERSION("x-ep-application-version"),
    APPLICATION_ID("x-ep-application-id"),
    APPLICATION_VERSION_ID("x-ep-application-version-id"),

    EVENT_API_VERSION("x-ep-event-api-version"),
    EVENT_API_ID("x-ep-event-api-id"),
    EVENT_API_VERSION_ID("x-ep-event-api-version-id"),

    EVENT_API_PRODUCT_NAME("x-ep-event-api-product-name"),
    EVENT_API_PRODUCT_ID("x-ep-event-api-product-id"),
    EVENT_API_PRODUCT_VERSION_ID("x-ep-event-api-product-version-id"),
    EVENT_API_PRODUCT_VERSION("x-ep-event-api-product-version"),

    EVENT_VERSION("x-ep-event-version"),
    EVENT_NAME("x-ep-event-name"),
    EVENT_VERSION_ID("x-ep-event-version-id"),
    EVENT_VERSION_DISPLAY_NAME("x-ep-event-version-displayname"),
    EVENT_STATE_ID("x-ep-event-state-id"),
    EVENT_ID("x-ep-event-id"),
    EVENT_STATE_NAME("x-ep-event-state-name");


    String value;

    AsyncApiExtensions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
