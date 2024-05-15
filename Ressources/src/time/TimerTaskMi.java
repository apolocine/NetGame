package time;

import java.util.TimerTask;

import javax.swing.JFrame;

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

public class  TimerTaskMi extends TimerTask {
	public TimerTaskMi() {
	}

	JFrame cadre;
	public boolean play = true;
	char tmp;
	int Allcase;

	public TimerTaskMi(JFrame cadreParent) {
		cadre = cadreParent;
	}

	public void run() {
		// for(int ind=0;ind<cadre.getTitle().length();ind++)
		if (play) {
			cadre.setTitle(playString(cadre.getTitle()));
			// cadre.jMenuFile.setText(playString( cadre.jMenuFile.getText()));
			/// cadre.jTextArea1.setText( playString( cadre.jTextArea1.getText())) ;
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
