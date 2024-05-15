package Interface.resources;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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

public final class FRessource {
	/*
	 * utilisation des fichier *.proprieties comme ressources
	 */
	static String defaultPath = "Interface.resources.NetWork";
	private static ResourceBundle resources;

	static {
		try {
			resources = ResourceBundle.getBundle("Interface.resources.NetWork", Locale.getDefault());
		} catch (MissingResourceException mre) {
			System.err.println("Interface.resources/NetWork.properties not found");
			System.exit(1);
		}
	}

	public FRessource() {

	}

	public void changeRessourceDirTo(String str) {
		try {
			resources = ResourceBundle.getBundle(str, Locale.getDefault());
		} catch (MissingResourceException mre) {
			System.err.println(str + " not found");
			System.exit(1);
		}

	}

	public String getString(String str) {
		return resources.getString(str);

	}

}
