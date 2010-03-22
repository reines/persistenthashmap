/**
 * 
 * This file is part of the PersistentHashMap library.
 * Copyright (C) 2010 Jamie Furness (http://www.jamierf.co.uk)
 * License: http://www.gnu.org/licenses/gpl.html GPL version 3 (or higher)
 * 
 */

package com.jamierf.persistenthashmap;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.jamierf.persistenthashmap.serializers.OOSSerializer;
import com.jamierf.persistenthashmap.serializers.ObjectSerializer;

public class CachedPersistentHashMap<K extends Serializable, V extends Serializable> extends PersistentHashMap<K, V> {

	protected HashMap<K, V> cache;
	
	public CachedPersistentHashMap(File root) {
		this (root, new OOSSerializer());
	}
	
	@SuppressWarnings("unchecked")
	public CachedPersistentHashMap(File root, ObjectSerializer serializer) {
		super(root, serializer);
		
		cache = new HashMap<K, V>();

		Iterator<Map.Entry<K, V>> iterator = new EntryIterator(this);
		while (iterator.hasNext()) {
			Map.Entry<K, V> e = iterator.next();
			cache.put(e.getKey(), e.getValue());
		}
	}
	
	public synchronized boolean containsKey(Object key) {
		return cache.containsKey(key);
	}
	
	public synchronized boolean containsValue(Object v) {
		return cache.containsValue(v);
	}
	
	@SuppressWarnings("unchecked")
	public synchronized V get(Object key) {
		if (cache.containsKey(key))
			return cache.get(key);
		
		V value = super.get(key);
		if (value != null)
			cache.put((K) key, value);
		
		return value;
	}
	
	public synchronized V put(K key, V value) {
		cache.put(key, value);
		
		return super.put(key, value);
	}
	
	public synchronized V remove(Object key) {
		cache.remove(key);
		
		return super.remove(key);
	}
	
	public boolean isEmpty() {
		return cache.isEmpty();
	}

	public int size() {
		return cache.size();
	}
	
	public synchronized void clear() {
		cache.clear();
		super.clear();
	}
	
}
