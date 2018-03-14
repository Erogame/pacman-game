package pacman.model;

import java.awt.Dimension;
import java.awt.Point;

/**
 *  El mapa de colisiones del juego, puntos y fantasmas.
 *  @version    1.0
 */
public class Map {
	/* codes map */
	private int[][] _mapaColisiones;
	/* stationary objects map ("Real" objects: JLabels / JPanels) */
	private StationaryObject[][] _objectsMap;
	/* map dimensions */
	private Dimension _dimensionJuego;
	/* cage position (initial position for the monsters) */
	private Point _fantasmasPosicionIni;
	/* pacman initial position */
	private Point _pacmanPosicionIni;
	/* Total de puntos en el mapa actual */
	private int _totalPuntos;
	
	/**
	 * Dibuja un nuevo mapa
	 * @param map given array de codigos
	 * @param fantasmasPosicionIni posicion inicial del fantasma)
	 * @param pacmanPosicionIni pacman posicion inicial
	 */
	protected Map(int[][] map, Point fantasmasPosicionIni, Point pacmanPosicionIni) {
		_mapaColisiones = map;
		_dimensionJuego = new Dimension(_mapaColisiones[0].length, _mapaColisiones.length); // Ancho y Alto de la Matriz Mapa .
		_fantasmasPosicionIni = fantasmasPosicionIni;
		_pacmanPosicionIni = pacmanPosicionIni;
		_objectsMap = new StationaryObject[_dimensionJuego.height][_dimensionJuego.width];
		
		// creating the stationary objects array base on the codes:
		//  0 - Vacio
		//  1 - Pared
		//  2 - Punto pequeño
		//  3 - Punto fantasma
		//  4 - Punto super fantasma
		// -1 - Puerta de escape
		
		
		for (int i = 0; i < _dimensionJuego.height; i++) {
			for (int j = 0; j < _dimensionJuego.width; j++) {
				if (_mapaColisiones[i][j] == 1) {
					_objectsMap[i][j] = new Pared();
				} else if (_mapaColisiones[i][j] == 2) {
					_objectsMap[i][j] = new Punto();
					_totalPuntos++;
				} else if (_mapaColisiones[i][j] == 3) {
					// mighty pill
					_objectsMap[i][j] = new PuntoFantasma();
					_totalPuntos++;
				} else if (_mapaColisiones[i][j] == 4) {
					// super pill
					_objectsMap[i][j] = new SuperPill();
					_totalPuntos++;
				} else if (_mapaColisiones[i][j] == -1) {
					_objectsMap[i][j] = new CageGate();
				} else {
					_objectsMap[i][j] = new StationaryObject(null);
				}
			}
		}
		
	}
	
	public int getTotalPuntos() {
		return _totalPuntos;
	}
	
	public Point getCagePosition() {
		return _fantasmasPosicionIni;
	}
	
	public Point getPacmanInitialPosition() {
		return _pacmanPosicionIni;
	}
	
	public Dimension getGameDimension() {
		return _dimensionJuego;
	}
	
	public int[][] getCollisionMap() {
		return _mapaColisiones;
	}
	
	public StationaryObject[][] getStationaryObjectsMap() {
		return _objectsMap;
	}
	
	public boolean puedeMoverse(ControllableObject object, Point position) {
		return puedeMoverse(object, position.x, position.y);
	}
	
	/**
	 * Determina si el actual objeto puede moverse a la siguente posición
	 * @param object controllable object
	 * @param position target position
	 * @return true si puede ser movido, false si no se puede.
	 */
	public boolean puedeMoverse(ControllableObject object, int x, int y) {
		int height = getGameDimension().height;
		int width = getGameDimension().width;

		// if the target position is out of the panel size
		if (y < 0)
			y = height - 1;
		else if (y >= height)
			y = 0;

		if (x < 0)
			x = width - 1;
		else if (x >= width)
			x = 0;
		
		if (_objectsMap[y][x] != null)
			return _objectsMap[y][x].isCollidableWith(object);
		
		return true;
	}
	
	/**
	 * Retorna el primer nivel del mapa
	 * @return el objeto Map que representa el mapa del primer nivel.
	 */
	public static Map getMapaNivelUno() {
		
		int[][] map = {
				//  0 - Vacio
				//  1 - Pared
				//  2 - Punto pequeño
				//  3 - Punto fantasma
				//  4 - Punto super fantasma
				// -1 - Puerta de escape
				{ 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
				{ 1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1 },
				{ 1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1 },
				{ 1,4,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,4,1 },
				{ 1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1 },
				{ 1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1 },
				{ 1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1 },
				{ 1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1 },
				{ 1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1 },
				{ 1,1,1,1,1,1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,2,1,1,1,1,1,1 },
				{ 0,0,0,0,0,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,0,0,0,0,0 },
				{ 0,0,0,0,0,1,2,1,1,0,1,1,1,-1,-1,1,1,1,0,1,1,2,1,0,0,0,0,0 },
				{ 1,1,1,1,1,1,2,1,1,0,1,0,0,0,0,0,0,1,0,1,1,2,1,1,1,1,1,1 },
				{ 0,0,0,0,0,0,2,0,0,0,1,0,0,0,0,0,0,1,0,0,0,2,0,0,0,0,0,0 }, // PORTAL
				{ 1,1,1,1,1,1,2,1,1,0,1,0,0,0,0,0,0,1,0,1,1,2,1,1,1,1,1,1 },
				{ 0,0,0,0,0,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,0,0,0,0,0 },
				{ 0,0,0,0,0,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,0,0,0,0,0 },
				{ 1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1 },
				{ 1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1 },
				{ 1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1 },
				{ 1,4,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,4,1 },
				{ 1,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,1 },
				{ 1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1 },
				{ 1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1 },
				{ 1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1 },
				{ 1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1 },
				{ 1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1 },
				{ 1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1 }, 
				{ 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 },
				
		};
			
			return new Map(map, new Point(15, 12), new Point(14, 16));
	}
}
