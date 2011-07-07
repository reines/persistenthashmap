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

import com.jamierf.persistenthashmap.util.FileUtils;
import com.thoughtworks.xstream.XStream;

public class XStreamSerializer implements ObjectSerializer {

	XStream xstream;

	public XStreamSerializer() {
		xstream = new XStream();
	}

	public Object readObject(File f) throws IOException {
		String data = FileUtils.getGZIPContents(f);
		return xstream.fromXML(data);
	}

	public void writeObject(File f, Serializable o, boolean force) throws IOException {
		String data = xstream.toXML(o);
		FileUtils.putGZIPContents(f, data, force);
	}
}
