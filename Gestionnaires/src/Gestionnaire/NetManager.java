package Gestionnaire;

import commun_rc.PersoInfos;
import commun_rc.TPoint;
import commun_rc.UnCoup;

public abstract class NetManager {
	public Gestionnaire superviseur;

	/**
	 * NetManager
	 *
	 * @param gestionnaire Gestionnair
	 */
	public NetManager(Gestionnaire Parent) {
		this.superviseur = Parent;
	}

	public boolean ClientOn(int Port, String Host) {
		return false;
	}

	public void SendConfirmationScorpForServeur(int un) {
	}

	public void SendScoreForAllClents(String score) {
	}

	public void SendTxtTo(int indice) {
	}

	public boolean ServerOn(int Port, int NumClientAutorise) {
		return false;
	}

	public void addItemtoStrList(String str) {
	}

	public void erreurMessage(String str) {
	}

	public void forwardCoups(UnCoup[] unCoup) {
	}

	public void setcorrectMessage(String str) {
	}

	public String gettxtMessagetoSend() {
		return "Error from NetManager .gettxtMessagetoSend(); Methode mast bi inplemented in iniritante class ";
	}

	public String getLastcorrectMessage() {
		return "Error from NetManager .getLastcorrectMessage(); Methode mast bi inplemented in iniritante class ";
	}

	public String getLasterreurMessage() {
		return "Error from NetManager . getLasterreurMessage(); Methode mast bi inplemented in iniritante class ";
	}

	public String getMsgAceInterdit() {
		return "Error from NetManager .getMsgAceInterdit();  Methode mast bi inplemented in iniritante class ";
	}

	public void sendCoupForAll(UnCoup oneCoup) {
	}

	public void setsendCoup(UnCoup coup) {
	}

	public void sendHit(TPoint hit, int indice) {
	}

	public void sendId() {
	}

	public void sendUpdatetoClients(PersoInfos[] ALLPerso) {
	}

	public void sendinitMaxGame(TPoint[][] MxNavir) {
	}

	public void trtMsgrecuToClient(Object obj) {
	}

	public void trtMsgrecuToServer(Object obj) {
	}
}
