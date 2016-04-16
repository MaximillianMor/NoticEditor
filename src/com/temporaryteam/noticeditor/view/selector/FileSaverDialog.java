package com.temporaryteam.noticeditor.view.selector;

import javafx.stage.Window;

/**
 * File saving dialog
 * 
 * @author Max Balushkin
 */
public class FileSaverDialog extends FileSelectorDialog {

	/**
	 * Creates new file saving dialog
	 * 
	 * @param aOwnerWindow Dialog parent
	 */
	public FileSaverDialog(Window aOwnerWindow) {
		super(aOwnerWindow);
	}

	@Override
	public SelectorDialog show(String title) {
		super.show(title);
		selectedFile = fileChooser.showSaveDialog(ownerWindow);
		lastFilter = fileChooser.getSelectedExtensionFilter();
		
		if (selectedFile != null) {
			SelectorDialogService.setLastDirectory(selectedFile.getParentFile());
		}
		
		return this;
	}
	
}
