package com.jamierf.persistenthashmap;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

class KeySet<K extends Serializable, V extends Serializable> extends AbstractSet<K> {

	private PersistentHashMap<K, V> map;
	
	public KeySet(PersistentHashMap<K, V> map) {
		this.map = map;
	}
	
	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	@SuppressWarnings("unchecked")
	public boolean containsAll(Collection<?> c) {
		Collection<K> ec = (Collection<K>) c;
		for (K key : ec)
			if (!contains(key))
				return false;
		
		return true;
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Iterator<K> iterator() {
		return new KeyIterator<K, V>(map);
	}

	public int size() {
		return map.size();
	}
}