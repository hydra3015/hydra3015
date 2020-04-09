package com.zjw.tetris.config;

import org.dom4j.Element;

public class DBConfig {

	private String className;
	private String url;
	private String username;
	private String password;
	public String getClassName() {
		return className;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	
	public DBConfig() {
		super();
		init();
	}
	public void init() {
		Element element = ConfigReader.getElementByPath("game/data");
		className = element.element("className").getText();
		url = element.element("url").getText();
		username = element.element("username").getText();
		password = element.element("password").getText();
	}
}
