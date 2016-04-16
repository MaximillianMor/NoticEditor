/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temporaryteam.noticeditor.view.selector;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max Balushkin
 */
public class SelectorDialogServiceTest {
	
	@Test
	public void testGet() {
//		SelectorDialogService.fileSaver(new FileSaverDialog(null));
		SelectorDialogService.register(new FileSaverDialog(null));

		FileSelectorDialog saver = SelectorDialogService.get(FileSaverDialog.class);
		int foo = 0;
	}

}
