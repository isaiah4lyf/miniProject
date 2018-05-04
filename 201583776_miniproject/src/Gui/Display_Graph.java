package Gui;

import graph.*;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

import File_IO.Files_Management;



public class Display_Graph extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image bgImage = null;
	private String fileName;
	private Files_Management files_man;
	public Display_Graph(String fileName)
	{
		this.fileName = fileName;
		this.files_man = new Files_Management();
	}
    private final int ARR_SIZE = 6;
    
    void draw_Arrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

	protected void paintComponent(Graphics g)
	{
	    Graphics2D g2 = (Graphics2D) g;
	    Graphics2D g3 = (Graphics2D) g;
	    Font font1 = new Font("Serif", Font.BOLD, 18);
	    g3.setFont(font1);
	    Font font = new Font("Serif", Font.ROMAN_BASELINE, 18);
	    g2.setFont(font);
		try 
		{
			bgImage = ImageIO.read(new File("Files/Images/df.jpg"));
		}
		catch (IOException e)
		{e.printStackTrace();}

		g.drawImage(bgImage,0,0,(ImageObserver) this);
		Graph<String, String> graph;
		String[][] vertices_coordinates = null;
		try {
			graph = files_man.graph_Reader(fileName, true);
			Vertex<String,String>[] vert = graph.return_Vertices_Array();
			vertices_coordinates = new String[vert.length][3];
			int y = 80;
			int x = 50;
			int count = 1;
			int rows = 1;
			g2.setColor(Color.BLACK);
			for(Vertex<String,String> v : graph.return_Vertices_Array())
			{
				if(count % 4 == 0) 
				{
					y += 150;
					x = rows * 50;
					rows++;
				}
				vertices_coordinates[count - 1][0] =  v.getData();
				vertices_coordinates[count - 1][1] =  String.valueOf(x);
				vertices_coordinates[count - 1][2] =  String.valueOf(y);
				g2.drawString(v.getData(), x, y+10);
			    g2.drawOval(x - 10, y - 25, 140, 60);
				
				g.drawLine(x, y, x, y);
				x += 350;
				count++;
			}
			g3.setColor(Color.red);
			for(Edge<String,String> e : graph.return_Edges_Array()){
				for(int i = 0; i<vertices_coordinates.length; i++)
				{
					if(e.getV1().getData() == vertices_coordinates[i][0])
					{
						for(int j = 0; j<vertices_coordinates.length; j++)
						{
							if(e.getV2().getData() == vertices_coordinates[j][0])
							{
								
								draw_Arrow(g3,Integer.parseInt(vertices_coordinates[i][1]) + 130, Integer.parseInt(vertices_coordinates[i][2])+5,Integer.parseInt(vertices_coordinates[j][1]) - 10, Integer.parseInt(vertices_coordinates[j][2]));
								g2.drawString(String.valueOf((int)e.getWeight()),Integer.parseInt(vertices_coordinates[j][1]) - 40, Integer.parseInt(vertices_coordinates[j][2]));

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
