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

import File_IO.Files_Management;

public class Main{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public static void main(String[] args) {
		
		
		String[] filesNames = new Files_Management().Load_Last_Project();
		String ProjectName = "Project_Name";
		
		Jframe frame = new Jframe(filesNames);
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
			icon = ImageIO.read(new File("pj.png"));}
		catch (IOException e)
		{e.printStackTrace();}
		
		frame.setIconImage(icon);

	}

}
