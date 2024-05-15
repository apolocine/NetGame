package jeux;

import Gestionnaire.Gestionnaire;

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
 * Soci�t� : drmdh
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class GestionDesSalles {
	Gestionnaire parent;
	int MaxSalle = 1;
	Salle[] Tabsalle = new Salle[MaxSalle];

	public Salle getFirstSalle() {
		return (Salle) Tabsalle[0];
	}

	public Salle getSalle(int num) {
		return (Salle) Tabsalle[num];
	}

	public GestionDesSalles() {
		for (int i = 0; i < MaxSalle; i++) {
			Tabsalle[i] = new Salle(this);
		}

	}

	public GestionDesSalles(Gestionnaire parent) {
		this.parent = parent;
		for (int i = 0; i < MaxSalle; i++) {
			Tabsalle[i] = new Salle(this);
		}

	}

//////////////////////////////////////////////////////////////////////////////
	/*
	 * public static void main(String[] args) { GestionDesSalles gestionDesSalles1 =
	 * new GestionDesSalles(); System.out.println("nembre de table " +
	 * gestionDesSalles1.Tabsalle[0].getNbMaxTable()); for (int i = 0; i <
	 * gestionDesSalles1.Tabsalle[0].getNbMaxTable(); i++){ System.out.println(
	 * "nombre de joueur par  table : "+i+" "+ +
	 * gestionDesSalles1.Tabsalle[0].tTable[i].getJoueurMax()); }
	 * 
	 * 
	 * 
	 * PersoInfos personne= new PersoInfos();
	 * 
	 * for (int i = 0; i < gestionDesSalles1.Tabsalle[0].getNbMaxTable(); i++){
	 * gestionDesSalles1.Tabsalle[0].tTable[i].addJoueur(personne);
	 * gestionDesSalles1.Tabsalle[0].tTable[i].addJoueur(new
	 * PersoInfos(5,"faouzi","","",""));
	 * System.out.println(gestionDesSalles1.Tabsalle[0].tTable[i].tostring()); }
	 * 
	 * 
	 * 
	 * System.out.println( "getNbJoueurDansLaSalle apr�s addJoueur :" +
	 * gestionDesSalles1.Tabsalle[0].getNbJoueurDansLaSalle() );
	 * 
	 * 
	 * for (int i = 0; i < gestionDesSalles1.Tabsalle[0].getNbMaxTable(); i++){
	 * gestionDesSalles1.Tabsalle[0].tTable[i].setJoueurForfaitair(personne);
	 * System.out.println(gestionDesSalles1.Tabsalle[0].tTable[i].tostring()); }
	 * 
	 * 
	 * 
	 * System.out.println( "getNbJoueurDansLaSalle apres removeJoueur :" +
	 * gestionDesSalles1.Tabsalle[0].getNbJoueurDansLaSalle() );
	 * 
	 * 
	 * // System.out.println(gestionDesSalles1.Tabsalle[1].getNbMaxTable());
	 * 
	 * }
	 */
}
