package com.zjw.tetris.ui;

import java.util.Date;

import javax.swing.JOptionPane;

import com.zjw.tetris.dao.PlayerDao;
import com.zjw.tetris.entity.Player;

public class SaveRecord extends Thread {

	private int score;
	private PlayerDao PlayDaoBD;
	private PlayerDao PlayDaolocal;
	
	public SaveRecord(int score, PlayerDao playDaoBD, PlayerDao playDaolocal) {
		super();
		this.score = score;
		PlayDaoBD = playDaoBD;
		PlayDaolocal = playDaolocal;
	}

	@Override
	public void run() {
		super.run();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String name = JOptionPane.showInputDialog("请输入您的尊姓大名");
		if(!"".equals(name) && name != null) {
			Player player = new Player();
			player.setName(name);
			player.setScore(score);
			player.setCreateTime(new Date());
			try {
				PlayDaoBD.save(player);
				PlayDaolocal.save(player);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
