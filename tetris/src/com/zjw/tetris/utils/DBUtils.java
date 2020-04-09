package com.zjw.tetris.utils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zjw.tetris.config.DBConfig;

public class DBUtils {

	private Connection connection;
	public Connection getConnection() {
		if(connection == null) {
			init();
		}
		return connection;
	}
	
	private void init() {
		DBConfig dbConfig = new DBConfig();
		try {
			Class.forName(dbConfig.getClassName());
			connection = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public PreparedStatement getPreparedStatement(String sql) {
		if(connection == null) {
			init();
		}
		PreparedStatement ps =  null;
		try {
			ps = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}
	
	public void close(AutoCloseable... closeables) {
		for (AutoCloseable closeable : closeables) {
			if (closeable != null) {
				try {
					closeable.close();
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
