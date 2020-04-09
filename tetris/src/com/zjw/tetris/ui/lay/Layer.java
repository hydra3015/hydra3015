package com.zjw.tetris.ui.lay;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.zjw.tetris.dto.GameDto;
import com.zjw.tetris.entity.NumberImage;
import com.zjw.tetris.entity.StringImage;

public abstract class Layer {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected String title;
	protected GameDto gameDto;
	protected static int title_x;
	protected static int title_y;
	protected static int padding;
	protected static int stringSize;
	protected static int numberSize;
	protected static String imgPath;
	protected static BufferedImage img;
	protected static int imageWidth;
	protected static int imageHeight;
	protected static int bold;
	protected static int imageBold;
	
	public static int getNumberSize() {
		return numberSize;
	}

	public static void setNumberSize(int numberSize) {
		Layer.numberSize = numberSize;
	}

	public static int getPadding() {
		return padding;
	}
	
	public static void setPadding(int padding) {
		Layer.padding = padding;
	}
	
	public GameDto getGameDto() {
		return gameDto;
	}

	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static int getTitle_x() {
		return title_x;
	}

	public static void setTitle_x(int title_x) {
		Layer.title_x = title_x;
	}

	public static int getTitle_y() {
		return title_y;
	}

	public static void setTitle_y(int title_y) {
		Layer.title_y = title_y;
	}

	public static int getStringSize() {
		return stringSize;
	}

	public static void setStringSize(int stringSize) {
		Layer.stringSize = stringSize;
	}

	public static String getImgPath() {
		return imgPath;
	}

	public static void setImgPath(String imgPath) {
		Layer.imgPath = imgPath;
		try {
			Layer.img = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource(imgPath));
			imageWidth = img.getWidth(null);
			imageHeight = img.getHeight(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Image getImg() {
		return img;
	}

	public static int getBold() {
		return bold;
	}

	public static void setBold(int bold) {
		Layer.bold = bold;
	}

	public static int getImageWidth() {
		return imageWidth;
	}

	public static void setImageWidth(int imageWidth) {
		Layer.imageWidth = imageWidth;
	}

	public static int getImageHeight() {
		return imageHeight;
	}

	public static void setImageHeight(int imageHeight) {
		Layer.imageHeight = imageHeight;
	}

	public static int getImageBold() {
		return imageBold;
	}

	public static void setImageBold(int imageBold) {
		Layer.imageBold = imageBold;
	}

	protected void drawLay(Graphics g) {
		g.drawImage(img, x, y, x+bold, y+bold, 0, 0, imageBold, imageBold, null);
		g.drawImage(img, x+bold, y, x+width-bold, y+bold, imageBold, 0, imageWidth-imageBold, imageBold, null);
		g.drawImage(img, x+width-bold, y, x+width, y+bold, imageWidth-imageBold, 0, imageWidth, imageBold, null);
		g.drawImage(img, x, y+bold, x+bold, y+height-bold, 0, imageBold, imageBold, imageHeight-imageBold, null);
		g.drawImage(img, x+bold, y+bold, x+width-bold, y+height-bold, imageBold, imageBold, imageWidth-imageBold, imageHeight-imageBold, null);
		g.drawImage(img, x+width-bold, y+bold, x+width, y+height-bold, imageWidth-imageBold, imageBold, imageWidth, imageHeight-imageBold, null);
		g.drawImage(img, x, y+height-bold, x+bold, y+height, 0, imageHeight-imageBold, imageBold, imageHeight, null);
		g.drawImage(img, x+bold, y+height-bold, x+width-bold, y+height, imageBold, imageHeight-imageBold, imageWidth-imageBold, imageHeight, null);
		g.drawImage(img, x+width-bold, y+height-bold, x+width, y+height, imageWidth-imageBold, imageHeight-imageBold, imageWidth, imageHeight, null);
	
	}

	public abstract void paint(Graphics g);
	
	protected void drawNumber(Graphics g,int num, int numX, int numY) {
		Stack<Integer> st = new Stack<Integer>();
		do {
			int i = num % 10;
			st.push(i);
			num /=10;
		}while(num>0);
		for(int i = 0;!st.isEmpty();i++) {
			int j = st.pop();
			Image numImg = NumberImage.getS(j);
			int imgWidth = numImg.getWidth(null);
			int imgHeight = numImg.getHeight(null);
			int dispHeight = numberSize;
			int dispWidth = (int) (1.0 * imgWidth / imgHeight * dispHeight);
			g.drawImage(numImg, 
					x + numX + i * dispWidth, y + numY,
					x + numX + (i+1) * dispWidth, y + numY + dispHeight,
					0,0,imgWidth,imgHeight,null);
		}
	}
	
	protected void drawNumber2(Graphics g,int num, int numX, int numY) {
		int i=0,j = 0;
		do{
			i = num % 10;
			Image numImg = NumberImage.getS(i);
			int imgWidth = numImg.getWidth(null);
			int imgHeight = numImg.getHeight(null);
			int dispHeight = numberSize;
			int dispWidth = (int) (1.0 * imgWidth / imgHeight * dispHeight);
			g.drawImage(numImg, 
					x + numX - j++ * dispWidth, y + numY, 
					x + numX - (j - 2) * dispWidth, y + numY + dispHeight,
					0,0,imgWidth,imgHeight,null);
			num /= 10;
		}while(num>0);
	}
	
	

	protected void drawTitle(Graphics g) {
			Image stringImage = StringImage.get(title);
			int imgTotalWidth = stringImage.getWidth(null);
			int imgTotalHeight = stringImage.getHeight(null);
			int imgDispHeight = stringSize;
			int imgDispWidth = (int)(1.0 * imgTotalWidth / imgTotalHeight * imgDispHeight);
			int dx1 = x + title_x;
			int dy1 = y + title_y;
			int dx2 = x + title_x + imgDispWidth;
			int dy2 = y + title_y + imgDispHeight;
			int sx1 = 0;
			int sy1 = 0;
			int sx2 = imgTotalWidth;
			int sy2 = imgTotalHeight;
			g.drawImage(stringImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		
	}
	
	
	protected void drawString(Graphics g,String str,int strx ,int stry) {
		Image stringImage = StringImage.get(str);
		if(stringImage != null) {
			int imgTotalWidth = stringImage.getWidth(null);
			int imgTotalHeight = stringImage.getHeight(null);
			int imgDispHeight = stringSize;
			int imgDispWidth = (int)(1.0 * imgTotalWidth / imgTotalHeight * imgDispHeight);
			int dx1 = x + strx;
			int dy1 = y + stry;
			int dx2 = x + strx + imgDispWidth;
			int dy2 = y + stry + imgDispHeight;
			int sx1 = 0;
			int sy1 = 0;
			int sx2 = imgTotalWidth;
			int sy2 = imgTotalHeight;
			g.drawImage(stringImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		}
	
}
	
	
	
	
	protected void drawCommon(Graphics g) {
		if(width != 0 && height != 0) {
			drawLay(g);
		}
		if(title != null && !"".equals(title)) {
			drawTitle(g);
		}
	}
	
}
