package Gui.Cost_Optimization_Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import File_IO.Files_Management;
import Gui.TabbedPane;

public class Optimization_Histo_Pan extends JPanel{
	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private String[] files;
	
	private Files_Management files_Man;
	private String Selected_Task;
	public Optimization_Histo_Pan(TabbedPane prices_Histo,String[] files,JFrame fram,int selected_Task)
	{
		this.files = files;
		this.files_Man = new Files_Management();
		Components_Prices_Panel comp_Class = new Components_Prices_Panel(prices_Histo,files,selected_Task,fram);
		Selected_Task = comp_Class.getSelected_Task();
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

		
		
		double[] prices_ = files_Man.Return_Prices_For_Specific_Task(files[0],files[4],Selected_Task);
		String[] components = files_Man.ReturnComponentsForTask(files[4],Selected_Task);

		
		g.setColor(Color.CYAN);
		g.drawString("All components and Their Prices required by: '"+Selected_Task+"' with the total cost",0 ,10);
		g.drawLine(10, this.getHeight() - 45, this.getWidth() - 10, this.getHeight() - 45);
		int individualWidth = (int)(((this.getWidth() - 40)/24) * 0.90);
		
		if(components != null)
		{
			String[] newComponents = new String[components.length - 1];
			for(int i = 0; i < components.length-1; i++)
			{
				newComponents[i] = components[i+1];
			}
			int interval = (this.getWidth() - 40)/(components.length);
			
			int initialPos = 10;



		    double totalCost = 0;
			for(int i = 0; i< prices_.length; i++ )
			{
				totalCost += prices_[i];
			}
			for(int i = 0; i< newComponents.length; i++ )
			{
				
				int barHeight = (int)(((double)prices_[i] / (double)totalCost) *(this.getHeight() - 65));
				
				g.fill3DRect(initialPos, this.getHeight() - 45 - barHeight, individualWidth, barHeight,true);
				g.drawString(newComponents[i], initialPos, this.getHeight() - 30);
				String priceString = String.valueOf(prices_[i]);
				g.drawString("R "+priceString, initialPos - 10, this.getHeight() - 45 - barHeight);
				initialPos += interval;
			}

			int barHeight = (int)(((double)totalCost / (double)totalCost) *(this.getHeight() - 65));
			g.setColor(Color.RED);
			g.fill3DRect(initialPos - 10, this.getHeight() - 45 - barHeight, individualWidth, barHeight,true);
			g.drawString(Selected_Task, initialPos - 15, this.getHeight() - 30);
			String priceString = String.valueOf(totalCost);
			g.drawString("R "+priceString, initialPos -20, this.getHeight() - 45 - barHeight);

		}
	
	}

}
