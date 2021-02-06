package Game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ihm.ButtonStartPause;
import ihm.MyFrame;
import ihm.MyPanel;
import ihm.MyParameters;
import ihm.Plateau;
import ihm.ZoneEdition;
import ihm.EditionZoneTampon;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Cette classe est utilisse pour construire le jeu.
 * C'est ici que l'on place tous les composants a leur place, et
 * que l'on definit toutes les autres specifications du jeu.
 * Elle herite de MyFrame et implemente l'interface Runnable.
 * 
 * @author Sangbe SIDIBE & William FLEURQUIN
 *
 */
public class Game extends MyFrame implements Runnable {

	private Plateau plateau; 	// Le plateau sur lequel on dessine les cellules
	private MyParameters parameters_zone; // La zone ou se trouvent les parametres du jeu
	private ZoneEdition edition_zone; // La zone permettant d'editer le jeu
	private ButtonStartPause bt_start_pause; // Le bouton permettant de lancer/arreter le jeu
	private EditionZoneTampon zone_tampon; // La zone ou l'on peut sauver/charger des motifs
	private boolean isLaunched = false; // L'attribut indiquant si le jeu est tourne ou non

	
	public static final int DEFAULT_BOARD_SIZE = 100; // Taille par defaut du plateau de jeu
	public static final int DEFAULT_GRID_SIZE = 100; // Taille par defaut de la grille des cellules
	
	/**
	 * Le constructeur.
	 * @param title  : Le titre du jeu
	 */
	public Game(String title) {
		super(title); // On fait appel au constructeur de la classe mere

		// =================  On cree les composants du jeu
		this.plateau = new Plateau(DEFAULT_BOARD_SIZE, DEFAULT_GRID_SIZE, MyPanel.DEFAULT_CELL_SIZE);
		this.parameters_zone = new MyParameters();
		this.edition_zone = new ZoneEdition();
		this.bt_start_pause = new ButtonStartPause();
		this.zone_tampon = new EditionZoneTampon();
		
		// On abonne le controlleur aux composants qui font le jeu
		this.edition_zone.getBoutonTailleTableau().addActionListener(new GameController());
		this.edition_zone.getBoutonProbabilite().addActionListener(new GameController());
		this.edition_zone.getBoutonReset().addActionListener(new GameController());
		this.bt_start_pause.addMouseListener(new GameController());
		this.zone_tampon.getZoneTampon().addMouseListener(new GameController());
		this.plateau.addMouseListener(new GameController());
		
		// Les differentes boites representant les parties de la fenetre
		Box box_left = new Box(BoxLayout.Y_AXIS);
		Box box_middle = new Box(BoxLayout.Y_AXIS);
		Box box_right = new Box(BoxLayout.Y_AXIS);
		Box cadre = new Box(BoxLayout.X_AXIS);

		// ================================== La zone de GAUCHE
		Box box_bt_start_pause = new Box(BoxLayout.X_AXIS);
		box_bt_start_pause.add(Box.createHorizontalGlue());
		box_bt_start_pause.add(this.bt_start_pause);
		box_bt_start_pause.add(Box.createHorizontalGlue());

		box_left.add(this.edition_zone);
		box_left.add(Box.createVerticalStrut(25));
		box_left.add(this.parameters_zone);
		box_left.add(Box.createVerticalStrut(45));
		box_left.add(box_bt_start_pause);

		// ==================================== La zone de DROITE
		box_right.add(this.zone_tampon);
		box_right.add(Box.createVerticalGlue());

		// ==================================== La zone du MILIEU
		box_middle.add(this.plateau);
		
		// =================== Le CADRE
		cadre.add(box_left);
		cadre.add(Box.createHorizontalStrut(25));
		cadre.add(box_middle);
		cadre.add(Box.createHorizontalStrut(25));
		cadre.add(box_right);
		
		// Le panel sur lequel sont disposes tous les composants
		JPanel panel = new JPanel();
		// On met le cadre sur le panel
		panel.add(cadre);
		// ================================= On met le panel dans la fenetre
		this.add(panel, BorderLayout.NORTH);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * methode permettant de lancer le jeu.
	 * Tant que l'etat du jeu vaut TRUE, on passe a l'etat suivant toutes les 100 ms.
	 */
	public void launchTheGame() {
		while (this.isLaunched) {
			try {
				plateau.nextStep(); // On passe a l'etat suivant
				Thread.sleep(100); // On attend 100ms.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Redefinition de la methode run() de l'interface Runnable.
	 * Ici, on fait appel a la methode launchTheGame() afin de lancer le jeu
	 * a l'aide d'un Thread.
	 */
	@Override
	public void run() {
		this.launchTheGame();
	}

	/**
	 * Cette classe est utilisee pour gerer le Controller rattachee a la classe Game.
	 * C'est ici, que l'on definit, toutes les specificites liees a Game.
	 * @author narcisse
	 */
	private class GameController extends MouseAdapter implements ActionListener {

		@Override
		public void mouseClicked(MouseEvent evt) {
			if (evt.getSource() == bt_start_pause) { // Si l'on clique sur le bouton Play/Pause
				if (bt_start_pause.getState()) {  // Si l'etat bouton est est sur Play ==> Le jeu ne tourne pas
					// On applique tous les parametres definis au model du plateau de jeu.
					plateau.getModel().setMortAsphyxie(parameters_zone.getMortParAsphyxie());
					plateau.getModel().setMortSolitude(parameters_zone.getMortParSolitude());
					plateau.getModel().setVieMin(parameters_zone.getVieMin());
					plateau.getModel().setVieMax(parameters_zone.getVieMax());
					
					bt_start_pause.changeState(); // On change l'etat du bouton Play/Pause
					isLaunched = true; // On indique que le jeu tourne
					plateau.enableTheBoard(); // On active le plateau de jeu
					
					// On lance le jeu
					Thread m = new Thread(Game.this); // On cree un Thread qui agit sur le jeu
					m.start(); // On lance le thread.

				} else { // Si le jeu tourne ==> le etat du bouton sur Pause
					isLaunched = false; // On indique que le jeu ne tourne plus					
					plateau.disableTheBoard(); // On desactive le plateau de jeu
					bt_start_pause.changeState(); // On change l'etat du bouton Play/pause
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent evt) {
			int modifiers = evt.getModifiers(); // On recupere la touche qui a ete en meme temps que le bouton de la souris.
			int x = evt.getX()/plateau.getCellSize(); // Position x de la souris
			int y = evt.getY()/plateau.getCellSize(); // position y de la souris
			if(bt_start_pause.getState()){ // Si le jeu ne tourne pas ==> etat du bouton Play/Pause sur play
				if (evt.getSource() == plateau) { // Si le click a eu lieu sur la plateau de jeu
					// Si le click a ete effectue avec le bouton droit de la souris et la touche SHIFT enfonce
					// Alors on copie le motif de la zone tampon sur le plateau de jeu a partir de l'endroit ou
					// le click a eu lieu
					if (evt.getButton() == MouseEvent.BUTTON3 && ((modifiers & MouseEvent.SHIFT_MASK) != 0)) {
						plateau.copyZoneTampon(zone_tampon.getZoneTampon().getModel(), x, y);						
					}
					// Si le click a ete effectue avec le bouton droit de la souris seulement, alors,
					// on copie le motif du plateau de jeu a partir de l'endroit ou le click a ete effectue
					// sur la zone tampon
					else if(evt.getButton() == MouseEvent.BUTTON3) {
						zone_tampon.getZoneTampon().copyPlateau(plateau.getModel(), x, y);
					}
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent act) {
			// C'est ici que l'on edite le plateau du jeu ==> Que l'on tient compte des
			// evenements de la zone d'edition 
			if (bt_start_pause.getState()) { // Si le jeu e tourne pas ==> etat du bouton Play/Pause sur play
				// Si l'evenement s'est produit sur le bouton de validation de la taille de la grille
				// des cellules, on modifie la taille de la grille des cellules du plateau de jeu
				if (act.getSource() == edition_zone.getBoutonTailleTableau()){
					int r = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier la taille de la grille", "Edition taille de la grille",
							JOptionPane.YES_NO_OPTION);
					if (r == JOptionPane.YES_OPTION)
						plateau.setSizeOfDesignArea(edition_zone.getTailleTableau());
				}
				// Si l'evenement s'est produit sur le bouton de validation de la probabilite de vie
				// des cellules, on modifie la probabilite du modele du plateau de jeu
				else if (act.getSource() == edition_zone.getBoutonProbabilite()){
					int r = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier la probabilite", "Edition probabilite",
							JOptionPane.YES_NO_OPTION);
					if (r == JOptionPane.YES_OPTION)
						plateau.getModel().setProba(edition_zone.getProbabilite());
				}
				// Si l'evenement s'est produit sur le bouton de reinitialisation de la grille des cellules
				// du plateau, on met la probabilite de vie des cellules a 0.0
				else if (act.getSource() == edition_zone.getBoutonReset()){
					int r = JOptionPane.showConfirmDialog(null, "Voulez-vous reinitialiser le jeu", "Reinitialisation",
							JOptionPane.YES_NO_OPTION);
					if (r == JOptionPane.YES_OPTION)
						plateau.getModel().setProba(0.0);
				}
				// A la fin on repaint le plateau de jeu.
				plateau.repaint();
			}
		}

	}
}
