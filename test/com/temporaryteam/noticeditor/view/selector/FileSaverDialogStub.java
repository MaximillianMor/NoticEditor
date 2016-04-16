package com.temporaryteam.noticeditor.view.selector;

import java.io.File;
import javafx.stage.Window;

/**
 * Stub object of file saving dialog
 * 
 * @author Max Balushkin
 */
public class FileSaverDialogStub extends FileSaverDialog {
	
	public static String FILENAME = "there is no file";
	
	public FileSaverDialogStub(Window aOwnerWindow) {
		super(aOwnerWindow);
	}

	/**
	 * @return <code>File("there is no file")</code>
	 */
	@Override
	public File result() {
		return new File(FILENAME);
	}
	
}
