package Gui.Components_Management_Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import File_IO.Files_Management;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Components_Dependencies extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image bgImage = null;
	private Files_Management files_man;

	public Components_Dependencies(String[] files)
	{
		files_man = new Files_Management();
		Graph<String, String> graph;

		try {
			graph = files_man.graph_Reader(files[1], true);
			
		    Edge<String,String>[] edg = graph.return_Edges_Array();
		    this.add(new tableName("Dependencies"));
		    this.add(new head_Label("Required Component","Component","Required Component Quantity"));
			for(int i = 0; i<edg.length; i++)
			{
				this.add(new label2(i+1+". "+edg[i].getV1().getData(),edg[i].getV2().getData(),String.valueOf((int)edg[i].getWeight())));

			}
			this.setLayout(new GridLayout(edg.length+2,1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	class tableName extends JLabel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Image bgImage2 = null;
		
		private String label;

		public tableName(String label)
		{
			this.label = label;
		}
		protected void paintComponent(Graphics g)
		{
			try 
			{
				bgImage2 = ImageIO.read(new File("Files/Images/df.jpg"));
			}
			catch (IOException e)
			{e.printStackTrace();}
			
			g.drawImage(bgImage2,0,0,(ImageObserver) this);
		    Font font = new Font("TimesRoman", Font.PLAIN, 18);
		    g.setFont(font);
		    g.setColor(Color.black);
		    String dep = label;
			g.drawString(label,280, 15);
			g.drawLine(280, 15, 280+dep.length()*10-20, 15);
		}	
	}
	class head_Label extends JLabel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Image bgImage2 = null;
		private String label;
		private String label2;
		private String label3;
		public head_Label(String label,String label2, String label3)
		{
			this.label = label;
			this.label2 = label2;
			this.label3 = label3;
		}
		protected void paintComponent(Graphics g)
		{
			try 
			{
				bgImage2 = ImageIO.read(new File("Files/Images/df.jpg"));
			}
			catch (IOException e)
			{e.printStackTrace();}
			
			g.drawImage(bgImage2,0,0,(ImageObserver) this);
		    Font font = new Font("TimesRoman", Font.PLAIN, 18);
		    g.setFont(font);
		    g.setColor(Color.black);
			g.drawString(label, 5, 15);
			g.drawString(label2, 200, 15);
			g.drawString(label3, 380, 15);
		}	
	}
	class label2 extends JLabel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Image bgImage2 = null;
		private String label;
		private String label2;
		private String label3;
		public label2(String label,String label2, String label3)
		{
			this.label = label;
			this.label2 = label2;
			this.label3 = label3;
		}
		protected void paintComponent(Graphics g)
		{
			try 
			{
				bgImage2 = ImageIO.read(new File("Files/Images/df.jpg"));
			}
			catch (IOException e)
			{e.printStackTrace();}
			
			g.drawImage(bgImage2,0,0,(ImageObserver) this);
		    Font font = new Font("Serif", Font.ITALIC, 18);
		    g.setFont(font);
		    g.setColor(Color.black);
			g.drawString(label, 5, 25);
			g.drawString(label2, 200, 25);
			g.drawString(label3, 480, 25);
		}	
	}
	protected void paintComponent(Graphics g)
	{
		try 
		{
			bgImage = ImageIO.read(new File("Files/Images/df.jpg"));
		}
		catch (IOException e)
		{e.printStackTrace();}
		
		g.drawImage(bgImage,0,0,(ImageObserver) this);
	}	
}
