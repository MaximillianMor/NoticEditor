package com.temporaryteam.noticeditor.view.selector;

import java.io.File;
import java.util.HashMap;

/**
 * Providing selectors (file saving, file loading, directory selecting) service
 * 
 * @author Max Balushkin
 */
public class SelectorDialogService {
	
	private static final HashMap<Class, SelectorDialog> services = new HashMap<>();
	
	/**
	 * Registers new selector
	 * 
	 * @param <T> Selector type
	 * @param service Selector
	 */
	public static <T extends SelectorDialog> void register(T service) {
		services.put(service.getClass(), service);
	}
	
	/**
	 * Registers new selector without type inference
	 * 
	 * @param service Selector
	 * @param serviceType Selector type
	 */
	public static void register(SelectorDialog service, Class serviceType) {
		services.put(serviceType, service);
	}
	
	/**
	 * Provides selector
	 * @param <T> Selector type
	 * @param selectorClass Selector type
	 * @return Selector
	 */
	public static <T extends SelectorDialog> T get(Class<T> selectorClass) {
		return (T) services.get(selectorClass);
	}
	
	private static File lastDirectory;

	/**
	 * Sets last selected directory
	 * 
	 * @param aLastDirectory 
	 */
	public static void setLastDirectory(File aLastDirectory) {
		lastDirectory = aLastDirectory;
	}
	
	/**
	 * Returns last selected directory
	 * 
	 * @return Last selected directory
	 */
	public static File getLastDirectory() {
		return lastDirectory;
	}
	
	/**
	 * Sets initial directory for every selector
	 * 
	 * @param initialDirectory Directory
	 */
	public static void setInitialDirectory(final File initialDirectory) {
		setLastDirectory(initialDirectory);
		for (SelectorDialog selector : services.values()) {
			selector.setInitialDirectory(lastDirectory);
		}
	}
}
