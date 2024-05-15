package Interface;

import java.awt.Dialog;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;

import Gestionnaire.Gestionnaire;
import Gestionnaire.NetManager;
import Interface.interfaceJeux.Plateaudejeu;
import commun_rc.PersoInfos;
import commun_rc.TPoint;
import commun_rc.UnCoup;
import jeux.GestionDesSalles;
import jeux.Table;
import media.WavLocaliser;
import observation.ObservateurLog;
import reseau.GestionDuReseau;

/**
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
 * @author Hamid Madani
 * @version 1.0
 */

public class superviseur implements Gestionnaire {

	static ResourceBundle res = ResourceBundle.getBundle("Interface.Res");
	GestionDesSalles gGestionDesSalles;
	NetManager gGestionDuReseau;
	CadrePr parentIHM;
	public ObservateurLog observlog;

	public JTextArea aenvoyer;
	public JTextArea lue;
	public JComboBox Allclient;
	int numsalle = 0;
	int numtable = 0;

	public void setPersoID(PersoInfos persoID_) {
		this.persoID = persoID_;
		setFileNameLog(persoID_.getLogin());

	}

	public PersoInfos getPersoID() {
		return persoID;
	}

	public superviseur(CadrePr parentIHM

			, JTextArea readen, JTextArea towrit, JComboBox ConnectedClient) {
		this.parentIHM = parentIHM;
		this.aenvoyer = towrit;
		this.lue = readen;
		Allclient = ConnectedClient;
		gGestionDesSalles = new GestionDesSalles(this);
		gGestionDuReseau = new GestionDuReseau(this);
		observlog = new ObservateurLog(null);

	}

	/**
	 * setFileNameLog
	 *
	 * @param filename String
	 */
	public void setFileNameLog(String filename) {
		this.observlog.logpathtxtField.setText(filename + res.getString("_log"));
	}

	/**
	 * getDialogBoxLog
	 *
	 * @return Dialog
	 */
	public Dialog getDialogBoxLog() {
		return this.observlog;
	}

	/**
	 * repaint
	 */
	public void repaint() {
		parentIHM.repaint();
	}

	int jesuisle = -1;

	int MonNumeroEst() {
		return jesuisle;
	}

	/**
	 *
	 * @return int
	 */
	public int whereIam() {

		jesuisle = whoIam().getNum();
		return jesuisle;

	}

	public PersoInfos whoIam() {
		Iterator i = getTableDeLaSalle().iterator();
		int x = -1;

		while (i.hasNext()) {
			Object o = i.next();
			PersoInfos tmp = (PersoInfos) o;
			if (tmp.getLogin().equals(parentIHM.getPersoID().getLogin())) {
				return tmp;
			}
		}

		return new PersoInfos();
	}

	public PersoInfos whoIs(int qui) {
		return (PersoInfos) getTableDeLaSalle().get(qui);
	}

	public Table getTableDeLaSalle() {
		return getTableDeLaSalle(numsalle, numtable);
	}

	public Table getTableDeLaSalle(int table, int salle) {
		return gGestionDesSalles.getSalle(salle).getTable(table);
	}

	/**
	 * selon la formule temps de phase egual 2*MTTR + delta phase prerequis temps
	 * MAX par tour de jeux Max de ping reperage du client le plus eloigne reseau
	 * (diametre du reseau) ce calcul devra etre revalue a chaque brodcaste
	 *
	 * @return PersoInfos[]
	 */
	public PersoInfos[] getlocalAllId() {
		return getTableDeLaSalle().getlocalAllId();
	}

	public void ClientOn(int Port, String Host) {

		if (this.gGestionDuReseau.ClientOn(Port, Host)) {
			this.addLogComments(this.persoID.getLogin() + " ", res.getString("client_connect_"));

		} else {
			this.addLogComments(this.persoID.getLogin() + " ", res.getString("probl_me_connexion_r"));
		}

	}

	/**
	 * sendId
	 */
	public void sendId() {
		this.gGestionDuReseau.sendId();
	}

	public void ServerOn(int Port, int NumClientAutorise) {

		if (this.gGestionDuReseau.ServerOn(Port, NumClientAutorise)) {
			this.addLogComments(this.persoID.getLogin() + " ", res.getString("al_coute"));

		} else {
			this.addLogComments(this.persoID.getLogin() + " ", res.getString("probl_me_de_mise_en"));
		}

	}

	/**
	 *
	 * @param Navires TPoint[][]
	 *
	 */
	public void setonClientInitGame(TPoint[][] Navires) {

		// autiser les clics
		try {
			// informer la table dans la salle
			getTableDeLaSalle().setlocalintstart(Navires, true);

		} catch (Exception ex) {
		}

		finally {
			try {
				parentIHM.showAllNavires(getTableDeLaSalle().getNavires());
			} catch (Exception ex1) {
			}
			try {
				setRestartLape(true);
			} catch (Exception ex2) {
			}
			try {
				String str = "";

				str = str + res.getString("les_navires_du_joueur")
						+ getTableDeLaSalle().getIdentityJoueur(this.jesuisle) + "  "
						+ new TPoint().toString(Navires[this.jesuisle]) + "\n";

				addLogComments(str, res.getString("debut_de_la_") + parentIHM.reStartGame() + res.getString("partie"));
			} catch (Exception ex3) {
			}

		}
		// affichage
		// showAllNavires(Navires);

		parentIHM.setTimeurGestionnair(this);
		parentIHM.musicAmbianceSelector(true, 5);
		setWaitScore(false);
	}

	/**
	 * attribueId enregistrement du joueur
	 *
	 * @param Perso PersoInfos
	 */
	public void addIDtitysinServer(PersoInfos Perso) {

		getTableDeLaSalle().addJoueur(Perso);
		update(getTableDeLaSalle().getlocalAllId());

	}

	public void update(PersoInfos[] ALLPerso) {
		selectWhereToshow();
		sendUpdatetoClients(ALLPerso);
	}

	/**
	 * addIDtitysinClient
	 *
	 * @param persoInfoses PersoInfos[]
	 */
	public void addIdentiteOnClient(PersoInfos[] persoInfoses) {

		// l'ajout dans la combobox ici annule
//  removeAlItemtoStrList();
		String perso = "\n";
//  System.out.println("   LA BONNE BOUCLE: ");

		getTableDeLaSalle().clear();
		// System.out.println("addIDtitysinClient : persoInfoses.length : "+
		// persoInfoses.length);

		for (int i = 0; i < persoInfoses.length; i++) {

			getTableDeLaSalle().addJoueurOnClient(i, persoInfoses[i]);

			perso = perso + "\t" + persoInfoses[i].getLogin() + " \n";

			selectpane(persoInfoses[i], i);
		}
		getTableDeLaSalle().setNbjoueursPresent(getTableDeLaSalle().size());
		getTableDeLaSalle().setJoueurVivants(getTableDeLaSalle().getNbjoueursPresent());
		this.getTableDeLaSalle().setNbCoupsCycleArecevoire(getTableDeLaSalle().getJoueurVivants());
		parentIHM.musicAmbianceSelector(true, 7);
		System.out.println(res.getString("les_joueurs_au_tour") + perso);
		addLogComments(res.getString("les_joueurs_au_tour"), perso);

		/*
		 * System.out.println("addJoueurOnClient : getJoueurMax( :"
		 * +getTableDeLaSalle().getJoueurMax());
		 * System.out.println("addJoueurOnClient : getNbjoueursPresent() :" +
		 * getTableDeLaSalle().getNbjoueursPresent());
		 * System.out.println("addJoueurOnClient : getJoueurVivants() :" +
		 * getTableDeLaSalle().getJoueurVivants());
		 * 
		 * System.out.println(perso); addLogComments(perso, " ");
		 * addItemtoStrList(perso);
		 * 
		 */
	}

	/**
	 * addItemtoStrList
	 *
	 * @param string String
	 */
	public void addItemtoStrList(String string) {

		this.Allclient.addItem(string);
		this.Allclient.setPopupVisible(true);

	}

	/**
	 * addItemtoStrList
	 *
	 */
	public void removeAlItemtoStrList() {
		this.Allclient.setPopupVisible(false);
		this.Allclient.removeAll();

	}

	/**
	 * setlocalClientAllIds
	 * 
	 * @param persoInfoses PersoInfos[]
	 */
	public void setlocalClientAllIds(PersoInfos[] persoInfoses) {

		addIdentiteOnClient(persoInfoses);
		parentIHM.setQui(whereIam());

		////
		selectWhereToshow();

	}

	/**
	 * selectWhereToshow affichage du joueur sur la place correspondante
	 */
	public void selectWhereToshow() {

		Iterator i = getTableDeLaSalle().iterator();

		int x = 0;
		while (i.hasNext()) {
			Object o = i.next();
			selectpane(o, x);
			x++;
		}

	}

	/**
	 * selectpane
	 * 
	 * @param qui Object
	 * @param num int
	 */
	private void selectpane(Object qui, int num) {
		PersoInfos quii = (PersoInfos) qui;
		switch (num) {
		case 0:
			parentIHM.getPlateau(num).setText(quii.getLogin());
			break;
		case 1:
			parentIHM.getPlateau(num).setText(quii.getLogin());
			break;
		case 2:
			parentIHM.getPlateau(num).setText(quii.getLogin());
			break;
		case 3:
			parentIHM.getPlateau(num).setText(quii.getLogin());
			break;
		}
	}

	public static void main(String[] args) {

	}

	private PersoInfos persoID;
	private int nbjoueur = 4;

	public void setNbjoueur(int nbjoueur) {
		this.nbjoueur = nbjoueur;
	}

	public int getNbjoueur() {

		return nbjoueur;
	}

	/**
	 * setsendCoup
	 *
	 * @param i      int
	 * @param i1     int
	 * @param tPoint TPoint
	 */
	public void setsendCoup(int i, int i1, TPoint tPoint) {
		UnCoup coup = new UnCoup(i, i1, tPoint);
		gGestionDuReseau.setsendCoup(coup);

	}

	/**
	 * SendTxtTo
	 *
	 * @param i int
	 */
	public void SendTxtTo(int i) {
		gGestionDuReseau.SendTxtTo(i);
	}

	/**
	 * sendinitMaxGame
	 */
	public void TableDeLaSalleinitMaxGame() {
		TPoint[][] MxNavir = getTableDeLaSalle().RandomPosNavir();

		try {
			try {
				gGestionDuReseau.sendinitMaxGame(MxNavir);
			} catch (Exception ex1) {
			}

		} catch (Exception ex) {
		} finally {

			System.out.println("\n\t_____");
			String str = "\n";
			for (int i = 0; i < MxNavir.length; i++) {

				str = str + res.getString("les_navires_du_joueur") + getTableDeLaSalle().getIdentityJoueur(i).getLogin()
						+ "  " + new TPoint().toString(MxNavir[i]) + "\n";

				// prise en compte localement de la disposition des bateaux sur le serveur
				try {
					setLocalInitGame(MxNavir);
				} catch (Exception ex2) {
				}
			} /**
				 * @todo a controler
				 *
				 */
			repaint();
			System.out.print(str);
			this.addLogComments(str, "\n\t_____");

		}

	}

	public void reinitialistationDuJeu() {

		try {
			try {
				// reinitialisation des donnees sur la table du jeu
				getTableDeLaSalle().reinitialisationdujeu();

			} catch (Exception ex) {
			}

			try {
				// reinitialisation de l'affichage
				this.parentIHM.reinitialistationDuJeu();
			} catch (Exception ex1) {
			}

		} catch (Exception ex2) {
		}

		finally { // reinitialisation de la disposition des bateaux
			if (getTableDeLaSalle().isServeurTable()) {
				TableDeLaSalleinitMaxGame();

			}

		}
		this.parentIHM.repaint();
	}

	/**
	 * sendUpdatetoClients
	 * 
	 * @param ALLPerso PersoInfos[]
	 */
	public void sendUpdatetoClients(PersoInfos[] ALLPerso) {
		System.out.println(res.getString("il_y_a") + ALLPerso.length + res.getString("joueur_s_en_ligne"));
		switch (ALLPerso.length) {

		/**
		 * si temps diponible essayer de devlopper IA !
		 */
		case 0:
		case 1:
		case 2:
		case 3:
			try {
				gGestionDuReseau.sendUpdatetoClients(ALLPerso);
			} catch (Exception ex) {
			}

			System.out.println(res.getString("il_y_a") + ALLPerso.length + res.getString("joueur_s_en_ligne"));
			break;
		case 4:
			try {
				System.out.println(res.getString("il_y_a1") + ALLPerso.length + res.getString("joueur_s_en_ligne1"));
				gGestionDuReseau.sendUpdatetoClients(ALLPerso);

			} catch (Exception e) {

			} finally {
				/**
				 * iniitalisation du jeux
				 */

				TableDeLaSalleinitMaxGame();
			}

			break;
		default:
			System.out.println(res.getString("il_y_a1") + ALLPerso.length + res.getString("en_ligne"));
			break;
		}

	}

	/**
	 * manCoup
	 *
	 * @param unCoup UnCoup
	 */

	/**
	 * sendScorptoClients
	 *
	 * @param score String
	 */
	private void sendScoretoClients(String score) {

		gGestionDuReseau.SendScoreForAllClents(score);
	}

	/**
	 * selectWhereToshowCoups
	 *
	 * 
	 * @param Coups UnCoup[]
	 */
	private void selectWhereToshowCoups(UnCoup[] Coups) {
		// System.out.println(
		// "\t\tentree Traitement superviseur.selectWhereToshowCoups");
		int numcoup = getTableDeLaSalle().getCycleEncour();
		for (int i = 0; i < Coups.length; i++) {
			setCoupOnPanel(Coups[i], numcoup);

		}
		// System.out.println(
		// "\t\tsotie Traitement superviseur.selectWhereToshowCoups");
	}

	/**
	 * setCoupOnPanel
	 *
	 * @param o     Object
	 * @param place int
	 */

	private void setCoupOnPanel(Object o, int place) {

		UnCoup uncoup = (UnCoup) o;
		if (uncoup.isTouche()) {
			// Coup ayant touche un case pleine
			setPointToucheSurPanel(place, uncoup);
		} else {
			setPointPerduSurPanel(place, uncoup);
		}

		parentIHM.repaint();
	}

	/**
	 * getNembrePointResiduel
	 *
	 * @param joueu int
	 * @return int
	 */
	private int getNembrePointResiduel(int joueu) {
		return this.getTableDeLaSalle().getNembrePointResiduel(joueu);
	}

	void setPointToucheSurPanel(int place, UnCoup coup) {
		// affichage des points touches
		getPlateau(coup.getReceiver()).bBatailleNavale.setJoueurPointTouche(place, coup.getPoint());

		this.getTableDeLaSalle().setJoueurPointTouche(this.getTableDeLaSalle().getnumTable(), coup.getReceiver(),
				coup.getPoint());

		getPlateau(coup.getReceiver()).setNbrDevieEncour(getNembrePointResiduel(coup.getReceiver()));

		if (!coup.isAlive()) {

			// si le joueur mort est le joueur local
			if (coup.getReceiver() == this.MonNumeroEst()) {
				// interdiction de la reprise du jeu chez le joueur mort
				// pour etre sur qu'il ne renvoie pas de coups avant le traitement
				setRestartLape(false);

				this.getTableDeLaSalle().getIdentityJoueur(coup.getReceiver()).setAlive(false);

				setWaitScore(true);

				// getPlateau(coup.getReceiver()).setTextGameState("Game Over" +
				// whoIam().getLogin());

				// si le joueur mort est le joueur local desactivation de tous les paneaux
				// au nombre de joueur totale a quatre
				for (int i = 0; i < 4; i++) {
					getPlateau(i).setDisable(true);
					if (i != this.MonNumeroEst()) {
						getPlateau(i).setTextGameState(res.getString("X"));
						positionTMPClic(new TPoint(-30, -30), i);
					}

				}
				// faire mouvoir le cadre
				parentIHM.moveit(0, 0, 1, 0);
				// lancer la music du GameOver
				parentIHM.musicAmbianceSelector(true, 9);

			} else {
				getPlateau(coup.getReceiver()).setDisable(true);
			}
			getPlateau(coup.getReceiver()).setTextGameState(res.getString("Game_Over"));
			parentIHM.musicAmbianceSelector(false, 3);

		} else {
			parentIHM.musicAmbianceSelector(true, 8);

		}

	}

	private boolean waitScorp = false;

	/**
	 * setWaitScorp
	 *
	 * @param b boolean
	 */
	private void setWaitScore(boolean b) {
		this.waitScorp = b;
	}

	public boolean isWaitScore() {
		return this.waitScorp;

	}

	void setPointPerduSurPanel(int place, UnCoup coup) {
		getPlateau(coup.getReceiver()).bBatailleNavale.setJoueurPointPerdu(place, coup.getPoint());

		parentIHM.musicAmbianceSelector(true, 6);
	}

	public Plateaudejeu getPlateau(int x) {
		return parentIHM.getPlateau(x);

	}

	/**
	 * sendUpdateCoupstoClients envoi des points au clients
	 * 
	 * @param unCoups UnCoup[]
	 *
	 */
	private void sendUpdateCoupstoClients(UnCoup[] unCoups) {
		// System.out.println("Avant superviseur.sendUpdateCoupstoClientse " + unCoups);

		gGestionDuReseau.forwardCoups(unCoups);
		// System.out.println("Apres superviseur.sendUpdateCoupstoClientse " + unCoups);
		System.out.println(res.getString("superviseur_forward") + unCoups.length + res.getString("coups_"));
	}

	public void man(TPoint Point) {

		parentIHM.rPlateaudejeuL.bBatailleNavale.setPcurrentLocation(Point);

	}

	public void positionTMPClic(TPoint Point, int quelPanel) {

		getPlateau(quelPanel).bBatailleNavale.setPcurrentLocation(Point);

	}

	/**
	 * setText
	 *
	 * @param str String
	 */
	public void setText(String str) {
		this.lue.setText(str);

	}

	/**
	 *
	 * @return String
	 */
	public String getText() {
		return this.lue.getText();
	}

	/**
	 * getTexttosend
	 *
	 * @return String
	 */
	public String getTexttosend() {
		return this.aenvoyer.getText();
	}

	/**
	 * setLocalInitGame
	 *
	 * @param Navires TPoint[][]
	 */
	public void setLocalInitGame(TPoint[][] Navires) {

		parentIHM.setintstart(Navires, true);

		WavLocaliser.playMidimusic01();
		setWaitScore(false);
	}

	TPoint hitPoint = new TPoint();

	/**
	 * setHit
	 *
	 * @param Point TPoint
	 */
	public void setHit(TPoint Point) {

		hitPoint = Point;
	}

	/**
	 * getNbjeutonsParJoueur
	 *
	 * @return int
	 */
	public int getNbjeutonsParJoueur() {
		return getTableDeLaSalle().getNbjeutonsParJoueur();
	}

	/**
	 * receptionCoupParServeur Traittement du coup a sa reception par le serveur
	 * 
	 * @param unCoup UnCoup
	 */
	public void receptionCoupParServeur(UnCoup unCoup) {

		manCoup(unCoup);
		System.out.println(unCoup.toString());
	}

	/**
	 * manCoup
	 *
	 * @param unCoup UnCoup
	 */
	// des variables pour le log

	public void manCoup(UnCoup unCoup) {
		// Structure de l'ensemble des coups pour un cycle
		UnCoup[] Coups = null;
		// Init du vainqueur designe
		PersoInfos winer = null;
		int ic = 0;
		int icc = 0; // des variables pour le log

		// changement de invariant du prochain cycle
		this.getTableDeLaSalle().setNbCoupsCycleArecevoire(getTableDeLaSalle().getJoueurVivants());

		//// traitement coups par coup reeu demande si le cycle est fini
		if (getTableDeLaSalle().ValidationWaitCycle(unCoup)) {

			// recuperation des coups traites
			Coups = this.getTableDeLaSalle().getCoupsduCycle();

			/// diffusion de l'ensembles des coups traites
			sendUpdateCoupstoClients(Coups);

			// Affichage serveur
			selectWhereToshowCoups(Coups);
			addLogComments(res.getString("Seperviseur_") + getTableDeLaSalle().getTraceInfos(), "_");
			getTableDeLaSalle().setTraceInfos("");
			int nbjoueur = getTableDeLaSalle().getJoueurVivants();
			ic = getTableDeLaSalle().getCoupDuCycleEnCour();
			icc = getTableDeLaSalle().getCycleEncour();
			// System.out.println("le cycle numero : " + icc//,+" en attente du " + ic + "
			// coup(s)");
			getTableDeLaSalle().remiseAZeroDesCoupsCycleEnCour();

			switch (nbjoueur) {
			case 0: {
				// nbjoueur=0
				winer = getTableDeLaSalle().getTheWiner();
				sendScoretoClients(res.getString("Match_null_aucun"));
				getTableDeLaSalle().setJoueurVivants(0);
				getTableDeLaSalle().setAllAsAlive();
				getTableDeLaSalle().setCycleEncour(0);
				addLogComments(getTableDeLaSalle().getTraceInfos(), res.getString("Nous_attandons_La"));

				getTableDeLaSalle().setTraceInfos("");
			}

				break;
			case 1: { // nbjoueur=1
				winer = getTableDeLaSalle().getTheWiner();
				sendScoretoClients(res.getString("The_winer_is") + winer.getLogin());
				// remise a zero du nombre de joueurs presents

				getTableDeLaSalle().setJoueurVivants(0);
				getTableDeLaSalle().setAllAsAlive();
				getTableDeLaSalle().setCycleEncour(0);

				addLogComments(getTableDeLaSalle().getTraceInfos() + res.getString("The_winer_is_") + winer.getLogin(),
						res.getString("Nous_attandons_La"));
				getTableDeLaSalle().setTraceInfos("");
			}
				break;
			default: {
				// incrementation du cycle au niveau de la table du serveur
				getTableDeLaSalle().upCycleEncour();
				getTableDeLaSalle().setTraceInfos("");
			}
				break;
			}

		}

	}

	/**
	 * receptionCoupParClient
	 *
	 *
	 * @param Coups UnCoup[]
	 */
	public void receptionCoupParClient(UnCoup[] Coups) {
		/*
		 * if(this.getTableDeLaSalle(). getIdentityJoueur(MonNumeroEst()).isAlive() ){
		 * setRestartLape(true); }
		 */
		try {
			// incrementation du cycle au niveau de la table du joueur
			selectWhereToshowCoups(Coups);

			getTableDeLaSalle().upCycleEncour();
			setRestartLape(true);

		} catch (Exception ex) {
		} finally {
			/**
			 * permetre au joueur de lencer un coup
			 * 
			 * @param <any> unknown
			 */

// la remise a zero n'est approprie que chez le serveurmessagerecu.getOneCoup().getSender()).getLogin()
// getTableDeLaSalle().remiseAZeroDesCoupsCycleEnCour();

			setRestartLape(true);

		}

		String str = res.getString("le_cycle_N_") + getTableDeLaSalle().getCycleEncour();
		for (int i = 0; i < Coups.length; i++) {
			str = str + "\n" + res.getString("le_coup") + i + res.getString("envoy_par_le_joueur_")
					+ getTableDeLaSalle().getIdentityJoueur(Coups[i].getSender()).getLogin()
					+ res.getString("contre_le_joueur_")
					+ getTableDeLaSalle().getIdentityJoueur(Coups[i].getReceiver()).getLogin()
					+ res.getString("sur_la_position_") + Coups[i].getPoint();

		}
		System.out.println(str);

		addLogComments("", str);
		// setRestartLape(true);

	}

	private boolean restartLape;

	/**
	 * canRestartLape
	 *
	 * @return boolean
	 */
	public boolean canRestartLape() {
		return restartLape;
	}

	/**
	 * setRestartLape
	 *
	 * @param restart boolean
	 */
	public void setRestartLape(boolean restart) {
		int x = 0;
		if (restart) {
			x = getTableDeLaSalle().getCycleEncour() + 1;
			/**/
			parentIHM.setTitle(res.getString("GO") + this.parentIHM.getPersoID().getLogin()
					+ res.getString("pour_le_tour_N_") + x);

			for (int i = 0; i < 4; i++) {
				if (i != this.whereIam()) {
					getPlateau(i).setTextWait("");

				}

			}
			parentIHM.reSatrtTimer();

		} else {
			// une confirmation de l'arret du cont a revour
			this.bye();

			x = getTableDeLaSalle().getCycleEncour() + 2;
			/*  */
			parentIHM.setTitle(res.getString("Attendez") + this.parentIHM.getPersoID().getLogin()
					+ res.getString("le_tour_N_") + x);

			for (int i = 0; i < 4; i++) {
				if (i != this.whereIam()) {
					getPlateau(i).setTextWait(res.getString("Wait_for_lap_") + x);
				}
			}
			repaint();

		}

		this.restartLape = restart;
	}

	/**
	 * iamconnected
	 *
	 * 
	 * @param connectedr boolean
	 */
	public void iamconnected(boolean connectedr) {

		this.parentIHM.isediamconnected(connectedr);
	}

	/**
	 * sendDefault
	 */
	public void sendDefault() {
		// en envoi un coup que si on attent pas le Scorp
		if (!this.isWaitScore()) {
			this.setText(res.getString("Un_nouveau_coup_a_t"));
			this.gGestionDuReseau.setsendCoup(getUnPointPerdu());
			this.setText("" + this.parentIHM.getTimerValeur());
		}

	}

	/**
	 * getUnPointPerdu
	 *
	 * @return UnCoup
	 */
	int xpt = 0;

	private UnCoup getUnPointPerdu() {

		TPoint pt = getTableDeLaSalle().getNavires()[jesuisle][xpt];
		xpt++;
		return new UnCoup(jesuisle, jesuisle, pt);
	}

	public TPoint[][] getNavires() {
		return getTableDeLaSalle().getNavires();
	}

	/**
	 * bye
	 */
	public void bye() {
		this.parentIHM.bye();
	}

	/**
	 * erreurMessage
	 *
	 * @param string String
	 */
	public void erreurMessage(String string) {

		javax.swing.JOptionPane.showMessageDialog(new JDialog(), res.getString("Attention_l_erreur") + string

		);

	}

	/**
	 * addLogComments
	 *
	 * @param col1 String
	 * @param col2 String
	 */
	public void addLogComments(String col1, String col2) {
		this.observlog.addandAndShow(col1, col2);
	}

	/**
	 * showScorpMessage
	 *
	 * @param str String
	 */
	public void showScorpMessage(String str) {
		setWaitScore(false);
		setRestartLape(false);
		for (int i = 0; i < 4; i++) {
			getPlateau(i).setDisable(true);
			if (i != this.MonNumeroEst()) {
				positionTMPClic(new TPoint(-30, -30), i);
			}

		}

		int tour = parentIHM.reStartGame() - 1;
		addLogComments(res.getString("Fin_de_partie_") + tour + "\n", str);
		javax.swing.JOptionPane.showMessageDialog(new JDialog(), str

		);
		/**
		 * @todo a tester intencement
		 *
		 * 
		 *       int value = JOptionPane.showConfirmDialog(new JDialog(), " voulez vous
		 *       continue ?", " Fin de partie ", JOptionPane.CANCEL_OPTION) ;
		 */
		getTableDeLaSalle().setJoueurVivants(0);
		this.reinitialistationDuJeu();

	}

	/**
	 * setasServeur
	 *
	 * @param b boolean
	 */
	public void setasServeur(boolean b) {
		getTableDeLaSalle().setasServeurTable(b);
	}

	/**
	 * getJoueurPersID
	 *
	 * @param i int
	 * @return PersoInfos
	 */
	public PersoInfos getJoueurPersID(int i) {
		return (PersoInfos) getTableDeLaSalle().get(i);
	}

	/**
	 * notifyReinitGameFromClient
	 *
	 */
	public void notifyReinitGameFromClient() {
		// a chaque confirmation de la reprise du jeu par un joueur le nembre de joueur
		// vivant
		// est incremente
		getTableDeLaSalle().setJoueurVivants(getTableDeLaSalle().getJoueurVivants() + 1);
		this.getTableDeLaSalle().setNbCoupsCycleArecevoire(getTableDeLaSalle().getJoueurVivants());
		System.out.println(res.getString("Superviser_notifyR") + getTableDeLaSalle().getJoueurVivants());
		if (getTableDeLaSalle().isServeurTable()
				&& getTableDeLaSalle().getJoueurVivants() == getTableDeLaSalle().getJoueurMax()) {
			this.reinitialistationDuJeu();

		}
	}

}
