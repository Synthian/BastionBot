package com.iancordle.bb.speedrun;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iancordle.bb.speedrun.model.Run;
import com.iancordle.bb.speedrun.requests.RunsParams;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class SpeedrunApi {

    private final SpeedrunClient speedrunClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    SpeedrunApi(SpeedrunClient speedrunClient) {
        this.speedrunClient = speedrunClient;
    }

    public List<Run> getRuns(RunsParams runsParams) throws IOException {
        JsonNode jsonNode = formatJson(speedrunClient.getRuns(runsParams));
        ObjectReader reader = objectMapper.readerFor(new TypeReference<List<Run>>() {});
        return reader.readValue(jsonNode);
    }

    private JsonNode formatJson(String json) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(json);
        if (jsonNode.has("data"))
            jsonNode = jsonNode.get("data");
        fixNode(jsonNode);
        return jsonNode;
    }

    private void fixNode(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            ObjectNode object = (ObjectNode)jsonNode;
            if (jsonNode.has("data")) {
                JsonNode data = jsonNode.get("data").deepCopy();
                object.set("data", data);
            }
            List<String> embeds = Arrays.asList("game");
            embeds.forEach((embed) -> {
                if (jsonNode.has(embed) && jsonNode.get(embed).isValueNode()) {
                    String id = object.get(embed).asText();
                    JsonNode replacement = objectMapper.createObjectNode().put("id", id);
                    object.replace(embed, replacement);
                }
            });
        }
        if (!jsonNode.isValueNode())
            jsonNode.forEach(this::fixNode);
    }
}
