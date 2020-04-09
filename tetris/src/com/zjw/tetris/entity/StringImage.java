package com.zjw.tetris.entity;

import sun.net.www.protocol.jar.URLJarFile;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;

import javax.imageio.ImageIO;

public class StringImage {

	private static Map<String,BufferedImage> stringImages = null;
	static {
		initStringImages();
	}

	
	public static void initStringImages() {
		stringImages = new HashMap<String,BufferedImage>();
		URL resource = Thread.currentThread().getContextClassLoader().getResource("string");
		String file = resource.getFile();
		File StringImageDir = new File(file);
		File[] stringImageFiles = StringImageDir.listFiles();
		for (File imageFile : stringImageFiles) {
			String fileName = imageFile.getName();
			BufferedImage stringImage = null;
			try {
				stringImage = ImageIO.read(imageFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String key = fileName.substring(0, fileName.lastIndexOf("."));
			stringImages.put(key, stringImage);
		}

	}
	
	public static Image get(String str) {
		return stringImages.get(str);
	}


}
