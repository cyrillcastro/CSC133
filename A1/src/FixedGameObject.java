package com.mycompany.a1;

public abstract class FixedGameObject extends GameObject {
	
	public FixedGameObject(int size, float x, float y, int r, int g, int b) {
		super(size, x, y, r, g, b);
	}
	
	@Override
	public void setLocation(float x, float y) { }
	
}
