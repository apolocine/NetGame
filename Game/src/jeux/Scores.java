package jeux;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Properties;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright : Copyright (c) 2004
 * </p>
 * <p>
 * Soci�t� :
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class Scores implements Serializable, Runnable {
	private String msg = " hello ";
	private int delay = 500;
	private transient Thread delayer;
	private boolean stopped = false;
	private LinkedList fildes = new LinkedList();

	public Scores() {
		msg = "bienvenu !";
		fildes.add(msg + " 1");
		fildes.add(msg + " 2");
		fildes.add(msg + " 3");
		initDelay();
	}

	private void initDelay() {
		delayer = new Thread();
		delayer.start();
	}

	public void run() {

		try {
			while (true) {
				// ce mettre en attente tant qu'il nest pas arr�t�.
				synchronized (this) {
					while (stopped) {

						wait();

					}
				}
				// afficher le message
				System.out.println(msg);
				// ce rem�tre en somm�il.

				delayer.wait(delay);

			}
		} catch (InterruptedException ex) {
			// afficher le message
			System.out.println(msg + ex);
		}
	}

	public synchronized void startPrint() {
		stopped = false;
		notify();
	}

	public synchronized void stopPrint() {
		stopped = true;
	}

	private void writeObject(ObjectOutputStream os) throws IOException {
		os.defaultWriteObject();

	}

	private void readObject(ObjectInputStream is) throws ClassNotFoundException, IOException {
		is.defaultReadObject();
	}

///read trame

	public Object readTrame(String TrameFile) {
		try {
			FileInputStream fis = new FileInputStream(TrameFile);
			ObjectInputStream ois = new ObjectInputStream(fis);

			Object obj = ois.readObject();
			return obj;

		} catch (FileNotFoundException e) {
			System.err.println("Unable to find file" + e);
			return null;
		} catch (ObjectStreamException e) {
			System.err.println("Unable to read object" + e);
			return null;
		} catch (IOException e) {
			System.err.println("Unable to read" + e);
			return null;
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to find class" + e);
			return null;
		}

	}

	public static void writeTrame(Object obj, String TrameFile) {
		Object str = obj.toString();
		try {
			FileOutputStream fos = new FileOutputStream(TrameFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(str);

			oos.close();
		} catch (IOException e) {
			System.err.println("Unable to save" + e);
		}

	}

	public void readO( /* Object obj */) {
		try {
			FileInputStream fis = new FileInputStream("props.save");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Properties readProps = (Properties) ois.readObject();
			ois.close();
			readProps.list(System.out);
		} catch (FileNotFoundException e) {
			System.err.println("Unable to find file" + e);
		} catch (ObjectStreamException e) {
			System.err.println("Unable to read object" + e);
		} catch (IOException e) {
			System.err.println("Unable to read" + e);
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to find class" + e);
		}

	}

	public void writeO( /* Object obj */) {
		Properties writeProps = System.getProperties();
		try {
			FileOutputStream fos = new FileOutputStream("props.save");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(writeProps);
			System.out.println("oos.toString() : " + oos.toString());
			oos.close();
		} catch (IOException e) {
			System.err.println("Unable to save");
		}
	}

	public String toString() {

		for (int i = 0; i < fildes.size(); i++) {
			this.msg = this.msg + " " + fildes.get(i);
		}
		return this.msg + " ";
	}

	public static void main(String[] args) {
		Scores myFile1 = new Scores();
		/*
		 * MessageReseau trame = new MessageReseau();
		 * 
		 * Scores.writeTrame( (Object) trame, "Scores.log"); // Message
		 * trame2=(Message)myFile1.readTrame("Scores.log");
		 * 
		 * System.out.println(myFile1.readTrame("Scores.log").toString());
		 */
	}

}
