package com.temporaryteam.noticeditor.io.format;

import com.temporaryteam.noticeditor.io.IO;
import com.temporaryteam.noticeditor.model.NoticeTree;
import java.io.IOException;

/**
 * Document format. Loads and saves documents
 * 
 * @author Max Balushkin
 */
public interface Format {
	
	/**
	 * Loads notices from datasource
	 * 
	 * @param source Source (e.g. file)
	 * @return Notice tree
	 * @throws FormatException
	 * @throws IOException 
	 */
	NoticeTree load(IO source) throws FormatException, IOException;
	/**
	 * Saces notices to datasource
	 * @param destination Destination
	 * @param tree Notice tree
	 * @throws FormatException
	 * @throws IOException 
	 */
	void save(IO destination, NoticeTree tree) throws FormatException, IOException;
	
}
