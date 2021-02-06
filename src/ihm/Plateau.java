package ihm;

import model.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 * Cette classe est utilisee pour gerer le plateau du jeu.
 * Elle herite de MyPanel.
 * 
 * @author Sangbe SIDIBE & William FLEURQUIN
 *
 */
public class Plateau extends MyPanel {
	private Model model;  // Le model du plateau de jeu
	private boolean isRunning = false;  // Etat indiquant si le plateau est active ou non
	private int gridSize, cellSize; // Taille de la grille des cellules, et des cellules

	public static final int DEFAULT_CELL_SIZE = 5; // Taille par defaut des cellules
	private static final int MAX_GRID_SIZE = 100; // Taille maximale de la grille des cellules
	
	/**
	 * Le constructeur de la classe Plateau.
	 * @param size  : Dimension du plateau
	 * @param gSize : Taille de la grille des cellules en ligne et en colonnes
	 * @param cSize : Taille d'une cellule
	 */
	public Plateau(int size, int gSize, int cSize) {
		super(size, gSize, cSize); // On fait appel au constructeur de la classe mere
		this.gridSize = gSize; // On definit la taille de la grille des cellules
		// On definit la taille des cellules
		if(cSize > DEFAULT_CELL_SIZE)
			this.cellSize = cSize;
		else
			this.cellSize = DEFAULT_CELL_SIZE;
		// On definit la dimension du plateau
		this.setPreferredSize(new Dimension(this.gridSize * this.cellSize, this.gridSize * this.cellSize));
		this.setBorder(BorderFactory.createLineBorder(Color.black)); // On lui met une bordure
		
		this.model = new Model(this.gridSize); // On cree le model correpondant avec le taille de la grille
		this.model.generate(); // On genere les cellules
		this.addMouseListener(new PlateauController()); // On lui ajoute le controlleur
	}
	
	/**
	 * Methode utilisee pour recuperer la taille de la grille des cellules.
	 * @return : Taille actuelle de la grille des cellules
	 */
	public int getGridSize() { 
		return this.gridSize; 
	}

	/**
	 * Methode permettant de recupere le modele du plateau.
	 * @return  : Le modele derriere le plateau
	 */
	public Model getModel() {
		return this.model;	
	}

	/**
	 * Methode permettant de modifier la taille des cellules de la grille du plateau.
	 * @param size : Nouvelle taille des cellules
	 */
	public void setCellSize(int size) {
		// La nouvelle est prise en compte ssi elle superieure
		// ou egale a la taille par defaut
		if (size >= DEFAULT_CELL_SIZE)
			this.cellSize = size;
	}

	/**
	 * Methode permettant de recuperer la taille des cellules de la grille du plateau.
	 * @return : Taille des cellules
	 */
	public int getCellSize(){ 
		return this.cellSize;
	}
	
	
	/**
	 * Methode utilisee pour modifier la taille de la grille des celllules.
	 * @param size  : Nouvelle Taille de la grille des cellules
	 */
	public void setSizeOfDesignArea(int size) {
		// La modification est prise en compte ssi la taille est positive
		// et si elle est inferieure ou egale a la taille maximale
		if (size > 0 && size <= MAX_GRID_SIZE) {
			this.gridSize = size; // on applique la nouvelle taille
			// On redefinit la taille des cellules pour que la grille s'adapte
			// la taille du plateau
			this.setCellSize((MAX_GRID_SIZE/size) * DEFAULT_CELL_SIZE);			
			this.model = new Model(size); // On reinitialise le model du jeu avec la nouvelle taille.
		}
		else{
			JOptionPane.showMessageDialog(null,	"Taille incorrecte. Veuillez entrer une valeur entre 1 et 100");
		}
	}

	/**
	 * Methode permettant de generer ou de tuer les cellules.
	 * Cette methode fait appel a la methode nextStep() du model
	 * qui genere  ou tue les cellules selon plusieurs configurations.
	 */
	public void nextStep() {
		this.model.nextStep(); // On fait appel a la methode du model
		this.repaint(); // On repaint le plateau
	}

	/**
	 * Methode permettant d'activer le plateau ==> Possibilite de representer
	 * des cellules.
	 */
	public void enableTheBoard() {
		this.isRunning = true;
	}

	/**
	 * Methode permettant de desactiver le plateau ==> Impossibilite de representer
	 * des cellules.
	 */
	public void disableTheBoard() {
		this.isRunning = false; 
		this.repaint(); // on repaint le plateau.
	}

	/**
	 * Methode permettant de recuperer le statut du plateau.
	 * @return : Etat du plateau, active ou non
	 */
	public boolean getStatus() { 
		return this.isRunning; 
	}

	/**
	 * Methode permettant de copier la zone tampon sur la grille du plateau.
	 * On recupere le tableau de cellules du modele de la zone tampon
	 * et l'endroit ou l'on a clique sur le plateau afin de definir l'endroit ou copier.
	 * @param m  : Le modele dont on copie les cellules
	 * @param x  : la position x du coin a gauche
	 * @param y  : La position y du coin a droite
	 */
	public void copyZoneTampon(Model m, int x, int y){
		// On fait appel a lamethode permettant de copier un model avec la taille
		// de du modele de la zone tampon.
		this.model.copyModel(m.getTabCells(), m.getSize() ,x, y);
		this.repaint(); // On repaint le plateau.
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
	 * Le cellules mortes sont representees que si le jeu ne tourne pas (plateau desactive).
	 *  (i*c_size ; j*c_size) = position d'une cellule dans le tableau de cellules
	 *  
	 * @param g  : Le graphic sur lequel on dessine.
	 */
	public void drawCells(Graphics g) {
		int c_size = this.getCellSize(); // On recupere la tailles des cellules
		for (int i = 0; i < this.getGridSize(); i++) {
			for (int j = 0; j < this.getGridSize(); j++) {
				
				if (this.isRunning){ // Si le jeu tourne 
					if (this.model.getTabCells()[i][j].getEtat()) {
						g.setColor(Color.green); // On dessine les cellules avec la couleur verte
						g.fillOval(i * c_size, j * c_size, c_size, c_size);
					}
				} else { // Si le jeu ne tourne pas
					if (this.model.getTabCells()[i][j].getEtat()) {
						g.setColor(Color.blue);
						g.fillOval(i * c_size, j * c_size, c_size, c_size);
					} else {
						g.setColor(Color.black);
						g.drawRect(i * c_size, j * c_size, c_size, c_size);
					}
				}
			}
		}
	}

	

	/**
	 * Cette classe est utilisee pour gerer le Controller rattachee a la classe Plateau.
	 * C'est ici, que l'on definit, toutes les specificites liees a cette zone.
	 * 
	 * @author Sangbe SIDIBE & William FLEURQUIN
	 */
	private class PlateauController extends MouseAdapter {
		// Lorsque le plateau est desactive, c-a-d, lorsque le jeu ne tourne pas
		// Si l'on clique dans la grille du plateau avec le bouton gauche de
		// la souris, on recupere la position de la souris, et l'on fait
		// vivre ou mourir la cellule se trouvant a cette position
		@Override
		public void mouseClicked(MouseEvent evt) {
			if(!isRunning) { // Si le jeu ne tourne pas
				if (evt.getButton() == MouseEvent.BUTTON1) { // Si c'est le bouton gauche de la souris
					int x = evt.getX() / Plateau.this.getCellSize(); // Position x de la souris 
					int y = evt.getY() / Plateau.this.getCellSize(); // Position y de la souris
					Plateau.this.getModel().changeState(x, y); // On change l'etat de la cellule
					Plateau.this.repaint(); // On repaint le plateau.
				}
			}
		}
	}
}
