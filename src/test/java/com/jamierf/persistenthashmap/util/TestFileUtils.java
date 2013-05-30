package com.jamierf.persistenthashmap.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class TestFileUtils {

  @Test
	public void testGetStringFromReader() throws IOException {
		String string = "Testing this.";
		assertTrue(FileUtils.getStringFromReader(new StringReader(string)).equals(string));
	}

}
