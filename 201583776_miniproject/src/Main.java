import Gui.*;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.doublyLinkedList.DoublyLinkedList;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
		
		String[] filesNames = Load_Last_Project();
		
		
		Jframe frame = new Jframe(filesNames);
		frame.pack();
		frame.setTitle("Project Management System");
		frame.setSize(1380,780);
		frame.setLocation(-5,0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		Image icon = null;

		try 
		{
			icon = ImageIO.read(new File("pj.png"));}
		catch (IOException e)
		{e.printStackTrace();}
		
		frame.setIconImage(icon);

	}

	public static String[] Load_Last_Project()
	{
		 BufferedReader file = null;
		 String[] files = null;
			try {
				file = new BufferedReader(new FileReader("Files/Last_Project.txt"));
			    String project_Name =  file.readLine();
			    files = new String[5];
			    files[0] = "Files/" + project_Name + "/Components_Prices.txt";
			    files[1] = "Files/" + project_Name + "/Components.txt";
			    files[2] = "Files/" + project_Name + "/Required_Components_Prices.txt";
			    files[3] = "Files/" + project_Name + "/Tasks.txt";
			    files[4] = "Files/" + project_Name + "/Tasks_Components.txt";
			    
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		        
		    try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    
		return files;
	}

}
