/**
 * 
 * This file is part of the Persistent-HashMap library.
 * Copyright (C) 2010 Jamie Furness (http://www.jamierf.co.uk)
 * License: http://www.gnu.org/licenses/gpl.html GPL version 3 (or higher)
 * 
 */

package com.jamierf.persistenthashmap;

import java.io.Serializable;
import java.util.Iterator;

class ValueIterator<K extends Serializable, V extends Serializable> implements Iterator<V> {
	private PersistentHashMap<K, V> map;
	private Iterator<K> iterator;
	private K current;
	
	public ValueIterator(PersistentHashMap<K, V> map) {
		this.map = map;
		
		iterator = new KeyIterator<K, V>(map);
		current = null;
	}
	
	public boolean hasNext() {
		return iterator.hasNext();
	}

	public V next() {
		current = iterator.next();
		
		return map.get(current);
	}

	public void remove() {
		if (current != null)
			map.remove(current);
	}
}