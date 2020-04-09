package com.zjw.tetris.ui.lay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import com.zjw.tetris.dto.GameDto;

public class SuspendLayer extends Layer{

	private Image suspendImg= null;



	public Image getSuspendImg() {
		return suspendImg;
	}

	public void setSuspendImg(Image suspendImg) {
		this.suspendImg = suspendImg;
	}

	public SuspendLayer() {
		init();
	}

	private void init() {
		suspendImg = new ImageIcon("image/string/suspend.png").getImage();
	}
	
	@Override
	public void paint(Graphics g) {
		if(gameDto.isSuspend()) {
			int imgWidth = suspendImg.getWidth(null);
			int imgHeight = suspendImg.getHeight (null);
			int panelWidth = 1280;
			int panelHeight = 720;
			int dx1 = (panelWidth - imgWidth) / 2;
			int dy1 = (panelHeight - imgHeight) / 2 -32;
			int dx2 = dx1 + imgWidth;
			int dy2 = dy1 + imgHeight;
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(0, 0, panelWidth, panelHeight);
			g.drawImage(suspendImg, dx1, dy1, dx2, dy2, 0, 0, imgWidth, imgHeight, null);
		}
		
	}

}
