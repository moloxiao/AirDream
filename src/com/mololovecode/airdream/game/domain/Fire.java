package com.mololovecode.airdream.game.domain;

public class Fire {
	
	public static final int POWER_LEVEL_1 = 1;
	public static final int SPEED_LEVEL_1 = 600;
	
	public static final int WIDTH = 90;
	public static final int HEIGHT = 39;

	private int power;
	private int speed;

	public Fire(int power, int speed) {
		super();
		this.power = power;
		this.speed = speed;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public static Fire getDefaultShipFire() {
		Fire fire = new Fire(Fire.POWER_LEVEL_1, Fire.SPEED_LEVEL_1);
		return fire;
	}
 }
