package com.zjw.tetris.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.zjw.tetris.factory.FrameGameFactory;
import com.zjw.tetris.ui.lay.Layer;

public class PlayerController implements KeyListener {

	private GameController gameController;

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_S:
			gameController.moveDown();
			break;
		case KeyEvent.VK_A:
			gameController.moveLeft();
			break;
		case KeyEvent.VK_D:
			gameController.moveRight();
			break;
		case KeyEvent.VK_ENTER:
			gameController.rorate();
			break;
		case KeyEvent.VK_SPACE:
			gameController.suspend();
			break;
		case KeyEvent.VK_SHIFT:
			gameController.newGame();
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
