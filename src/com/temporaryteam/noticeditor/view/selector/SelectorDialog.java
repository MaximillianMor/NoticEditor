package com.temporaryteam.noticeditor.view.selector;

import java.io.File;
import com.temporaryteam.noticeditor.io.IO;

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
	 * @deprecated Use <code>io</code>
	 */
	File result();
	
	/**
	 * Returns containered datasource
	 * 
	 * @return IO container
	 */
	IO io();
	
}
