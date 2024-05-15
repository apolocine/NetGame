package commun_rc;

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
 
public class PersoInfos {
	// nembre de champ d'identification
	private int numID = 8;
	private String[] strID;
	// separateur entre les chanos d'identitees
	private String parce = new Pasers().getParce();// "S[S";
	private String parceMulti = new Pasers().getParceMulti();
	private java.util.StringTokenizer TokV;

	private void intArd() {
		strID = new String[numID];
		for (int j = 0; j < numID; j++) {
			strID[j] = "";
		}
		strID[0] = "-1";
		strID[6] = "1";
		strID[7] = "0";
	}

	public PersoInfos() {
		intArd();
		setInfos(-1, "drmdh", "madani", "hamid", "drmdh@free.fr");

	}

	public PersoInfos(String str) {
		intArd();

		this.unTok(str);
	}

	public PersoInfos(int num, String Login, String Nom, String Prenom, String Mail) {
		intArd();
		setInfos(num, Login, Nom, Prenom, Mail);
	}

	public void unTok(String str) {
		TokV = new java.util.StringTokenizer(str, parce);
		int i = 0;
		while (TokV.hasMoreElements()) {
			// if(i<TokV.countTokens())
			Object o = TokV.nextElement();

			strID[i] = o.toString();
			i++;
		}

	}

	public void setInfos(int num, String Login, String Nom, String Prenom, String Mail) {
		setNum(num);
		setLogin(Login);
		setNom(Nom);
		setPnom(Prenom);
		setMail(Mail);
		setAlive(true);

	}

	/**
	 * setNum
	 *
	 * @param num int
	 */
	public void setScore(int Score) {

		strID[posScore] = "" + Score;
	}

	/**
	 * setNum
	 *
	 * @param num int
	 */
	public void setNum(int num) {

		strID[posNum] = "" + num;
	}

	public void setLogin(String str) {
		strID[posLogin] = str;
	}

	public void setNom(String str) {
		strID[posNom] = str;
	}

	public void setPnom(String str) {
		strID[posPnom] = str;
	}

	public void setMail(String str) {
		strID[posMail] = str;
	}

	/**
	 * setAlive
	 *
	 * @param alive int
	 */
	public void setAlive(boolean alive) {
		if (alive) {
			strID[posaAlive] = "" + 1;
		} else {
			strID[posaAlive] = "" + 0;
		}

	}

	private int getAlive() {
		int num = 0;
		try {
			num = Integer.parseInt(strID[posaAlive]);
		} catch (NumberFormatException ex) {
		}
		return num;

	}

	public boolean isAlive() {
		return getAlive() == 1;
	}

	/**
	 * setNum
	 *
	 * @param num int
	 */
	public int getScore() {
		int num = 0;
		try {
			num = Integer.parseInt(strID[posScore]);
		} catch (NumberFormatException ex) {
		}
		return num;

	}

	private String getNumasStr() {
		return strID[posNum];
	}

	public int getNum() {
		int num = 0;
		try {
			num = Integer.parseInt(strID[posNum]);
		} catch (NumberFormatException ex) {
		}
		return num;
	}

	public String getLogin() {
		return strID[posLogin];
	}

	public String getNom() {
		return strID[posNom];
	}

	public String getPnom() {
		return strID[posPnom];
	}

	public String getMail() {
		return strID[posMail];
	}

	public String toString() {
		String MID = "";
		/*
		 * for (int i = 0; i < numID; i++) { MID = MID + strID[i] + parce; }
		 * 
		 */
		MID = strID[posNum] + parce + strID[posLogin] + parce + strID[posNom] + parce + strID[posPnom] + parce
				+ strID[posMail] + parce + strID[posaAlive] + parce + strID[posScore];
		;
		return MID;
	}

	public String toStringID() {
		String MID = "";
		/*
		 * for (int i = 0; i < numID; i++) { MID = MID + strID[i] + parce; }
		 * 
		 */
		MID = strID[posNum] + "\t" + strID[posLogin] + "\t" + strID[posNom] + "\t" + strID[posPnom] + "\t"
				+ strID[posMail] + "\t" + strID[posaAlive] + "\t" + strID[posScore];
		return MID;
	}

	public PersoInfos[] toPersoInfos(String str, int nbrpers) {
		java.util.StringTokenizer TokparceMulti = new java.util.StringTokenizer(str, parceMulti);
		PersoInfos[] IDAp = new PersoInfos[nbrpers];

		int i = 0;

		while (TokparceMulti.hasMoreElements()) {
			// if(i<TokparceMulti.countTokens())
			IDAp[i] = new PersoInfos(TokparceMulti.nextElement().toString());
			i++;
		}

		return IDAp;
	}

	public String toStringMulti(PersoInfos[] personnes) {
		String str = "";

		for (int i = 0; i < personnes.length; i++)
			str = str + personnes[i].toString() + parceMulti;
		return str;
	}

	private int posNum = 0;
	private int posLogin = 1;
	private int posNom = 2;
	private int posPnom = 3;
	private int posMail = 4;
	private int posaAlive = 5;
	private int posScore = 5;

	/**
	 * <p>
	 * metode de test du PersoInfos<|p>
	 * 
	 * @param args
	 *             <p>
	 *             metode de test du PersoInfos<|p>
	 */
	public static void main(String[] args) {
		int defaultnum = -1;
		PersoInfos perso = new PersoInfos(defaultnum, "drmdh", "madani", "hamid", "drmdh@free.fr");
		System.out.print("----perso.toString()-----------------------------------------\n");
		System.out.println(perso.toString());
		System.out.println("----perso1.get()-----------------------------------------\n");
		System.out.println(perso.getNum() + perso.getNumasStr());
		System.out.println(perso.getLogin());
		System.out.println(perso.getNom());
		System.out.println(perso.getPnom());
		System.out.println(perso.getMail());
		PersoInfos perso1 = new PersoInfos(perso.toString());
		System.out.println("----perso1.toString()-----------------------------------------\n");
		System.out.println(perso1.toString());

		System.out.println("----perso1.get()-----------------------------------------\n");
		System.out.println(perso.getNum() + perso.getNumasStr());
		System.out.println(perso1.getLogin());
		System.out.println(perso1.getNom());
		System.out.println(perso1.getPnom());
		System.out.println(perso1.getMail());
		int max = 3;
		PersoInfos[] persoMulti = new PersoInfos[max];
		persoMulti[0] = perso;
		persoMulti[1] = perso1;
		persoMulti[2] = new PersoInfos(33, "faousi", "bereksi", "mohamed faouzi", "bibiche");
		String Allperson = new PersoInfos().toStringMulti(persoMulti);
		System.out.print("----perso.toStringAll()-----------------------------------------\n");

		System.out.println(Allperson);
		System.out.print("-new PersoInfos().toPersoInfos(Allperson,persoMulti.length)--------\n");
		PersoInfos[] persoM2 = new PersoInfos().toPersoInfos(Allperson, max);
		for (int i = 0; i < max; i++)
			System.out.println(persoM2[i].toString());

	}
}
