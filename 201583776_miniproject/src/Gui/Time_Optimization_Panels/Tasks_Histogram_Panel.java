package Gui.Time_Optimization_Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import File_IO.Files_Management;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Tasks_Histogram_Panel extends JPanel{
	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;

	private Graph<String,String> graph = null;
	private Edge<String,String>[] edg_search = null;
	private Edge<String,String>[] edg = null;
	private Files_Management files_man;
	public Tasks_Histogram_Panel(String[] files)
	{
		files_man = new Files_Management();

		try {
			graph = files_man.graph_Reader(files[3], true);
			edg_search = graph.edges_array();
			
		
			for (int j=0; j<edg_search.length;j++)
			{
				  for (int k=j+1;k<edg_search.length;k++)
				  {
					    if (k!=j && edg_search[k].getV1() == edg_search[j].getV1())
					    {
					    	if(edg_search[j].getWeight() > edg_search[k].getWeight() )
					    	{
					    		graph.removeEdge(edg_search[k]);
					    	}
					    	else
					    	{
					    		graph.removeEdge(edg_search[j]);
					    	}
					    	
					    }      
				  }
			}
			edg = graph.edges_array();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		g.setColor(Color.CYAN);
		g.drawString("All Tasks and their duration",0 ,10);
		g.drawLine(10, this.getHeight() - 45, this.getWidth() - 10, this.getHeight() - 45);
		int individualWidth = (int)(((this.getWidth() - 40)/24) * 0.90);
		
		if(edg.length != 0)
		{
			int interval = (this.getWidth() - 40)/(edg.length);
			
			int initialPos = 10;
		    double maxPrice = edg[0].getWeight();

		    for(int i=0;i<edg.length;i++){
		        if(edg[i].getWeight()>maxPrice){
		        	maxPrice= edg[i].getWeight();
		        }

		    }
			for(int i = 0; i< edg.length; i++ )
			{
				int barHeight = (int)(((double)edg[i].getWeight() / (double)maxPrice) *(this.getHeight() - 65));
				
				g.fill3DRect(initialPos, this.getHeight() - 45 - barHeight, individualWidth, barHeight,true);
				g.drawString(edg[i].getV1().getData(), initialPos, this.getHeight() - 30);
				String priceString = String.valueOf((int)edg[i].getWeight());
				g.drawString(priceString+" days", initialPos , this.getHeight() - 45 - barHeight);
				initialPos += interval;
			}
		}

	}
}
