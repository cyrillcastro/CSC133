package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * MapView is a container that will display game objects and an observable by observer GameWorld. 
 */
public class MapView extends Container implements Observer {
	
	private GameWorld gw;
	
	/**
	 * Constructor for MapView. Registers MapView as an observer to 
	 * the Observable that is passed. 
	 * @param o
	 */
	public MapView(Observable o, GameWorld gw) {
		o.addObserver(this);
		this.gw = gw;
	}

	/**
	 * Method to update the observer. Outputs the game object information to the console. Calls 
	 * outputMap method from GameWorld 
	 */
	@Override
	public void update(Observable observable, Object data) {
		System.out.print("\nOutputting current map...\n");
		((GameWorld) observable).outputMap();
		this.repaint();
	}
	
	/**
	 * Overridden paint method for the Component. Calls the super.paint() method first, then creates
	 * an iterator for the game object collection of the GameWorld. Iterates through the collection, 
	 * and draws each object.  
	 */
	@Override 
	public void paint(Graphics g) {
		super.paint(g);
		IIterator theObjects = gw.getGameObjColl().getIterator();
		
		while (theObjects.hasNext()) {
			GameObject drawObject = theObjects.getNext();
			Point pCmpRelScrn = new Point((int) this.getX(), (int) this.getY());
			drawObject.draw(g, pCmpRelScrn);
		}
	}
	
	/**
	 * Method to press and highlight on Fixed Game Objects that are selected. 
	 * 
	 * If the game is paused, then check if the gameworld can be switched for the position. If so, 
	 * then find the fixedgameobject that is selected and change the location of it when the new location is 
	 * selected. 
	 * 
	 * If the gameworld cannot be switched, wait until the player selects an item and set the boolean to true. 
	 * Else, set it to false. 
	 */
	@Override
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());		
		IIterator selectables;
		
		if (gw.isPaused()) {
			
			if (gw.canSwitchPosition()) {
				selectables = gw.getGameObjColl().getIterator();
				
				while (selectables.hasNext()) {
					GameObject hold = selectables.getNext();				
					if (hold instanceof FixedGameObject && ((FixedGameObject) hold).isSelected()) {
						Point p = new Point(pPtrRelPrnt.getX() - this.getX(), pPtrRelPrnt.getY() - this.getY());
						((FixedGameObject) hold).setLocation(p.getX(), p.getY());
						gw.setSwitchPosition(false);
					}
				}
			} else {
				selectables = gw.getGameObjColl().getIterator();
				
				while (selectables.hasNext()) {
					GameObject hold = selectables.getNext();
					if (hold instanceof FixedGameObject) {
						if (((FixedGameObject) hold).contains(pPtrRelPrnt, pCmpRelPrnt)) 
							((FixedGameObject) hold).setSelected(true);
						else 
							((FixedGameObject) hold).setSelected(false);
					} 
				}
			}			
		}  
		this.repaint();		
	}

}
