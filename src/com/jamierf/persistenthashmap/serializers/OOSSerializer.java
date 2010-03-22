package com.jamierf.persistenthashmap.serializers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class OOSSerializer implements ObjectSerializer {

	public Object readObject(File f) throws IOException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			Object o = ois.readObject();
			ois.close();
			
			return o;
		}
		catch (ClassNotFoundException e) {
			return null;
		}
	}

	public void writeObject(File f, Serializable o) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(o);
		oos.close();
	}
}
