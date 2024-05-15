package commun_rc;

import java.awt.event.MouseEvent;

/**
 *
 * <p>
 * Titre : NetGame
 * </p>
 * <p>
 * Description : jeux en r�seau Projet CDI
 * </p>
 * <p>
 * Copyright : Copyright (c) 2004
 * </p>
 * <p>
 * Soci�t� : drmdh
 * </p>
 * 
 * @author hamid Madani
 * @version 1.0
 */

class TPointAll {
	private int x, y;

	public TPointAll() {
		x = 0;
		y = 0;
	}

	public TPointAll(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public TPointAll(double x, double y) {
		this.x = (int) x;
		this.y = (int) y;
	}

	public TPointAll(TPoint P) {
		x = P.X();
		y = P.Y();
	}

	public TPointAll(String P) {
		this(new PointTokenizer(P).getPoint());

	}

	/**
	 * TPointAll
	 *
	 *
	 * @param event MouseEvent
	 */

	public TPointAll(MouseEvent event) {
		this.x = event.getX();
		this.y = event.getY();
	}

	public int X() {
		return x;
	}

	public int Y() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setX(double x) {
		Double dtmp = new Double(x);
		this.x = dtmp.intValue();
	}

	public void setX(TPoint P) {
		x = P.X();
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setY(double y) {
		Double dtmp = new Double(y);
		this.y = dtmp.intValue();
	}

	public void setY(TPoint P) {
		y = P.Y();
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setXY(double x, double y) {
		Double dtmpx = new Double(x);
		Double dtmpy = new Double(y);
		this.x = dtmpx.intValue();
		this.y = dtmpy.intValue();
	}

	public void setXY(TPoint P) {
		x = P.X();
		y = P.Y();
	}

	public TPoint droit(int indice) {
		return new TPoint(this.X() + indice, this.Y());
	}

	public TPoint gauche(int indice) {
		return new TPoint(this.X() - indice, this.Y());
	}

	public TPoint haut(int indice) {
		return new TPoint(this.X(), this.Y() - indice);
	}

	public TPoint bas(int indice) {
		return new TPoint(this.X(), this.Y() + indice);
	}

	public TPoint basDroit(int indice) {
		return new TPoint(this.X() + indice, this.Y() + indice);
	}

	public TPoint hautGauche(int indice) {
		return new TPoint(this.X() - indice, this.Y() - indice);
	}

	public TPoint hautDroit(int indice) {
		return new TPoint(this.X() + indice, this.Y() - indice);
	}

	public TPoint basGauche(int indice) {
		return new TPoint(this.X() - indice, this.Y() + indice);
	}

	public String PointtoString(int indice) {
		String str = "";
		str = str + "M[ ici ][" + indice + "]=new TPoint(" + this.X() + "," + "" + this.Y() + ");";
		return str;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

}

public final class TPoint extends TPointAll {

	public TPoint() {
		super();

	}

	public TPoint(MouseEvent e) {
		super(e);

	}
 
	public TPoint(double x, double y) {
		super(x, y);

	}

	public TPoint(int x, int y) {
		super(x, y);
	}

	public TPoint(TPoint P) {
		super(P);

	}

	public TPoint(String P) {
		super(P);
	}

	public TPoint toPoint(String str) {
		return new PointTokenizer(str).getPoint();

	}

	public TPoint[] toVPoint(String str, int nbrPn) {
		return new PointTokenizer(str).getVPoint(nbrPn);

	}

	public String toString(TPoint[] VPoint) {
		String vp = "";
		for (int i = 0; i < VPoint.length; i++)
			vp = vp + VPoint[i].toString();
		return vp;
	}

	public TPoint[][] toMPoint(String str, int vecteur, int Case) {
		PointTokenizer tokMat = new PointTokenizer(str);

		TPoint[][] MV = new TPoint[vecteur][Case];
		for (int i = 0; i < MV.length; i++)
			MV[i] = tokMat.getVPoint(Case);

		return MV;
	}

	public String toString(TPoint[][] toMPoint) {
		String mp = "";
		for (int i = 0; i < toMPoint.length; i++)
			for (int j = 0; j < toMPoint[0].length; j++)
				mp = mp + toMPoint[i][j].toString();
		return mp;
	}

	public boolean PoinEstdansTab(TPoint[] tab) {
		boolean ok = false;

		for (int j = 0; j < tab.length;)
			if (!TPointEgal(tab[j])) {
				ok = false;
				j++;
			} else
				return true;

		return ok;
	}

	public boolean PoinEstdansTab(Object[] tab) {
		boolean ok = false;

		for (int j = 0; j < tab.length;)
			if (!TPointEgal((TPoint) tab[j])) {
				ok = false;
				j++;
			} else
				return true;

		return ok;
	}

	public boolean equals(TPoint P) {

		return ((X() == P.X()) && (Y() == P.Y()));
	}

	public boolean TPointEgal(TPoint P) {

		return equals(P);
	}

	/*
	 *
	 * 
	 * public static TPoint point=new TPoint(); public static TPoint Point(){ return
	 * point; }
	 *
	 */
	/**
	 * metode de test de la class Test Unitaire
	 * 
	 * @param arg
	 *            <p>
	 *            la trame en string
	 *            </p>
	 */
	public static void main(String[] arg) {

//Test TPoint en ton que static class
		// TPoint p=Point();
		// p.setX(5);p.setY(15);
		// System.out.println(" "+ p);

		TPoint[] VP0 = new TPoint[3];
		VP0[0] = new TPoint(0, -3);
		VP0[1] = new TPoint(1, 0);
		VP0[2] = new TPoint(2, 1);
		TPoint[] VP1 = new TPoint[3];
		VP1[0] = new TPoint(6, 5);
		VP1[1] = new TPoint(5, 4);
		VP1[2] = new TPoint(4, 3);
		TPoint[][] MP0 = { VP0, VP1 };

		System.out.println(new TPoint().toString(MP0));
		System.out.println(new TPoint().toString(VP0));

		TPoint pt = new TPoint();

		TPoint[][] MP1 = pt.toMPoint(pt.toString(MP0), MP0.length, MP0[0].length);

		System.out.println("  MP1 ");
		System.out.println(pt.toString(MP1));

		System.out.println(" ");
		System.out.println(" ");
	}

}
