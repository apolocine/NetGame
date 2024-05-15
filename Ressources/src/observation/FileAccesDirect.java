package observation;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

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
 * Societe :
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

class FileAccesDirect implements Serializable, Runnable {
	private transient Thread delayer;
	private boolean stopped = false;
	private int delay = 500;

	public FileAccesDirect() {
		initDelay();

	}

	private void initDelay() {
		delayer = new Thread();
		delayer.start();
	}

	public synchronized void startPrinting() {
		stopped = false;
		notify();
	}

	public synchronized void stopPrinting() {
		stopped = true;
	}

	public void run() {
		try {
			while (true) {
				// ce mettre en attente tant qu'il nest pas arrete.
				synchronized (this) {
					while (stopped) {

						wait();

					}
				}
				// afficher le message
				// System.out.println(msg);
				// ce remetre en sommeil.

				delayer.wait(delay);

			}
		} catch (InterruptedException ex) {
			// afficher le message
			// System.out.println(msg + ex);
		}
	}

	static boolean saveFile(String fileName, String text) {
		// startPrinting();
		// Open a file of the current name.
		File file = new File(fileName);
		boolean saved = false;
		// Create an output writer that will write to that file.
		// FileWriter handles international characters encoding conversions.
		FileWriter out = null;
		try {
			out = new FileWriter(file);
			out.write(text);
			out.close();
			saved = true;
		} catch (IOException ex) {
			saved = false;
			System.out.println(" Error saving " + fileName);
		}

		return saved;
	}

	/**
	 * Open named file; read text from file and return it;
	 *
	 *
	 * @param fileName String
	 * @return String
	 */
	static String openFile(String fileName) {
		String strtmp = "";
		try {
// Open a file of the given name.
			File file = new File(fileName);
			if (!file.exists()) {
				saveFile(fileName, "");
			}
// Get the size of the opened file.
			int size = (int) file.length();

// Set to zero a counter for counting the number of
// characters that have been read from the file.
			int chars_read = 0;

// Create an input reader based on the file, so we can read its data.
// FileReader handles international character encoding conversions.
			FileReader in = new FileReader(file);

// Create a character array of the size of the file,
// to use as a data buffer, into which we will read
// the text data.
			char[] data = new char[size];

// Read all available characters into the buffer.
			while (in.ready()) {
				// Increment the count for each character read,
				// and accumulate them in the data buffer.
				chars_read += in.read(data, chars_read, size - chars_read);
			}
			in.close();

// Create a temporary string containing the data,
// and return the string
			return strtmp = new String(data, 0, chars_read);

		} catch (IOException e) {
			System.out.println(" Error opening " + fileName);
		}
		return strtmp;
	}

	static void ecritureDeString(String nomFichier, String str) throws IOException {
		// le tableau des couleurs :
		// String[] couleurs = {"vert","bleu","gris","brun","noir"};
		// le flux en ecriture sur disque :
		Writer sortie = new FileWriter(new File(nomFichier));
		sortie.write(str);

		sortie.close();
	}

	final static short DIM = 1024;
	static int nombreAuxiliaireC = 0;

	static String lectureDeString(String nomFichier) throws IOException {
		File f = new File(nomFichier);
		if (f.canRead())
			nombreAuxiliaireC = (int) f.length();

		// instanciation d'un tableau de "DIM" caracteres :
		char[] tableau = new char[nombreAuxiliaireC];
		// le flux en lecture sur disque :
		Reader entree = new FileReader(new File(nomFichier));
		// String tableau="";

		while (entree.read(tableau) != -1) {
			// affichage des caracteres lus sur la sortie standard :
			System.out.println(tableau);
		}
		// entree.close();
		return new String(tableau);
	}

	static void ecritureDirecte(String nomFichier, String text) throws IOException {
		// acces en ecriture (mode "rw") au fichier de nom "nomFichier" :
		RandomAccessFile monFichier = new RandomAccessFile(nomFichier, "rw");

		monFichier.writeBytes(text);
		monFichier.close();
	}

	/**
	 * appendFile
	 *
	 * @param nomFichier String
	 * @param strtoadd   String
	 * @throws IOException
	 * 
	 *                     public static void appendFile(String nomFichier,String
	 *                     strtoadd)throws IOException { String strtmp =
	 *                     lectureDirecte(nomFichier); System.out.println(" lue
	 *                     "+strtmp); String strtmp2=strtmp+ strtoadd;
	 * 
	 *                     ecritureDirecte( nomFichier,strtmp2);
	 * 
	 * 
	 *                     }
	 */
	/**
	 *
	 * @param nomFichier String
	 * @throws IOException
	 * @return String
	 */
	static String lectureDirecte(String nomFichier) throws IOException {
		RandomAccessFile monFichier = null;
		String str = "";
		try {
			// acces en lecture (mode "r") au fichier de nom "nomFichier" :
			monFichier = new RandomAccessFile(nomFichier, "r");
			str = (String) monFichier.readLine();

		} catch (Exception e) {

		} finally {

			System.out.println(str);
			if (monFichier != null) {

				monFichier.close();
			}
		}
		return str;

	}

	/**
	 * addString
	 *
	 * @param str String
	 */
	public static String addligne(String str) {
		return "\n" + str;
	}

	private static String addcolonesurLaligne(String str) {
		return "\t  \t  " + str;
	}

	/*
	 * private static void addligneColones(String ligne, String colone) {
	 * addligne(ligne); addcolonesurLaligne(colone); }
	 */
	public static void addligneColones(String nomFichier, String ligne, String colone) throws IOException {
		String str = "\n" + ligne + "\t\t" + colone;
		appendFile(nomFichier, str);
	}

	public static void addligneColones(String nomFichier, String ligne, String[] colone) throws IOException {
		String tmpC = "from tmp hello ";
		for (int i = 0; i < colone.length; i++) {
			tmpC = tmpC + addcolonesurLaligne(colone[i]);
		}
		String tmpL = addligne(ligne) + tmpC;

		appendFile(nomFichier, tmpL);
	}

	/**
	 * appendFile
	 *
	 *
	 */
	public static void appendFile(String nomFichier, String strt) throws IOException {
		/*
		 * String strtmp = lectureDirecte(nomFichier); String strtmp2= strtmp+ strtoadd;
		 * 
		 * ecritureDirecte( nomFichier,strtmp2);
		 */
		ecritureDirecte(nomFichier, openFile(nomFichier) + "\n" + strt);
		// ecritureDirecte(nomFichier,lectureDirecte(nomFichier)+"\n"+strt);
		//

	}

	public static void main(String[] args) {
		String[] str = { "hello", "world" };

		try {
			appendFile("exemple.log", "hello is good ?\n");
			// addligneColones("exemple.log","hello",str);
		} catch (IOException ex) {
		}

		System.out.println(openFile("exemple.log"));

	}

}
