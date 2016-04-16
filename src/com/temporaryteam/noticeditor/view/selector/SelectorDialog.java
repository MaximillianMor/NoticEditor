package com.temporaryteam.noticeditor.view.selector;

import java.io.File;

/**
 * Interface of selector
 * 
 * @author Max Balushkin
 */
public interface SelectorDialog {
	
	/**
	 * Sets dialog title
	 * @param string Title
	 */
	void setTitle(String string);
	
	/**
	 * Sets initial directory
	 * @param value Directory
	 */
	void setInitialDirectory(File value);
	
	/**
	 * Shows dialog
	 * @param title Dialog title
	 * 
	 * @return This
	 */
	SelectorDialog show(String title);
	/**
	 * Returns selected file
	 * 
	 * @return Selected file
	 */
	File result();
}
