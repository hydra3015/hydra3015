package com.zjw.tetris.dto;

import java.util.List;

import com.zjw.tetris.entity.GameAct;
import com.zjw.tetris.entity.Player;

public class GameDto {

	private List<Player> dbRecord;
	private List<Player> localRecord;
	private boolean[][] gameMap;
	private GameAct gameAct;
	private GameAct next;
	private int score;
	private int clearLine;
	private int gread;
	private int current;
	private boolean suspend;
	public boolean isRuning() {
		return runing;
	}
	public void setRuning(boolean runing) {
		this.runing = runing;
	}
	private boolean runing;
	
	public GameAct getGameAct() {
		return gameAct;
	}
	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}
	public List<Player> getDbRecord() {
		return dbRecord;
	}
	public void setDbRecord(List<Player> dbRecord) {
		this.dbRecord = dbRecord;
	}
	public List<Player> getLocalRecord() {
		return localRecord;
	}
	public void setLocalRecord(List<Player> localRecord) {
		this.localRecord = localRecord;
	}
	public boolean[][] getGameMap() {
		return gameMap;
	}
	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getClearLine() {
		return clearLine;
	}
	public void setClearLine(int clearLine) {
		this.clearLine = clearLine;
	}
	public int getGread() {
		return gread;
	}
	public void setGread(int gread) {
		this.gread = gread;
	}
	
	public GameAct getNext() {
		return next;
	}
	public void setNext(GameAct next) {
		this.next = next;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public boolean isSuspend() {
		return suspend;
	}
	public void setSuspend(boolean suspend) {
		this.suspend = suspend;
	}
	public GameDto() {
		super();
		init();
	}
	private void init() {
		gameMap = new boolean[10][18];
		gameAct = new GameAct();
		gread = 1;
		next = new GameAct();
		runing = true;
	}
	
}
