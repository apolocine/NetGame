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

public class MessageReseauGen// extends Trame
{

	MessageReseau trame;

	public MessageReseauGen() {
		trame = new MessageReseau();
	}

	/**
	 *
	 * @param ctrame Trame
	 */
	public MessageReseauGen(MessageReseau ctrame) {
		this.trame = ctrame;
	}

	/**
	 *
	 * @param perso PersoInfos
	 * @return Message
	 */
	public MessageReseau MakeUserNumtypeTrame(int perso) {
		return this.trame.updateUserNum(perso).upNumTrame();
	}

	/**
	 *
	 * @param persoID PersoInfos
	 * @return Message
	 * @todo MakeConntypeTrame est identique a MakePersUsertypeTrame
	 */
	public MessageReseau MakeConntypeTrame(PersoInfos persoID) {

		return this.trame.upNumTrame().updateUserID(persoID);
	}

	/**
	 * MakeMultiPersonInfos
	 * 
	 * @todo contoler
	 * @param multiPerso PersoInfos[]
	 * @return Message
	 */
	public MessageReseau MakeMultiPersonInfos(PersoInfos[] multiPerso/* ,int maxPersonne */) {
		return this.trame.upNumTrame().updateMultiPerso(multiPerso/* , multiPerso.length */);
	}

	/**
	 *
	 * @param MaxVavt TPoint[][]
	 * @return Message
	 */
	public MessageReseau MakeInitGametypeTrame(TPoint[][] MaxVavt) {

		return this.trame.upNumTrame().updateMxPoint(MaxVavt);
	}

	/**
	 *
	 * @param hit TPoint
	 * @return Message
	 */

	public MessageReseau MakeHittypeTrame(TPoint hit) {

		return this.trame.upNumTrame().updateHit(hit);
	}

	/**
	 * MakeWaittypeTrame
	 *
	 * @param waitMsg String
	 * @return Message
	 */
	public MessageReseau MakeWaittypeTrame(String waitMsg) {
		return this.trame.upNumTrame().updateWaitScorp(waitMsg);
	}

	/**
	 * MakeScorptypeTrame
	 *
	 * @param scorp String
	 * @return Message
	 */
	public MessageReseau MakeScorptypeTrame(String scorp) {
		return this.trame.upNumTrame().updateScore(scorp);
	}

	/**
	 *
	 * @param str String
	 * @return Message
	 */
	public MessageReseau MakeDefaultTrame(String str) {

		return this.trame.upNumTrame().updatestrMsg(str);
	}

	/**
	 *
	 * @param str String
	 * @return Message
	 */
	public MessageReseau MakeStrMsgtypeTrame(String str) {

		return this.trame.upNumTrame().updatestrMsg(str);
	}

	/**
	 * MakeCouptypeTrame
	 *
	 * @param coup UnCoup
	 * @return Message
	 * 
	 */
	public MessageReseau MakeCouptypeTrame(UnCoup coup) {

		return this.trame.upNumTrame().updateOneCoup(coup);
	}

	public void asType() {
		if (trame.isConntype()) {
		}
		if (trame.isUpdatetype()) {
		}
		if (trame.isInitGametype()) {
		}
		if (trame.isHittype()) {
		}
		if (trame.isEndLapetype()) {
		}
		if (trame.isScoretype()) {
		}
		if (trame.isEndGametype()) {
		}
		if (trame.isStrMsgtype()) {
		}
	}

	public void witchType(int type) {

		switch (type) {
		case 1:
			break;
		case 10:
			break;
		default:
			break;
		}
	}

	/**
	 * MakeCycleCoupTrame
	 *
	 * @param unCoups UnCoup[]
	 * @return Message
	 */
	public MessageReseau MakeEndLapeTrame(UnCoup[] unCoups) {

		return this.trame.upNumTrame().updateEndLape(unCoups);
	}

	/**
	 * @todo ce ci est un tests
	 * @param args String[]
	 */
	public static void main(String[] args) {
		/*
		 * int defaultnum = -1; //begin test1 Trame tramFF = new Trame(); TrameGen
		 * trameGen1 = new TrameGen(tramFF); System.out.println("" + tramFF.toString());
		 * 
		 * System.out.println("Trame num :" + tramFF.getNumtrame());
		 * 
		 */
		// trameGen1.MakeDefaultTrame( /*tramFF,*/" hello this is Make as
		// DefaultTrame\n");
		/*
		 * System.out.println("" + tramFF);
		 * 
		 * System.out.println("Trame num :" + tramFF.getNumtrame());
		 * trameGen1.MakeConntypeTrame(new PersoInfos());
		 * System.out.println(tramFF.toString() +
		 * "     was is Make as ConntypeTrame\n");
		 * 
		 * 
		 * TPoint[][] MxNavir = new PosiBoardgen().MatrixDesNavir(4 ,18);
		 * trameGen1.MakeInitGametypeTrame(MxNavir).toString();
		 * System.out.println(tramFF.toString());
		 * System.out.println(tramFF.getUserNum());
		 * System.out.println(tramFF.getType()+tramFF.getTypeasstr());
		 * 
		 * 
		 * System.out.println("Trame num :" + tramFF.getNumtrame());
		 * trameGen1.MakeWaittypeTrame( "   was is Make as WaittypeTrame\n").toString();
		 * System.out.println(tramFF.getType()+" "+tramFF.getTypeasstr());
		 * System.out.println(tramFF.toString());
		 * 
		 * 
		 * System.out.println("Trame num :" + tramFF.getNumtrame());
		 * trameGen1.MakeScorptypeTrame(
		 * "   was is Make as ScorptypeTrame\n").toString();
		 * System.out.println(tramFF.getType()+" "+tramFF.getTypeasstr());
		 * System.out.println(tramFF.toString()); System.out.println("Trame num :" +
		 * tramFF.getNumtrame());
		 * 
		 * 
		 * PersoInfos perso = new PersoInfos(defaultnum,"drmdh", "madani", "hamid",
		 * "drmdh@free.fr"); PersoInfos perso1 = new PersoInfos(defaultnum+1,"faouzi",
		 * "bereksi", "faouzi", "faouzi@wanadoo.fr"); PersoInfos[]
		 * persoMulti={perso,perso1};
		 * 
		 * trameGen1.MakeMultiPersonInfos( persoMulti).toString();
		 * System.out.println(tramFF.getType()+" "+tramFF.getTypeasstr());
		 * System.out.println(tramFF.toString()); System.out.println("Trame num :" +
		 * tramFF.getNumtrame());
		 * 
		 * int newPrs=4; trameGen1.MakeUserNumtypeTrame( newPrs).toString();
		 * System.out.println(tramFF.getType()+" "+tramFF.getTypeasstr());
		 * System.out.println(tramFF.toString()); System.out.println("Trame num :" +
		 * tramFF.getNumtrame());
		 * 
		 */
		// end test1

	}

}
