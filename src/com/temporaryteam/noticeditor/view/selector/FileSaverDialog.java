/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temporaryteam.noticeditor.view.selector;

import javafx.stage.Window;

/**
 *
 * @author Max Balushkin
 */
public class FileSaverDialog extends FileSelectorDialog {

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
