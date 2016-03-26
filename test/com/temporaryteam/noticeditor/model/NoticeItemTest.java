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
	public void testToString() {
		String expected = "foo\n\nbar";
		String actual = new NoticeItem("foo", "bar").toString();
		assertEquals(expected, actual);
	}
	
}
