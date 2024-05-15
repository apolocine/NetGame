package jeux;

import java.util.Iterator;
import java.util.LinkedList;

import commun_rc.MatrixListe;
import commun_rc.PersoInfos;
import commun_rc.TPoint;
import commun_rc.UnCoup;

/**
 *
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright : Copyright (c) 2004
 * </p>
 * <p>
 * Societe : drmdh
 * </p>
 * 
 * @author Hamid Madani
 * @version 1.0
 */
public class Table extends LinkedList {
	private String traceInfos;

	int latable = 0;

	public int getnumTable() {
		return this.latable;
	}

	public void setTable(int numTble) {
		this.latable = numTble;
	}

	/**
	 * le nembre maximum de joueur que peut admetre une table
	 * 
	 * @param joueurMax int
	 */

	public void setJoueurMax(int joueurMax) {
		this.joueurMax = joueurMax;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	/**
	 * addCoup utilise par le serveur a la reception d'un coup emie par le joueur x
	 * 
	 * @param unCoup Coup
	 */
	public void addCoupCycle(UnCoup unCoup) {
		this.upCoupDuCycleEnCour();

		listCoupsduCycle.add(unCoup);

	}

	/**
	 * addCoup utilise par le serveur a la reception pour stoquer l'ensemble des
	 * coups
	 *
	 * @param Coups UnCoup[]
	 */
	public void addCoupGlobale(UnCoup[] Coups) {
		System.out.println("\t\t\tentree  Traitement Table.addCoupGlobale");
		for (int i = 0; i < Coups.length; i++) {

			if (Coups[i].isTouche()) {
				setJoueurPointTouche(getnumTable(), Coups[i].getReceiver(), Coups[i].getPoint());
			} else {
				setJoueurPointPerdu(getnumTable(), Coups[i].getReceiver(), Coups[i].getPoint());

			}

		}
		System.out.println("\t\t\tsortie  Traitement Table.addCoupGlobale");
	}

	/**
	 * setJoueurPointPerdu
	 *
	 * @param i      int
	 * @param i1     int
	 * @param tPoint TPoint
	 */
	public void setJoueurPointPerdu(int i, int i1, TPoint tPoint) {
		listCoupsGlobale.setJoueurPointPerdu(i, i1, tPoint);
	}

	/**
	 * setJoueurPointTouche
	 *
	 * @param i      int
	 * @param i1     int
	 * @param tPoint TPoint
	 */
	public void setJoueurPointTouche(int i, int i1, TPoint tPoint) {
		listCoupsGlobale.setJoueurPointTouche(i, i1, tPoint);
	}

	public void setWaitingForAllCoup(boolean waitingForAllCoup) {

		this.waitingForAllCoup = waitingForAllCoup;
	}

	public void setWaitCycle(boolean waitCycle) {
		this.waitCycle = waitCycle;
	}

	public void setGameStart(boolean gameStart) {
		this.gameStart = gameStart;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public void setCycleEncour(int cycleEncour) {
		this.cycleEncour = cycleEncour;
	}

	public void setCoupDuCycleEnCour(int coupDuCycleEnCour) {
		this.coupDuCycleEnCour = coupDuCycleEnCour;
	}

	public void setJoueurVivants(int joueurVivants) {
		this.joueurVivants = joueurVivants;
	}

	// setNbjoueursPresent permet de de reinitiliser le nembre de joueur
	// present en cas de deconnection
	public void setNbjoueursPresent(int nbjoueursPresent) {
		this.nbjoueursPresent = nbjoueursPresent;
	}

	public void setNbCoupsCycleArecevoire(int nbCoupsCycleArecevoire) {
		this.nbCoupsCycleArecevoire = nbCoupsCycleArecevoire;
	}

	public void appendTraceInfos(String traceInfos) {
		// this.traceInfos = traceInfos;
		setTraceInfos(getTraceInfos() + traceInfos);
	}

	public void setTraceInfos(String traceInfos) {
		this.traceInfos = traceInfos;
	}

	public void setasServeurTable(boolean laTableCentrale) {
		this.ServeurTable = laTableCentrale;
	}

	public void setNavires(TPoint[][] Navires) {
		this.Navires = Navires;
	}

//en icremente le cycle en cour si toutes les personne sont presente
	// et si on a reeu un nembre de coup correspondant au nembre de joueur vivant
	public void upCoupDuCycleEnCour() {

		/*
		 * if(isFull()&&getJoueurVivants()!=getCoupDuCycleEnCour()){
		 * 
		 * setWaitCycle(true); this.coupDuCycleEnCour = getCoupDuCycleEnCour()+1; }else{
		 * setWaitCycle(false); }
		 */
		if (getJoueurVivants() == getCoupDuCycleEnCour()) {
			setWaitCycle(false);
		} else {
			setWaitCycle(true);
			this.coupDuCycleEnCour = getCoupDuCycleEnCour() + 1;

		}

	}

	/**
	 *
	 * @param personne PersoInfos
	 */
	public void setJoueurForfaitair(PersoInfos personne) {
		PersoInfos joueurForfait = new PersoInfos(-1, "forfait." + personne.getLogin(), "", "", "");
		super.set(super.indexOf(personne), joueurForfait);
//   super.remove(personne);
	}

	// la table est pleine ssi le maximum de personnes est atteint
	// et personne ne s'est deconnecte
	public boolean isFull() {
		setFull(getNbjoueursPresent() == getJoueurMax());
		return full;
	}

	public boolean isWaitingForAllCoup() {
		return isFull() && waitingForAllCoup;
	}

	public boolean isWaitCycle(int variant_) {
//System.out.print("Table.isWaitCycle : coups enregistres  = "+this.getlistCoupsduCycle().size());
		String infosout = "\n Table.isWaitCycle : Invariant d'iteration Ne = " + this.getCoupDuCycleEnCour()
				+ getNbCoupsCycleArecevoire() + " variant = " + variant_ + " \t condition  : "
				+ "variant_ >= Invariant le coups ";
		appendTraceInfos(infosout);
		System.out.print(infosout);
		// return this.getlistCoupsduCycle().size() == getNbCoupsCycleArecevoire() ;
		return (variant_ >= getNbCoupsCycleArecevoire()) || (this.getJoueurVivants() == 1);
	}

	public int getJoueurMax() {
		return joueurMax;
	}

	public int getCoupDuCycleEnCour() {
		return coupDuCycleEnCour;
	}

	public boolean isGameStart() {
		return gameStart;
	}

	public boolean isStart() {
		return start;
	}

	public void upCycleEncour() {
		setCycleEncour(getCycleEncour() + 1);
	}

	public int getCycleEncour() {
		return cycleEncour;
	}

	public TPoint[][] getNavires() {
		// return Navires;
		return listCoupsGlobale.getNavirJoueurP(this.getnumTable());

	}

	/**
	 * addJoueur
	 *
	 * @param personne PersoInfos
	 */
	public void addJoueur(PersoInfos personne) {
		if (!isFull()) {
			// System.out.println("addJoueur : avant this.size() : this.add(personne)"
			// +this.size());

			this.add(personne);
			// System.out.println("addJoueur : apres this.size() :" +this.size());

			setNbjoueursPresent(this.size());
			setJoueurVivants(getNbjoueursPresent());
			this.setNbCoupsCycleArecevoire(getJoueurVivants());
			System.out.println("   Valeur initiale de l'Ivariant de traitement  " + getNbCoupsCycleArecevoire());
			System.out.println("   le(s) joueur(s) present et en vie  " + getJoueurVivants());
			// System.out.println("addJoueur : getJoueurMax( :" + getJoueurMax());
			// System.out.println("addJoueur : getNbjoueursPresent() :" +
			// getNbjoueursPresent());
			// System.out.println("addJoueur : getJoueurVivants() :" + getJoueurVivants());

		} else {
			System.out.println("addJoueur :  la Table est Pleine");
		}

	}

	/**
	 * Table
	 *
	 * @param numeroTble int
	 */

	public Table(int numeroTble) {
		// setTable( numeroTble);
		// setJoueurMax(numeroTble);
	}

	public Table(int numeroTble, int joueur) {
		setTable(numeroTble);
		setJoueurMax(joueur);
	}

	public String totring() {
		String str = "";
		Iterator i = this.iterator();
		int x = 0;
		while (i.hasNext()) {
			Object o = i.next();
			PersoInfos tmp = (PersoInfos) o;
			str = str + "___/___" + tmp.toString();
		}

		return str;
	}

//la liste des coup ne doit contenir que les coups valides par les joueurs
	private LinkedList listCoupsduCycle = new LinkedList();
	private static MatrixListe listCoupsGlobale = new MatrixListe();

	public LinkedList getlistCoupsduCycle() {
		return listCoupsduCycle;
	}

	public int getJoueurVivants() {
		return joueurVivants;
	}

	public int getNbjoueursPresent() {
		return nbjoueursPresent;
	}

	public int getNbCoupsCycleArecevoire() {
		return nbCoupsCycleArecevoire;
	}

	public String getTraceInfos() {
		return traceInfos;
	}

	public boolean isServeurTable() {
		return ServeurTable;
	}

	private boolean ServeurTable;

	private boolean full;
	private int joueurMax = 4;
	private int nbjoueursPresent = 0;
	private int joueurVivants = 0;
	private TPoint[][] Navires;
	private boolean start;

	private int coupDuCycleEnCour = -1;

	private boolean waitingForAllCoup = true;
	private boolean waitCycle;
	private boolean gameStart;
	private int nbJeutonsparoyeur = 18;
	private int cycleEncour = 0;

	/**
	 *
	 * @return PersoInfos[]
	 */
	public PersoInfos[] getlocalAllId() {
		int numper = getNbjoueursPresent();
		PersoInfos[] ALLPerso = new PersoInfos[numper]; // Allperso=ALLPerso;+1

		Iterator i = this.iterator();
		int x = 0;
		while (i.hasNext()) {
			Object o = i.next();
			ALLPerso[x] = (PersoInfos) o;
			ALLPerso[x].setNum(x);

			x++;
		}
		return ALLPerso;

	}

	/**
	 *
	 * @return PersoInfos[]
	 */
	public Object[] getlocalAllId2() {

		return this.toArray();

	}

	/**
	 * setintstart la visibilitee est determine par le boolean visible chez le
	 * serveur sur tous les plateaux chez le client sur le plateu approprie
	 * 
	 * @param Navires_ TPoint[][]
	 * @param visible  boolean
	 */
	public void setintstart(TPoint[][] Navires_, boolean visible) {
		listCoupsGlobale.setNavirAllJoueurs(getnumTable(), Navires_);

		setNavires(Navires_);
		setStart(visible);

	}

	/**
	 * setlocalintstart
	 *
	 * 
	 * @param Navires_ TPoint[][]
	 * @param visible  boolean
	 */
	public void setlocalintstart(TPoint[][] Navires_, boolean visible) {
		setintstart(Navires_, visible);
	}

	/**
	 * getNbjeutonsParJoueur
	 * 
	 * @return int
	 */
	public int getNbjeutonsParJoueur() {
		return nbJeutonsparoyeur;
	}

	/**
	 * genPosNavir
	 *
	 * @return TPoint[][]
	 */
	public TPoint[][] RandomPosNavir() {
		TPoint[][] MxNavir = new PosiBoardgen().MatrixDesNavir(getJoueurMax(), getNbjeutonsParJoueur());
		;

		setintstart(MxNavir, true);
		return MxNavir;
	}

	/**
	 * getAllCoups
	 *
	 *
	 * @return UnCoup[]
	 */
	public UnCoup[] getCoupsduCycle() {
		UnCoup[] coups = new UnCoup[getNBCoupDuCycleEnCour()];
		Iterator i = this.listCoupsduCycle.iterator();
		int x = 0;
		while (i.hasNext()) {
			Object o = i.next();
			coups[x] = (UnCoup) o;
			x++;
		}
		return coups;
	}

	/**
	 * getNBCoupDuCycleEnCour
	 *
	 * @return int
	 */
	private int getNBCoupDuCycleEnCour() {
		return listCoupsduCycle.size();
	}

	public void rules(UnCoup[] coups) {
		System.out.println("\t\tentree  Traitement Table.rules");
		// Application des regles du jeu sur les n coup du cycle
		// avant leur enregistrement
		rulesattribue(coups);

// enregistrement des coups
		addCoupGlobale(coups);

		// resultas a partir des enregistrements apres application des regles
		rulenombredejoueurenvie(coups);

		System.out.println("\t\tsortie  Traitement Table.rules");
	}

	public void rulesattribue(UnCoup[] coups) {
		// le test est realise dans netgame.CadrePr.jButton4_actionPerformed
		System.out.println("\t\t\tentree  Traitement Table.rulesattribue");
		for (int i = 0; i < coups.length; i++) {
			for (int j = 0; j < coups.length; j++) {
				if (!coups[i].estattribue()) {
					coups[i].setAttribue(coups[i].getSender());
				}
				// comparaison
				if (coups[i].equalsdest(coups[j]) && !coups[j].estattribue()) {
					coups[j].setAttribue(coups[i].getSender());

				}
			}

			rules2isValide(coups[i]);
		}
		System.out.println("\t\t\tsortie  Traitement Table.rulesattribue");
	}

	/**
	 * rules2isValide( coups[i], TPoint[] pointNV) ; rules2isValide
	 *
	 * 
	 * @param Coup UnCoup
	 * @return boolean
	 */
	public boolean rules2isValide(UnCoup Coup) {
		// le test est realise dans netgame.CadrePr.jButton4_actionPerformed
		// MBF : plutot que de tester avec getnavire : tester ds structure
		// propre au cycle
		if (Coup.getPoint().PoinEstdansTab(getNavires()[Coup.getReceiver()])) {

			return true;
		} else {
			return false;

		}

	}

	private int nbCoupsCycleArecevoire;

	/**
	 * Validation
	 *
	 * @param coup UnCoup
	 * @return boolean
	 */
	public int variant = 0;

	public boolean ValidationWaitCycle(UnCoup coup) {
		PersoInfos p = (PersoInfos) this.get(coup.getSender());
		if (p.isAlive()) {

			if (rules2isValide(coup)) {
				setJoueurPointTouche(getnumTable(), coup.getReceiver(), coup.getPoint());
				coup.setTouche(1);

				rulenombredejoueurenvie(coup);

			} else {
				setJoueurPointPerdu(getnumTable(), coup.getReceiver(), coup.getPoint());

			}

			addCoupCycle(coup);
			variant++;

		}
		return isWaitCycle(variant);
	}

	/**
	 * rulenombredejoueurenvie
	 *
	 *
	 * @param coup UnCoup
	 * @return int
	 */
	public int rulenombredejoueurenvie(UnCoup coup) {
		if (listCoupsGlobale.isdead(this.getnumTable(), coup.getReceiver())) {
			System.out.println("le joueur " + getIdentityJoueur(coup.getReceiver()) + " est mort");
			appendTraceInfos("\n ---> \tle joueur " + getIdentityJoueur(coup.getReceiver()).getLogin() + " est mort");
			coup.setAlive(0);

			setAlive(coup.getReceiver(), false);

			setJoueurVivants(getJoueurVivants() - 1);
		}
		return getJoueurVivants();
	}

	void setAlive(int x, boolean alive) {
		PersoInfos O = (PersoInfos) get(x);
		O.setAlive(alive);
	}

	public void setAllAsAlive() {
		PersoInfos personne = null;
		Iterator i = this.iterator();
		while (i.hasNext()) {
			Object o = i.next();
			personne = (PersoInfos) o;
			personne.setAlive(true);
		}
	}

	public PersoInfos getTheWiner() {
		PersoInfos personne = null;
		Iterator i = this.iterator();
		while (i.hasNext()) {
			Object o = i.next();
			personne = (PersoInfos) o;
			if (personne.isAlive()) {
				return personne;
			}
		}
		return personne;
	}

	/**
	 * addCoupGlobale
	 *
	 * @param coup UnCoup
	 */
	public void addCoupGlobale(UnCoup coup) {
		if (coup.isTouche()) {
			setJoueurPointTouche(getnumTable(), coup.getReceiver(), coup.getPoint());

		} else {
			setJoueurPointPerdu(getnumTable(), coup.getReceiver(), coup.getPoint());

		}

	}

	/**
	 * @todo implementer la regle de decrementation des joueurs en vie
	 *
	 * @param coups UnCoup[] DERNIERE PHASE D4EVALUATION DOIT DETERMINER SI LES
	 *              JOUEURS ENCORE EN LICE SONT TOUJOURS VIVANT ET CE PAR PRIORITE
	 */
	public void rulenombredejoueurenvie_A_Revoir(UnCoup[] coups) {
		System.out.println("\t\t\t|______________________AV________________|");

		System.out.println("getlistCoupsduCycle().size() = " + getlistCoupsduCycle().size());

		System.out.println("getJoueurVivants() = " + getJoueurVivants());
		for (int i = 0; i < coups.length; i++) {
			// si celui contre qui le coup est deriger n'est pas marque mort danc marque
			// vivant
			if (coups[i].isAlive()) {

				// verifier a ce stade s'il est mort
				if (listCoupsGlobale.isdead(this.getnumTable(), coups[i].getReceiver())) {
					System.out.println("le joueur " + coups[i].getReceiver() + "est mort");

					coups[i].setAlive(0);
					setJoueurVivants(getJoueurVivants() - 1);

					System.out.println("\t\t\t|_____________________AP_________________|");
					System.out.println("getlistCoupsduCycle().size() = " + getlistCoupsduCycle().size());

					System.out.println("getJoueurVivants() = " + getJoueurVivants());
					// fin d'execution

					return;
				}
			}
		}

	}

	/**
	 * @todo implementer la regle de decrementation des joueurs en vie
	 *
	 * @param coups UnCoup[] DERNIERE PHASE D4EVALUATION DOIT DETERMINER SI LES
	 *              JOUEURS ENCORE EN LICE SONT TOUJOURS VIVANT ET CE PAR PRIORITE
	 */
	public void rulenombredejoueurenvie(UnCoup[] coups) {
		System.out.println("\t\t\t|entree  Traitement Table.rulenombredejoueurenvie");
		System.out.println("\t\t\t|______________________AV________________|");

		System.out.println(" Table rulenombredejoueurenvie :getJoueurVivants() = " + getJoueurVivants());
		setJoueurVivants(coups.length);
		System.out.println("Table rulenombredejoueurenvie :getJoueurVivants() = " + getJoueurVivants());
		for (int i = 0; i < coups.length; i++) {

			if (listCoupsGlobale.isdead(this.getnumTable(), coups[i].getReceiver())) {
				System.out.println("le joueur " + coups[i].getReceiver() + "est mort");

				coups[i].setAlive(0);
				setJoueurVivants(getJoueurVivants() - 1);

				System.out.println("\t\t\t|_____________________AP_________________|");
				System.out.println("getlistCoupsduCycle().size() = " + getlistCoupsduCycle().size());

				System.out.println("getJoueurVivants() = " + getJoueurVivants());
				// fin d'execution
				System.out.println("\t\t\t|sortie  par return  Traitement Table.rulenombredejoueurenvie");
				return;
			}
		}
		System.out.println("\t\t\t|sortie  normale  Traitement Table.rulenombredejoueurenvie");
	}

	/**
	 * @todo regle d'interdiction d'd'envoyer des coups une fois le joueur est mort
	 *
	 * @return boolean
	 */
	public boolean interditdacce() {
		return false;
	}

	/**
	 * remise a Zero Du Cycle En Cour
	 */
	public void remiseAZeroDesCoupsCycleEnCour() {
		/*
		 * System.out.println("avant remiseAZeroDuCycleEnCour le nembre de coups est " +
		 * this.listCoupsduCycle.size() + "__");
		 */
		setCoupDuCycleEnCour(0);
		this.listCoupsduCycle.clear();
		variant = 0;
		/*
		 * System.out.println("apres remiseAZeroDuCycleEnCour le nembre de coups" +
		 * this.listCoupsduCycle.size() + "__");
		 */
	}

	/**
	 * isThereaWiner
	 *
	 * @return boolean
	 */
	public boolean isThereaWiner() {

		return getJoueurVivants() == 0;
	}

	/**
	 * remise a zero et effacement de tous des donnee afficher
	 */
	public void reinitialisationdujeu() {

		listCoupsGlobale.reinitialiseAllTablesInfos();

		remiseAZeroDesCoupsCycleEnCour();
		setCycleEncour(0);
		setJoueurVivants(getNbjoueursPresent());
		this.setNbCoupsCycleArecevoire(getJoueurVivants());
	}

	public static void main(String[] args) {

		Table table1 = new Table(0, 4);

		System.out.println(table1.getJoueurMax());

	}

	/**
	 * getIdentityJoueur
	 *
	 * @param joueur int
	 * @return PersoInfos
	 */
	public PersoInfos getIdentityJoueur(int joueur) {
		return (PersoInfos) this.get(joueur);

	}

	/**
	 * getCurenNavirNavires
	 *
	 * @return TPoint[][]
	 */
	public TPoint[][] getCurenNavirNavires() {
		/*
		 * TPoint[][] nv= new TPoint[getJoueurMax()][getNbjeutonsParJoueur()]; for(int
		 * i=0;i<getJoueurMax();i++){ for(int
		 * x=0;x<listCoupsGlobale.getNavirJoueur(this.getnumTable(),i).length;x++){
		 * nv[i][x]= (TPoint)listCoupsGlobale.getNavirJoueur(this.getnumTable(),i)[x];
		 * 
		 * } }
		 */
		return getNavires();
	}

	/**
	 * addJoueur
	 *
	 *
	 * @param place      int
	 * @param persoInfos PersoInfos
	 */
	public void addJoueurOnClient(int place, PersoInfos persoInfos) {

		if (!isFull()) {

			this.add(place, persoInfos);
			/*
			 * System.out.println("Nombre de joueurs au tour de la table client est :" +
			 * this.size());
			 * 
			 * setNbjoueursPresent(this.size()); setJoueurVivants(getNbjoueursPresent());
			 * System.out.println("addJoueurOnClient : getJoueurMax( :" + getJoueurMax());
			 * System.out.println("addJoueurOnClient : getNbjoueursPresent() :" +
			 * getNbjoueursPresent());
			 * System.out.println("addJoueurOnClient : getJoueurVivants() :" +
			 * getJoueurVivants());
			 * 
			 */
		} else {
			System.out.println("addJoueur :  la Table sur le client est Pleine");
		}

	}

	/**
	 * addLogComments
	 *
	 * @param lesJoueurs String
	 * @param persoInfos String
	 */
	private void addLogComments(String lesJoueurs, String persoInfos) {

	}

	/**
	 * getJoueurPointsTouche
	 *
	 * @param joueur int
	 * @return int
	 */
	public int getNembrePointTouche(int joueur) {
		return listCoupsGlobale.getNembrePointTouche(this.getnumTable(), joueur);
	}

	/**
	 * getNembrePointResiduel
	 *
	 *
	 * @param joueu int
	 * @return int
	 */
	public int getNembrePointResiduel(int joueu) {
		return listCoupsGlobale.getNembrePointResiduel(this.getnumTable(), joueu);
	}

}
