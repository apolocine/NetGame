package time;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
public class Horloge {
	// definition d'une classe interne qui n'a de raison d'etre qu'au
	// sein de la sur-classe Horloge :
	class HorlogeInterne extends JPanel implements Runnable {
		private Thread threadDeMiseAJour = null;
		private Date dateCourante = null;
		private Font fonteHeure = new Font("Helvetica", Font.BOLD, 12);
		private Font fonteJour = new Font("Helvetica", Font.PLAIN, 12);
		private DateFormat formatHeure = null;
		private DateFormat formatJour = null;

		HorlogeInterne(Locale choixDeLaLocale) {
			// On redimensionne le composant courant avant de fixer
			// les formats d'affichage de la date et de
			// l'heure. Enfin, on lance le thread de rafraechissement
			// de l'horloge :
			setPreferredSize(new Dimension(180, 40));
			formatHeure = DateFormat.getTimeInstance(DateFormat.MEDIUM, choixDeLaLocale);
			formatJour = DateFormat.getDateInstance(DateFormat.LONG, choixDeLaLocale);
			threadDeMiseAJour = new Thread(this);
			threadDeMiseAJour.start();
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// affichage de l'heure et de la date courante
			// respectivement dans des fontes de grosse et petite
			// tailles :
			dateCourante = Calendar.getInstance().getTime();
			g.setFont(fonteHeure);
			g.drawString(formatHeure.format(dateCourante), 20, 10);
			g.setFont(fonteJour);
			g.drawString(formatJour.format(dateCourante), 80, 10);
		}

		public void run() {
			// boucle infinie :
			while (true) {
				// mise e jour du composant graphique (repaint lance
				// paintComponent) :
				repaint();
				try {
					// le thread "sommeille" pendant 1 seconde avant
					// de boucler :
					threadDeMiseAJour.wait(1000);
				} catch (InterruptedException uneException) {
				}
			}
		}
	}

	// definition d'une classe interne qui n'a de raison d'etre qu'au
	// sein de la sur-classe Horloge :
	class HorlogeJLabel extends TimerTask // implements Runnable
	{
		JLabel horloge;
		private Date dateCourante = null;
		private Font fonteHeure = new Font("Helvetica", Font.ITALIC | Font.PLAIN, 12);
		char tmpL, tmpF;
		java.util.Timer timer = new java.util.Timer();

		private DateFormat formatHeure = null;
		private DateFormat formatJour = null;

		HorlogeJLabel(JLabel horloge_, Locale choixDeLaLocale) {
			this.horloge = horloge_;
			// On redimensionne le composant courant avant de fixer
			// les formats d'affichage de la date et de
			// l'heure. Enfin, on lance le thread de rafraechissement
			// de l'horloge :
			formatHeure = DateFormat.getTimeInstance(DateFormat.MEDIUM, choixDeLaLocale);
			formatJour = DateFormat.getDateInstance(DateFormat.LONG, choixDeLaLocale);

		}

		public String playString(String str) {
			if (str.length() == -1)
				return "";
			else {
				tmpF = str.charAt(0);
				// tmpL=str.charAt(str.length()-1);
				str = str.replaceFirst("" + tmpF, "").concat("" + tmpF);
				// str=str;
				// str=str+tmp;
			}
			return str;
		}

		public void playCirculairTxt() {

			horloge.setText(playString(horloge.getText()));
		}

		public void ShowTime() {

			// affichage de l'heure et de la date courante
			// respectivement dans des fontes de grosse et petite
			// tailles :
			dateCourante = Calendar.getInstance().getTime();
			horloge.setFont(fonteHeure);

			horloge.setText(
					"          " + formatJour.format(dateCourante) + "      " + formatHeure.format(dateCourante));
		}

		void startShowTime() {

			int delay = 1000; // delay for 5 sec.
			int period = 300; // repeat every sec.
			timer.scheduleAtFixedRate(this, delay, period);
		}

		public void run() {
			ShowTime();
			// playCirculairTxt();
		}

	}

	public Horloge(JLabel stat, Locale choixDeLaLocale) {
		new HorlogeJLabel(stat, choixDeLaLocale).startShowTime();

	}

	public Horloge(JPanel laFenetre, Locale choixDeLaLocale) {

		// on insere ensuite le composant permettant l'affichage de
		// l'horloge dans la fenetre :
		laFenetre.add(new HorlogeInterne(choixDeLaLocale));

		// on procede enfin e l'affichage de l'ensemble :

		laFenetre.setVisible(true);
	}

	/*
	 * public static void main (String[] arguments) {
	 * 
	 * // on commence par instancier la fenetre qui correspond e // l'horloge, puis
	 * on definit son comportement e la fermeture // de la fenetre : JFrame
	 * laFenetre= new JFrame("Mon horloge...");
	 * 
	 * new Horloge(laFenetre,Locale.FRANCE); new Horloge(laFenetre,Locale.GERMAN); }
	 */
}
