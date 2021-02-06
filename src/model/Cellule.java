package model;

/**
 * Cette classe est utilisee pour gerer les cellules.
 * 
 * @author Sangbe SIDIBE & William FLEURQUIN
 *
 */
public class Cellule {

	private boolean etat; // L'etat de la cellule
	public static final boolean DEFAULT_STATE = false; // L'etat par defaut des cellules

	/**
	 * Le constructeur.
	 * A la creation d'une cellule, celle-ci n'est pas vivante
	 */
	public Cellule() {
		this.etat = DEFAULT_STATE; // On initialise la cellule
	}

	/**
	 * Methode utilisee pour tuer les cellules.
	 * On fait passer son etat a FALSE
	 */
	public void meurt() { 
		this.etat = false; 
	}

	/**
	 * Methode utilisee pour faire naitre une cellule.
	 * On fait passer son etat a TRUE
	 */
	public void nait() { 
		this.etat = true; 
	}

	/**
	 * Methode utilisee pour changer l'etat d'une cellule.
	 * Si celle-ci est vivante, on la fait mourir
	 * Sinon si elle est morte, on la fait vivre.
	 */
	public void change() {
		if (!this.getEtat())
			this.nait();
		 else
			this.meurt();
	}
	
	/**
	 * Methode utilisee pour recuperer l'etat d'une cellule.
	 * @return  : Etat actuel de la cellule
	 */
	public boolean getEtat() {	
		return this.etat; 
	}

	/**
	 * Methode utilisee pour modifier l'etat d'une cellule.
	 * @param b : Nouvel etat de la cellule
	 */
	public void setEtat(boolean b) {
		this.etat = b; 
	}
}
