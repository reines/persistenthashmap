package com.jamierf.persistenthashmap;

import com.jamierf.persistenthashmap.serializers.OOSSerializer;

public class TestOOSSerializerPersistentHashMap extends TestPersistentHashMap {

	public TestOOSSerializerPersistentHashMap() {
		super(new OOSSerializer());
	}
	
}
