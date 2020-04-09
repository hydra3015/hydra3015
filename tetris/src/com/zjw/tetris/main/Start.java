package com.zjw.tetris.main;

import javax.swing.JFrame;

import com.zjw.tetris.factory.FrameGameFactory;

import java.net.URL;

public class Start {

	public static void main(String[] args) {
		JFrame FrameGame = FrameGameFactory.getGameFrame();
		FrameGame.setVisible(true);
	}
}
