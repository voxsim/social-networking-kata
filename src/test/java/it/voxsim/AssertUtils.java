package it.voxsim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

public class AssertUtils {
	public static void assertEmpty(Collection<?> collection) {
		assertEquals(0, collection.size());
	}
	
	public static void assertNotEmpty(Collection<?> collection) {
		assertTrue(collection + " shold be not empty", collection.size() > 0);
	}
}
