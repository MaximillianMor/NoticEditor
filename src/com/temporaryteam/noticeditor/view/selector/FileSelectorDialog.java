/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temporaryteam.noticeditor.view.selector;

import java.io.File;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 *
 * @author Max Balushkin
 */
public abstract class FileSelectorDialog implements SelectorDialog {
	
	public static final ExtensionFilter SUPPORTED = new ExtensionFilter("Supported Files", "*.zip", "*.txt", "*.md", "*.htm", "*.html", "*.json");
	public static final ExtensionFilter JSON = new ExtensionFilter("Json Files", "*.json");
	public static final ExtensionFilter ZIP = new ExtensionFilter("Zip Files", "*.zip");
	public static final ExtensionFilter ALL = new ExtensionFilter("All Files", "*.*");
	
	protected final FileChooser fileChooser;
	protected final Window ownerWindow;
	
	protected File selectedFile;
	protected ExtensionFilter lastFilter;
	
	protected FileSelectorDialog(Window aOwnerWindow) {
		fileChooser = new FileChooser();
		ownerWindow = aOwnerWindow;
		lastFilter = ALL;
	}
	
	@Override
	public void setTitle(String title) {
		fileChooser.setTitle(title);
	}

	@Override
	public void setInitialDirectory(File initialDirectory) {
		fileChooser.setInitialDirectory(initialDirectory);
	}

	@Override
	public File result() {
		return selectedFile;
	}
	
	public ObservableList<ExtensionFilter> getExtensionFilters() {
		return fileChooser.getExtensionFilters();
	}
	
	public FileSelectorDialog filter(ExtensionFilter... filters) {
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters().addAll(filters);
		
		return this;
	}
	
	public ExtensionFilter getLastExtension() {
		return lastFilter;
	}
	
	public void clearFilters() {
		fileChooser.getExtensionFilters().clear();
	}

	@Override
	public SelectorDialog show(String title) {
		setTitle(title);
		
		File lastDir = SelectorDialogService.getLastDirectory();
		boolean hasLastDir = lastDir != null  && lastDir.exists() && lastDir.isDirectory();
		if (hasLastDir) {
			setInitialDirectory(selectedFile);
		}
		
		return this;
	}
	
}
