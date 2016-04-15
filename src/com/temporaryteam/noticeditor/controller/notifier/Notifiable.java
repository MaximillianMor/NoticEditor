package com.temporaryteam.noticeditor.controller.notifier;

import javafx.scene.paint.Paint;
import javafx.util.Duration;

/**
 * Can receive string notifications
 * 
 * @author Max Balushkin
 */
public interface Notifiable {
	
	/**
	 * Shows message
	 * @param text Message text
	 */
	void message(String text);
	
	/**
	 * Shows message for a specified time
	 * @param text Message text
	 * @param duration Duration
	 */
	void message(String text, Duration duration);
	
	/**
	 * Shows coloured message for a specified time
	 * @param text Message text
	 * @param duration Duration
	 * @param textFill Colour
	 */
	void message(String text, Duration duration, Paint textFill);
	
	/**
	 * Shows error message
	 * @param text Message text
	 */
	void error(String text);
	
	/**
	 * Shows success message
	 * @param text Message text
	 */
	void success(String text);
}
