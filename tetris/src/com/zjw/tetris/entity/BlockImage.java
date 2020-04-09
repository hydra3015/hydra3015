package com.zjw.tetris.entity;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class BlockImage {

	private static String imagePath = "game/rect.png";
	private static Map<Integer,Image> blockImages = null;
	public static final Integer WHITE =		0;
	public static final Integer RED = 		1;
	public static final Integer ORANGE = 	2;
	public static final Integer YELLOW = 	3;
	public static final Integer GREEN = 	4;
	public static final Integer CYAN = 		5;
	public static final Integer BLUE = 		6;
	public static final Integer PURPLE = 	7;
	public static final Integer GRAY = 		8;
	public static Map<Integer,String> colorMap;
	static {
		initBlockImages();
	}

	
	public static void  initBlockImages(){
		// 取得图片读入器
		  Iterator readers = ImageIO.getImageReadersByFormatName("png");
		  ImageReader reader = (ImageReader) readers.next();
		  // 取得图片读入流
		  InputStream source;
		try {
			source = Thread.currentThread().getContextClassLoader().getResourceAsStream(imagePath);
			ImageInputStream iis;
			iis = ImageIO.createImageInputStream(source);
		  reader.setInput(iis, true);
		  // 图片参数

		  ImageReadParam param = reader.getDefaultReadParam();
		  blockImages = new HashMap<Integer,Image>();
		  for(int i = 0 ;i<9;i++) {
			  Rectangle rect = new Rectangle(i * 32, 0, 32, 32);
			  param.setSourceRegion(rect);
			  Image img = null;
			try {
				img = reader.read(0, param);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  blockImages.put(i, img);
		  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//初始化colorMap
		colorMap = new HashMap<Integer,String>();
		colorMap.put(0, "WHITE");
		colorMap.put(1, "RED");
		colorMap.put(2, "ORANGE");
		colorMap.put(3, "YELLOW");
		colorMap.put(4, "GREEN");
		colorMap.put(5, "CYAN");
		colorMap.put(6, "BLUE");
		colorMap.put(7, "PURPLE");
		colorMap.put(8, "GRAY");
	}
	
	public static Image get(Integer color) {
		return blockImages.get(color);
	}
	public static Integer roundColor() {
		return (int)(Math.random()*7) + 1;
	}
	public static String getColor(Integer color) {
		return colorMap.get(color);
	}
}
