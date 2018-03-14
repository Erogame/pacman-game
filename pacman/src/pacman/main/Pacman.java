package pacman.main;

import javax.swing.SwingUtilities;

import pacman.controllers.GameEngine;

/**
 *  Pacman - 2014
 *  Este juego es parte de la asignatura de la Programacion orientada a objetos - UNED 
 *   
 */
public class Pacman {

	public static void main(String[] args) {
		// Inicia el juego corriendo inicializando el objeto GameEngine 
		SwingUtilities.invokeLater(new GameEngine());
	}

}
