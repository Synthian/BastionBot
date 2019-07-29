package com.iancordle.bb.speedrun;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iancordle.bb.speedrun.model.Leaderboard;
import com.iancordle.bb.speedrun.model.Run;
import com.iancordle.bb.speedrun.requests.LeaderboardParams;
import com.iancordle.bb.speedrun.requests.RunsParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class SpeedrunApi {
    private final static Logger LOG = LoggerFactory.getLogger(SpeedrunApi.class);
    private final static List<String> embeds = Arrays.asList("game", "category", "level");

    private final SpeedrunClient speedrunClient;
    private final ObjectMapper objectMapper;

    SpeedrunApi(SpeedrunClient speedrunClient, ObjectMapper objectMapper) {
        this.speedrunClient = speedrunClient;
        this.objectMapper = objectMapper;
    }

    public List<Run> getRuns(RunsParams runsParams) {
        try {
            JsonNode jsonNode = formatJson(speedrunClient.getRuns(runsParams));
            ObjectReader reader = objectMapper.readerFor(new TypeReference<List<Run>>() {});
            return reader.readValue(jsonNode);
        } catch (IOException ex) {
            LOG.error("Could not deserialize API runs response.", ex);
            return Collections.emptyList();
        }
    }

    public Leaderboard getLeaderboard(String game, String category, String level, Map<String, String> variables, @NonNull LeaderboardParams leaderboardParams) {
        Map<String, String> varMap = formatVariableMap(variables);
        String json;
        if (level != null) {
            json = speedrunClient.getLevelLeaderboard(game, level, category, varMap, leaderboardParams);
        } else {
            json = speedrunClient.getLeaderboard(game, category, varMap, leaderboardParams);
        }
        try {
            JsonNode jsonNode = formatJson(json);
            ObjectReader reader = objectMapper.readerFor(new TypeReference<Leaderboard>() {});
            return reader.readValue(jsonNode);
        } catch (IOException ex) {
            LOG.error("Could not deserialize API leaderboard response.", ex);
            return null;
        }
    }

    private Map<String, String> formatVariableMap(Map<String, String> variables) {
        Map<String, String> newVars = new HashMap<>();
        if (variables != null) {
            for (String key : variables.keySet()) {
                newVars.put("var-" + key, variables.get(key));
            }
        }
        return newVars;
    }

    private JsonNode formatJson(String json) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(json);
        jsonNode = normalizeNode(jsonNode);
        correctApiErrors(jsonNode);
        return jsonNode;
    }

    /**
     * Recursively modifies a JsonNode, removing any wrapper nodes,
     * and making any unembedded nodes which are embeddable 
     * be properly represented as an object rather than a value
     * node containing the ID of the node.
     * 
     * @param jsonNode parent node to be normalized
     */
    private JsonNode normalizeNode(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            if (jsonNode.has("data")) {
                jsonNode = jsonNode.get("data").deepCopy();
            }
        }
        if (jsonNode.isObject()) {
            for (String embed : embeds) {
                if (jsonNode.has(embed) && jsonNode.get(embed).isValueNode()) {
                    ObjectNode object = (ObjectNode) jsonNode;
                    String id = object.get(embed).asText();
                    JsonNode replacement = objectMapper.createObjectNode().put("id", id);
                    object.replace(embed, replacement);
                }
            }
            ObjectNode objectNode = (ObjectNode)jsonNode;
            for (Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields(); iter.hasNext(); ) {
                Map.Entry<String, JsonNode> entry = iter.next();
                objectNode.set(entry.getKey(), normalizeNode(entry.getValue()));
            }
        } else if (jsonNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode)jsonNode;
            for (int i = 0; i < arrayNode.size(); i++) {
                arrayNode.set(i, normalizeNode(arrayNode.get(i)));
            }
        }
        return jsonNode;
    }

    /**
     * Returns a modified JsonNode that fixes errors in the Speedrun.com API.
     *
     * @param jsonNode parent node to be corrected
     */
    private void correctApiErrors(JsonNode jsonNode) {
        for (JsonNode parent : jsonNode.findParents("level")) {
            if (parent.isObject() && parent.get("level").isArray()) {
                ((ObjectNode)parent).set("level", NullNode.getInstance());
            }
        }
    }
}
