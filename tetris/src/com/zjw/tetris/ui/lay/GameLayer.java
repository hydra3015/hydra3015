package com.zjw.tetris.ui.lay;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Iterator;

import com.zjw.tetris.entity.BlockImage;
import com.zjw.tetris.entity.GameAct;

public class GameLayer extends Layer{

	

	@Override
	public void paint(Graphics g) {
		drawLay(g);
		drawBlockPanel(g);
		drawCurrentBlock(g);
	}
	
	private void drawBlockPanel(Graphics g) {
		boolean[][] gameMap = gameDto.getGameMap();
		Image img = gameDto.isRuning()?BlockImage.get(BlockImage.WHITE):BlockImage.get(BlockImage.GRAY);
		for (int i = 0; i < gameMap.length; i++) {
			for (int j = 0; j < gameMap[i].length; j++) {
				if(gameMap[i][j]) {
					g.drawImage(img, x + i * 32 + Layer.bold, y + j * 32 + Layer.bold, null);
				}
			}
		}
	}
	private void drawCurrentBlock(Graphics g) {
		GameAct gameAct = gameDto.getGameAct();
		if(gameAct != null) {
			for (Point point : gameAct.getPoints()) {
				g.drawImage(gameAct.getImg(), x + (int)point.getX() * 32 + Layer.bold, y + (int)point.getY() * 32 + Layer.bold, null);
			}
		}
	}

}
