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
public class NoticeStatusTest {
	@Test
	public void testNoticeStatus() {
		NoticeStatus ns = new NoticeStatus("foobar", 42);
		assertEquals("foobar", ns.getName());
		assertEquals(42, ns.getCode());
		
		ns.setName("baz");
		assertEquals("baz", ns.getName());
		
		ns.setCode(0);
		assertEquals(0, ns.getCode());
		
		assertEquals(ns.getName(), ns.toString());
	}
}
