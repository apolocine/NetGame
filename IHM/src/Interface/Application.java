package Interface;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.UIManager;

/**
 * <p>
 * Titre :
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Copyright : Copyright (c) 2004
 * </p>
 * <p>
 * Soci�t� :
 * </p>
 * 
 * @author non attribuable
 * @version 1.0
 */

public class Application {
	boolean packFrame = false;

	// Construire l'application
	public Application() {
		CadrePr frame = new CadrePr();
		// Valider les cadres ayant des tailles pr�d�finies
		// Compacter les cadres ayant des infos de taille pr�f�r�es - ex. depuis leur
		// disposition
		if (packFrame) {
			frame.pack();
		} else {
			frame.validate();
		}
		// Centrer la fen�tre
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		frame.setVisible(true);
	}

	// M�thode main
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Application();

	}
}
