package jeux;

import java.util.LinkedList;
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

public class Matrice {

	/** generateur aleatoire pour faouzi: */
	private Random genAleatoire;

	public Matrice() {
		genAleatoire = new Random();
	}

	// construction de la matrice de Points
	public TPoint[][] matricePoint(int L, int C) {
		TPoint[][] tab = new TPoint[L][C];
		for (int i = 0; i < L; i++)
			for (int j = 0; j < C; j++)
				tab[i][j] = new TPoint(-L * C, -L * C);
		return tab;
	}

	// construuction matriece de matrice LigneMatx=0;
	static TPoint[][][][] matrice2nMatrixPoint(int LigneMatx, int nMatrix, int L, int C, int x, int y) {

		TPoint tab[][][][] = new TPoint[LigneMatx][nMatrix][L][C];
		for (int N = 0; N < LigneMatx; N++) {
			for (int M = 0; M < nMatrix; M++) {
				tab[N][M] = matricePoint(L, C, x, y);
			}
		}

		return tab;
	}

//construction de la matrice de Points Attention a retester
	static TPoint[][] matricePoint(int L, int C, int x, int y) {
//construction de vecteur horizontal xxxxxx
		TPoint tab[][] = new TPoint[C][L];
		for (int i = 0; i < L; i++)
			tab[i] = vecteurPoint(C, x, y);
		return tab;
	}

	// construction d'Un vecteur de Points
	static TPoint[] vecteurPoint(int NbrCase, int x, int y) {
		TPoint tab[] = new TPoint[NbrCase];
		for (int j = 0; j < NbrCase; j++)
			tab[j] = new TPoint(x, y);

		return tab;
	}

	// construction d'un vecteur boolean tout a false;
	public boolean[] vecteurbool(int nembrecase, boolean VF) {
		boolean[] vecteurbool = new boolean[nembrecase];
		for (int i = 0; i < nembrecase; i++)
			vecteurbool[i] = VF;
		return vecteurbool;
	}

	/**
	 * creation d'une matrice carre de taille indice initialisee a zero
	 * 
	 * @param indice int
	 * @return int[][]
	 */
	public int[][] matriceCarre(int indice) {
		int matrice[][] = new int[indice][indice];
		for (int i = 0; i <= indice; i++) {
			for (int j = 0; j <= indice; j++) {
				matrice[i][j] = 0;
			}

		}
		return matrice;
	}

	/**
	 *
	 * creation d'une matrice carre de taille indice initialisee a zero
	 * 
	 * @param ligne int
	 * @param col   int
	 * @return int[][]
	 */
	public int[][] matrice(int ligne, int col) {
		int matrice[][] = new int[ligne][col];

		return matrice;
	}

	public int[] vecteurDirections(int Ligne) {
		int[][] Matrice = MatriceDirection();
		return Matrice[Ligne];
	}

	/**
	 * les direction Haut bas droit gauche
	 * 
	 * @return int[][]
	 */
	public int[][] MatriceDirection() {
		int MaxDirection = 9;
		// Matrice des Positions
		int[][] MP = matriceCarre(MaxDirection);
		MP[0][2] = 1;
		MP[0][3] = 1;
		MP[0][4] = 1;
		MP[0][5] = 1;
		MP[0][6] = 1;
		MP[1][4] = 1;
		MP[1][5] = 1;
		MP[1][6] = 1;
		MP[2][0] = 1;
		MP[2][4] = 1;
		MP[2][5] = 1;
		MP[2][6] = 1;
		MP[2][7] = 1;
		MP[3][0] = 1;
		MP[3][6] = 1;
		MP[3][7] = 1;
		MP[4][0] = 1;
		MP[4][1] = 1;
		MP[4][2] = 1;
		MP[4][6] = 1;
		MP[4][7] = 1;
		MP[5][0] = 1;
		MP[5][1] = 1;
		MP[5][2] = 1;
		MP[6][0] = 1;
		MP[6][1] = 1;
		MP[6][2] = 1;
		MP[6][3] = 1;
		MP[6][4] = 1;
		MP[7][2] = 1;
		MP[7][3] = 1;
		MP[7][4] = 1;
		for (int i = 0; i < MaxDirection; i++) {
			MP[8][i] = 1;
		}
		return MP;
	}

	public boolean estextreme(int[][] matrice, int[] directio, int L, int col) {
		boolean ok = true;

		if (L != 0 && L != matrice[0].length && col != 0 && col != matrice[0].length) {
			directio = vecteurDirections(8);
			ok = false;
		}
		if (L == 0) {
			if (col == matrice[0].length) {
				directio = vecteurDirections(1);
			}
			if (col == 0)
				directio = vecteurDirections(3);
		}

		if (L == matrice[0].length) {
			if (col == matrice[0].length) {
				directio = vecteurDirections(7);

			}
			if (col == 0) {
				directio = vecteurDirections(5);

			}
		}

		return ok;
	}

	/**
	 * return un vecteur de la taille de V1 avec les point d'intercection entre le
	 * vecteur V1 et le vecteur V2 le rest des points a la position horsOrigine
	 * 
	 * @param V1          TPoint[]
	 * @param V2          TPoint[]
	 * @param horsOrigine TPoint
	 * @return TPoint[]
	 */
	public TPoint[] intersection(TPoint[] V1, TPoint[] V2, TPoint horsOrigine) {
		TPoint[] VR = vecteurPoint(V1.length, -horsOrigine.X(), -horsOrigine.Y());
		for (int i = 0; i < V1.length; i++)
			for (int j = 0; j < V2.length; j++)
				if (V1[i] == V2[j])
					VR[i] = V1[i];
		return V1;
	}

	/**
	 * return un vecteur de la taille de V1 avec les point de bijection entre le
	 * vecteur V1 et le vecteur V2 le rest des points a la position horsOrigine
	 * 
	 * @param V1          TPoint[]
	 * @param V2          TPoint[]
	 * @param horsOrigine TPoint
	 * @return TPoint[]
	 */
	public TPoint[] bijection(TPoint[] V1, TPoint[] V2, TPoint horsOrigine) {
		TPoint[] VR = vecteurPoint(V1.length, -horsOrigine.X(), -horsOrigine.Y());
		for (int i = 0; i < V1.length; i++)
			for (int j = 0; j < V2.length; j++)
				if (V1[i] != V2[j])
					VR[i] = V1[i];
		return V1;
	}

	/**
	 * return vrais si P1 est dans le vecteur V
	 * 
	 * @param P1 TPoint
	 * @param V  TPoint[]
	 * @return boolean
	 */
	public static boolean PointestPresantdans(TPoint P1, TPoint[] V) {
		for (int j = 0; j < V.length; j++)
			if (P1.TPointEgal(V[j]))
				return true;
		return false;
	}

	/**
	 * return un vecteur de la taille de V1 fait de boolein vrais au intersection
	 * avec V2
	 * 
	 * @param V1 TPoint[]
	 * @param V2 TPoint[]
	 * @return boolean[]
	 */
	public boolean[] VecteurestPresantdans(TPoint[] V1, TPoint[] V2) {
		boolean[] vecteu = this.vecteurbool(V1.length, false);
		for (int i = 0; i < V1.length; i++)
			if (PointestPresantdans(V1[i], V2))
				vecteu[i] = true;
		return vecteu;
	}

	/**
	 * Matrix transpose
	 * 
	 * @param mat square matrix
	 * @return transposed matrix
	 * @author Ladd p222
	 */
	public static TPoint[][] transposePoints(TPoint[][] mat) {
		int j = mat[0].length;
		int k = mat.length;
		TPoint[][] result = new TPoint[j][k];
		for (int r = 0; r < k; r++)
			for (int i = 0; i < j; i++)
				result[i][r] = mat[r][i];
		return result;
	}

	/**
	 * Matrix transpose
	 * 
	 * @param mat   TPoint[][]
	 * @param xnull int
	 * @param ynull int
	 * @return TPoint[][]
	 */
	public static TPoint[][] transposePointsPlateau(TPoint[][] mat, int xnull, int ynull) {
		int j = mat[0].length;
		int k = mat.length;
		TPoint[][] result = matricePoint(k, j, xnull, xnull);
		for (int r = 0; r < k; r++)
			for (int i = 0; i < j; i++)
				if (mat[r][i].X() != xnull || mat[r][i].Y() != ynull)
					result[i][r] = mat[r][i];
		return result;
	}

	/**
	 * return the Verticale symetrique matrice of a square matrix
	 * 
	 * @param matrix TPoint[][]
	 * @param xnull  int
	 * @param ynull  int
	 * @return TPoint[][]
	 */
	public static TPoint[][] symetrieVerticale(TPoint[][] matrix, int xnull, int ynull) {
		int len = matrix.length;
		if (len != matrix[0].length)
			throw new ArithmeticException("Matrix.symetrieVerticale(): matrix is not square.");
		TPoint[][] symVert = matricePoint(len, len, xnull, xnull);
		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
				if (matrix[i][len - 1 - j].X() != xnull || matrix[i][len - 1 - j].Y() != ynull)
					symVert[i][j] = matrix[i][len - 1 - j];
		return symVert;
	}

	/**
	 * return the Verticale symetrique matrice of a square matrix
	 * 
	 * @param matrix TPoint[][]
	 * @param xnull  int
	 * @param ynull  int
	 * @return TPoint[][]
	 */
	public static TPoint[][] symetrieHorisontale(TPoint[][] matrix, int xnull, int ynull) {
		int len = matrix.length;
		if (len != matrix[0].length)
			throw new ArithmeticException("Matrix.symetrieVerticale(): matrix is not square.");
		TPoint[][] symVert = matricePoint(len, len, xnull, xnull);

		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
				if (matrix[i][j].X() != xnull || matrix[i][j].Y() != ynull)
					symVert[i][len - 1 - j] = matrix[i][j];
		return symVert;
	}

	public static TPoint[][] integrationVecteuDansMatrix(TPoint[][] mM, TPoint[] V) {
		for (int i = 0; i < V.length; i++) {
			mM[V[i].X()][(V[i].Y())] = V[i];
		}
		return mM;
	}

	public static TPoint[] extractionVNonNullMatrix(TPoint[][] M, int caseParligne, int xnull, int ynull) {

		TPoint[] V = vecteurPoint(caseParligne, xnull, ynull);
//Case  est la place dans le vecteur
		int CaseX = 0;
		for (int x = 0; x < M.length; x++) {
			for (int y = 0; y < M[0].length; y++) {
				if (M[x][y].X() != xnull || M[x][y].Y() != ynull) {
					V[CaseX] = M[x][y];
					CaseX++;
				}
			}
		}
		return V;
	}

	public static TPoint[][] MatrixChoisi(int caseParligne, int xnull, int ynull) {
		TPoint[][] M = matricePoint(5, caseParligne, xnull, ynull);

		// deux de deux point
		M[0][0] = new TPoint(9, 8);
		M[0][1] = new TPoint(8, 8);
		// troi de troi point
		M[0][2] = new TPoint(7, 2);
		M[0][3] = new TPoint(8, 2);
		M[0][4] = new TPoint(6, 2); // quatre de quatre point)
		M[0][5] = new TPoint(2, 8);
		M[0][6] = new TPoint(2, 7);
		M[0][7] = new TPoint(2, 6);
		M[0][8] = new TPoint(2, 5);
		// quatre de qutre point (
		M[0][9] = new TPoint(6, 1);
		M[0][10] = new TPoint(5, 1);
		M[0][11] = new TPoint(4, 1);
		M[0][12] = new TPoint(3, 1);
		// cinq de cinq point (
		M[0][13] = new TPoint(6, 8);
		M[0][14] = new TPoint(6, 7);
		M[0][15] = new TPoint(6, 6);
		M[0][16] = new TPoint(6, 5);
		M[0][17] = new TPoint(6, 4);
		////////////////////////
		M[1][0] = new TPoint(7, 1);
		M[1][1] = new TPoint(6, 1);
		M[1][2] = new TPoint(7, 5);
		M[1][3] = new TPoint(7, 6);
		M[1][4] = new TPoint(7, 7);
		M[1][5] = new TPoint(1, 8);
		M[1][6] = new TPoint(2, 8);
		M[1][7] = new TPoint(3, 8);
		M[1][8] = new TPoint(4, 8);
		M[1][9] = new TPoint(1, 6);
		M[1][10] = new TPoint(1, 5);
		M[1][11] = new TPoint(1, 4);
		M[1][12] = new TPoint(1, 3);
		M[1][13] = new TPoint(1, 2);
		M[1][14] = new TPoint(0, 0);
		M[1][15] = new TPoint(1, 0);
		M[1][16] = new TPoint(2, 0);
		M[1][17] = new TPoint(3, 0);

		/////////////////////////////////
		M[2][0] = new TPoint(7, 1);
		M[2][1] = new TPoint(7, 2);
		M[2][2] = new TPoint(5, 7);
		M[2][3] = new TPoint(6, 7);
		M[2][4] = new TPoint(7, 7);
		M[2][5] = new TPoint(1, 8);
		M[2][6] = new TPoint(1, 7);
		M[2][7] = new TPoint(1, 6);
		M[2][8] = new TPoint(1, 5);
		M[2][9] = new TPoint(3, 9);
		M[2][10] = new TPoint(4, 9);
		M[2][11] = new TPoint(5, 9);
		M[2][12] = new TPoint(6, 9);
		M[2][13] = new TPoint(7, 9);
		M[2][14] = new TPoint(1, 0);
		M[2][15] = new TPoint(2, 0);
		M[2][16] = new TPoint(3, 0);
		M[2][17] = new TPoint(4, 0);

		////////////////////////////////////

		M[3][0] = new TPoint(6, 3);
		M[3][1] = new TPoint(7, 3);
		M[3][2] = new TPoint(3, 2);
		M[3][3] = new TPoint(3, 3);
		M[3][4] = new TPoint(3, 4);
		M[3][5] = new TPoint(7, 7);
		M[3][6] = new TPoint(7, 6);
		M[3][7] = new TPoint(7, 8);
		M[3][8] = new TPoint(7, 9);
		M[3][9] = new TPoint(1, 1);
		M[3][10] = new TPoint(1, 2);
		M[3][11] = new TPoint(1, 3);
		M[3][12] = new TPoint(1, 4);
		M[3][13] = new TPoint(3, 9);
		M[3][14] = new TPoint(2, 9);
		M[3][15] = new TPoint(1, 9);
		M[3][16] = new TPoint(0, 9);
		M[3][17] = new TPoint(4, 9);
		//////////////////////////////////////////

		M[4][0] = new TPoint(6, 3);
		M[4][1] = new TPoint(6, 4);
		M[4][2] = new TPoint(5, 8);
		M[4][3] = new TPoint(4, 8);
		M[4][4] = new TPoint(3, 8);
		M[4][5] = new TPoint(8, 6);
		M[4][6] = new TPoint(8, 5);
		M[4][7] = new TPoint(8, 7);
		M[4][8] = new TPoint(8, 8);
		M[4][9] = new TPoint(1, 1);
		M[4][10] = new TPoint(1, 2);
		M[4][11] = new TPoint(1, 3);
		M[4][12] = new TPoint(1, 4);
		M[4][13] = new TPoint(5, 0);
		M[4][14] = new TPoint(6, 0);
		M[4][15] = new TPoint(7, 0);
		M[4][16] = new TPoint(8, 0);
		M[4][17] = new TPoint(4, 0);

		return M;
	}

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

	private class MatrixListe {
		/**
		 * matrice2nMatrixPoint( int LigneMatx,int nMatrix, int L,int C,int x,int y)
		 */
		LinkedList lkd[][][];

		/**
		 * creation de des differente boites des points toucher perdu et les jettons
		 * distribue par table et par joueurpar joueur
		 * 
		 * @param table        int
		 * @param joueursTable int
		 * @param main         int
		 * @return LinkedList[][][]
		 */
		LinkedList[][][] makelists(int table, int joueursTable, int main) {
			for (int t = 0; t <= table; t++) {
				for (int j = 0; j <= joueursTable; j++) {
					for (int m = 0; m <= main; m++) {
						lkd[t][j][m] = new LinkedList();
					}
				}
			}
			return lkd;
		}

		LinkedList[][][] makelists(int joueursTable, int main) {
			for (int t = 0; t <= 0; t++) {
				for (int j = 0; j <= joueursTable; j++) {
					for (int m = 0; m <= main; m++) {
						lkd[t][j][m] = new LinkedList();
					}
				}
			}
			return lkd;
		}

		MatrixListe() {
		}
	}

}
