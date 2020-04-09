package com.zjw.tetris.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class ConfigReader {
	private static Element rootElement= null;
	private ConfigReader() {}
	public static Element getrootElement() {
		if(rootElement != null)
			return rootElement;
		else {
			SAXReader saxReader = new SAXReader();
			
			try {
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("gameConfig.xml");
				Document read = saxReader.read(is);
				rootElement = read.getRootElement();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return rootElement;
		}
	}
	
	public static Element getElementByPath(String path) {
		if(rootElement == null) {
			getrootElement();
		}
		if(rootElement.getName().equals(path)) {
			return rootElement;
		}
		String[] nodes = path.split("/");
		if(!nodes[0].equals(rootElement.getName())) {
			return null;
		}
		if(nodes.length > 1) {
			Element temp = rootElement;
			for (int i = 1; i < nodes.length; i++) {
				temp = temp.element(nodes[i]);
				if(temp == null) {
					return null;
				}
			}
			return temp;
		}
		return null;
	}
}
