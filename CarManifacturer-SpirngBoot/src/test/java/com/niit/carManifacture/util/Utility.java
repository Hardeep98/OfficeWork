package com.niit.carManifacture.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Utility {

	public static JsonObject convertStringIntoJsonObject(String contentAsString) {
		if (contentAsString != null && !contentAsString.isEmpty()) {
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = jsonParser.parse(contentAsString).getAsJsonObject();
			return jsonObject;
		}
		return null;
	}

	public static JsonArray convertStringIntoJsonArray(String contentAsString) {
		if (contentAsString != null && !contentAsString.isEmpty()) {
			JsonParser jsonParser = new JsonParser();
			JsonArray jsonArray = jsonParser.parse(contentAsString).getAsJsonArray();
			return jsonArray;
		}
		return null;
	}

	public static String objectToJsonConverter(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return objectMapper.writeValueAsString(obj);
	}

}
