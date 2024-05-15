package commun_rc;

import java.util.LinkedList;

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

public class Pasers {
	private String quest = "?";
	private String mod = "%";
	private String dollar = "$";
	private String and = "&";
	private String or = "|";
	private String bakslash = "\\";
	private String livrestr = "�";
	private String doublearobas = "@@";
	private String chapeau = "^";
	private String prc1 = "___";
	private String parceI = "{";
	private String parceII = "}";
	private String parce = "[";
	private String parceOP = "/";
	private String parceMultiCoup = dollar;
	private String parceMulti = "]";
	private char toc1 = '(';
	private char toc2 = ',';
	private char toc3 = ')';
	private int length = 18;

	/**
	 * getParsers
	 *
	 * @return String[]
	 */
	public LinkedList getParsers() {

		LinkedList pars = new LinkedList();
		pars.add(getParceOP());
		pars.add("" + getToc1());
		pars.add("" + getToc2());
		pars.add("" + getToc3());
		pars.add(getParce());
		pars.add(getParceI());
		pars.add(getParceII());
		pars.add(getPrc1());
		pars.add(getParceMulti());
		pars.add(getQuest());
		pars.add(getMod());
		pars.add(getDollar());
		pars.add(getAnd());
		pars.add(getOr());
		pars.add(getBakslash());
		pars.add(getLivrestr());
		pars.add(getDoublearobas());
		pars.add(getChapeau());
		return pars;
	}

	/**
	 * isaParser
	 *
	 * @param parser String
	 * @return boolean
	 */
	public boolean isaParser(String parser) {
		LinkedList pars = getParsers();

		return pars.contains(parser);
	}

	/**
	 * getParceMultiCoup
	 *
	 * @return String
	 */
	public String getParceMultiCoup() {
		return parceMultiCoup;
	}

	public String getParceOP() {
		return parceOP;
	}

	public char getToc1() {
		return toc1;
	}

	public char getToc2() {
		return toc2;
	}

	public char getToc3() {
		return toc3;
	}

	public String getParce() {
		return parce;
	}

	public String getParceI() {
		return parceI;
	}

	public String getParceII() {
		return parceII;
	}

	public String getPrc1() {
		return prc1;
	}

	public String getParceMulti() {
		return parceMulti;
	}

	public String getQuest() {
		return quest;
	}

	public String getMod() {
		return mod;
	}

	public String getDollar() {
		return dollar;
	}

	public String getAnd() {
		return and;
	}

	public String getOr() {
		return or;
	}

	public String getBakslash() {
		return bakslash;
	}

	public String getLivrestr() {
		return livrestr;
	}

	public String getDoublearobas() {
		return doublearobas;
	}

	public String getChapeau() {
		return chapeau;
	}

	public int getLength() {
		return length;
	}

	public void setParceMulti(String parceMulti) {
		this.parceMulti = parceMulti;
	}

	public void setParceOP(String parceOP) {
		this.parceOP = parceOP;
	}

	public void setToc2(char toc2) {
		this.toc2 = toc2;
	}

	public void setParce(String parce) {
		this.parce = parce;
	}

	public void setToc1(char toc1) {
		this.toc1 = toc1;
	}

	public void setToc3(char toc3) {
		this.toc3 = toc3;
	}

	public void setPrc1(String prc1) {
		this.prc1 = prc1;
	}

	public void setParceII(String parceII) {
		this.parceII = parceII;
	}

	public void setParceI(String parceI) {
		this.parceI = parceI;
	}

	public void setQuest(String quest) {
		this.quest = quest;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

	public void setDollar(String dollar) {
		this.dollar = dollar;
	}

	public void setAnd(String and) {
		this.and = and;
	}

	public void setOr(String or) {
		this.or = or;
	}

	public void setBakslash(String bakslash) {
		this.bakslash = bakslash;
	}

	public void setLivrestr(String livrestr) {
		this.livrestr = livrestr;
	}

	public void setDoublearobas(String doublearobas) {
		this.doublearobas = doublearobas;
	}

	public void setChapeau(String chapeau) {
		this.chapeau = chapeau;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setParceMultiCoup(String parceMultiCoup) {
		this.parceMultiCoup = parceMultiCoup;
	}

	public Pasers() {
		/*
		 * setPrc1("??"); setParceI("k"); setParceII("____"); setParce("S[S");
		 * setParceOP("/"); setParceMulti("D]D"); setToc1('('); setToc2(',');+�
		 * 
		 * setToc3(')');
		 */
	}

	public static void main(String[] args) {
		Pasers pasers1 = new Pasers();
		System.out.println("ddd  " + pasers1.isaParser("ddd"));
		System.out.println("�  " + pasers1.isaParser("�"));
		System.out.println("%  " + pasers1.isaParser("%"));
	}

}
