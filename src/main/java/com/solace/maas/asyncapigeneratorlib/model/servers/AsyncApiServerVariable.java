package com.solace.maas.asyncapigeneratorlib.model.servers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AsyncApiServerVariable {

    private String[] enumerations;
    private String _default;
    private String description;
    private String[] examples;

}
