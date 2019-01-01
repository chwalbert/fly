package com.cloud.fly.content.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference mapType = new TypeReference<Map<String, Object>>() {
    };


    public static <T> T toObjectOrNull(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            logger.error("toObjectOrNull exp.", e);
            return null;
        }
    }

    public static <T> T toObject(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            logger.error("toObject exp.", e);
        }
        return null;
    }


    public static Map<String, Object> toMap(String json) {
        try {
            return objectMapper.readValue(json, mapType);
        } catch (Exception e) {
            logger.error("toMap exp.", e);
        }
        return null;
    }

    public static List toList(String json) {
        try {
            ObjectReader objectReader = objectMapper.reader().forType(new TypeReference<List<Map>>() {
            });
            List<Map> result = objectReader.readValue(json);
            return result;
        } catch (Exception e) {
            logger.error("toMap exp.", e);
        }
        return null;
    }

    public static String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            logger.error("toMap exp.", e);
        }
        return null;
    }


}
