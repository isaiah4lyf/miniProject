package Gui.Overall_Project_Optimization_Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Tasks_Crit_Histogram_Panel extends JPanel{

	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private String[] files;
	/**
	 * Components Histogram
	 */
	public Tasks_Crit_Histogram_Panel(String[] files)
	{
		this.files = files;
	}
	
    private final int ARR_SIZE = 6;
    void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.setColor(Color.RED);
        g.transform(at);
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
		}
		catch (IOException e)
		{e.printStackTrace();}

		g.drawImage(bgImage,0,0,(ImageObserver) this);
		Graph<String, String> graph;
		String[][] storeCoordinates = null;
		int[] dur = null;
		try {
	

			dur = read_Crit_Path_Duration(files[5]);
			int y = 80;
			int x = 50;
			int count = 1;
			int rows = 1;
			g2.setColor(Color.BLACK);
			g2.drawString("Optimized Critical Path",this.getWidth()/2 - 100,20);
			g2.drawLine(this.getWidth()/2 - 100, 20, this.getWidth()/2 + 70, 20);
			String[] vert = read_Crit_Path_Tasks(files[5]);
			if(vert != null)
			{
				
				storeCoordinates = new String[vert.length][3];
				for(String v : vert)
				{
					if(count % 6 == 0) 
					{
						y += 150;
						x = rows * 50;
						rows++;
					}
					storeCoordinates[count - 1][0] =  v;
					storeCoordinates[count - 1][1] =  String.valueOf(x);
					storeCoordinates[count - 1][2] =  String.valueOf(y);
					g2.drawString(v, x, y + 5);
				    g2.drawOval(x - 10, y - 30, 120, 60);
					
					
					x += 300;
					count++;
				}
				
				//g3.setColor(Color.red);
				for(int i = 0; i < storeCoordinates.length - 1; i++ ){
					
					drawArrow(g3,Integer.parseInt(storeCoordinates[i][1]) + 110, Integer.parseInt(storeCoordinates[i][2]),Integer.parseInt(storeCoordinates[i+1][1]) - 10, Integer.parseInt(storeCoordinates[i+1][2]));
					g2.drawString(String.valueOf(dur[i]) + " days", Integer.parseInt(storeCoordinates[i][1]) + 170, Integer.parseInt(storeCoordinates[i][2]));
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	public String[] read_Crit_Path_Tasks(String FileName)
	{
		BufferedReader file = null;
		String[] cal = null;
		try 
		{
			file = new BufferedReader(new FileReader(FileName));
			if(file.readLine() != null)
			{
				
				String[] lineTokens =  (file.readLine()).split(",");
				cal = new String[lineTokens.length];
				for(int i = 0; i < lineTokens.length; i++)
				{
					String[] task = lineTokens[i].split(" = ");
					cal[i] = task[0];
				}
			}


		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;	
	}
	
	public int[] read_Crit_Path_Duration(String FileName)
	{
		BufferedReader file = null;
		int[] cal = null;
		try 
		{
			file = new BufferedReader(new FileReader(FileName));
			if(file.readLine() != null)
			{
				String[] lineTokens =  (file.readLine()).split(",");
				cal = new int[lineTokens.length];
				for(int i = 0; i < lineTokens.length; i++)
				{
					String[] task = lineTokens[i].split(" = ");
					cal[i] = Integer.parseInt(task[1]);
				}

			}
	

		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;	
	}
}
