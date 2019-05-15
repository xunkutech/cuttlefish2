package com.example.demo.dao.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtils {

  private static final ObjectMapper JSON =
      new ObjectMapper()
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
          .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true)
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
          .registerModule(new JavaTimeModule());

  public static <T> List<T> jsonToList(String jsonArray) throws JsonException {
    try {
      return JSON.readValue(jsonArray, new TypeReference<List<T>>() {});
    } catch (Throwable e) {
      log.error("jsonToList error", e);
      throw new JsonException(e);
    }
  }

  public static <K, V> Map<K, V> jsonToMap(String json) throws JsonException {
    try {
      return JSON.readValue(json, new TypeReference<Map<K, V>>() {});
    } catch (Throwable e) {
      log.error("jsonToMap error", e);
      throw new JsonException(e);
    }
  }

  public static String toJson(Object o) {
    try {
      return JSON.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      log.error("json string write error", e);
      return null;
    }
  }

  public static <T> T jsonToObject(String json, Class<T> clazz) throws JsonException {
    try {
      return JSON.readValue(json, clazz);
    } catch (Throwable e) {
      log.error("jsonToObject error", e);
      throw new JsonException(e);
    }
  }

  public static <T> T jsonToObject(InputStream json, Class<T> clazz) throws JsonException {
    try {
      return JSON.readValue(json, clazz);
    } catch (Throwable e) {
      log.error("jsonToObject error", e);
      throw new JsonException(e);
    }
  }

  public static <T> T jsonToObject(String json, Type type) throws JsonException {
    try {
      JavaType javaType = JSON.getTypeFactory().constructType(type);
      return JSON.readValue(json, javaType);
    } catch (Throwable e) {
      log.error("jsonStringToObject error", e);
      throw new JsonException(e);
    }
  }

  public static <T> T jsonToObject(InputStream json, Type type) throws JsonException {
    try {
      JavaType javaType = JSON.getTypeFactory().constructType(type);
      return JSON.readValue(json, javaType);
    } catch (Throwable e) {
      log.error("jsonInputStreamToObject error", e);
      throw new JsonException(e);
    }
  }

  //    public static void main(String[] args) throws Exception {
  //        Map<String, Object> testmap = new HashMap<>();
  //        testmap.put("a", 2334);
  //        testmap.put("b", "sdfa");
  //
  //        String testmapstr = toJson(testmap);
  //        System.out.println(testmapstr);
  //        T t = new T();
  //        Type superclass = t.getClass().getGenericSuperclass();
  //        ParameterizedType parameterizedType = (ParameterizedType) superclass;
  //        Map<String, Object> testmap2 = jsonToObject(testmapstr,
  // parameterizedType.getActualTypeArguments()[0]);
  //
  //        String testmapstr2 = toJson(testmap2);
  //        System.out.println(testmapstr2);
  //    }
  //
  //    static class T extends ArrayList<Map<String, Object>> {
  //
  //    }
}
