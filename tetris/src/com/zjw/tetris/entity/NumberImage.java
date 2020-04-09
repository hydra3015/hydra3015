package com.zjw.tetris.entity;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class NumberImage {

	private static String imagePathS = "game/number(40-24).png";
	private static String imagePathL = "game/number(80-48).png";
	private static Map<Integer,Image> numberImagesS = null;
	private static Map<Integer,Image> numberImagesL = null;
	static {
		initBlockImages();
	}

	
	public static void initBlockImages() {
		// 取得图片读入器
		Iterator readers = ImageIO.getImageReadersByFormatName("png");
		ImageReader reader = (ImageReader) readers.next();
		// 取得图片读入流
		InputStream source;
		try {
			source = Thread.currentThread().getContextClassLoader().getResourceAsStream(imagePathS);
			ImageInputStream iis;
			iis = ImageIO.createImageInputStream(source);
			reader.setInput(iis, true);
			// 图片参数

			ImageReadParam param = reader.getDefaultReadParam();
			numberImagesS = new HashMap<Integer, Image>();
			for (int i = 0; i < 10; i++) {
				Rectangle rect = new Rectangle(i * 24, 0, 24, 40);
				param.setSourceRegion(rect);
				Image img = null;
				try {
					img = reader.read(0, param);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				numberImagesS.put(i, img);
			}
			
			
			source = Thread.currentThread().getContextClassLoader().getResourceAsStream(imagePathL);
			iis = ImageIO.createImageInputStream(source);
			reader.setInput(iis, true);
			// 图片参数
			param = reader.getDefaultReadParam();
			numberImagesL = new HashMap<Integer, Image>();
			for (int i = 0; i < 10; i++) {
				Rectangle rect = new Rectangle(i * 48, 0, 48, 80);
				param.setSourceRegion(rect);
				Image img = null;
				try {
					img = reader.read(0, param);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				numberImagesL.put(i, img);
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Image getS(Integer num) {
		return numberImagesS.get(num);
	}
	public static Image getL(Integer num) {
		return numberImagesL.get(num);
	}

}
