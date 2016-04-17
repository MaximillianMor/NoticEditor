package com.temporaryteam.noticeditor.io.format;

import com.temporaryteam.noticeditor.io.HtmlExportStrategy;
import java.util.HashMap;

/**
 * Provides document formatters
 * 
 * @author Max Balushkin, aNNiMON
 */
public final class FormatService {

	public static final HtmlExportStrategy HTML = new HtmlExportStrategy();
	
	private static final HashMap<String, Format> services = new HashMap<>();
	
	/**
	 * Registers new service
	 * @param extension File extension
	 * @param service Formatter
	 */
	public static void register(String extension, Format service) {
		services.put(extension, service);
	}
	
	/**
	 * Provides formatter of specified extension
	 * @param <T> Formatter type
	 * @param extension File extension
	 * @return Formatter
	 */
	public static <T extends Format> T get(String extension) {
		return (T) services.get(extension);
	}
}
