package reseau;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
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

public class ListGroup extends java.util.LinkedList {

	void displayLinkedList(LinkedList set) {
		System.out.println("The size of the set is: " + set.size());
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Object o = i.next();
			if (o == null)
				System.out.println("null");
			else
				System.out.println(o.toString());
		}
	}

	public ListGroup() {
	}

	public ListGroup(Collection p0) {
		super(p0);
	}

	public static void main(String[] args) {
		ListGroup listGroup1 = new ListGroup();
	}
}
