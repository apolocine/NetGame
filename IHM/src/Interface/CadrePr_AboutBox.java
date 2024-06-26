package Interface;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <p>
 * Titre :
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Copyright : Copyright (c) 2004
 * </p>
 * <p>
 * Soci�t� :
 * </p>
 * 
 * @author non attribuable
 * @version 1.0
 */

public class CadrePr_AboutBox extends JDialog implements ActionListener {

	static ResourceBundle res = ResourceBundle.getBundle("Interface.Res");
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel insetsPanel1 = new JPanel();
	JPanel insetsPanel2 = new JPanel();
	JPanel insetsPanel3 = new JPanel();
	JButton button1 = new JButton();
	JLabel imageLabel = new JLabel();
	JLabel label1 = new JLabel();
	JLabel label2 = new JLabel();
	JLabel label3 = new JLabel();
	JLabel label4 = new JLabel();
	ImageIcon image1 = new ImageIcon();
	BorderLayout borderLayout1 = new BorderLayout();
	BorderLayout borderLayout2 = new BorderLayout();
	FlowLayout flowLayout1 = new FlowLayout();
	GridLayout gridLayout1 = new GridLayout();
	String product = "";
	String version = "1.0";
	String copyright = res.getString("Copyright_c_2004");
	String comments = "";

	public CadrePr_AboutBox(Frame parent) {
		super(parent);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Initialiser le composant
	private void jbInit() throws Exception {
		image1 = new ImageIcon(Interface.CadrePr.class.getResource(res.getString("about_png")));
		imageLabel.setIcon(image1);
		this.setTitle(res.getString("Apropos"));
		panel1.setLayout(borderLayout1);
		panel2.setLayout(borderLayout2);
		insetsPanel1.setLayout(flowLayout1);
		insetsPanel2.setLayout(flowLayout1);
		insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gridLayout1.setRows(4);
		gridLayout1.setColumns(1);
		label1.setText(product);
		label2.setText(version);
		label3.setToolTipText("");
		label3.setText(res.getString("drmdh_Copyright_c"));
		label3.addMouseListener(new Cadre1_AboutBox_label3_mouseAdapter(this));
		label4.setText(comments);
		insetsPanel3.setLayout(gridLayout1);
		insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
		button1.setText(res.getString("Ok"));
		button1.addActionListener(this);
		insetsPanel2.add(imageLabel, null);
		panel2.add(insetsPanel2, BorderLayout.WEST);
		this.getContentPane().add(panel1, null);
		insetsPanel3.add(label1, null);
		insetsPanel3.add(label2, null);
		insetsPanel3.add(label3, null);
		insetsPanel3.add(label4, null);
		panel2.add(insetsPanel3, BorderLayout.CENTER);
		insetsPanel1.add(button1, null);
		panel1.add(insetsPanel1, BorderLayout.SOUTH);
		panel1.add(panel2, BorderLayout.NORTH);
		setResizable(true);
	}

	// Supplant�, ainsi nous pouvons sortir quand la fen�tre est ferm�e
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			cancel();
		}
		super.processWindowEvent(e);
	}

	// Fermer le dialogue
	void cancel() {
		dispose();
	}

	// Fermer le dialogue sur un �v�nement bouton
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			cancel();
		}
	}

	void label3_mousePressed(MouseEvent e) {

	}

}

class Cadre1_AboutBox_label3_mouseAdapter extends java.awt.event.MouseAdapter {
	CadrePr_AboutBox adaptee;

	Cadre1_AboutBox_label3_mouseAdapter(CadrePr_AboutBox adaptee) {
		this.adaptee = adaptee;
	}

	public void mousePressed(MouseEvent e) {
		adaptee.label3_mousePressed(e);
	}
}
