package com.jlmsystemsinc.util;

import java.io.Closeable;
import java.util.LinkedList;
import java.util.List;

/**
 * https://stackoverflow.com/questions/884007/correct-way-to-close-nested-streams-and-writers-in-java
 * @author 
 *
 */
public abstract class AutoFileCloser {

	protected abstract void doWork() throws Throwable;

	private List<Closeable> closeables_ = new LinkedList<Closeable>();

	protected final <T extends Closeable> T autoClose(T closeable) {
		closeables_.add(0, closeable);
		return closeable;
	}

	public AutoFileCloser() {
		// a variable to track a "meaningful" exception, in case
		// a close() throws an exception
		Throwable pending = null;

		try {
			doWork(); // do the real work

		} catch (Throwable throwable) {
			pending = throwable;

		} finally {
			// close the watched streams
			for (Closeable closeable : closeables_) {
				if (closeable != null) {
					try {
						closeable.close();
					} catch (Throwable throwable) {
						if (pending == null) {
							pending = throwable;
						}
					}
				}
			}

			// if we had a pending exception, rethrow it
			// this is necessary b/c the close can throw an
			// exception, which would remove the pending
			// status of any exception thrown in the try block
			if (pending != null) {
				if (pending instanceof RuntimeException) {
					throw (RuntimeException) pending;
				} else {
					throw new RuntimeException(pending);
				}
			}
		}
	}
}
