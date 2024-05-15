package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Interface.interfaceJeux.RotatePanel;
import commun_rc.PersoInfos;
import observation.Mover;

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
 * Societe :
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class SeterIdentity extends JPanel implements Cloneable {

	static ResourceBundle res = ResourceBundle.getBundle("Interface.Res");
	PersoInfos infosJoueur;
	Box myBox = Box.createHorizontalBox();
	Box myBox1 = Box.createHorizontalBox();
	Box myBox2 = Box.createHorizontalBox();
	Box myBox3 = Box.createHorizontalBox();
	Box myBox4 = Box.createHorizontalBox();
	Box myBox5 = Box.createHorizontalBox();
	Box myBox6 = Box.createHorizontalBox();
	Box myBox7 = Box.createHorizontalBox();
	Box myBoxVert = Box.createVerticalBox();
	RotatePanel logo = new RotatePanel(res.getString("images_requin_gif"));
	CadrePr parent;
	Mover deplacer;
	String rulesTxt = res.getString("Bienvenue_sur_NetGame1") + res.getString("apr_s_identification1")
			+ res.getString("au_serveur_avec_votre1") + res.getString("quatri_me_joueur_vous1")
			+ res.getString("contre_qui_vous1") + res.getString("tentatives_de_tirs1") + res.getString("leur_tour_1")
			+ res.getString("_les_carr_s_rouges1") + res.getString("_les_carr_s_magentas");

	JTextField Prenom = new JTextField();
	JLabel jLabel4 = new JLabel();
	JButton connection = new JButton();
	JTextField Nom = new JTextField();
	JLabel jLabel1 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JTextField ServerPort = new JTextField();
	int defaultnum = -1;
	JTextField Login = new JTextField();
	JPanel jPanel1 = new JPanel();
	JLabel jLabel2 = new JLabel();
	JTextField ServerName = new JTextField();
	JButton asserver = new JButton();
	JTextField Mail = new JTextField();
	JButton accept = new JButton();
	JLabel ser = new JLabel();
	JLabel jLabel5 = new JLabel();
	JTextArea rules = new JTextArea();
	JLabel jLabel6 = new JLabel();
	JLabel jLabel7 = new JLabel();

	public void setInfosJoueur(PersoInfos infoJoueur) {
		Login.setText(infoJoueur.getLogin());
		Nom.setText(infoJoueur.getNom());
		Prenom.setText(infoJoueur.getPnom());
		Mail.setText(infoJoueur.getMail());

		this.infosJoueur = infoJoueur;
	}

	public PersoInfos getInfosJoueur() {
		return infosJoueur;
	}

	public SeterIdentity(CadrePr parent) {
		this.parent = parent;
		this.setInfosJoueur(this.parent.getPersoID());
		this.myBox.setVisible(false);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		deplacer = new Mover(parent);
	}

	public SeterIdentity(LayoutManager p0, boolean p1) {
		super(p0, p1);
	}

	public SeterIdentity(LayoutManager p0) {
		super(p0);
	}

	public SeterIdentity(boolean p0) {

		super(p0);
	}

	public static void main(String[] args) {
		CadrePr parent1 = new CadrePr();
		SeterIdentity seterIdentity1 = new SeterIdentity(parent1);
	}

	private void jbInit() throws Exception {

		// Prenom.setDebugGraphicsOptions(0);
		Prenom.setPreferredSize(new Dimension(290, 21));
		Prenom.setCaretPosition(3);
		Prenom.setText(res.getString("Pr_nom"));
		Prenom.setColumns(0);
		Prenom.setScrollOffset(0);
		jLabel4.setFont(new java.awt.Font(res.getString("Dialog"), 1, 11));
		jLabel4.setToolTipText("");
		jLabel4.setText(res.getString("port_1"));
		connection.setFont(new java.awt.Font(res.getString("Dialog"), 3, 10));
		connection.setMaximumSize(new Dimension(129, 23));
		connection.setOpaque(false);
		connection.setPreferredSize(new Dimension(129, 23));
		connection.setToolTipText("");
		connection.setActionCommand(res.getString("connection"));
		connection.setText(res.getString("connect_and_play"));
		connection.addActionListener(new SeterIdentity_connection_actionAdapter(this));

		Nom.setPreferredSize(new Dimension(290, 21));
		Nom.setRequestFocusEnabled(true);
		Nom.setToolTipText("");
		Nom.setText(res.getString("Nom"));
		Nom.setScrollOffset(0);
		jLabel1.setRequestFocusEnabled(true);
		jLabel1.setToolTipText("");
		jLabel1.setText(res.getString("Nom1"));
		jLabel3.setToolTipText("");
		jLabel3.setText(res.getString("Mail"));
		ServerPort.setPreferredSize(new Dimension(290, 21));
		ServerPort.setToolTipText("");
		ServerPort.setText("2004");
		ServerPort.setScrollOffset(1);
		Login.setAlignmentX((float) 0.5);
		Login.setPreferredSize(new Dimension(300, 21));
		Login.setToolTipText("");
		Login.setText(res.getString("login"));
		Login.setScrollOffset(0);
		jPanel1.setBackground(new Color(214, 180, 28));
		jPanel1.setFont(new java.awt.Font(res.getString("Arial"), 1, 11));
		jPanel1.setForeground(Color.pink);
		jPanel1.setDebugGraphicsOptions(0);
		jPanel1.setMinimumSize(new Dimension(404, 35));
		jPanel1.setOpaque(true);
		jPanel1.setPreferredSize(new Dimension(421, 394));
		jPanel1.setToolTipText("");
		jPanel1.addMouseListener(new SeterIdentity_jPanel1_mouseAdapter(this));
		jPanel1.addMouseMotionListener(new SeterIdentity_jPanel1_mouseMotionAdapter(this));
		jLabel2.setToolTipText("");
		jLabel2.setText(res.getString("Prenom"));
		ServerName.setOpaque(true);
		ServerName.setPreferredSize(new Dimension(290, 20));
		ServerName.setRequestFocusEnabled(true);
		ServerName.setToolTipText("");
		ServerName.setText(res.getString("younes"));
		asserver.setFont(new java.awt.Font(res.getString("Dialog"), 3, 11));
		asserver.setAlignmentX((float) 0.0);
		asserver.setOpaque(false);
		asserver.setPreferredSize(new Dimension(129, 23));
		asserver.setToolTipText("");
		asserver.setActionCommand(res.getString("start_serveur"));
		asserver.setHorizontalAlignment(SwingConstants.CENTER);
		asserver.setHorizontalTextPosition(SwingConstants.CENTER);
		asserver.setText(res.getString("start_as_serveur_"));
		asserver.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		asserver.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
		asserver.addActionListener(new SeterIdentity_asserver_actionAdapter(this));

		Mail.setPreferredSize(new Dimension(290, 21));
		Mail.setToolTipText("");
		Mail.setText(res.getString("Mail1"));
		Mail.setScrollOffset(0);
		accept.setAlignmentX((float) 0.0);
		accept.setMaximumSize(new Dimension(97, 21));
		accept.setOpaque(false);
		accept.setToolTipText("");
		accept.setBorderPainted(true);
		accept.setHorizontalAlignment(SwingConstants.LEFT);
		accept.setHorizontalTextPosition(SwingConstants.LEFT);

		accept.setMnemonic('0');
		accept.setText(res.getString("accepter"));
		accept.addActionListener(new SeterIdentity_accept_actionAdapter(this));

		ser.setFont(new java.awt.Font(res.getString("Dialog"), 1, 11));
		ser.setRequestFocusEnabled(true);
		ser.setToolTipText("");
		ser.setText(res.getString("server_name_"));
		jLabel5.setFont(new java.awt.Font(res.getString("Dialog"), 1, 11));
		jLabel5.setForeground(UIManager.getColor(res.getString("MenuBar_foreground")));
		jLabel5.setToolTipText("");
		jLabel5.setHorizontalAlignment(SwingConstants.LEADING);
		jLabel5.setIconTextGap(4);
		jLabel5.setText(res.getString("login_"));
		this.setBackground(new Color(214, 180, 28));
		this.setPreferredSize(new Dimension(421, 394));
		this.setToolTipText("");
		rules.setBackground(new Color(214, 180, 28));
		rules.setFont(new java.awt.Font(res.getString("Arial"), 1, 12));
		rules.setBorder(BorderFactory.createEtchedBorder());
		rules.setPreferredSize(new Dimension(7, 150));
		rules.setRequestFocusEnabled(false);
		rules.setToolTipText("");
		rules.setCaretPosition(0);
		rules.setDisabledTextColor(Color.white);
		rules.setSelectionEnd(1);
		rules.setLineWrap(true);
		rules.setText("8");
		rules.setRows(0);

		jLabel6.setToolTipText("");
		jLabel6.setVerifyInputWhenFocusTarget(true);
		jLabel6.setText("                    ");
		jLabel7.setFont(new java.awt.Font(res.getString("Abadi_MT_Condensed"), 0, 11));
		jLabel7.setText("           ");
		myBox.add(jLabel6, null);
		myBox.add(connection, null);
		myBox.add(jLabel7, null);
		myBox.add(asserver, null);

		myBox1.add(jLabel5, null);
		myBox1.add(Login, null);

		myBox2.add(jLabel1, null);
		myBox2.add(Nom, null);

		myBox3.add(jLabel2, null);
		myBox3.add(Prenom, null);

		myBox4.add(jLabel3, null);
		myBox4.add(Mail, null);

		myBox5.add(ser, null);
		myBox5.add(ServerName, null);

		myBox6.add(jLabel4, null);
		myBox6.add(ServerPort, null);
		myBox7.add(accept, null);
		myBoxVert.add(myBox1, null);
		myBoxVert.add(myBox2, null);

		myBoxVert.add(myBox3, null);
		myBoxVert.add(myBox4, null);
		myBoxVert.add(myBox5, null);
		myBoxVert.add(myBox6, null);
		myBoxVert.add(rules, null);
		myBoxVert.add(myBox, null);

		myBoxVert.add(myBox7, null);
		// myBoxVert.add(logo, null);
		jPanel1.add(myBoxVert, null);
		// jPanel1.add(accept, null);

		jPanel1.add(logo, null);
		this.add(jPanel1, null);

		rules.setText(rulesTxt);
	}

	void updateInfos(CadrePr frame_) {

		setInfosJoueur(new PersoInfos(defaultnum, Login.getText(), Nom.getText(), Prenom.getText(), Mail.getText()));
		frame_.setPersoID(getInfosJoueur());

		this.parent.ServerName = this.ServerName.getText();

		try {
			this.parent.ServerPort = Integer.parseInt(this.ServerPort.getText());
			this.parent.Game.setSelectedIndex(1);
		} catch (NumberFormatException ex) {
			this.parent.Game.setSelectedIndex(0);
			this.ServerPort.setText(res.getString("corrigez_le_numero_de"));
			parent.seterConnection.ServerPort.setText(res.getString("corrigez_le_numero_de"));
		}
		this.parent.setTitle(Login.getText() + "\\" + this.parent.ServerName + "\\" + this.parent.ServerPort);

	}

	void setasIdentity() {

	}

	public void setastoconnection() {
		setInfosJoueur(parent.getPersoID());
		this.accept.setVisible(false);
		this.myBox.setVisible(true);
		// this.parent.connectiontab.setVisible(true);
		// this.rules.setVisible(false);
	}

	void setasgame() {

	}

	void update() {
		updateInfos(this.parent);
		parent.seterConnection.setastoconnection();
		this.parent.seterConnection.ServerName.setText(this.parent.ServerName);
		this.parent.seterConnection.ServerPort.setText("" + this.parent.ServerPort);

	}

	void accept_actionPerformed(ActionEvent e) {
		update();
	}

	void connection_actionPerformed(ActionEvent e) {
		this.parent.ClientOnPop.doClick();
		this.parent.setasServeur(false);
	}

	void asserver_actionPerformed(ActionEvent e) {
		this.parent.ServerOnPop.doClick();
		this.parent.setasServeur(true);
	}

	void jPanel1_mouseDragged(MouseEvent e) {
		deplacer.drag(e);
	}

	void jPanel1_mousePressed(MouseEvent e) {
		deplacer.pressed(e);
	}

}

class SeterIdentity_accept_actionAdapter implements java.awt.event.ActionListener {
	SeterIdentity adaptee;

	SeterIdentity_accept_actionAdapter(SeterIdentity adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.accept_actionPerformed(e);
	}
}

class SeterIdentity_connection_actionAdapter implements java.awt.event.ActionListener {
	SeterIdentity adaptee;

	SeterIdentity_connection_actionAdapter(SeterIdentity adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.connection_actionPerformed(e);
	}
}

class SeterIdentity_asserver_actionAdapter implements java.awt.event.ActionListener {
	SeterIdentity adaptee;

	SeterIdentity_asserver_actionAdapter(SeterIdentity adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.asserver_actionPerformed(e);
	}
}

class SeterIdentity_jPanel1_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
	SeterIdentity adaptee;

	SeterIdentity_jPanel1_mouseMotionAdapter(SeterIdentity adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseDragged(MouseEvent e) {
		adaptee.jPanel1_mouseDragged(e);
	}
}

class SeterIdentity_jPanel1_mouseAdapter extends java.awt.event.MouseAdapter {
	SeterIdentity adaptee;

	SeterIdentity_jPanel1_mouseAdapter(SeterIdentity adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		adaptee.jPanel1_mousePressed(e);
	}
}
