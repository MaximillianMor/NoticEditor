package com.temporaryteam.noticeditor.view.selector;

import javafx.stage.Window;

/**
 * File loading dialog
 * 
 * @author Max Balushkin
 */
public class FileLoaderDialog extends FileSelectorDialog {

	/**
	 * Creates new file loading dialog
	 * 
	 * @param aOwnerWindow Dialog parent
	 */
	public FileLoaderDialog(Window aOwnerWindow) {
		super(aOwnerWindow);
	}

	@Override
	public SelectorDialog show(String title) {
		super.show(title);
		selectedFile = fileChooser.showOpenDialog(ownerWindow);
		lastFilter = fileChooser.getSelectedExtensionFilter();
		
		if (selectedFile != null) {
			SelectorDialogService.setLastDirectory(selectedFile.getParentFile());
		}
		
		return this;
	}
	
}
