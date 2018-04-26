package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Components_Dependencies extends JPanel{
	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	public Components_Dependencies()
	{
		Graph<String, String> graph;

		try {
			graph = Graph.inParser("MIT.txt", true);
			
		    Edge<String,String>[] edg = graph.edges_array();
		    this.add(new label2("Heading goes here"));
			for(int i = 0; i<edg.length - 8; i++)
			{
				this.add(new label2(i+1+". "+edg[i].getV1()+ ": Depends on -> "+ edg[i].getV2()+" "+edg[i].getWeight()+"X"));
			}
			this.setLayout(new GridLayout(edg.length,1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	class label2 extends JLabel{
		Image bgImage2 = null;
		private int iWidth;
		private int iHeight;
		private String label;
		public label2(String label)
		{
			this.label = label;
		}
		protected void paintComponent(Graphics g)
		{
			try 
			{
				bgImage2 = ImageIO.read(new File("df.jpg"));
			    iWidth = bgImage2.getWidth((ImageObserver) this)/2;
			    iHeight = bgImage2.getHeight((ImageObserver) this)/2;
			}
			catch (IOException e)
			{e.printStackTrace();}
			
			g.drawImage(bgImage2,0,0,(ImageObserver) this);
		    Font font = new Font("Serif", Font.ITALIC, 18);
		    g.setFont(font);
		    g.setColor(Color.BLUE);
			g.drawString(label, 5, 25);
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
	}	
}