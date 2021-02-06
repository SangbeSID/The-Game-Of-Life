package ihm;

import java.awt.Color;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 * Cette classe est utilisee pour creer la zone d'edition du plateau de jeu.
 * 
 * @author Sangbe SIDIBE & William FLEURQUIN
 *
 */
public class ZoneEdition extends Box {

	private JTextField txt_probabilite; // Zone de texte permettant de definir la probabilite de vie des cellules
	private JTextField txt_taille_plateau; // Zone de texte permettant de definir la taille de la grille des cellules
	private JButton bt_reset;  // Bouton permmettant de reinitialiser le plateau de jeu
	private JButton bt_probabilite; // Bouton permettant de valider la probabilite definie
	private JButton bt_taille_plateau; // Bouton permettant de valider la taille de la grille
	
	/**
	 * Le constructeur.
	 */
	public ZoneEdition() {
		super(BoxLayout.Y_AXIS);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
				
		// On definit le format de la zone permettant de definir la taille de la grille
		// On specifie que cette zone ne prend que des entiers et rien d'autre.
		NumberFormatter sizeFormatter = new NumberFormatter(NumberFormat.getIntegerInstance());
		sizeFormatter.setValueClass(Integer.class);
		sizeFormatter.setAllowsInvalid(false); // On autorise que les entiers
		sizeFormatter.setMinimum(0); // On definit la valeur minimum pour cette zone
		sizeFormatter.setMaximum(999); // On definit la valeur maximale pour cette zone
		// On applique le format a la zone de saisie
		this.txt_taille_plateau = new JFormattedTextField(sizeFormatter);
		
		// On definit le format de la zone permettant de definir la probabilite de vie des cellules
		// On specifie que cette zone ne prend que des entiers et rien d'autre.
		NumberFormatter probaFormatter = new NumberFormatter(NumberFormat.getIntegerInstance());
		probaFormatter.setValueClass(Integer.class);
		probaFormatter.setAllowsInvalid(false); // On autorise que les entiers
		probaFormatter.setMinimum(0); // On definit la valeur minimum pour cette zone
		probaFormatter.setMaximum(100); // On definit la valeur maximale pour cette zone
		// On applique le format
		this.txt_probabilite = new JFormattedTextField(probaFormatter);
		
		this.txt_probabilite.setText("00"); // On definit une valeur par defaut de la probabilite
		this.txt_probabilite.setHorizontalAlignment(JTextField.RIGHT);
		this.txt_taille_plateau.setHorizontalAlignment(JTextField.RIGHT);

		this.bt_reset = new JButton("Reinitialiser le Plateau"); // On cree le bouton de reinitialisation
		this.bt_probabilite = new JButton("OK"); // On cree le bouton de validation de la probabilite
		this.bt_taille_plateau = new JButton("OK"); // On cree le bouton de validation de la taille

		Box box_taille_plateau = new Box(BoxLayout.X_AXIS);
		Box box_probabilite = new Box(BoxLayout.X_AXIS);

		// ------- Les LABELS
		JLabel lbl_edit_plateau = new JLabel("Edition du plateau");
		JLabel lbl_taille_plateau = new JLabel("Taille du Plateau");
		JLabel lbl_probabilite = new JLabel("Probabilite");
		lbl_edit_plateau.setHorizontalAlignment(JLabel.CENTER);
		lbl_taille_plateau.setHorizontalAlignment(JLabel.CENTER);
		lbl_probabilite.setHorizontalAlignment(JLabel.CENTER);

		// --------- La zone de la TAILLE de LA GRILLE
		Box box_lbl_taille_plateau = new Box(BoxLayout.X_AXIS);
		box_lbl_taille_plateau.add(Box.createHorizontalGlue());
		box_lbl_taille_plateau.add(lbl_taille_plateau);
		box_lbl_taille_plateau.add(Box.createHorizontalGlue());

		box_taille_plateau.add(txt_taille_plateau);
		box_taille_plateau.add(Box.createHorizontalStrut(10));
		box_taille_plateau.add(bt_taille_plateau);

		Box cadre_taille_plateau = new Box(BoxLayout.Y_AXIS);
		cadre_taille_plateau.add(box_lbl_taille_plateau);
		cadre_taille_plateau.add(Box.createVerticalStrut(10));
		cadre_taille_plateau.add(box_taille_plateau);

		// -------- Boutton RESET
		Box box_bt_rest = new Box(BoxLayout.X_AXIS);
		box_bt_rest.add(Box.createHorizontalGlue());
		box_bt_rest.add(this.bt_reset);
		box_bt_rest.add(Box.createHorizontalGlue());

		// -------- Zone de PROBABILITE
		Box box_lbl_probabilite = new Box(BoxLayout.X_AXIS);
		box_lbl_probabilite.add(Box.createHorizontalGlue());
		box_lbl_probabilite.add(lbl_probabilite);
		box_lbl_probabilite.add(Box.createHorizontalGlue());

		box_probabilite.add(this.txt_probabilite);
		box_probabilite.add(Box.createHorizontalStrut(10));
		box_probabilite.add(this.bt_probabilite);

		Box cadre_proba = new Box(BoxLayout.Y_AXIS);
		cadre_proba.add(box_lbl_probabilite);
		cadre_proba.add(Box.createVerticalStrut(10));
		cadre_proba.add(box_probabilite);

		// ========== La ZONE D'EDITION FINALE
		this.setOpaque(true);
		this.setBackground(Color.green);
		this.add(cadre_taille_plateau);
		this.add(Box.createVerticalStrut(20));
		this.add(box_bt_rest);
		this.add(Box.createVerticalStrut(20));
		this.add(cadre_proba);
		this.add(Box.createVerticalStrut(10));
	}

	/**
	 * Cette Methode permet de recuperer le bouton de reinitialisation
	 * @return  : Le bouton de reinitialisation du plateau de jeu
	 */
	public JButton getBoutonReset() {
		return this.bt_reset;
	}
	
	/**
	 * Cette Methode permet de recuperer le bouton de validation de la probabilite
	 * @return  : Le bouton de validation de la probabilite de vie des cellules
	 */
	public JButton getBoutonProbabilite() {
		return this.bt_probabilite;
	}

	/**
	 * Cette Methode permet de recuperer le bouton de validation de la taille de la grille
	 * @return  : Le bouton de validation de la taille de la grille des cellules
	 */
	public JButton getBoutonTailleTableau() {
		return this.bt_taille_plateau;
	}

	/**
	 * Methode permettant de recuperer la valeur de la probailite a partir de la zone
	 * de saisie.
	 * On divise la valeur du champ par 100 afin d'avoir la probabilite correspondante
	 * si et seulement si ce champ n'est pas vide. Dans le cas contraire , on retourne 0.
	 * @return  : Valeur de la probabilite de vie des cellules
	 */
	public double getProbabilite() {
		if (this.txt_probabilite.getText() != null)
			return (Integer.valueOf(this.txt_probabilite.getText())).intValue()/100.;
		else
			return 0.0;
	}

	/**
	 * Methode permettant de recuperer la taille de la grille des cellules 
	 * a partir de la zone de saisie.
	 * Si le champ est vide on retourne 10, sinon on retourne la valeur saisie.
	 * @return  : Taille de la grille des cellules saisie.
	 */
	public int getTailleTableau() {
		if (this.txt_taille_plateau.getText() != null)
			return (Integer.valueOf(this.txt_taille_plateau.getText())).intValue();
		else
			return 10;
	}
}
