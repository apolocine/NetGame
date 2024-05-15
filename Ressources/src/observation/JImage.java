package observation;

/**
 *
 * <p>Titre : NetGame</p>
 * <p>Description : jeux en r�seau Projet CDI</p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Soci�t� : drmdh</p>
 * @author hamid Madani
 * @version 1.0
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.IndexColorModel;
import java.util.Base64;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JImage extends JPanel implements ImageObserver {
	JPanel jPanel;
	Image img;
	MediaTracker mediaTracker;

	public JImage(String fileName) {
		img = Toolkit.getDefaultToolkit().getImage(fileName);
		initialize(img);
	}

	public JImage(int width, int height) {
		this(generatePixels(width, height, new Rectangle2D.Float(-2.0f, -1.2f, 3.2f, 2.4f)));

	}

	public JImage(byte[] imgSrc) {
		ImageIcon imgIcon = new ImageIcon(imgSrc);
		img = imgIcon.getImage();
//        img = Toolkit.getDefaultToolkit().createImage(imgSrc);
		initialize(img);
	}

	private void initialize(Image img) {
//        imgIcon = new ImageIcon(img);
//        imgIcon.setImageObserver(this);

		mediaTracker = new MediaTracker(this);
		mediaTracker.addImage(img, 0);

		try {
			mediaTracker.waitForID(0);
		} catch (Exception e) {
			// new com.lisocon.util.ErrorDialog(e).show();
		}

//        jPanel = new JPanel();

		this.setSize(img.getWidth(this), img.getHeight(this));
		this.setPreferredSize(new Dimension(img.getWidth(this), img.getHeight(this)));

//        this.add(jPanel);
//        this.setViewportView(jPanel);

		System.out.println(img.getWidth(this));

	}

//    public void update(Graphics g)
//    {
//        paint(g);
//    }

//    public void paint(Graphics g)
//    {
//        if ((img != null) && (mediaTracker != null) && (mediaTracker.checkID(0)))
//        {
//            System.out.println("drawing...");
//            g.drawImage(img, 0, 0, this);
//        }
//    }

	public void paintComponent(Graphics g) {
		if ((img != null) && (mediaTracker != null) && (mediaTracker.checkID(0))) {
			System.out.println("drawing2...");
			g.drawImage(img, 0, 0, this);
		}
	}

	public boolean imageUpdate(Image img, int flags, int x, int y, int width, int height) {
		System.out.println("img Update: (" + x + "," + y + ") <" + width + "x" + height + ">");
		System.out.println("Flags " + flags);
		return true;
	}

	final static public byte[] getBytes(String str) {
		byte[] tmp = new byte[str.length()];

		for (int i = 0; i < str.length(); i++) {
			tmp[i] = (byte) str.charAt(i);
		}
		return tmp;
	}

//  final static public byte[] getBytesBASE64(String str)
//  {      byte[] dec = null;
//      try {
//        dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
//      }
//      catch (IOException ex) {
//      }
//    return dec;
//  }

	public static byte[] getBytesBASE64(String str) {
		return Base64.getDecoder().decode(str);
	}

	final static private byte[] generatePixels(int w, int h, Rectangle2D.Float loc) {
		float xmin = loc.x;
		float ymin = loc.y;
		float xmax = loc.x + loc.width;
		float ymax = loc.y + loc.height;

		byte[] pixels = new byte[w * h];
		int pIx = 0;
		float[] p = new float[w];
		float q = ymin;
		float dp = (xmax - xmin) / w;
		float dq = (ymax - ymin) / h;

		p[0] = xmin;
		for (int i = 1; i < w; i++) {
			p[i] = p[i - 1] + dp;
		}

		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				int color = 1;
				float x = 0.0f;
				float y = 0.0f;
				float xsqr = 0.0f;
				float ysqr = 0.0f;
				do {
					xsqr = x * x;
					ysqr = y * y;
					y = 2 * x * y + q;
					x = xsqr - ysqr + p[c];
					color++;
				} while (color < 512 && xsqr + ysqr < 4);
				pixels[pIx++] = (byte) (color % 16);
			}
			q += dq;
		}
		return pixels;
	}

	private static ColorModel generateColorModel() {
		// Generate 16-color model
		byte[] r = new byte[16];
		byte[] g = new byte[16];
		byte[] b = new byte[16];

		r[0] = 0;
		g[0] = 0;
		b[0] = 0;
		r[1] = 0;
		g[1] = 0;
		b[1] = (byte) 192;
		r[2] = 0;
		g[2] = 0;
		b[2] = (byte) 255;
		r[3] = 0;
		g[3] = (byte) 192;
		b[3] = 0;
		r[4] = 0;
		g[4] = (byte) 255;
		b[4] = 0;
		r[5] = 0;
		g[5] = (byte) 192;
		b[5] = (byte) 192;
		r[6] = 0;
		g[6] = (byte) 255;
		b[6] = (byte) 255;
		r[7] = (byte) 192;
		g[7] = 0;
		b[7] = 0;
		r[8] = (byte) 255;
		g[8] = 0;
		b[8] = 0;
		r[9] = (byte) 192;
		g[9] = 0;
		b[9] = (byte) 192;
		r[10] = (byte) 255;
		g[10] = 0;
		b[10] = (byte) 255;
		r[11] = (byte) 192;
		g[11] = (byte) 192;
		b[11] = 0;
		r[12] = (byte) 255;
		g[12] = (byte) 255;
		b[12] = 0;
		r[13] = (byte) 80;
		g[13] = (byte) 80;
		b[13] = (byte) 80;
		r[14] = (byte) 192;
		g[14] = (byte) 192;
		b[14] = (byte) 192;
		r[15] = (byte) 255;
		g[15] = (byte) 255;
		b[15] = (byte) 255;

		return new IndexColorModel(4, 16, r, g, b);
	}

}
