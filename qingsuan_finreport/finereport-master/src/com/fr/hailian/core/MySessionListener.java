package com.fr.hailian.core;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

public class MySessionListener {
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		MySessionContext.AddSession(httpSessionEvent.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		MySessionContext.DelSession(session);
	}
}
