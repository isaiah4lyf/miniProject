package Gui.Time_Optimization_Panels;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;

import File_IO.Files_Management;
import Gui.Jframe;

import graph.Graph;
import graph.Vertex;

public class Tasks_Selection_Panel extends JPanel{
	private Graph<String,String> graph = null;
	private Vertex<String,String>[] vert = null;
	private Graph<String,String> graph2 = null;
	private Vertex<String,String>[] vert2 = null;
	
	private Files_Management files_man;
	public Tasks_Selection_Panel(String[] files,int Select_T_1,int Select_T_2,JFrame fram)
	{
		files_man = new Files_Management();
		Object[] items = null;

		try {
			graph = files_man.graph_Reader(files[3], true);
			vert = graph.vertices_array();
			items = new Object[vert.length];

			for(int i = 0; i<items.length; i++)
			{
				items[i] = vert[i];
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel label1 = new label("Calculate Critical Path Duration between Tasks");
		
		JComboBox jcb = new JComboBox(items);
		JComboBox jcb2 = new JComboBox(items);
		if(items.length != 0)
		{
			jcb.setSelectedIndex(Select_T_1);
			jcb2.setSelectedIndex(Select_T_2);
		}

	    Font font = new Font("Serif", Font.ITALIC, 18);
	    jcb.setFont(font);
	    jcb2.setFont(font);
		JButton changeTask = new JButton("Change Tasks");
		changeTask.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
   
        		String[] filesNames = new Files_Management().Load_Last_Project();
        		String ProjectName = "Project_Name";
        		
        		Jframe frame = new Jframe(filesNames,0,jcb.getSelectedIndex(),jcb2.getSelectedIndex());
        		frame.pack();
        		frame.setTitle("Project Management System - "+ ProjectName);
        		frame.setSize(1380,780);
        		frame.setLocation(-5,0);
        		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		frame.pack();
        		frame.setVisible(true);
        		Image icon = null;

        		try 
        		{
        			icon = ImageIO.read(new File("Files/Images/pj.png"));}
        		catch (IOException ex)
        		{ex.printStackTrace();}
        		
        		frame.setIconImage(icon);
            	fram.setVisible(false);
        		fram.dispose();

            }
        });
		

		add(new label(""));
		add(new label(""));
		add(new tableName("Tasks Duration Management"));
		add(new label(""));
		add(label1);
		add(jcb);
		add(jcb2);
		add(changeTask);
		
		add(new label(""));
		add(new label(""));

	
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
		    Font font = new Font("Serif", Font.BOLD, 18);
		    g.setFont(font);
		    g.setColor(Color.BLUE);
		    String dep = label;
			g.drawString(label,190, 20);
			g.drawLine(190, 20, 190+dep.length()*10-40, 20);
		}	
	}
	class label extends JLabel{
		private String label;
		private Image bgImage = null;
		
		public label(String label)
		{
			this.label = label;
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
		    Font font = new Font("Serif", Font.ITALIC, 18);
		    g.setFont(font);
		    g.setColor(Color.BLUE);
			g.drawString(label, 0, 20);
		}	
	}

}
