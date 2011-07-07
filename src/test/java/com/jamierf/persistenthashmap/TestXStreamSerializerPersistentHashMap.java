package com.jamierf.persistenthashmap;

import com.jamierf.persistenthashmap.serializers.XStreamSerializer;

public class TestXStreamSerializerPersistentHashMap extends TestPersistentHashMap {

	public TestXStreamSerializerPersistentHashMap() {
		super(new XStreamSerializer());
	}

}
