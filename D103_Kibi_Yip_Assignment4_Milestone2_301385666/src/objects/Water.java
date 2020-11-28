package objects;

import static util.Util.random;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import processing.core.PApplet;
import processing.core.PVector;
import util.Util;

public class Water extends AllObjects {

	private float xStart;
	private float xSeed;
	private float ySeed;
	private PApplet pa;
	private int width, height;
	private boolean isShowing = false;

	public Water(float x, float y) {
		super(x, y);
		xStart = Util.random(10);
		xSeed = xStart;
		xSeed = random(10);
		pa = new PApplet();
		width = height = 100;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if (isShowing) {
			float noiseFactor;
			AffineTransform origin = g.getTransform();
			g.translate(pos.x, pos.y);
			for (int y = -height / 2; y <= height / 2; y += 5) {
				ySeed += 0.1;
				xSeed = xStart;
				for (int x = -width / 2; x <= width / 2; x += 5) {
					xSeed += 0.1;
					noiseFactor = pa.noise(xSeed, ySeed);

					AffineTransform at = g.getTransform();
					g.translate(x, y);
					g.rotate(noiseFactor * Util.radians(360));
					float diameter = noiseFactor * 35;
					int grey = (int) (150 + (noiseFactor * 105));
					int alph = (int) (20 + (noiseFactor * 105));
					g.setColor(new Color(grey, grey, grey, alph));
					g.fill(new Ellipse2D.Float(-diameter / 2, -diameter / 4, diameter, diameter / 2));
					g.setTransform(at);
				}
			}
			g.setTransform(origin);
		}
	}




	public void setIsShowing(boolean x) {
		isShowing = x;
	}

}
