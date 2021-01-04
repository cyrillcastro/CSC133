package com.mycompany.a2;

/**
 * Flag class constructs Flag objects. Flags are fixed game objects
 * with a sequenceNumber. 
 */
public class Flag extends FixedGameObject {
	
	// private field exclusive for Flags
	private int sequenceNumber;
	
	/**
	 * Flag constructor, inherits from FixedGameObject. Also 
	 * instantiates sequenceNumber of the class.
	 * 
	 * @param x
	 * @param y
	 * @param sequenceNumber
	 */
	public Flag(float x, float y, int sequenceNumber) {
		super(10, x, y, 0, 0, 255);	
		this.sequenceNumber = sequenceNumber;
	}
	
	/**
	 * Flags cannot change color once created. Method overrides setColor
	 * from GameObject to fit this requirement. 
	 */
	@Override
	public void setColor(int r, int g, int b) { }

	/**
	 * Getter method for sequenceNumber.
	 * @return sequenceNumber
	 */
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}
	
	/**
	 * Setter method for sequenceNumber. 
	 * @param sequenceNumber
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	} 
	
	/**
	 * Overridden toString method to print Flag values for map. 
	 */
	@Override
	public String toString() {
		return "Flag: " + this.printLocation() + " " + this.printColor() + " " + this.printSize() + " seqNum=" + this.sequenceNumber;
	}

}
