package com.jamierf.persistenthashmap.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class FileUtils {

	protected static final int BUFFER_SIZE = 4096;
	
	private FileUtils() { }
	
	public static String getGZIPContents(File f) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(f))));
		StringBuffer outBuffer = new StringBuffer();
		
		char[] inBuffer = new char[BUFFER_SIZE];
		while (in.read(inBuffer, 0, BUFFER_SIZE) != -1)
			outBuffer.append(inBuffer);
		
		in.close();
		
		return outBuffer.toString();
	}
	
	public static void putGZIPContents(File f, String contents) throws FileNotFoundException, IOException {
		PrintWriter out = new PrintWriter(new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(f))));
		out.print(contents);
		out.close();
	}
	
	public static void deleteDirectory(File dir) {
		if (!dir.isDirectory())
			return;
		
		File[] files = dir.listFiles();
		for (File f : files) {
			if (f.isDirectory())
				deleteDirectory(f);
			else
				f.delete();
		}
		
		dir.delete();
	}
}
