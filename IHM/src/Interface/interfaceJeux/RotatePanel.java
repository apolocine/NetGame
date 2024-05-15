package Interface.interfaceJeux;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

public class RotatePanel extends JPanel {
	private Image image;
	TimerTaskMi tTimerTaskMi = null;
	private double currentAngle;

	public RotatePanel(Image image) {
		this.image = image;
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(image, 0);
		try {
			mt.waitForID(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RotatePanel(String image) {
		Image images = Toolkit.getDefaultToolkit().getImage(image);
		this.image = images;
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(images, 0);
		try {
			mt.waitForID(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Initialiser le composant
	private void jbInit() throws Exception {
		/**
		 * @todo tentative de de mise a jour de l'image resolu dans d'autre endroi par
		 *       doublebuffring
		 *
		 */
		// la rotation de l'image
		/*
		 * tTimerTaskMi=new TimerTaskMi(this);
		 */
		this.setOpaque(true);
	}

	public void rotate() {
		// rotate 5 degrees at a time
		currentAngle += 30.0;
		if (currentAngle >= 360.0) {
			currentAngle = 0;
		}
		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform origXform = g2d.getTransform();
		AffineTransform newXform = (AffineTransform) (origXform.clone());
		// center of rotation is center of the panel
		int xRot = this.getWidth() / 2;
		int yRot = this.getHeight() / 2;
		newXform.rotate(Math.toRadians(currentAngle), xRot, yRot);
		g2d.setTransform(newXform);
		// draw image centered in panel
		int x = (getWidth() - image.getWidth(this)) / 2;
		int y = (getHeight() - image.getHeight(this)) / 2;
		g2d.drawImage(image, x, y, this);
		g2d.setTransform(origXform);
	}

	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(this), image.getHeight(this));
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		Container cp = f.getContentPane();
		cp.setLayout(new BorderLayout());
		Image testImage = Toolkit.getDefaultToolkit().getImage("Interface/Grote_girl.jpg");
		final RotatePanel rotatePanel = new RotatePanel(testImage);
		JButton b = new JButton("Rotate");

		cp.add(rotatePanel, BorderLayout.CENTER);
		cp.add(b, BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}

	void b_actionPerformed(ActionEvent e) {
		this.rotate();
	}

}

class TimerTaskMi extends TimerTask {
	public TimerTaskMi() {
	} 

	RotatePanel panel;
	public boolean play = true;
	char tmp;
	int Allcase;

	public TimerTaskMi(RotatePanel cadreParent) {
		panel = cadreParent;
	}

	public void run() {
		// for(int ind=0;ind<cadre.getTitle().length();ind++)
		if (play) {
			panel.rotate();
		}
	}

	public String playString(String str) {
		if (str.length() == -1)
			return "";
		else {
			tmp = str.charAt(0);
			str = str.replaceFirst("" + tmp, "");
			str = str + tmp;
		}
		return str;
	}

	void playCcircularText() {
		java.util.Timer timer = new java.util.Timer();
		int delay = 3000; // delay for 5 sec.
		int period = 300; // repeat every sec.

		timer.scheduleAtFixedRate(this, delay, period);

	}

}
