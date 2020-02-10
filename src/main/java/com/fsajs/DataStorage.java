package com.fsajs;

import com.arakelian.jackson.utils.JacksonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toSet;

class DataStorage {

    static Map<String, Object> data = new ConcurrentHashMap<>();

    private static final ObjectMapper objectMapper = JacksonUtils.getObjectMapper();

    @SuppressWarnings("unchecked")
    static Map<String, Object> search(Map<String, Object> requestParams) {
        Map<String, Object> resultMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : DataStorage.data.entrySet()) {
            Object value = DataStorage.data.get(entry.getKey());
            try {
                Map mapOfValue = objectMapper.readValue(objectMapper.writeValueAsString(value), Map.class);
                if (mapOfValue != null) {
                    Set valuesSet = (Set) mapOfValue.entrySet().stream().map(String::valueOf).collect(toSet());
                    Set requestParamsSet = requestParams.entrySet().stream().map(String::valueOf).collect(toSet());
                    if (valuesSet.containsAll(requestParamsSet)) {
                        resultMap.put(entry.getKey(), entry.getValue());
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return resultMap;
    }
}
