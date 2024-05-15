package observation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

public class FileManager implements Serializable, Runnable {
	private String logfile = "logfile.log";
	private String strtxt = "";
	private String msg;
	private int delay = 500;
	private transient Thread delayer;
	private boolean stopped = false;
	private Date dateCourante = null;

	private DateFormat formatHeure = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.FRANCE);
	private DateFormat formatJour = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);

	/**
	 * getTime
	 *
	 * @return String
	 */
	public String getHTime() {
		// affichage de l'heure et de la date courante
		// respectivement dans des fontes de grosse et petite
		// tailles :
		dateCourante = Calendar.getInstance().getTime();
		String str = "" + Calendar.getInstance().getTimeInMillis();
		return // formatJour.format(dateCourante)
				// +" "+
		formatHeure.format(dateCourante)
		// +":"+
		// str.charAt(0)+str.charAt(1);
		;
	}

	/**
	 * getTime
	 *
	 * @return String
	 */
	public String getTime() {
		// affichage de l'heure et de la date courante
		// respectivement dans des fontes de grosse et petite
		// tailles :
		dateCourante = Calendar.getInstance().getTime();
		String str = "" + Calendar.getInstance().getTimeInMillis();
		return formatJour.format(dateCourante) + " " + formatHeure.format(dateCourante)
		// +":"+
		// str.charAt(0)+str.charAt(1);
		;
	}

	public FileManager() {
		msg = "bienvenue !";
		initDelay();
	}

	public FileManager(String file) {
		this.logfile = file;
		msg = "bienvenue !";
		initDelay();
	}

	private void initDelay() {
		delayer = new Thread();
		delayer.start();
	}

	public void run() {

		try {
			while (true) {
				// ce mettre en attente tant qu'il nest pas arr�t�.
				synchronized (this) {
					while (stopped) {

						wait();

					}
				}
				// afficher le message
				System.out.println(msg);
				// ce rem�tre en somm�il.

				delayer.wait(delay);

			}
		} catch (InterruptedException ex) {
			// afficher le message
			System.out.println(msg + ex);
		}
	}

	public synchronized void startPrint() {
		stopped = false;
		notify();
	}

	public synchronized void stopPrint() {
		stopped = true;
	}

	public String readFile() {
		File file = new File(this.logfile);
		if (!file.exists()) {
// writefile(this.logfile+" \t"+getTime());

			writeObject(this.logfile + " \t" + getTime(), this.logfile);
		}

		return (String) readFile(this.logfile);// new String();

	}

	public Object readFile(String file) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			return obj;

		} catch (FileNotFoundException e) {
			System.err.println("Unable to find file" + e);
			return null;
		} catch (ObjectStreamException e) {
			System.err.println("Unable to read object" + e);
			return null;
		} catch (IOException e) {
			System.err.println("Unable to read" + e);
			return null;
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to find class" + e);
			return null;
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}

			} catch (IOException ex) {
			}
		}

	}

	public static void writeObject(String obj, String file) {
		Object str = obj.toString();
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);
			oos.writeObject(str);

		} catch (IOException e) {
			System.err.println("Unable to save" + e);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException ex) {
				}
			}

		}
	}

	/*
	 * public String readFile() {
	 * 
	 * String strtmp=""; try { // Open a file of the given name. File file = new
	 * File(this.logfile); if(!file.exists()){
	 * writefile(this.logfile+" \t"+getTime()); } // Get the size of the opened
	 * file. int size = (int)file.length();
	 * 
	 * // Set to zero a counter for counting the number of // characters that have
	 * been read from the file. int chars_read = 0;
	 * 
	 * // Create an input reader based on the file, so we can read its data. //
	 * FileReader handles international character encoding conversions. FileReader
	 * in = new FileReader(file);
	 * 
	 * // Create a character array of the size of the file, // to use as a data
	 * buffer, into which we will read // the text data. char[] data = new
	 * char[size];
	 * 
	 * // Read all available characters into the buffer. while(in.ready()) { //
	 * Increment the count for each character read, // and accumulate them in the
	 * data buffer. chars_read += in.read(data, chars_read, size - chars_read); }
	 * in.close();
	 * 
	 * // Create a temporary string containing the data, // and return the string
	 * return strtmp=new String(data, 0, chars_read);
	 * 
	 * } catch (IOException e) { System.out.println(" Error opening " +
	 * this.logfile); } return strtmp;
	 * 
	 * 
	 * }
	 * 
	 * 
	 */

	public void writefile(String text) {
		/*
		 * // Open a file of the current name. File file = new File (this.logfile);
		 * boolean saved=false; // Create an output writer that will write to that file.
		 * // FileWriter handles international characters encoding conversions.
		 * FileWriter out = null; try { out = new FileWriter(file); out.write(text);
		 * out.close(); saved= true; } catch (IOException ex) { saved= false;
		 * System.err.println("FileManager.writefile : Error saving " + this.logfile); }
		 * 
		 * return saved;
		 */
		writeObject(text, this.logfile);
	}

	public void writefile() {
		/*
		 * // Open a file of the current name. File file = new File (this.logfile);
		 * boolean saved=false; // Create an output writer that will write to that file.
		 * // FileWriter handles international characters encoding conversions.
		 * FileWriter out = null; try { out = new FileWriter(file); out.write(strtxt);
		 * out.close(); saved= true; } catch (IOException ex) { saved= false;
		 * System.err.println("FileManager.writefile : Error saving " + this.logfile); }
		 * 
		 * return saved;
		 */
		writeObject(strtxt, this.logfile);

	}

	/**
	 * appendFile
	 *
	 * @param str String
	 */
	public void appendFile(String str) {
		String strtmp = (String) this.readFile();
		strtmp = strtmp + str;
		strtxt = strtmp + strtxt;
		this.writefile(strtmp);
	}

	/**
	 * appendFile
	 *
	 *
	 */
	public void appendFile() {
		String strtmp = (String) this.readFile();
		strtxt = strtmp + strtxt;
		this.writefile();
	}

	public void clearFile(String File) {
		this.writefile("");
	}

	/**
	 * addString
	 *
	 * @param str String
	 */
	public void addligne(String str) {
		strtxt = strtxt + "\n" + getHTime() + " :  " + str;
	}

	public void addcolonesurLaligne(String str) {
		strtxt = strtxt + "\t       \t" + str;
	}

	public void addligneColones(String ligne, String colone) {
		addligne(ligne);
		addcolonesurLaligne(colone);
	}

	public static void main(String[] args) {
		FileManager myFile1 = new FileManager("trame.txt");
		String str1, str2 = new String("hello world ");
		myFile1.addligne("hamid a dit : ");
		myFile1.addcolonesurLaligne("how are you");
		myFile1.addligne("faouzi a dit : ");
		myFile1.addcolonesurLaligne("fine and you ");
		myFile1.addligne("hamid a dit: ");
		myFile1.addcolonesurLaligne("like yestoday at this time");
		myFile1.addligne("hamid a dit : ");
		myFile1.addcolonesurLaligne("are you sure");
		myFile1.addligneColones("A a dit   ", " whose  ");
		myFile1.addligneColones("B a dit   ", " you don't know ");
		myFile1.addligneColones("A a dit  ", "I forget it  ");
		myFile1.addligneColones("B a dit   ", "tu est vraiment un nase  ");
		String[] str = { "nous somme ", "le mercredi", "23", "JUIN", "2004" };

		myFile1.appendFile();
		myFile1.appendFile("\n\t\t_______________________\t\t\n");
		System.out.println(myFile1.readFile().toString());

	}

}
