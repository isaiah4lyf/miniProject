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

public class Calculations_Panel extends JPanel{
	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private String[] files;
	
	public Calculations_Panel(String[] files)
	{
		this.files = files;
	}
	
	protected void paintComponent(Graphics g)
	{
		try 
		{
			bgImage = ImageIO.read(new File("df.jpg"));
		    iWidth2 = bgImage.getWidth((ImageObserver) this)/2;
		    iHeight2 = bgImage.getHeight((ImageObserver) this)/2;
		}
		catch (IOException e)
		{e.printStackTrace();}
		
		g.drawImage(bgImage,0,0,(ImageObserver) this);
	    Font font = new Font("Serif", Font.BOLD, 18);
	    g.setFont(font);
		String[] cal = read_Calculations(files[5]);
		if(cal != null)
		{
			g.setColor(Color.BLUE);
			g.drawString("Optimized Critical Path Total Cost and Duration", this.getWidth()/2 -200, 30);
			g.drawLine(this.getWidth()/2 -200, 30, this.getWidth()/2 + 185, 30);
			g.drawString(cal[0] + ":", this.getWidth()/2 -100, 80);
			g.drawString(cal[2] + ":", this.getWidth()/2 -100, 130);
			g.setColor(Color.RED);
			g.drawString("R "+cal[1], this.getWidth()/2 + 50, 80);
			g.drawString(cal[3] + " days", this.getWidth()/2 + 50, 130);
		}
	}

	public String[] read_Calculations(String fileName)
	{
		BufferedReader file = null;
		String[] cal = null;
		try 
		{
			file = new BufferedReader(new FileReader(fileName));
			file.readLine();
			file.readLine();
			String[] lineTokens =  (file.readLine()).split(",");
			String[] cost_n_Value = lineTokens[0].split(" = ");
			String[] duration_n_Value = lineTokens[1].split(" = ");
			cal = new String[4];
			cal[0] = cost_n_Value[0];
			cal[1] = cost_n_Value[1];
			cal[2] = duration_n_Value[0];
			cal[3] = duration_n_Value[1];
		}
		catch(Exception ex)
		{
			
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
