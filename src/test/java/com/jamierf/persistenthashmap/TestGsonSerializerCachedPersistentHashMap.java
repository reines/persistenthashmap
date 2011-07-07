package com.jamierf.persistenthashmap;

import com.jamierf.persistenthashmap.serializers.GsonSerializer;

public class TestGsonSerializerCachedPersistentHashMap extends TestCachedPersistentHashMap {

	public TestGsonSerializerCachedPersistentHashMap() {
		super(new GsonSerializer());
	}

}
