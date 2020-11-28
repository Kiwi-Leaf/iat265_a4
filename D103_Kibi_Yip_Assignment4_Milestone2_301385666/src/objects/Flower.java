package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import util.Util;

public class Flower extends AllObjects {
	// This class contains a recursive function
	private Ellipse2D.Double petal,center;
	private Color flowerColor,centerColor;
	private float angle = 0;
	private float length;

	

	public Flower(float x, float y, Color flowerColor,float angle, float length) {
		super(x, y);
		this.flowerColor = flowerColor;
		this.angle =angle;
		this.length = length;
		petal = new Ellipse2D.Double(-length / 10, -length, length / 5, length);
		centerColor=new Color (240,230,56);
		center= new Ellipse2D.Double(-10, -10, 20, 20);

	}





	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
		
		AffineTransform origin = g.getTransform();
		g.translate(pos.x, pos.y);
		g.setColor(flowerColor);
		for (int i = 0; i < 20; i++) {
			g.rotate(Math.toRadians(angle + 36 * i));
			g.fill(petal);
		}
		
		g.setColor(centerColor);
		g.fill(center);
		g.setTransform(origin);
		if (length >65) {
			// draw(g, flowerColor.darker(), angle + 5, length - 10);
			new Flower(pos.x, pos.y, flowerColor.darker(),angle-5, length - 5).draw(g);
			;
		}

	}

}
