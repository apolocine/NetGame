package Gestionnaire;

import java.awt.Dialog;

import commun_rc.PersoInfos;
import commun_rc.TPoint;
import commun_rc.UnCoup;

public interface Gestionnaire {
	public void man(TPoint Point);

	/**
	 * canRestartLape
	 *
	 * @return boolean
	 */
	public boolean canRestartLape();

	/**
	 * setRestartLape
	 *
	 * @param restartLape boolean
	 */
	public void setRestartLape(boolean restartLape);

	public void manCoup(UnCoup unCoup);

	public void setText(String str);

	public void setonClientInitGame(TPoint[][] Navires);

	/**
	 * addIDtitysinServer
	 *
	 * @param Perso PersoInfos
	 */
	public void addIDtitysinServer(PersoInfos Perso);

	/**
	 * setlocalClientAllIds
	 *
	 * @param persoInfoses PersoInfos[]
	 */
	public void setlocalClientAllIds(PersoInfos[] persoInfoses);

	/**
	 * usreseivedCoup
	 *
	 * @param unCoup UnCoup
	 */
	public void receptionCoupParServeur(UnCoup unCoup);

	/**
	 * getNbjoueur
	 *
	 * @return int
	 */
	public int getNbjoueur();

	/**
	 * getNbjeutonsParJoueur
	 *
	 * @return int
	 */
	public int getNbjeutonsParJoueur();

	/**
	 * setHit
	 *
	 * @param Point TPoint
	 */
	public void setHit(TPoint Point);

	/**
	 * setLocalInitGame
	 *
	 * @param MxPoint TPoint[][]
	 */
	public void setLocalInitGame(TPoint[][] MxPoint);

	/**
	 * getPersoID
	 *
	 * @return PersoInfos
	 */
	public PersoInfos getPersoID();

	/**
	 * getText
	 * 
	 * @return String
	 */
	public String getText();

	/**
	 * addItemtoStrList
	 *
	 * @param str String
	 */
	public void addItemtoStrList(String str);

	/**
	 * getTexttosend
	 *
	 * @return String
	 */
	public String getTexttosend();

	/**
	 * setPersoID
	 *
	 * @param persoID PersoInfos
	 */
	public void setPersoID(PersoInfos persoID);

	/**
	 * setsendCoup
	 *
	 * @param i      int
	 * @param i1     int
	 * @param tPoint TPoint
	 */
	public void setsendCoup(int i, int i1, TPoint tPoint);

	/**
	 * ClientOn
	 *
	 * @param i      int
	 * @param string String
	 */
	public void ClientOn(int i, String string);

	/**
	 * ServerOn
	 *
	 * @param i           int
	 * @param nembrJoueur int
	 */
	public void ServerOn(int i, int nembrJoueur);

	/**
	 * TableDeLaSalleinitMaxGame
	 */
	public void TableDeLaSalleinitMaxGame();

	/**
	 * SendTxtTo
	 *
	 * @param i int
	 */
	public void SendTxtTo(int i);

	/**
	 * receptionCoupParClient
	 *
	 * @param unCoup UnCoup
	 */
	public void receptionCoupParClient(UnCoup[] unCoup);

	/**
	 * iamconnected
	 *
	 * @param connectedr boolean
	 */
	public void iamconnected(boolean connectedr);

	/**
	 * sendDefault
	 */
	public void sendDefault();

	/**
	 * erreurMessage
	 *
	 * @param string String
	 */
	public void erreurMessage(String string);

	/**
	 * addLogComments
	 *
	 * @param col1 String
	 * @param col2 String
	 */
	public void addLogComments(String col1, String col2);

	/**
	 * showScorpMessage
	 *
	 * @param string String
	 */
	public void showScorpMessage(String string);

	/**
	 * setasServeur
	 *
	 * @param b boolean
	 */
	public void setasServeur(boolean b);

	/**
	 * getJoueurPersID
	 *
	 * @param i int
	 * @return PersoInfos
	 */
	public PersoInfos getJoueurPersID(int i);

	/**
	 * notifyReinitGameFromClient
	 *
	 */
	public void notifyReinitGameFromClient();

	public TPoint[][] getNavires();

	/**
	 * whereIam
	 *
	 * @return int
	 */
	public int whereIam();

	/**
	 * whoIam
	 *
	 * @return Object
	 */
	public PersoInfos whoIam();

	/**
	 * setFileNameLog
	 *
	 * @param string String
	 */
	public void setFileNameLog(String string);

	/**
	 * getDialogBoxLog
	 *
	 * @return Dialog
	 */
	public Dialog getDialogBoxLog();

	/**
	 * whoIs
	 *
	 * @param joueur int
	 * @return String
	 */
	public PersoInfos whoIs(int joueur);
}
