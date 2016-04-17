package com.temporaryteam.noticeditor.io;

import java.io.*;

/**
 * Container for file datasource
 * 
 * @author Max Balushkin
 */
public class FileIO implements IO {
	
	private final File file;
	
	public FileIO(File aFile) {
		file = aFile;
	}

	@Override
	public String read() throws IOException {
		return stringFromStream(new FileInputStream(file));
	}

	@Override
	public void write(String content) throws IOException {
		try (OutputStream os = new FileOutputStream(file, false);
				Writer writer = new OutputStreamWriter(os, "UTF-8")) {
			writer.write(content);
		}
	}
	
	/**
	 * Converts stream to string
	 * Charset - UTF-8
	 * 
	 * @param stream Input stream
	 * @return Stream content
	 * @throws IOException 
	 */
	private static String stringFromStream(InputStream stream) throws IOException {
		return stringFromStream(stream, "UTF-8");
	}
	
	/**
	 * Converts stream to string with specified charset
	 * @param stream Input stream
	 * @param charset Stream content
	 * @return Stream charset
	 * @throws IOException 
	 */
	private static String stringFromStream(InputStream stream, String charset) throws IOException {
		final StringBuilder result = new StringBuilder();
		try (Reader isr = new InputStreamReader(stream, charset);
				BufferedReader reader = new BufferedReader(isr)) {
			String line;
			while ( (line = reader.readLine()) != null ) {
				result.append(line).append(IOUtil.NEW_LINE);
			}
		}
		return result.toString();
	}

	@Override
	public boolean isAvailable() {
		return file != null;
	}
	
	/**
	 * Returns path to file
	 * @return File path
	 */
	public String getPath() {
		return file.getAbsolutePath();
	}
	
	/**
	 * Returns file object
	 * @return File
	 */
	public File getFile() {
		return file;
	}
	
	@Override
	public String getDatasourceName() {
		return file.getName();
	}
	
}
