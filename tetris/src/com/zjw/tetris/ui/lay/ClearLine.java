package com.zjw.tetris.ui.lay;

import java.awt.Graphics;

public class ClearLine extends Layer{

	public String title = "clearLine";
	{
		super.setTitle(title);
	}
	

	@Override
	public void paint(Graphics g) {
		drawCommon(g);
		drawNumber2(g, getGameDto().getClearLine(), 105, 40);
	}

}
