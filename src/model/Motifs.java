package model;

/**
 * Cette classe est utilisee pour representer les motifs disponibles
 * dans l'edition de la zone tampon.
 * 
 * @author Sangbe SIDIBE & William FLEURQUIN
 *
 */
public class Motifs {
	public enum Shape {canon, bateau, vaisseau, planner, murHortizontal, murVertical}; // La liste des motifs
	
	private Cellule[][] motif; // Le tableau de cellules correspondant au motif
	public final static int SIZE = 10; // la taille du tableau de cellules
	
	/**
	 * Le constructeur.
	 */
	public Motifs(){
		this.motif = new Cellule[SIZE][SIZE]; // On cree le tableau de cellules
		// Dans chaque case de ce tableau, on cree une cellule
		for(int i=0 ; i<SIZE ; i++){
			for(int j=0 ; j<SIZE ; j++){
				this.motif[i][j] = new Cellule();
			}
		}
		
	}
	
	/**
	 * Methode permettant de recuperer le motif
	 * @return : Le motif actuel
	 */
	public Cellule[][] getMotif(){
		return this.motif;
	}
	
	/**
	 * Cette permet de dessiner le motif choisi dans l'edition de la zone tampon
	 * a travers la liste des motifs.
	 * @param sh   : Le motif a representer
	 */
	public void drawMotif(Shape sh) {
		if(sh == Shape.bateau)
			this.drawBoat();
		else if(sh == Shape.canon)
			this.drawCanon();
		else if(sh == Shape.vaisseau)
			this.drawSpaceShip();
		else if(sh == Shape.planner)
			this.drawPlanner();
		else if(sh == Shape.murHortizontal)
			this.drawHorizontalWall();
		else if(sh == Shape.murVertical)
			this.drawVerticalWall();
	}
	
	
	/**
	 *  Cette methode permet de dessiner le canon
	 */
	public void drawCanon(){
		this.motif[1][4].nait();
		this.motif[1][5].nait();
		this.motif[1][6].nait();
		
		this.motif[2][3].nait();
		this.motif[2][7].nait();
		this.motif[3][2].nait();
		
		this.motif[4][2].nait();
		this.motif[3][8].nait();
		this.motif[4][8].nait();
		
		this.motif[5][5].nait();
		this.motif[6][7].nait();
		this.motif[6][3].nait();
		
		this.motif[7][4].nait();
		this.motif[7][5].nait();
		this.motif[7][6].nait();
		
		this.motif[8][5].nait();
	}
	
	/**
	 *  Cette methode permet de dessiner le bateau
	 */
	public void drawBoat(){
		this.motif[2][4].nait();
		this.motif[3][3].nait();
		this.motif[3][5].nait();
		
		this.motif[4][2].nait();
		this.motif[4][4].nait();
		this.motif[4][6].nait();
		
		this.motif[5][3].nait();
		this.motif[5][5].nait();
		this.motif[6][4].nait();
		this.motif[7][4].nait();
		this.motif[8][3].nait();
		this.motif[8][5].nait();
	}
	
	/**
	 * Methode utilisee pour dessiner un vaisseau.
	 */
	public void drawSpaceShip() {
		this.motif[1][3].nait();
		this.motif[1][4].nait();
		this.motif[1][5].nait();
		
		this.motif[2][2].nait();
		this.motif[2][3].nait();
		this.motif[2][5].nait();
		this.motif[2][6].nait();
		
		this.motif[3][2].nait();
		this.motif[3][3].nait();
		this.motif[3][5].nait();
		this.motif[3][6].nait();
		
		this.motif[4][2].nait();
		this.motif[4][3].nait();
		this.motif[4][4].nait();
		this.motif[4][5].nait();
		this.motif[4][6].nait();
		
		this.motif[5][1].nait();
		this.motif[5][2].nait();
		this.motif[5][6].nait();
		this.motif[5][7].nait();
	}
	
	/**
	 * Methode utilisee pour dessiner un planner
	 */
	public void drawPlanner() {
		this.motif[1][3].nait();
		this.motif[1][4].nait();
		this.motif[1][6].nait();
		this.motif[1][7].nait();
		
		this.motif[2][2].nait();
		this.motif[2][8].nait();
		this.motif[4][1].nait();
		this.motif[4][9].nait();
		
		this.motif[5][1].nait();
		this.motif[5][4].nait();
		this.motif[5][6].nait();
		this.motif[5][9].nait();
		
		this.motif[6][1].nait();
		this.motif[6][2].nait();
		this.motif[6][3].nait();
		this.motif[6][7].nait();
		this.motif[6][8].nait();
		this.motif[6][9].nait();
	}
	
	/**
	 * Cette methode permet de dessiner un mur horizontal.
	 */
	public void drawHorizontalWall() {
		for(int i=0 ; i<SIZE ; i++) {
			for(int j=0 ; j<3 ; j++)
				this.motif[i][j].nait();
		}
	}
	
	/**
	 * Cette methode permet de dessiner un mur vertical.
	 */
	public void drawVerticalWall() {
		for(int i=0 ; i<3 ; i++) {
			for(int j=0 ; j<SIZE ; j++)
				this.motif[i][j].nait();
		}
	}
}
