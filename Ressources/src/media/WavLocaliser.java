package media;

import java.io.File;

public class WavLocaliser {
	private static boolean stoped = false;


	private static File ClipGameOver = new File("rc/media/wave/ClipGameOver.wav");
	private static File caover = new File("rc/media/wave/caover.wav");
	private static File ClipExplodedBlocks = new File("rc/media/wave/ClipExplodedBlocks.wav");
	private static File cloche = new File("rc/media/wave/cloche.wav");
	private static File ClipStage2 = new File("rc/media/wave/ClipStage2.wav");
	private static File ClipInitGame = new File("rc/media/wave/ClipInitGame.wav");

	private static File ClipDropBlocks = new File("rc/media/wave/ClipDropBlocks.wav");

	private static File Clipdejatouche = new File("rc/media/wave/Clipdejatouche.wav");
	private static File ClipNoAcces = new File("rc/media/wave/ClipNoAcces.wav");

	private static File ClipStage1 = new File("rc/media/wave/ClipStage1.wav");

	private static File music01 = new File("rc/media/wave/midi/music01.mid");
	private static File music02 = new File("rc/media/wave/midi/music02.mid");
	private static File music03 = new File("rc/media/wave/midi/music03.mid");
	private static File Victory = new File("rc/media/wave/midi/Victory.mid");

	static Sonido qSonido = new Sonido();

	/*
	 * javax.swing.JOptionPane.showMessageDialog(new JDialog() ,
	 * "Extraction failed :added packages and or class behind  \n" // +
	 * this.getClass().getName() + ".class"
	 * 
	 * );
	 */
	public static void setStop(boolean stop) {
		qSonido.setStop(stop);
		stoped = stop;
		/*
		 * if(isAllfIlesLoaded()){ qSonido.setMuet(muted); muet = muted; }else{
		 * filesLoader(); qSonido.setMuet(muted); }
		 */
	}

	public static boolean isStoped() {
		return stoped;
	}

	public WavLocaliser() {
	}

	/**
	 * playMIDI
	 *
	 * @param filename String
	 */
	public void playMIDI(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			qSonido.playMidi(file);
		}

	}

	public static void playClipStage2() {
		if (!isStoped()) {
			qSonido.playWave(ClipStage2);
		}
	}

	/**
	 * playMIDI
	 *
	 * @param filename String
	 */
	public void playWave(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			qSonido.playWave(file);
		}

	}

	public static void playClipInitGame() {
		if (!isStoped()) {
			qSonido.playWave(ClipInitGame);
		}
	}

	public static void playClipDropBlocks() {
		if (!isStoped()) {

			qSonido.playWave(ClipDropBlocks);

		}

	}

	public static void playClipdejatouche() {
		if (!isStoped()) {
			qSonido.playWave(Clipdejatouche);

		}

	}

	public static void playClipStage1() {
		if (!isStoped()) {
			qSonido.playWave(ClipStage1);
		}

	}

	/**
	 * playClipNoAcces
	 */
	public static void playClipNoAcces() {
		if (!isStoped()) {
			qSonido.playWave(ClipNoAcces);

		}

	}

	/*  */

	public static void playClipCaover() {
		if (!isStoped()) {
			qSonido.playWave(caover);
		}
	}

	public static void playClipCloche() {
		if (!isStoped()) {
			qSonido.playWave(cloche);

		}

	}

	public static void playMidimusic01() {
		if (!isStoped()) {
			qSonido.playMidi((File) music01);

		}

	}

	public static void playMidimusic02() {
		if (!isStoped()) {
			qSonido.playMidi(music02);

		}

	}

	public static void playMidimusic03() {
		if (!isStoped()) {
			qSonido.playMidi(music03);

		}

	}

	public static void playClipGameOver() {
		if (!isStoped()) {
			qSonido.playWave(ClipGameOver);
		}
	};

	public static void playClipExplodedBlocks() {
		if (!isStoped()) {

			qSonido.playWave(ClipExplodedBlocks);
		}

	}

	public static void playMidiVictory() {
		if (!isStoped()) {
			qSonido.playMidi(Victory);

		}

	}

	
	
	
	//private static File[] AllFiles=new File[15];
		
		  private static File ClipButtonAction = new
		  File("rc/media/wave/ClipButtonAction.wav"); private static File
		  ClipFillBlocks = new File("rc/media/wave/ClipFillBlocks.wav"); private static
		  File ClipMovedBlocks = new File("rc/media/wave/ClipMovedBlocks.wav");
		  
		  private static File ClipStage3 = new File("rc/media/wave/ClipStage3.wav");
		  private static File ClipTime05 = new File("rc/media/wave/ClipTime05.wav");
		  
		  private static File ClipTime20 = new File("rc/media/wave/ClipTime20.wav");
		  
		 
	
	
	 public static void playClipButtonAction() { if (!isStoped()) {
	  
	 qSonido.playWave(ClipButtonAction); }
	  
	  }
	  
	  
	  public static void playClipFillBlocks() { if (!isStoped()) {
	  qSonido.playWave(ClipFillBlocks); } }
	  
	  
	  public static void playClipMovedBlocks() { if (!isStoped()) {
	  qSonido.playWave(ClipMovedBlocks); } }
	  
	  
	  
	  
	  public static void playClipStage3() { if (!isStoped()) {
	  qSonido.playWave(ClipStage3); } }
	  
	  public static void playClipTime05() { if (!isStoped()) {
	  qSonido.playWave(ClipTime05); } }
	  
	  public static void playClipTime20() { if (!isStoped()) {
	  qSonido.playWave(ClipTime20); }
	  
	  }
	  
	  
	  
	 

	public static void main(String[] ar) {
		WavLocaliser.playMidimusic02();
		WavLocaliser.playClipDropBlocks();
		
		  WavLocaliser.playClipButtonAction(); WavLocaliser.playClipExplodedBlocks();
		  WavLocaliser.playClipFillBlocks(); WavLocaliser.playClipInitGame();
		  WavLocaliser.playClipGameOver(); WavLocaliser.playClipMovedBlocks();
		  WavLocaliser.playClipStage1(); WavLocaliser.playClipStage2();
		  WavLocaliser.playClipStage3(); WavLocaliser.playClipTime05();
		  WavLocaliser.playClipTime20();
		 
	}

}
