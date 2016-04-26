package com.temporaryteam.noticeditor.controller.notifier;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Notifier.
 * Checks message broadcasting.
 * 
 * @author Max Balushkin
 */
public class NotifierTest {
	
	private final Notifiable listener0;
	private final Notifiable listener1;
	
	public NotifierTest() {
		listener0 = EasyMock.mock(Notifiable.class);
		listener1 = EasyMock.mock(Notifiable.class);
		
		Notifier.register(listener0);
		Notifier.register(listener1);
	}

	@After
	public void reset() {
		EasyMock.reset(listener0, listener1);
	}
	
	@Test
	public void testMessage_String() {
		/* expect */ listener0.message("message");
		EasyMock.expectLastCall();
		/* expect */ listener1.message("message");
		EasyMock.expectLastCall();
		EasyMock.replay(listener0, listener1);
		
		Notifier.message("message");
	}

	@Test
	public void testMessage_String_Duration() {
		final Duration duration = new Duration(1);
		
		/* expect */ listener0.message("message", duration);
		EasyMock.expectLastCall();
		/* expect */ listener1.message("message", duration);
		EasyMock.expectLastCall();
		EasyMock.replay(listener0, listener1);
		
		Notifier.message("message", duration);
	}

	@Test
	public void testMessage_3args() {		
		final Duration duration = new Duration(1);
		final Paint textFill = Color.ALICEBLUE;
		
		/* expect */ listener0.message("message", duration, textFill);
		EasyMock.expectLastCall();
		/* expect */ listener1.message("message", duration, textFill);
		EasyMock.expectLastCall();
		EasyMock.replay(listener0, listener1);
		
		Notifier.message("message", duration, textFill);
	}

	@Test
	public void testError() {
		/* expect */ listener0.error("err");
		EasyMock.expectLastCall();
		/* expect */ listener1.error("err");
		EasyMock.expectLastCall();
		EasyMock.replay(listener0, listener1);
		
		Notifier.error("err");
	}

	@Test
	public void testSuccess() {
		/* expect */ listener0.success("ok");
		EasyMock.expectLastCall();
		/* expect */ listener1.success("ok");
		EasyMock.expectLastCall();
		EasyMock.replay(listener0, listener1);
		
		Notifier.success("ok");
	}
	
}
