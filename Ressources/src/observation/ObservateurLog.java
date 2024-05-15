package observation;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright : Copyright (c) 2004
 * </p>
 * <p>
 * Soci�t� :
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class ObservateurLog extends JDialog implements ActionListener {
	FileManager logfile;
	BorderLayout borderLayout1 = new BorderLayout();
	JTextArea logastxt = new JTextArea();
	JButton actualise = new JButton();
	JPanel jPanel1 = new JPanel();
	JButton Fermer = new JButton();
	public JTextField logpathtxtField = new JTextField();
	Box myBoxH = Box.createHorizontalBox();
	Box myBoxV = Box.createVerticalBox();
	private String logFilePath;
	JButton append = new JButton();
	JButton clearLog = new JButton();
	JButton yesBt = new JButton();
	JButton NOBT = new JButton();
	JButton MOVEBT = new JButton();
	JButton pliebt = new JButton();

	public void setLogFilePath(String logFilePath) {
		logpathtxtField.setText(logFilePath);
		this.logFilePath = logFilePath;
	}

	public String getLogFilePath() {
		return logFilePath;
	}

	public ObservateurLog() {

		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.pack();
		this.show();
	}

	public ObservateurLog(Frame parent) {
		super(parent);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void jbInit() throws Exception {
		this.getContentPane().setLayout(borderLayout1);
		actualise.setToolTipText("");
		actualise.setActionCommand("actualise");
		actualise.setText("actualise");
		actualise.addActionListener(new ObservateurLog_actualise_actionAdapter(this));
		logastxt.setOpaque(true);
		logastxt.setPreferredSize(new Dimension(800, 350));
		logastxt.setSelectionStart(0);
		logastxt.setText("");
		Fermer.setActionCommand("Fermer");
		Fermer.setText("Fermer");
		Fermer.addActionListener(new ObservateurLog_Fermer_actionAdapter(this));

		logpathtxtField.setPreferredSize(new Dimension(163, 20));
		logpathtxtField.setRequestFocusEnabled(true);
		logpathtxtField.setToolTipText("");
		logpathtxtField.setSelectionStart(11);
		logpathtxtField.setText("logTest.log");
		logpathtxtField.setScrollOffset(0);
		append.setActionCommand("append");
		append.setText("append");
		append.addActionListener(new ObservateurLog_append_actionAdapter(this));
		clearLog.setToolTipText("");
		clearLog.setActionCommand("clearLog");
		clearLog.setText("clearLog");
		clearLog.addActionListener(new ObservateurLog_clearLog_actionAdapter(this));
		yesBt.setToolTipText("");
		yesBt.setText("YES");
		yesBt.addActionListener(new ObservateurLog_yesBt_actionAdapter(this));
		NOBT.setActionCommand("NO");
		NOBT.setText("NO");
		MOVEBT.setVerifyInputWhenFocusTarget(true);
		MOVEBT.setText("MOVE");
		MOVEBT.addActionListener(new ObservateurLog_MOVEBT_actionAdapter(this));
		pliebt.setText("Pli");
		pliebt.addActionListener(new ObservateurLog_pliebt_actionAdapter(this));
		this.getContentPane().add(jPanel1, BorderLayout.CENTER);

		myBoxV.add(logastxt, null);
		myBoxH.add(actualise, null);
		myBoxH.add(append, null);
		myBoxH.add(clearLog, null);
		myBoxH.add(Fermer, null);
		myBoxV.add(logpathtxtField, null);
		myBoxH.add(pliebt, null);
		myBoxH.add(MOVEBT, null);
		myBoxH.add(NOBT, null);
		myBoxH.add(yesBt, null);

		myBoxV.add(myBoxH, null);
		jPanel1.add(myBoxV, null);
		NOBT.addActionListener(new ObservateurLog_NOBT_actionAdapter(this));

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
		if (e.getSource() == Fermer) {
			cancel();
		}
	}

	void Fermer_actionPerformed(ActionEvent e) {
		cancel();
	}

	void actualise_actionPerformed(ActionEvent e) {
		logfile = new FileManager(this.logpathtxtField.getText());
		this.logastxt.setText((String) logfile.readFile());
		new Mover((Window) this, 1, 0, 0, 0);
	}

	public void addandAndShow(String col1, String col2) {
		/* */
		logfile = new FileManager(this.logpathtxtField.getText());
		logfile.addligneColones(col1, col2);
		logfile.appendFile();
		if (this.isShowing()) {
			this.logastxt.setText((String) logfile.readFile());

		}

	}

	void append_actionPerformed(ActionEvent e) {
		addandAndShow(this.logastxt.getText(), "     ok ");
	}

	public static void main(String[] argv) {
		/* ObservateurLog laFen�tre = */ new ObservateurLog();

	}

	void clearLog_actionPerformed(ActionEvent e) {
		logfile = new FileManager(this.logpathtxtField.getText());
		logfile.clearFile(this.logpathtxtField.getText());
		this.logastxt.setText((String) logfile.readFile());
	}

	void pliebt_actionPerformed(ActionEvent e) {
		new Mover((Window) this, 0, 0, 0, 1);
	}

	void NOBT_actionPerformed(ActionEvent e) {
		new Mover((Window) this, 0, 1, 0, 0);
	}

	void MOVEBT_actionPerformed(ActionEvent e) {
		new Mover((Window) this, 0, 0, 1, 0);
	}

	void yesBt_actionPerformed(ActionEvent e) {
		new Mover((Window) this, 1, 0, 0, 0);
	}

}

class ObservateurLog_Fermer_actionAdapter implements java.awt.event.ActionListener {
	ObservateurLog adaptee;

	ObservateurLog_Fermer_actionAdapter(ObservateurLog adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.Fermer_actionPerformed(e);
	}
}

class ObservateurLog_actualise_actionAdapter implements java.awt.event.ActionListener {
	ObservateurLog adaptee;

	ObservateurLog_actualise_actionAdapter(ObservateurLog adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.actualise_actionPerformed(e);
	}
}

class ObservateurLog_append_actionAdapter implements java.awt.event.ActionListener {
	ObservateurLog adaptee;

	ObservateurLog_append_actionAdapter(ObservateurLog adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.append_actionPerformed(e);
	}
}

class ObservateurLog_clearLog_actionAdapter implements java.awt.event.ActionListener {
	ObservateurLog adaptee;

	ObservateurLog_clearLog_actionAdapter(ObservateurLog adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.clearLog_actionPerformed(e);
	}
}

class ObservateurLog_pliebt_actionAdapter implements java.awt.event.ActionListener {
	ObservateurLog adaptee;

	ObservateurLog_pliebt_actionAdapter(ObservateurLog adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.pliebt_actionPerformed(e);
	}
}

class ObservateurLog_NOBT_actionAdapter implements java.awt.event.ActionListener {
	ObservateurLog adaptee;

	ObservateurLog_NOBT_actionAdapter(ObservateurLog adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.NOBT_actionPerformed(e);
	}
}

class ObservateurLog_MOVEBT_actionAdapter implements java.awt.event.ActionListener {
	ObservateurLog adaptee;

	ObservateurLog_MOVEBT_actionAdapter(ObservateurLog adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.MOVEBT_actionPerformed(e);
	}
}

class ObservateurLog_yesBt_actionAdapter implements java.awt.event.ActionListener {
	ObservateurLog adaptee;

	ObservateurLog_yesBt_actionAdapter(ObservateurLog adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.yesBt_actionPerformed(e);
	}
}
