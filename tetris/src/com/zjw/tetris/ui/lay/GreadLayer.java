package com.zjw.tetris.ui.lay;

import java.awt.Graphics;

public class GreadLayer extends Layer{

	public String title = "gread";
	{
		super.setTitle(title);
	}


	@Override
	public void paint(Graphics g) {
		drawCommon(g);
		drawNumber2(g, gameDto.getGread(), 80, 60);
	}

}
