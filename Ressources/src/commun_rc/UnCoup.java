package commun_rc;

import java.util.StringTokenizer;

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
public class UnCoup {
	private int fields = 7;
	protected String parceII = new Pasers().getParceOP();
	protected String parceMultiCoup = new Pasers().getParceMultiCoup();
	private int sender;
	private int receiver;
	private String atribueA;
	private TPoint point;
	private int attribue;
	private int touche = 0;
	private int alive = 1;
	private int aCycle;

	private int posSender = 0;
	private int posReceiver = 1;
	private int posPoint = 2;
	private int posValeur = 3;
	private int posAcquis = 4;
	private int posaTimeCross = 5;
	private int posCycle = 5;

	public void setAttribueA(String atribueA) {
		this.atribueA = atribueA;
	}

	public void setAttribue(int attribue) {
		this.attribue = attribue;
	}

	public void setTouche(int touche_) {
		this.touche = touche_;
	}

	public void setAlive(int alive) {
		this.alive = alive;
	}

	public void setACycle(int aCycle) {
		this.aCycle = aCycle;
	}

	public String getAtribueA() {
		return atribueA;
	}

	public UnCoup(int sender_, int receiver_, TPoint point) {
		this.sender = sender_;
		this.receiver = receiver_;
		this.point = point;
		setAttribue(-1);
		this.setACycle(-1);
	}

	public UnCoup() {
		this.sender = -1;
		this.receiver = -1;
		point = new TPoint();
		setAttribue(-1);
		this.setACycle(-1);

	}

	public UnCoup(TPoint pointi) {
		this.sender = -1;
		this.receiver = -1;
		this.point = pointi;
		setAttribue(-1);
		this.setACycle(-1);
	}

	public UnCoup(String str) {
		StringTokenizer Tokppp = new StringTokenizer(str, parceII);
		String[] strppp = new String[fields];
		try {
			int z = 0;
			while (Tokppp.hasMoreElements()) {
				strppp[z] = Tokppp.nextElement().toString();
				z++;
			}
		} catch (Exception ex) {
		}
		this.sender = Integer.parseInt(strppp[posSender]);
		this.receiver = Integer.parseInt(strppp[posReceiver]);
		this.point = new TPoint(strppp[posPoint]);
		setAttribue(Integer.parseInt(strppp[posValeur]));
		this.setTouche(Integer.parseInt(strppp[posAcquis]));
		this.setAlive(Integer.parseInt(strppp[posaTimeCross]));
		this.setACycle(Integer.parseInt(strppp[posCycle]));

	}

	public int getSender() {
		return this.sender;
	}

	public int getReceiver() {
		return this.receiver;
	}

	public TPoint getPoint() {
		return point;
	}

	public int getAttribue() {
		return attribue;
	}

	public int getTouche() {
		return touche;
	}

	public int getAlive() {
		return alive;
	}

	public int getACycle() {
		return aCycle;
	}

	public String toString() {

		String str = "";
		str = this.sender + this.parceII + this.receiver + this.parceII + this.point + this.parceII + this.getAttribue()
				+ this.parceII + this.getTouche() + this.parceII + this.getAlive() + this.parceII + this.getACycle();
		return str;
	}

	/**
	 *
	 * @param str String
	 * @return UnCoup
	 */
	public UnCoup StrToCoup(String str) {

		StringTokenizer Tokppp = new StringTokenizer(str, parceII);
		String[] strppp = new String[fields];
		try {
			int z = 0;
			while (Tokppp.hasMoreElements()) {
				strppp[z] = Tokppp.nextElement().toString();
				z++;
			}
		} catch (Exception ex) {
		}
		this.sender = Integer.parseInt(strppp[posSender]);
		this.receiver = Integer.parseInt(strppp[posReceiver]);
		this.point = new TPoint(strppp[posPoint]);
		setAttribue(Integer.parseInt(strppp[posValeur]));
		this.setTouche(Integer.parseInt(strppp[posAcquis]));
		this.setAlive(Integer.parseInt(strppp[posaTimeCross]));
		this.setACycle(Integer.parseInt(strppp[posCycle]));
		return this;

	}

	public boolean estattribue() {
		return this.getAttribue() != -1;
	}

	/**
	 * equalse
	 *
	 *
	 * @param cp UnCoup
	 * @return boolean
	 */
	public boolean equalsdest(UnCoup cp) {
//    this.jTextArea1.setText( this.jTextArea1.getText()+"\nequalsedest(cp1 = "+cp1+"  ,  cp2 ="+cp2+");\n");
		return (this.getReceiver() == cp.getReceiver() && this.getPoint().equals(cp.getPoint()));
	}

	/**
	 * toString
	 *
	 * @param unCoups UnCoup[]
	 * @return String
	 */
	public String toString(UnCoup[] unCoups) {
		String str = "";
		for (int i = 0; i < unCoups.length; i++) {
			str = str + unCoups[i] + parceMultiCoup;
		}

		return str;
	}

	public UnCoup[] toMultiCoups(String str) {
		java.util.StringTokenizer TokparceMulti = new java.util.StringTokenizer(str, parceMultiCoup);

		UnCoup[] IDAp = new UnCoup[TokparceMulti.countTokens()];

		int i = 0;

		while (TokparceMulti.hasMoreElements()) {
			// if(i<TokparceMulti.countTokens())
			IDAp[i] = new UnCoup(TokparceMulti.nextElement().toString());
			i++;
		}

		return IDAp;
	}

	public static void main(String[] args) {
		UnCoup oneCoup1 = new UnCoup("1/2/(3,4)/-1/0/0/3");
		String str = oneCoup1.toString();
		System.out.println(str);
		System.out.println(oneCoup1.getAttribue());
		System.out.println(new UnCoup(str));
		oneCoup1.setAttribueA("drmdh");
		System.out.println(oneCoup1.getAtribueA());
		oneCoup1.setAttribue(5);
		System.out.println(oneCoup1.getAttribue());
		System.out.println("cycle " + oneCoup1.getACycle());
		UnCoup oneCoup2 = new UnCoup();
		UnCoup[] Multi = { oneCoup1, oneCoup2 };
		System.out.println("cycle " + oneCoup1.toString(Multi));
		System.out.println("cycle " + oneCoup2.getACycle());
	}

	/**
	 * isAcquis
	 *
	 * @return boolean
	 */
	public boolean isTouche() {
		return this.getTouche() == 1;
	}

	/**
	 * isAlive
	 *
	 * @return boolean
	 */
	public boolean isAlive() {
		return this.getAlive() == 1;
	}
}
