package Gui.Overall_Project_Optimization_Panels;

import java.awt.Color;
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

public class Components_Histogram_Panel extends JPanel{
	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private String[] files;
	private Files_Management files_man;
	
	/**
	 * Components in critical path
	 */
	
	public Components_Histogram_Panel(String[] files)
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
		String[] components = files_man.read_Crit_Path_Components(files[5]);
		double[] prices = files_man.read_Crit_Path_Components_Prices(components,files[0]);
		
		g.setColor(Color.CYAN);
		g.drawString("Components Required By Tasks in Optimized Critical Path",this.getWidth()/2 - 180,10);
		g.drawLine(this.getWidth()/2 - 180, 10, this.getWidth()/2 + 140, 10);
		g.drawLine(10, this.getHeight() - 45, this.getWidth() - 10, this.getHeight() - 45);
		int individualWidth = (int)(((this.getWidth() - 40)/24) * 0.90);
		
		if(components != null || prices != null)
		{
			int interval = (this.getWidth() - 40)/(components.length);
			
			int initialPos = 10;

		 
		    double maxDur = prices[0];

		    for(int i=0;i<prices.length;i++){
		        if(prices[i]>maxDur){
		        	maxDur=prices[i];
		        }

		    }

			for(int i = 0; i< prices.length; i++ )
			{
				int barHeight = (int)(((double)prices[i] / (double)maxDur) *(this.getHeight() - 65));
				
				g.fill3DRect(initialPos, this.getHeight() - 45 - barHeight, individualWidth, barHeight,true);
				g.drawString(components[i], initialPos, this.getHeight() - 30);
				String durString = String.valueOf(prices[i]);
				g.drawString("R"+durString, initialPos, this.getHeight() - 45 - barHeight);
				initialPos += interval;
			}
		}
	}
}
