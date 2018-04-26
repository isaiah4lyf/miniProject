import Gui.*;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.doublyLinkedList.DoublyLinkedList;

import java.awt.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends JFrame{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
		Jframe frame = new Jframe();

		frame.pack();
		frame.setTitle("Project Management System");
		frame.setSize(1380,780);
		frame.setLocation(-5,0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Image icon = null;

		try 
		{
			icon = ImageIO.read(new File("pj.png"));}
		catch (IOException e)
		{e.printStackTrace();}
		
		frame.setIconImage(icon);
		try {

			Graph<String,String> graph = Graph.inParser("MIT.txt", true);
			System.out.println("Print graph state\n");
			System.out.println(graph);
			
			System.out.println("BFS:");
			for(Vertex<String,String> v : graph.BFS())
				System.out.print(v + " ");
			
			System.out.println("\n\nDFS");
			for(Vertex<String,String> v : graph.DFS())
				System.out.print(v + " ");
			
			System.out.println("\n\nIs connected: "+graph.isConnected());
			System.out.println("Is directed: "+graph.isDirected());
			System.out.println("Is cyclic: "+graph.isCyclic());
			System.out.println("Number of Connected components: "+graph.connectedComponents());
			
			System.out.println("\nClone graph ... ");
			Graph<String,String> cloned = graph.clone();
			
			System.out.println("Apply Transitive closure to the cloned graph");
			cloned.transitiveClosure();
			
			System.out.println("Print state of the new graph\n");
			System.out.println(cloned);
			
			
			
			System.out.println("");
			System.out.println("");
			
			
			Vertex<String,String> vetA = null;
			Vertex<String,String> vetB = null;
			
			for(Vertex<String,String> v : graph.vertices_array())
			{
				if(v.getData().equals("Task A"))
				{
					vetA = v;
					System.out.println("ok");
				}
				if(v.getData().equals("Task I"))
				{
					vetB = v;
					System.out.println("ok");
				}
			}
			
			int cost = 0;
			for(Edge<String,String> e: graph.dijkstra(vetA, vetB))
			{
				cost += e.getWeight();
				System.out.println(e);
				System.out.println("ok");
			}
			System.out.println("\n" + cost);
			System.out.println("\n\n\n\n");
			
			
			DoublyLinkedList<Vertex<String,String>> items_Reqired = new DoublyLinkedList<Vertex<String,String>>();
			for(Vertex<String,String> v3 : graph.vertices_array())
			{
				if(!v3.getData().equals("Task G"))
				{
					Vertex<String,String>[] vvv = graph.DFS(v3);
					for(Vertex<String,String> v : vvv)
					{
						System.out.print(v + " ");
						if(items_Reqired.search(v) == null && !v.getData().equals("Task G"))
						{
							items_Reqired.add(v);
						}
						
						if(v.getData().equals("Task G"))
						{
							System.out.println("");
							break;
						}
					}
				}

			}
			System.out.print("\n");
			System.out.print("\n");
			
			items_Reqired.remove(items_Reqired.getTail());
			System.out.println(items_Reqired);
			System.out.print("\n");
			System.out.print("\n");
			for(Vertex<String,String> v3 : graph.vertices_array())
			{
				if(!v3.getData().equals("Task B"))
				{
					Vertex<String,String>[] vvv = graph.DFS(v3);
					for(Vertex<String,String> v : vvv)
					{
						System.out.print(v + " ");
						if(v.getData().equals("Task B"))
						{
							System.out.println("Yes");
							break;
						}
						//if(graph.DFS(v).length == 1)
						//{
							//System.out.println("\n" + vvv[0]);
						//}
					}
				}

			}
			System.out.print("\n");
			System.out.print("\n");
			for(Vertex<String,String> v3 : graph.vertices_array())
			{
				Vertex<String,String>[] vvv = graph.BFS(v3);
				for(Vertex<String,String> v : vvv)
				{
					System.out.print(v + " ");
					if(graph.BFS(v).length == 1)
					{
						System.out.println("\n" + vvv[0]);
						//System.out.print("\n");
					}
				}
			}
		
			Vertex<String,String>[] edg = graph.vertices_array();

			Vertex<String,String>[] neibours = edg[1].getNeighbors_in();
			System.out.println("\n\n");
			String str = edg[0].toString();
			for(int i = 0; i< neibours.length; i++)
			{
				System.out.println(neibours[i]);
			}
		
			//graph.DeleteVertex("MIT.txt", 0);
			
			//System.out.println(str.substring(0, 1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
