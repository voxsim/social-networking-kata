package it.voxsim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

public class AssertUtils {
	public static final long ONE_SECOND = 1000;
	public static final long ONE_MINUTE = 60 * ONE_SECOND;
	public static final long ONE_HOUR = 60 * ONE_MINUTE;
	public static final long ONE_DAY = 24 * ONE_HOUR;

	public static void assertEmpty(Collection<?> collection) {
		assertEquals(0, collection.size());
	}

	public static void assertNotEmpty(Collection<?> collection) {
		assertTrue(collection + " shold be not empty", collection.size() > 0);
	}
}
