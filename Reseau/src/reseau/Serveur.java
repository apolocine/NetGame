package reseau;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

import Gestionnaire.NetManager;

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

public class Serveur implements Runnable {
	Thread threadServer;
	public NetManager gGestionDuReseau;
	ThreadGroup group = new ThreadGroup("gameusers");

	private boolean connected;
	public ServerSocket socketServeur = null;
	boolean modeMessagerie = true;
	public ThrService Msg; /* objet Messagerie [] */
	public AcceInterdit enattente;
	int fNembrConnAutoriser = -1;

	public int nembrThread = -1;
	public boolean stoprun = false;
	/* pile ou seront stoque le thread */
	// public Pile pile1 ;
	/* List ou seront stoque le thread des client active */
	public ListGroup Listactive = new ListGroup();
	/* List d'attente des cllient inactif */
	public ListGroup Listattent = new ListGroup();;

	/* numero de port du serveur */
	/*
	 * final static int port=2100; int timeOut=150;
	 */
	/* this.jComboBox1.addItem(this.jTextField2.getText()); */
	public Serveur(int port, int timeOut, int NembrConnAutoriser, NetManager gestionDuReseau, boolean isMessagerie) {
		gGestionDuReseau = gestionDuReseau;
		;

		this.modeMessagerie = isMessagerie;
		this.fNembrConnAutoriser = NembrConnAutoriser;
		// pile1 = new Pile(NembrConnAutoriser);
		try {
			socketServeur = new ServerSocket(port, timeOut);
			this.setcorrectMessage("serveur en route ");
		} catch (IOException e) {
			OFF();
		}
		ON();

	}

	public void setStoprun(boolean stoprun) {
		this.stoprun = stoprun;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public boolean isStoprun() {

		return stoprun;
	}

	public boolean isConnected() {
		return connected;
	}

	public void ON() {
		threadServer = new Thread(this);
		threadServer.start();
		setConnected(true);
		setStoprun(false);
	}

	public void OFF() {
		setConnected(false);
		setStoprun(true);

		try {
			if (this.socketServeur != null) {
				this.socketServeur.close();
			}
		} catch (IOException ex) {
		}

		if (threadServer != null && threadServer.isAlive()) {
			threadServer.interrupt();
		}

	}

	public void run() {

		Socket socket = null; /* socket de communication avec un client */

		try {
			while (!isStoprun()) {
				if (socketServeur != null) {
					socket = socketServeur.accept();

				}

				else {
					OFF();
					this.erreurMessage(" la socket du Serveur n'a pas ete cree");

				}

				if (isConnected()) {

					this.setcorrectMessage(this.getLastcorrectMessage() + "\n" + "en attente des client");
					/* nouvelle connexion ==> nouveau client */
					/* lancer une nouvelle thread ==> fournir un service au client demandeur */

					this.setcorrectMessage(this.getLastcorrectMessage() + "\n" + "tentative de creation d'un fils");

					if (modeMessagerie) {
						if (this.Listactive.size() < this.fNembrConnAutoriser) {
							nembrThread++;
							Msg = new ThrService(nembrThread, socket, this);
							Listactive.add(Msg);
						} else {
							enattente = new AcceInterdit(nembrThread, socket, this);
							Listattent.add(enattente);
							this.setcorrectMessage(this.getLastcorrectMessage() + "\n" + "une " + nembrThread + "\n"
									+ "personne a voulue ce connecter ");
						}

					}
				} else {
					OFF();
				}
			}
		} catch (IOException e) {
			OFF();
			erreurMessage("probleme de connexion !");
		}

	}

	public void MsgFrom(int num, Object obj) {
		// gGestionDesAffichage.trtMsgrecu(obj);

		trtMsgrecuToServer(obj);
	}

	public void writeToAllClient(Object obj) {
		Iterator i = this.Listactive.iterator();
		ThrService srvc = null;
		while (i.hasNext()) {
			Object o = i.next();
			srvc = (ThrService) o;
			if (o == null) {
				System.out.println("Serveur.writeToAllClient : ThrService = null");
			}

			else {
				srvc.writeTrame(obj);
			}
			;
		}
		/*
		 * for (int i = 0; i < List.size(); i++) { writeTo(obj, i); }
		 */
	}

	public void writeToAllClientExcept(int numero, Object obj) {
		for (int i = 0; i < Listactive.size(); i++) {
			if (i != numero) {
				writeTo(obj, i);
			}
		}
	}

	public void writeTo(Object obj, int num) {
		this.getThrService(num).writeTrame(obj);
	}

	public ThrService getThrService(int num) {

		return (ThrService) this.Listactive.get(num);
	}

	void trtMsgrecuToServer(Object obj) {
		gGestionDuReseau.trtMsgrecuToServer(obj);
	}

	void setcorrectMessage(String str) {
		gGestionDuReseau.setcorrectMessage(str);
	}

	String getLastcorrectMessage() {
		return gGestionDuReseau.getLastcorrectMessage();
	}

	void erreurMessage(String str) {
		gGestionDuReseau.erreurMessage(str);
	}

	String gettxtMessagetoSend() {
		return gGestionDuReseau.gettxtMessagetoSend();
	}

	public void addItemtoStrList(String str) {
		gGestionDuReseau.addItemtoStrList(str);
	}

	/**
	 * getMsgAceInterdit
	 *
	 * @return String
	 */
	public String getMsgAceInterdit() {
		return gGestionDuReseau.getMsgAceInterdit();
	}

	/**
	 * revoveFromServer
	 *
	 * @param threadNum int
	 */
	public void revoveFromServer(int threadNum) {
		this.Listactive.remove(threadNum);
	}

}
