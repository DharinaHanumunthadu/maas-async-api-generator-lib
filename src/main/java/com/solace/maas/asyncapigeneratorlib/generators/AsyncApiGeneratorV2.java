package com.solace.maas.asyncapigeneratorlib.generators;

import com.solace.maas.asyncapigeneratorlib.model.AsyncApiChannel;
import com.solace.maas.asyncapigeneratorlib.model.AsyncApiMessage;
import com.solace.maas.asyncapigeneratorlib.model.AsyncApiSchema;
import com.solace.maas.asyncapigeneratorlib.model.AsyncApiSpec;
import com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiDisplayExtensionOptions;
import com.solace.maas.asyncapigeneratorlib.model.constant.AsyncApiVersion;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AsyncApiGeneratorV2 extends AsyncApiGeneratorBase {

    public static final String ASYNC_API_GENERATOR_V2_LOG_PREFIX = "AsyncApiGeneratorV2 - ";
    private final AsyncApiSchemaGenerator asyncApiSchemaGenerator;
    private final AsyncApiInfoGenerator asyncApiInfoGenerator;
    private final AsyncApiServerGenerator asyncApiServerGenerator;
    private final AsyncApiMessageGenerator asyncApiMessageGenerator;
    private final AsyncApiChannelGenerator asyncApiChannelGenerator;

    @Autowired
    public AsyncApiGeneratorV2(AsyncApiSchemaGenerator asyncApiSchemaGenerator,
                               AsyncApiInfoGenerator asyncApiInfoGenerator, AsyncApiServerGenerator asyncApiServerGenerator,
                               AsyncApiMessageGenerator asyncApiMessageGenerator, AsyncApiChannelGenerator asyncApiChannelGenerator) {
        super(List.of(AsyncApiVersion.V2_0_0, AsyncApiVersion.V2_5_0));
        this.asyncApiSchemaGenerator = asyncApiSchemaGenerator;
        this.asyncApiInfoGenerator = asyncApiInfoGenerator;
        this.asyncApiServerGenerator = asyncApiServerGenerator;
        this.asyncApiMessageGenerator = asyncApiMessageGenerator;
        this.asyncApiChannelGenerator = asyncApiChannelGenerator;
    }

    @Override
    public JSONObject generate(AsyncApiSpec spec, AsyncApiDisplayExtensionOptions includedExtensions) {
        JSONObject root = getBaseJsonObject();
        root.put("asyncapi", spec.getAsyncApiVersion().getValue());
        JSONObject infoNode = root.getJSONObject("info");
        asyncApiInfoGenerator.addInfo(spec.getInfo(), infoNode, includedExtensions);

        if (spec.getServers() != null) {
            root.put("servers", new JSONObject());
            asyncApiServerGenerator.addServers(spec.getServers(), root, includedExtensions);
        }

        for (AsyncApiSchema schema : spec.getSchemas()) {
            root.getJSONObject("components").getJSONObject("schemas").put(schema.getComponentDisplayName(),
                    asyncApiSchemaGenerator.addSchema(schema, includedExtensions));
        }

        for (AsyncApiMessage message : spec.getMessages()) {
            root.getJSONObject("components").getJSONObject("messages")
                    .put(message.getComponentDisplayName(), asyncApiMessageGenerator.addMessage(message, includedExtensions));
        }

        for (AsyncApiChannel channel : spec.getChannels()) {
            root.getJSONObject("channels").put(asyncApiChannelGenerator.getChannelName(channel),
                    asyncApiChannelGenerator.createChannelNode(channel, includedExtensions));
        }
        return root;
    }
}
