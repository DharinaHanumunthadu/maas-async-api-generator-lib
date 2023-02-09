package com.solace.maas.asyncapigeneratorlib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AsyncApiComponent {

    protected String componentDisplayName;
    protected String parentName;
    protected String version;
    protected String stateId;
    protected String parentId;
    protected String versionDisplayName;
    protected String versionId;
    protected String stateName;
}
