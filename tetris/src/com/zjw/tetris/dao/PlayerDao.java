package com.zjw.tetris.dao;

import java.util.List;

import com.zjw.tetris.entity.Player;

public interface PlayerDao {

	public void save(Player player) throws Exception;
	public List<Player> find() throws Exception;
}
