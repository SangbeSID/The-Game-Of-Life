package model;

/**
 * Cette classe est utilisee pour gerer le noyau fonctionnel du jeu.
 * C'est ici que l'on cree les cellules et manipule les cellules.
 * 
 * @author Sangbe SIDIBE & William FLEURQUIN
 *
 */
public class Model {

	private Cellule[][] tabCells; // Le tableau contenant les cellules
	private int size; // La taille du tableau de cellules
	private double proba;  // La probabilite utilisee pour generer les cellules
	private int mortAsphyxie;  // La valeur indiquant comment meurt une cellule par asphyxie
	private int mortSolitude;  // La valeur indiquant comment meurt une cellule par solitude
	private int vieMin; // Le nbre minimum de cellules permettant de generer une nouvelle cellule
	private int vieMax; // Le nbre maximum de cellules permettant de generer une nouvelle cellule


	public static final int DEFAULT_ASPHYXIE = 4; // Valeur par defaut pour mortAsphyxie
	public static final int DEFAULT_SOLITUDE = 1; // Valeur par defaut pour mortSolitude
	public static final int DEFAULT_VIEMAX = 3;  // Valeur par defaut pour vieMax
	public static final int DEFAULT_VIEMIN = 3; // Valeur par defaut pour vieMin
	public static final double DEFAULT_PROBA = 0.0; // Valeur par defaut de la probabilite
	/**
	 * Le constructeur.
	 * @param s  : La taille du tableau de cellules
	 */
	public Model(int s) {
		this.proba = DEFAULT_PROBA; // On initialise la probabilite
		this.size = s; // On definit la taille du tableau de cellules
		this.tabCells = new Cellule[this.size][this.size]; // On cree le tableau de cellules
		this.mortAsphyxie = DEFAULT_ASPHYXIE; // On initialise mortAsphyxie
		this.mortSolitude = DEFAULT_SOLITUDE; // On initialise mortSolitude
		this.vieMin = DEFAULT_VIEMIN; // On initialise vieMin
		this.vieMax = DEFAULT_VIEMAX; // On initialise vieMax
		
		// On remplit chaque case du tableau avec une nouvelle cellule. (INITIALISATION)
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				this.tabCells[i][j] = new Cellule();
			}
		}
	}

	/**
	 * Methode utilisee pour recuperer la taille du tableau de cellules
	 * @return  : Taille actuelle du tableau de cellules
	 */
	public int getSize(){ 
		return this.size;
	}
	
	/**
	 * Methode utilisee pour modifier la taille du tableau de cellules.
	 * Ici, on cree on nouveau tableau de cellules avec la nouvelle taille.
	 * @param s : Nouvelle taille du tableau de cellules
	 */
	public void setDimension(int s) {
		if (s > 0) { // Si la taille est positive
			Cellule[][] tab = new Cellule[s][s]; // On cree un nouveau tableau de cellules.
			this.size = s; // On modifie la taille actuelle
			this.setTabCells(tab); // On affecte le nouveau tableau au modele.
		}
	}

	/**
	 * Methode utilisee pour recuperer la probabilite.
	 * @return  : Probabilite de creation de cellules vivantes.
	 */
	public double getProba() { 
		return this.proba; 
	}
	
	/**
	 * Methode utilisee pour modifier la probabilite de generation de cellules vivantes.
	 * La nouvelle probabilite est prise en compte ssi elle est comprise [0.0 ; 1.0]
	 * @param p  : Nouvelle probabilite
	 */
	public void setProba(double p) {
		if (p >= 0. && p <= 1.0) {
			this.proba = p; // On affecte la nouvelle probabilite
			this.generate(); // On genere les nouvelles cellules vivantes.
		}
	}
	
	/**
	 * Methode utilisee pour recupere la valeur de mortAsphyxie.
	 * @return  : Valeur indiquant comment meurt une cellule par asphyxie
	 */
	public int getMortAsphyxie() { 
		return mortAsphyxie;
	}

	/**
	 * Methode utilisee pour modifier la valeur indiquant comment meurt une cellule par asphyxie.
	 * Cette valeur est prise en compte ssi appartient a [1 ; 8]
	 * @param mortAsp  : Nouvelle valeur
	 */
	public void setMortAsphyxie(int mortAsp) {
		if (mortAsp >= 0 && mortAsp < 9)
			this.mortAsphyxie = mortAsp; // On affecte la nouvelle valeur
	}

	/**
	 * Methode utilisee pour recupere la valeur de mortSolitude.
	 * @return  : Valeur indiquant comment meurt une cellule par solitude
	 */
	public int getMortSolitude() {
		return mortSolitude;
	}

	/**
	 * Methode utilisee pour modifier la valeur indiquant comment meurt une cellule par solitude.
	 * Cette valeur est prise en compte ssi elle appartient a [1 ; 8]
	 * @param mortSol  : Nouvelle valeur
	 */
	public void setMortSolitude(int mortSol) {
		if (mortSol >= 0 && mortSol < 9)
			this.mortSolitude = mortSol; // On affecte la nouvelle valeur
	}

	/**
	 * Methode permettant de recuperer le nbre minimum de cellules vivantes
	 * permettant de generer une nouvelle cellule vivante.
	 * @return : Nombre minimum de cellules
	 */
	public int getVieMin() { 
		return this.vieMin; 
	}

	/**
	 * Methode utilisee pour modifier la valeur minimum de cellules vivantes permettant
	 * de generer une nouvelle cellule vivante.
	 * Cette valeur est prise en compte ssi elle est appatient a [1 ; 8]
	 * @param vie  : Nouvelle valeur
	 */
	public void setVieMin(int vie) {
		if (vie >= 0 && vie < 9)
			this.vieMin = vie; // On affecte la nouvelle valeur
	} 

	/**
	 * Methode permettant de recuperer le nbre maximum de cellules vivantes
	 * permettant de generer une nouvelle cellule vivante.
	 * @return : Nombre maximum de cellules
	 */
	public int getVieMax() { 
		return this.vieMax;	
	}

	/**
	 * Methode utilisee pour modifier la valeur maximum de cellules vivantes permettant
	 * de generer une nouvelle cellule vivante.
	 * Cette valeur est prise en compte ssi elle est appatient a [1 ; 8]
	 * @param vie  : Nouvelle valeur
	 */
	public void setVieMax(int vie) {
		if (vie >= 0 && vie < 9)
			this.vieMax = vie; // On affecte la nouvelle valeur
	}

	/**
	 * Methode utilisee pour recuperer le tableau de cellules du noyau fonctionnel.
	 * @return  : Le tableau de cellules actuel
	 */
	public Cellule[][] getTabCells() { 
		return this.tabCells; 
	}

	/**
	 * Methode utilisee pour remplacer le tableau de cellules actuel par un
	 * autre tableau de cellules.
	 * @param tab  : Nouveau tableau de cellules.
	 */
	public void setTabCells(Cellule[][] tab) {
		this.tabCells = tab; // On affecte le nouveau tableau;
	}

	
	
	/**
	 * Methode utilisee pour copier un tableau de cellules plus petit dans celui du model qui
	 * lui est plus grand.
	 * @param tab      : Tableau de cellules a copier
	 * @param tabSize  : Taille du tableau de cellules a copier
	 * @param x        : Position x a partir de laquelle la copie sera effectuee dans le tableau du model
	 * @param y        : Position y a partir de laquelle la copie sera effectuee dans le tableau du model
	 */
	public void copyModel(Cellule[][] tab, int tabSize, int x, int y){
		int tmpY = y; // On sauvegarde la valeur de y
		// On parcourt completement le tableau de cellules a copier
		for(int i=0 ; i<tabSize ; i++){
			for(int j=0 ; j<tabSize ; j++){
				if(y < this.size && x < this.size){ 
					// Si x et y sont inferieurs a la taille du tableau de cellules du model alors,
                    this.tabCells[x][y].setEtat(tab[i][j].getEtat()); // On effectue la copie
                    y++; // On incremente y
                }
			}
			x++; // on incremente x
			y = tmpY; // On remet y a sa valeur de depart.
		}
	}

	
	/** 
	 * Cette methode est utilisee pour copier un tableau de cellules dans celui d'un
	 * d'un model dont le tableau de cellules est plus petit, a partir de coordonnees (x,y).
	 * Si la position (x,y) ne permet pas de copier la totalite du tableau tab, seule la partie
	 * pouvant etre copiee est sauvegardee.
	 * 
	 * @param tab      : Tableau de cellules a copier
	 * @param tabSize  : Taille du tableau de cellules a copier
	 * @param x        : Position x a partir de laquelle la copie sera effectuee dans le tableau du model
	 * @param y        : Position y a partir de laquelle la copie sera effectuee dans le tableau du model
	 */
	public void copyPlateau(Cellule[][] tab, int tabSize, int x, int y) {
			int ytmp = y;
			for(int i=0 ; i<this.size ; i++) {
				for(int j=0 ; j<this.size ; j++) {
					if(y < tabSize && x < tabSize){
	                    this.tabCells[i][j].setEtat(tab[x][y].getEtat());
	                    y++;
	                }
				}
				x++;
				y = ytmp;
			}
	
	}
	
	/**
	 * Cette methode est utilisee pour initialiser le tableau de cellules en fonction
	 * de la probabilite donnee.
	 */
	public void generate() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				int rand = (int) (Math.random() + this.proba);
				if (rand == 1)
					this.tabCells[i][j].setEtat(true);
				else
					this.tabCells[i][j].setEtat(false);
			}
		}
	}


	/**
	 * Cette methode est utilisee pour changer l'etat d'une cellule a partir
	 * de sa position dans le tableau de cellules.
	 * @param x :  Position x de la cellule dans le tableau de cellules
	 * @param y :  Position y de la cellule dans le tableau de cellules
	 */
	public void changeState(int x, int y) {
		if (x >= 0 && x < size && y >= 0 && y < size)
			tabCells[x][y].change();
	}

	/**
	 * Cette methode est utilisee pour compter le nombre de cellules voisines
	 * d'une cellule se trouvant dans le tableau de cellules selon sa position.
	 * @param x :   Position x de la cellule dans le tableau de cellules.
	 * @param y :   Position y de la cellule dans le tableau de cellules
	 * @return  :   Le nombre de cellules voisines.
	 */
	public int testVoisine(int x, int y) {
		int xmin, xmax;
		int ymin, ymax;
		int nbVoisine = 0;
		// Si x=0 alors xmin = 0 sinon xmin = x-1
		xmin = (x == 0 ? 0 : x - 1); 
		// Si x=this.size-1 alors xmax = this.xmin-1 sinon xmax=x+1
		xmax = (x == this.size - 1 ? this.size - 1 : x + 1);
		// Si y=0 alors ymin=0 sinon ymin = y-1
		ymin = (y == 0 ? 0 : y - 1);
		// Si y=this.size-1 alors, ymax=this.size-1 sinon ymax=y+1
		ymax = (y == this.size - 1 ? this.size - 1 : y + 1);

		for (int i = xmin; i <= xmax; i++) {
			for (int j = ymin; j <= ymax; j++) {
				if (this.tabCells[i][j].getEtat())
					nbVoisine += 1;
			}
		}
		if (this.tabCells[x][y].getEtat()) {
			nbVoisine -= 1;
		}

		return nbVoisine;
	}
	
	
	/**
	 * Cette methode est utilisee pour faire evoluer le tableau de cellules du model
	 * en passant d'un etat T a T+1.
	 * Ici, on duplique le plateau de cellules et nous faisons tous les tests sur
	 * la copie, Puis, nous faisons evoluer l'original selon les parametres definis.
	 */
	public void nextStep() {
		Model tmp = new Model(this.size); // On cree la copie
		// On duplique le tableau de cellules du model dans tmp
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				tmp.tabCells[i][j].setEtat(this.tabCells[i][j].getEtat());
			}
		}
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (tmp.testVoisine(i, j) <= this.getMortSolitude())
					this.tabCells[i][j].meurt();
				else if (tmp.testVoisine(i, j) >= this.getMortAsphyxie())
					this.tabCells[i][j].meurt();
				else if (tmp.testVoisine(i, j) >= this.getVieMin() && tmp.testVoisine(i, j) <= this.getVieMax())
					this.tabCells[i][j].nait();
			}
		}
	}
}
