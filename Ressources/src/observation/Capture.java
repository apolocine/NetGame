package observation;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

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
 * @version 1.0
 */

public class Capture {
	static int num = 0;

	public Capture() {
	}

	public void capture() {

		try {
			Toolkit tk = Toolkit.getDefaultToolkit();
			tk.sync();
			Rectangle ecran = new Rectangle(tk.getScreenSize());
			Robot robot = new Robot();
			robot.setAutoDelay(0);
			robot.setAutoWaitForIdle(false);
			BufferedImage image = robot.createScreenCapture(ecran);
			// You get a BufferedImage in TYPE_INT_BGR.

			File file = new File("captures/CapImageIO" + num + ".png");
			javax.imageio.ImageIO.write(image, "PNG", file);
		} catch (Exception e) {
			System.out.println(e);
		}

		num++;
	}

	/**
	 * docapNsave
	 *
	 * @param sec int
	 */
	public void docapNsave(int sec) {
		try {
			save(Bufferedcapture(), "captures/CapImageIO" + sec + "," + num + ".png");

		} catch (Exception e) {
			System.out.println(e);
		}
		num++;

	}

	public BufferedImage Bufferedcapture() {

		try {
			Toolkit tk = Toolkit.getDefaultToolkit();
			tk.sync();
			Rectangle ecran = new Rectangle(tk.getScreenSize());
			Robot robot = new Robot();
			robot.setAutoDelay(0);
			robot.setAutoWaitForIdle(false);
			BufferedImage image = robot.createScreenCapture(ecran);
			// You get a BufferedImage in TYPE_INT_BGR.

			return image;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void save(BufferedImage image, String filestr) {
		try {
			File file = new File(filestr);
			javax.imageio.ImageIO.write(image, "PNG", file);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
