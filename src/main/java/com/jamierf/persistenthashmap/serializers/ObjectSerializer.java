/**
 *
 * This file is part of the Persistent-HashMap library.
 * Copyright (C) 2010 Jamie Furness (http://www.jamierf.co.uk)
 * License: http://www.gnu.org/licenses/gpl.html GPL version 3 (or higher)
 *
 */

package com.jamierf.persistenthashmap.serializers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public interface ObjectSerializer {
	public void writeObject(File f, Serializable o, boolean force) throws IOException;
	public Object readObject(File f) throws IOException;
}
