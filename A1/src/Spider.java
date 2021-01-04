package com.mycompany.a1;

public class Spider extends MovableGameObject {

	public Spider(int size, float x, float y, int heading, int speed) {
		super(size, x, y, 0, 0, 0, heading, speed);	
	}
	
	@Override
	public void setColor(int r, int g, int b) { }
	
	@Override
	public void move(int heading, int speed) {
		float deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
		float deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
		
		float newX = this.getLocation().getX() + deltaX;
		float newY = this.getLocation().getY() + deltaY;
		
		if (newX <= 0.0 || newX >= 1024.0) {
			if (heading < 180)
				heading += 180;
			else if (heading > 180)
				heading -= 180;
			else if (heading == 180)
				heading = 0;
			
			deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
			deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
			
			newX = this.getLocation().getX() + deltaX;
			newY = this.getLocation().getY() + deltaY;
		}
		
		if (newY <= 0.0 || newY >= 768.0) {
			if (heading < 180)
				heading += 180;
			else if (heading > 180)
				heading -= 180;
			else if (heading == 180)
				heading = 0;
			
			deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speed);
			deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speed);
			
			newX = this.getLocation().getX() + deltaX;
			newY = this.getLocation().getY() + deltaY;
		}
		
		this.setLocation(newX, newY);
	}
	
	@Override
	public String toString() {
		return "Spider: " + this.printLocation() + " " + this.printColor() + " " + this.printHeading() + " " + this.printSpeed() + " " + this.printSize();
	}

}
