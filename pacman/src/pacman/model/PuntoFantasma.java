package pacman.model;

import javax.swing.ImageIcon;

import pacman.controllers.GameEngine;
import pacman.views.utils.AssetsManager;

/**
 *  Mighty Pill
 *  @version    1.0
 */
public class PuntoFantasma extends Punto {
	private static final long serialVersionUID = -1096374292609342594L;

	/**
	 * Creates a new mighty pill
	 */
	public PuntoFantasma() {
		super();
		setIcon(new ImageIcon(AssetsManager.getResource(PuntoFantasma.class, "img.png")));
	}
	
	/**
	 * Handles collision with pacman (kind of visitor pattern)
	 */
	@Override
	public void collideWith(Pacman pacman, GameEngine engine) {
		engine.eatMightyPill(pacman, this);
	}
}
