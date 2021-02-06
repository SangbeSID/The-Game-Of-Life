package ihm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

/**
 * Cette classe est utilisee pour gerer le plateau de jeu et la zone tampon.
 * Elle herite de JPanel.
 * 
 * @author Sangbe SIDIBE & William FLEURQUIN
 *
 */

public abstract class MyPanel extends JPanel {

	private int taille; // La taille du plateau
	private int grid_size; // La taille de la grille (en nombre de cellules)
	private int cell_size; // La taille des cellules

	public static final int DEFAULT_CELL_SIZE = 5;

		
	/**
	 * Le constructeur
	 * @param t   : La taille du plateau
	 * @param gSize  : La taille de la grille en nombre de cellules par colonne et par ligne
	 * @param cSize : La taille des cellules
	 */
	public MyPanel(int t, int gSize, int cSize) {
		super();
		this.taille = t;  // On definit la taille du plateau
		this.grid_size = gSize;  // On specifie la taille de la grille
		this.cell_size = cSize; // On specifie la taille des cellules
		this.setMinimumSize(new Dimension(this.taille, this.taille)); // On definit la taille Min du plateau
		this.setOpaque(true); // On rend opaque le plateau
		this.addMouseWheelListener(new MyPanelController()); // On lui ajoute le controller.
	}
	
	/**
	 * Methode abstraite permettant de dessiner les cellules.
	 * @param g  : Le graphique sur lequel on dessine
	 */
	public abstract void drawCells(Graphics g);
			
	/**
	 * Methode permettant de modifier la taille de la zone de dessin.
	 * @param t  : Nouvelle taille du plateau
	 */
	public void setDimensionPlateau(int t) {
		if (t > 0) {
			this.taille = t;
		}
	}
	
	/**
	 * Method utilisee pour modifier la taille des cellules.
	 * La modification est effectuee si et seulement la nouvelle
	 * taille est superieure ou egale à la taille par defaut.
	 * @param size : Nouvellle taille des cellules.
	 */
	public void setCellSize(int size) {
		if (size >= DEFAULT_CELL_SIZE)
			this.cell_size = size;
	}

	
	/**
	 * Methode permettant de recuperer la taille de la zone.
	 * @return  : Taille actuelle de la zone
	 */
	public int getTaille() { return this.taille; }

	/**
	 * Methode permettant de recuperer la taille de la grille.
	 * @return  : Taille actuelle de la grille
	 */
	public int getGridSize() { return this.grid_size; }

	/**
	 * Methode permettant de recuperer la taille des cellules.
	 * @return  : Taille actuelle des cellules
	 */
	public int getCellSize() { return this.cell_size; }

	
	/**
	 * Cette classe est utilisee pour gerer le Controller rattachee a la classe MyPanel.
	 * C'est ici, que l'on definit, toutes les specificites liees a MyPanel.
	 * @author Sangbe SIDIBE & William FLEURQUIN
	 */
	private class MyPanelController implements MouseWheelListener{
		/**
		 * On redefinit cette methode afin d'effectuer le ZOOM.
		 */
		@Override
		public void mouseWheelMoved(MouseWheelEvent evt) {
			// On augmente la taille des cellules si l'on roule la molette vers le haut
			// et on la diminue si l'on roule dans le sens inverse.
			MyPanel.this.setCellSize(MyPanel.this.getCellSize() - evt.getWheelRotation());
			MyPanel.this.repaint(); // On repaind la zone.
		}		
	}
	
}
