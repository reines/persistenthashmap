/**
 *
 * This file is part of the Persistent-HashMap library.
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

	public CachedPersistentHashMap(File root, ObjectSerializer serializer) {
		super(root, serializer, false);

		cache = new HashMap<K, V>();

		Iterator<Map.Entry<K, V>> iterator = new EntryIterator<K, V>(this);
		while (iterator.hasNext()) {
			Map.Entry<K, V> e = iterator.next();
			cache.put(e.getKey(), e.getValue());
		}
	}

	@Override
	public synchronized boolean containsKey(Object key) {
		return cache.containsKey(key);
	}

	@Override
	public synchronized boolean containsValue(Object v) {
		return cache.containsValue(v);
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized V get(Object key) {
		if (cache.containsKey(key))
			return cache.get(key);

		V value = super.get(key);
		if (value != null)
			cache.put((K) key, value);

		return value;
	}

	@Override
	public synchronized V put(K key, V value) {
		cache.put(key, value);

		return super.put(key, value);
	}

	@Override
	public synchronized V remove(Object key) {
		cache.remove(key);

		return super.remove(key);
	}

	@Override
	public boolean isEmpty() {
		return cache.isEmpty();
	}

	@Override
	public int size() {
		return cache.size();
	}

	@Override
	public synchronized void clear() {
		cache.clear();
		super.clear();
	}
}
