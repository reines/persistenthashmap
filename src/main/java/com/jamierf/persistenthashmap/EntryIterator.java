/**
 * 
 * This file is part of the Persistent-HashMap library.
 * Copyright (C) 2010 Jamie Furness (http://www.jamierf.co.uk)
 * License: http://www.gnu.org/licenses/gpl.html GPL version 3 (or higher)
 * 
 */

package com.jamierf.persistenthashmap;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

class EntryIterator<K extends Serializable, V extends Serializable> implements Iterator<Map.Entry<K, V>> {

	private PersistentHashMap<K, V> map;
	private Iterator<K> iterator;
	private K current;

	public EntryIterator(PersistentHashMap<K, V> map) {
		this.map = map;

		iterator = new KeyIterator<K, V>(map);
		current = null;
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	public Map.Entry<K, V> next() {
		current = iterator.next();

		return new AbstractMap.SimpleEntry<K, V>(current, map.get(current));
	}

	public void remove() {
		if (current != null)
			map.remove(current);
	}
}
