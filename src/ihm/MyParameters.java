package ihm;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Cette classe est utilisee pour gerer la zone des parametres du jeu.
 * Elle herite de la classe Box.
 * 
 * @author Sangbe SIDIBE & William FLEURQUIN
 *
 */
public class MyParameters extends Box {
	private String[] list_values = { "0", "1", "2", "3", "4", "5", "6", "7", "8" }; // Valeur des parametres
	private JLabel lbl_parameter = new JLabel("Parametres du jeu"); // Label de la zone des parametres.
	private JComboBox<String> cmb_mort_asphyxie; // Liste des valeurs pour definir la mort par asphyxie
	private JComboBox<String> cmb_mort_solitude; // Liste des valeurs pour definir la mort par solitude
	private JComboBox<String> cmb_vieMin; // Liste des valeurs pour definir la vie minimum d'une cellule
	private JComboBox<String> cmb_vieMax; // // Liste des valeurs pour definir la vie maximale d'une cellule
	
	/**
	 * Le constructeur.
	 * C'est ici que l'on cree et positionne tous les composants 
	 * relatifs a la zone des parametres.
	 */
	public MyParameters() {
		super(BoxLayout.Y_AXIS); // on fait appel au constructeur de la classe-mere.
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		// On cree la liste des valeurs des parametres du jeu
		this.cmb_mort_asphyxie = new JComboBox<String>(this.list_values); 
		this.cmb_mort_solitude = new JComboBox<String>(this.list_values);
		this.cmb_vieMin = new JComboBox<String>(this.list_values);
		this.cmb_vieMax = new JComboBox<String>(this.list_values);
		// On initialise tous les parametres
		this.cmb_mort_asphyxie.setSelectedItem(list_values[4]);
		this.cmb_mort_solitude.setSelectedItem(list_values[1]);
		this.cmb_vieMin.setSelectedItem(list_values[3]);
		this.cmb_vieMax.setSelectedItem(list_values[3]);

		this.lbl_parameter.setHorizontalAlignment(JLabel.CENTER);
		//  On positionne les composants dans des boites
		Box box_lbl_parameter = new Box(BoxLayout.X_AXIS);
		box_lbl_parameter.add(Box.createHorizontalGlue());
		box_lbl_parameter.add(lbl_parameter);
		box_lbl_parameter.add(Box.createHorizontalGlue());
		
		Box box_mPAXYE = new Box(BoxLayout.X_AXIS);
		Box box_mPSOL = new Box(BoxLayout.X_AXIS);
		Box box_vMIN = new Box(BoxLayout.X_AXIS);
		Box box_vMAX = new Box(BoxLayout.X_AXIS);

		JLabel lbl_vieMin = new JLabel("Vie Min");
		JLabel lbl_vieMax = new JLabel("Vie MAX");
		JLabel lbl_mPAXYE = new JLabel("Mort par Asphyxie");
		JLabel lbl_mPSOL = new JLabel("Mort par Solitude");

		box_mPAXYE.add(lbl_mPAXYE);
		box_mPAXYE.add(Box.createHorizontalStrut(10));
		box_mPAXYE.add(cmb_mort_asphyxie);

		box_mPSOL.add(lbl_mPSOL);
		box_mPSOL.add(Box.createHorizontalStrut(10));
		box_mPSOL.add(cmb_mort_solitude);

		box_vMIN.add(lbl_vieMin);
		box_vMIN.add(Box.createHorizontalStrut(10));
		box_vMIN.add(cmb_vieMin);

		box_vMAX.add(lbl_vieMax);
		box_vMAX.add(Box.createHorizontalStrut(10));
		box_vMAX.add(cmb_vieMax);
		
		// On met toutes les boites dans la zone des parametres
		this.setOpaque(true);
		this.setBackground(Color.green);
		this.add(box_lbl_parameter);
		this.add(Box.createVerticalStrut(20));
		this.add(box_mPAXYE);
		this.add(Box.createVerticalStrut(20));
		this.add(box_mPSOL);
		this.add(Box.createVerticalStrut(20));
		this.add(box_vMIN);
		this.add(Box.createVerticalStrut(20));
		this.add(box_vMAX);
		this.add(Box.createVerticalStrut(10));
	}

	/**
	 * Cette methode permet de recuperer la valeur de la vie maximale d'une cellule
	 * @return  : Valeur selectionnee dans la liste
	 */
	public int getVieMax() {
		return (Integer.valueOf((String) this.cmb_vieMax.getSelectedItem())).intValue();
	}

	/**
	 * Cette methode permet de recuperer la valeur de la vie minimale d'une cellule
	 * @return  : Valeur selectionnee dans la liste
	 */
	public int getVieMin() {
		return (Integer.valueOf((String) this.cmb_vieMin.getSelectedItem())).intValue();
	}

	/**
	 * Cette methode permet de recuperer la valeur indiquant la condition 
	 * a laquelle une cellule peut mourir par asphyxie.
	 * @return  : Valeur selectionnee dans la liste
	 */
	public int getMortParAsphyxie() {
		return (Integer.valueOf((String) this.cmb_mort_asphyxie.getSelectedItem())).intValue();
	}

	/**
	 * Cette methode permet de recuperer la valeur indiquant la condition 
	 * a laquelle une cellule peut mourir par solitude.
	 * @return  : Valeur selectionnee dans la liste
	 */
	public int getMortParSolitude() {
		return (Integer.valueOf((String) this.cmb_mort_solitude.getSelectedItem())).intValue();
	}
}
