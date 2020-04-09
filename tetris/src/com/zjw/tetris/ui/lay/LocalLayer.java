package com.zjw.tetris.ui.lay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import com.zjw.tetris.entity.Player;

public class LocalLayer extends Layer{

	private int top = 80;
	private int lineHeight = 30;
	private int margin = 10;
	private int namePadding = 20;
	private int scorePadding = 100;
	private int fontsize = 24;
	
	
	public String title = "localRecord";
	{
		super.setTitle(title);
	}
	

	@Override
	public void paint(Graphics g) {
		drawCommon(g);
		drawRecord(g);
	}

	
	public void drawRecord(Graphics g) {
		Font f = g.getFont();
		g.setFont(new Font("微软雅黑", Font.BOLD, fontsize));
		List<Player> players = gameDto.getLocalRecord();
		for (int i = 0;i< players.size();i++) {
			String name = players.get(i).getName();
			int score = players.get(i).getScore();
			g.drawString(name, x + namePadding , y + top + (margin + lineHeight) * i);
			g.drawString(score+"", x + width - scorePadding , y + top + (margin + lineHeight) * i);
			if(i!=players.size()-1) {
				g.drawLine(x + namePadding, y + top + (margin + lineHeight) * i + 10, x + width - scorePadding + 60, y + top + (margin + lineHeight) * i + 10);
			}
		}
		g.setFont(f);
	}
	
}


