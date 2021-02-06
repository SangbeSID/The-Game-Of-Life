package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Model;

/**
 * Cette classe est utilisee pour representee la zone tampon.
 * C'est ici que l'on pourra charger les motifs predefinis et une partie
 * du plateau de jeu.
 * Elle herite de MyPanel.
 * 
 * @author Sangbe SIDIBE & William FLEURQUIN
 *
 */

public class ZoneTampon extends MyPanel {

	private Model model;  // Le model se trouvant derriere la zone tampon
	private int size, grid_size; // La taille et le nombre de cellules de la zone tampon

	/**
	 * Le constructeur.
	 * @param s  : Taille de la zone tampon
	 * @param gsize : Nombre de cellules en ligne et en colonne
	 * @param cSize : Taille des cellules
	 */
	public ZoneTampon(int s, int gsize, int cSize) {
		super(s, gsize, cSize); // On fait appel au constructeur de la classe-mere
		this.size = s; // On specifie la taille de la zone tampon
		this.grid_size = gsize; // On specifie le nbre de cellules en hauteur et en largeur
		this.model = new Model(gsize); // on cree le model correspondant a cette zone
		this.setPreferredSize(new Dimension(this.grid_size * cSize, this.grid_size * cSize));
		this.addMouseListener(new ZoneTamponController()); // On lui ajoute le controlleur
	}

	/**
	 * Methode permettant de recuperer le modele qui se trouve derriere la zone tampon
	 * @return  : Le modele de la zone tampon
	 */
	public Model getModel() {
		return this.model;
	}
	
	/**
	 * Methode permettant de copier une partie du plateau de jeu dans la zone tampon.
	 * On recupere le tableau de cellules du modele et l'endroit ou l'on a clique sur
	 * le plateau afin de delimiter la partie a copier.
	 * @param m  : Le modele dont on extrait une partie
	 * @param x  : la position x du coin a gauche
	 * @param y  : La position y du coin a droite
	 */
	public void copyPlateau(Model m, int x, int y){
		this.model.copyPlateau(m.getTabCells(), m.getSize() ,x, y);
		this.repaint(); // On repaint la zone tampon apres la copie de l'echantillon
	}
	
	
	/**
	 * Redefinition de la methode paintComponent
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawCells(g);
	}

	/**
	 * Method utilisee pour dessiner les cellules.
	 * Si la cellule est vivante on la dessine avec un cercle plein de couleur bleue.
	 * Si elle est morte on la dessine avec un rectangle vide de couleur noire.
	 *  (i*c_size ; j*c_size) = position d'une cellule dans le tableau de cellules
	 * @param g  : Le graphic sur lequel on dessine
	 */
	@Override
	public void drawCells(Graphics g) {
		int c_size = this.getCellSize(); // On recupere la taille des cellules
		for (int i = 0; i < this.getGridSize(); i++) {
			for (int j = 0; j < this.getGridSize(); j++) {
				if (this.model.getTabCells()[i][j].getEtat()) { // Si la cellule est vivante
					g.setColor(Color.blue);
					g.fillOval(i * c_size, j * c_size, c_size, c_size);
					
				} else { // Si la cellule est morte
					g.setColor(Color.black);
					g.drawRect(i * c_size, j * c_size, c_size, c_size);
				}
			}
		}
	}
	
	/**
	 * Cette classe est utilisee pour gerer le Controller rattachee a la classe ZoneTampon.
	 * C'est ici, que l'on definit, toutes les specificites liees a cette zone.
	 * 
	 * @author Sangbe SIDIBE & William FLEURQUIN
	 */
	private class ZoneTamponController extends MouseAdapter {
		// Lorsque l'on clique dans la zone tampon avec le bouton gauche de
		// la souris, on recupere la position de la souris, et l'on fait
		// vivre ou mourir la cellule se trouvant a cette position
		@Override
		public void mouseClicked(MouseEvent evt) {
			if (evt.getButton() == MouseEvent.BUTTON1) {
				int x = evt.getX() / ZoneTampon.this.getCellSize(); // Potion x de la souris
				int y = evt.getY() / ZoneTampon.this.getCellSize(); // Position y de la souris
				ZoneTampon.this.getModel().changeState(x, y); // Changement de l'etat de la cellule
				ZoneTampon.this.repaint(); // On repaint la zone tampon
			}
		}
	}
	
}
