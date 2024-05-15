package commun_rc;

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

public class MessageReseau {

	/**
	 * utlis� pour la trame en default
	 */
	TPoint[] VP0 = { new TPoint(0, -3), new TPoint(1, 0), new TPoint(2, 1) };

	TPoint[] VP1 = { new TPoint(6, 5), new TPoint(5, 4), new TPoint(4, 3) };
	TPoint[][] MP0 = { VP0, VP1 };

	PersoInfos perso = new PersoInfos();
	PersoInfos perso1 = new PersoInfos();
	PersoInfos[] persoMulti = { perso, perso1 };

	// nombre de champs de la MessageReseau
	private int fields = 13;

	private String[] fStrTrame = new String[fields];
	// s�parateur des champs de la trame
	private String prc1 = new Pasers().getPrc1();

	public int getMaxpersonne() {
		return Integer.parseInt(fStrTrame[12]);
	}

	private void setMaxpersonne(int maxPersonne) {
		fStrTrame[12] = "" + maxPersonne;
	}

//on ne change pas le type du message
//car il doit etre de type T_UPDATE correspond a multipersonne
	public void updateMaxpersonne(int maxPersonne) {
		setMaxpersonne(maxPersonne);

	}

	public MessageReseau() {
		for (int i = 0; i < fields; i++) {
			fStrTrame[i] = "1";
		}
		setDefaulNumTrame();
		fStrTrame[0] = "" + (-1);
		setTrame(1, perso, 1, new PersoInfos().toStringMulti(persoMulti), MP0, new TPoint(1, 5), "wait", "scorp",
				"text to send ", new UnCoup());

		this.setOneCoup(new UnCoup());
		// max personne initiale est a zero
		setMaxpersonne(0);
	}

	public MessageReseau(MessageReseau trame) {
		this();
		this.fStrTrame = trame.fStrTrame;
		setDefaulNumTrame();

	}

	public MessageReseau(String str) {

	}

	public MessageReseau(int type, int UserNum, PersoInfos UserID, int Numtrame, String ALLuserInfos,
			TPoint[][] McePoints, TPoint Point, String WaitScorp, String Scorp, String strMsg, UnCoup uncoup) {
		this.settype(type);
		setTrame(UserNum, UserID, Numtrame, ALLuserInfos, McePoints, Point, WaitScorp, Scorp, strMsg, uncoup);

	}

	public MessageReseau updateUserNum(int num) {
		this.settypeUserNum();
		setUserNum(num);
		return this;
	};

	/**
	 * updateMultiPerso
	 *
	 *
	 * 
	 * @param multiPerso PersoInfos[]
	 * @return Message
	 */
	public MessageReseau updateMultiPerso(PersoInfos[] multiPerso/* ,int maxPersonne */) {
		String str = "";
		this.settypeUpdateALLuser();
		str = new PersoInfos().toStringMulti(multiPerso);
		this.updateMaxpersonne(multiPerso.length);
		setALLuser(str);
		return this;
	}

	public MessageReseau upNumTrame() {
		int num = this.getNumtrame() + 1;
		setNumtrame(num);
		return this;
	};

	public MessageReseau updateUserID(PersoInfos user) {
		this.settypeConn();
		this.setUserID(user);
		return this;
	};

	public MessageReseau updateALLuser(PersoInfos[] users) {
		this.settypeUpdateALLuser();
		this.setALLuser(new PersoInfos().toStringMulti(users));

		return this;
	};

	public MessageReseau updateMxPoint(TPoint[][] point) {
		this.settypeInitGame();
		this.setMxPoint(point);
		return this;
	};

	public MessageReseau updateHit(TPoint point) {
		this.settypeHit();
		this.setHit(point);
		return this;
	};

	public MessageReseau updateScore(String Score) {
		this.settypeScore();
		this.setScore(Score);
		return this;

	};

	/**
	 * updateWait
	 *
	 * @param waitMsg String
	 * @return Message
	 */
	public MessageReseau updateWaitScorp(String waitMsg) {
		this.settypeWaitScorp();
		this.setWaitScore(waitMsg);
		return this;
	}

	public MessageReseau updatestrMsg(String str) {
		this.settypeStrMsg();
		this.setstrMsg(str);
		return this;
	};

	public MessageReseau updateOneCoup(UnCoup coup) {

		this.settypeOneCoup();
		this.setOneCoup(coup);
		return this;
	};

	public MessageReseau updateEndLape(UnCoup[] coups) {

		this.settypeEndLape();
		this.setCoupsEndLape(coups);
		return this;
	}

	public MessageReseau toTrame(String strrecu) {
		MessageReseau tmptrame = new MessageReseau();
		String[] strV = new String[fields];
		java.util.StringTokenizer TokV = new java.util.StringTokenizer(strrecu, prc1);
		int i = 0;
		try {
			i = 0;
			while (TokV.hasMoreElements()) {
				strV[i] = TokV.nextElement().toString();
				i++;
			}
		} catch (Exception ex) {

		}

		tmptrame.fStrTrame = strV;
		return tmptrame;
	}

	public String toString() {
		String Mot = "";

		Mot = this.gettypeMsg() + prc1 + this.getUserNum() + prc1 + this.getUserID() + prc1 + this.getNumtrame() + prc1
				+ this.getALLuser() + prc1 + this.getVecteurPoint() + prc1 + this.getHit().toString() + prc1
				+ this.getWaitScore() + prc1 + this.getScore() + prc1 + this.getstrMsg() + prc1 + this.getOneCoup()
				+ prc1 + this.getCoupsEndLapeAsStr() + prc1 + this.getMaxpersonne();

		return Mot;
	}

	private void settype(int type) {
		fStrTrame[0] = "" + type;
	};

	public void setUserNum(int str) {
		fStrTrame[1] = "" + str;
	};

	public void setUserID(PersoInfos id) {
		fStrTrame[2] = id.toString();
	};

	public void setNumtrame(int str) {
		fStrTrame[3] = "" + str;
	};

	public void setALLuser(PersoInfos[] users) {
		setALLuser(new PersoInfos().toStringMulti(users));
	};

	public void setALLuser(String str) {
		fStrTrame[4] = str;
	};

	public void setVecteurPoint(TPoint[] point) {

		String str = new TPoint().toString(point);
		fStrTrame[5] = str;
	};

	public void setMxPoint(TPoint[][] point) {
		String str = new TPoint().toString(point);
		fStrTrame[5] = str;
	};

	public void setHit(TPoint point) {

		fStrTrame[6] = point.toString();
	};

	public void setWaitScore(String str) {
		fStrTrame[7] = str;
	};

	public void setScore(String Scorp) {
		fStrTrame[8] = Scorp;
	};

	public void setstrMsg(String str) {
		fStrTrame[9] = str;
	};

	public void setOneCoup(UnCoup coup) {
		fStrTrame[10] = coup.toString();
	};

	public void setCoupsEndLape(UnCoup[] coups) {

		setMultiCoups(new UnCoup().toString(coups));

	}

	/**
	 * setMultiCoups
	 *
	 * @param str String
	 */
	private void setMultiCoups(String str) {
		fStrTrame[11] = str;
	}

	public int gettypeMsg() {
		return Integer.parseInt(fStrTrame[0]);
	};

	public int getUserNum() {
		return Integer.parseInt(fStrTrame[1]);
	};

	public PersoInfos getUserID() {
		return new PersoInfos(fStrTrame[2]);

	};

	/**
	 *
	 * @return
	 * <p>
	 *            a la place de Time ulterieurelment
	 */
	public int getNumtrame() {
		return Integer.parseInt(fStrTrame[3]);
	};

	public String getALLuser() {
		return fStrTrame[4];
	};

	public PersoInfos[] getAllUserID(int maxpersonnes) {

		return (new PersoInfos().toPersoInfos(fStrTrame[4], maxpersonnes));
	};

	public String getVecteurPoint() {
		return fStrTrame[5];
	};

	public TPoint[] getVecteurPoint(int nbrPn) {
		return new TPoint().toVPoint(fStrTrame[5], nbrPn);
	};

	public String getMxPoint() {
		return fStrTrame[5];
	};

	public TPoint[][] getMxPoint(int vecteur, int Case) {
		return new TPoint().toMPoint(fStrTrame[5], vecteur, Case);
	};

	public TPoint getHit() {
		return new TPoint(fStrTrame[6]);
	};

	public String getWaitScore() {
		return fStrTrame[7];
	};

	public String getScore() {
		return fStrTrame[8];
	};

	public String getstrMsg() {
		return fStrTrame[9];
	};

	public UnCoup getOneCoup() {

		return new UnCoup(fStrTrame[10]);
	}

	public UnCoup[] getCoupsEndLape() {

		return new UnCoup().toMultiCoups(fStrTrame[11]);

	}

	public String getCoupsEndLapeAsStr() {

		return fStrTrame[11];

	}

	public void setTrame(int UserNum, PersoInfos UserID, int Numtrame, String ALLuserInfos,
			// TPoint[] VecteurPoints,
			TPoint[][] McePoints,

			TPoint Point, String WaitScore, String Score, String strMsg, UnCoup uncoup

	) {

		if (this.getType() != -1) {
			setUserNum(UserNum);
			setUserID(UserID);
			setNumtrame(Numtrame);
			setALLuser(ALLuserInfos);
			// setVecteurPoint(VecteurPoints);
			setMxPoint(McePoints);
			setHit(Point);
			setWaitScore(WaitScore);
			setScore(Score);
			setstrMsg(strMsg);
			this.setOneCoup(uncoup);
		}
	}

	/*
	 * lenght permet d'avoir la talle MessageReseau
	 */
	public int length() {

		return this.toString().length();

	}

	int min(int x, int y) {
		if (x < y) {
			return x;
		} else {
			return y;
		}
	}

	int max(int x, int y) {
		if (x > y) {
			return x;
		} else {
			return y;
		}
	}

	/**
	 * * T_CONN connection et identification ; T_UPDATE information sur les autres
	 * utilisateurs; T_INITGAME information sur l'initiatisation du jeux (placement
	 * des bateuax ); T_HIT information sur le changement en cour li� a la sourie ;
	 * T_ENDLAPE fin du jeux attente du scorp ; T_SCORP resultas est scor T_END
	 * demande de r�initialisation de la partie pr�c�dante
	 */
	private int T_DEFAULT = -1;
	private int T_CONN = 0;
	private int T_UPDATE = 1;
	private int T_INITGAME = 2;
	private int T_HIT = 3;
	private int T_ENDLAPE = 4;
	private int T_SCORE = 5;
	private int T_ENDGAME = 6;
	private int T_STRMSG = 7;
	private int T_UNCOUP = 8;
	private int T_USRNUM = 9;
	private int T_WAIT = 10;

	public void settypeInitUsrNum() {
		settype(T_USRNUM);
	};

	public boolean isInitUsrNumtype() {
		return this.getUserNum() == T_USRNUM;
	}

	public void settypeUserNum() {
		settype(T_USRNUM);
	}

	public void settypeConn() {
		settype(T_CONN);
	};

	public void setDefaulNumTrame() {
		this.setNumtrame(T_DEFAULT);

	}

	public void settypeUpdateALLuser() {
		settype(T_UPDATE);
	};

	public void settypeInitGame() {
		settype(T_INITGAME);
	};

	public void settypeHit() {
		settype(T_HIT);
	};

	public void settypeEndLape() {
		settype(T_ENDLAPE);
	};

	/**
	 * settypeWait
	 */
	private void settypeWaitScorp() {
		settype(T_WAIT);
	}

	public void settypeScore() {
		settype(T_SCORE);
	};

	public void settypeEndGame() {
		settype(T_ENDGAME);
	};

	public void settypeStrMsg() {
		settype(T_STRMSG);
	};

	public void settypeOneCoup() {
		settype(T_UNCOUP);
	}

	public boolean isfirstTrame() {
		return this.getNumtrame() == 0;
	}

	public boolean isDefaultype() {
		return this.gettypeMsg() == T_DEFAULT;
	}

	public boolean isConntype() {
		return this.gettypeMsg() == T_CONN;
	}

	public boolean isUpdatetype() {
		return this.gettypeMsg() == T_UPDATE;
	}

	public boolean isInitGametype() {
		return this.gettypeMsg() == T_INITGAME;
	}

	public boolean isHittype() {
		return this.gettypeMsg() == T_HIT;
	}

	public boolean isEndLapetype() {
		return this.gettypeMsg() == T_ENDLAPE;
	}

	public boolean isScoretype() {
		return this.gettypeMsg() == T_SCORE;
	}

	public boolean isEndGametype() {
		return this.gettypeMsg() == T_ENDGAME;
	}

	public boolean isStrMsgtype() {
		return this.gettypeMsg() == T_STRMSG;
	}

	public boolean isUnCouptype() {
		return this.gettypeMsg() == T_UNCOUP;
	}

	/**
	 *
	 * @return
	 *         <p>
	 *         en r�flection
	 */
	public int getType() {
		return Integer.parseInt(fStrTrame[0]);
	}

	public String getTypeasstr() {
		int num = getType();
		String str = null;
		switch (num) {

		case -1:
			str = "T_DEFAULT";
			break;
		case 0:
			str = "  T_CONN ";
			break;
		case 1:
			str = " T_UPDATE  ";
			break;
		case 2:
			str = " T_INITGAME  ";
			break;
		case 3:
			str = " T_HIT  ";
			break;
		case 4:
			str = " T_ENDLAPE  ";
			break;
		case 5:
			str = " T_SCORP  ";
			break;
		case 6:
			str = "T_ENDGAME   ";
			break;
		case 7:
			str = " T_STRMSG  ";
			break;
		case 8:
			str = " T_UNCOUP  ";
			break;
		case 9:
			str = " T_USRNUM  ";
			break;
		case 10:
			str = "T_WAIT";
			break;

		}
		return str;
	}

	/**
	 *
	 * <p>
	 * Titre : NetGame
	 * </p>
	 * <p>
	 * Description :
	 * </p>
	 * <p>
	 * Copyright : Copyright (c) 2004
	 * </p>
	 * <p>
	 * Soci�t� :
	 * </p>
	 * 
	 * @author drmdh Madani Hamid
	 * @version 1.0
	 */
	class TestTrame {
		/**
		 * testSetTypes
		 * 
		 * @todo ce ci est un tests
		 * @param testtrm Trame
		 */
		public void testSetTypes(MessageReseau testtrm) {
			String type = testtrm.getTypeasstr();
			System.out.println("type is : " + type);
			testtrm.settypeConn();
			type = testtrm.getTypeasstr();
			System.out.println("type is : " + testtrm.getType() + type);
			testtrm.settypeEndGame();
			type = testtrm.getTypeasstr();
			System.out.println("type is : " + testtrm.getType() + type);
			testtrm.settypeEndLape();
			type = testtrm.getTypeasstr();
			System.out.println("type is : " + testtrm.getType() + type);
			testtrm.settypeHit();
			type = testtrm.getTypeasstr();
			System.out.println("type is : " + testtrm.getType() + type);
			testtrm.settypeInitGame();
			type = testtrm.getTypeasstr();
			System.out.println("type is : " + testtrm.getType() + type);
			testtrm.settypeOneCoup();
			type = testtrm.getTypeasstr();
			System.out.println("type is : " + testtrm.getType() + type);
			testtrm.settypeScore();
			type = testtrm.getTypeasstr();
			System.out.println("type is : " + testtrm.getType() + type);
			testtrm.settypeStrMsg();
			type = testtrm.getTypeasstr();
			System.out.println("type is : " + testtrm.getType() + type);
			testtrm.settypeUpdateALLuser();
			type = testtrm.getTypeasstr();
			System.out.println("type is : " + testtrm.getType() + type);
			testtrm.settypeUserNum();
			type = testtrm.getTypeasstr();
			System.out.println("type is : " + testtrm.getType() + type);
			testtrm.settypeInitUsrNum();
			type = testtrm.getTypeasstr();
			System.out.println("type is : " + testtrm.getType() + type);
		}

		/**
		 * testUpdates
		 *
		 * @param trameObj Trame
		 */
		public void testUpdates(MessageReseau trameObj) {
			trameObj.updateOneCoup(new UnCoup(2, 3, new TPoint(5, 6)));
			System.out.println("trameObj.getOneCoup()= : " + trameObj);
			System.out.println("trameObj.getOneCoup()= : " + trameObj.getOneCoup());
		}

		/**
		 * testUpNumTrame
		 *
		 * @param trameObj Trame
		 * @param max      int
		 */
		public void testUpNumTrame(MessageReseau trameObj, int max) {
			System.out.println("initiale : " + trameObj.getNumtrame());

			for (int i = 0; i < max; i++) {
				trameObj.upNumTrame();
				System.out.println("apr�s " + i + " fois :" + trameObj.getNumtrame());
			}
			System.out.println("a la fin : " + trameObj);
		}

		public TestTrame() {
		}
	}

	/**
	 * testtrame
	 */
	public void testtrame() {
		new TestTrame().testSetTypes(this);
		new TestTrame().testUpdates(this);
		int max = 4;
		new TestTrame().testUpNumTrame(this, max);

	}

	/**
	 * metode de test de la class Test Unitaire
	 * 
	 * @param arg
	 *            <p>
	 *            la trame en string
	 *            </p>
	 *
	 * @todo ce ci est un tests
	 */
	public static void main(String[] arg) {
		/*
		 * Trame tmp1=new Trame();
		 * System.out.println("\n --------- tmp1.testexception();----------\n ");
		 * 
		 * tmp1.testexception();
		 * 
		 * Trame tmp2= new Trame(tmp1); tmp2.updatestrMsg("hello  was udated");
		 * if(tmp2.isStrMsgtype()){
		 * System.out.println("\n --------- ( tmp2.getstrMsg());----------\n ");
		 * 
		 * System.out.println( tmp2.getstrMsg()); }
		 * System.out.println("\n --------- ( tmp2.toString());----------\n ");
		 * System.out.println( tmp2.toString());
		 * 
		 * 
		 * TPoint[] VP0=new TPoint[3]; VP0[0]=new TPoint(0,-3 ); VP0[1]=new TPoint(
		 * 1,0); VP0[2]=new TPoint(2,1 ); TPoint[] VP1=new TPoint[3]; VP1[0]=new
		 * TPoint(6,5 ); VP1[1]=new TPoint( 5,4); VP1[2]=new TPoint(4,3 ); TPoint[][]
		 * MP0={VP0,VP1};
		 * 
		 * PersoInfos perso = new PersoInfos("drmdh", "madani", "hamid",
		 * "drmdh@free.fr"); PersoInfos perso1 = new PersoInfos("faouzi", "bereksi",
		 * "faouzi", "faouzi@wanadoo.fr"); PersoInfos[] persoMulti={perso,perso1};
		 * 
		 * Trame t1 = new Trame(); t1.settypeStrMsg(); t1.setTrame(1 , perso , 1 ,new
		 * PersoInfos().toStringMulti(persoMulti) ,MP0 ,new TPoint(1,5) ,"wait" ,"scorp"
		 * , "text to send ");
		 * 
		 * 
		 * System.out.println(t1.toString());
		 * 
		 * System.out.println("\n -------------------\n "); if (t1.isStrMsgtype()) {
		 * System.out.println(t1.getstrMsg());
		 * 
		 * }
		 * 
		 * TPoint pt =new TPoint(); // String MPStr= TPoint[][]
		 * MP1=pt.toMPoint(pt.toString(MP0),MP0.length,MP0[0].length);
		 * 
		 * System.out.println("  MP1 "); System.out.println(pt.toString(MP1));
		 * System.out.println(" "); System.out.println(" "); System.out.println(" ");
		 * System.out.println(" ");
		 * 
		 */
		MessageReseau testtrm = new MessageReseau();
		System.out.println("testtrm.toString() :" + testtrm);

		testtrm.testtrame();
	}

}
