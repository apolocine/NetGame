package Gestionnaire;

import java.util.LinkedList;

import commun_rc.PersoInfos;
import commun_rc.TPoint;
import commun_rc.UnCoup;

public interface TableDiscriber {
	public TPoint[][] RandomPosNavir();

	public boolean ValidationWaitCycle(UnCoup coup);

	public void addCoupCycle(UnCoup unCoup);

	public void addCoupGlobale(UnCoup coup);

	public void addCoupGlobale(UnCoup[] Coups);

	public void addJoueur(PersoInfos personne);

	public void addJoueurOnClient(int place, PersoInfos persoInfos);

	public void appendTraceInfos(String traceInfos);

	public int getCoupDuCycleEnCour();

	public UnCoup[] getCoupsduCycle();

	public TPoint[][] getCurenNavirNavires();

	public int getCycleEncour();

	public PersoInfos getIdentityJoueur(int joueur);

	public int getJoueurMax();

	public int getJoueurVivants();

	public TPoint[][] getNavires();

	public int getNbCoupsCycleArecevoire();

	public int getNbjeutonsParJoueur();

	public int getNbjoueursPresent();

	public int getNembrePointResiduel(int joueu);

	public int getNembrePointTouche(int joueur);

	public PersoInfos getTheWiner();

	public String getTraceInfos();

	public LinkedList getlistCoupsduCycle();

	public PersoInfos[] getlocalAllId();

	public Object[] getlocalAllId2();

	public int getnumTable();

	public boolean interditdacce();

	public boolean isFull();

	public boolean isGameStart();

	public boolean isServeurTable();

	public boolean isStart();

	public boolean isThereaWiner();

	public boolean isWaitCycle(int variant_);

	public boolean isWaitingForAllCoup();

	public void remiseAZeroDesCoupsCycleEnCour();

	public int rulenombredejoueurenvie(UnCoup coup);

	public void rulenombredejoueurenvie(UnCoup[] coups);

	public void rulenombredejoueurenvie_A_Revoir(UnCoup[] coups);

	public void rules(UnCoup[] coups);

	public boolean rules2isValide(UnCoup Coup);

	public void rulesattribue(UnCoup[] coups);

	public void reinitialisationdujeu();

	public void setAllAsAlive();

	public void setCoupDuCycleEnCour(int coupDuCycleEnCour);

	public void setCycleEncour(int cycleEncour);

	public void setFull(boolean full);

	public void setGameStart(boolean gameStart);

	public void setJoueurForfaitair(PersoInfos personne);

	public void setJoueurMax(int joueurMax);

	public void setJoueurPointPerdu(int i, int i1, TPoint tPoint);

	public void setJoueurPointTouche(int i, int i1, TPoint tPoint);

	public void setJoueurVivants(int joueurVivants);

	public void setNavires(TPoint[][] Navires);

	public void setNbCoupsCycleArecevoire(int nbCoupsCycleArecevoire);

	public void setNbjoueursPresent(int nbjoueursPresent);

	public void setStart(boolean start);

	public void setTable(int numTble);

	public void setTraceInfos(String traceInfos);

	public void setWaitCycle(boolean waitCycle);

	public void setWaitingForAllCoup(boolean waitingForAllCoup);

	public void setasServeurTable(boolean laTableCentrale);

	public void setintstart(TPoint[][] Navires_, boolean visible);

	public void setlocalintstart(TPoint[][] Navires_, boolean visible);

	public String totring();

	public void upCoupDuCycleEnCour();

	public void upCycleEncour();
}
