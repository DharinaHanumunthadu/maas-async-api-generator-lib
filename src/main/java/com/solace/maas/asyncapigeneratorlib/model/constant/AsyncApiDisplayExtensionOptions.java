package com.solace.maas.asyncapigeneratorlib.model.constant;

public enum AsyncApiDisplayExtensionOptions {

    all("all"), parent("parent"), version("version"), none("none");

    private String value;

    AsyncApiDisplayExtensionOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
