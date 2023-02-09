package com.solace.maas.asyncapigeneratorlib.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AsyncApiChannelParameter {

    private String addressNodeName;
    private String displayName;
    private String enumVersion;
    private String enumVersionStateId;
    private String stateName;
    private String enumVersionId;
    private String enumVersionDisplayName;
    private String parentEnumId;
    private String parentEnumName;
    private String enumVersionName;
    private String description;
    private AsyncApiChannelParameterSchema asyncApiChannelParameterSchema;

    @Data
    @Builder
    public static class AsyncApiChannelParameterSchema {
        private String pattern;
        private List<String> enumValues;
    }
}
