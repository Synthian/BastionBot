package com.iancordle.bb.speedrun.model.deserial;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.iancordle.bb.speedrun.model.Embeddable;

import java.io.IOException;

public class EmbedDeserializer extends JsonDeserializer<Embeddable> implements ContextualDeserializer {

    private JavaType type;

    public EmbedDeserializer() {
    }

    private EmbedDeserializer(JavaType type) {
        this.type = type;
    }

    @Override
    public Embeddable deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Class<?> clazz = type.getRawClass();
        if (!type.isTypeOrSubTypeOf(Embeddable.class)) {
            return null;
        }

        try {
            if (node.isValueNode()) {
                Embeddable embed = (Embeddable) clazz.newInstance();
                embed.setId(node.asText());
                return embed;
            }

            if (node.has("data")) {
                node = node.get("data");
            }

            Embeddable embed = (Embeddable) node.traverse(oc).readValueAs(clazz);
            return embed;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JavaType type = deserializationContext.getContextualType() != null
                ? deserializationContext.getContextualType()
                : beanProperty.getMember().getType();
        return new EmbedDeserializer(type);
    }
}
