package com.jamierf.persistenthashmap;

import com.jamierf.persistenthashmap.serializers.OOSSerializer;

public class TestOOSSerializerCachedPersistentHashMap extends TestCachedPersistentHashMap {

	public TestOOSSerializerCachedPersistentHashMap() {
		super(new OOSSerializer());
	}

}
