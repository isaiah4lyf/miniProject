package Gui;

import graph.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.prism.BasicStroke;


public class TaskPanel extends JPanel{

	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	
	public TaskPanel()
	{

	}
	
	
    private final int ARR_SIZE = 6;

    void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

	protected void paintComponent(Graphics g)
	{
	    Graphics2D g2 = (Graphics2D) g;
	    Graphics2D g3 = (Graphics2D) g;
	    Font font1 = new Font("Serif", Font.BOLD, 40);
	    g3.setFont(font1);
	    Font font = new Font("Serif", Font.ROMAN_BASELINE, 18);
	    g2.setFont(font);
		try 
		{
			bgImage = ImageIO.read(new File("hero11.jpg"));
		    iWidth2 = bgImage.getWidth((ImageObserver) this)/2;
		    iHeight2 = bgImage.getHeight((ImageObserver) this)/2;
		}
		catch (IOException e)
		{e.printStackTrace();}

		 //int x2 = this.getWidth()/2 - iWidth2;
		 ///int y2 = this.getParent().getHeight()/2 - iHeight2;
		 g.drawImage(bgImage,0,0,(ImageObserver) this);
		Graph<String, String> graph;
		String[][] storeCoordinates = null;
		try {
			graph = Graph.inParser("MIT.txt", true);
			Vertex<String,String>[] vert = graph.vertices_array();
			storeCoordinates = new String[vert.length][3];
			int y = 80;
			int x = 50;
			int count = 1;
			int rows = 1;
			g2.setColor(Color.BLACK);
			for(Vertex<String,String> v : graph.vertices_array())
			{
				
				if(count % 6 == 0) 
				{
					y += 150;
					x = rows * 50;
					rows++;
				}
				storeCoordinates[count - 1][0] =  v.getData();
				storeCoordinates[count - 1][1] =  String.valueOf(x);
				storeCoordinates[count - 1][2] =  String.valueOf(y);
				g2.drawString(v.getData(), x, y);
			    g2.drawOval(x - 10, y - 25, 80, 40);
				
				g.drawLine(x, y, x, y);
				x += 200;
				count++;
			}
			g3.setColor(Color.red);
			//Font d = new Font();
			//g.setFont(Font.ITALIC);
			for(Edge<String,String> e : graph.edges_array()){
				for(int i = 0; i<storeCoordinates.length; i++)
				{
					if(e.getV1().getData() == storeCoordinates[i][0])
					{
						for(int j = 0; j<storeCoordinates.length; j++)
						{
							if(e.getV2().getData() == storeCoordinates[j][0])
							{
								drawArrow(g3,Integer.parseInt(storeCoordinates[i][1]), Integer.parseInt(storeCoordinates[i][2]),Integer.parseInt(storeCoordinates[j][1]), Integer.parseInt(storeCoordinates[j][2]));
								//g3.drawLine();
								//g.drawString("some", Integer.parseInt(storeCoordinates[i][1])/2, Integer.parseInt(storeCoordinates[i][1])/2);
							}
						}
						
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
