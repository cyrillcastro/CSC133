package com.mycompany.a1;

public abstract class MovableGameObject extends GameObject {
	
	private int heading;
	private int speed;
	
	public MovableGameObject(int size, float x, float y, int r, int g, int b, int heading, int speed) {
		super(size, x, y, r, g, b);
		this.heading = heading;
		this.speed = speed;
	}

	public int getHeading() {
		return this.heading;
	}

	public void setHeading(int heading) {
		if (heading >= 0 && heading <= 360)
			this.heading = heading;
	}
	
	public String printHeading() {
		return "heading=" + this.heading;
	}

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public String printSpeed() {
		return "speed=" + this.speed;
	}
	
	public abstract void move(int heading, int speed);		
	
}
