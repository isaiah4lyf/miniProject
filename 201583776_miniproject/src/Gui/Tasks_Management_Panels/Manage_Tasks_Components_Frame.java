package Gui.Tasks_Management_Panels;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import File_IO.Files_Management;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Manage_Tasks_Components_Frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graph<String,String> graph = null;
	private Graph<String,String> graph2 = null;
	private Vertex<String,String>[] vert = null;
	private Vertex<String,String>[] vert2 = null;
	private Files_Management files_man;
	public Manage_Tasks_Components_Frame(int SelectedTaskIndex,String[] files)
	{
		files_man = new Files_Management();
		setLayout(new GridLayout(2,1));
		Object[] tasks = null;
		Object[] components = null;
		try {
			graph = files_man.graph_Reader(files[3], true);
			graph2 = files_man.graph_Reader(files[1], true);
			vert = graph.vertices_array();
			tasks = new Object[vert.length];
			for(int i = 0; i<vert.length; i++)
			{
				tasks[i] = vert[i].getData();
			}
			
			vert2 = graph2.vertices_array();
			components = new Object[vert2.length];
			for(int i = 0; i<vert2.length; i++)
			{
				components[i] = vert2[i].getData();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JComboBox jcb = new JComboBox(tasks);
		if(tasks.length != 0)
		{
			jcb.setSelectedIndex(SelectedTaskIndex);
		}

		JComboBox jcb2 = new JComboBox(components);
		JComboBox jcb3 = new JComboBox(components);
		
		JButton changeTask = new JButton("Change Task");	
		JFrame fram = this;
		changeTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Manage_Tasks_Components_Frame frame = new Manage_Tasks_Components_Frame(jcb.getSelectedIndex(),files);
        		frame.setTitle("Manage Components For a Specific Task");
        		frame.setSize(350,450);
        		frame.setResizable(false);
        		frame.setLocation(550,200);

        		Image icon = null;
        		try 
        		{
        			icon = ImageIO.read(new File("pj.png"));}
        		catch (IOException ex)
        		{ex.printStackTrace();}
        		frame.setVisible(true);
        		frame.setIconImage(icon);
            	fram.setVisible(false);
        		fram.dispose();
        		
            }
        });
		
		
		JButton addComponent = new JButton("Add Component");
		addComponent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	files_man.addTaskComponents(files[4],jcb.getSelectedItem().toString(),jcb2.getSelectedItem().toString());
            	
            	Manage_Tasks_Components_Frame frame = new Manage_Tasks_Components_Frame(jcb.getSelectedIndex(),files);
        		frame.setTitle("Manage Components For a Specific Task");
        		frame.setSize(350,450);
        		frame.setResizable(false);
        		frame.setLocation(550,200);

        		Image icon = null;
        		try 
        		{
        			icon = ImageIO.read(new File("pj.png"));}
        		catch (IOException ex)
        		{ex.printStackTrace();}
        		frame.setVisible(true);
        		frame.setIconImage(icon);
            	fram.setVisible(false);
        		fram.dispose();
        		
            }
        });
		JButton deleteComponent = new JButton("Delete Component");
		

		
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
		
		if(tasks.length != 0)
		{
			ComponentsPanel compPanel = new ComponentsPanel(jcb.getSelectedItem().toString(),files[4]);
			compPanel.setPreferredSize(new Dimension( 500,550));
			JScrollPane compScroll = new JScrollPane(compPanel);
			compScroll.setAutoscrolls(true);
			compScroll.setPreferredSize(new Dimension( 800,300));
			add(compScroll);
			add(mainPanel);	
		}

		
	}
	
	
	class label2 extends JLabel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Image bgImage2 = null;
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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Image bgImage = null;
		public ComponentsPanel(String TaskName, String FileName)
		{

			String[] Componets = files_man.ReturnComponentsForTask(FileName,TaskName);
		    this.add(new tableName("Components Required by '" + TaskName +"'"));
		    if(Componets != null)
		    {
				for(int i = 0; i<Componets.length - 1; i++)
				{
					this.add(new label2(i+ 1 +". "+Componets[i + 1]));

				}
				this.setLayout(new GridLayout(Componets.length+10,1));
		    }
		    else
		    {
		    	this.setLayout(new GridLayout(1,1));
		    }

			

		}

		
		
		
		class tableName extends JLabel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Image bgImage2 = null;
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
				}
				catch (IOException e)
				{e.printStackTrace();}
				
				g.drawImage(bgImage2,0,0,(ImageObserver) this);
			    Font font = new Font("Serif", Font.BOLD, 18);
			    g.setFont(font);
			    g.setColor(Color.BLUE);
			    String dep = label;
				g.drawString(label,10, 15);
				g.drawLine(10, 15, 10+dep.length()*10-50, 15);
			}	
		}
		
		class label2 extends JLabel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Image bgImage2 = null;

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
			}
			catch (IOException e)
			{e.printStackTrace();}
			
			g.drawImage(bgImage,0,0,(ImageObserver) this);
		}	
	}

}
