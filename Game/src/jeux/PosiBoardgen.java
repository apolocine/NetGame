package jeux;

import java.util.Random;

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
public class PosiBoardgen {

	// generateur aleatoire :
	private Random genAleatoire;
	int x, y, taille, limite;
	int xnull = -(x * taille + limite * taille);
	int ynull = -(y * taille + limite * taille);
	int caseParligne;
	int initX = 0;
	int initY = 0;
	int Ligne = 4;
	int Col = 18;
	int tailleCase = 20;
	int Limite = 10;

	public PosiBoardgen(int xp, int yp, int tailleC, int limitep, int CaseParligne) {
		x = xp;
		y = yp;
		taille = tailleC;
		limite = limitep;
		caseParligne = CaseParligne;
		genAleatoire = new Random();
	}

	public PosiBoardgen() {
		x = initX;
		y = initY;
		taille = tailleCase;
		limite = Limite;
		caseParligne = Col;
		genAleatoire = new Random();
	}

//generateur de case utilisables

	public TPoint[] choixDesNavir() {
		int choixL = this.genAleatoire.nextInt(4);
		int posMatrix = this.genAleatoire.nextInt(4);

		return choixDeBateaux(choixL, posMatrix);
	}

	public TPoint[] choixDeBateaux(int choixL, int posMatrix) {
		// M vas servir pour l'integration d'un vecteur valide de bateaux
		TPoint[][] M = Matrice.matricePoint(this.limite, this.limite, xnull, ynull);
//TMP vas servir pour la matrice transverce ou symetrique
		TPoint[][] TMP = Matrice.matricePoint(this.limite, this.limite, xnull, ynull);
		// le nouveau placement des bateaux;
		TPoint[] V = Matrice.vecteurPoint(caseParligne, xnull, ynull);

		M = Matrice.integrationVecteuDansMatrix(M, Matrice.MatrixChoisi(caseParligne, xnull, ynull)[choixL]);

		if (posMatrix == 1) {
			TMP = Matrice.transposePointsPlateau(M, xnull, ynull);
		} else if (posMatrix == 2) {
			TMP = Matrice.symetrieVerticale(M, xnull, ynull);
		} else if (posMatrix == 3) {
			TMP = Matrice.symetrieHorisontale(M, xnull, ynull);
		} else {
			TMP = M;
		}
		V = Matrice.extractionVNonNullMatrix(TMP, caseParligne, xnull, ynull);

		return V;

	}

	/**
	 *
	 * @param ligne
	 *              <p>
	 *              nembre de lignes
	 * @param col
	 *              <p>
	 *              nembre de colones de case
	 * @return
	 *         <p>
	 *         genere Matrice contenant des vecteurs qui contiennent les position de
	 *         bateux
	 */
	public TPoint[][] MatrixDesNavir(int ligne, int col) {
		TPoint[][] tmp = new TPoint[ligne][col];
		for (int i = 0; i < ligne; i++)
			tmp[i] = this.choixDesNavir();
		return tmp;
	}

	/**
	 *
	 * @param ligne
	 *              <p>
	 *              nembre de lignes
	 * @param col
	 *              <p>
	 *              nembre de colones de case
	 * @return
	 *         <p>
	 *         genere Matrice contenant des vecteurs qui contiennent les position de
	 *         bateux sous forme de String
	 */
	public String genAsStrMatrixDesNavir(int ligne, int col) {
		return new TPoint().toString(MatrixDesNavir(ligne, col));
	}

	/**
	 *
	 * @param Matrix
	 *               <p>
	 *               Matrice de points
	 * @return
	 *         <p>
	 *         converssion de @param en un String
	 */
	public String toStrMatrixDesNavir(TPoint[][] Matrix) {
		return new TPoint().toString(Matrix);
	}

	/**
	 *
	 * @param MStr
	 *              <p>
	 *              non nul contenant lePointTokenizer ( , )
	 * @param ligne
	 *              <p>
	 *              non nul
	 * @param col
	 *              <p>
	 *              non nul
	 * @return
	 *         <p>
	 *         converssion de MStr en une Matrix caracterise par ligne est colone
	 *         String
	 */
	public TPoint[][] StrToMatrixDesNavir(String MStr, int ligne, int col) {
		return new TPoint().toMPoint(MStr, ligne, col);
	}

	/**
	 *
	 * @param ligne
	 *              <p>
	 *              nembre de lignes
	 * @param col
	 *              <p>
	 *              nembre de colones de case
	 * @return
	 *         <p>
	 *         genere Matrice contenant des vecteurs qui contiennent les position de
	 *         bateux
	 */
	public TPoint[][] MatrixNullDesNavir(int ligne, int col) {
		TPoint[][] tmp = new Matrice().matricePoint(ligne, col);
		return tmp;
	}

	/**
	 * tet unitaire des vecteur est des matrices
	 * 
	 * @param arg
	 *            <p>
	 *            les donnee son deffinies dans le test
	 *            </p>
	 */
	public static void main(String[] arg) {
		PosiBoardgen posA = new PosiBoardgen(0, 0, 20, 10, 18);
		int ligne = 4;
		int col = 18;
		String MxasStr = posA.genAsStrMatrixDesNavir(ligne, col);
		System.out.println(MxasStr);
		/**
		 * resultas attention generation aleatoire des vecteur pas des positios des
		 * bateaux MxasStr =
		 * (1,1)(1,2)(3,2)(1,3)(3,3)(6,3)(7,3)(1,4)(3,4)(7,6)(7,7)(7,8)(0,9)(1,9)(2,9)(3,9)(4,9)(7,9)
		 * (1,8)(1,7)(1,6)(1,5)(1,0)(2,0)(3,9)(3,0)(4,9)(4,0)(5,9)(5,7)(6,9)(6,7)(7,9)(7,7)(7,2)(7,1)
		 * (3,1)(4,1)(5,1)(6,1)(6,2)(7,2)(8,2)(6,4)(2,5)(6,5)(2,6)(6,6)(2,7)(6,7)(2,8)(6,8)(8,8)(9,8)
		 * (3,1)(4,1)(5,1)(6,1)(6,2)(7,2)(8,2)(6,4)(2,5)(6,5)(2,6)(6,6)(2,7)(6,7)(2,8)(6,8)(8,8)(9,8)
		 */

		TPoint[][] Mx2asMx = posA.StrToMatrixDesNavir(MxasStr, ligne, col);

		System.out.println(posA.toStrMatrixDesNavir(Mx2asMx));
		/**
		 * resultas Mx2asMx =
		 * (1,1)(1,2)(3,2)(1,3)(3,3)(6,3)(7,3)(1,4)(3,4)(7,6)(7,7)(7,8)(0,9)(1,9)(2,9)(3,9)(4,9)(7,9)
		 * (1,8)(1,7)(1,6)(1,5)(1,0)(2,0)(3,9)(3,0)(4,9)(4,0)(5,9)(5,7)(6,9)(6,7)(7,9)(7,7)(7,2)(7,1)
		 * (3,1)(4,1)(5,1)(6,1)(6,2)(7,2)(8,2)(6,4)(2,5)(6,5)(2,6)(6,6)(2,7)(6,7)(2,8)(6,8)(8,8)(9,8)
		 * (3,1)(4,1)(5,1)(6,1)(6,2)(7,2)(8,2)(6,4)(2,5)(6,5)(2,6)(6,6)(2,7)(6,7)(2,8)(6,8)(8,8)(9,8)
		 */

		System.out.println(" bbbbbbbb");

	}

}
