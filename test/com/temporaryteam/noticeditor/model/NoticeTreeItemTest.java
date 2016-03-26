package com.temporaryteam.noticeditor.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class NoticeTreeItemTest {

	@Test
	public void testBranch() {
		NoticeTreeItem branch = new NoticeTreeItem("branch");
		assertTrue(branch.isBranch());
		assertFalse(branch.isLeaf());
		assertEquals(0, branch.getInternalChildren().size());
	}
	
	@Test
	public void testLeaf() {
		NoticeTreeItem leaf = new NoticeTreeItem("leaf", "content");
		assertEquals("leaf", leaf.getTitle());
		assertFalse(leaf.isBranch());
		assertTrue(leaf.isLeaf());
		assertEquals(0, leaf.getInternalChildren().size());
	}
	
	@Test
	public void testAddChild() {
		NoticeTreeItem branch = new NoticeTreeItem("branch");
		assertEquals(0, branch.getInternalChildren().size());
		for (int i = 0; i < 10; i++) {
			branch.addChild(new NoticeTreeItem("branch"));
		}
		assertEquals(10, branch.getInternalChildren().size());
		
		branch.addChild(null);
		assertEquals(11, branch.getInternalChildren().size());
	}
	
	@Test
	public void testChangeContent() {
		NoticeTreeItem branch = new NoticeTreeItem("branch");
		assertNull(branch.getContent());
		branch.changeContent("test");
		assertNull(branch.getContent());
		
		NoticeTreeItem leaf = new NoticeTreeItem("leaf", "content");
		assertEquals("content", leaf.getContent());
		leaf.changeContent("test");
		assertEquals("test", leaf.getContent());
	}
	
	@Test
	public void testFieldTitle() {
		NoticeTreeItem item = new NoticeTreeItem("foo");
		assertEquals("foo", item.getTitle());
		item.setTitle("bar");
		assertEquals("bar", item.getTitle());
	}
	
	@Test
	public void testFieldStatus() {
		NoticeTreeItem item = new NoticeTreeItem("foo", "content", 0);
		assertEquals(0, item.getStatus());
		item.setStatus(1);
		assertEquals(1, item.getStatus());
	}
}
