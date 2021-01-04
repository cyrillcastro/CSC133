package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * Game class constructs the Game. Game is an extension of a 
 * form. 
 */
public class Game extends Form implements Runnable {
	
	// private fields - every Game has a game world, MapView, ScoreView 
	private GameWorld gw;
	private MapView mv;	
	private ScoreView sv;	
	
	// command objects
	private AccelerateCommand accelerate;
	private BrakeCommand brake;
	private LeftTurnCommand leftTurn;
	private RightTurnCommand rightTurn;
	private ExitCommand exit;
	private SetSoundCommand sound;
	private PositionCommand position;
	private GiveHelpCommand help;
	private GiveAboutCommand about;
	private PauseCommand setPause;
	
	// Command Buttons 
	private CommandButton accelButton;
	private CommandButton leftTurnButton;
	private CommandButton brakeButton;
	private CommandButton rightTurnButton;
	private CommandButton positionButton;
	private CommandButton setModeButton;
	
	// checkbox for sound
	private CheckBox soundBox;
	
	// timer 
	private UITimer timer = new UITimer(this);
		
	/**
	 * Constructor for the Game. Creates and initializes the game world.
	 * Calls the play method so that player can "play" the game. 
	 */
	public Game() {
		// Initialize the private fields 
		gw = new GameWorld();
		mv = new MapView(gw, gw);
		sv = new ScoreView(gw);
		
		// Add observers to the Observable - follows Observer pattern
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		// Set the layout of the overall game form to BorderLayout 
		this.setLayout(new BorderLayout());
		
		// Create Toolbar and title the game 
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
				
		sv.getAllStyles().setPadding(Component.LEFT, 150);
		sv.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));
		
		Container left = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		left.getAllStyles().setPadding(Component.TOP, 100);
		left.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));

		Container right = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		right.getAllStyles().setPadding(Component.TOP, 100);
		right.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));
		
		Container bottom = new Container(new BoxLayout(BoxLayout.X_AXIS));
		bottom.getAllStyles().setPadding(Component.LEFT, 800);
		bottom.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.LTGRAY));
		
		// Instantiate the command objects
		accelerate = new AccelerateCommand(gw);
		brake = new BrakeCommand(gw);
		leftTurn = new LeftTurnCommand(gw);
		rightTurn = new RightTurnCommand(gw);
		exit = new ExitCommand(gw);
		
		sound = new SetSoundCommand(gw, this);
		soundBox = new CheckBox("Sound");
		soundBox.setCommand(sound);
		soundBox.getAllStyles().setBgTransparency(255);
		soundBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundBox.setSelected(true);
		
		about = new GiveAboutCommand();
		
		help = new GiveHelpCommand();
		
		position = new PositionCommand(gw);
		
		// Add commands to side menu and toolbar
		myToolbar.addComponentToSideMenu(soundBox);
		myToolbar.addCommandToSideMenu(accelerate);
		myToolbar.addCommandToSideMenu(about);
		myToolbar.addCommandToSideMenu(help);
		myToolbar.addCommandToRightBar(help);
		myToolbar.addCommandToSideMenu(exit);
		
		// Create buttons for the command objects, then add to their allocated
		// containers 
		accelButton = new CommandButton();
		accelButton.setCommand(accelerate);
		left.add(accelButton);
		
		leftTurnButton = new CommandButton();
		leftTurnButton.setCommand(leftTurn);
		left.add(leftTurnButton);
		
		brakeButton = new CommandButton();
		brakeButton.setCommand(brake);
		right.add(brakeButton);
		
		rightTurnButton = new CommandButton();
		rightTurnButton.setCommand(rightTurn);
		right.add(rightTurnButton);
		
		positionButton = new CommandButton();
		positionButton.setCommand(position);
		bottom.add(positionButton);
		
		// for the play/pause mode button and command, add an action listener to
		// the button to set the text 
		setModeButton = new CommandButton();
		setPause = new PauseCommand(gw, setModeButton);
		setModeButton.setCommand(setPause);
		setModeButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (setModeButton.getText().equals("Pause"))
					setModeButton.setText("Play");
				else 
					setModeButton.setText("Pause");
			}
		});
		bottom.add(setModeButton);
		
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
		
		// Instantiate the Timer and schedule it 
		timer.schedule(40, true, this);
	}

	/**
	 * Run method on the game for the timer, calls tick method from GameWorld 
	 * 
	 * If the game is paused, then it disables and enables the necessary commands. Else, enable and disable the same 
	 * commands. It also plays the background sound as long as the sound is on and the game is not paused
	 */
	@Override
	public void run() {
		Dimension dCmpSize = new Dimension(gw.getWidth(), gw.getHeight());
		
		gw.tickClock(40, dCmpSize);
		
		if (gw.isPaused()) {
			this.disableCommands();
			this.positionButton.setEnabled(true);
			gw.getBgSound().pause();
		} else {
			this.enableCommands();
			this.positionButton.setEnabled(false);
			if (gw.soundOn()) 
				gw.getBgSound().play();
		}
	}
	
	/**
	 * Method that enables all the appropriate commands when in play mode 
	 */
	public void enableCommands() {
		this.addKeyListener('a', accelerate);
		this.addKeyListener('b', brake);
		this.addKeyListener('l', leftTurn);
		this.addKeyListener('r', rightTurn);
		this.addKeyListener('x', exit);
		this.addKeyListener('p', setPause);		// don't disable this key listener
		
		this.accelerate.setEnabled(true);
		this.soundBox.setEnabled(true);
		this.accelButton.setEnabled(true);
		this.brakeButton.setEnabled(true);
		this.leftTurnButton.setEnabled(true);
		this.rightTurnButton.setEnabled(true);
	}
	
	/**
	 * Method that disables all the appropriate commands when in pause mode 
	 */
	public void disableCommands() {
		this.removeKeyListener('a', accelerate);
		this.removeKeyListener('b', brake);
		this.removeKeyListener('l', leftTurn);
		this.removeKeyListener('r', rightTurn);
		this.removeKeyListener('x', exit);
		
		this.accelerate.setEnabled(false);
		this.soundBox.setEnabled(false);
		this.accelButton.setEnabled(false);
		this.brakeButton.setEnabled(false);
		this.leftTurnButton.setEnabled(false);
		this.rightTurnButton.setEnabled(false);
	}
}
