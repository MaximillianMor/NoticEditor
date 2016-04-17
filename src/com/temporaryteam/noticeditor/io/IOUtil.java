package com.temporaryteam.noticeditor.io;

import gcardone.junidecode.Junidecode;
import java.io.*;
import java.net.URLEncoder;

public final class IOUtil {
	
	public static final int FILENAME_LIMIT = 60;
	public static final String NEW_LINE = System.lineSeparator();

	// FilImporter:[42, 52]
	public static String readContent(File file) throws IOException {
		return stringFromStream(new FileInputStream(file));
	}
	
	// HtmlImportStrategy:50
	public static void writeContent(File file, String content) throws IOException {
		try (OutputStream os = new FileOutputStream(file);
				Writer writer = new OutputStreamWriter(os, "UTF-8")) {
			writer.write(content);
		}
	}
	
	// no usage
	public static void removeDirectory(File directory) {
		if (directory.isFile() || !directory.exists()) return;
		removeDirectoryHelper(directory);
	}
	
	// self:[30 (no usage), 37 (recursive)]
	private static void removeDirectoryHelper(File file) {
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				removeDirectoryHelper(f);
			}
		}
		file.delete();
	}
	
	/**
	 * Sanitizes filename - transliterates and trims
	 * 
	 * @param name Sanitizing name
	 * @return Sanitized name, 
	 * if name was <code>null</code> - "empty"
	 */
	public static String sanitizeFilename(String name) {
		if (name == null || name.isEmpty()) return "empty";
		
		String newName = name;
		// Quick transliteration
		newName = Junidecode.unidecode(newName);
		// Convert non-ascii chars to char code
		try {
			newName = URLEncoder.encode(newName, "UTF-8");
		} catch (UnsupportedEncodingException ex) { }
		// Allow only english chars, numbers and some specific symbols
		newName = newName.toLowerCase().replaceAll("[^a-z0-9._\\(\\)]", "_");
		// Limit filename length
		if (newName.length() > FILENAME_LIMIT) {
			newName = newName.substring(0, FILENAME_LIMIT);
		}
		return newName;
	}
	
	// ZipWithIndexFormat:101
	public static InputStream toStream(String content) throws IOException {
		return toStream(content, "UTF-8");
	}
	
	// self:75
	public static InputStream toStream(String content, String charset) throws IOException {
		return new ByteArrayInputStream(content.getBytes(charset));
	}
	
	// self:16
	// WebImporter:24
	public static String stringFromStream(InputStream stream) throws IOException {
		return stringFromStream(stream, "UTF-8");
	}
	
	// self:83
	public static String stringFromStream(InputStream stream, String charset) throws IOException {
		final StringBuilder result = new StringBuilder();
		try (Reader isr = new InputStreamReader(stream, charset);
				BufferedReader reader = new BufferedReader(isr)) {
			String line;
			while ( (line = reader.readLine()) != null ) {
				result.append(line).append(NEW_LINE);
			}
		}
		return result.toString();
	}
	
	/**
	 * Copies content of stream to stream
	 * @param is Input
	 * @param os Output
	 * @throws IOException 
	 */
	public static void copy(InputStream is, OutputStream os) throws IOException {
		final int bufferSize = 4096;
		final byte[] buffer = new byte[bufferSize];
		int readed = 0;
		while ((readed = is.read(buffer, 0, bufferSize)) != -1) {
			os.write(buffer, 0, readed);
		}
	}
}
