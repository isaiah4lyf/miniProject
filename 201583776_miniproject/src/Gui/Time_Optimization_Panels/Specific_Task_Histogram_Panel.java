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

public class Specific_Task_Histogram_Panel extends JPanel{
	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private String[] files;
	
	private Graph<String,String> graph = null;
	private Edge<String,String>[] edg_search = null;
	private Edge<String,String>[] edg = null;
	private Files_Management files_man;
	private Vertex<String,String>[] vert = null;
	private int Select_T_1;
	private int Select_T_2;
	public Specific_Task_Histogram_Panel(String[] files,int Select_T_1,int Select_T_2)
	{
		this.Select_T_1 = Select_T_1;
		this.Select_T_2 = Select_T_2;
		files_man = new Files_Management();

		try {
			graph = files_man.graph_Reader(files[3], true);
			vert = graph.return_Vertices_Array();
			if(vert.length != 0)
			{
				edg = graph.dijkstra_Shortest_Path(vert[Select_T_1],vert[Select_T_2]);
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	protected void paintComponent(Graphics g)
	{
		try 
		{
			bgImage = ImageIO.read(new File("Files/Images/Hist.jpg"));
		    iWidth2 = bgImage.getWidth((ImageObserver) this)/2;
		    iHeight2 = bgImage.getHeight((ImageObserver) this)/2;
		}
		catch (IOException e)
		{e.printStackTrace();}
		
		g.drawImage(bgImage,0,0,(ImageObserver) this);

		g.setColor(Color.BLUE);
		g.drawLine(10, this.getHeight() - 45, this.getWidth() - 10, this.getHeight() - 45);
		if(vert.length != 0)
		{
			g.drawString("Criticat path between '"+vert[Select_T_1].getData()+"' and '"+vert[Select_T_2].getData()+"'",0 ,10);
			int individualWidth = (int)(((this.getWidth() - 40)/24) * 0.90);
			
			if(edg.length != 0)
			{
				int interval = (this.getWidth() - 40)/(edg.length);
				
				int initialPos = 10;
				double totalCost = 0;
			  

			    for(int i=0;i<edg.length;i++){
			       
			    	totalCost += edg[i].getWeight();
			        

			    }
				  double maxPrice = totalCost;
				for(int i = 0; i< edg.length; i++ )
				{
					int barHeight = (int)(((double)edg[i].getWeight() / (double)maxPrice) *(this.getHeight() - 65));
					
					g.fill3DRect(initialPos, this.getHeight() - 45 - barHeight, individualWidth, barHeight,true);
					g.drawString(edg[i].getV1().getData(), initialPos -10, this.getHeight() - 30);
					String priceString = String.valueOf((int)edg[i].getWeight());
					g.drawString(priceString+" days", initialPos - 10 , this.getHeight() - 45 - barHeight);
					initialPos += interval;
				}
				
				int barHeight = (int)(((double)totalCost / (double)totalCost) *(this.getHeight() - 65));
				g.setColor(Color.RED);
				g.fill3DRect(initialPos - 40, this.getHeight() - 45 - barHeight, individualWidth, barHeight,true);
				g.drawString(edg[edg.length - 1].getV2().getData(), initialPos - 45, this.getHeight() - 30);
				String priceString = String.valueOf((int)totalCost);
				g.drawString(priceString+" days", initialPos -50, this.getHeight() - 45 - barHeight);

			}
	
		}

	}
}
