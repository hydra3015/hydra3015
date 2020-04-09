package com.zjw.tetris.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.zjw.tetris.entity.Player;

public class PlayDaoLocal implements PlayerDao {

	private static List<Player> players;
	static {
		initPlayers();
	}
	
	public static void initPlayers() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("record");
		File record = null;
		if(url != null){
			record = new File(url.getFile());
		}
		if((record == null || !record.exists()) && players == null) {
			players = new ArrayList<Player>();
		}else {
			FileInputStream fis = null;
			ObjectInputStream ois  = null;
			try {
				fis = new FileInputStream(record);
				ois = new ObjectInputStream(fis);
				players = (List<Player>)ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					ois.close();
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	
	
	@Override
	public void save(Player player) throws Exception {
		players.add(player);
		File record = new File("record");
		if(!record.exists()) record.createNewFile();
		FileOutputStream fos = new FileOutputStream(record);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(players);
		oos.flush();
		oos.close();
		fos.close();
		initPlayers();
	}

	@Override
	public List<Player> find() throws Exception {
		List<Player> temp;
		Collections.sort(players);
		if (players.size() >= 5){
			temp = players.subList(0, 5);
		}else{
			temp = players.subList(0, players.size());
		}
		return temp;
	}

}
