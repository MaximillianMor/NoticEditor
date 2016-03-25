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
public class PreviewStylesTest {

	@Test
	public void testValues() {
		PreviewStyles ps = PreviewStyles.DEFAULT;
		ps = PreviewStyles.GITHUB;
		ps = PreviewStyles.MARKDOWN;
		
		assertEquals("Markdown", ps.getName());
		assertEquals(PreviewStyles.PATH + "markdown.css", ps.getCssPath());
	}
}
