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
		System.out.println(filesNames[0]);
		System.out.println(filesNames[1]);
		System.out.println(filesNames[2]);
		System.out.println(filesNames[3]);
		System.out.println(filesNames[4]);
		
		System.out.println(Create_New_Project("Project_4")[0]);

	}
	public static String[] Create_New_Project(String project_Name)
	{

		 String[] files = null;
		 BufferedReader file = null;

			try {
				file = new BufferedReader(new FileReader("Files/Projects.txt"));
			    int size =  Integer.parseInt(file.readLine());
			    String[] projects = new String[size];
			    for(int i = 0; i<size; i++)
			    {
			    	projects[i] = file.readLine();
			    }
			    PrintWriter write0 = new PrintWriter(new File("Files/Projects.txt"));
			    write0.println(size + 1);
			    for(int i = 0; i<size; i++)
			    {
			    	write0.println(projects[i]);
			    }
			    write0.println(project_Name);
			    write0.close();
			    
				new File("Files/"+project_Name).mkdirs();
			    files = new String[5];
			    files[0] = "Files/" + project_Name + "/Components_Prices.txt";
			    new File("Files/" + project_Name + "/Components_Prices.txt").createNewFile();
			    PrintWriter write1 = new PrintWriter(new File(files[0]));
			    write1.println("size=0");
			    write1.close();
			    
			    files[1] = "Files/" + project_Name + "/Components.txt";
			    new File("Files/" + project_Name + "/Components.txt").createNewFile();
			    PrintWriter write2 = new PrintWriter(new File(files[1]));
			    write2.println("size=0");
			    write2.close();
			    
			    files[2] = "Files/" + project_Name + "/Required_Components_Prices.txt";
			    new File("Files/" + project_Name + "/Required_Components_Prices.txt").createNewFile();
			    PrintWriter write3 = new PrintWriter(new File(files[2]));
			    write3.println("0");
			    write3.close();
			    
			    files[3] = "Files/" + project_Name + "/Tasks.txt";
			    new File("Files/" + project_Name + "/Tasks.txt").createNewFile();
			    PrintWriter write4 = new PrintWriter(new File(files[3]));
			    write4.println("0");
			    write4.close();
			    
			    files[4] = "Files/" + project_Name + "/Tasks_Components.txt";
			    new File("Files/" + project_Name + "/Tasks_Components.txt").createNewFile();
			    PrintWriter write5 = new PrintWriter(new File(files[4]));
			    write5.println("size=0");
			    write5.close();
			    
			    PrintWriter write6 = new PrintWriter(new File("Files/Last_Project.txt"));
			    write6.println(project_Name);
			    write6.close();
			    
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        
		return files;
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
