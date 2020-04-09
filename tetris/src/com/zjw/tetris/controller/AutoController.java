package com.zjw.tetris.controller;

import com.zjw.tetris.dto.GameDto;

public class AutoController extends Thread {

	private GameController gameController;
	private GameDto gameDto;

	public GameDto getGameDto() {
		return gameDto;
	}

	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}
	
	public void run() {
		super.run();
		int gread = 0;
		int speed = 0;
		while(gameDto.isRuning()) {
			if(gread != gameDto.getGread()) {
				gread = gameDto.getGread();
				speed = (int) (-1.0 * (100) * gread + 1100);
			}
			try {
				Thread.sleep(speed);
				gameController.moveDown();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
