package com.zjw.tetris.factory;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.zjw.tetris.config.FrameConfig;
import com.zjw.tetris.config.LayerConfig;
import com.zjw.tetris.controller.AutoController;
import com.zjw.tetris.controller.GameController;
import com.zjw.tetris.controller.PlayerController;
import com.zjw.tetris.dao.PlayDaoBD;
import com.zjw.tetris.dao.PlayDaoLocal;
import com.zjw.tetris.dto.GameDto;
import com.zjw.tetris.service.GameService;
import com.zjw.tetris.ui.GamePanel;
import com.zjw.tetris.ui.lay.Layer;
import com.zjw.tetris.ui.lay.NextLayer;

public class FrameGameFactory {
	
	private static JFrame  gameFrame= null;

	public static JFrame getGameFrame(){
		if(gameFrame == null) {
			createGameFrame();
		} 
		return gameFrame;
	}
	
	private static void createGameFrame() {
		gameFrame = new JFrame();
		FrameConfig fc = new FrameConfig();
		//设置对象属性
		gameFrame.setSize(fc.getWidth(),fc.getHeight());
		double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int  fgx = fc.getX() == null?(int)((screenWidth-gameFrame.getWidth())/2):fc.getX();
		int  fgy = fc.getY() == null?(int)((screenHeight-gameFrame.getHeight())/2-32):fc.getY();
		gameFrame.setLocation(fgx,fgy);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		GamePanel gamePanel = new GamePanel();
		//去除边框
		//fg.setUndecorated(true);
		//设置透明度
		//AWTUtilities.setWindowOpacity(fg, 0.8f);
		//创建gameDto
		GameDto gameDto = new GameDto();
		//创建layers
		List<LayerConfig> layersCfgs = fc.getLayers();
		List<Layer> layers = new ArrayList<Layer>();
		for (int i = 0; i < layersCfgs.size(); i++) {
			LayerConfig lc = layersCfgs.get(i);
			try {
				Class<?> layerClz = Class.forName(lc.getClassName());
				Layer layer = (Layer)layerClz.newInstance();
				layer.setX(lc.getX());
				layer.setY(lc.getY());
				layer.setWidth(lc.getWidth());
				layer.setHeight(lc.getHeight());
				layer.setGameDto(gameDto);
				layers.add(layer);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//创建GameController
		GameController gameController = new GameController();
		//创建GameService
		GameService gameService = new GameService();
		//创建PlayerController
		PlayerController playerController = new PlayerController();
		//创建AutoController
		AutoController autoController = new AutoController();
		//设置依赖关系
		gameController.setGamePanel(gamePanel);
		gameController.setGameService(gameService);
		gameController.setAutoController(autoController);
		gameService.setPlayDaoBD(new PlayDaoBD());
		gameService.setPlayDaoLocal(new PlayDaoLocal());
		gameService.setGameDto(gameDto);
		gameService.initGameDto();
		playerController.setGameController(gameController);
		gamePanel.setGameDto(gameDto);
		gamePanel.setLayers(layers);
		autoController.setGameController(gameController);
		autoController.setGameDto(gameDto);
		NextLayer.setNext(gameDto.getNext());
		//添加事件监听
		gameFrame.addKeyListener(playerController);
		gameFrame.add(gamePanel);
		//启动自动下落线程
		autoController.start();
	}
}
