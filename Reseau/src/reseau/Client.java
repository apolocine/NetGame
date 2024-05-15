package reseau;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class Client implements Runnable {
	Thread threadClient;

	GestionDuReseau gGestionDuReseau;
	public Socket socket;
	String TramasStr = "";
	DataOutputStream OenSortie; // flux de sortie
	DataInputStream IenEntree; // flux d'entree
	ObjectOutputStream enSortie;
	ObjectInputStream enEntree;

	int portCon;
	String hoteCon;
	byte[] message;
	public boolean gowrite = true;
	private boolean stoprun = false;
	private boolean connected;

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

	public Client(int port, String hote, GestionDuReseau gestionDuReseau) {

		gGestionDuReseau = gestionDuReseau;
		this.portCon = port;
		this.hoteCon = hote;

		ON();
	}

	int delay = 300;
	static int count = 0;

	public void ON() {
		threadClient = new Thread(this);
		threadClient.start();
		if (threadClient.isAlive()) {
			setConnected(true);
		} else {
			setConnected(true);
		}
		setStoprun(false);
	}

	public void OFF() {
		setConnected(false);
		setStoprun(true);

		try {
			if (this.socket != null) {
				this.socket.close();
			}
			if (this.enEntree != null) {
				this.enEntree.close();
			}
			if (this.enSortie != null) {
				this.enSortie.close();
			}
		} catch (IOException ex) {
		}

		if (threadClient != null && threadClient.isAlive()) {
			threadClient.interrupt();
		}

	}

	public void run() {
		try {
			setcorrectMessage(" tentative de connection");
			socket = new Socket(this.hoteCon, this.portCon);
			enSortie = new ObjectOutputStream(socket.getOutputStream());
			enEntree = new ObjectInputStream(socket.getInputStream());

			setcorrectMessage("Connection establie");
		}

		catch (IOException exc) {
			OFF();

			this.autodestruction("\nProbleme de connection avec le server : " + this.hoteCon + "\nsur le port :"
					+ this.portCon + "\npossible qu'il soit hors ligne \n " + exc);

		}

		while (!isStoprun()) {
			getsendID();
			setcorrectMessage("identity was sent");

			while (isConnected()) {

				readTrame();
			}
			OFF();
		}

	}

	void readTrame() {
		try {
			trtMsgrecu(readObject());
		} catch (Exception eio) {
			OFF();
			autodestruction("" + eio);
		}
	}

	public Object readObject() {
		try {

			Object obj = enEntree.readObject();
			return obj;

		} catch (Exception e) {
			OFF();
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
			autodestruction("" + e);
		}
	}

	void autodestruction(String msg) {

		System.err.println("auto-d�struction \n " + msg);
		this.erreurMessage("\n  auto-d�struction \n " + msg);
		setStoprun(true);
		try {
			if (enEntree != null) {
				this.enEntree.close();
			}
			if (enSortie != null) {
				this.enSortie.close();
			}
			if (socket != null) {
				this.socket.close();
			}

		} catch (IOException ex) {
		}

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

	void trtMsgrecu(Object obj) {
		gGestionDuReseau.trtMsgrecuToClient(obj);
	}

	/**
	 * sendID
	 */
	private void getsendID() {
		gGestionDuReseau.sendId();
	}

}
