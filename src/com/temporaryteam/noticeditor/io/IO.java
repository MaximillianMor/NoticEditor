package com.temporaryteam.noticeditor.io;

import java.io.IOException;

/**
 * Container for IO operations
 * 
 * @author Max Balushkin
 * 
 * Now it has totally bad design and needs remastering. 
 * It doesn't provides such functionality like unified interface 
 * for any type of external sources (any file formats (e.g. zip), databases, etc) as was suggested.
 * At this time it works only with simple datasources like plain files.
 * Other cases requires type casting.
 */
public interface IO {
	
	/**
	 * Reads from datasource
	 * 
	 * @return Content of datasource
	 * @throws IOException 
	 */
	String read() throws IOException;
	
	/**
	 * Writes content to datasource
	 * @param content Content
	 * @throws IOException 
	 */
	void write(String content) throws IOException;
	
	/**
	 * Returns datasource name (e.g. filename)
	 * @return 
	 */
	String getDatasourceName();
	
	/**
	 * Is datasource available?
	 * @return 
	 */
	boolean isAvailable();
	
}
