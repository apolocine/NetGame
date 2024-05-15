package commun_rc;

import java.util.LinkedList;

/**
 *
 * <p>
 * Titre : NetGame
 * </p>
 * <p>
 * Description : jeux en reseau Projet CDI
 * </p>
 * <p>
 * Copyright : Copyright (c) 2004
 * </p>
 * <p>
 * Societe : drmdh
 * </p>
 * 
 * @author hamid Madani
 * @version 1.0
 */

public class MatrixListe {
	private LinkedList Tables = new LinkedList();

//int posiVecteurPerdu indique la position du vecteur des points perdu
	// d'un joueur dans une Table
	//
	int nembreVecParJoueur = 3;
	private int posNavirs = 0;
	private int posTouche = 1;
	private int posiPerdu = 2;
	private int defMaxJoueur = 4;

	public MatrixListe() {
		createDefaultTable4Joueur4main();
		// test();
	}

	////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////

	public void createDefaultTable4Joueur4main() {
		creeLesTables(1);
		ajouterJoueurs(0, defMaxJoueur);
		ajouterNBtypeCoups(0, defMaxJoueur);
	}

	// la creation de la table avec un nembre definie de joueurs
	// des jetons par joueur represantant le navires
	// les points perdus
	// et les points Gagnie

	public void creeLesTables(int nembreTables) {
		for (int t = 0; t < nembreTables; t++) {
			Tables.add(new LinkedList());
		}
	}

	public void ajouterJoueurs(int numeroTable, int nbJoueurs) {
		LinkedList tmp = (LinkedList) Tables.get(numeroTable);
		for (int t = 0; t < nbJoueurs; t++) {
			tmp.add(new LinkedList());
		}
	}

	public void ajouterNBtypeCoups(int numeroTable, int nbrType) {
		LinkedList tmp = (LinkedList) Tables.get(numeroTable);
		int len = tmp.size();
		for (int i = 0; i < len; i++) {
			LinkedList tmpj = (LinkedList) tmp.get(i);
			for (int t = 0; t < nbrType; t++) {
				tmpj.add(new LinkedList());
			}
		}
	}

//// fin de creation de table de jeux

	public LinkedList getjetonList(int laTable, int lenbjoueur, int vec) {
		LinkedList latable = (LinkedList) Tables.get(laTable);
		LinkedList lejoueur = (LinkedList) latable.get(lenbjoueur);
		// LinkedList type1=(LinkedList)lejoueur.get(vec);
		// return type1;
		return (LinkedList) lejoueur.get(vec);
	}

	public void setNavirAllJoueurs(int laTable, TPoint[][] points) {
		for (int i = 0; i < points.length; i++) {
			setNavirJoueur(laTable, i, points[i]);
		}
	}

	// @ requires points != null;
	public void setNavirJoueur(int laTable, int lejoueur, TPoint[] points) {
		LinkedList type1 = getjetonList(laTable, lejoueur, posNavirs);
		// @ assume points!= null && points.length>0
		int i = 0;
		int n = points.length;
		// @ requires n >= 1;
		if (n > 0) {
			// @ loop_invariant (\forall int j; j>=0 & j <= i ==> type1[j] == points[j]);
			while (i != n) {
				type1.add(points[i]);
				i++;

			}
			// @ assert i==n && (\forall int j; j>=0 & j < n ==> type1[j] == points[j]);
		}

	}

	public void setJoueurPointTouche(int laTable, int lejoueur, TPoint point) {
		// LinkedList type1=getjetonList( laTable,lejoueur,posTouche) ;
		// pas de doublons dans les points touches
		if (!point.PoinEstdansTab(this.getJoueurPointsTouche(laTable, lejoueur))) {
			getjetonList(laTable, lejoueur, posTouche).add(point);

		}
	}

	public void setJoueurPointPerdu(int laTable, int lejoueur, TPoint point) {
		LinkedList type1 = getjetonList(laTable, lejoueur, posiPerdu);
		// pas de doublons dans les points perdus
		if (!point.PoinEstdansTab(this.getJoueurPointsPerdu(laTable, lejoueur))) {
			type1.add(point);
		}

	}

	// restitu sous forme de vecteur l'enssemble des jetons
	public Object[] getNavirJoueur(int laTable, int lejoueur) {
		return getjetonList(laTable, lejoueur, posNavirs).toArray();

	}

	public TPoint[] getNavirJoueurP(int laTable, int lejoueur) {
		int len = getnembredeCaseNavire(laTable, lejoueur);
		Object[] tmpB = getNavirJoueur(laTable, lejoueur);
		TPoint[] nvtmp = new TPoint[len];
		for (int i = 0; i < len; i++) {
			nvtmp[i] = (TPoint) tmpB[i];
		}
		return nvtmp;
	}

	public TPoint[][] getNavirJoueurP(int laTable) {
		TPoint[][] tmp = new TPoint[defMaxJoueur][];
		for (int i = 0; i < defMaxJoueur; i++) {
			tmp[i] = getNavirJoueurP(laTable, i);
		}
		return tmp;
	}

	public int getnembredeCaseNavire(int laTable, int lejoueur) {
		return getjetonList(laTable, lejoueur, posNavirs).size();
	}

	// restitu sous forme de vecteur l'enssemble des PointsTouche
	public Object[] getJoueurPointsTouche(int laTable, int lejoueur) {
		return getjetonList(laTable, lejoueur, posTouche).toArray();
	}

	public int getNembrePointTouche(int laTable, int lejoueur) {
		return getjetonList(laTable, lejoueur, posTouche).size();
	}

	// restitu sous forme de vecteur l'enssemble des points perdus
	public Object[] getJoueurPointsPerdu(int laTable, int lejoueur) {
		return getjetonList(laTable, lejoueur, posiPerdu).toArray();
	}

	// restitu sous forme de vecteur l'enssemble des points perdus
	public int getnPointsPerdu(int laTable, int lejoueur) {
		return getjetonList(laTable, lejoueur, posiPerdu).size();
	}

	public boolean isdead(int laTable, int lejoueur) {

		System.out.println(
				"getnembredeCaseNavire(" + laTable + "," + lejoueur + ") " + getnembredeCaseNavire(laTable, lejoueur));
		System.out.println(
				"getNembrePointTouche(" + laTable + "," + lejoueur + ") " + getNembrePointTouche(laTable, lejoueur));
		System.out.println("getnPointsPerdu(" + laTable + "," + lejoueur + ") " + getnPointsPerdu(laTable, lejoueur));

		// return getNembrePointTouche( laTable, lejoueur) >= getnembredeCaseNavire(
		// laTable, lejoueur);
		return isToucheAsNavirs(laTable, lejoueur);
	}

	public int getNembrePointPerdu(int laTable, int lejoueur) {
		return getjetonList(laTable, lejoueur, posiPerdu).size();
	}

	/**
	 *
	 * @param tables int
	 * @param joueur int
	 * @return boolean
	 */
	public boolean isToucheAsNavirs(int tables, int joueur) {
		return getNembreCoupTouche(tables, joueur) >= getNembreCaseNavir(tables, joueur);
	}

	public Object getJoueurPointPerdu(int laTable, int lenbjoueur, int point) {
		LinkedList type1 = getjetonList(laTable, lenbjoueur, posiPerdu);
		return type1.get(point);
	}

	public int getNembredeTable() {
		return Tables.size();
	}

	public int getNembredeJoueurTbl(int Tble) {
		LinkedList latable = (LinkedList) Tables.get(Tble);
		return latable.size();
	}

	public int getNembredeVecteurParJoueurTable(int laTable, int lejoueur) {
		LinkedList latablelkd = (LinkedList) Tables.get(laTable);
		LinkedList lejoueurlkd = (LinkedList) latablelkd.get(lejoueur);
		return lejoueurlkd.size();
	}

	public int getNembreCaseNavir(int laTable, int lenbjoueur) {
		return getjetonList(laTable, lenbjoueur, posNavirs).size();
	}

	public int getNembreCoupTouche(int laTable, int lenbjoueur) {
		return getjetonList(laTable, lenbjoueur, posTouche).size();
	}

	public int getNembreCoupPerdus(int laTable, int lenbjoueur) {
		return getjetonList(laTable, lenbjoueur, posiPerdu).size();
	}

//////////////////////////////////////////////////////////////////////////////

	public void test() {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				setJoueurPointPerdu(0, j, new TPoint());

			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println(getJoueurPointPerdu(0, j, i));
			}

		}

		Object[] p = this.getJoueurPointsPerdu(0, 1);
		for (int i = 0; i < p.length; i++) {
			System.out.println(p[i]);
		}

	}

	public void testNemres() {
		int nbtable = this.getNembredeTable();
		int nbjoueur = 0;
		System.out.println("nembre de table  :" + nbtable);
		for (int i = 0; i < nbtable; i++) {
			nbjoueur = this.getNembredeJoueurTbl(i);
			System.out.println("dans la table : " + i + " il y a  " + nbjoueur);
			for (int j = 0; j < nbjoueur; j++) {
				System.out.println("nembre de case du navir du joueur " + i + "  " + this.getNembreCaseNavir(i, j));
				System.out.println("nembre de point touche du joueur " + i + "  " + this.getNembreCoupTouche(i, j));
				System.out.println("nembre de point perdus du joueur " + i + "  " + this.getNembreCoupPerdus(i, j));
				System.out.println("\n");
			}
		}

	}

	public static void main(String[] args) {
		MatrixListe matrixListe1 = new MatrixListe();
		matrixListe1.test();
		matrixListe1.testNemres();
		matrixListe1.setJoueurPointPerdu(0, 1, new TPoint(3, 5));
		matrixListe1.testNemres();
		System.out.println("reinitialisation ");
		matrixListe1.reinitialiseAllTablesInfos();

		matrixListe1.testNemres();
		System.out.println();
		System.out.println();
	}

	public void reinitialiseAllTablesInfos() {
		for (int i = 0; i < Tables.size(); i++) {

			reinitialiseDonneeDesJoueurs(i);

		}

	}

	// reinitialisation des vecteur des joueurs de la table num latable
	public void reinitialiseDonneeDesJoueurs(int latable) {

		int nbjoueur = 0;
		int vec = 0;
		nbjoueur = this.getNembredeJoueurTbl(latable);
		for (int j = 0; j < nbjoueur; j++) {
			vec = getNembredeVecteurParJoueurTable(latable, j);
			for (int v = 0; v < vec; v++) {
				getjetonList(latable, j, v).clear();
			}

		}

	}

	/**
	 * getNembrePointResiduel
	 *
	 * @param laTable  int
	 * @param lejoueur int
	 * @return int
	 */
	public int getNembrePointResiduel(int laTable, int lejoueur) {
		int caseNave = getnembredeCaseNavire(laTable, lejoueur);
		int caseTouchees = getNembrePointTouche(laTable, lejoueur);
		int residu = caseNave - caseTouchees;
		/*
		 * System.out.println("Matrix Liste .getNembrePointResiduel :cases Navire  " +
		 * caseNave);
		 * System.out.println("Matrix Liste .getNembrePointResiduel :cases  Touchees  "
		 * + caseTouchees);
		 * System.out.println("Matrix Liste .getNembrePointResiduel :cases  residu  " +
		 * residu);
		 */
		return residu;
	}

}
