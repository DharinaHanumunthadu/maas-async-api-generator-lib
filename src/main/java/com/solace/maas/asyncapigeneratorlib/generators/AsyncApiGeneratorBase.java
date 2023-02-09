package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.AsyncApiSpec;
import com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiDisplayExtensionOptions;
import com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiVersion;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.List;

@Slf4j
@Data
public abstract class AsyncApiGeneratorBase {

    private List<AsyncApiVersion> acceptedVersions;

    AsyncApiGeneratorBase(List<AsyncApiVersion> acceptedVersions) {
        this.acceptedVersions = acceptedVersions;
    }

    public abstract JSONObject generate(AsyncApiSpec spec, AsyncApiDisplayExtensionOptions includedExtensions);

    protected JSONObject getBaseJsonObject() {
        JSONObject root = new JSONObject();

        JSONObject infoNode = new JSONObject();
        infoNode.put("title", "Solace Generated AsyncAPI Specification");
        JSONObject componentsNode = new JSONObject();
        componentsNode.put("messages", new JSONObject());
        componentsNode.put("schemas", new JSONObject());

        root.put("info", infoNode);
        root.put("components", componentsNode);
        root.put("channels", new JSONObject());

        return root;
    }
}
