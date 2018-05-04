package Gui.Overall_Project_Optimization_Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import File_IO.Files_Management;

public class Calculations_Panel extends JPanel{
	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private String[] files;
	private Files_Management files_man;
	
	public Calculations_Panel(String[] files)
	{
		this.files = files;
		files_man = new Files_Management();
	}
	
	protected void paintComponent(Graphics g)
	{
		try 
		{
			bgImage = ImageIO.read(new File("Files/Images/df.jpg"));
		    iWidth2 = bgImage.getWidth((ImageObserver) this)/2;
		    iHeight2 = bgImage.getHeight((ImageObserver) this)/2;
		}
		catch (IOException e)
		{e.printStackTrace();}
		
		g.drawImage(bgImage,0,0,(ImageObserver) this);
	    Font font = new Font("Serif", Font.PLAIN, 18);
	    g.setFont(font);
		String[] cal = files_man.read_Calculations(files[5]);
		g.setColor(Color.BLACK);
		g.drawString("Optimized Critical Path Total Cost and Duration", this.getWidth()/2 -150, 30);
		g.drawLine(this.getWidth()/2 -150, 30, this.getWidth()/2 + 200, 30);
		if(cal != null)
		{
			
			
			g.drawString(cal[0] + ":", this.getWidth()/2 -100, 80);
			g.drawString(cal[2] + ":", this.getWidth()/2 -100, 130);
			g.setColor(Color.RED);
			g.drawString("R "+cal[1], this.getWidth()/2 + 50, 80);
			g.drawString(cal[3] + " days", this.getWidth()/2 + 50, 130);
		}
	}


}
