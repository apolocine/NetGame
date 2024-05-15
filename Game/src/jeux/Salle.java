package jeux;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

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

public class Salle extends LinkedList {
	GestionDesSalles gSalles;
	public Table[] tTable = new Table[4];
	private int MaxTable = 1;

	public boolean isFull() {
		for (int i = 0; i < tTable.length; i++) {
			if (!tTable[i].isFull()) {
				return false;
			}
		}
		return true;
	}

	public void setNbMaxTable(int MaxTable) {
		this.MaxTable = MaxTable;
	}

	public int getNbMaxTable() {
		return MaxTable;
	}

	public int getNbJoueurDansLaSalle() {
		Iterator i = this.iterator();
		int x = 0;
		while (i.hasNext()) {
			Object o = i.next();
			Table tmp = (Table) o;
			x = x + tmp.getNbjoueursPresent();
		}
		return x;
	}

	public Table getFirstTable() {
		return (Table) this.getFirst();
	}

	public Table getTable(int num) {
		return (Table) this.get(num);
	}

	public Salle(GestionDesSalles gSalles) {
		this.gSalles = gSalles;
		for (int i = 0; i < getNbMaxTable(); i++) {
			tTable[i] = new Table(i);
			addTable(tTable[i]);
		}
	}

	void addTable(Table tTable) {
		add(tTable);
	}

	public Salle(Collection p0) {
		super(p0);
	}

}
