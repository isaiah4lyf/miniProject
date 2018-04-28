package Gui.Tasks_Management_Panels;

import static javax.swing.JOptionPane.showMessageDialog;

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
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.sun.glass.events.WindowEvent;

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
	public Manage_Tasks_Components_Frame(int SelectedTaskIndex)
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
		
		JComboBox jcb = new JComboBox(items);
		jcb.setSelectedIndex(SelectedTaskIndex);
		JComboBox jcb2 = new JComboBox(items);
		JComboBox jcb3 = new JComboBox(items);
		
		JButton changeTask = new JButton("Change Task");	
		JFrame fram = this;
		changeTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Manage_Tasks_Components_Frame frame = new Manage_Tasks_Components_Frame(jcb.getSelectedIndex());
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
            	
            	addTaskComponents("Task_Components.txt",jcb.getSelectedItem().toString(),jcb2.getSelectedItem().toString());
            	
            	Manage_Tasks_Components_Frame frame = new Manage_Tasks_Components_Frame(jcb.getSelectedIndex());
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
		
		
		ComponentsPanel compPanel = new ComponentsPanel(jcb.getSelectedItem().toString(),"Task_Components.txt");
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

	public void addTaskComponents(String fileName, String TaskName,String TaskNameToAdd)
	{
		 BufferedReader file = null;
			try {
				file = new BufferedReader(new FileReader(fileName));
			    String size =  file.readLine();;
		
			    String[] fileLines = new String[Integer.parseInt(size) + 1];
			    boolean TaskExist = false;
			    

			    for(int i = 0; i < Integer.parseInt(size); i++)
			    {
			    	String line = file.readLine();
			    	String[] lineTokens = line.split(" = ");
			    	if(TaskName.equals(lineTokens[0]))
			    	{
			    		String[] TaskComponents = lineTokens[1].split(",");
			    		int componentsNum = Integer.parseInt(TaskComponents[0]);
			    		
			    		String newComponents = "";
			    		boolean compExist = false;
			    		
			    		
			    		for(int j = 0; j< componentsNum; j++)
			    		{
			    			if(TaskNameToAdd.equals(TaskComponents[j+1]))
			    			{
			    				compExist = true;
			    				newComponents += TaskComponents[j+1] + ",";
			    			}
			    			else
			    			{
			    				newComponents += TaskComponents[j+1] + ",";
			    			}
			    		}
			    		if(compExist == false)
			    		{
			    			newComponents  += TaskNameToAdd + ",";
			    			componentsNum++;
			    		}
			    		
			    		TaskExist = true;
			    		String FinalComponents = componentsNum + "," + newComponents.substring(0, newComponents.length()-1);
			    		fileLines[i] = TaskName + " = " + FinalComponents;
			    	}
			    	else
			    	{
			    		fileLines[i] = line;

			    	}
			    	
			    }
			    if(TaskExist == false)
			    {
			    	int newSize = Integer.parseInt(size);
			    	fileLines[newSize] = TaskName + " = " + 1 + "," + TaskNameToAdd;
			    }
			    PrintWriter write = new PrintWriter(new File(fileName));
			    if(TaskExist == false)
			    {
				    write.println(Integer.parseInt(size) + 1);
				    for(int i = 0; i<Integer.parseInt(size) + 1; i++)
				    {
				    	write.println(fileLines[i]);
				    }
			    }
			    else
			    {
				    write.println(Integer.parseInt(size));
				    for(int i = 0; i<Integer.parseInt(size); i++)
				    {
				    	write.println(fileLines[i]);
				    }
			    }

			    write.close();
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
	}
	
	class ComponentsPanel extends JPanel
	{
		Image bgImage = null;
		private int iWidth2;
		private int iHeight2;
		public ComponentsPanel(String TaskName, String FileName)
		{

			String[] Componets = ReturnComponentsForTask(FileName,TaskName);
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
		public String[] ReturnComponentsForTask(String fileName,String TaskName)
		{
			 BufferedReader file = null;
			 String[] Components = null;
				try {
					file = new BufferedReader(new FileReader(fileName));
				    String size =  file.readLine();

				    for(int i = 0; i < Integer.parseInt(size); i++)
				    {
				    	String line = file.readLine();
				    	String[] lineTokens = line.split(" = ");
				    	if(TaskName.equals(lineTokens[0]))
				    	{
				    		String[] TaskComponents = lineTokens[1].split(",");
				    		int componentsNum = Integer.parseInt(TaskComponents[0]);
			    		
				    		Components = TaskComponents;
	
				    	}

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
				    
			return Components;
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
			    String dep = label;
				g.drawString(label,10, 15);
				g.drawLine(10, 15, 10+dep.length()*10-50, 15);
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
