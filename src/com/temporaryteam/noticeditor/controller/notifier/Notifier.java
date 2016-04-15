package com.temporaryteam.noticeditor.controller.notifier;

import java.util.ArrayList;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

/**
 * Component which sends string notifications to subscribers
 * 
 * @see Notifiable
 * @author Max Balushkin
 */
public class Notifier {
	private static final ArrayList<Notifiable> subscribers = new ArrayList<>();
	
	/**
	 * Registers subscriber
	 * 
	 * @param notifiable Subscriber
	 */
	public static void register(Notifiable notifiable) {
		subscribers.add(notifiable);
	}
	
	/**
	 * Sends message to subscribers
	 * 
	 * @param text Message text
	 */
	public static void message(String text) {
		subscribers.forEach((Notifiable n) -> n.message(text));
	}
	
	/**
	 * Sends message for a time
	 * 
	 * @param text Message text
	 * @param duration Duration
	 */
	public static void message(String text, Duration duration) {
		subscribers.forEach((Notifiable n) -> n.message(text, duration));
	}
	
	/**
	 * Sends coloured message for a specified time
	 * @param text Message text
	 * @param duration Duration
	 * @param textFill Colour
	 */
	public static void message(String text, Duration duration, Paint textFill) {
		subscribers.forEach((Notifiable n) -> n.message(text, duration, textFill));
	}
	
	/**
	 * Sends error message
	 * @param text Message text
	 */
	public static void error(String text) {
		subscribers.forEach((Notifiable n) -> n.error(text));
	}
	
	/**
	 * Sends success message
	 * @param text Message text
	 */
	public static void success(String text) {
		subscribers.forEach((Notifiable n) -> n.success(text));
	}
}
