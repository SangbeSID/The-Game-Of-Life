package ihm;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Motifs;
import model.Motifs.Shape;

/**
 * Cette classe est utilisee pour gerer tout ce qui touche a la ZONE TAMPON.
 * C'est ici, que l'on edite cette zone.
 * 
 * @author Sangbe SIDIBE & William FLEURQUIN
 *
 */
@SuppressWarnings("serial")
public class EditionZoneTampon extends JPanel {
	private ZoneTampon tampon;  // La zone tampon ou l'on charge les motifs
	private JComboBox<Shape> cmb_model; // La liste des motifs disponibles
	private JButton bt_charger = new JButton("Charger"); // Le bouton permettant de charger les motifs
	private Motifs motif = new Motifs(); // L'element qui contient les motifs
	
	public final static int ZONE_SIZE = 10; // Taille par defaut de la zone tampon.
	
	/**
	 * Le constructeur.
	 * C'est ici que l'on place tous les composants relatifs a la zone tampon.
	 */
	public EditionZoneTampon() {
		super();
		// On cree la zone tampon
		this.tampon = new ZoneTampon(ZONE_SIZE, ZONE_SIZE, MyPanel.DEFAULT_CELL_SIZE);

		this.setPreferredSize(new Dimension(200, 100));
		this.setOpaque(true);

		// On cree la liste des motifs
		this.cmb_model = new JComboBox<Shape>(Shape.values());

		Box box = new Box(BoxLayout.X_AXIS);
		box.add(Box.createHorizontalGlue());
		box.add(this.tampon);
		box.add(Box.createHorizontalGlue());

		Box box_bt = new Box(BoxLayout.X_AXIS);
		box_bt.add(Box.createHorizontalGlue());
		box_bt.add(this.bt_charger);
		box_bt.add(Box.createHorizontalGlue());

		// On ajoute le controlleur au bouton charger
		this.bt_charger.addMouseListener(new EditionZoneTamponController());
		
		Box cadre = new Box(BoxLayout.Y_AXIS);
		cadre.add(Box.createVerticalStrut(10));
		cadre.add(box);
		cadre.add(Box.createVerticalStrut(20));
		cadre.add(this.cmb_model);
		cadre.add(Box.createVerticalStrut(20));
		cadre.add(box_bt);

		this.add(cadre);
	}

	/**
	 * Methode permettant de recuperer la zone tampon.
	 * @return  : La zone tampon
	 */
	public ZoneTampon getZoneTampon() {
		return this.tampon;
	}

	/**
	 * Cette classe est utilisee pour gerer le Controller rattachee a la classe EditionZoneTampon.
	 * C'est ici, que l'on definit, toutes les specificites liees a cette zone.
	 * 
	 * @author Sangbe SIDIBE & William FLEURQUIN
	 */
	private class EditionZoneTamponController extends MouseAdapter {
		// Lorsque l'on clique sur le bouton charger, le motif selectionne
		// dans la liste est dessine sur la zone tampon
		@Override
		public void mouseClicked(MouseEvent evt) {
			// Quel que soit le motif chosi, celui-ci est reproduit sur la zone tampon
			motif.drawMotif((Shape)cmb_model.getSelectedItem());
			tampon.getModel().copyModel(motif.getMotif(), ZONE_SIZE, 0, 0);		
			motif = new Motifs(); // On reinitialise le motif
			tampon.repaint(); // On repaint la zone tampon
		}
	}
}
