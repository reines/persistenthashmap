package com.jamierf.persistenthashmap;

import com.jamierf.persistenthashmap.serializers.GsonSerializer;

public class TestGsonSerializerPersistentHashMap extends TestPersistentHashMap {

	public TestGsonSerializerPersistentHashMap() {
		super(new GsonSerializer());
	}

}
