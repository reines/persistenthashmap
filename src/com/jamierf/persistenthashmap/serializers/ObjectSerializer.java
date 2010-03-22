package com.jamierf.persistenthashmap.serializers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public interface ObjectSerializer {
	public void writeObject(File f, Serializable o) throws IOException;
	public Object readObject(File f) throws IOException;
}
