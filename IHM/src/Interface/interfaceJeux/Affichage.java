package Interface.interfaceJeux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import commun_rc.TPoint;

/**
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

public class Affichage implements Runnable {
	private int delay = 500;
	private transient Thread delayer;
	private boolean stopped = false;

	// for double-buffering graphics

	private BatailleNavale appelant;
	// for double-buffering graphics
	private String infos = "";//
	private String text2 = "";//
	Image earth = Toolkit.getDefaultToolkit().getImage("rc/media/images/earth.jpg");;

	// Image player
	// =Toolkit.getDefaultToolkit().getImage("rc/media/images/linux.gif");;
	// Image bateau
	// =Toolkit.getDefaultToolkit().getImage("rc/media/images/bateau.png");;
	// Image feuxbateau
	// =Toolkit.getDefaultToolkit().getImage("rc/media/images/feuxbateau.png");;
	// Image requin
	// =Toolkit.getDefaultToolkit().getImage("rc/media/images/requin.gif");
	public void setInfos(String infos) {
		this.infos = infos;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getInfos() {
		return infos;
	}

	public String getText2() {
		return text2;
	};

	public Affichage(BatailleNavale Appelant) {
		appelant = Appelant;
		initImage();
		initDelay();

	}

	Dimension size;
	Image buffer = Toolkit.getDefaultToolkit().getImage("rc/media/images/earth.jpg");;
	Graphics bufferGraphics;
	Graphics bufGtmpOriginal;

	// initialisation de l'image a affiche
	public void initImage() {

		size = new Dimension(BatailleNavale.appelant.getWidth(), BatailleNavale.appelant.getHeight());
		buffer = BatailleNavale.appelant.createImage((int) size.getWidth(), (int) size.getHeight());
		if (buffer != null) {
			bufferGraphics = buffer.getGraphics();

		}
	}

	public void bufferediMAGE() {
		// Create a buffered image texture patch of size 5x5
		BufferedImage bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
		Graphics2D big = bi.createGraphics();
// Render into the BufferedImage graphics to create the texture
		big.setColor(Color.green);
		big.fillRect(0, 0, 5, 5);
		big.setColor(Color.lightGray);
		big.fillOval(0, 0, 5, 5);

// Create a texture paint from the buffered image
		Rectangle r = new Rectangle(0, 0, 5, 5);
		// TexturePaint tp = new TexturePaint(bi,r,TexturePaint.TRANSLUCENT);

// Add the texture paint to the graphics context.
		// big.setPaint(tp);

// Create and render a rectangle filled with the texture.
		big.fillRect(0, 0, 200, 200);

	}

	public void paint(BatailleNavale Pv, Graphics bufferGraphicsOrginale, Color couleur, int xOrigine, int yOrigine,
			int largeur, int hauteur, boolean raised) {

//    bufGtmpOriginal= bufferGraphicsOrginale;

		stopPaint();
//hauteur*largeur
		if (buffer == null) {
			initImage();
			// System.out.println("Affichage.paint:buffer==null");
		}
		dessinePlateau(bufferGraphics, couleur, xOrigine, yOrigine, largeur, hauteur, raised);

		CasePlayer(bufferGraphics, Color.GREEN, Pv.getPcurrentLocation()
		// ,player// ,requin
				, xOrigine, yOrigine, largeur, largeur);

		Vecteur(bufferGraphics, Color.YELLOW, Pv.getNavirJoueur(Pv.getJoueur())
		// ,bateau
				, xOrigine// +Pv.tailleCase()
				, yOrigine, largeur, largeur);
		Vecteur(bufferGraphics, Color.red, Pv.getJoueurPointsPerdu()
		// ,requin
				, xOrigine// +Pv.tailleCase()
				, yOrigine// +Pv.tailleCase()
				, largeur, largeur);
		Vecteur(bufferGraphics, Color.MAGENTA, Pv.getJoueurPointsTouche(Pv.getJoueur())
		// ,feuxbateau
				, xOrigine// +Pv.tailleCase()
				, yOrigine, largeur, largeur);

		Text(getInfos(), bufferGraphics);
		Text2(getText2(), bufferGraphics);
		/*
		 * infos
		 */

		startPaint();
		bufferGraphicsOrginale.drawImage(buffer, xOrigine, yOrigine, BatailleNavale.appelant);
	}

	public void CasePlayer(Graphics g, Color couleurcase, TPoint Case,
			// Image image,
			int xOrigine, int yOrigine, int largeur, int hauteur) {
		int x = xOrigine + Case.X() * largeur;
		int y = yOrigine + Case.Y() * hauteur;

		/*
		 * images =Toolkit.getDefaultToolkit().getImage(image);
		 * 
		 */
		g.setColor(couleurcase);
		g.fill3DRect(x, y, largeur, hauteur, true);
		g.setColor(Color.BLACK);
		g.draw3DRect(x, y, largeur / 2, hauteur, true);
		g.setColor(Color.BLACK);
		g.draw3DRect(x, y, largeur, hauteur / 2, true);

		/*
		 * g.drawImage(image,x, y, largeur , hauteur , BatailleNavale.appelant);
		 */

	}

	/**
	 * Afichage de un vecteur de point sur plateau
	 * 
	 * @param graph    Graphics
	 * @param couleur  Color
	 * @param Lvecteur TPoint[]
	 * @param xOrigine int
	 * @param yOrigine int
	 * @param largeur  int
	 * @param hauteur  int
	 */
	private void Vecteur(Graphics graph, Color couleur, Object[] Lvecteur,
			// Image image,
			int xOrigine, int yOrigine, int largeur, int hauteur) {

		graph.setColor(couleur);
		for (int i = 0; i < Lvecteur.length; i++) {
			CasePer(graph, couleur, Lvecteur[i], /* image, */xOrigine, yOrigine, largeur, hauteur);
		}
	}

	/**
	 *
	 * @param g           Graphics
	 * @param couleurcase Color
	 * @param objCase     Object
	 * @param xOrigine    int
	 * @param yOrigine    int
	 * @param largeur     int
	 * @param hauteur     int
	 */
	private void CasePer(Graphics g, Color couleurcase, Object objCase,
			// Image image,
			int xOrigine, int yOrigine, int largeur, int hauteur) {
		TPoint Case = (TPoint) objCase;
		int x = xOrigine + Case.X() * largeur;
		int y = yOrigine + Case.Y() * hauteur;

		/*
		 * images =Toolkit.getDefaultToolkit().getImage(image);
		 */

		g.setColor(Color.YELLOW);
		g.draw3DRect(x, y, largeur, hauteur, true);// / 2/ 2
		g.setColor(couleurcase);
		g.fill3DRect(x, y, largeur, hauteur, true);/// / 2/ 2
		// Attention l'affichage de dixhuit Photos conssme 20% du CPU
		// l'algorythme a revoire
		// g.drawImage(image,x, y, largeur , hauteur , BatailleNavale.appelant);
	}

	/**
	 * affichage de text sur le panel appelant
	 * 
	 * @param str   String
	 * @param graph Graphics
	 */
	private void Text(String str, Graphics graph) {

		Font font16 = new Font("Fixed", Font.BOLD | Font.ITALIC | Font.PLAIN, 35);
		graph.setFont(font16);
		graph.setColor(Color.WHITE);
		if (str == null)
			str = "le message n'est pas preparer ";
		graph.drawString(str, appelant.X(), appelant.Limite() * (appelant.tailleCase() / 2));

	}

	/**
	 * affichage de text sur le panel appelant
	 * 
	 * @param str   String
	 * @param graph Graphics
	 */
	private void Text2(String str, Graphics graph) {

		Font font16 = new Font("Fixed", Font.BOLD | Font.ITALIC | Font.PLAIN, 20);
		graph.setFont(font16);
		graph.setColor(Color.CYAN);
		if (str == null)
			str = "le message n'est pas preparer ";
		graph.drawString(str, appelant.X(), // +(appelant.Limite()* appelant.tailleCase())
				appelant.Limite() * (appelant.tailleCase() / 2));

	}

	/**
	 * on dessine le plateau en fonction d'un graphics
	 * 
	 * @param graph         Graphics
	 * @param plateauColor  Color
	 * @param xp            int
	 * @param yp            int
	 * @param tailleCase    int
	 * @param Limite        int
	 * @param plateauRaised boolean
	 */
	private void dessinePlateau(Graphics graph, Color plateauColor,
			// String Image,
			int xp, int yp, int tailleCase, int Limite, boolean plateauRaised) {

		graph.drawImage(earth, xp, yp, BatailleNavale.appelant.getSize().width,
				BatailleNavale.appelant.getSize().height, BatailleNavale.appelant);

		// des champs temporaires
		plateauRaised = false;
		int x, y, tC, lim;
		// on recupere des informations necessaires
		x = xp;
		y = yp;
		tC = tailleCase;
		lim = Limite;

		// dessin de l'hombre
		graph.setColor(Color.gray);
		// on dessine un rectangle (x,y,largeur,hauteur)

		graph.draw3DRect(x, y, (lim * tC), lim * tC, plateauRaised);
		// graph.setColor(plateauColor);
		if (plateauRaised) {
			graph.fill3DRect(x + tC / 2, y + tC / 2, (lim * tC), lim * tC, plateauRaised);

			// on prepare graphiquement le plateau
			// on dessine les images avant les cadres
			// dessineImages(graph);
			// on charge la couleur noire

		}

		graph.setColor(Color.green);
		// on dessine un rectangle (x,y,largeur,hauteur)
		graph.draw3DRect(x, y, lim * tC, lim * tC, true);
		graph.setColor(plateauColor);
		if (plateauRaised) {
			graph.fill3DRect(x, y, lim * tC, lim * tC, plateauRaised);

			// graph.drawRect(x,y,lim*tC,lim*tC);
			// on dessine les traits du plateau
		}

		graph.setColor(Color.red);

		for (int i = 0; i <= lim; i++) {
			graph.drawLine(x + i * tC, y, x + i * tC, y + lim * tC);
			graph.drawLine(x, y + i * tC, x + lim * tC, y + i * tC);

		}

		/*
		 * MediaTracker mt = new MediaTracker(appelant); mt.addImage(images, 0); try {
		 * mt.waitForID(0); } catch (Exception e) { e.printStackTrace(); }
		 * 
		 */

	}

	public void run() {

		try {
			while (true) {
				// ce mettre en attente tant qu'il nest pas arrete.
				synchronized (this) {
					while (stopped) {

						wait();

					}
					// bufGtmpOriginal.drawImage()=bufferGraphics;
				}
				// ce remetre en sommeil.

				delayer.wait(delay);

			}
		} catch (InterruptedException ex) {
		}
	}

	private void initDelay() {
		delayer = new Thread();
		delayer.start();
	}

	public synchronized void startPaint() {
		stopped = false;
		notify();
	}

	public synchronized void stopPaint() {
		stopped = true;
	}

}
