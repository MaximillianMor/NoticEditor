package com.temporaryteam.noticeditor.view.selector;

import java.io.File;

/**
 *
 * @author Max Balushkin
 */
public interface SelectorDialog {
	
	void setTitle(String string);
	void setInitialDirectory(File value);
	
	SelectorDialog show(String title);
	File result();
}
