package ihm;

import javax.swing.JButton;
import jbutton.ButtonIcon;

/**
 * Cette classe est utilisee pour gerer le bouton permettant de lancer
 * et d'arreter le jeu. C'est ici que l'on cree le bouton Play/Pause
 * 
 * @author Sangbe SIDIBE & William FLEURQUIN
 *
 */
public class ButtonStartPause extends JButton {
	private boolean play = true; // L'etat initial du bouton. Il est sur Play a sa creation
	private static final int DEFAULT_SIZE = 40; // Taille par defaut du bouton

	/**
	 * Le Constructeur.
	 * Ici, on cree le bouton a l'etat Play.
	 */
	public ButtonStartPause() {
		this.setIcon(new ButtonIcon(ButtonIcon.START, DEFAULT_SIZE, DEFAULT_SIZE));
	}

	/**
	 * Methode permettant de changer l'etat du bouton.
	 * S'il est sur Play, on le met a Pause et vice-versa.
	 */
	public void changeState() {
		if (this.play)
			this.setIcon(new ButtonIcon(ButtonIcon.PAUSE, DEFAULT_SIZE, DEFAULT_SIZE));
		else
			this.setIcon(new ButtonIcon(ButtonIcon.START, DEFAULT_SIZE, DEFAULT_SIZE));
		this.play = !this.play;
	}

	/**
	 * Cette methode permet de recuperer l'etat du bouton.
	 * @return : L'etat actuel du bouton Play/Pause
	 */
	public Boolean getState() {
		return this.play;
	}
}
