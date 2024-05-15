package Interface;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import Gestionnaire.Gestionnaire;
import Interface.interfaceJeux.Plateaudejeu;
import Interface.interfaceJeux.RotatePanel;
import Interface.resources.FRessource;
import Interface.resources.time.Timeur;
import commun_rc.PersoInfos;
import commun_rc.TPoint;
import media.WavLocaliser;
import observation.JImage;
import observation.Mover;
import time.Horloge;
import time.TimerTaskMi;

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
 * @stereotype entity
 */

public class CadrePr extends JFrame {

	static ResourceBundle res = ResourceBundle.getBundle("Interface.Res");
	String rulesTxt = res.getString("Bienvenue_sur_NetGame") + res.getString("apr_s_identification")
			+ res.getString("au_serveur_avec_votre") + res.getString("quatri_me_joueur_vous")
			+ res.getString("contre_qui_vous") + res.getString("tentatives_de_tirs") + res.getString("leur_tour_")
			+ res.getString("_les_carr_s_rouges") + res.getString("_les_carr_s_magentas");;/**/
	FRessource resource = new FRessource();
	Gestionnaire gestionDesAffichage1;
	JImage myJImage, myJImage2;
	Timeur tTimeur;
	Mover deplacer = new Mover(this) ;

	Box myHBox = Box.createHorizontalBox();
	Box QuiteretObserverBox = Box.createVerticalBox();
	//
	// public Chat chat = new Chat();
	private PersoInfos persoID = new PersoInfos();

	SeterIdentity seterIdentity = new SeterIdentity(this);
	SeterIdentity seterConnection = new SeterIdentity(this);

	private int nbjeutonsParJoueur = 18;

	public void setPersoID(PersoInfos persoID) {
		this.persoID = persoID;
		this.gestionDesAffichage1.setPersoID(persoID);
	}

	private int qui = -1;

	public void setQui(int qui) {
		persoID.setNum(qui);
		/**
		 * @todo ce si est a enlever si ca ne sert plus a rien desormais cqui sert a
		 *       l'identification
		 *
		 **/
		this.qui = qui;
	}

	public int getQui() {
		persoID.getNum();
		return qui;
	}

// contreQui determine le numero de l'adverssaire au moment du clic sur la grille
	private int contreQui = -1;

	public void setContreQui(int contreQui) {
		this.contreQui = contreQui;
	}

	public void setNbjeutonsParJoueur(int nbjeutonsParJoueur) {
		this.nbjeutonsParJoueur = nbjeutonsParJoueur;
	}

	public int getContreQui() {
		return contreQui;
	}

	public PersoInfos getPersoID() {
		return persoID;
	}

	public int getNbjeutonsParJoueur() {
		return nbjeutonsParJoueur;
	}

	String ServerName = "";
	int ServerPort = 2004;

	JPopupMenu ActionPopupMenu1 = new JPopupMenu();
	JMenu jMenu5 = new JMenu();
	JMenuItem ServerOnPop = new JMenuItem();
	JMenuItem ServerOFFPop = new JMenuItem();
	JMenu jMenuConn = new JMenu();
	JMenuItem ClientOnPop = new JMenuItem();
	JMenuItem ClientOFFPop = new JMenuItem();
	JMenuItem KillAllPop = new JMenuItem();

	RotatePanel logo = new RotatePanel(res.getString("images_netgamelogoi"));

	RotatePanel PlateaudejeuRot = new RotatePanel(res.getString("images_Tortu_jpg"));

	public Plateaudejeu rPlateaudejeuL;
	public Plateaudejeu rPlateaudejeuR1;
	public Plateaudejeu rPlateaudejeuR2;
	public Plateaudejeu rPlateaudejeuR3;
	JButton rotation = new JButton();
	JPanel jPanel5 = new JPanel();

	JPanel contentPane;
	JMenuBar jMenuBar1 = new JMenuBar();
	JMenu jMenuFile = new JMenu();
	JMenuItem jMenuFileExit = new JMenuItem();
	JMenu jMenuHelp = new JMenu();
	JMenuItem jMenuHelpAbout = new JMenuItem();
	JToolBar jToolBar = new JToolBar();
	ImageIcon image1;
	ImageIcon image2;
	ImageIcon image3;
	ImageIcon image4;
	ImageIcon gomusic;
	ImageIcon stopmus;
	ImageIcon nocap;
	ImageIcon nocapup;
	JLabel statusBar = new JLabel();

	BorderLayout borderLayout1 = new BorderLayout();
	JMenuItem chatbar = new JMenuItem();
	JMenu Observateur = new JMenu();
	JMenu jMenu4 = new JMenu();
	JTabbedPane jTabbedPane1 = new JTabbedPane();
	JTabbedPane Game = new JTabbedPane();
	JTabbedPane Connection = new JTabbedPane();
	JTabbedPane jTabbedPane4 = new JTabbedPane();
	JTabbedPane Apropos = new JTabbedPane();
	JTextArea AproposAuteurs = new JTextArea();
	JPanel GameN1 = new JPanel();
	JPanel identification = new JPanel();
	JPanel connectiontab = new JPanel();
	JPanel jPanel4 = new JPanel();

	JTextArea Tread = new JTextArea();
	JScrollPane scrTread = new JScrollPane();
	JTextArea Twrite = new JTextArea();
	JScrollPane scrTwrite = new JScrollPane();

	Border border1;
	TitledBorder titledBorder1;
	Border border2;
	Border border3;
	Border border4;

	JMenuItem helpMenu = new JMenuItem();
	JButton start = new JButton();
	Border border5;
	Border border6;
	Border border7;
	/*  */

//Construire le cadre
	public CadrePr(PersoInfos perso, String host, int port) {

		this.persoID = perso;
		this.ServerName = host;
		this.ServerPort = port;

		// this.setUndecorated(true);

		// qSonido.play("sound/spacemusic.au");
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void repaint() {
		super.repaint();
		for (int i = 0; i < 4; i++) {
			getPlateau(i).repaint();

		}

	}

	// Construire le cadre
	public CadrePr() {
		this.setUndecorated(true);

		// qSonido.play("sound/spacemusic.au");
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			/**
			 * @todo uncomment this line to get music at start
			 * @param <any> unknown
			 */
			musicAmbianceSelector(false, 1);
		} catch (Exception ex) {
		}

	}

	/**
	 *
	 * @param Navires TPoint[][]
	 */
	public void setInitGame(TPoint[][] Navires) {

		rPlateaudejeuL.bBatailleNavale.setintstart(Navires, true);

	}

	public void createPanelinitjeux() {
		rPlateaudejeuL = new Plateaudejeu(0, Color.PINK, Color.BLUE, true);
		rPlateaudejeuR1 = new Plateaudejeu(1, Color.GREEN, Color.GREEN, true);
		rPlateaudejeuR2 = new Plateaudejeu(2, Color.ORANGE, Color.LIGHT_GRAY, false);
		rPlateaudejeuR3 = new Plateaudejeu(3, Color.MAGENTA, Color.PINK, true);

	}

	// Initialiser le composant
	private void jbInit() throws Exception {

		tTimeur = new Timeur(this.gestionDesAffichage1, 10);
		tTimeur.setCanautoplay(true);
		this.tTimeur.setGocap(captureC.isSelected());
		myJImage = new JImage(res.getString("images_earth_jpg"));

		border5 = BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.pink, Color.green, Color.orange),
				BorderFactory.createEmptyBorder(10, 10, 10, 10));
		border6 = BorderFactory.createCompoundBorder(border5, titledBorder1);
		border7 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.blue, Color.yellow, Color.red,
				Color.lightGray);
		border8 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.orange, Color.orange, Color.black,
				Color.black);
		myBox1 = Box.createVerticalBox();
		myBox2 = Box.createVerticalBox();

		createPanelinitjeux();
		rPlateaudejeuR1.addMouseListener(new CadrePr_rPlateaudejeuR1_mouseAdapter(this));

		image1 = new ImageIcon(Interface.CadrePr.class.getResource(res.getString("openFile_png")));
		image2 = new ImageIcon(Interface.CadrePr.class.getResource(res.getString("closeFile_png")));
		image3 = new ImageIcon(Interface.CadrePr.class.getResource(res.getString("help_png")));
		image4 = new ImageIcon(Interface.CadrePr.class.getResource(res.getString("cap_jpg")));
		nocap = new ImageIcon(Interface.CadrePr.class.getResource(res.getString("nocap_png")));
		nocapup = new ImageIcon(Interface.CadrePr.class.getResource(res.getString("nocapup_png")));
		gomusic = new ImageIcon(Interface.CadrePr.class.getResource(res.getString("gomusic_png")));
		stopmus = new ImageIcon(Interface.CadrePr.class.getResource(res.getString("stopmus_png")));

		contentPane = (JPanel) this.getContentPane();
		border1 = new MatteBorder(null);
		titledBorder1 = new TitledBorder(border1, null);
		border2 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.pink, Color.pink, new Color(124, 124, 124),
				new Color(178, 178, 178));
		border3 = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.pink, new Color(99, 99, 124),
				new Color(142, 142, 178));
		border4 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.pink, new Color(99, 99, 124),
				new Color(142, 142, 178));
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(537, 635));
		this.setTitle(resource.getString("Title"));
		this.addMouseListener(new CadrePr_this_mouseAdapter(this));
		this.addKeyListener(new CadrePr_this_keyAdapter(this));

		statusBar.setText(rulesTxt);

		statusBar.setBackground(Color.orange);
		statusBar.setDebugGraphicsOptions(0);
		new Horloge(statusBar, Locale.FRANCE);

		jMenuFile.setText(res.getString("Fichier"));
		jMenuFileExit.setText(res.getString("Quitter"));
		jMenuFileExit.addActionListener(new CadrePr_jMenuFileExit_ActionAdapter(this));
		jMenuHelp.setText(res.getString("Aide"));
		jMenuHelpAbout.setText(res.getString("Apropos"));
		jMenuHelpAbout.addActionListener(new CadrePr_jMenuHelpAbout_ActionAdapter(this));
		chatbar.setText(res.getString("Tableau_chat"));
		chatbar.addActionListener(new CadrePr_chatbar_actionAdapter(this));
		AproposAuteurs.setBackground(new Color(214, 180, 28));
		AproposAuteurs.setText(res.getString("dr_Madani_Hamid_drmdh"));
		AproposAuteurs.setTabSize(8);
		Tread.setBackground(UIManager.getColor(res.getString("EditorPane")));
		Tread.setBorder(border3);
		Tread.setMinimumSize(new Dimension(453, 20));
		Tread.setPreferredSize(new Dimension(453, 185));
		Tread.setRequestFocusEnabled(true);
		Tread.setToolTipText("");
		Tread.setSelectedTextColor(Color.red);
		Tread.setSelectionColor(Color.orange);
		Tread.setText("");
		Tread.setLineWrap(true);
		Tread.setRows(0);
		Tread.setWrapStyleWord(true);
		Twrite.setBackground(UIManager.getColor(res.getString("EditorPane")));
		Twrite.setBorder(border3);
		Twrite.setMinimumSize(new Dimension(61, 20));
		Twrite.setOpaque(true);
		Twrite.setPreferredSize(new Dimension(453, 185));
		Twrite.setToolTipText("");
		Twrite.setMargin(new Insets(0, 0, 0, 0));
		Twrite.setSelectedTextColor(Color.orange);
		Twrite.setSelectionColor(Color.red);
		Twrite.setLineWrap(true);
		Twrite.setTabSize(8);
		Twrite.setWrapStyleWord(true);
		rotation.setText(res.getString("rotation"));
		rotation.addActionListener(new CadrePr_rotation_actionAdapter(this));
		Apropos.setPreferredSize(new Dimension(250, 340));
		jTabbedPane4.setPreferredSize(new Dimension(250, 340));
		jPanel5.setPreferredSize(new Dimension(250, 340));
		GameN1.setForeground(Color.orange);
		GameN1.setPreferredSize(new Dimension(921, 195));
		GameN1.addKeyListener(new CadrePr_GameN1_keyAdapter(this));
		jPanel4.setBackground(new Color(214, 180, 28));
		jPanel4.setPreferredSize(new Dimension(921, 195));

		rPlateaudejeuL.setDebugGraphicsOptions(0);
		rPlateaudejeuL.setMinimumSize(new Dimension(0, 0));
		// rPlateaudejeuL.setPreferredSize(new Dimension(250,250 ));
		rPlateaudejeuL.setToolTipText(res.getString("HI_B_L"));
		rPlateaudejeuL.addMouseListener(new CadrePr_rPlateaudejeuL_mouseAdapter(this));

		rPlateaudejeuR1.setMinimumSize(new Dimension(0, 0));
		// rPlateaudejeuR1.setPreferredSize(new Dimension(250, 250));
		rPlateaudejeuR1.setToolTipText(res.getString("R1"));

		/**/
		rPlateaudejeuR2.setMinimumSize(new Dimension(0, 0));
		// rPlateaudejeuR2.setPreferredSize(new Dimension(250, 250));
		rPlateaudejeuR2.setToolTipText(res.getString("R2"));

		rPlateaudejeuR2.addMouseListener(new CadrePr_rPlateaudejeuR2_mouseAdapter(this));

		rPlateaudejeuR3.setMinimumSize(new Dimension(0, 0));
//     rPlateaudejeuR3.setPreferredSize(new Dimension(250, 250));
		rPlateaudejeuR3.setToolTipText(res.getString("R3"));

		rPlateaudejeuR3.addMouseListener(new CadrePr_rPlateaudejeuR3_mouseAdapter(this));

		contentPane.setBackground(new Color(231, 180, 216));
		contentPane.setPreferredSize(new Dimension(931, 515));
		Game.addKeyListener(new CadrePr_Game_keyAdapter(this));

		helpMenu.setText(res.getString("Help"));
		helpMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke('\uFFFF'));

		ServerOnPop.setText(res.getString("Server_On"));
		ServerOnPop.addActionListener(new CadrePr_ServerOnPop_actionAdapter(this));
		ServerOFFPop.setText(res.getString("Server_OFF"));
		jMenuConn.setFont(new java.awt.Font(res.getString("Dialog"), 1, 14));
		jMenuConn.setForeground(Color.magenta);
		jMenuConn.setBorder(null);
		jMenuConn.setToolTipText("");
		jMenuConn.setActionCommand(res.getString("connexion"));
		jMenuConn.setBorderPainted(true);
		jMenuConn.setFocusPainted(true);
		jMenuConn.setIcon(null);
		jMenuConn.setRolloverEnabled(true);
		jMenuConn.setText(res.getString("connexion"));
		ClientOnPop.setForeground(Color.black);
		ClientOnPop.setHorizontalAlignment(SwingConstants.LEADING);
		ClientOnPop.setText(res.getString("Client_On"));
		ClientOnPop.addActionListener(new CadrePr_ClientOnPop_actionAdapter(this));
		ClientOFFPop.setText(res.getString("Client_OFF"));
		KillAllPop.setFont(new java.awt.Font(res.getString("Dialog"), 1, 14));
		KillAllPop.setForeground(Color.red);
		KillAllPop.setText(res.getString("Kill_All"));

		connectmi.addMouseListener(new CadrePr_connectmi_mouseAdapter(this));
		Sendbt.setBackground(Color.pink);
		Sendbt.setForeground(Color.blue);
		Sendbt.setAlignmentY((float) 0.5);
		Sendbt.setBorder(border8);
		Sendbt.setPreferredSize(new Dimension(163, 26));
		Sendbt.setActionCommand(res.getString("Sendbt"));
		Sendbt.setContentAreaFilled(true);
		Sendbt.setMargin(new Insets(2, 34, 2, 14));
		Sendbt.setText(res.getString("Send"));
		Sendbt.addMouseListener(new CadrePr_Sendbt_mouseAdapter(this));
		Sendbt.addActionListener(new CadrePr_Sendbt_actionAdapter(this));
		ConnPop.setActionCommand(res.getString("Connexion"));
		ConnPop.setText(res.getString("Connexion"));
		Sender.setAlignmentY((float) 0.5);
		Sender.setActionCommand(res.getString("Send"));
		Sender.setText(res.getString("Send"));
		PrInfosSender.setActionCommand(res.getString("PrInfos"));
		PrInfosSender.setText(res.getString("PrInfos"));

		AllPersonSender.setActionCommand(res.getString("AllPerson"));
		AllPersonSender.setText(res.getString("AllPerson"));

		InitGameSender.setText(res.getString("InitGame"));
		InitGameSender.addActionListener(new CadrePr_InitGameSender_actionAdapter(this));
		TxtMsg.setText(res.getString("Msg"));
		TxtMsg.addActionListener(new CadrePr_TxtMsg_actionAdapter(this));
		start.addMouseListener(new CadrePr_start_mouseAdapter(this));
		PlateaudejeuRot.setFont(new java.awt.Font(res.getString("Arial"), 0, 19));
		PlateaudejeuRot.setForeground(Color.orange);
		tTimeur.setBackground(new Color(214, 180, 28));
		tTimeur.setPreferredSize(new Dimension(40, 20));

		tTimeur.setRequestFocusEnabled(true);
		tTimeur.setToolTipText("");
		jPanel1.setBackground(new Color(214, 180, 28));
		jPanel1.setMinimumSize(new Dimension(203, 17));
		jPanel1.setPreferredSize(new Dimension(203, 30));
		jPanel1.setToolTipText("");
		sendreceivTxt.setBackground(Color.cyan);
		sendreceivTxt.setDebugGraphicsOptions(0);
		sendreceivTxt.setMinimumSize(new Dimension(203, 17));
		sendreceivTxt.setPreferredSize(new Dimension(203, 17));
		sendreceivTxt.setCaretColor(Color.blue);
		sendreceivTxt.setSelectionStart(29);
		sendreceivTxt.setText(res.getString("reception_et_envoi_de"));
		seterIdentity.setForeground(Color.green);
		seterIdentity.setMaximumSize(new Dimension(327, 327));
		seterIdentity.setOpaque(false);
		seterIdentity.setPreferredSize(new Dimension(451, 394));
		seterIdentity.setRequestFocusEnabled(true);
		seterConnection.setPreferredSize(new Dimension(451, 394));

		seterIdentity.setToolTipText("");
		seterIdentity.addMouseMotionListener(new CadrePr_seterIdentity_mouseMotionAdapter(this));
		seterIdentity.addMouseListener(new CadrePr_seterIdentity_mouseAdapter(this));
		seterConnection.setToolTipText("");

		identification.setBackground(new Color(214, 180, 28));
		identification.setPreferredSize(new Dimension(921, 195));
		identification.setToolTipText("");
		Quiter.setForeground(Color.black);
		Quiter.setOpaque(false);
		Quiter.setToolTipText("");
		Quiter.setActionCommand(res.getString("Quiter"));
		Quiter.setFocusPainted(true);
		Quiter.setText(res.getString("Quiter_"));
		Quiter.addActionListener(new CadrePr_Quiter_actionAdapter(this));

		ObservateurBt.setOpaque(false);
		ObservateurBt.setPreferredSize(new Dimension(103, 23));
		ObservateurBt.setToolTipText("");
		ObservateurBt.setActionCommand(res.getString("ObservateurBt"));
		ObservateurBt.setText(res.getString("Observateur"));
		ObservateurBt.addActionListener(new CadrePr_ObservateurBt_actionAdapter(this));
		jLabel1.setToolTipText("");
		jLabel1.setText(" ________________");
		// connectiontab.setEnabled(false);
		connectiontab.setRequestFocusEnabled(true);
		connectiontab.setToolTipText("");
		logo.addMouseListener(new CadrePr_logo_mouseAdapter(this));
		jTabbedPane1.addMouseMotionListener(new CadrePr_jTabbedPane1_mouseMotionAdapter(this));
		jTabbedPane1.addMouseListener(new CadrePr_jTabbedPane1_mouseAdapter(this));
		Game.addMouseMotionListener(new CadrePr_Game_mouseMotionAdapter(this));
		Game.addMouseListener(new CadrePr_Game_mouseAdapter(this));
		QuiteretObserverBox.add(Quiter);
		QuiteretObserverBox.add(jLabel1, null);
		QuiteretObserverBox.add(ObservateurBt, null);
		identification.add(jButton1, null);

		Aide.setToolTipText("");
		Aide.setActionCommand(res.getString("Aide"));
		Aide.setText(res.getString("Aide"));
		Aide.addActionListener(new CadrePr_Aide_actionAdapter(this));
		Apropos_.setToolTipText("");
		Apropos_.setActionCommand(res.getString("Apropos_"));
		Apropos_.setText(res.getString("Apropos1"));
		Apropos_.addActionListener(new CadrePr_Apropos__actionAdapter(this));
		jButton2.setText(res.getString("jButton2"));
		Sounds.setToolTipText("");
		Sounds.setText(res.getString("Sounds"));
		Sounds.addActionListener(new CadrePr_Sounds_actionAdapter(this));
		muted.setBackground(new Color(214, 180, 28));
		muted.setToolTipText(res.getString("clic_to_stop_or_run"));
		muted.setActionCommand(res.getString("muted"));
		muted.setIcon(gomusic);
		muted.setPressedIcon(stopmus);
		muted.setSelected(false);
		muted.setSelectedIcon(stopmus);
		muted.setText("");
		muted.addMouseListener(new CadrePr_muted_mouseAdapter(this));
		muted.setPreferredSize(new Dimension(40, 20));

		captureC.setPreferredSize(new Dimension(40, 20));
		captureC.setBackground(new Color(214, 180, 28));
		captureC.setToolTipText(res.getString("clic_to_strat"));
		captureC.setActionCommand(res.getString("captureC"));
		captureC.setDisabledIcon(nocap);
		captureC.setDisabledSelectedIcon(nocapup);
		captureC.setIcon(nocap);
		captureC.setPressedIcon(image4);
		captureC.setRolloverEnabled(true);
		captureC.setSelected(false);
		captureC.setSelectedIcon(image4);
		captureC.setText("");
		captureC.addMouseListener(new CadrePr_captureC_mouseAdapter(this));
		Observateur.setActionCommand(res.getString("Observateur"));
		Observateur.setText(res.getString("Observateur"));
		observateur_log.setActionCommand(res.getString("observateur_log"));
		observateur_log.setText(res.getString("observateur_log"));
		observateur_log.addActionListener(new CadrePr_observateur_log_actionAdapter(this));
		jTabbedPane1.setBackground(new Color(214, 180, 28));
		connectiontab.setBackground(new Color(214, 180, 28));
		Game.setBackground(Color.orange);
		jToolBar.setBackground(Color.orange);
		Alluser.setBackground(new Color(214, 180, 28));
		logo.setBackground(new Color(214, 180, 28));
		jMenuBar1.setBackground(new Color(214, 180, 28));
		jButton1.setVisible(false);
		jButton1.setText(res.getString("jButton1"));
		jButton1.addActionListener(new CadrePr_jButton1_actionAdapter(this));

		Replay.setVisible(false);
		Replay.setOpaque(false);
		Replay.setActionCommand(res.getString("Replay"));
		Replay.setDisabledIcon(null);
		Replay.setSelectedIcon(null);
		Replay.setText(res.getString("Replay"));
		Replay.addActionListener(new CadrePr_Replay_actionAdapter(this));
		jMenuConn.add(ClientOnPop);
		jMenuConn.add(ServerOnPop);
		ActionPopupMenu1.add(jMenuConn);
		ActionPopupMenu1.setInvoker(this.connectmi);

		myJImage.setPreferredSize(new Dimension(250, 340));
		start.setBorder(border7);
		start.setPreferredSize(new Dimension(171, 20));
		start.setToolTipText("");
		start.setMargin(new Insets(2, 14, 2, 14));
		start.setText(res.getString("start"));
		start.addActionListener(new CadrePr_start_actionAdapter(this));
		Alluser.setPreferredSize(new Dimension(120, 15));
		// Connection.setBackground(Color.orange);
		Connection.setAlignmentY((float) 0.5);
		connectmi.setActionCommand(res.getString("connectme"));
		connectmi.setText(res.getString("connectme"));
		connectmi.addActionListener(new CadrePr_connectmi_actionAdapter(this));
		jMenuFile.add(chatbar);
		jMenuFile.add(jMenuFileExit);
		jMenuHelp.add(jMenuHelpAbout);
		jMenuHelp.add(helpMenu);
		jMenuBar1.add(jMenuFile);
		jMenuBar1.add(Observateur);
		jMenuBar1.add(jMenu4);
		jMenuBar1.add(jMenuHelp);

		// uncomment pour remaitre le menu
		// this.setJMenuBar(jMenuBar1);

		contentPane.add(jToolBar, BorderLayout.NORTH);
		contentPane.add(statusBar, BorderLayout.SOUTH);
		contentPane.add(jTabbedPane1, BorderLayout.CENTER);

		GameN1.add(rPlateaudejeuL, null);
		GameN1.add(rPlateaudejeuR1, null);
		GameN1.add(rPlateaudejeuR2, null);
		GameN1.add(rPlateaudejeuR3, null);
		GameN1.add(jPanel1, null);
		GameN1.setBackground(new Color(214, 180, 28));

		myHBox.add(muted, null);
		myHBox.add(Replay, null);
		myHBox.add(captureC, null);
		myHBox.add(tTimeur, null);
		myHBox.add(sendreceivTxt, null);
		myHBox.add(Alluser, null);
		Game.add(identification, res.getString("identification"));

		GameN1.add(myHBox, null);

		identification.add(seterIdentity, res.getString("set_infos"));

		identification.add(QuiteretObserverBox, null);

		connectiontab.add(seterConnection, res.getString("set_infos"));

		Game.add(connectiontab, res.getString("connection"));
		Game.add(GameN1, res.getString("Game_Number_one"));

		jTabbedPane1.add(Game, res.getString("Game"));
		jTabbedPane1.add(Connection, res.getString("chat"));
		Connection.add(jPanel4, res.getString("Communication"));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		scrTread.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
		scrTwrite.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
		Tread.add(scrTread);
		Twrite.add(scrTwrite);
		jPanel4.add(Tread, null);
		jPanel4.add(Twrite, null);
		jPanel4.add(Sendbt, null);

		jTabbedPane1.add(Apropos, res.getString("Aide"));
		Apropos.add(AproposAuteurs, res.getString("Apropos_des_auteurs"));
		// Apropos.add(logo, resource.getString("Auteurs")+" logo");
		Apropos.add(logo, res.getString("NerGame_version_1_0"));
		// logo.add(Sounds, null);

		jPanel5.add(myJImage, null);
		jPanel5.add(myBox1, null);
		myBox1.add(connectmi, null);
		myBox1.add(Aide, null);
		myBox1.add(jButton2, null);
		myBox1.add(Apropos_, null);
		myBox1.add(rotation, null);
		myBox1.add(start, null);
		ConnPopMenu.add(ConnPop);
		ConnPopMenu.addSeparator();
		ConnPopMenu.add(Sender);
		Sender.add(TxtMsg);
		Sender.add(PrInfosSender);
		Sender.add(AllPersonSender);
		Sender.add(InitGameSender);
		Observateur.add(observateur_log);

		gestionDesAffichage1 = new superviseur(this, this.sendreceivTxt, this.Twrite, this.Alluser);
		AproposAuteurs.setText(resource.getString("Auteurs"));

// playCircularText( );

	}

	// Operation Fichier | Quitter effectuee
	public void jMenuFileExit_actionPerformed(ActionEvent e) {
		System.out.print(res.getString("bye_bye"));
		System.exit(0);
	}

	// Operation Aide | A propos effectuee
	public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
		showDialgBox(new CadrePr_AboutBox(this), true);
	}

	/**
	 * affichage d'une nouvelle Frame
	 *
	 * @param table JFrame
	 * @return int
	 */
	public int showTable(JFrame table) {

		Dimension dlgSize = table.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		table.setLocation(((frmSize.width - dlgSize.width) / 2) + loc.x,
				((frmSize.height) / 2 + (frmSize.height - dlgSize.height) / 2) + loc.y);
		// table.setLocation(50,50);
		// table.setSize(new Dimension(400,400));

		table.pack();
		table.show();
		return 0;
	}

	// Supplante, ainsi nous pouvons sortir quand la fenetre est fermee
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			jMenuFileExit_actionPerformed(null);
		}
	}

	void chatbar_actionPerformed(ActionEvent e) {

	}

	void rotation_actionPerformed(ActionEvent e) {
		this.logo.rotate();
		JOptionPane.showMessageDialog(this, res.getString("You_are_") + persoID.toString() + "\n"
				+ res.getString("Connection_to_") + ServerName + "\n" + res.getString("port_") + ServerPort + "\n"

		);

	}

	void rPlateaudejeuL_mouseClicked(MouseEvent e) {

		// est il possible de jouer
		if (rPlateaudejeuL.SourisSurPlateau(e)) {
			setContreQui(rPlateaudejeuL.bBatailleNavale.getJoueur());
			if (this.gestionDesAffichage1.canRestartLape()) {
				if (!rPlateaudejeuL.pointDejaSelectione(e, getContreQui())) {

					if (!rPlateaudejeuL.isDisable()) {
						this.gestionDesAffichage1.setRestartLape(false);
						// moveit(1);
						tTimeur.bye();
						this.gestionDesAffichage1.setsendCoup(getQui(), getContreQui(),
								rPlateaudejeuR1.bBatailleNavale.Souris(e.getX(), e.getY()));
						rPlateaudejeuL.mouseClicked(e);
						musicAmbianceSelector(true, 1);

						// moveit(1,0,0,0);
						// new Mover(this,1,0,0,0);
					} else {
						musicAmbianceSelector(true, 2);
						System.out.println(MsgForLocalUser[0]);

						moveit(0, 1, 0, 0);
					}

				} else {
					musicAmbianceSelector(true, 3);
					System.out.println(MsgForLocalUser[2]);
					moveit(0, 1, 0, 0);
				}

				repaint();
			} else {
				musicAmbianceSelector(true, 2);
				moveit(0, 1, 0, 0);
				System.out.println(MsgForLocalUser[1]);
			}
		}

	}

	void rPlateaudejeuR1_mouseClicked(MouseEvent e) {

		// est il possible de jouer ?
		if (rPlateaudejeuR1.SourisSurPlateau(e)) {
			setContreQui(rPlateaudejeuR1.bBatailleNavale.getJoueur());

			if (this.gestionDesAffichage1.canRestartLape()) {
				if (!rPlateaudejeuR1.pointDejaSelectione(e, getContreQui())) {

					if (!rPlateaudejeuR1.isDisable()) {

						this.gestionDesAffichage1.setRestartLape(false);

						tTimeur.bye();
						this.gestionDesAffichage1.setsendCoup(getQui(), getContreQui(),
								rPlateaudejeuR1.bBatailleNavale.Souris(e.getX(), e.getY()));
						rPlateaudejeuR1.mouseClicked(e);
						musicAmbianceSelector(true, 1);
						// moveit(1,0,0,0);
						// new Mover(this,1,0,0,0);
					} else {
						musicAmbianceSelector(true, 2);
						System.out.println(MsgForLocalUser[0]);
						new Mover(this, 0, 1, 0, 0);
					}

				} else {
					musicAmbianceSelector(true, 3);
					System.out.println(MsgForLocalUser[2]);
					new Mover(this, 0, 1, 0, 0);
				}

				repaint();
			} else {
				musicAmbianceSelector(true, 2);
				System.out.println(MsgForLocalUser[1]);
				new Mover(this, 0, 1, 0, 0);
			}

		}

	}

	void rPlateaudejeuR2_mouseClicked(MouseEvent e) {
		// est il possible de jouer
		if (rPlateaudejeuR2.SourisSurPlateau(e)) {
			setContreQui(rPlateaudejeuR2.bBatailleNavale.getJoueur());

			if (this.gestionDesAffichage1.canRestartLape()) {
				if (!rPlateaudejeuR2.pointDejaSelectione(e, getContreQui())) {

					if (!rPlateaudejeuR2.isDisable()) {
						this.gestionDesAffichage1.setRestartLape(false);
						// moveit(1);
						tTimeur.bye();
						this.gestionDesAffichage1.setsendCoup(getQui(), getContreQui(),
								rPlateaudejeuR1.bBatailleNavale.Souris(e.getX(), e.getY()));
						rPlateaudejeuR2.mouseClicked(e);
						musicAmbianceSelector(true, 1);
						// moveit(1,0,0,0);
//  new Mover(this,1,0,0,0);
					} else {
						musicAmbianceSelector(true, 2);
						System.out.println(MsgForLocalUser[0]);
						new Mover(this, 0, 1, 0, 0);
					}

				} else {
					musicAmbianceSelector(true, 3);
					System.out.println(MsgForLocalUser[2]);
					new Mover(this, 0, 1, 0, 0);
				}

				repaint();

			} else {
				musicAmbianceSelector(true, 2);
				System.out.println(MsgForLocalUser[1]);
				new Mover(this, 0, 1, 0, 0);
			}

		}

	}

	void rPlateaudejeuR3_mouseClicked(MouseEvent e) {
		// WavLocaliser. playMidimusic03();
		// est il possible de jouer
		if (rPlateaudejeuR3.SourisSurPlateau(e)) {
			setContreQui(rPlateaudejeuR3.bBatailleNavale.getJoueur());
			if (this.gestionDesAffichage1.canRestartLape()) {
				if (!rPlateaudejeuR3.pointDejaSelectione(e, getContreQui())) {

					if (!rPlateaudejeuR3.isDisable()) {
						this.gestionDesAffichage1.setRestartLape(false);
						// moveit(1);
						tTimeur.bye();
						this.gestionDesAffichage1.setsendCoup(getQui(), getContreQui(),
								rPlateaudejeuR1.bBatailleNavale.Souris(e.getX(), e.getY()));
						rPlateaudejeuR3.mouseClicked(e);
						musicAmbianceSelector(true, 1);
						// moveit(1,0,0,0);
						// new Mover(this,1,0,0,0);
					} else {
						musicAmbianceSelector(true, 2);
						System.out.println(MsgForLocalUser[0]);
						new Mover(this, 0, 1, 0, 0);
					}

				} else {
					musicAmbianceSelector(true, 3);
					System.out.println(MsgForLocalUser[2]);
					new Mover(this, 0, 1, 0, 0);
				}

				repaint();

			} else {
				musicAmbianceSelector(true, 2);
				System.out.println(MsgForLocalUser[1]);
				new Mover(this, 0, 1, 0, 0);
			}

		}

	}

	/**
	 * moveit
	 *
	 *
	 *
	 * @param yes  int
	 * @param no   int
	 * @param move int
	 * @param pli  int
	 */
	public void moveit(int yes, int no, int move, int pli) {
		new Mover(this, yes, no, move, pli);
	}

	void Game_keyPressed(KeyEvent e) {
		PlateaudejeuRot.rotate();
	}

	public void showHelp() {

	}

	void this_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == java.awt.event.KeyEvent.VK_F5) {

			// anythink
		}
	}

	void start_actionPerformed(ActionEvent e) {

	}

	String[] MsgForLocalUser = { res.getString("vous_n_avez_pas_le"), res.getString("le_jeu_n_a_pas_encor"),
			res.getString("la_case_est_d_ja_s_l"), "", "", "" };

	byte[] dec = null;
	JComboBox Alluser = new JComboBox();
	JButton connectmi = new JButton();
	JButton Sendbt = new JButton();
	Border border8;
	JPopupMenu ConnPopMenu = new JPopupMenu();
	JMenuItem ConnPop = new JMenuItem();
	JMenu Sender = new JMenu();
	JMenuItem PrInfosSender = new JMenuItem();
	JMenuItem AllPersonSender = new JMenuItem();
	JMenuItem InitGameSender = new JMenuItem();
	JMenuItem TxtMsg = new JMenuItem();
	JPanel jPanel1 = new JPanel();
	JTextArea sendreceivTxt = new JTextArea();
	JButton Quiter = new JButton();
	JButton Aide = new JButton();
	JButton Apropos_ = new JButton();
	Box myBox1;
	Box myBox2;
	JButton jButton2 = new JButton();
	JButton Sounds = new JButton();
	JCheckBox muted = new JCheckBox();
	JCheckBox captureC = new JCheckBox();
	JMenuItem observateur_log = new JMenuItem();

	void playCircularText() {
		java.util.Timer timer = new java.util.Timer();
		int delay = 3000; // delay for 5 sec.
		int period = 300; // repeat every sec.
		TimerTask tTimerTaskMi = new TimerTaskMi((JFrame) this);
		timer.scheduleAtFixedRate(tTimerTaskMi, delay, period);

	}

	void connectmi_actionPerformed(ActionEvent e) {

	}

	void connectmi_mouseClicked(MouseEvent e) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = e.getX();
		int y = e.getY();
		ActionPopupMenu1.show(this.connectmi, x, y);

	}

	void ClientOnPop_actionPerformed(ActionEvent e) {
		this.gestionDesAffichage1.setPersoID(this.persoID);
		this.gestionDesAffichage1.ClientOn(this.ServerPort, this.ServerName);

	}

	void ServerOnPop_actionPerformed(ActionEvent e) {
		int nembrJoueur = 4;
		this.gestionDesAffichage1.setPersoID(this.persoID);
		gestionDesAffichage1.ServerOn(this.ServerPort, nembrJoueur);

	}

	void Sendbt_actionPerformed(ActionEvent e) {

	}

	void Sendbt_mouseClicked(MouseEvent e) {
		/*
		 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); int x =
		 * e.getX(); int y = e.getY();
		 * 
		 * ConnPopMenu.show(this.Sendbt, x, y);
		 */
		JOptionPane.showConfirmDialog(this, res.getString("not_yet_implemented"), res.getString("Chat"),
				JOptionPane.CANCEL_OPTION);
	}

	void TxtMsg_actionPerformed(ActionEvent e) {
		this.gestionDesAffichage1.SendTxtTo(this.Alluser.getSelectedIndex());

		repaint();
	}

	void InitGameSender_actionPerformed(ActionEvent e) {
		this.gestionDesAffichage1.TableDeLaSalleinitMaxGame();
		repaint();
	}

	void start_mouseClicked(MouseEvent e) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = e.getX();
		int y = e.getY();

		ConnPopMenu.show(this.start, x - 50, y - 50);

	}

	void GameN1_keyPressed(KeyEvent e) {
		this.Twrite.setText(this.Twrite.getText() + e.getKeyChar());
		this.gestionDesAffichage1.SendTxtTo(this.Alluser.getSelectedIndex());

		repaint();

	}

	void this_mouseClicked(MouseEvent e) {

	}

	/**
	 * isediamconnected
	 *
	 * @param connectedr boolean
	 */
	public void isediamconnected(boolean connectedr) {

		if (connectedr) {
			Game.setSelectedIndex(2);
		} else {
			Game.setSelectedIndex(0);
			seterIdentity.ServerName
					.setText(seterIdentity.ServerName.getText() + res.getString("ce_nom_de_serveur_ou"));
		}

	}

	void Quiiter_mouseClicked(MouseEvent e) {
		System.exit(0);
	}

	void Quiter_actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	void Aide_actionPerformed(ActionEvent e) {
		showHelp();
	}

	/**
	 * showDialgBox
	 * 
	 * @param dialogbox Dialog
	 * @param modal     boolean
	 */
	public void showDialgBox(Dialog dialogbox, boolean modal) {

		Dimension dlgSize = dialogbox.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dialogbox.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dialogbox.setModal(modal);
		dialogbox.pack();
		dialogbox.show();

	}

	void Apropos__actionPerformed(ActionEvent e) {

		showDialgBox(new CadrePr_AboutBox(this), true);
		/*
		 * CadrePr_AboutBox dlg = new CadrePr_AboutBox(this); Dimension dlgSize =
		 * dlg.getPreferredSize(); Dimension frmSize = getSize(); Point loc =
		 * getLocation(); dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
		 * (frmSize.height - dlgSize.height) / 2 + loc.y); dlg.setModal(true);
		 * dlg.pack(); dlg.show();
		 */
	}

	void Sounds_actionPerformed(ActionEvent e) {

	}

	void muted_mouseClicked(MouseEvent e) {
		WavLocaliser.setStop(muted.isSelected());
		// musicAmbianceSelector(false, 1);
	}

	void captureC_mouseClicked(MouseEvent e) {
		this.tTimeur.setGocap(captureC.isSelected());
		/**
		 * if(this.tTimeur.isGocap()){ this.tTimeur.setGocap(false);
		 * btncaptures.setIcon(nocap); }else{ this.tTimeur.setGocap(true);
		 * btncaptures.setIcon(image4);
		 * 
		 * }
		 */
	}

	void observateur_log_actionPerformed(ActionEvent e) {
		// this.gestionDesAffichage1.setFileNameLog(this.persoID.getLogin() + ".log");
		showDialgBox(this.gestionDesAffichage1.getDialogBoxLog(), false);

	}

	/**
	 * setServeur
	 *
	 * @param b boolean
	 */
	public void setasServeur(boolean b) {
		this.gestionDesAffichage1.setasServeur(b);
	}

	/**
	 * reinitialistationDuJeu reinitialisation de l'affichage
	 */
	public void reinitialistationDuJeu() {
		for (int i = 0; i < 4; i++) {
			initAffichage(getPlateau(i));
		}

	}

	static int musiAmb = 4;
	JButton jButton1 = new JButton();
	JButton Replay = new JButton();

	public void musicAmbianceSelector(boolean court, int indice) {

		if (!court) {
			switch (musiAmb) {
			case 1:
				WavLocaliser.playMidimusic02();
				musiAmb = 2;
				break;
			case 2:
				WavLocaliser.playMidimusic01();
				musiAmb = 3;
				break;
			case 3:
				WavLocaliser.playMidimusic03();
				musiAmb = 4;
				break;
			case 4:
				WavLocaliser.playMidiVictory();
				musiAmb = 1;
				break;
			}

//    WavLocaliser.setMuet(true);
			// WavLocaliser.setMuet(false);

		} else {
			switch (indice) {
			case 1:
				WavLocaliser.playClipDropBlocks();

				break;
			case 2:
				WavLocaliser.playClipNoAcces();

				break;
			case 3:
				WavLocaliser.playClipdejatouche();

				break;
			case 4:
				WavLocaliser.playClipStage1();

				break;

			case 5:
				WavLocaliser.playClipInitGame();

				break;
			case 6:
				WavLocaliser.playClipStage2();

				break;
			case 7:
				WavLocaliser.playClipCloche();

				break;
			case 8:

				WavLocaliser.playClipExplodedBlocks();

				break;
			case 9:

				WavLocaliser.playClipGameOver();

				break;

			}

		}

	}

	// init affichage par plateau
	public void initAffichage(Plateaudejeu plj) {
		int nbvie = 18;
		plj.bBatailleNavale.reinitialisationdujeu();
		plj.setTextGameState("");
		plj.setNbrDevieEncour(nbvie);

	}

	/**
	 * getPlateau
	 *
	 *
	 * @param x int
	 * @return Plateaudejeu
	 */
	public Plateaudejeu getPlateau(int x) {
		switch (x) {
		case 0:
			return rPlateaudejeuL;
		case 1:
			return rPlateaudejeuR1;
		case 2:
			return rPlateaudejeuR2;
		case 3:
			return rPlateaudejeuR3;
		default:
			return null;

		}

	}

	/**
	 * setintstart
	 *
	 * @param Navires TPoint[][]
	 * @param b       boolean
	 */
	public void setintstart(TPoint[][] Navires, boolean b) {

		for (int i = 0; i < Navires.length; i++) {
			this.getPlateau(i).bBatailleNavale.setintstart(Navires, b);
		}

	}

	void jButton1_actionPerformed(ActionEvent e) {

		setintstart(gestionDesAffichage1.getNavires(), true);

	}

	/**
	 * showAllNavires
	 *
	 * @param Navires TPoint[][]
	 */
	public void showAllNavires(TPoint[][] Navires) {
		int who = 0;

		try {
			who = gestionDesAffichage1.whereIam();

		} catch (Exception ex) {
		} finally {
			for (int i = 0; i < Navires.length; i++) {
				if (i == who) {
					this.getPlateau(i).bBatailleNavale.setintstart(Navires[i], true);
					this.getPlateau(i).setDisable(true);
				} else {
					this.getPlateau(i).bBatailleNavale.setintstart(Navires[i], false);
					this.getPlateau(i).setDisable(false);
				}
			}

			repaint();

		}
//       gestionDesAffichage1.addLogComments("Navires du joueur 1 :", Navires[who]);
	}

	/**
	 * showAllNavires
	 *
	 * @param Navires TPoint[][]
	 */
	public void showAllNavires2(TPoint[][] Navires) {
		int who = 0;

		for (int i = 0; i < Navires.length; i++) {
			if (i == who) {
				this.getPlateau(i).bBatailleNavale.setintstart(Navires[i], true);

			} else {
				this.getPlateau(i).bBatailleNavale.setintstart(Navires[i], false);

			}
		}

		try {
			who = gestionDesAffichage1.whereIam();

		} catch (Exception ex) {
		} finally {
			switch (who) {
			case 0:
				rPlateaudejeuL.bBatailleNavale.setintstart(Navires[0], true);
				rPlateaudejeuL.setDisable(true);
				rPlateaudejeuR1.setDisable(false);
				rPlateaudejeuR2.setDisable(false);
				rPlateaudejeuR3.setDisable(false);
				break;
			case 1:
				rPlateaudejeuR1.bBatailleNavale.setintstart(Navires[1], true);
				rPlateaudejeuL.setDisable(false);
				rPlateaudejeuR1.setDisable(true);
				rPlateaudejeuR2.setDisable(false);
				rPlateaudejeuR3.setDisable(false);
				break;
			case 2:
				rPlateaudejeuR2.bBatailleNavale.setintstart(Navires[2], true);
				rPlateaudejeuL.setDisable(false);
				rPlateaudejeuR1.setDisable(false);
				rPlateaudejeuR2.setDisable(true);
				rPlateaudejeuR3.setDisable(false);
				break;
			case 3:
				rPlateaudejeuR3.bBatailleNavale.setintstart(Navires[3], true);
				rPlateaudejeuL.setDisable(false);
				rPlateaudejeuR1.setDisable(false);
				rPlateaudejeuR2.setDisable(false);
				rPlateaudejeuR3.setDisable(true);
				break;
			}
			repaint();

		}
		/**
		 * selon e numeros du joueur on affiche celle qui lui convien
		 */
		/*
		 * for(int i=0;i<Navires.length;i++){
		 * this.getPlateau(i).bBatailleNavale.setintstart(Navires[i], true); }
		 */
		System.out.println("\n\t_____");
		System.out.println(res.getString("nombre_de_vecteur") + Navires.length);
		System.out.println(res.getString("nombre_de_point_par") + Navires[0].length);
		for (int i = 0; i < Navires.length; i++) {
			for (int x = 0; x < Navires[i].length; x++) {

				// observlog.addLogComments(Navires[i][x],"\n\t_____");

				System.out.print(Navires[i][x]);
			}
			System.out.println("\n\t_____");
		}
		System.out.println(res.getString("CadrePr") + who + "\n" + res.getString("CadrePr1")
				+ gestionDesAffichage1.whoIam().getNum());

	}

	static int firstround = 0;
	JButton ObservateurBt = new JButton();
	JLabel jLabel1 = new JLabel();

	public int reStartGame() {
		if (firstround != 0) {
			this.Replay.setVisible(true);
		} else {
			this.Replay.setVisible(false);
		}
		firstround = firstround + 1;

		return firstround;
	}

	void Replay_actionPerformed(ActionEvent e) {
		int who = gestionDesAffichage1.whereIam();
		System.out.println(res.getString("CadrePR") + who);
		try {
			this.getPlateau(this.getQui()).bBatailleNavale.setintstart(gestionDesAffichage1.getNavires()[1], true);
			System.out.println(res.getString("CadrePR1") + getQui());
			for (int x = 0; x < gestionDesAffichage1.getNavires()[1].length; x++)
				System.out.print("/" + gestionDesAffichage1.getNavires()[1][x]);
		} catch (Exception ex) {
		}
		/*
		 * int i =gestionDesAffichage1.whereIam();
		 * this.getPlateau(i).bBatailleNavale.steStart(true);
		 * this.getPlateau(i).bBatailleNavale.setintstart(gestionDesAffichage1.
		 * getNavires()[i],true); } finally{ // this.Replay.setVisible(false); }
		 */
	}

	/**
	 * bye
	 */
	public void bye() {
		// une confirmation de l'arret du cont a revour
		tTimeur.bye();
	}

	/**
	 * getTimerValeur
	 *
	 * @return int
	 */
	public int getTimerValeur() {
		return tTimeur.getValeur();
	}

	/**
	 * reSatrtTimer
	 */
	public void reSatrtTimer() {
		this.tTimeur.reSatrt();
	}

	/**
	 * setTimeurGestionnair
	 *
	 * @param gestionnair Gestionnair
	 */
	public void setTimeurGestionnair(Gestionnaire gestionnair) {
		this.tTimeur.setGestionnaire(gestionnair);
	}

	void ObservateurBt_actionPerformed(ActionEvent e) {
		// this.gestionDesAffichage1.setFileNameLog(this.persoID.getLogin() + ".log");
		showDialgBox(this.gestionDesAffichage1.getDialogBoxLog(), false);

	}

	void logo_mouseClicked(MouseEvent e) {
		this.logo.rotate();
		musicAmbianceSelector(true, 8);
	}

	void jTabbedPane1_mouseDragged(MouseEvent e) {
		deplacer.drag(e);
	}

	void jTabbedPane1_mousePressed(MouseEvent e) {
		deplacer.pressed(e);
	}

	void Game_mouseDragged(MouseEvent e) {
		deplacer.drag(e);
	}

	void Game_mousePressed(MouseEvent e) {
		deplacer.pressed(e);
	}

	void seterIdentity_mousePressed(MouseEvent e) {
		deplacer.pressed(e);
	}

	void seterIdentity_mouseDragged(MouseEvent e) {
		deplacer.drag(e);

	}

}

class CadrePr_jMenuFileExit_ActionAdapter implements ActionListener {
	CadrePr adaptee;

	CadrePr_jMenuFileExit_ActionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuFileExit_actionPerformed(e);
	}
}

class CadrePr_jMenuHelpAbout_ActionAdapter implements ActionListener {
	CadrePr adaptee;

	CadrePr_jMenuHelpAbout_ActionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuHelpAbout_actionPerformed(e);
	}
}

class CadrePr_chatbar_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_chatbar_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.chatbar_actionPerformed(e);
	}
}

class CadrePr_rotation_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_rotation_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.rotation_actionPerformed(e);
	}
}

class CadrePr_Game_keyAdapter extends java.awt.event.KeyAdapter {
	CadrePr adaptee;

	CadrePr_Game_keyAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.Game_keyPressed(e);
	}
}

class CadrePr_rPlateaudejeuR1_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_rPlateaudejeuR1_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.rPlateaudejeuR1_mouseClicked(e);
	}
}

class CadrePr_this_keyAdapter extends java.awt.event.KeyAdapter {
	CadrePr adaptee;

	CadrePr_this_keyAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.this_keyPressed(e);
	}
}

class CadrePr_start_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_start_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.start_actionPerformed(e);
	}
}

class CadrePr_connectmi_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_connectmi_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.connectmi_actionPerformed(e);
	}
}

class CadrePr_connectmi_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_connectmi_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.connectmi_mouseClicked(e);
	}
}

class CadrePr_ClientOnPop_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_ClientOnPop_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.ClientOnPop_actionPerformed(e);
	}
}

class CadrePr_ServerOnPop_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_ServerOnPop_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.ServerOnPop_actionPerformed(e);
	}
}

class CadrePr_Sendbt_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_Sendbt_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.Sendbt_actionPerformed(e);
	}
}

class CadrePr_Sendbt_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_Sendbt_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.Sendbt_mouseClicked(e);
	}
}

class CadrePr_TxtMsg_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_TxtMsg_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.TxtMsg_actionPerformed(e);
	}
}

class CadrePr_InitGameSender_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_InitGameSender_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.InitGameSender_actionPerformed(e);
	}
}

class CadrePr_start_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_start_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.start_mouseClicked(e);
	}
}

class CadrePr_GameN1_keyAdapter extends java.awt.event.KeyAdapter {
	CadrePr adaptee;

	CadrePr_GameN1_keyAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.GameN1_keyPressed(e);
	}
}

class CadrePr_rPlateaudejeuR2_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_rPlateaudejeuR2_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.rPlateaudejeuR2_mouseClicked(e);
	}
}

class CadrePr_rPlateaudejeuR3_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_rPlateaudejeuR3_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.rPlateaudejeuR3_mouseClicked(e);
	}
}

class CadrePr_this_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_this_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.this_mouseClicked(e);
	}
}

class CadrePr_Quiter_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_Quiter_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.Quiter_actionPerformed(e);
	}
}

class CadrePr_Aide_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_Aide_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.Aide_actionPerformed(e);
	}
}

class CadrePr_Apropos__actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_Apropos__actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.Apropos__actionPerformed(e);
	}
}

class CadrePr_Sounds_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_Sounds_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.Sounds_actionPerformed(e);
	}
}

class CadrePr_muted_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_muted_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.muted_mouseClicked(e);
	}
}

class CadrePr_captureC_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_captureC_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.captureC_mouseClicked(e);
	}
}

class CadrePr_rPlateaudejeuL_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_rPlateaudejeuL_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.rPlateaudejeuL_mouseClicked(e);
	}
}

class CadrePr_observateur_log_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_observateur_log_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.observateur_log_actionPerformed(e);
	}
}

class CadrePr_jButton1_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_jButton1_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class CadrePr_Replay_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_Replay_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.Replay_actionPerformed(e);
	}
}

class CadrePr_ObservateurBt_actionAdapter implements java.awt.event.ActionListener {
	CadrePr adaptee;

	CadrePr_ObservateurBt_actionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.ObservateurBt_actionPerformed(e);
	}
}

class CadrePr_logo_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_logo_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.logo_mouseClicked(e);
	}
}

class CadrePr_jTabbedPane1_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
	CadrePr adaptee;

	CadrePr_jTabbedPane1_mouseMotionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseDragged(MouseEvent e) {
		adaptee.jTabbedPane1_mouseDragged(e);
	}
}

class CadrePr_jTabbedPane1_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_jTabbedPane1_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		adaptee.jTabbedPane1_mousePressed(e);
	}
}

class CadrePr_Game_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
	CadrePr adaptee;

	CadrePr_Game_mouseMotionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseDragged(MouseEvent e) {
		adaptee.Game_mouseDragged(e);
	}
}

class CadrePr_Game_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_Game_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		adaptee.Game_mousePressed(e);
	}
}

class CadrePr_seterIdentity_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr adaptee;

	CadrePr_seterIdentity_mouseAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		adaptee.seterIdentity_mousePressed(e);
	}
}

class CadrePr_seterIdentity_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
	CadrePr adaptee;

	CadrePr_seterIdentity_mouseMotionAdapter(CadrePr adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseDragged(MouseEvent e) {
		adaptee.seterIdentity_mouseDragged(e);
	}
}
