package observation;

import java.awt.Window;
import java.awt.event.MouseEvent;

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

public class Mover implements Runnable {
	Window frame;
	Thread owner;
	int amplitude = 5, frequence = 2;
	int delay = 50;
	int yes = 0, no = 0, move = 0, pli = 0;
	private boolean stop;

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public void setAmplitude(int amplitude) {
		this.amplitude = amplitude;
	}

	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public boolean isStop() {
		return stop;
	}

	public int getAmplitude() {
		return amplitude;
	}

	public int getFrequence() {
		return frequence;
	}

	public int getDelay() {
		return delay;
	}

	public Mover(Window frame_, int yes, int no, int move, int pli) {
		this.yes = yes;
		this.no = no;
		this.move = move;
		this.pli = pli;
		this.frame = frame_;

		Thread owner = new Thread(this);
		owner.start();
	}

	public Mover(Window frame_) {
		this.frame = frame_;

		Thread owner = new Thread(this);
		owner.start();
	}

	public Mover(Window frame_, int amplitude_, int frequence_) {
		this.frame = frame_;
		this.amplitude = amplitude_;
		this.frequence = frequence_;
		Thread owner = new Thread(this);
		owner.start();
	}

	/*
	 * public Mover(JFrame frame_, int n) { this.frame = frame_; this.nx = n;
	 * 
	 * Thread owner = new Thread(this); owner.start(); }
	 */

	MouseEvent etmp;

	public synchronized void pressed(MouseEvent e) {
		etmp = e;
	}

	public synchronized void drag(MouseEvent e) {
		int x = (int) this.frame.getLocation().getX() + (e.getX() - etmp.getX());
		int y = (int) this.frame.getLocation().getY() + (e.getY() - etmp.getY());
		this.frame.setLocation(x, y);
	}

	public synchronized void sayNO() {
		int largeur = 5;
		int demilargeurg = (int) largeur / 5;
		int tmpx = (int) this.frame.getLocation().getX();
		int tmpy = (int) this.frame.getLocation().getY();
		for (int i = getAmplitude(); i > 0; i--) { //
			for (int j = getFrequence(); j > 0; j--) {
				try {
					this.frame.setLocation(i + tmpx, tmpy);
					super.wait(getDelay());
					this.frame.setLocation(tmpx - i, tmpy);
					super.wait(getDelay());
				} catch (InterruptedException ex) {
				}
//i=(int)i-i/4!=0?(int)i-i/4:i--;
			}

			i = i - demilargeurg;
		}
		/*
		 * this.frame.setLocation(tmpx, tmpy);
		 */

	}

	public synchronized void sayYES() {
		int tmpx = (int) this.frame.getLocation().getX();
		int tmpy = (int) this.frame.getLocation().getY();
		for (int i = getAmplitude(); i > 0; i--) { //
			for (int j = getFrequence(); j > 0; j--) {
				try {
					this.frame.setLocation(tmpx, i + tmpy);
					super.wait(getDelay());

					this.frame.setLocation(tmpx, tmpy - i);
					super.wait(getDelay());

				} catch (InterruptedException ex) {
				}

			}
			// i=((int)i-i/4)!=0?(int)i-i/4:i--;
		}
		/*
		 * this.frame.setLocation(tmpx, tmpy);
		 */

	}

	public synchronized void moveit(int frequence_) {
		int tmpx = (int) this.frame.getLocation().getX();
		int tmpy = (int) this.frame.getLocation().getY();

		for (int i = this.getAmplitude(); i > 0; i--) {
			for (int j = frequence_; j > 0; j--) {
				try {
					this.frame.setLocation(tmpx, i + tmpy);
					super.wait(getDelay());
					this.frame.setLocation(i + tmpx, tmpy);
					super.wait(getDelay());
					this.frame.setLocation(tmpx, tmpy - i);
					super.wait(getDelay());
					this.frame.setLocation(tmpx - i, tmpy);
					super.wait(getDelay());
				} catch (InterruptedException ex) {
				}

			}
		}
		/*
		 * this.frame.setLocation(tmpx, tmpy);
		 */
	}

	void douwnGD(int tmpx, int tmpy) {
//   for (int i = this.getAmplitude(); i > 0; i--) {
		for (int i = 0; i < this.getAmplitude(); i++) {
			try {
				this.frame.setLocation(tmpx + i, i + tmpy);
				super.wait(getDelay());
			} catch (InterruptedException ex) {
			}
		}
	}

	void upGD(int tmpx, int tmpy) {

		for (int i = 0; i < this.getAmplitude(); i++) {

			try {

				this.frame.setLocation(tmpx + i, tmpy - i);
				super.wait(getDelay());

			} catch (InterruptedException ex) {
			}

		}

	}

	void downDG(int tmpx, int tmpy) {

		for (int i = 0; i < this.getAmplitude(); i++) {

			try {

				this.frame.setLocation(tmpx - i, tmpy + i);
				super.wait(getDelay());

			} catch (InterruptedException ex) {
			}

		}
	}

	void upDG(int tmpx, int tmpy) {

		for (int i = 0; i < this.getAmplitude(); i++) {

			try {

				this.frame.setLocation(tmpx - i, tmpy - i);
				super.wait(getDelay());

			} catch (InterruptedException ex) {
			}

		}

	}

	public synchronized void ballanced(int frequence_) {
		int tmpx = (int) this.frame.getLocation().getX();
		int tmpy = (int) this.frame.getLocation().getY();

		douwnGD(tmpx, tmpy);
		upGD(tmpx, tmpy);
		downDG(tmpx, tmpy);
		upDG(tmpx, tmpy);
		this.frame.setLocation(tmpx, tmpy);
	}

	public synchronized void movingPli(int amplitude) {

		int tmpx = (int) this.frame.getLocation().getX();
		int tmpy = (int) this.frame.getLocation().getY();
		int width = this.frame.getWidth();
		int height = this.frame.getHeight();
		// int tmpxIV = tmpx;
		// int tmpyIV = tmpy;
		// int widthIV = width;
		// int heightIV = height;

		for (int pos = 0; pos < 160;) {
			try {

				this.frame.setLocation(tmpx + pos, tmpy + pos);
				super.wait(getDelay());
				this.frame.setSize(width - 2 * pos, height - 2 * pos);
				super.wait(getDelay());

			} catch (InterruptedException ex) {
			}
			pos = pos + 10;
		}

		tmpx = (int) this.frame.getLocation().getX();
		tmpy = (int) this.frame.getLocation().getY();
		width = this.frame.getWidth();
		height = this.frame.getHeight();
		for (int pos = 0; pos < 160;) {
			try {

				this.frame.setLocation(tmpx - pos, tmpy - pos);
				super.wait(getDelay());
				this.frame.setSize(width + 2 * pos, height + 2 * pos);
				super.wait(getDelay());

				// this.frame.setLocation(difwidth + tmpx, tmpy + difheidth);
				// super.wait(500);
			} catch (InterruptedException ex) {
			}
			pos = pos + 10;
		}

		// this.frame.setSize(widthIV, heightIV);
		// this.frame.setLocation(tmpxIV, tmpyIV);
		/*    */
	}

	public void run() {
		/** @todo Implement this java.lang.Runnable method */
// movingCloseit(  this.nx);
		// collabs( this.nx);
		synchronized (this) {

			if (this.yes == 1) {
				sayYES();
			}
			if (this.no == 1) {
				sayNO();
			}
			if (this.move == 1) {
				// ballanced(this.getFrequence());
				moveit(this.getFrequence());
			}
			if (this.pli == 1) {
				movingPli(this.getAmplitude());
			}

		}
	}

	/**
	 * Released
	 *
	 * @param e MouseEvent
	 */
	public void Released(MouseEvent e) {

	}
}
