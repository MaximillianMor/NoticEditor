package com.temporaryteam.noticeditor.view.selector;

import java.io.File;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * File selector. Can be filtered
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
	
	/**
	 * Creates new file selector
	 * 
	 * @param aOwnerWindow Dialog parent
	 */
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
	
	/**
	 * Returns filter list
	 * @return Filter list
	 */
	public ObservableList<ExtensionFilter> getExtensionFilters() {
		return fileChooser.getExtensionFilters();
	}
	
	/**
	 * Sets filters
	 * 
	 * @param filters Filters list
	 * @return This
	 */
	public FileSelectorDialog filter(ExtensionFilter... filters) {
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters().addAll(filters);
		
		return this;
	}
	
	/**
	 * Returns last selected extension
	 * 
	 * @return Selected extension
	 */
	public ExtensionFilter getLastExtension() {
		return lastFilter;
	}
	
	/**
	 * Clear filters
	 */
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
