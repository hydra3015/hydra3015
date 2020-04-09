package com.zjw.tetris.entity;

import java.io.Serializable;
import java.util.Date;

public class Player implements Serializable,Comparable<Player>{

	private static final long serialVersionUID = 7718632509928199136L;
	private String name;
	private Integer score;
	private Date createTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + ", createTime=" + createTime + "]";
	}
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compareTo(Player o) {
		return o.score - this.score;
	}
	public Player(String name, Integer score, Date createTime) {
		super();
		this.name = name;
		this.score = score;
		this.createTime = createTime;
	}
	
	
}
