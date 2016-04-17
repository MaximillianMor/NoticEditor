package com.temporaryteam.noticeditor.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.prefs.Preferences;
import org.easymock.EasyMock;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max Balushkin
 * TODO: Test with mock-objects cause of environment modifying
 */
public class PrefsTest {
	
	@Test 
	public void injectMockPrefs() throws Exception {
		Preferences pref = EasyMock.createMock(Preferences.class);
		
		Field field = Prefs.class.getDeclaredField("user");
		field.setAccessible(true);
		Field modifier = Field.class.getDeclaredField("modifiers");
		modifier.setAccessible(true);
		modifier.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		modifier.setAccessible(false);
		
		field.set(null, pref);
		assertEquals(pref, field.get(null));
		field.setAccessible(false);
		String foo = Prefs.getLastDirectory();
		int bar = 42;
	}
//	@Test
//	public void testLastDirectory() {
////		Prefs.setLastDirectory("C:\\");
////		assertEquals("C:\\", Prefs.getLastDirectory());
//	}
//
//	@Test
//	public void testRecentFiles() {
////		List<String> current = Prefs.getRecentFiles();
////		Prefs.addToRecentFiles("C:\\etc\\null\\tmp.json");
////		List<String> recent = Prefs.getRecentFiles();
////		recent.removeAll(current);
////		assertEquals(1, recent.size());
//	}
}
