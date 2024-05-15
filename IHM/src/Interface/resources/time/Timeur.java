package Interface.resources.time;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Gestionnaire.Gestionnaire;
import media.WavLocaliser;
import observation.Capture;

/**
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
 * Societe : drmdh
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class Timeur extends JPanel implements Runnable {
	private Thread threadDeMiseAJour = null;
	private int valeur = 18;
	private int fromstart = 0;
	private boolean stopped = false;
	private int initialVal = 5;
	private Gestionnaire gestionnair;
	int sec = 1;
	Capture capture = new Capture();
	private boolean gocap;

	public boolean isGocap() {
		return gocap;
	}

	private boolean canautoplay = true;

	public boolean isCanautoplay() {
		return canautoplay;
	}

	private int inchangedValeur = 10;

	public void setInchangedValeur(int atstart) {
		this.inchangedValeur = atstart;
	}

	public int getInchangedValeur() {
		return inchangedValeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
		if (!(getValeur() <= 0)) {
			startPrinting();
		} else {
			stopPrinting();
		}
	}

	public void setFromstart(int atstart) {
		this.fromstart = atstart;
	}

	public void setCanautoplay(boolean canautoplay) {
		this.canautoplay = canautoplay;
	}

	public void setInitialVal(int initialVal) {
		this.initialVal = initialVal;
	}

	public void setGocap(boolean gocap) {
		this.gocap = gocap;
	}

	/**
	 *
	 * @return int
	 */
	public int getValeur() {
		return valeur;
	}

	public int getFromstart() {
		return fromstart;
	}

	public int getInitialVal() {
		return initialVal;
	}

	public Timeur() {

		threadDeMiseAJour = new Thread(this);
		threadDeMiseAJour.start();

	}

	public Timeur(Gestionnaire gestionnair, int valeuInit) {
		this.gestionnair = gestionnair;
		setValeur(valeuInit);

		threadDeMiseAJour = new Thread(this);
		threadDeMiseAJour.start();
	}

	public Timeur(int valeuInit) {
		setValeur(valeuInit);

		threadDeMiseAJour = new Thread(this);
		threadDeMiseAJour.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("" + valeur, 10, 10);
		g.drawString("" + fromstart, 40, 10);
		// System.out.print("-"+ fromstart);
	}

	/**
	 * startPrinting
	 */
	private synchronized void startPrinting() {
		stopped = false;
		notify();

	}

	/**
	 * stopPrinting
	 */
	private synchronized void stopPrinting() {
		/**
		 * @todo a commenter apres le test
		 */
		stopped = true;
	}

	private boolean restart = true;

	public boolean canRestart() {
		return restart;
	}

	public synchronized void reSatrt() {
		restart = true;
		notify();
	}

	public synchronized void bye() {
		restart = false;
		setValeur(getInchangedValeur());
	}

	public void run() {

		try {

			while (true) {
				synchronized (this) {
					// wait(1000);

					// boucle infinie :
					while (canRestart()) {

						try {

							// le thread "sommeille" pendant 1 seconde avant
							// de boucler :

							super.wait(1000);
							setValeur(getValeur() - 1);
							// mise e jour du composant graphique (repaint lance
							// paintComponent) :
							// System.out.print(""+getValeur()+"/ ");
							setFromstart(getFromstart() + 1);
							repaint();
						} catch (InterruptedException uneException) {
						}
						// se mettre en attente tant qu'il n'est pas arrete.
						synchronized (this) {
							while (stopped) {
								if (isGocap()) {
									capture.docapNsave(sec);
									// mise e jour du composant graphique (repaint lance
									// paintComponent) :
									sec++;
								}

								// System.out.println("\nTimeur waiting for notify after 4 sec");
								super.wait(1000); // wait();
								if (gestionnair != null) {

									if (isCanautoplay()) {
										WavLocaliser.playClipCaover();
										// this.gestionnair.sendDefault();
										// gestionnair.setRestartLape(false);
									}
								} else {
									System.out.println("attention le gestionnaire n'existe pas ");
								}

								setValeur(getInchangedValeur());
							}
						}
					}
					setValeur(getInchangedValeur());
					threadDeMiseAJour.wait();
				}

			}
		}

		catch (Exception ex) {
		}

	}

	public static void main(String[] args) {
		JFrame laFenetre = new JFrame("Mon Timeur...") {
			// Supplante, ainsi nous pouvons sortir quand la fenetre est fermee
			protected void processWindowEvent(WindowEvent e) {
				super.processWindowEvent(e);
				if (e.getID() == WindowEvent.WINDOW_CLOSING) {

				}
			}

		};
		final Timeur timeur1 = new Timeur(5);
		JButton jButton1 = new JButton();

		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				timeur1.setValeur(timeur1.getFromstart());
				;

			}
		}

		);

		jButton1.add(timeur1);
		laFenetre.getContentPane().add(jButton1, null);

		laFenetre.pack();
		laFenetre.show();

	}

	/**
	 * setGestionnaire
	 *
	 * @param gestionnaire Gestionnair
	 */
	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnair = gestionnaire;
	}

}
