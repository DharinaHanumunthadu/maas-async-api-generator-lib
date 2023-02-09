package com.solace.maas.asyncapigeneratorlib.model.constant;

import lombok.Getter;
import org.springframework.http.MediaType;

@Getter
public enum AsyncApiFormat {

    json(MediaType.APPLICATION_JSON),
    yaml(MediaType.TEXT_PLAIN);

    private final MediaType mediaType;

    AsyncApiFormat(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}
