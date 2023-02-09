package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiDisplayExtensionOptions;
import com.solace.maas.base.error.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.solace.maas.asyncapigeneratorlib.generators.AsyncApiGeneratorV2.ASYNC_API_GENERATOR_V2_LOG_PREFIX;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.APPLICATION_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.APPLICATION_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.APPLICATION_VERSION_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.ENUM_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.ENUM_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.ENUM_VERSION_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_PRODUCT_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_PRODUCT_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_PRODUCT_VERSION_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_VERSION_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_VERSION_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.SCHEMA_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.SCHEMA_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.SCHEMA_VERSION_ID;


@Slf4j
@Component
public abstract class AsyncApiFieldExtensionHelper {

    private Set<String> parentIdsAndSemanticVersionNumbers;
    private Set<String> versionIdsAndSemanticVersionNumbers;
    private Set<String> allEpDbExtensions;

    public AsyncApiFieldExtensionHelper() {
        initializeEpDbExtensionKeySets();
    }

    private void initializeEpDbExtensionKeySets() {

        parentIdsAndSemanticVersionNumbers = Set.of(APPLICATION_ID.getValue(),
                APPLICATION_VERSION.getValue(), EVENT_API_ID.getValue(), EVENT_API_VERSION.getValue(),
                EVENT_ID.getValue(), EVENT_VERSION.getValue(), SCHEMA_ID.getValue(), SCHEMA_VERSION.getValue(),
                ENUM_ID.getValue(), ENUM_VERSION.getValue(), EVENT_API_PRODUCT_ID.getValue(),
                EVENT_API_PRODUCT_VERSION.getValue());

        versionIdsAndSemanticVersionNumbers = Set.of(APPLICATION_VERSION_ID.getValue(),
                APPLICATION_VERSION.getValue(), EVENT_API_VERSION_ID.getValue(), EVENT_API_VERSION.getValue(),
                EVENT_VERSION_ID.getValue(), EVENT_VERSION.getValue(), SCHEMA_VERSION_ID.getValue(),
                SCHEMA_VERSION.getValue(), ENUM_VERSION_ID.getValue(), ENUM_VERSION.getValue(),
                EVENT_API_PRODUCT_VERSION_ID.getValue(), EVENT_API_PRODUCT_VERSION.getValue());

        allEpDbExtensions = Stream
                .concat(parentIdsAndSemanticVersionNumbers.stream(),
                        versionIdsAndSemanticVersionNumbers.stream())
                .collect(Collectors.toSet());
    }

    public void addToJsonObject(JSONObject jsonObject, String key, String value,
                                AsyncApiDisplayExtensionOptions extensionDisplayOption) {

        if (!allEpDbExtensions.contains(key)) {
            jsonObject.put(key, value);
            return;
        }

        boolean addToJsonObject = false;
        switch (extensionDisplayOption) {
            case all:
                addToJsonObject = allEpDbExtensions.contains(key);
                break;
            case parent:
                addToJsonObject = parentIdsAndSemanticVersionNumbers.contains(key);
                break;
            case version:
                addToJsonObject = versionIdsAndSemanticVersionNumbers.contains(key);
                break;
            case none:
            default:
                break;
        }
        if (addToJsonObject) {
            jsonObject.put(key, value);
        }
    }

    public JSONObject createJsonObjectFromContent(String name, String schemaContent) {
        if (ObjectUtils.isEmpty(schemaContent)) {
            throw ClientException.builder()
                    .formattedMessage("The schema {} has no content.", name)
                    .build();
        }
        try {
            return new JSONObject(schemaContent);
        } catch (Exception e) {
            log.debug(ASYNC_API_GENERATOR_V2_LOG_PREFIX +
                            "Failed to convert Async Api payload content to a JSON object, content={}, " +
                            "exception={}",
                    schemaContent.replaceAll("\\s+", ""), e.getMessage());
            throw ClientException.builder()
                    .formattedMessage("The schema {} is not valid JSON.", name)
                    .build();
        }
    }
}
