package com.zjw.tetris.config;

import org.dom4j.Element;

public class LayerConfig {

	private String className;
	private int x;
	private int y;
	private int width;
	private int height;
	
	
	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public LayerConfig(Element layersElement) {
		this.className = layersElement.attributeValue("className");
		this.x = Integer.parseInt(layersElement.attributeValue("x"));
		this.y = Integer.parseInt(layersElement.attributeValue("y"));
		this.width = Integer.parseInt(layersElement.attributeValue("width"));
		this.height = Integer.parseInt(layersElement.attributeValue("height"));
	}
}
