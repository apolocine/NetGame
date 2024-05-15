package Interface.interfaceJeux;

import Interface.resources.TVersion;
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
abstract class TPlateauJeu {

	// les variables privees :
	// la limite du plateau de jeu
	private int limite;

	// les coordonnees graphiques (xp,yp) et la taille des cases
	private int xp, yp, tailleCase;
	// la version de la classe
	public TVersion version = new TVersion(1.15, "Plateau de jeu", "Drmdh", 2004);

	public int Limite()
	// acces e la variable privee limite
	{
		return limite;
	}

	public void setLimite(int limite)
	// modification de la limite du plateau de jeu
	{
		this.limite = limite;

	}

	public TPlateauJeu()
	// construteur-defaut du plateau de jeu
	{
		xp = 0;
		yp = 0;
		limite = 8;
		tailleCase = 50;

	}

	public TPlateauJeu(TPlateauJeu T)
	// constructeur-copie d'un plateau de jeu (TPlateauJeu)
	{
		xp = T.X();
		yp = T.Y();
		limite = T.Limite();
		tailleCase = T.tailleCase();

	}

	public TPlateauJeu(int limite)
	// constructeur-limite du plateau de jeu
	{
		xp = 0;
		yp = 0;
		this.limite = limite;
		tailleCase = 50;

	}

	public TPlateauJeu(int x, int y)
	// constructeur du plateau de jeu (x,y)
	{
		xp = x;
		yp = y;
		limite = 8;
		tailleCase = 50;

	}

	public TPlateauJeu(TPoint P)
	// constructeur du plateau de jeu (TPoint P)
	{
		xp = P.X();
		yp = P.Y();
		limite = 8;
		tailleCase = 50;

	}

	public TPlateauJeu(int x, int y, int tailleCase)
	// constructeur du plateau de jeu (x,y,tailleCase)
	{
		xp = x;
		yp = y;
		limite = 8;
		this.tailleCase = tailleCase;

	}

	public TPlateauJeu(TPoint P, int tailleCase)
	// constructeur du plateau de jeu (TPoint P,tailleCase)
	{
		xp = P.X();
		yp = P.Y();
		limite = 8;
		this.tailleCase = tailleCase;

	}

	public TPlateauJeu(int x, int y, int limite, int tailleCase)
	// constructeur du plateau de jeu (x,y,limite,tailleCase)
	{
		xp = x;
		yp = y;
		this.limite = limite;
		this.tailleCase = tailleCase;

	}

	public TPlateauJeu(TPoint P, int limite, int tailleCase)
	// constructeur du plateau de jeu (TPoint P,limite,tailleCase)
	{
		xp = P.X();
		yp = P.Y();
		this.limite = limite;
		this.tailleCase = tailleCase;

	}

	public int X()
	// acces e la variable privee xp
	{
		return xp;
	}

	public int Y()
	// acces e la variable privee yp
	{
		return yp;
	}

	public void setX(int x)
	// modification de la variable xp
	{
		xp = x;
	}

	public void setY(int y)
	// modification de la variable yp
	{
		yp = y;
	}

	public void setXY(int x, int y)
	// modification des variables (x,y)
	{
		xp = x;
		yp = y;
	}

	public int tailleCase()
	// acces e la variable privee tailleCase
	{
		return tailleCase;
	}

	public void setTailleCase(int tailleCase)
	// modification du tailleCase
	{
		this.tailleCase = tailleCase;
	}

	public void setXYTailleCase(int x, int y, int tailleCase)
	// modification de (x,y,tailleCase)
	{
		xp = x;
		yp = y;
		this.tailleCase = tailleCase;
	}

	public void setTout(int x, int y, int limite, int tailleCase)
	// modification generale (x,y,limite,tailleCase)
	{
		xp = x;
		yp = y;
		this.limite = limite;
		this.tailleCase = tailleCase;

	}

	public TPoint Souris(int x, int y)
	// calcul des coordonnees de la souris sur le plateau de jeu
	{
		return new TPoint((x - xp) / tailleCase, (y - yp) / tailleCase);
	}

	public TPoint Souris(TPoint P)
	// calcul des coordonnees de la souris sur le plateau de jeu
	{
		return new TPoint((P.X() - xp) / tailleCase, (P.Y() - yp) / tailleCase);
	}

	public TPoint PointPlateau(TPoint P)
	// calcul des coordonnees de la souris sur le plateau de jeu
	{
		return Souris(P);
	}

	public TPoint PointReel(int col, int ligne)
	// calcul des coordonnees de la souris sur le plateau de jeu
	{
		return new TPoint(xp + (col * tailleCase), yp + (ligne * tailleCase));

	}

	public TPoint PointReel(TPoint P)
	// calcul des coordonnees de la souris sur le plateau de jeu
	{
		return new TPoint(xp + (P.X() - Limite()), yp + (P.Y() - Limite()));
	}

	// la souris survole-t-elle sur le plateau ?
	public boolean SourisSurPlateau(int x, int y) {
		return ((x > X()) && (x < (X() + Limite() * tailleCase())) && (y > Y())
				&& (y < (Y() + Limite() * tailleCase())));
	}

	// la souris survole-t-elle sur le plateau ?
	public boolean SourisSurPlateau(TPoint P) {
		return SourisSurPlateau(P.X(), P.Y());
	}

	public TPoint getPointDelaCase(int ligne, int col) {

		return new TPoint(X() + ligne * this.tailleCase(), Y() + col * this.tailleCase());
	}

	public TPoint getPCase(int x, int y)
	// acces au TPoint de case (TPoint) du plateau de jeu
	{
		TPoint point;
		if (CaseValide(x, y))
			point = new TPoint(x, y);
		else
			point = new TPoint();
		return point;
	}

	public TPoint[][] getMatricePoint() {
		TPoint PlateauJeup[][] = new TPoint[this.limite][this.limite];
		for (int L = 0; L < this.limite; L++) {
			for (int C = 0; C < this.limite; C++) {
				PlateauJeup[L][C] = getPointDelaCase(L, C);
				// PlateauJeup[L][C]=new TPoint(this.xp+C*this.tailleCase(),
				// yp+L*tailleCase());

			}

		}
		return PlateauJeup;
	}

	public boolean CaseValide(int x, int y)
	// verification de la validite d'une case : est-elle sur le plateau de jeu ?
	{
		return ((x >= 0) && (x < limite) && (y >= 0) && (y < limite));
	}

	public boolean CaseValide(TPoint P)
	// verification de la validite d'une case : est-elle sur le plateau de jeu ?
	{
		return CaseValide(P.X(), P.Y());
	}

	public String Version()
	// acces e la version de TPlateauJeu
	{
		return version.toString();
	}

}
