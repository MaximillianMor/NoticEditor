package com.temporaryteam.noticeditor.view.selector;

import com.temporaryteam.noticeditor.io.FileIO;
import com.temporaryteam.noticeditor.io.IO;
import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

/**
 * Directory selector
 * 
 * @author Max Balushkin
 */
public class DirectorySelectorDialog implements SelectorDialog {

	private final DirectoryChooser directoryChooser;
	private final Window ownerWindow;
	
	private File selectedDirectory;
	
	/**
	 * Creates new directory selector
	 * 
	 * @param aOwnerWindow Dialog parent
	 */
	public DirectorySelectorDialog(Window aOwnerWindow) {
		directoryChooser = new DirectoryChooser();
		ownerWindow = aOwnerWindow;
	}
	
	@Override
	public void setTitle(String string) {
		directoryChooser.setTitle(string);
	}

	@Override
	public void setInitialDirectory(File initialDirectory) {
		directoryChooser.setInitialDirectory(initialDirectory);
	}

	@Override
	public SelectorDialog show(String title) {
		setTitle(title);
		selectedDirectory = directoryChooser.showDialog(ownerWindow);
		
		if (selectedDirectory != null) {
			SelectorDialogService.setLastDirectory(selectedDirectory);
		}
		
		return this;
	}

	/**
	 * @deprecated  
	 */
	@Override
	public File result() {
		return selectedDirectory;
	}
	
	@Override
	public IO io() {
		return new FileIO(selectedDirectory);
	}
	
}
