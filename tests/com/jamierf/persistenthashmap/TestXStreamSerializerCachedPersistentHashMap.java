package com.jamierf.persistenthashmap;

import com.jamierf.persistenthashmap.serializers.XStreamSerializer;

public class TestXStreamSerializerCachedPersistentHashMap extends TestCachedPersistentHashMap {
	
	public TestXStreamSerializerCachedPersistentHashMap() {
		super(new XStreamSerializer());
	}
	
}
