package com.zjw.tetris.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zjw.tetris.entity.Player;
import com.zjw.tetris.utils.DBUtils;

public class PlayDaoBD implements PlayerDao {

	@Override
	public void save(Player player) throws Exception {
		DBUtils dbUtils = new DBUtils();
		String sql = "insert into player values (null,?,?,?)";
		PreparedStatement ps = dbUtils.getPreparedStatement(sql);
		ps.setString(1, player.getName());
		ps.setInt(2, player.getScore());
		ps.setObject(3, new Date());
		ps.execute();
		dbUtils.close(ps);
	}

	@Override
	public List<Player> find() throws Exception {
		DBUtils dbUtils = new DBUtils();
		String sql = "select * from player order by score desc limit 0 , 5";
		PreparedStatement ps = dbUtils.getPreparedStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Player> players = new ArrayList<Player>();
		while(rs.next()) {
			Player player = new Player();
			player.setName(rs.getString("name"));
			player.setScore(rs.getInt("score"));
			player.setCreateTime(rs.getDate("time"));
			players.add(player);
		}
		return players;
	}

}
