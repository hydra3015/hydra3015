package com.zjw.tetris.ui.lay;

import java.awt.Graphics;

public class ScoreLayer extends Layer{

	public String title = "score";
	{
		super.setTitle(title);
	}
	

	@Override
	public void paint(Graphics g) {
		drawCommon(g);
		drawNumber2(g, getGameDto().getScore(), 105,40);
	}

}
