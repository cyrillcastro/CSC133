package com.mycompany.a1;

public class Flag extends FixedGameObject {
	
	private int sequenceNumber;
	
	public Flag(float x, float y, int sequenceNumber) {
		super(10, x, y, 0, 0, 255);	
		this.sequenceNumber = sequenceNumber;
	}
	
	@Override
	public void setColor(int r, int g, int b) { }

	public int getSequenceNumber() {
		return this.sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	} 
	
	@Override
	public String toString() {
		return "Flag: " + this.printLocation() + " " + this.printColor() + " " + this.printSize() + " seqNum=" + this.sequenceNumber;
	}

}
