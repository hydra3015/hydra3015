package com.zjw.tetris.service;

import java.awt.Point;

import com.zjw.tetris.dao.PlayerDao;
import com.zjw.tetris.dto.GameDto;
import com.zjw.tetris.entity.GameAct;
import com.zjw.tetris.ui.SaveRecord;
import com.zjw.tetris.ui.lay.NextLayer;

public class GameService {

	private GameDto gameDto;
	private PlayerDao playDaoBD;
	private PlayerDao playDaoLocal;
	
	public PlayerDao getPlayDaoBD() {
		return playDaoBD;
	}

	public void setPlayDaoBD(PlayerDao playDaoBD) {
		this.playDaoBD = playDaoBD;
	}

	public PlayerDao getPlayDaoLocal() {
		return playDaoLocal;
	}

	public void setPlayDaoLocal(PlayerDao playDaoLocal) {
		this.playDaoLocal = playDaoLocal;
	}

	public GameDto getGameDto() {
		return gameDto;
	}

	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}

	public void initGameDto() {
		try {
			//gameDto.setDbRecord(playDaoBD.find());
			gameDto.setLocalRecord(playDaoLocal.find());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void moveDown() {
		if(!gameDto.isRuning()) return;
		if(gameDto.isSuspend()) return;
		boolean[][] gameMap = gameDto.getGameMap();
		if(!gameDto.getGameAct().move(0, 1,gameMap)) {
			check();
		}
	}
	
	private void check() {
		boolean[][] gameMap = gameDto.getGameMap();
		Point[] points = gameDto.getGameAct().getPoints();
		for (Point point : points) {
			gameMap[point.x][point.y] = true;
		}
		checkClearLine();
		checkAddGread();
		if(checkFail()) {
			failProcess();
		}else {
			gameDto.setGameAct(gameDto.getNext());
			gameDto.setNext(new GameAct());
			NextLayer.setNext(gameDto.getNext());
		}
	}

	private boolean checkFail() {
		boolean[][] gameMap = gameDto.getGameMap();
		int y = 0;
		for (int x = 0; x <= GameAct.MAX_X; x++) {
			if(gameMap[x][y]) {
				return true;
			}
		}
		return false;
		
	}
	
	private void failProcess() {
		gameDto.setRuning(false);
		gameDto.setGameAct(null);
		new SaveRecord(gameDto.getScore(), playDaoBD, playDaoLocal).start();
	}
	
	private void checkAddGread() {
		int score = gameDto.getScore();
		int gread = gameDto.getGread();
		if(score >= gread * 1000) {
			gread++;
		}
		gameDto.setGread(gread);
	}

	public void moveLeft() {
		if(!gameDto.isRuning()) return;
		if(gameDto.isSuspend()) return;
		boolean[][] gameMap = gameDto.getGameMap();
		gameDto.getGameAct().move(-1, 0,gameMap);
	}

	public void moveRight() {
		if(!gameDto.isRuning()) return;
		if(gameDto.isSuspend()) return;
		boolean[][] gameMap = gameDto.getGameMap();
		gameDto.getGameAct().move(1, 0,gameMap);
	}

	public void rorate() {
		if(!gameDto.isRuning()) return;
		if(gameDto.isSuspend()) return;
		gameDto.getGameAct().rorate();
	}
	
	public void checkClearLine() {
		int clearNum = 0;
		int score = gameDto.getScore();
		boolean[][] gameMap = gameDto.getGameMap();
		int maxI = gameMap.length;
		int maxJ = gameMap[0].length;
		for (int j = maxJ - 1; j >= 0 ; j--) {
			boolean flag = true;
			for(int i =0 ;i < maxI;i++) {
				if(!gameMap[i][j]) {
					flag = false;
					break;
				}
			}
			if(flag) {
				for(int m = j ; m > 0 ; m--) {
					for(int n = 0;n <= GameAct.MAX_X;n++) {
						gameMap[n][m] = gameMap[n][m -1];
					}
				}
				clearNum++;
				j++;
			}
		}
		switch (clearNum) {
		case 1:
			score+=100;
			break;
		case 2:
			score+=300;
			break;
		case 3:
			score+=500;
			break;
		case 4:
			score+=800;
			break;
		}
		gameDto.setClearLine(gameDto.getClearLine() + clearNum);
		gameDto.setScore(score);
	}

	public void suspend() {
		gameDto.setSuspend(!gameDto.isSuspend());
	}

	public void newGame(GameDto gameDto) {
		this.gameDto = gameDto;
		initGameDto();
	}
	
}
