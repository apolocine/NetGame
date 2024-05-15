package Interface.interfaceJeux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import commun_rc.MatrixListe;
import commun_rc.TPoint;

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

public class BatailleNavale extends TPlateauJeu {

	static public JPanel appelant;

	// for double-buffering graphics
	static Dimension size;
	static Image buffer;
	static Graphics bufferGraphics;

	String Infos = " ";

	// class traitant l'affichage
	Affichage afficher;

	int joueur = 0;
	int aquiletour = 0;
	static int nembredecase = 0;
	static int indice0 = 0;
	static int indice1 = 0;

	public Color plateauColor = Color.WHITE;
	public Color CaseColor = Color.WHITE;
	public Color NavirColor = Color.ORANGE;
	public boolean plateauRaised;

	static MatrixListe JoueurTouchePerdu;

	int tables = 0;
	int NumJoueur = 4;
	int VNavirtouchesperdus = 4;
	int posNav = 0;
	int posTouche = 1;
	int posPerdu = 2;
	int posCoupSouri = 3;
	int caseParligne = 18;

	public BatailleNavale(int Joueur, JPanel Appelant, int x, int y, int limite, int tailleCase, boolean raised) {
		super(x, y, limite, tailleCase);
		setJoueur(Joueur);
		appelant = Appelant;

		afficher = new Affichage(this);
		setraised(raised);

	}

	public void initjeux() {
		initVecteursTouchePerdu();

	}

	public void initVecteursTouchePerdu() {
		JoueurTouchePerdu = new MatrixListe();

	}

	// methode de rafraechissement de la fenetre Infos
	public void paintComponent(Graphics graph) {

		initpaintBuf();

		graph.drawImage(buffer, X(), Y(), appelant);

	}

	public void initpaintBuf() {

		initImage();

		dessinePlateau(bufferGraphics);
	}

	// initialisation de l'image a affiche
	static public void initImage() {

		size = new Dimension(appelant.getWidth(), appelant.getHeight());
		buffer = appelant.createImage(size.width, size.height);
		bufferGraphics = buffer.getGraphics();
	}

	// on dessine le plateau de jeu
	public void dessinePlateau(Graphics graph) {
		// et on dessine en fonction de ce Graphics
		afficher.paint(this, graph, plateauColor, X(), Y(), tailleCase(), Limite(), true);
	}

	////////////////////////////////////
	// appete par class Affichage
	public void setNavirJoueur(int joueur, TPoint[] points) {
		JoueurTouchePerdu.setNavirJoueur(tables, joueur, points);
	}

	/**
	 * doit etre appele par l'arbitre
	 * 
	 * @param Nav TPoint[][]
	 */
	public void setNavirJoueurs(TPoint[][] Nav) {

		for (int i = 0; i < Nav.length; i++) {
			setNavirJoueur(i, Nav[i]);

		}
	}

//numero du joueur setJoueurTouchePointposTouche
	public void setJoueurPointTouche(int joueur, int coupIndice, TPoint point) {
		JoueurTouchePerdu.setJoueurPointTouche(tables, joueur, point);

	}

	boolean isFull(int max) {
		return JoueurTouchePerdu.getNembreCoupTouche(tables, this.getJoueur()) == max;
	}

	boolean isDead() {
		return JoueurTouchePerdu.isToucheAsNavirs(tables, this.getJoueur());
	}

	/**
	 * remise a zero et effacement de tous des donnee afficher
	 */
	public void reinitialisationdujeu() {
		JoueurTouchePerdu.reinitialiseAllTablesInfos();
	}

//numero du joueur setJoueurTouchePoint
	public void setJoueurPointTouche(int coupIndice, TPoint point) {
		setJoueurPointTouche(this.getJoueur(), coupIndice, point);

	}

	public void setJoueurPointPerdu(int joueur, int coup, TPoint point) {
		JoueurTouchePerdu.setJoueurPointPerdu(tables, joueur, point);
	}

	public void setJoueurPointPerdu(int coup, TPoint point) {
		setJoueurPointPerdu(this.getJoueur(), coup, point);
	}

	// public TPoint[] getNavirJoueur() est appele par la calss de l'Affichage
	public Object[] getNavirJoueur(int joueur) {
		return JoueurTouchePerdu.getNavirJoueur(tables, joueur);
	}

	public Object[] getJoueurPointsTouche(int joueur) {
		return JoueurTouchePerdu.getJoueurPointsTouche(tables, joueur);
	}

	public Object[] getJoueurPointsTouche() {
		return getJoueurPointsTouche(this.getJoueur());
	}

	public Object[] getJoueurPointsPerdu(int joueur) {
		return JoueurTouchePerdu.getJoueurPointsPerdu(tables, joueur);
	}

	// public TPoint[] getJoueurPointsPerdu() est appele par la calss de l'Affichage
	public Object[] getJoueurPointsPerdu() {
		return getJoueurPointsPerdu(this.getJoueur());

	}

	//////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/**
	 * coup d'envoi du jeux
	 */
	private boolean start = false;

	public void steStart(boolean startg) {
		this.start = startg;
	}

	public boolean getstart() {
		return this.start;
	}

	public void setintstart(TPoint[][] navirs, boolean Start) {

		try {
			if (Start) {
				setNavirJoueurs(navirs);

				appelant.repaint();
				this.start = Start;
			}
		} catch (Exception ex) {
		}

	}

	public void asmouscliked(MouseEvent event, int joueur) {

		setPcurrentLocation(event.getX(), event.getY());
	}

	public void showText(String str) {

		this.Infos = str;
	}

	/**
	 * Couleur du Plateau a Color plateau
	 * 
	 * @param plateau Color
	 */
	void setCouleurPlateau(Color plateau) {
		this.plateauColor = plateau;
	}

	/**
	 * Couleur de la Case
	 * 
	 * @param Case Color
	 */
	void setCouleurCase(Color Case) {
		this.CaseColor = Case;
	}

	/**
	 * dessin de la Couleur de la Case
	 * 
	 * @param raised boolean
	 */
	void setraised(boolean raised) {
		this.plateauRaised = raised;
	}

//numero du joueur
	public void setJoueur(int Joueur) {
		this.joueur = Joueur;

	}

	public int getJoueur() {
		return joueur;
	}

	TPoint currentLocation = new TPoint(-10, -10);

	public void setPcurrentLocation(int x, int y) {
		currentLocation = Souris(x, y);
		// System.out.println("BatailleNavale ligne 676 : "+ptest);
	}

	public void setPcurrentLocation(TPoint test) {
		currentLocation = Souris(test);
		// System.out.println("BatailleNavale ligne 681 : "+ptest);
	}

	public TPoint getPcurrentLocation() {
		return currentLocation;
	}

	public void setTextGameState(String str) {
		afficher.setInfos(str);
	}

	/**
	 * setintstart
	 *
	 *
	 * @param navirs TPoint[]
	 * @param b      boolean
	 */
	public void setintstart(TPoint[] navirs, boolean b) {

		try {
			// if (!this.start) {
			if (b) {
				setNavirJoueur(this.getJoueur(), navirs);
				appelant.repaint();
				this.start = b;
			}
		} catch (Exception ex) {
		}

	}

	/**
	 * pointDejaSelectione le point est deja selectionne s'il est dans le vecteur
	 * des point touche ou dans le vecteur des points perdus points perdus
	 *
	 *
	 * @param e      MouseEvent
	 * @param joueur int
	 * @return boolean
	 */
	public boolean pointDejaSelectione(MouseEvent e, int joueur) {
		TPoint p = new TPoint(e);
		// System.out.println("p = "+p);
		// System.out.println(" this.PointReel(p)= "+ this.PointPlateau(p));

		/*
		 * if(this.getJoueurPointsTouche(joueur).length!=0){
		 * System.out.println("this.getJoueurPointsTouche(joueur) = "+this.
		 * getJoueurPointsTouche(joueur)[0]);
		 * 
		 * }else{ System.out.println("this.getJoueurPointsTouche(joueur) ==  "+this.
		 * getJoueurPointsTouche(joueur).length); }
		 */
		return this.PointPlateau(p).PoinEstdansTab(this.getJoueurPointsPerdu(joueur))
				|| this.PointPlateau(p).PoinEstdansTab(this.getJoueurPointsTouche(joueur));

	}

	/**
	 * setTextWait
	 *
	 * @param string String
	 */
	public void setTextWait(String string) {
		afficher.setText2(string);
	}

}
