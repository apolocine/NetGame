package media;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

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
 * @version 1.0setStop
 */
public class Sonido extends JFrame {
	private boolean stop = false;

	WavPlayer wavplayer;

	PlayMidi playMidi1 = new PlayMidi();

	public void setStop(boolean stop) {
		playMidi1.setStop(stop);
		this.stop = stop;
	}

	public boolean isMuet() {
		return stop;
	}

	public Sonido(String strWav) {
		wavplayer = new WavPlayer(strWav);
	}

	public Sonido() {
		wavplayer = new WavPlayer();

	}

	public void play(String strWav) {
		wavplayer.play(strWav);

	}

	public void playWave(File filef) {

		wavplayer.playWave(filef);

	}

	public void play() {

		wavplayer.play();

	}

	public void playMidi(String midipath) {
		playMidi1.play(midipath);
	}

	public void playMidi(File midiFile) {
		playMidi1.play(midiFile);

	}

	// Supplant�, ainsi nous pouvons sortir quand la fen�tre est ferm�e
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			Exit_actionPerformed(null);
		}
	}

	// Op�ration Fichier | Quitter effectu�e
	public void Exit_actionPerformed(ActionEvent e) {
		System.out.print("bye bye");
		System.exit(0);
	}

	public class WavPlayer implements Runnable {
		AudioFileFormat aff;
		AudioInputStream ais;
		File sf;
		Thread threadControler;
		private boolean canPlay;

		public synchronized void setCanPlay(boolean canPlay) {
			this.canPlay = canPlay;
		}

		public synchronized boolean isCanPlay() {
			return canPlay;
		}

		/**
		 * Player
		 */
		public WavPlayer() {
			setCanPlay(false);
			threadControler = new Thread(this);
			threadControler.start();
		}

		/**
		 * player
		 *
		 * @param strWav String
		 */
		public WavPlayer(String strWav) {
			setCanPlay(true);
			sf = new File(strWav);
			threadControler = new Thread(this);
			threadControler.start();
		}

		/**
		 * play
		 */
		public synchronized void play() {
			try {
				aff = AudioSystem.getAudioFileFormat(sf);
				ais = AudioSystem.getAudioInputStream(sf);
				AudioFormat af = aff.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat(),
						((int) ais.getFrameLength() * af.getFrameSize()));

				Clip ol = (Clip) AudioSystem.getLine(info);

				ol.open(ais);
//ol.start();
				// ol.stop();
				/**
				 * @todo d�commenter la ligne suivate pour la lecture en continue du fichier
				 *       wave
				 *
				 */

				// ol.loop(Clip.LOOP_CONTINUOUSLY);
				ol.loop(0);
//        System.out.println("I am playing " + this.sf.getPath() +
				// " \n reproduci�n empezada, apretar CTRL-C para interrumpir");

			} catch (UnsupportedAudioFileException ee) {
				System.out.println(ee);

			} catch (IOException ea) {
				System.out.println(ea);
			} catch (LineUnavailableException LUE) {
				System.out.println(LUE);
			}

		}

		/**
		 * stop
		 */
		public void stop() {
		}

		/**
		 * play
		 *
		 * @param file String
		 */
		public synchronized void play(String file) {
			this.sf = new File(file);
			setCanPlay(true);
			this.run();
		}

		/**
		 * replay
		 *
		 * 
		 */
		public void replay() {
			play();
		}

		public void run() {
			synchronized (this) {
				if (isCanPlay()) {
					play();
				}

			}

		}

		/**
		 * play
		 *
		 * @param filef File
		 */
		private void playWave(File filef) {
			this.sf = filef;
			setCanPlay(true);
			this.run();
		}

	}

	public static void main(String[] ar) {
		Sonido snd = new Sonido();
		snd.playMidi1.play("wave\\midi\\Victory.mid");
		snd.playMidi("wave\\midi\\music02.mid");
		snd.playMidi1.play("wave\\midi\\music02.mid");
		snd.playMidi1.play("wave\\midi\\music01.mid");

		snd.playMidi1.play("wave\\midi\\music01.mid");
		snd.playMidi1.play("wave\\midi\\music02.mid");
		snd.playMidi1.play("wave\\midi\\music03.mid");
		snd.play("C:\\sounds\\drop.wav");
		snd.playWave(new File("C:\\sounds\\drop.wav"));
		snd.play("C:\\sounds\\error.wav");

		// snd.play(new
		// File(resources.wave.WavLocaliser.class.getResource("ClipButtonAction.wav").getFile()));
		snd.playWave(new File("wave\\ClipExplodedBlocks.wav"));
		snd.playWave(new File("wave\\ClipGameOver.wav"));
		snd.playWave(new File("wave\\ClipMovedBlocks.wav"));
		snd.playWave(new File("wave\\ClipFillBlocks.wav"));
//snd. Exit_actionPerformed(null);
	}

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

	public class PlayMidi implements Runnable, MetaEventListener {
		File file;
		Thread playerCont;
		Sequencer sequencer;
		private boolean stop = false;

		private boolean midiEOM = false;
		private boolean canPlay = false;
		private boolean firstplay = false;
		private boolean canreplay = true;

		public boolean isStop() {
			return stop;
		}

		public boolean isFirstplay() {
			return firstplay;
		}

		public boolean Canreplay() {
			return canreplay;
		}

		public void setStop(boolean stop) {
			this.stop = stop;
			if (stop) {
				stopSequencer();
			} else {
				playThread();
			}

		}

		public void setCanPlay(boolean canPlay) {
			this.canPlay = canPlay;
		}

		public void setCanreplay(boolean canreplay) {
			this.canreplay = canreplay;
		}

		public void setFirstplay(boolean first) {
			this.firstplay = first;
		}

		public boolean CanPlay() {
			return canPlay;
		}

		public PlayMidi(String pathe) {
			file = new File(pathe);
			try {
				sequencer = MidiSystem.getSequencer();
			} catch (MidiUnavailableException ex1) {
			}

			sequencer.addMetaEventListener(this);
			playerCont = new Thread(this);
			setCanPlay(false);
			playerCont.start();
		}

		public PlayMidi() {
			try {
				sequencer = MidiSystem.getSequencer();
			} catch (MidiUnavailableException ex1) {
			}

			sequencer.addMetaEventListener(this);
			playerCont = new Thread(this);
			setCanPlay(false);
			playerCont.start();
		}

		public void meta(MetaMessage message) {
			if (message.getType() == 47) { // 47 is end of track
				midiEOM = true;
			}
		}

		/**
		 * playMidi
		 *
		 * @param object Object
		 */
		public synchronized void playMidi(Object object) {
			Object currentSound = null;
//  String currentName;

			try {
				currentSound = AudioSystem.getAudioInputStream((File) object);
			} catch (IOException ex) {
			} catch (UnsupportedAudioFileException ex) {
			}

			if (object instanceof File) {
				// currentName = ( (File) object).getName();

				try {
					currentSound = AudioSystem.getAudioInputStream((File) object);
				} catch (Exception e1) {
					// load midi & rmf as inputstreams for now
					// try {
					// currentSound = MidiSystem.getSequence((File) object);
					// } catch (Exception e2) {
					try {
						FileInputStream is = new FileInputStream((File) object);
						currentSound = new BufferedInputStream(is, 1024);
					} catch (Exception e3) {

						currentSound = null;

					}
				}
			}

			// user pressed stop or changed tabs while loading
			if (sequencer == null) {
				currentSound = null;
				return;
			}

			
			try {
				sequencer.open();
			} catch (MidiUnavailableException ex2) {
			}
		 
		 sequencerSound (  sequencer,  currentSound); 
			
			
 

			if (currentSound instanceof Sequence || currentSound instanceof BufferedInputStream) {

				// startTime = System.currentTimeMillis();
				// System.out.print("startTime"+startTime);

				sequencer.start();
				while (!midiEOM && playerCont != null && !isMuet()) {
					try {
						playerCont.wait(99);

					} catch (Exception e) {
						break;
					}
				}
				if (isFirstplay()) {
					setFirstplay(false);
				}

				/// sequencer.stop();
				// sequencer.close();

				currentSound = null;
			}

		}
		public void sequencerSound (Sequencer sequencer,Object currentSound) {
	        try {
	            sequencer.open();
	        } catch (MidiUnavailableException e) {
	            // Handle the exception appropriately
	            e.printStackTrace();
	            return; // or throw a new exception, depending on the requirements
	        }

	        try {
	            if (currentSound instanceof Sequence) {
	                sequencer.setSequence((Sequence) currentSound);
	            } else if (currentSound instanceof BufferedInputStream) {
	                try {
	                    Sequence sequence = MidiSystem.getSequence((BufferedInputStream) currentSound);
	                    sequencer.setSequence(sequence);
	                } catch (InvalidMidiDataException | IOException ex) {
	                    // Handle the exception appropriately
	                    ex.printStackTrace();
	                    currentSound = null; // Or take other appropriate action
	                }
	            } else {
	                System.out.println("Unsupported audio file.");
	                currentSound = null;
	            }
	        } catch (InvalidMidiDataException imde) {
	            System.out.println("Unsupported audio file.");
	            currentSound = null;
	            // Handle the exception appropriately
	            imde.printStackTrace();
	        }
	    }
		public synchronized void play(File pathe) {
			file = pathe;
			setCanPlay(true);
			// playerCont.start(); this.sf=filef;
			if (!isMuet()) {

				if (playerCont.isAlive()) {

					playMidi(pathe);
					// super.notifyAll();
				} else {
					playThread();
				}
			}
		}

		private void playThread() {
			// System.out.print("il est mort");
			if (playerCont != null) {
				playerCont = new Thread(this);
				playerCont.start();
			}

			// System.out.print("je revie");
		}

		public void play(String pathe) {

			play(new File(pathe));
		}

		public void pause() {
			// this.playerCont=null;
			this.sequencer.stop();
		}

		public void stopSequencer() {
//   this.playerCont=null;
			this.sequencer.stop();
		}

		/// sequencer.stop();
		public void replay() {

			this.sequencer.start();
		}

		long startTime;
		int length = 0;
		int lenSec;

		public void run() {

			/*
			 * if(!isMuet()){ if(CanPlay() ){ playMidi(file);
			 * System.out.println("\n"+this.sequencer.getMicrosecondLength()); }
			 */

			// //
			while (!isMuet() && Canreplay()) {
				if (CanPlay()) {
					synchronized (this) {
						playMidi(file);

						// System.out.print("i am waiting ");
						length = ((int) this.sequencer.getMicrosecondLength() / 1000) + 1000;
						lenSec = length / 1000;
						/*
						 * System.out.println("Sodino.run: .FileName = "+file.getAbsolutePath());
						 * System.out.println("Sodino.run: .sequencer.getMicrosecondLength = "+lenSec);
						 */ try {
							super.wait(length);
							// super.wait( );
							stopSequencer();
							System.out.println("Sodino.run : REPLAY ");
						} catch (InterruptedException ex) {
						}

					}

				}
			}
			// System.out.print("je meure");

		}
		/*
		 * public static void main(String[] args) { PlayMidi playMidi1 = new
		 * PlayMidi();//new File()"wave\\midi\\music02.mid"
		 * playMidi1.play("wave\\midi\\music02.mid");
		 * playMidi1.play("wave\\midi\\music01.mid");
		 * 
		 * }
		 */
	}

}
