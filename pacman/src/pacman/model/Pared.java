package pacman.model;

import java.awt.Color;

/**
 *  Game wall
 *  @version    1.0
 */
public class Pared extends StationaryObject {
	private static final long serialVersionUID = -1931280758910189974L;

	/**
	 * Creates a new wall
	 */
	public Pared() {
		super(null);
		setOpaque(true);
		setBackground(new Color(47,87,255));
	}
	
	/**
	 * Cannot collide with any controllable object
	 */
	@Override
	public boolean isCollidableWith(ControllableObject object) {
		return false;
	}
}
