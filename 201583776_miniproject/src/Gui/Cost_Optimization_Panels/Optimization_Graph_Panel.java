package Gui.Cost_Optimization_Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class Optimization_Graph_Panel extends JPanel{
	 double f(double x) {
	        return Math.sin(x);
	    }

	    double gCos(double y) {
	        return Math.cos(y);
	    }

	    protected void paintComponent(Graphics g) 
	    {
	        super.paintComponent(g);

	        g.drawLine(10, 100, 380, 100);
	        g.drawLine(200, 30, 200, 190);

	        g.drawLine(380, 100, 370, 90);
	        g.drawLine(380, 100, 370, 110);
	        g.drawLine(200, 30, 190, 40);
	        g.drawLine(200, 30, 210, 40);

	        g.drawString("X", 360, 80);
	        g.drawString("Y", 220, 40);

	        Polygon p = new Polygon();
	        Polygon p2 = new Polygon();

	       for (int x = -170; x <= 170; x++) {
	            p.addPoint(x + 200, 100 - (int) (50 * f((x / 100.0) * 2
	                    * Math.PI)));

	        }

	        for (int x = -170; x <= 170; x++) {
	            p2.addPoint(x + 200, 100 - (int) (50 * gCos((x / 100.0) * 2
	                    * Math.PI)));

	        }

	        g.setColor(Color.red);
	        g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
	        g.drawString("-2\u03c0", 95, 115);
	        g.drawString("2\u03c0", 305, 115);
	        g.drawString("0", 200, 115);

	        g.setColor(Color.blue);
	        g.drawPolyline(p2.xpoints, p2.ypoints, p2.npoints);

	    }

}
