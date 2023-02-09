package com.solace.maas.asyncapigeneratorlib.model;

import lombok.Data;

import java.util.List;

@Data
public class AsyncApiChannel {

    private String name;
    private Type type;
    private AsyncApiOperation publishOperation;
    private AsyncApiOperation subscribeOperation;
    private List<AsyncApiChannelParameter> parameters;

    public enum Type {
        topic
    }
}
