package com.redhat.util;

public class RunUtil {

	public void stop() throws Exception {

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.exit(0);
			}
		}).start();
	}
}
