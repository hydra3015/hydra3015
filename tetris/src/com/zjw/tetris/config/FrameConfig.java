package com.zjw.tetris.config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.zjw.tetris.ui.lay.Layer;

public class FrameConfig {

	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private List<LayerConfig> layers = null;


	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public List<LayerConfig> getLayers() {
		return layers;
	}

	public void setLayers(List<LayerConfig> layers) {
		this.layers = layers;
	}

	public FrameConfig() {
		init();
	}

	private void init() {
		Element frameElement = ConfigReader.getElementByPath("game/frame");
		String strX = frameElement.attributeValue("x");
		String strY = frameElement.attributeValue("y");
		if(!"".equals(strX) && !"auto".equals(strX)) {
			this.x = Integer.parseInt(strX);
		}
		if(!"".equals(strY) && !"auto".equals(strY)) {
			this.x = Integer.parseInt(strY);
		}
		this.width = Integer.parseInt(frameElement.attributeValue("width"));
		this.height = Integer.parseInt(frameElement.attributeValue("height"));
		layers = new ArrayList<LayerConfig>();
		//layer静态属性赋值
		Element layerDefaultElement = frameElement.element("layerDefault");
		String strTitle_x = layerDefaultElement.attributeValue("title_x");
		String strTitle_y = layerDefaultElement.attributeValue("title_y");
		String strPadding = layerDefaultElement.attributeValue("padding");
		String imgPath = layerDefaultElement.attributeValue("imgPath");
		String strBold = layerDefaultElement.attributeValue("bold");
		String strStringSize = layerDefaultElement.attributeValue("stringSize");
		String strNumberSize = layerDefaultElement.attributeValue("numberSize");
		String strImagebold = layerDefaultElement.attributeValue("imagebold");
		Layer.setTitle_x(Integer.parseInt(strTitle_x));
		Layer.setTitle_y(Integer.parseInt(strTitle_y));
		Layer.setPadding(Integer.parseInt(strPadding));
		Layer.setBold(Integer.parseInt(strBold));
		Layer.setStringSize(Integer.parseInt(strStringSize));
		Layer.setNumberSize(Integer.parseInt(strNumberSize));
		Layer.setImageBold(Integer.parseInt(strImagebold));
		Layer.setImgPath(imgPath);
		//
		List<Element> layersElements = frameElement.elements("layer");
		for (Element layersElement:layersElements) {
			layers.add(new LayerConfig(layersElement));
		}
	}
}
