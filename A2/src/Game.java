package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

/**
 * Game class constructs the Game. Game is an extension of a 
 * form. 
 */
public class Game extends Form {
	
	// private fields - every Game has a game world, MapView, ScoreView 
	private GameWorld gw;
	private MapView mv;			// new in A2
	private ScoreView sv;		// new in A2
	
	/**
	 * Constructor for the Game. Creates and initializes the game world.
	 * Calls the play method so that player can "play" the game. 
	 */
	public Game() {
		// Initialize the private fields 
		gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView(gw);
		
		// Add observers to the Observable - follows Observer pattern
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		// Set the layout of the overall game form to BorderLayout 
		this.setLayout(new BorderLayout());
		
		// Create toolbar and title the game 
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		Toolbar.setOnTopSideMenu(false);
		myToolbar.setTitle("BugZ Game");
		
		/*
		 * Code here to create Command objects for each command,
		 * add commands to side menu and title bar area, bind commands to 
		 * keys, create control containers for buttons, add buttons to the 
		 * control containers, add commands to the buttons, and add control 
		 * containers, MapView, and ScoreView to the form 
		 */
		
		// Create and adjust the appearances of each of the containers
		mv.getAllStyles().setBgTransparency(255);
		mv.getAllStyles().setBgColor(ColorUtil.WHITE);
		mv.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255, 0, 0)));
				
		sv.getAllStyles().setPadding(Component.LEFT, 365);
		sv.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));
		
		Container left = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		left.getAllStyles().setPadding(Component.TOP, 100);
		left.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));

		Container right = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		right.getAllStyles().setPadding(Component.TOP, 100);
		right.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));
		
		Container bottom = new Container(new BoxLayout(BoxLayout.X_AXIS));
		bottom.getAllStyles().setPadding(Component.LEFT, 450);
		bottom.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));
		
		// Creating the command objects to allocated containers and appropriate 
		// key binding 
		Command accelerate = new AccelerateCommand(gw);
		addKeyListener('a', accelerate);
		
		Command brake = new BrakeCommand(gw);
		addKeyListener('b', brake);
		
		Command leftTurn = new LeftTurnCommand(gw);
		addKeyListener('l', leftTurn);
		
		Command rightTurn = new RightTurnCommand(gw);
		addKeyListener('r', rightTurn);
		
		Command flagCollide = new FlagCollideCommand(gw);
		
		Command foodStationCollide = new FoodStationCollideCommand(gw);
		addKeyListener('f', foodStationCollide);
		
		Command spiderCollide = new SpiderCollideCommand(gw);
		addKeyListener('g', spiderCollide);
		
		Command tick = new TickCommand(gw);
		addKeyListener('t', tick);
		
		Command exit = new ExitCommand(gw);
		addKeyListener('x', exit);
		
		Command sound = new SetSoundCommand(gw);
		CheckBox soundBox = new CheckBox("Sound");
		soundBox.setCommand(sound);
		soundBox.getAllStyles().setBgTransparency(255);
		soundBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		
		Command about = new GiveAboutCommand();
		
		Command help = new GiveHelpCommand();
		
		// Add commands to side menu and toolbar
		myToolbar.addComponentToSideMenu(soundBox);
		myToolbar.addCommandToSideMenu(accelerate);
		myToolbar.addCommandToSideMenu(about);
		myToolbar.addCommandToSideMenu(help);
		myToolbar.addCommandToRightBar(help);
		myToolbar.addCommandToSideMenu(exit);
		
		// Create buttons for the command objects, then add to their allocated
		// containers 
		CommandButton accelButton = new CommandButton();
		accelButton.setCommand(accelerate);
		left.add(accelButton);
		
		CommandButton leftTurnButton = new CommandButton();
		leftTurnButton.setCommand(leftTurn);
		left.add(leftTurnButton);
		
		CommandButton brakeButton = new CommandButton();
		brakeButton.setCommand(brake);
		right.add(brakeButton);
		
		CommandButton rightTurnButton = new CommandButton();
		rightTurnButton.setCommand(rightTurn);
		right.add(rightTurnButton);
		
		CommandButton flagCollideButton = new CommandButton();
		flagCollideButton.setCommand(flagCollide);
		bottom.add(flagCollideButton);
		
		CommandButton spiderCollideButton = new CommandButton();
		spiderCollideButton.setCommand(spiderCollide);
		bottom.add(spiderCollideButton);
		
		CommandButton foodStationCollideButton = new CommandButton();
		foodStationCollideButton.setCommand(foodStationCollide);
		bottom.add(foodStationCollideButton);
		
		CommandButton tickButton = new CommandButton();
		tickButton.setCommand(tick);
		bottom.add(tickButton);
		
		// Add the containers to their allocated region 
		this.add(BorderLayout.CENTER, mv)
			.add(BorderLayout.NORTH, sv)
			.add(BorderLayout.WEST, left)
			.add(BorderLayout.EAST, right)
			.add(BorderLayout.SOUTH, bottom);
		
		this.show();
		
		/*
		 * Code here to query MapView's width and height and set them as 
		 * world's width and height 
		 */
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
		      
		// Initialize world 
		gw.init();			
		
		// Once the world is initialized, update the observers 
		sv.update(gw, gw.getPlayer());
		mv.update(gw, gw.getGameObjColl());
	}
	

}
