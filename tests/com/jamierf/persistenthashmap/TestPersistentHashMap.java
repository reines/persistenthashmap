package com.jamierf.persistenthashmap;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import com.jamierf.persistenthashmap.PersistentHashMap;
import com.jamierf.persistenthashmap.serializers.ObjectSerializer;
import com.jamierf.persistenthashmap.util.FileUtils;

public abstract class TestPersistentHashMap {
	
	private File root;
	private ObjectSerializer serializer;
	private PersistentHashMap<String, Date> map;
	
	public TestPersistentHashMap(ObjectSerializer serializer) {
		this.serializer = serializer;
		root = new File("data");
	}
	
	@Before
	public void setUp() {
		FileUtils.deleteDirectory(root);
		map = new PersistentHashMap<String, Date>(root, serializer);
	}
	
	@After
	public void tearDown() {
		FileUtils.deleteDirectory(root);
	}
	
	@Test
	public void testContainsKey() {
		// The map shouldn't contain the key "key" to start with
		assertFalse(map.containsKey("key"));
		
		// Insert the entry
		map.put("key", new Date());
		
		// The map should now contain the key "key"
		assertTrue(map.containsKey("key"));
		// But shouldn't contain things we didn't add
		assertFalse(map.containsKey("fail"));
	}
	
	@Test
	public void testContainsValue() {
		// The map shouldn't contain the value "value" to start with
		assertFalse(map.containsValue("value"));
		
		// Create an object to store
		Date value = new Date();
		
		// Insert the entry
		map.put("key", value);
		
		// The map should now contain the value "value"
		assertTrue(map.containsValue(value));
		// But shouldn't contain things we didn't add
		assertFalse(map.containsValue("fail"));
	}
	
	@Test
	public void testGet() {
		Date value = new Date();
		
		// Insert an entry
		map.put("key", value);
		
		// Check the map returns exactly what we put in
		assertEquals(value, map.get("key"));
	}
	
	@Test
	public void testPut() {
		Date value = new Date();
		
		// The map shouldn't contain the key "key" to start with
		assertFalse(map.containsKey("key"));
		assertFalse(map.containsValue(value));
		
		// Insert the entry
		map.put("key", value);
		
		// The map should now contain the key "key"
		assertTrue(map.containsKey("key"));
		assertTrue(map.containsValue(value));
	}
	
	@Test
	public void testPutAll() {
		Date[] dates = new Date[5];
		for (int i = 0;i < 5;i++)
			dates[i] = new Date();
		
		Map<String, Date> testMap = new HashMap<String, Date>();
		
		// Insert 5 test entries into the test map and confirm they don't exist in the map
		for (int i = 0;i < 5;i++) {
			testMap.put("key" + i, dates[i]);
		
			assertFalse(map.containsKey("key" + i));
			assertFalse(map.containsValue(dates[i]));
		}
		
		// Put all the entries from the test map into the map
		map.putAll(testMap);
		
		// Confirm all the entries were added correctly
		for (int i = 0;i < 5;i++) {
			assertTrue(map.containsKey("key" + i));
			assertTrue(map.containsValue(dates[i]));
		}
	}
	
	@Test
	public void testRemove() {
		Date value = new Date();
		// Insert an entry
		map.put("key", value);
		
		// We should now have the entry in the map
		assertTrue(map.containsKey("key"));
		assertTrue(map.containsValue(value));
		
		// Remove the entry
		map.remove("key");
		
		// The entry should now no longer be in the map
		assertFalse(map.containsKey("key"));
		assertFalse(map.containsValue(value));
	}
	
	@Test
	public void testIsEmpty() {
		// The map should be empty to start with
		assertTrue(map.isEmpty());
		
		// Add an entry
		map.put("key", new Date());
		
		// After adding an entry it should no longer be empty
		assertFalse(map.isEmpty());
	}
	
	@Test
	public void testSize() {
		// The map should start with size 0
		assertEquals(0, map.size());
		
		// Add 2 unique entries
		map.put("test", new Date());
		map.put("test1", new Date());
		
		// The map size should now be 2
		assertEquals(2, map.size());
		
		// Add an existing key, this should overwrite "test1"
		map.put("test1", new Date());
		
		// The size shouldn't be increased because we overwrote the old entry
		assertEquals(2, map.size());
	}
	
	@Test
	public void testClear() {
		Date value = new Date();
		// Insert an entry
		map.put("key", value);
		
		// Confirm the entry has been added
		assertTrue(map.containsKey("key"));
		assertTrue(map.containsValue(value));
		
		// Clear the map
		map.clear();
		
		// Confirm the entry no longer exists
		assertFalse(map.containsKey("key"));
		assertFalse(map.containsValue(value));
	}
	
	@Test
	public void testToZip() {
		// Insert an entry
		map.put("key", new Date());
		
		// Create a file for the zip, if it exists delete it
		File zipFile = new File("test.zip");
		if (zipFile.exists())
			zipFile.delete();
		
		// Confirm the file doesn't exist
		assertFalse(zipFile.exists());
		
		// Create a backup
		map.toZip(zipFile);
		
		// Confirm the backup has been saved
		assertTrue(zipFile.exists());
		
		zipFile.delete();
	}
	
}
