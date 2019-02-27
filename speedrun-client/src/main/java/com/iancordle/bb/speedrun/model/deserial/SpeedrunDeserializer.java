package com.iancordle.bb.speedrun.model.deserial;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.iancordle.bb.speedrun.model.Embeddable;

import java.io.IOException;

public class SpeedrunDeserializer {
//
//    private BeanDescription beanDesc;
//    private JsonDeserializer<?> defaultDeserializer;
//
//    public SpeedrunDeserializer() {
//    }
//
//    public SpeedrunDeserializer(JsonDeserializer<?> defaultDeserializer, BeanDescription type) {
//        this.defaultDeserializer = defaultDeserializer;
//        this.beanDesc = beanDesc;
//    }
//
//    @Override
//    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        ObjectCodec oc = jsonParser.getCodec();
//        JsonNode node = oc.readTree(jsonParser);
//
//
//        if (node.has("data")) {
//            node = node.get("data");
//        }
//
//        try {
//            if (node.isValueNode()) {
//                Embeddable embed = (Embeddable) clazz.newInstance();
//                embed.setId(node.asText());
//                return embed;
//            }
//
//            if (node.has("data")) {
//                node = node.get("data");
//            }
//
//            Embeddable embed = (Embeddable) node.traverse(oc).readValueAs(clazz);
//            return embed;
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//            return null;
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
