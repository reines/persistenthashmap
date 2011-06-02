/**
 * 
 * This file is part of the Persistent-HashMap library.
 * Copyright (C) 2010 Jamie Furness (http://www.jamierf.co.uk)
 * License: http://www.gnu.org/licenses/gpl.html GPL version 3 (or higher)
 * 
 */

package com.jamierf.persistenthashmap;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

class KeyIterator<K extends Serializable, V extends Serializable> implements Iterator<K> {

	private PersistentHashMap<K, V> map;
	private Iterator<File> iterator;
	private K current;

	public KeyIterator(PersistentHashMap<K, V> map) {
		this.map = map;

		iterator = map.new FileIterator(map.keyStore);
		current = null;
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	@SuppressWarnings("unchecked")
	public K next() {
		try {
			File keyFile = iterator.next();
			if (keyFile == null)
				return null;

			return (K) map.serializer.readObject(keyFile);
		}
		catch (IOException ioe) {
			return null;
		}
	}

	public void remove() {
		if (current == null)
			return;

		map.remove(current);
	}
}
