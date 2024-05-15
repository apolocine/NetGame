package reseau;

import Gestionnaire.Gestionnaire;
import Gestionnaire.NetManager;
import commun_rc.MessageReseau;
import commun_rc.MessageReseauGen;
import commun_rc.PersoInfos;
import commun_rc.TPoint;
import commun_rc.UnCoup;

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
public class GestionDuReseau extends NetManager {

	public Serveur serveur;
	public Client client;
	int port;
	String host;
	int numclientautorise = 5;

	/* la varriable isserver permet la mise en route du serveur */
	public boolean isServer = false;
	public boolean connected = false;

	MessageReseau messagerecu = new MessageReseau();
	MessageReseau messageenvoye = new MessageReseau();
	MessageReseauGen msgGen = new MessageReseauGen(new MessageReseau());

	public GestionDuReseau(Gestionnaire Parent) {
		super(Parent);

	}

	public void setPort(int Port) {
		this.port = Port;
	}

	public void setHost(String Host) {
		this.host = Host;
	}

	public void setNumClientAutorise(int Numclientautorise) {
		numclientautorise = Numclientautorise;
	}

	public boolean ClientOn(int Port, String Host) {
		this.setPort(Port);
		this.setHost(Host);
		isServer = false;
		try {
			client = new Client(port, host, this);
			superviseur.iamconnected(true);
			connected = true;
		} catch (Exception ex) {
			connected = false;
		}
		return client.isConnected();
	}

	public boolean ServerOn(int Port, int NumClientAutorise) {
		this.setPort(Port);
		this.setNumClientAutorise(NumClientAutorise);
		isServer = true;

		try {
			serveur = new Serveur(this.port, 150, numclientautorise, this, true);
			superviseur.iamconnected(true);

			connected = true;
		} catch (Exception ex) {
			connected = false;
			isServer = false;
		}
		return serveur.isConnected();
	}

	private void writeToClient(int numero, String str) {
		try {
			serveur.writeTo(str, numero);

		} catch (Exception srv) {

		}

	}

	private void writeToAllClient(String str) {
		serveur.writeToAllClient((Object) str);

	}

	private void writeToServer(String str) {

		try {
			client.writeTrame((String) str);
			;
		} catch (Exception ex) {

		}

	}

	public void trtMsgrecuToServer(Object obj) {
		/**/
		String str = (String) obj;
		try {
			messagerecu = new MessageReseau().toTrame(str);
			// System.out.println("Trame num :" + messagerecu.getNumtrame());
			// addLogComments(" MessageReseau Num :", "" + messagerecu.getNumtrame());
			// System.out.print(" UserNum : " + messagerecu.getUserNum());
			// addLogComments("le joueur :", "" + messagerecu.getUserNum());
			// System.out.print(" UserID login : " + messagerecu.getUserID().getLogin());
			// addLogComments(" UserID login :", messagerecu.getUserID().getLogin());
			// System.out.print(" Trame num :" + messagerecu.getTypeasstr());
			// addLogComments(" MessageReseau Num :", messagerecu.getTypeasstr());
		} catch (Exception ex) {
		}

		try {
			/**
			 * * @todo isConntype()) doit etre traiter par le server qui ce rand compte
			 * d'une nouvel connection
			 */
			if (messagerecu.isConntype()) {

				setInServerIDs(messagerecu.getUserID());
				addLogComments("Le jouer  :   " + messagerecu.getUserID().getLogin(), "vient de se connecter ");
				// inutile de poursuivre les autres clauses de verification
				return;

			}

			/**
			 * doit etre traiter par le client mise a jour de la list des joueurs
			 **/
			if (messagerecu.isUpdatetype()) {
				this.addLogComments(
						"un  client a trensmie une mose e jour  de la liste des de l'enssemble" + "des client", "");
				// inutile de poursuivre les autres clauses de verification
				return;

			}
// Seul le serveur peut envoyer msg de type initGame sinon erreur
			if (messagerecu.isInitGametype()) {

				this.addLogComments("un client tente d'initialise le jeu ", "");
				// inutile de poursuivre les autres clauses de verification
				return;

			}

			if (messagerecu.isHittype()) {

				setHit(messagerecu.getHit());
				superviseur.man(messagerecu.getHit());
				writeToAllClient(str);

				// inutile de poursuivre les autres clauses de verification
				return;

			}
			if (messagerecu.isUnCouptype()) {
				/**
				 * receprion des coups part le srveur_
				 */
				superviseur.receptionCoupParServeur(messagerecu.getOneCoup());
				addLogComments(
						"reception d'un coup emis par  "
								+ this.superviseur.whoIs(messagerecu.getOneCoup().getSender()).getLogin() + " contre ",
						this.superviseur.whoIs(messagerecu.getOneCoup().getReceiver()).getLogin() + " sur la case "
								+ messagerecu.getOneCoup().getPoint());

				// inutile de poursuivre les autres clauses de verification
				return;
			}
			if (messagerecu.isEndLapetype()) {
				setcorrectMessage(messagerecu.getWaitScore());

				// inutile de poursuivre les autres clauses de verification
				return;

			}
			if (messagerecu.isScoretype()) {
				setcorrectMessage(messagerecu.getScore());
				addLogComments("reception du score emis par un client :  ", messagerecu.getScore());
				// inutile de poursuivre les autres clauses de verification
				return;

			}
			if (messagerecu.isEndGametype()) {
				setcorrectMessage("end game ");
				addLogComments("reception d'un message du jeu  :  ", messagerecu.getstrMsg());
				// inutile de poursuivre les autres clauses de verification
				return;

			}
			if (messagerecu.isStrMsgtype()) {
				// init game reeue par le serveur traduie une reinitialisation du jeu
				// apres une fin d'une encienne parie
				if (messagerecu.getstrMsg().equals("can restart")) {
					superviseur.notifyReinitGameFromClient();

					addLogComments("confirmation de reception du score par le joueur   ", messagerecu.getScore());

				}

				setcorrectMessage(messagerecu.getstrMsg());
				// inutile de poursuivre les autres clauses de verification
				return;
			}
			// else this.erreurMessage("attention il n'y a pas de type pour cette
			// MessageReseau");

			// assert (messagerecu.isDefaultype());else else else else else else else else
		} catch (Exception ex1) {

		}

	}

	/**
	 * forwardCoups
	 *
	 * @param unCoup UnCoup
	 */
	public void forwardCoups(UnCoup[] unCoup) {
		sendCycleCoupForAll(unCoup);

		/*
		 * System.out.print(this.getClass().getName() + " MessageReseau  num :" + new
		 * UnCoup().toString(unCoup));
		 */
		// addLogComments("diffusion des coup du cycle en cour ", new
		// UnCoup().toString(unCoup));
	}

	/**
	 * sendCycleCoupForAll
	 *
	 * @param unCoup UnCoup[]
	 */
	private void sendCycleCoupForAll(UnCoup[] unCoup) {
		if (isServer) {

//envoyer la disposition des bateauxserveur.
			writeToAllClient(msgGen.MakeEndLapeTrame(unCoup).toString());

		}

	}

	public void trtMsgrecuToClient(Object obj) {
		/**/
		String str = (String) obj;
		try {
			messagerecu = new MessageReseau().toTrame(str);
		} catch (Exception ex) {
		}

		try {

			if (messagerecu.isUpdatetype()) {
				setlocalClientAllIds(messagerecu.getAllUserID(messagerecu.getMaxpersonne()));

				// inutile de poursuivre les autres clauses de verification
				return;
			}
			if (messagerecu.isInitGametype()) {

				//// test

				superviseur.setonClientInitGame(
						messagerecu.getMxPoint(superviseur.getNbjoueur(), superviseur.getNbjeutonsParJoueur()));

				// inutile de poursuivre les autres clauses de verification
				return;
			}
			if (messagerecu.isHittype()) {
				setHit(messagerecu.getHit());
				superviseur.man(messagerecu.getHit());
				addLogComments("reception d'un hit: de ", messagerecu.getUserID().getLogin());
				// inutile de poursuivre les autres clauses de verification
				return;
			}
			if (messagerecu.isUnCouptype()) {
				/**
				 * @todo _________________a renommer
				 * 
				 *       superviseur. receptionCoupParClient(tramerecu.getOneCoup());
				 */

				superviseur.manCoup(messagerecu.getOneCoup());

				// inutile de poursuivre les autres clauses de verification
				return;
			}
			if (messagerecu.isEndLapetype()) {

				superviseur.receptionCoupParClient(messagerecu.getCoupsEndLape());

				// inutile de poursuivre les autres clauses de verification
				return;
			}
			if (messagerecu.isScoretype()) {
				try {
					// affichage scorp
					showScorpMessage(messagerecu.getScore());
				} catch (Exception ex2) {
				}

				finally {
					// envoi d'une confirmation de reception du scorp
					// valeur 1 pour accepter de rejouer par default
					SendConfirmationScorpForServeur(1);
					addLogComments(" reception du scorp  :  ", messagerecu.getScore());

				}

				// inutile de poursuivre les autres clauses de verification
				return;
			}
			if (messagerecu.isEndGametype()) {

				setcorrectMessage("end game ");
				addLogComments("reception fin du jeu  :  ", messagerecu.getScore());
				// inutile de poursuivre les autres clauses de verification
				return;
			}
			if (messagerecu.isStrMsgtype()) {
				setcorrectMessage(messagerecu.getstrMsg());
				addLogComments("reception d'un message du jeu  :  ", messagerecu.getstrMsg());
				// inutile de poursuivre les autres clauses de verification
				return;
			}
			// else this.erreurMessage("attention il n'y a pas de type pour ce
			// MessageReseau");

			// assert (messagerecu.isDefaultype());else else else else else else else else
		} catch (Exception ex1) {

		}

	}

	/**
	 * showScorpMessage
	 *
	 * @param str String
	 */
	private void showScorpMessage(String str) {
		superviseur.showScorpMessage(str);
	}

	/**
	 * addLogComments
	 *
	 * @param string  String
	 * @param string1 String
	 */
	private void addLogComments(String string, String string1) {

		this.superviseur.addLogComments(string, string1);
	}

	/**
	 * sendCoupForAll
	 *
	 * @param oneCoup OneCoup
	 */
	public void sendCoupForAll(UnCoup oneCoup) {
		if (isServer) {
			sendCoupTrame(oneCoup);
		}

	}

	/**
	 * sendCoupTrame
	 *
	 * @param oneCoup UnCoup
	 * @todo verifie s'il ny a pas de feadback intencive
	 */
	private void sendCoupTrame(UnCoup oneCoup) {

		if (isServer) {
			writeToAllClient(msgGen.MakeCouptypeTrame(oneCoup).toString());

			System.out.println(this.messageenvoye.toString());
			setcorrectMessage("send from server " + oneCoup.toString());

		} else {
			/**
			 * try { parent.rPlateaudejeuL.bBatailleNavale.setPtest(oneCoup.getPoint());
			 * writeToServer(trameGen.MakeCouptypeTrame(trameenvoye, oneCoup).toString());
			 * setIsCoupChanged(false); setcorrectMessage("from Client"+tmpcoup.toString());
			 * System.out.println(this.tmpcoup.toString()); if(isCoupChanged()){ }
			 * 
			 * } catch (Exception econ) { this.erreurMessage("verifier si vous ete
			 * connecte\n " + econ); }
			 * 
			 */
		}

	}

	/**
	 * setsendCoup enregistrement et envoie du coup selectionne par le joueur
	 *
	 * @param coup UnCoup
	 */
	public void setsendCoup(UnCoup coup) {

		if (isServer) {
			writeToAllClient(msgGen.MakeCouptypeTrame(coup).toString());

			setcorrectMessage(" Gestionair du reseau L 356 server as client send :" + coup.toString());
		} else {
			writeToServer(msgGen.MakeCouptypeTrame(coup).toString());

			setcorrectMessage(" Gestionair du reseau L 360 Clien send :" + coup.toString());
		}

	}

	/**
	 *
	 * @param Point TPoint
	 */
	public void setHit(TPoint Point) {
		superviseur.setHit(Point);

	}

	/**
	 *
	 * @param hit    TPoint
	 * @param indice int
	 */
	public void sendHit(TPoint hit, int indice) {
		if (isServer) {
			writeToAllClient(msgGen.MakeHittypeTrame(hit).toString());
			System.out.println(hit + "was sent by server");
		} else {
			try {
				writeToServer(msgGen.MakeHittypeTrame(hit).toString());
				System.out.println(hit + "was sent by client");
			} catch (Exception econ) {
				this.erreurMessage("verifier si vous ete connecte\n " + econ);
			}
		}

	}

	public void SendTxtTo(int indice) {
		if (isServer) {
			// writeToAllClientExcept(indice);
			writeToClient(indice, getTrameasStr());
		} else {
			try {
				writeToServer(getTrameasStr());
			} catch (Exception econ) {
				this.erreurMessage("verifier si vous ete connecte \n" + econ);
			}
		}

	}

	// identite prise en comptpte est envoyer par le client
	// le serveur n'a pas a en voyer sa propre identity
	//
	public void setInServerIDs(PersoInfos Perso) {
		superviseur.addIDtitysinServer(Perso);

	}

	/**
	 * setlocalClientAllIds
	 * 
	 * @param persoInfoses PersoInfos[]
	 */
	private void setlocalClientAllIds(PersoInfos[] persoInfoses) {

		superviseur.setlocalClientAllIds(persoInfoses);

	}

	/**
	 * sendUpdatetoClients
	 * 
	 * @todo attention a verrifier est tester trameGen.MakeMultiPersonInfos()
	 *       probleme de recurciite sendinitMaxGame();
	 */
	/**
	 *
	 * @param ALLPerso PersoInfos[]
	 */
	public void sendUpdatetoClients(PersoInfos[] ALLPerso) {

		writeToAllClient(msgGen.MakeMultiPersonInfos(ALLPerso).toString());

	}

	public String getTrameasStr() {

		try {
			return msgGen.MakeDefaultTrame(gettxtMessagetoSend()).toString();
		} catch (Exception ex) {
			System.out.println(getClass().getName() + ex);
			return null;
		}
	}

	/**
	 * getMsgAceInterdit
	 *
	 * @return String
	 */
	public String getMsgAceInterdit() {
		return msgGen.MakeDefaultTrame("la session est plaine vous ete en liste d'attente").toString();
	}

	/**
	 * envoyer par le client pour etre identifier
	 *
	 */
	public void sendId() {
		// pour eviter d'envoyer l'identitee sans etre connecter
		if (!isServer) {
			try {
				writeToServer(msgGen.MakeConntypeTrame(superviseur.getPersoID()).toString());

				System.out.println("Identity  sent by client");
				System.out.println(this.messageenvoye.getUserID());

			} catch (Exception econ) {
				this.erreurMessage("verifier si vous ete connecte ");
			}
		} else {
			this.erreurMessage("l'identification est envoyer connecter par le client ");
		}

	}

	public void sendinitMaxGame(TPoint[][] MxNavir) {

		if (isServer) {

//envoyer la disposition des bateauxserveur.
			writeToAllClient(msgGen.MakeInitGametypeTrame(MxNavir).toString());
			/**
			 *
			 * @todo determier le compte a rebour
			 */

			/**
			 *
			 * @todo attente de ACK de tous les clients soumise a un timeur -> tout se pasee
			 *       dans les delais on continue ->si au moins un client qui ne repond pas :
			 *       annulation de la partie V exclusion du client (s)
			 */
			/**
			 *
			 * informer les clients du debut reel de la partie
			 *
			 */
			/**
			 *
			 * a partir de cette etape on passe la main a un gestionaire de patire
			 * desiniation de l'Arbitre
			 * 
			 * @todo
			 */

			System.out.println("Init game  +was sent by server");
		} else {
			this.erreurMessage("you have't permission " + "to generate navires positions");

		}

	}

	public void setcorrectMessage(String str) {

		superviseur.setText(str);

	}

	public String getLastcorrectMessage() {
		return this.superviseur.getText();
	}

	public void erreurMessage(String str) {
		this.superviseur.erreurMessage(str);
	}

	public String getLasterreurMessage() {
		return this.superviseur.getText();
	}

	public String gettxtMessagetoSend() {
		String str = this.superviseur.getTexttosend();

		return str;
	}

	public void addItemtoStrList(String str) {
		superviseur.addItemtoStrList(str);
	}

	public void SendConfirmationScorpForServeur(int un) {
		writeToServer(msgGen.MakeStrMsgtypeTrame("can restart").toString());
	}

	/**
	 * SendScorpForAllClents
	 *
	 *
	 * @param score String
	 */
	public void SendScoreForAllClents(String score) {

		if (isServer) {

//envoyer la disposition des bateauxserveur.
			writeToAllClient(msgGen.MakeScorptypeTrame(score).toString());

		}

	}

}
