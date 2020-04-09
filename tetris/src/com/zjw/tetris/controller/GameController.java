package com.zjw.tetris.controller;

import java.util.List;

import com.zjw.tetris.dto.GameDto;
import com.zjw.tetris.service.GameService;
import com.zjw.tetris.ui.GamePanel;
import com.zjw.tetris.ui.lay.Layer;

public class GameController {

	private GamePanel gamePanel;
	private GameService gameService;
	private AutoController autoController;
	
	

	public AutoController getAutoController() {
		return autoController;
	}

	public void setAutoController(AutoController autoController) {
		this.autoController = autoController;
	}

	public GameService getGameService() {
		return gameService;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}



	public void moveDown() {
		gameService.moveDown();
		gamePanel.repaint();
	}

	public void moveLeft() {
		gameService.moveLeft();
		gamePanel.repaint();
	}

	public void moveRight() {
		gameService.moveRight();
		gamePanel.repaint();
	}
	public void rorate() {
		gameService.rorate();
		gamePanel.repaint();
	}

	public void suspend() {
		gameService.suspend();
		gamePanel.repaint();
		
	}
	
	public void newGame() {
		GameDto gameDto = new GameDto();
		gamePanel.setGameDto(gameDto);
		List<Layer> layers = gamePanel.getLayers();
		for (Layer layer : layers) {
			layer.setGameDto(gameDto);
		}
		gameService.newGame(gameDto);
		if(!autoController.isAlive()) {
			autoController = new AutoController();
			autoController.setGameController(this);
		}
		gamePanel.repaint();
	}

}
