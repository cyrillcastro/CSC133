package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;

/**
 * ScoreView is a container that will display player values (in labels) and an observable by observer GameWorld. 
 */
public class ScoreView extends Container implements Observer {
	
	// Private fields for ScoreView - only consists of labels containing the current player
	// and game values for graphical view. Instantiate to have the labels be blank upon creation
	private Label timeVal = new Label("");
	private Label livesLeftVal = new Label("");
	private Label foodLevelVal = new Label("");
	private Label healthLevelVal = new Label("");
	private Label lastFlagVal = new Label("");
	private Label soundVal = new Label("");
		
	/**
	 * Constructor for ScoreView. Adds ScoreView as an observer. 
	 * 
	 * Then creates the labels and sets the color and padding of them, 
	 * and finally adds it to the container. 
	 * @param o
	 */
	public ScoreView(Observable o) {
		o.addObserver(this);
		
		// For each label, divide it into two parts, text and value. Set the padding for each
		// value label so that the labels stay stable during game play. 
		Label time = new Label("Time:");
		time.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeVal.getAllStyles().setPadding(LEFT, 2);
		timeVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(time);
		this.add(timeVal);

		Label livesLeft = new Label(" Lives Left:");
		livesLeft.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesLeftVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesLeftVal.getAllStyles().setPadding(LEFT, 2);
		livesLeftVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(livesLeft);
		this.add(livesLeftVal);
		
		Label foodLevel = new Label(" Food Level:");
		foodLevel.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodLevelVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodLevelVal.getAllStyles().setPadding(LEFT, 2);
		foodLevelVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(foodLevel);
		this.add(foodLevelVal);
		
		Label healthLevel = new Label(" Health Level:");
		healthLevel.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthLevelVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthLevelVal.getAllStyles().setPadding(LEFT, 2);
		healthLevelVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(healthLevel);
		this.add(healthLevelVal);
		
		Label lastFlag = new Label(" Last Flag Reached:");
		lastFlag.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastFlagVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastFlagVal.getAllStyles().setPadding(LEFT, 2);
		lastFlagVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(lastFlag);
		this.add(lastFlagVal);
		
		Label sound = new Label(" Sound:");
		sound.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundVal.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundVal.getAllStyles().setPadding(LEFT, 2);
		soundVal.getAllStyles().setPadding(RIGHT, 5);
		this.add(sound);
		this.add(soundVal);
	}

	/**
	 * Code to update the value labels for ScoreView based on current game/ant state data. 
	 * Uses set text to change the text of the labels when a change happens in the game
	 * world. 
	 * 
	 * For sound, it checks on the value of the sound boolean of game world. If true,
	 * then sound is ON. Else, sound is OFF. 
	 * 
	 * Also calls display method from GameWorld. 
	 */
	@Override
	public void update(Observable observable, Object data) {
		System.out.print("\nDisplaying current game and player Ant state values...");
		
		timeVal.setText("" + ((GameWorld) observable).getClock());
		livesLeftVal.setText("" + ((GameWorld) observable).getLives());
		foodLevelVal.setText("" + ((GameWorld) observable).getPlayer().getFoodLevel());
		healthLevelVal.setText("" + ((GameWorld) observable).getPlayer().getHealthLevel());
		lastFlagVal.setText("" + ((GameWorld) observable).getPlayer().getLastFlagReached());
	
		String soundValStr;
		if (((GameWorld) observable).soundOn()) 
			soundValStr = "ON";
		else 
			soundValStr = "OFF";
		soundVal.setText("" + soundValStr);
		
		repaint();
		
		((GameWorld) observable).display();
	}

}
