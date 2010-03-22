package com.jamierf.persistenthashmap;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

class ValueSet<K extends Serializable, V extends Serializable> extends AbstractSet<V> {

	private PersistentHashMap<K, V> map;
	
	public ValueSet(PersistentHashMap<K, V> map) {
		this.map = map;
	}
	
	public boolean contains(Object o) {
		return map.containsValue(o);
	}

	@SuppressWarnings("unchecked")
	public boolean containsAll(Collection<?> c) {
		Collection<V> ec = (Collection<V>) c;
		for (V value : ec)
			if (!contains(value))
				return false;
		
		return true;
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Iterator<V> iterator() {
		return new ValueIterator<K, V>(map);
	}

	public int size() {
		return map.size();
	}
}