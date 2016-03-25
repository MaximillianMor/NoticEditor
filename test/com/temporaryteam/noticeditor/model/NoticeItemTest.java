/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temporaryteam.noticeditor.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max Balushkin
 */
public class NoticeItemTest {

	@Test
	public void testConstructors() {
		NoticeItem item = new NoticeItem("foobar");
		assertEquals("foobar", item.getTitle());
		assertEquals(null, item.getContent());
		assertEquals(NoticeItem.STATUS_NORMAL, item.getStatus());
		
		item = new NoticeItem("foo", "bar");
		assertEquals("foo", item.getTitle());
		assertEquals("bar", item.getContent());
		assertEquals(NoticeItem.STATUS_NORMAL, item.getStatus());
		
		item = new NoticeItem("foo", "bar", NoticeItem.STATUS_IMPORTANT);		
		assertEquals("foo", item.getTitle());
		assertEquals("bar", item.getContent());
		assertEquals(NoticeItem.STATUS_IMPORTANT, item.getStatus());
	}
	
	@Test
	public void testAddChild() {
	}

	@Test
	public void testIsLeaf() {
	}

	@Test
	public void testIsBranch() {
	}

	@Test
	public void testGetContent() {
	}

	@Test
	public void testChangeContent() {
	}

	@Test
	public void testGetTitle() {
	}

	@Test
	public void testSetTitle() {
	}

	@Test
	public void testSetStatus() {
	}

	@Test
	public void testGetStatus() {
	}

	@Test
	public void testToString() {
		String expected = "foo\n\nbar";
		String actual = new NoticeItem("foo", "bar").toString();
		assertEquals(expected, actual);
	}
	
}
