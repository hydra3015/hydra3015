package com.zjw.tetris.entity;

import java.awt.Image;
import java.awt.Point;
import java.util.Iterator;

public class GameAct {

	private Point[] points;
	private int type;
	private int color;
	private Image img;
	public static final Point[][] pointLib;
	public static final int MAX_X = 9;
	public static final int MIN_X = 0;
	public static final int MAX_Y = 17;
	public static final int MIN_Y = 0;
	static{
		pointLib = new Point[7][];
		pointLib[0] = new Point[4];
		pointLib[0][0] = new Point(4,0);
		pointLib[0][1] = new Point(3,0);
		pointLib[0][2] = new Point(5,0);
		pointLib[0][3] = new Point(5,1);
		pointLib[1] = new Point[4];
		pointLib[1][0] = new Point(4,0);
		pointLib[1][2] = new Point(3,0);
		pointLib[1][1] = new Point(3,1);
		pointLib[1][3] = new Point(5,0);
		pointLib[2] = new Point[4];
		pointLib[2][0] = new Point(4,0);
		pointLib[2][1] = new Point(3,0);
		pointLib[2][2] = new Point(4,1);
		pointLib[2][3] = new Point(5,1);
		pointLib[3] = new Point[4];
		pointLib[3][0] = new Point(4,0);
		pointLib[3][2] = new Point(3,1);
		pointLib[3][1] = new Point(4,1);
		pointLib[3][3] = new Point(5,0);
		pointLib[4] = new Point[4];
		pointLib[4][0] = new Point(4,0);
		pointLib[4][1] = new Point(3,0);
		pointLib[4][2] = new Point(4,1);
		pointLib[4][3] = new Point(5,0);
		pointLib[5] = new Point[4];
		pointLib[5][0] = new Point(4,0);
		pointLib[5][1] = new Point(4,1);
		pointLib[5][2] = new Point(5,0);
		pointLib[5][3] = new Point(5,1);
		pointLib[6] = new Point[4];
		pointLib[6][0] = new Point(4,0);
		pointLib[6][1] = new Point(3,0);
		pointLib[6][2] = new Point(5,0);
		pointLib[6][3] = new Point(6,0);
	}
	
	
	public Point[] getPoints() {
		return points;
	}
	public void setPoints(Point[] points) {
		this.points = points;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	
	public GameAct() {
		int type = (int) (Math.random() * 7);
		int color = BlockImage.roundColor();
		init(type,color);
	}
	public GameAct(int type) {
		int color = BlockImage.roundColor();
		init(type,color);
	}
	public GameAct(int type,int color) {
		init(type,color);
	}
	private void init(int type,int color) {
		this.type = type;
		this.color =color;
		img = BlockImage.get(color);
		points = new Point[pointLib[type].length];
		for (int i = 0; i < pointLib[type].length; i++) {
			points[i] = new Point(pointLib[type][i].x, pointLib[type][i].y);
		}
	}
	
	
	public boolean move(int dx,int dy,boolean[][] gameMap) {
		if(canMove(dx, dy,gameMap)) {
			doMove(dx, dy);
			return true;
		}
		return false;
	}
	
	private void doMove(int dx,int dy) {
		for (Point point : points) {
			point.translate(dx, dy);
		}
	}
	
	private boolean canMove(int dx, int dy,boolean[][] gameMap) {
		for (Point point : points) {
			int newX = (int)point.getX() +dx;
			int newY = (int)point.getY() +dy;
			if(newX < MIN_X || newX > MAX_X || newY < MIN_Y || newY > MAX_Y || gameMap[newX][newY]) {
				return false;
			}
		}
		return true;
	}
	
	public void rorate() {
		if(type == 5) return;
		int code= rorateBorderTest();
		doRorate();
		moveAfterRorate(code);
	}
	
	private void moveAfterRorate(int code) {
		if(code == -1) return;
		int direct = code % 4;
		int num = code / 4;
		switch (direct) {
		case 0:
			doMove(0, num);
			break;
		case 1:
			doMove(0, -num);
			break;
		case 2:
			doMove(num, 0);
			break;
		case 3:
			doMove(-num, 0);
			break;
		}
	}
	private void doRorate() {
		Point basePoint = points[0];
		int basePointX = (int)basePoint.getX();
		int basePointY = (int)basePoint.getY();
		for (Point point : points) {
			int newX = basePointY + basePointX - point.y;
			int newY = basePointY - basePointX + point.x;
			point.move(newX, newY);
		}
	}
	private int rorateBorderTest() {
		Point basePoint = points[0];
		int basePointX = (int)basePoint.getX();
		int basePointY = (int)basePoint.getY();
		int direct = -1;
		int num = -1;
		for (Point point : points) {
			int newX = basePointY + basePointX - point.y;
			int newY = basePointY - basePointX + point.x;
			if(newY < MIN_Y) {
				direct = 0;
				int temp = MIN_Y - newY;
				num = temp>num?temp:num;
			}else if(newY > MAX_Y) {
				direct = 1;
				int temp  = newY - MAX_Y;
				num = temp>num?temp:num;
			}else if(newX < MIN_X) {
				direct = 2;
				int temp  = MIN_X - newX;
				num = temp>num?temp:num;
			}else if(newX > MAX_X) {
				direct = 3;
				int temp  = newX - MAX_X;
				num = temp>num?temp:num;
			}
		}
		if(direct == -1) {
			return -1;
		}else {
			return (num << 2) + direct;
		}
		
	}
	
}
