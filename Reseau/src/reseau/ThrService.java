package reseau;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.net.Socket;

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

public class ThrService implements Runnable {
	Thread threadService;
	Socket socket; /* socket associee au client */
	DataOutputStream OenSortie; // flux de sortie
	DataInputStream IenEntree; // flux d'entree
	ObjectOutputStream enSortie;
	ObjectInputStream enEntree;

	byte[] message;

	Serveur gServeur;
	public String strRead, StrWrite;

	String TramashStr = "";
	private int threadNum;
	private boolean stoprun = false;

	public void setStoprun(boolean stoprun) {
		this.stoprun = stoprun;
	}

	public boolean isStoprun() {
		return stoprun;
	}

	public void setthreadNum(int UserNumID) {
		this.threadNum = UserNumID;
	}

	public int getthreadNum() {
		return threadNum;
	}

	public ThrService(int threadNumn, Socket socket, Serveur serveur) {

		gServeur = serveur;
		this.socket = socket;
		this.setthreadNum(threadNumn);

		gServeur.addItemtoStrList("" + this.socket.getInetAddress().toString());

		try {
			enSortie = new ObjectOutputStream(socket.getOutputStream());
			enEntree = new ObjectInputStream(socket.getInputStream());

		} catch (IOException exc) {
			// this.erreurMessage("probleme d'attribution de E/S sur la socket");
			OFF();
		}
		ON();

	}

	/* la m�thode de la thread Messagerie */
	public void run() {
		while (!isStoprun()) {
			readTrame();
		}
	}

	void readTrame() {

		try {
			giveMsg(readObject());
		} catch (Exception ex) {
			try {
				OFF();
			} catch (Exception ex1) {
			}

		}
	}

	/**
	 * ON
	 */
	public void ON() {
		setStoprun(false);
		threadService = new Thread(this);
		threadService.start();
	}

	/**
	 * OFF
	 */
	public void OFF() {
		setStoprun(true);
		try {
			if (this.socket == null) {
				this.socket.close();
			}
			if (this.enEntree == null) {
				this.enEntree.close();
			}
			if (this.enSortie == null) {
				this.enSortie.close();
			}
		} catch (IOException ex) {
		}

		if (threadService.isAlive()) {
			threadService.interrupt();
		}

	}

	public Object readObject() {
		try {

			Object obj = enEntree.readObject();
			return obj;

		} catch (ClassNotFoundException e) {
			setStoprun(true);
			// System.err.println("Unable to find class" + e);
			return null;
		} catch (ObjectStreamException e) {
			// System.err.println("Unable to read object" + e);
			setStoprun(true);
			return null;
		} catch (IOException e) {
			// System.err.println("Unable to read" + e);
			setStoprun(true);
			return null;
		}

	}

	public void writeTrame(Object obj) {
		writeObject(obj);
	}

	public void writeObject(Object obj) {

		try {
			enSortie.writeObject(obj);
			enSortie.flush();
		} catch (IOException e) {

			autodestruction();

			/**
			 * @todo a control� lor de la d�connection du client
			 */
			System.err.println(" controle  de  d�connection du thread de service \n" + e);
		}

	}

	void autodestruction() {
		erreurMessage("autod�struction du " + getthreadNum() + "correspondant");
		gServeur.revoveFromServer(getthreadNum());
		setStoprun(true);
		OFF();

	}

	void setcorrectMessage(String str) {
		gServeur.setcorrectMessage(str);
	}

	String getLastcorrectMessage() {
		return gServeur.getLastcorrectMessage();
	}

	void erreurMessage(String str) {
		gServeur.erreurMessage(str);
	}

	void giveMsg(Object obj) {
		gServeur.trtMsgrecuToServer(obj);
	}

}
