package pacman.model;

import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.views.utils.AssetsManager;

/**
 *  Regular Pacman
 *  @version    1.0
 */
public class Pacman extends ControllableObject implements Eater {
	private static final long serialVersionUID = 8644963962014952922L;
	/* Pacman current direction */
	private Direction _directionX = Direction.DERECHA;

	/**
	 * Creates a new Pacman with a given collisions map
	 * @param map collisions map
	 */
	public Pacman(Map map) {
		super(map);
		setPosition(map.getPacmanInitialPosition().x, map.getPacmanInitialPosition().y);
		setDirection(Direction.NONE);
	}
	
	@Override
	public void setDirection(Direction direction) {
		//Cambia la direccion de la imagendel pacman (set angulo 90 o 270, o setea imagen hacia la derecha o izquierda)
		switch(direction) {
		case ABAJO:
			if (_directionX == Direction.DERECHA)
				setSpriteAngle(90);
			else if (_directionX == Direction.IZQUIERDA)
				setSpriteAngle(270);
			startAnimation();
			break;
		case IZQUIERDA:
			try {
				setSpriteImage(ImageIO.read(AssetsManager.getResource(this.getClass(), "pacman-left.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			_directionX = Direction.IZQUIERDA;
			setSpriteAngle(0);
			startAnimation();
			break;
		case NONE:
			if (getSpriteImage() == null) {
				setDefaultIcon();
			}
			stopAnimation();
			break;
		case DERECHA:
			try {
				setSpriteImage(ImageIO.read(AssetsManager.getResource(this.getClass(), "pacman-right.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			_directionX = Direction.DERECHA;
			setSpriteAngle(0);
			startAnimation();
			break;
		case ARRIBA:
			if (_directionX == Direction.DERECHA)
				setSpriteAngle(270);
			else if (_directionX == Direction.IZQUIERDA)
				setSpriteAngle(90);
			startAnimation();
			break;
		default:
			break;
		
		}
		
		super.setDirection(direction);
	}
	
	/**
	 * Pacman die - change the pacman image
	 */
	public void die() {
		try {
			setSpriteImage(ImageIO.read(AssetsManager.getResource(this.getClass(), "pacman-death.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * set the pacman to its default icon
	 */
	public void setDefaultIcon() {
		try {
			// default image
			setSpriteImage(ImageIO.read(AssetsManager.getResource(this.getClass(), "pacman-right.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Determine if pacman can eat the given monster (using Visitor pattern)
	 * @param monster
	 * @return true if yes, otherwise not.
	 */
	public boolean eats(Monster monster) {
		return monster.eatenBy(this);
	}

	/**
	 * Regular pacman cannot eat weak monster
	 */
	@Override
	public boolean eats(WeakMonster monster) {
		return false;
	}

	/**
	 * Regular pacman cannot eat strong monster
	 */
	@Override
	public boolean eats(StrongMonster monster) {
		return false;
	}
	

}
