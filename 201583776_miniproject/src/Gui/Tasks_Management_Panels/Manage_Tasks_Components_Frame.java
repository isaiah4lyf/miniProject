package Gui.Tasks_Management_Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Gui.Tasks_Management_Panels.Tasks_Dependencies.head_Label;
import Gui.Tasks_Management_Panels.Tasks_Dependencies.label2;
import Gui.Tasks_Management_Panels.Tasks_Dependencies.tableName;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Manage_Tasks_Components_Frame extends JFrame{

	private Graph<String,String> graph = null;
	private Vertex<String,String>[] vert = null;
	private Edge<String,String>[] edg = null;
	public Manage_Tasks_Components_Frame()
	{
		setLayout(new GridLayout(2,1));
		
		
		
		Object[] items = null;
		Object[] Dependency = null;
		try {
			graph = Graph.inParser("MIT.txt", true);
			vert = graph.vertices_array();
			items = new Object[vert.length];
			for(int i = 0; i<vert.length; i++)
			{
				items[i] = vert[i].getData();
			}
			
			edg = graph.edges_array();
			Dependency = new Object[edg.length];
			for(int i = 0; i<edg.length; i++)
			{
				Dependency[i] = edg[i].getV1() + " & " + edg[i].getV2();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JButton changeTask = new JButton("Change Task");
		JButton addComponent = new JButton("Add Component");
		JButton deleteComponent = new JButton("Delete Component");
		
		
		JComboBox jcb = new JComboBox(items);
		JComboBox jcb2 = new JComboBox(items);
		JComboBox jcb3 = new JComboBox(items);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(9,1));
		mainPanel.add(new label2(""));
		mainPanel.add(jcb);
		mainPanel.add(changeTask);
		
		mainPanel.add(new label2(""));
		mainPanel.add(jcb2);
		mainPanel.add(addComponent);
		
		mainPanel.add(new label2(""));
		mainPanel.add(jcb3);
		mainPanel.add(deleteComponent);
		
		
		
		
		ComponentsPanel compPanel = new ComponentsPanel();
		compPanel.setPreferredSize(new Dimension( 500,550));
		JScrollPane compScroll = new JScrollPane(compPanel);
		compScroll.setAutoscrolls(true);
		compScroll.setPreferredSize(new Dimension( 800,300));
		add(compScroll);
		add(mainPanel);
		
	}
	
	
	class label2 extends JLabel{
		Image bgImage2 = null;
		private int iWidth;
		private int iHeight;
		private String label;
		private String label2;
		private String label3;
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
	
	
	class ComponentsPanel extends JPanel
	{
		Image bgImage = null;
		private int iWidth2;
		private int iHeight2;
		public ComponentsPanel()
		{
			Graph<String, String> graph;

			try {
				graph = Graph.inParser("MIT.txt", true);
				
			    Edge<String,String>[] edg = graph.edges_array();
			    this.add(new tableName("Dependencies"));
				for(int i = 0; i<3; i++)
				{
					this.add(new label2(i+""));

				}
				this.setLayout(new GridLayout(edg.length+2,1));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		class tableName extends JLabel{
			Image bgImage2 = null;
			private int iWidth;
			private int iHeight;
			private String label;

			public tableName(String label)
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
			    Font font = new Font("Serif", Font.BOLD, 18);
			    g.setFont(font);
			    g.setColor(Color.BLUE);
			    String dep = "Dependencies";
				g.drawString("Dependencies",10, 15);
				g.drawLine(10, 15, 10+dep.length()*10-15, 15);
			}	
		}
		
		class label2 extends JLabel{
			Image bgImage2 = null;
			private int iWidth;
			private int iHeight;
			private String label;
			private String label2;
			private String label3;
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

}
