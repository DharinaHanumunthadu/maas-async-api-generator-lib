package com.solace.maas.asyncapigeneratorlib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AsyncApiInfo extends AsyncApiComponent {

    private String title;
    private String description;
    private String asyncApiVersion;
    private String applicationDomainId;
    private String applicationDomainName;
    private String applicationVersion;
    private String applicationId;
    private String applicationVersionId;
    private String eventApiVersion;
    private String eventApiId;
    private String eventApiVersionId;
    private String eventApiProductId;
    private String eventApiProductVersionId;
    private String eventApiProductName;
    private String eventApiProductVersion;
}
