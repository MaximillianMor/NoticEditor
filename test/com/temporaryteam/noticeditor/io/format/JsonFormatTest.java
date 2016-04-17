/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temporaryteam.noticeditor.io.format;

import com.temporaryteam.noticeditor.io.StubIO;
import com.temporaryteam.noticeditor.model.NoticeTree;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Max Balushkin
 */
public class JsonFormatTest {
	
	@Test
	public void testJsonFormat() {
		try {
			JsonFormat fmt = new JsonFormat();
			
			String jsonStr =
					"{\"children\":"
					+ "[{\"children\":[],\"title\":\"Default notice\",\"content\":\"hkg\",\"status\":0}],"
					+ "\"title\":\"Root\"}";
			StubIO stub = new StubIO(jsonStr);
			NoticeTree tree = fmt.load(stub);
			fmt.save(stub, tree);
			
			assertEquals(stub.getDummyInput(), stub.getDummyOutput());
		} catch (FormatException | IOException ex) {
			fail(ex.getMessage());
		}
	}
	
}
