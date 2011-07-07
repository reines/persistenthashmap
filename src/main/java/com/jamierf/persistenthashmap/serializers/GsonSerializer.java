/**
 *
 * This file is part of the Persistent-HashMap library.
 * Copyright (C) 2010 George Haney (http://www.georgemh.com)
 * License: http://www.gnu.org/licenses/gpl.html GPL version 3 (or higher)
 *
 */

package com.jamierf.persistenthashmap.serializers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jamierf.persistenthashmap.util.FileUtils;

public class GsonSerializer implements ObjectSerializer {

	protected Gson gson;

	public GsonSerializer() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(ObjectContainer.class, new GenericTypeAdapter());
		builder.setDateFormat("yyMMddHHmmssSSSZ");
		gson = builder.create();
	}

	public Object readObject(File f) throws IOException {
		String data = FileUtils.getGZIPContents(f);
		return gson.fromJson(data, ObjectContainer.class).getValue();
	}

	public void writeObject(File f, Serializable o, boolean force) throws IOException {
		String data = gson.toJson(new ObjectContainer(o));
		FileUtils.putGZIPContents(f, data, force);
	}

	protected class ObjectContainer {
		protected String valueType;
		protected Object value;

		public ObjectContainer(Object obj) {
			valueType = obj.getClass().getName();
			value = obj;
		}

		public String getValueType() {
			return valueType;
		}

		public Object getValue() {
			return value;
		}
	}

	protected class GenericTypeAdapter implements JsonDeserializer<ObjectContainer>, JsonSerializer<ObjectContainer> {
		public JsonElement serialize(ObjectContainer value, Type arg1, JsonSerializationContext context) {
			JsonObject json = new JsonObject();

			json.addProperty("valueType", value.getValueType());
			json.add("value", context.serialize(value.getValue()));

			return json;
		}

		public ObjectContainer deserialize(JsonElement jsonElem, Type type, JsonDeserializationContext context) throws JsonParseException {
			if (!jsonElem.isJsonObject()) {
				throw new JsonParseException("Did not receive a JsonObject!");
			}

			JsonObject json = jsonElem.getAsJsonObject();
			String valueType = json.get("valueType").getAsString();

			Class<?> clazz;
			try {
				clazz = Class.forName(valueType);
			}
			catch (ClassNotFoundException e) {
				throw new JsonParseException("Unable to find Type: " + valueType);
			}

			return new ObjectContainer(context.deserialize(json.get("value"), clazz));
		}
	}
}
