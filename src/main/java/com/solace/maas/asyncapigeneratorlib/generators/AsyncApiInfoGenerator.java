package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.AsyncApiInfo;
import com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiDisplayExtensionOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.APPLICATION_DOMAIN_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.APPLICATION_DOMAIN_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.APPLICATION_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.APPLICATION_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.APPLICATION_VERSION_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EP_DISPLAY_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EP_STATE_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EP_STATE_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_PRODUCT_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_PRODUCT_NAME;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_PRODUCT_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_PRODUCT_VERSION_ID;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_VERSION;
import static com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiExtensions.EVENT_API_VERSION_ID;

@Slf4j
@Component
public class AsyncApiInfoGenerator extends AsyncApiFieldExtensionHelper {

    public void addInfo(AsyncApiInfo info, JSONObject infoNode, AsyncApiDisplayExtensionOptions includedExtensions) {
        if (StringUtils.isNotEmpty(info.getTitle())) {
            addToJsonObject(infoNode, "title", info.getTitle(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getDescription())) {
            addToJsonObject(infoNode, "description", info.getDescription(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getVersion())) {
            addToJsonObject(infoNode, "version", info.getVersion(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getVersionDisplayName())) {
            addToJsonObject(infoNode, EP_DISPLAY_NAME.getValue(), info.getVersionDisplayName(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getStateId())) {
            addToJsonObject(infoNode, EP_STATE_ID.getValue(), info.getStateId(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getStateName())) {
            addToJsonObject(infoNode, EP_STATE_NAME.getValue(), info.getStateName(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getApplicationDomainId())) {
            addToJsonObject(infoNode, APPLICATION_DOMAIN_ID.getValue(), info.getApplicationDomainId(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getApplicationDomainName())) {
            addToJsonObject(infoNode, APPLICATION_DOMAIN_NAME.getValue(), info.getApplicationDomainName(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getApplicationVersion())) {
            addToJsonObject(infoNode, APPLICATION_VERSION.getValue(), info.getApplicationVersion(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getApplicationId())) {
            addToJsonObject(infoNode, APPLICATION_ID.getValue(), info.getApplicationId(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getApplicationVersionId())) {
            addToJsonObject(infoNode, APPLICATION_VERSION_ID.getValue(), info.getApplicationVersionId(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getEventApiVersion())) {
            addToJsonObject(infoNode, EVENT_API_VERSION.getValue(), info.getEventApiVersion(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getEventApiId())) {
            addToJsonObject(infoNode, EVENT_API_ID.getValue(), info.getEventApiId(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getEventApiVersionId())) {
            addToJsonObject(infoNode, EVENT_API_VERSION_ID.getValue(), info.getEventApiVersionId(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getEventApiProductName())) {
            addToJsonObject(infoNode, EVENT_API_PRODUCT_NAME.getValue(), info.getEventApiProductName(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getEventApiProductId())) {
            addToJsonObject(infoNode, EVENT_API_PRODUCT_ID.getValue(), info.getEventApiProductId(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getEventApiProductVersionId())) {
            addToJsonObject(infoNode, EVENT_API_PRODUCT_VERSION_ID.getValue(), info.getEventApiProductVersionId(), includedExtensions);
        }
        if (StringUtils.isNotEmpty(info.getEventApiProductVersion())) {
            addToJsonObject(infoNode, EVENT_API_PRODUCT_VERSION.getValue(), info.getEventApiProductVersion(), includedExtensions);
        }
    }
}
