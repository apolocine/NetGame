package reseau;

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

public class AcceInterdit extends Thread {
	Socket socket; /* socket associee au client */
	ObjectOutputStream enSortie;
	ObjectInputStream enEntree;
	byte[] message;

	Serveur gServeur;
	public String strRead, StrWrite;

	public int UserID;
	boolean stoprun = false;

	public AcceInterdit(int threadNum,

			Socket socket, Serveur serveur) {

		gServeur = serveur;

		this.socket = socket;

		this.UserID = threadNum;
		gServeur.addItemtoStrList("" + this.socket.getInetAddress().toString());

		try {
			enSortie = new ObjectOutputStream(socket.getOutputStream());
			enEntree = new ObjectInputStream(socket.getInputStream());

		} catch (IOException exc) {
			// this.erreurMessage("probleme d'attribution de E/S sur la socket");
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
		this.start();

	}

	/* la m�thode de la thread Messagerie */
	public void run() {
		writeObject(getMsgAceInterdit());
		/*
		 * la m�thode de la thread Messagerie while (!stoprun) { readTrame(); }
		 */
	}

	public void writeObject(Object obj) {
		try {
			try {
				// enSortie = new DataOutputStream(socket.getOutputStream());
				// ObjectOutputStream oos = new ObjectOutputStream(enSortie);
				enSortie.writeObject(obj);
				enSortie.flush();
				// oos.close();
			} catch (IOException e) {
				System.err.println("Unable to close enSortie  \n" + e);
				enSortie.close();
			}
		} catch (Exception ex) {
		}
	}

	void readTrame() {
		try {
			giveMsg(readObject());
		} catch (Exception ex) {

		}
	}

	public Object readObject() {
		try {

			Object obj = enEntree.readObject();
			return obj;

		} catch (ClassNotFoundException e) {
			// System.err.println("Unable to find class" + e);
			return null;
		} catch (ObjectStreamException e) {
			// System.err.println("Unable to read object" + e);
			return null;
		} catch (IOException e) {
			// System.err.println("Unable to read" + e);
			return null;
		}

	}

	void giveMsg(Object obj) {
		gServeur.trtMsgrecuToServer(obj);
	}

	public String getMsgAceInterdit() {
		return gServeur.getMsgAceInterdit();
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

	void trtMsgrecu(String str) {
		gServeur.trtMsgrecuToServer(str);
	}

}
