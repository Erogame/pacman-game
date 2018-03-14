package pacman.model;

import javax.swing.ImageIcon;

import pacman.controllers.GameEngine;
import pacman.views.utils.AssetsManager;

/**
 *  Pacman Regular Pill
 *  @version    1.0
 */
public class Punto extends StationaryObject {
	private static final long serialVersionUID = -5179415181539598746L;

	/**
	 * Creates a new pill
	 */
	public Punto() {
		super(new ImageIcon(AssetsManager.getResource(Punto.class, "img.png")));
	}
	
	/**
	 * Handles collision with pacman (kind of visitor pattern)
	 */
	@Override
	public void collideWith(Pacman pacman, GameEngine engine) {
		engine.eatPill(pacman, this);
	}
}
