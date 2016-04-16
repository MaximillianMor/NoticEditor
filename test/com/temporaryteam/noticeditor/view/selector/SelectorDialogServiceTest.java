package com.temporaryteam.noticeditor.view.selector;

import org.junit.Test;
import static org.junit.Assert.*;

public class SelectorDialogServiceTest {
	
	@Test
	public void testServiceInjection() {
		FileSaverDialogStub stub = new FileSaverDialogStub(null);
		SelectorDialogService.register(stub, FileSaverDialog.class);
		FileSelectorDialog selector = SelectorDialogService.get(FileSaverDialog.class);
		assertEquals(stub, selector);
		assertEquals(FileSaverDialogStub.FILENAME, selector.result().getName());
	}
	
	@Test
	public void testServiceLocator() {
		FileSaverDialog saver = new FileSaverDialog(null);
		SelectorDialogService.register(saver);
		FileSelectorDialog selector = SelectorDialogService.get(FileSaverDialog.class);
		assertEquals(saver, selector);
	}

}
