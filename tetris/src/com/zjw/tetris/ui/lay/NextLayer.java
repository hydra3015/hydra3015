package com.zjw.tetris.ui.lay;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.zjw.tetris.entity.GameAct;

public class NextLayer extends Layer{
	public static Point[] points; 
	public static Image img; 
	private static final int NEXT_X = 25;
	private static final int NEXT_Y = 35;
	private static final int DISP_BLOCK_SIZE = 20;
	public static void setNext(GameAct next) {
		img = next.getImg();
		Point[] nextPoints = next.getPoints();
		points = new Point[nextPoints.length];
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point(nextPoints[i].x,nextPoints[i].y);
		}
	}

	public String title = "next";
	{
		super.setTitle(title);
	}

	
	@Override
	public void paint(Graphics g) {
		drawCommon(g);
		drawNextBolck(g);
	}
	
	private void drawNextBolck(Graphics g) {
		if(points != null) {
			for (Point point : points) {
				int dx1=x +  NEXT_X  + (point.x - 2) * DISP_BLOCK_SIZE;
				int dy1=y +  NEXT_Y  + (point.y + 1) * DISP_BLOCK_SIZE;
				int dx2=x +  NEXT_X  + (point.x - 1) * DISP_BLOCK_SIZE;
				int dy2=y +  NEXT_Y  + (point.y + 2) * DISP_BLOCK_SIZE;
				g.drawImage(img, dx1, dy1, dx2, dy2, 0, 0, 32, 32, null);
			}
		}
	}

}
