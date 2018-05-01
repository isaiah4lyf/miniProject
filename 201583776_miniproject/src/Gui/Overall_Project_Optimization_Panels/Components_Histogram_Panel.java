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

public class Components_Histogram_Panel extends JPanel{
	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private String[] files;
	
	/**
	 * Components in critical path
	 */
	
	public Components_Histogram_Panel(String[] files)
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
		String[] components = read_Crit_Path_Components(files[5]);
		double[] prices = read_Crit_Path_Components_Prices(components,files[0]);
		
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

	
	
	public String[] read_Crit_Path_Components(String fileName)
	{
		BufferedReader file = null;
		String[] cal = null;
		try 
		{
			file = new BufferedReader(new FileReader(fileName));
			String line  = file.readLine();
			if(line != null)
			{
				
				String[] lineTokens =  line.split(",");
				cal = new String[lineTokens.length];
				for(int i = 0; i < lineTokens.length; i++)
				{
					
					cal[i] = lineTokens[i];
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
	
	
	public double[] read_Crit_Path_Components_Prices(String[] components,String fileName)
	{
		BufferedReader file = null;
		double[] prices = null;
		try 
		{
			file = new BufferedReader(new FileReader(fileName));
			String line  = file.readLine();
			int size = Integer.parseInt(line.split("=")[1]);
			String[] lines = new String[size];
			if(components != null)
			{
				
				prices = new double[components.length];
				for(int i = 0; i < size; i++)
				{
					lines[i] = file.readLine();
				}
				
				for(int i = 0; i < components.length; i++)
				{
					for(int j = 0; j<lines.length; j++)
					{
						String component = lines[j].split(" = ")[0];
						if(components[i].equals(component))
						{
							prices[i] = Double.parseDouble(lines[j].split(" = ")[1]);
						}
					}
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
		return prices;	
	}
}
