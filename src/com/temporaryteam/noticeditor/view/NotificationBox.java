package com.temporaryteam.noticeditor.view;

import com.temporaryteam.noticeditor.controller.notifier.Notifiable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

/**
 * Notification box component
 * 
 * @author aNNiMON
 */
public class NotificationBox implements Notifiable {
	
	public static final Duration DURATION_SHORT = new Duration(2000);
	public static final Duration DURATION_LONG = new Duration(5000);
	
	private static final Duration TRANSITION_DURATION = new Duration(300);
	private static final Paint PAINT_MESSAGE = Color.WHITE;
	private static final Paint PAINT_ERROR = Color.rgb(255, 80, 80);
	private static final Paint PAINT_SUCCESS = Color.LIGHTGREEN;

	private final VBox notificationBox;
	private final Label notificationLabel;
	
	private Timeline hideTimer;
	private final TranslateTransition transitionIn, transitionOut;
	
	public NotificationBox(VBox aNotificationBox, Label aNotificationLabel) {
		notificationBox = aNotificationBox;
		notificationLabel = aNotificationLabel;
		
		transitionIn = new TranslateTransition(TRANSITION_DURATION, notificationBox);
		transitionIn.setToY(0);
		
		transitionOut = new TranslateTransition(TRANSITION_DURATION, notificationBox);
		transitionOut.setFromY(0);
		transitionOut.setOnFinished((e) -> notificationBox.setVisible(true));
	}
	
	/**
	 * Shows message for a short time
	 * 
	 * @param text Text message
	 */
	@Override
	public void message(String text) {
		message(text, DURATION_SHORT);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void message(String text, Duration duration) {
		message(text, duration, PAINT_MESSAGE);
	}
	
	@Override
	public void error(String text) {
		message(text, DURATION_LONG, PAINT_ERROR);
	}
	
	@Override
	public void success(String text) {
		message(text, DURATION_LONG, PAINT_SUCCESS);
	}
	
	@Override
	public void message(String text, Duration duration, Paint textFill) {
		if (hideTimer != null) {
			// message new notification while previous exists
			hideTimer.stop();
			transitionOut.stop();
		}
		notificationLabel.setTextFill(textFill);
		notificationLabel.setText(text);
		hideTimer = new Timeline(new KeyFrame(duration.add(TRANSITION_DURATION)));
		hideTimer.setOnFinished(this::hide);
		hideTimer.playFromStart();
		notificationBox.setVisible(true);
		
		transitionIn.setFromY(notificationBox.getHeight());
		transitionIn.playFromStart();
	}
	
	private void hide(ActionEvent e) {
		if (hideTimer != null) {
            hideTimer.stop();
            hideTimer = null;
        }
		transitionOut.setToY(notificationBox.getHeight());
		transitionOut.playFromStart();
	}

}
