/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temporaryteam.noticeditor.model;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Before;

/**
 *
 * @author Max Balushkin
 */
public class NoticeStatusListTest {
	@BeforeClass
	public static void setUpClass() {
		NoticeStatusList.save();
	}
	
	@Before
	public void setUp() {
		NoticeStatusList.restore();
	}
	
	@After
	public void tearDown() {
		NoticeStatusList.restore();
	}

	@Test
	public void testAdd_String() {
		NoticeStatusList.add("foobar");
		assertEquals(0, NoticeStatusList.getStatusCode("foobar"));
		assertEquals("foobar", NoticeStatusList.getStatus(0).getName());
	}

	@Test
	public void testAdd_String_int() {	
		NoticeStatusList.add("baz", 42);
		assertEquals(42, NoticeStatusList.getStatusCode("baz"));
		assertEquals("baz", NoticeStatusList.getStatus(42).getName());
	}
	
	@Test
	public void testAdd_IfCustomIndexIsLesserReal() {
		NoticeStatusList.add("foo", 42);
		NoticeStatusList.add("bar", 2);
		assertEquals(43, NoticeStatusList.getStatusCode("bar"));
	}

	@Test
	public void testGetStatusCode_IfThereIsNoSuchStatus() {
		assertEquals(0, NoticeStatusList.getStatusCode("abra"));		
	}

	@Test
	public void testGetStatus_IfThereIsNoSuchStatusCode() {		
		assertNull(NoticeStatusList.getStatus(42));		
	}
	
	@Test
	public void testMemento() {
		NoticeStatusList.save();
		NoticeStatusList.add("foobar", 42);
		assertNotNull(NoticeStatusList.getStatus(42));
		NoticeStatusList.restore();
		assertNull(NoticeStatusList.getStatus(42));
	}
	
	@Test
	public void testGetStatus_MustReturnZeroStatusCode() {
		NoticeStatusList.add("default");
		assertEquals(0, NoticeStatusList.getStatus(42).getCode());
	}
	
	@Test
	public void testClear() {
		NoticeStatusList.add("foobar", 42);
		NoticeStatusList.clear();
		assertNull(NoticeStatusList.getStatus(42));
	}
}
