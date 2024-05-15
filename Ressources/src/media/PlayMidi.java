package media;

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
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	private boolean midiEOM = false;
	private boolean canPlay = false;

	public void setCanPlay(boolean canPlay) {
		this.canPlay = canPlay;
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
	public void playMidi(Object object) {
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

		}

		try {
			try {
				sequencer.open();
			} catch (MidiUnavailableException ex2) {
			}
			if (currentSound instanceof Sequence) {
				sequencer.setSequence((Sequence) currentSound);
			} else {
				try {
					sequencer.setSequence((BufferedInputStream) currentSound);
				} catch (InvalidMidiDataException ex3) {
				} catch (IOException ex3) {
				}
			}

		} catch (InvalidMidiDataException imde) {
		//	System.out.println("Unsupported audio file.");
			currentSound = null;

		}

		if (currentSound instanceof Sequence || currentSound instanceof BufferedInputStream) {
			sequencer.start();
			while (!midiEOM && playerCont != null) {
				try {
					playerCont.wait(99);
				} catch (Exception e) {
					break;
				}
			}

			/// sequencer.stop();
			// sequencer.close();

			currentSound = null;
		}

	}

	public void play(String pathe) {
		file = new File(pathe);
		setCanPlay(true);
		// playerCont.start();
		if (playerCont.isAlive()) {
			System.out.print("il est en vie");
			// playerCont=null;
			System.out.print("il est en mort");
			playerCont = new Thread(this);
			playerCont.start();
			System.out.print("je revie");

		} else {
			System.out.print("il est   mort");
			playerCont = new Thread(this);
			playerCont.start();
			System.out.print("je revie");
		}

	}

	public void pause() {
		this.sequencer.stop();
	}

	public void replay() {

		this.sequencer.start();
	}

	public void run() {
		if (CanPlay()) {
			playMidi(file);
		}

		System.out.print("je meure");

	}

	public static void main(String[] args) {
		PlayMidi playMidi1 = new PlayMidi();// new File()"wave\\midi\\music02.mid"
		playMidi1.play("wave\\midi\\music02.mid");
		playMidi1.play("wave\\midi\\music01.mid");

	}

}
