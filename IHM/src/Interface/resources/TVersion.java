package Interface.resources;

import java.text.NumberFormat;

/**
 * <p>
 * Titre : Network Game
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Copyright : Copyright (c) 2004
 * </p>
 * <p>
 * Soci�t� : drmdh
 * </p>
 * 
 * @author Hamid Madani
 * @version 1.0
 */

public class TVersion {

	private double version;
	private String nom, auteur;
	private int copyright;

	public TVersion() {

		version = 1.00;
		auteur = "drmdh ";
		nom = "Dr Madani Hamid";
		copyright = 2004;
	}

	public TVersion(double version) {
		this.version = version;
		nom = "";
		auteur = "";
		copyright = 2004;
	}

	public TVersion(double version, String nom) {
		this.version = version;
		this.nom = nom + "  version ";
		auteur = "";
		copyright = 2004;
	}

	public TVersion(double version, String nom, String auteur) {
		this.version = version;
		this.nom = nom;
		this.auteur = auteur;
		copyright = 2004;
	}

	public TVersion(double version, String nom, String auteur, int copyright) {
		this.version = version;
		this.nom = nom;
		this.auteur = auteur;
		this.copyright = copyright;
	}

	public double Version() {
		return version;
	}

	public void setVersionS(double version) {
		this.version = version;
	}

	public String Nom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String Auteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public int Copyright() {
		return copyright;
	}

	public void setCopyright(int copyright) {
		this.copyright = copyright;
	}

	public String transformeDouble(double valeur, int apresvirgule) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		String resultat;

		// on d�finit le nombre de chiffre apr�s la virgule
		nf.setMaximumFractionDigits(apresvirgule);
		resultat = nf.format(valeur);
		if (resultat.length() == 1)
			resultat = resultat + ".00";
		else
			resultat = resultat.replace(',', '.');
		return resultat;
	}

	public String toString() {
		return (nom + " v" + transformeDouble(version, 2) + " " + auteur + " (open source) " + copyright);
	}
}
