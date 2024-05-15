package Interface.interfaceJeux;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import commun_rc.TPoint;

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
public class Plateaudejeu extends JPanel implements MouseListener, ActionListener, MouseMotionListener, ItemListener {
	// Raffrichissement raffrichissement= new Raffrichissement(this);
	BorderLayout borderLayout1 = new BorderLayout();
	public BatailleNavale bBatailleNavale;

	TPoint Psouris;
	int Tx = 0;
	int Ty = 0;
	int Tlim = 10;
	int TTCase = 20;

	final public int joueur1 = 1;
	final public int joueur2 = 1;
	private int joueur;
	final public int personne = 0;

	final public Color couleurJ1 = Color.red;

	int xs, ys, ouldx, ouldy;

	JLabel AffichagePanel = new JLabel();
	JProgressBar barredevie = new JProgressBar();
	private int nbrDevieEncour = 18;
	private int nbrdevieMax = 18;
	Border border1;
	Border border2;
	private boolean disable;

	public void setNbrdevieMax(int nbrdevieMax) {
		this.nbrdevieMax = nbrdevieMax;

	}

	public int getNbrdevieMax() {
		return nbrdevieMax;
	}

	public void setNbrDevieEncour(int nbrDevie) {
		this.nbrDevieEncour = nbrDevie;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public int getNbrDevieEncour() {
		return nbrDevieEncour;
	}

	public boolean isDisable() {
		return disable;
	}

	/**
	 * UpBardevie
	 */

	public void UpBardevie() {

		if (getNbrDevieEncour() < getNbrdevieMax()) {
			setNbrDevieEncour(getNbrDevieEncour() + 1);
		}
	}

	/**
	 * downBardevie
	 */
	public void downBardevie() {
		if (!(getNbrDevieEncour() <= 0)) {
			setNbrDevieEncour(getNbrDevieEncour() - 1);
		}

	}

	/**
	 * miseAjourBareVie
	 */
	public void miseAjourBareVie() {
		// int val = (getNbrDevieEncour() * 100) / getNbrdevieMax();
		// barredevie.setString(val + " %");
// int val2=getNbrDevieEncour()-getNbrdevieMax();
		barredevie.setToolTipText("il reste " + getNbrDevieEncour() + " point(s) de vies");

		this.setToolTipText("il reste " + getNbrDevieEncour() + " point(s) de vies pour" + AffichagePanel.getText());

		this.barredevie.setValue(getNbrDevieEncour());

	}

	public Plateaudejeu(int joueur, Color plateau, Color Case, boolean raised) {

		try {
			jbInit();

			this.barredevie.setForeground(plateau);
			bBatailleNavale = new BatailleNavale(joueur, this, Tx, Ty, Tlim, TTCase, raised);

			initjeux();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// attendre de cree toutes les dimentions
		try {
		} catch (Exception ex1) {
		}

	}

	public void initjeux() {

		bBatailleNavale.initjeux();
	}

	void jbInit() throws Exception {

		border1 = BorderFactory.createCompoundBorder(
				new TitledBorder(BorderFactory.createEtchedBorder(Color.orange, Color.cyan), "border2"),
				BorderFactory.createEmptyBorder(0, 3, 3, 0));
		border2 = BorderFactory.createEtchedBorder(Color.orange, Color.cyan);
		AffichagePanel.setBackground(new Color(214, 180, 28));
		AffichagePanel.setFont(new java.awt.Font("Arial Black", 1, 11));
		AffichagePanel.setForeground(SystemColor.infoText);
		AffichagePanel.setText("       En attente de joueur");
		this.setPreferredSize(new Dimension(220, 220));
		this.setToolTipText("");
		this.setVerifyInputWhenFocusTarget(false);
		this.setForeground(Color.orange);
		this.setFont(new java.awt.Font("Arial", 3, 19));
		this.setBackground(Color.orange);
		this.setForeground(Color.orange);
		this.setMaximumSize(new Dimension(2147483647, 2147483647));
		this.setOpaque(true);
		this.setBackground(new Color(214, 180, 28));
		this.setLayout(borderLayout1);
		initjeu();
		barredevie.setOrientation(JProgressBar.VERTICAL);
		barredevie.setBackground(Color.blue);
		barredevie.setEnabled(true);
		// barredevie.setForeground(Color.green);
		barredevie.setAlignmentX((float) 0.5);
		barredevie.setAlignmentY((float) 0.5);
		barredevie.setBorder(border2);

		barredevie.setMaximumSize(new Dimension(20, 32767));
		barredevie.setMinimumSize(new Dimension(22, 10));
		barredevie.setPreferredSize(new Dimension(18, 100));
		barredevie.setRequestFocusEnabled(true);
		barredevie.setToolTipText("barre de vie");
		barredevie.setVerifyInputWhenFocusTarget(false);
		barredevie.setString("barre de vie");
		barredevie.setValue(0);
		barredevie.setBorderPainted(true);
		barredevie.setStringPainted(false);
		this.barredevie.setMaximum(18);

		this.add(AffichagePanel, BorderLayout.SOUTH);
		this.add(barredevie, BorderLayout.EAST);

	}

	// initiatisation du information du jeu
	void initjeu() { //

	}

	// les actions enclenchees durant le jeu
	public void actionPerformed(ActionEvent event) {
		// if (event.getSource()==boutonRejouer)
		// rejouer();
	}

	// methode de rafraechissement de la fenetre
	public void paintComponent(Graphics graph) {

		bBatailleNavale.paintComponent(graph);
		miseAjourBareVie();
	}

	public void setText(String str) {
		this.AffichagePanel.setText(str);
	}

	public void setTextGameState(String str) {
		bBatailleNavale.setTextGameState(str);
	}

	// le mouvement de la sourie entraine l'affichage du rectange en rouge
	public void asmouscliked(MouseEvent event) {

		bBatailleNavale.asmouscliked(event, joueur);
	}

	// la souris a bouge ?
	public void mouseMoved(MouseEvent event) {

		this.bBatailleNavale.setPcurrentLocation(event.getX(), event.getY());

	}

	public void mouseClicked(MouseEvent event) {

		asmouscliked(event);

	}

	// on fait un Drag&Drop avec la souris ?
	public void mouseDragged(MouseEvent event) {
	}

	public void mouseExited(MouseEvent event) {
	}

	public void mouseEntered(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {
	}

	public void mousePressed(MouseEvent event) {
	}

	public void itemStateChanged(ItemEvent event) {

	}

	/**
	 * SourisSurPlateau
	 *
	 * @param e MouseEvent
	 * @return boolean
	 */
	public boolean SourisSurPlateau(MouseEvent e) {
		return this.bBatailleNavale.SourisSurPlateau(e.getX(), e.getY());
	}

	/**
	 * pointValide
	 *
	 *
	 * @param e      MouseEvent
	 * @param joueur int
	 * @return boolean
	 */
	public boolean pointValide(MouseEvent e, int joueur) {
		return this.bBatailleNavale.SourisSurPlateau(e.getX(), e.getY())
				&& !this.bBatailleNavale.pointDejaSelectione(e, joueur);
	}

	/**
	 * pointDejaSelectione
	 *
	 * @param e      MouseEvent
	 * @param joueur int
	 * @return boolean
	 */
	public boolean pointDejaSelectione(MouseEvent e, int joueur) {
		return this.bBatailleNavale.pointDejaSelectione(e, joueur);
	}

	/**
	 * setTextWait
	 *
	 * @param string String
	 */
	public void setTextWait(String string) {
		bBatailleNavale.setTextWait(string);
	}

}
