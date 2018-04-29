package Gui.Time_Optimization_Panels;

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

public class Optimization_Histo_Pan extends JPanel{
	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private String[] files;
	public Optimization_Histo_Pan(String[] files)
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

		
		
		double[] prices_ = Read_Prices(files[0]);
		String[] components = Return_Priced_Components(files[0]);
		g.setColor(Color.CYAN);
		g.drawString("Prices of all components",10 ,10);
		g.drawLine(10, this.getHeight() - 45, this.getWidth() - 10, this.getHeight() - 45);
		int individualWidth = (int)(((this.getWidth() - 40)/24) * 0.90);
		int interval = (this.getWidth() - 40)/(components.length);
		
		int initialPos = 10;

		double task_Cost = 500;
	    double maxPrice = task_Cost;


		for(int i = 0; i< prices_.length; i++ )
		{
			int barHeight = (int)(((double)prices_[i] / (double)maxPrice) *(this.getHeight() - 65));
			
			g.fill3DRect(initialPos, this.getHeight() - 45 - barHeight, individualWidth, barHeight,true);
			g.drawString(components[i], initialPos, this.getHeight() - 30);
			String priceString = String.valueOf(prices_[i]);
			g.drawString("R "+priceString, initialPos - 10, this.getHeight() - 45 - barHeight);
			initialPos += interval;
		}

		int barHeight = (int)(((double)task_Cost / (double)maxPrice) *(this.getHeight() - 65));
		g.setColor(Color.RED);
		g.fill3DRect(initialPos - 10, this.getHeight() - 45 - barHeight, individualWidth, barHeight,true);
		g.drawString("Something", initialPos - 30, this.getHeight() - 30);
		String priceString = String.valueOf(task_Cost);
		g.drawString("R "+priceString, initialPos -20, this.getHeight() - 45 - barHeight);

	}
	
	public double[] Read_Prices(String priceFileName)
	{
	    BufferedReader file = null;
	    double[] prices_ = null;
		try {
			file = new BufferedReader(new FileReader(priceFileName));
		    String line;

		    String[] size = (file.readLine()).split("=");
		    prices_ = new double[Integer.parseInt(size[1])];
		    
		    for(int i = 0; i < prices_.length; i++)
		    {
		    	prices_[i] = Double.parseDouble((file.readLine()).split("=")[1]);
		    
		    }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	    try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return prices_;
	}
	
	public String[] Return_Priced_Components(String priceFileName)
	{
	    BufferedReader file = null;
	    String[] components = null;
		try {
			file = new BufferedReader(new FileReader(priceFileName));
		    String line;

		    String[] size = (file.readLine()).split("=");
		    components = new String[Integer.parseInt(size[1])];
		    
		    for(int i = 0; i < components.length; i++)
		    {
		    	components[i] = (file.readLine()).split("=")[0];
		    
		    }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	    try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return components;
	}
	
}
