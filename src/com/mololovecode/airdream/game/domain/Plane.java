package com.mololovecode.airdream.game.domain;

public class Plane {

	private int life;
	private Fire fire;
	private int speed;
	
	public Plane(int life, Fire fire, int speed) {
		super();
		this.life = life;
		this.fire = fire;
		this.speed = speed;
	}

	public int getLife() {
		return life;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
	public Fire getFire() {
		return fire;
	}
	
	public void setFire(Fire fire) {
		this.fire = fire;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public static Plane getDefaultEnemyPlane() {
		return new Plane(1, Fire.getDefaultShipFire(), 280);
	}
}
