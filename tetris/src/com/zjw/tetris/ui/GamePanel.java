package com.zjw.tetris.ui;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import com.zjw.tetris.dto.GameDto;
import com.zjw.tetris.ui.lay.Layer;

public class GamePanel extends JPanel{

	private List<Layer> layers = null;
	
	private GameDto gameDto;

	
	public List<Layer> getLayers() {
		return layers;
	}

	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}

	
	public GameDto getGameDto() {
		return gameDto;
	}

	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Layer layer : layers) {
			layer.paint(g);
		}
	}

}
