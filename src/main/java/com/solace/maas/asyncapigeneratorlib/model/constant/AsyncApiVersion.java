package com.solace.maas.asyncapigeneratorlib.model.constant;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum AsyncApiVersion {

    V2_0_0("2.0.0"),
    V2_5_0("2.5.0");

    private static final Map<String, AsyncApiVersion> mapOfValueToAsyncApiVersion;
    public static final String ALLOWED_ASYNC_API_VERSIONS =
            Arrays.stream(AsyncApiVersion.values()).map(AsyncApiVersion::getValue).collect(Collectors.joining(", "));


    static {
        mapOfValueToAsyncApiVersion = new HashMap<>();
        for (AsyncApiVersion v : AsyncApiVersion.values()) {
            mapOfValueToAsyncApiVersion.put(v.getValue(), v);
        }
    }

    private final String value;

    AsyncApiVersion(String value) {
        this.value = value;
    }

    public static AsyncApiVersion findByValue(String value) {
        return StringUtils.isEmpty(value) ? null : mapOfValueToAsyncApiVersion.get(value);
    }

    @Override
    public String toString() {
        return getValue();
    }


}
