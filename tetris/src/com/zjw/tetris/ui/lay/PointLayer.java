package com.zjw.tetris.ui.lay;

import java.awt.Color;
import java.awt.Graphics;

public class PointLayer extends Layer{

	private int scoreY = 70;
	private int clearLineY = 20;
	private int stirngCommonX = 30;
	private int dataCommonX = 55;
	private int ValueSlotY = 120;
	private int ValueSlotHeight = 35;
	

	@Override
	public void paint(Graphics g) {
		drawCommon(g);
		drawString(g, "clearLine", stirngCommonX,clearLineY);
		drawNumber2(g, getGameDto().getClearLine(), width - dataCommonX, clearLineY);
		drawString(g, "score", stirngCommonX,scoreY);
		drawNumber2(g, getGameDto().getScore(), width - dataCommonX, scoreY);
		drawValueSlot(g);
	}
	
	private void drawValueSlot(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		int VSX = x + bold + padding;
		int VSY = y + ValueSlotY;
		int ValueSlotWidth = width - (bold + padding) * 2;
		g.fillRect(VSX, VSY, ValueSlotWidth, ValueSlotHeight);
		int p = 3;
		g.setColor(Color.WHITE);
		g.fillRect(VSX+p, VSY + p, ValueSlotWidth - 2 * p, ValueSlotHeight - 2 * p);
		p+=3;
		g.setColor(Color.BLACK);
		g.fillRect(VSX+p, VSY + p, ValueSlotWidth - 2 * p, ValueSlotHeight - 2 * p);
		
		int valueWidth =(int) (1.0 * gameDto.getScore() / (gameDto.getGread() * 1000) * (ValueSlotWidth - 2 * p));
		g.setColor(Color.GREEN);
		g.fillRect(VSX+p, VSY + p, valueWidth, ValueSlotHeight - 2 * p);
		g.setColor(c);
	}

}
