package de.hrw.actor.greep;

import java.util.Iterator;

import de.hrw.actor.Ship;
import de.hrw.actor.TomatoPile;

// (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 *
 * @author Uygar Akgül
 * @version 0.1
 */
public class Greep extends Creature {
	// Remember: you cannot extend the Greep's memory. So:
	// no additional fields (other than final fields) allowed in this class!

	/**
	 * Default constructor for testing purposes.
	 */
	public Greep() {
		this(null);
	}

	/**
	 * Create a Greep with its home space ship.
	 */
	public Greep(Ship ship) {
		super(ship);
		setImage("images/greep.png");
	}

	/**
	 * Do what a greep's gotta do.
	 */
	public void act() {
		super.act(); // do not delete! leave as first statement in act().

		if (carryingTomato()) {
			if (atShip()) {
				dropTomato();
				turn(180);
				move();
			} else {

				if (atWater() || atWorldEdge()) {
					turn(75);
					move();
				}
			}
			turnHome();
			move();

		} else {
			checkFood();
		}
		if (atWater() || atWorldEdge()) {
			turn(60);
			move();

		}
		if (!carryingTomato()) {
			return;
		}
	}

	/**
	 * Is there any food here where we are? If so, try to load some!
	 */
	public void checkFood() {
		// check whether there's a tomato pile here
		TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
		if (tomatoes != null) {
			loadTomato();
			// Note: this attempts to load a tomato onto *another* Greep. It
			// won't
			// do anything if we are alone here.
		} else {
			move();
		}
	}

	/**
	 * This method specifies the name of the author (for display on the result
	 * board).
	 */
	public static String getAuthorName() {
		return "matrikelnummer_prename"; // write your name here!
											// (matrikelnummer_prename)
	}

	/**
	 * This method specifies the image we want displayed at any time. (No need to
	 * change this for the competition.)
	 */
	public String getCurrentImage() {
		if (carryingTomato())
			return "images/greep-with-food.png";
		else
			return "images/greep.png";
	}

}