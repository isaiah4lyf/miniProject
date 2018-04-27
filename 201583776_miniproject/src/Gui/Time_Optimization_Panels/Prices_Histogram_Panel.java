package Gui.Time_Optimization_Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JPanel;

public class Prices_Histogram_Panel extends JPanel{

	public Prices_Histogram_Panel()
	{
		
	}
	protected void paintComponent(Graphics g)
	{
		double[] prices_ = Read_Prices("Prices.txt");
		String[] components = Return_Priced_Components("Prices.txt");
		g.setColor(Color.CYAN);
		g.drawLine(10, this.getHeight() - 45, this.getWidth() - 10, this.getHeight() - 45);
		int individualWidth = (int)(((this.getWidth() - 40)/24) * 0.90);
		int interval = (this.getWidth() - 40)/(components.length);
		
		int initialPos = 10;

	    double maxPrice = prices_[0];

	    for(int i=0;i<prices_.length;i++){
	        if(prices_[i]>maxPrice){
	        	maxPrice=prices_[i];
	        }

	    }
		for(int i = 0; i< prices_.length; i++ )
		{
			int barHeight = (int)(((double)prices_[i] / (double)maxPrice) *(this.getHeight() - 65));
			
			g.fill3DRect(initialPos, this.getHeight() - 45 - barHeight, individualWidth, barHeight,true);
			g.drawString(components[i], initialPos, this.getHeight() - 30);
			String priceString = String.valueOf(prices_[i]);
			g.drawString("R "+priceString, initialPos - 10, this.getHeight() - 45 - barHeight);
			initialPos += interval;
		}
		
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
