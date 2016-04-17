package com.temporaryteam.noticeditor.io;

import java.io.IOException;

/**
 *
 * @author Max Balushkin
 */
public class StubIO implements IO {
	
	private String dummyInput;
	private String dummyOutput;
	
	public StubIO(String aDummyInput) {
		dummyInput = aDummyInput;
	}

	@Override
	public String read() throws IOException {
		return dummyInput;
	}

	@Override
	public void write(String content) throws IOException {
		dummyOutput = content;
	}

	@Override
	public boolean isAvailable() {
		return true;
	}

	public String getDummyInput() {
		return dummyInput;
	}

	public String getDummyOutput() {
		return dummyOutput;
	}

	@Override
	public String getDatasourceName() {
		return "dummy";
	}

}
