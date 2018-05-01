package File_IO;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.doublyLinkedList.DLLNode;
import graph.doublyLinkedList.DoublyLinkedList;
import graph.doublyLinkedList.NodeIterator;

/**
 * @author Isaiah Ramafalo, 201583776 Mini Project
 *
 */
public class Files_Management {
	public Files_Management()
	{
		
	}

	
	
	/**
	 * Main I/O Methods
	 */
	
	/**
	 * @return
	 */
	public String[] Load_Last_Project()
	{
		 BufferedReader file = null;
		 String[] files = null;
			try {
				file = new BufferedReader(new FileReader("Files/Last_Project.txt"));
			    String project_Name =  file.readLine();
			    files = new String[6];
			    files[0] = "Files/" + project_Name + "/Components_Prices.txt";
			    files[1] = "Files/" + project_Name + "/Components.txt";
			    files[2] = "Files/" + project_Name + "/Required_Components_Prices.txt";
			    files[3] = "Files/" + project_Name + "/Tasks.txt";
			    files[4] = "Files/" + project_Name + "/Tasks_Components.txt";
			    files[5] = "Files/" + project_Name + "/Project_Optimization.txt";
			    
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
	
	
	
	
	
	/**
	 * Graph ADT I/O  Methods
	 */
	
	/**
	 * @param fileName
	 * @param Vertex
	 */
	public void AppendVertex(String fileName,String Vertex)
	{
		
	    BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(fileName));
		    String line;

		    Graph<String, String> graph = graph = graph_Reader(fileName, true);
		    Vertex<String,String>[] vert = graph.vertices_array();
		    Edge<String,String>[] edg = graph.edges_array();
		    String[] vertices = new String[vert.length];
		    String[] edges = new String[edg.length];
		    String firstLine = file.readLine();
		    for(int i = 0; i < vert.length; i++)
		    {
		    	vertices[i] = file.readLine();
		    }
		    file.readLine();
		    for(int i = 0; i < edg.length; i++)
		    {
		    	edges[i] = file.readLine();
		    }

		    PrintWriter write = new PrintWriter(new File(fileName));
		    int size = vert.length + 1;
		    write.println("size="+size);
		    for(int i = 0; i<vertices.length; i++)
		    {
		    	write.println(vertices[i]);
		    }
		    //14 = Task O
		    int length = vert.length;
		    write.println(length +" "+ "=" + " "+ Vertex);
		    write.println(";");
		    for(int i = 0; i<edges.length; i++)
		    {
		    	write.println(edges[i]);
		    }
		    write.println(";");
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

	
	/**
	 * @param fileName
	 * @param firstVertex
	 * @param SecondVertex
	 * @param weight
	 */
	public void AppendEdge(String fileName,int firstVertex,int SecondVertex,String weight)
	{
		
	    BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(fileName));
		    String line;

		    Graph<String, String> graph = graph = graph_Reader(fileName, true);
		    Vertex<String,String>[] vert = graph.vertices_array();
		    Edge<String,String>[] edg = graph.edges_array();
		    String[] vertices = new String[vert.length];
		    String[] edges = new String[edg.length];
		    String firstLine = file.readLine();
		    for(int i = 0; i < vert.length; i++)
		    {
		    	vertices[i] = file.readLine();
		    }
		    file.readLine();
		    for(int i = 0; i < edg.length; i++)
		    {
		    	edges[i] = file.readLine();
		    }

		    PrintWriter write = new PrintWriter(new File(fileName));
		    write.println(firstLine);
		    for(int i = 0; i<vertices.length; i++)
		    {
		    	write.println(vertices[i]);
		    }
		    write.println(";");
		    for(int i = 0; i<edges.length; i++)
		    {
		    	write.println(edges[i]);
		    }
		    write.println("("+firstVertex+","+SecondVertex+","+weight+")");
		    write.println(";");
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
	
	/**
	 * @param fileName
	 * @param EdgeIndex
	 */
	public void DeleteEdge(String fileName,int EdgeIndex)
	{
		
	    BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(fileName));
		    String line;

		    Graph<String, String> graph = graph = graph_Reader(fileName, true);
		    Vertex<String,String>[] vert = graph.vertices_array();
		    Edge<String,String>[] edg = graph.edges_array();
		    String[] vertices = new String[vert.length];
		    String[] edges = new String[edg.length - 1];
		    String firstLine = file.readLine();
		    for(int i = 0; i < vert.length; i++)
		    {
		    	vertices[i] = file.readLine();
		    }
		    file.readLine();
		    int edgCount = 0;
		    for(int i = 0; i < edg.length; i++)
		    {
		    	if(EdgeIndex != i)
		    	{
		    		edges[edgCount] = file.readLine();
		    		edgCount++;
		    	}
		    	else
		    	{
		    		file.readLine();
		    	}
		    }
		    PrintWriter write = new PrintWriter(new File(fileName));
		    write.println(firstLine);
		    for(int i = 0; i<vertices.length; i++)
		    {
		    	write.println(vertices[i]);
		    }
		    write.println(";");
		    for(int i = 0; i<edges.length; i++)
		    {
		    	write.println(edges[i]);
		    }
		    write.println(";");
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
	
	
	/**
	 * @param fileName
	 * @param VertextIndex
	 */
	public void DeleteVertex(String fileName,int VertextIndex)
	{
		
	    BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(fileName));
		    String line;

		    Graph<String, String> graph = graph_Reader(fileName, true);
		    Vertex<String,String>[] vert = graph.vertices_array();
		    Edge<String,String>[] edg = graph.edges_array();
		    String[] vertices = new String[vert.length - 1];

		    String firstLine = file.readLine();
		    int vert_Count = 0;
		    for(int i = 0; i < vert.length; i++)
		    {
		    	if(VertextIndex != i)
		    	{
		    		vertices[vert_Count] = file.readLine();
		    		vert_Count++;
		    	}
		    	else
		    	{
		    		file.readLine();
		    	}
		    	
		    }
		    file.readLine();
	
		    Graph<String, String> cloned = graph.clone();
		    Vertex<String,String>[] ve = cloned.vertices_array();
		    Vertex<String,String> vertToRemove = null;
		    for(int i = 0; i< ve.length; i++)
		    {
		    	if(i == VertextIndex)
		    	{
		    		vertToRemove = ve[i];
		    	}
		    }
			
		    NodeIterator<Edge<String,String>> outEdg = vertToRemove.getInEdges();
		    NodeIterator<Edge<String,String>> inEdge = vertToRemove.getOutEdges();


		    while(outEdg.hasNext())
		    {
		    	cloned.removeEdge(outEdg.next());
		    }
		    
		    while(inEdge.hasNext())
		    {
		    	cloned.removeEdge(inEdge.next());
		    }
		    
		    Edge<String,String>[] newEdges = cloned.edges_array();
		    
		    PrintWriter write = new PrintWriter(new File(fileName));
		    write.println("size="+vertices.length);
		    
		    for(int i = 0; i<vertices.length; i++)
		    {
		    	String[] tokens =  vertices[i].split("=");
		    	write.println(i + " =" + tokens[1]);
		    }
		    write.println(";");
		    for(int i = 0; i<newEdges.length; i++)
		    {
		    	int inde1 = vertexIndex(vertices,newEdges[i].getV1().getData());
		    	int inde2 = vertexIndex(vertices,newEdges[i].getV2().getData());
		    	write.println("(" + inde1 + ","+ inde2 +","+newEdges[i].getWeight()+")");
		    }
		    write.println(";");
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
	
	/**
	 * @param vertices
	 * @param Vertex
	 * @return
	 */
	public int vertexIndex(String[] vertices,String Vertex)
	{
		int index = -1;
		for(int i = 0; i<vertices.length;i++)
		{
			if(Vertex.equals((vertices[i]).split(" = ")[1]))
			{
				index = i;
			}
		}
		return index;
	}
	/**
	 * Read graph from input File
	 * @param fileName
	 * @param directed
	 * @return Graph created
	 * @throws FileNotFoundException
	 */
	public static Graph<String,String> graph_Reader(String fileName, boolean directed) throws FileNotFoundException{
		Graph<String,String> graph = new Graph<String,String>(directed);
		
		Scanner scan = new Scanner(new File(fileName));
		String readLine;
		Pattern pattern;
		Matcher matcher;
		
		readLine = scan.nextLine();
		pattern = Pattern.compile("size\\s*=\\s*(\\d+)");
		matcher = pattern.matcher(readLine);
		matcher.find();
		Vertex<String,String> vertices[] = new Vertex[Integer.parseInt(matcher.group(1))];
		
		while(!(readLine = scan.nextLine()).equals(";") ){
			pattern = Pattern.compile("([^0-9]*)\\s*(\\d+)\\s*=\\s*(.*)");
			matcher = pattern.matcher(readLine);
			matcher.find();
			if(matcher.group(1) == null || matcher.group(1).isEmpty()){
				vertices[Integer.parseInt(matcher.group(2))] = graph.addVertex(matcher.group(3));
			}else if(matcher.group(1).trim().equals("//") || matcher.group(1).trim().equals("#")){
				continue;
			}else{
				throw new InputMismatchException();
			}
		}
		
		while(!(readLine = scan.nextLine()).equals(";") ){
			pattern = Pattern.compile("(.*)\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*(,\\s*(\\d+|\\d+\\.\\d+)\\s*)?\\)(\\s*=\\s*(.*))?");
			matcher = pattern.matcher(readLine);
			matcher.find();
			if(matcher.group(1) == null || matcher.group(1).isEmpty()){
				double weight = 0.0;
				int v1Index = Integer.parseInt(matcher.group(2));
				int v2Index = Integer.parseInt(matcher.group(3));
				if(matcher.group(5) != null)
					weight = Double.parseDouble(matcher.group(5));
				String label = matcher.group(7);
				
				graph.addEdge(vertices[v1Index], vertices[v2Index], label, weight);
			}else if(matcher.group(1).trim().equals("//") || matcher.group(1).trim().equals("#")){
				continue;
			}else{
				throw new InputMismatchException();
			}
		}
		return graph;
	}
	
	
	
	
	
	/**
	 * Gui I/O Methods
	 */
	
	/**
	 * @return
	 */
	public String[] Load_All_Projects()
	{
		 BufferedReader file = null;
		 String[] files = null;
			try {
				file = new BufferedReader(new FileReader("Files/Projects.txt"));
			    int Num_Projects =  Integer.parseInt(file.readLine());
			    files = new String[Num_Projects];
			    for(int i = 0; i< Num_Projects; i++)
			    {
			    	files[i] = file.readLine();
			    }
			     
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
	public  String[] Load_Specific_Project(String Project_Name)
	{

		 String[] files = null;
			try {
			    files = new String[6];
			    files[0] = "Files/" + Project_Name + "/Components_Prices.txt";
			    files[1] = "Files/" + Project_Name + "/Components.txt";
			    files[2] = "Files/" + Project_Name + "/Required_Components_Prices.txt";
			    files[3] = "Files/" + Project_Name + "/Tasks.txt";
			    files[4] = "Files/" + Project_Name + "/Tasks_Components.txt";
			    files[5] = "Files/" + Project_Name + "/Project_Optimization.txt";
			    
			    PrintWriter write6 = new PrintWriter(new File("Files/Last_Project.txt"));
			    write6.println(Project_Name);
			    write6.close();
			    
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}

		return files;
	}
	
	public String[] Create_New_Project(String project_Name)
	{

		 String[] files = null;
		 BufferedReader file = null;

			try {
				file = new BufferedReader(new FileReader("Files/Projects.txt"));
			    int size =  Integer.parseInt(file.readLine());
			    String[] projects = new String[size];
			    boolean projectExist = false;

			    for(int i = 0; i<size; i++)
			    {
			    	projects[i] = file.readLine();
			    }
			    
			    for(int i = 0; i<projects.length; i++)
			    {
			    	if(projects[i].equals(project_Name))
			    	{
			    		projectExist = true;
			    	}

			    }
			    if(projectExist == true)
			    {
			    	projects = null;
			    }
			    else
			    {
				    PrintWriter write0 = new PrintWriter(new File("Files/Projects.txt"));
				    write0.println(size + 1);
				    for(int i = 0; i<size; i++)
				    {
				    	write0.println(projects[i]);
				    }
				    write0.println(project_Name);
				    write0.close();
				    
					new File("Files/"+project_Name).mkdirs();
				    files = new String[6];
				    files[0] = "Files/" + project_Name + "/Components_Prices.txt";
				    new File("Files/" + project_Name + "/Components_Prices.txt").createNewFile();
				    PrintWriter write1 = new PrintWriter(new File(files[0]));
				    write1.println("size=0");
				    write1.close();
				    
				    files[1] = "Files/" + project_Name + "/Components.txt";
				    new File("Files/" + project_Name + "/Components.txt").createNewFile();
				    PrintWriter write2 = new PrintWriter(new File(files[1]));
				    write2.println("size=0");
				    write2.println(";");
				    write2.println(";");
				    write2.close();
				    
				    files[2] = "Files/" + project_Name + "/Required_Components_Prices.txt";
				    new File("Files/" + project_Name + "/Required_Components_Prices.txt").createNewFile();
				    PrintWriter write3 = new PrintWriter(new File(files[2]));
				    write3.println("0");
				    write3.close();
				    
				    files[3] = "Files/" + project_Name + "/Tasks.txt";
				    new File("Files/" + project_Name + "/Tasks.txt").createNewFile();
				    PrintWriter write4 = new PrintWriter(new File(files[3]));
				    write4.println("size=0");
				    write4.println(";");
				    write4.println(";");
				    write4.close();
				    
				    files[4] = "Files/" + project_Name + "/Tasks_Components.txt";
				    new File("Files/" + project_Name + "/Tasks_Components.txt").createNewFile();
				    PrintWriter write5 = new PrintWriter(new File(files[4]));
				    write5.println("0");
				    write5.close();
				    
				    files[5] = "Files/" + project_Name + "/Project_Optimization.txt";
				    new File("Files/" + project_Name + "/Project_Optimization.txt").createNewFile();
				    
				    PrintWriter write6 = new PrintWriter(new File("Files/Last_Project.txt"));
				    write6.println(project_Name);
				    write6.close();
			    }

			    
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        
		return files;
	}
	
	
	
	/**
	 * Components Management I/O Methods
	 */
	
	
	/**
	 * Tasks Management I/O Methods
	 */
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
	
	/**
	 * Time Optimization I/O Methods
	 */
	
	
	/**
	 * Cost Optimization I/O Methods
	 */
	public double[] Read_Prices(String priceFileName)
	{
	    BufferedReader file = null;
	    double[] prices_ = null;
		try {
			file = new BufferedReader(new FileReader(priceFileName));
		    String line;

		    String[] size = (file.readLine()).split("=");
		    prices_ = new double[Integer.parseInt(size[1])];
		    
		    for(int i = 0; i < prices_.length; i++)
		    {
		    	prices_[i] = Double.parseDouble((file.readLine()).split("=")[1]);
		    
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
	    
	    return prices_;
	}
	
	public void add_Price(String ComponentsName, String price, String priceFileName,String requiredPricesFole)
	{
	    BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(priceFileName));
		    String line;

		    String[] size = (file.readLine()).split("=");
		    String[] prices = new String[Integer.parseInt(size[1])];
		    
		    for(int i = 0; i < prices.length; i++)
		    {
		    	prices[i] = file.readLine();
		    }

		    PrintWriter write = new PrintWriter(new File(priceFileName));
		    int new_size = prices.length + 1;
		    write.println("size="+new_size);
		    for(int i = 0; i<prices.length; i++)
		    {
		    	write.println(prices[i]);
		    }
		    write.println(ComponentsName + " = " + price);
		    write.close();
		    DeleteReuired_Price(ComponentsName,requiredPricesFole);
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
	
	public void DeleteReuired_Price(String component,String requiredPricesFole)
	{
	   
		try {

		    String[] prices = Return_Priced_Components_Panel(requiredPricesFole);
		    PrintWriter write = new PrintWriter(new File(requiredPricesFole));
		    int new_size = prices.length - 1;
		    
		    write.println(new_size);
		    int count = 0; 
		    for(int i = 0; i<prices.length; i++)
		    {
		    	if(!component.equals(prices[i]))
		    	{
		    		write.println(prices[i]);
		    		count++;
		    	}
		    }
		    write.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	}
	
	
	public String[] Return_Priced_Components_Panel(String priceFileName)
	{
	    BufferedReader file = null;
	    String[] components = null;
		try {
			file = new BufferedReader(new FileReader(priceFileName));
		    String line;

		    String size = file.readLine();
		    components = new String[Integer.parseInt(size)];
		    for(int i = 0; i < components.length; i++)
		    {
		    	components[i] = file.readLine();
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
	    return components;
	}
	

	
	
	public String[] Return_Priced_Components(String priceFileName)
	{
	    BufferedReader file = null;
	    String[] components = null;
		try {
			file = new BufferedReader(new FileReader(priceFileName));
		    String line;

		    String[] size = (file.readLine()).split("=");
		    components = new String[Integer.parseInt(size[1])];
		    
		    for(int i = 0; i < components.length; i++)
		    {
		    	components[i] = (file.readLine()).split("=")[0];
		    
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
	    
	    return components;
	}
	
	
	
	public double[] Return_Prices_For_Specific_Task(String priceFileName,String Tasks_Components_FilName,String taskName)
	{
	    double[] prices = null;
		try {

		    String[] components = ReturnComponentsForTask(Tasks_Components_FilName,taskName);
		    
		    if(components != null)
		    {
			    prices = new double[components.length];
			    
			    for(int i = 0; i < components.length-1; i++)
			    {
			    	prices[i] = Double.parseDouble(Return_Price_For_Specific_Comp(priceFileName,components[i+1]));
			    }
		    }


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	    
	    return prices;
	}
	
	
	public String Return_Price_For_Specific_Comp(String priceFileName,String Component)
	{
	    BufferedReader file = null;
	    String price = "";
		try {
			file = new BufferedReader(new FileReader(priceFileName));

		    String size = (file.readLine()).split("=")[1];

		    for(int i = 0; i < Integer.parseInt(size); i++)
		    {
		    	String[] lineTokens = (file.readLine()).split(" = ");
		    	if(Component.equals(lineTokens[0]))
		    	{
		    		price = lineTokens[1];
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
	    return price;
	}
	
	
	
	
	/**
	 * Overall Project Optimization I/O Methods
	 */
	public String[] read_Calculations(String fileName)
	{
		BufferedReader file = null;
		String[] cal = null;
		try 
		{
			file = new BufferedReader(new FileReader(fileName));
			file.readLine();
			file.readLine();
			String[] lineTokens =  (file.readLine()).split(",");
			String[] cost_n_Value = lineTokens[0].split(" = ");
			String[] duration_n_Value = lineTokens[1].split(" = ");
			cal = new String[4];
			cal[0] = cost_n_Value[0];
			cal[1] = cost_n_Value[1];
			cal[2] = duration_n_Value[0];
			cal[3] = duration_n_Value[1];
		}
		catch(Exception ex)
		{
			
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;	
	}
	
	
	public String[] read_Crit_Path_Components(String fileName)
	{
		BufferedReader file = null;
		String[] cal = null;
		try 
		{
			file = new BufferedReader(new FileReader(fileName));
			String line  = file.readLine();
			if(line != null)
			{
				
				String[] lineTokens =  line.split(",");
				cal = new String[lineTokens.length];
				for(int i = 0; i < lineTokens.length; i++)
				{
					
					cal[i] = lineTokens[i];
				}
			}


		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;	
	}
	
	
	public double[] read_Crit_Path_Components_Prices(String[] components,String fileName)
	{
		BufferedReader file = null;
		double[] prices = null;
		try 
		{
			file = new BufferedReader(new FileReader(fileName));
			String line  = file.readLine();
			int size = Integer.parseInt(line.split("=")[1]);
			String[] lines = new String[size];
			if(components != null)
			{
				
				prices = new double[components.length];
				for(int i = 0; i < size; i++)
				{
					lines[i] = file.readLine();
				}
				
				for(int i = 0; i < components.length; i++)
				{
					for(int j = 0; j<lines.length; j++)
					{
						String component = lines[j].split(" = ")[0];
						if(components[i].equals(component))
						{
							prices[i] = Double.parseDouble(lines[j].split(" = ")[1]);
						}
					}
				}
			}


		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prices;	
	}
	
	public void add_Optimized_Info(String input,int startTaskIndex,int endTaskIndex,String FileName1,String FileName2,String FileName3,Graph<String, String> graph,Vertex<String,String>[] vert)
	{
	   
		try 
		{
			DoublyLinkedList<String> components_In_Crit = new DoublyLinkedList<String>();
			
			double current_Cost = 0;
			double maxCost = Double.parseDouble(input);
			
			int current_Tasks_Count = 0; 
			int maxCount = 0;
			int optimized_Index = 0;
			double optimized_Max_Cost = 0;
			String tasks = "";
			Edge<String,String>[] finalEdge = null;
			for(int i = 0; i < vert.length - 1; i++)
			{
			    Edge<String,String>[] currentPah = graph.dijkstra(vert[startTaskIndex],vert[i]);
			    DoublyLinkedList<String> all_Components = return_Components_In_Crit_Path(currentPah,FileName1);

			    if(all_Components != null)
			    {
				    if(all_Components.size() != 0)
				    {
			    		NodeIterator<String> iterator = all_Components.iterator();
			    		String current = all_Components.first().getData();
			    		boolean found = false;
			    		while(iterator.hasNext())
			    		{
			    			String next = iterator.next();
			    		    if (current == next && !found) {
			    		        found = true;
			    		        DLLNode<String> node = all_Components.search(next);
			    		        all_Components.remove(node);
			    		    } else if (current != next) {
			    		        current = next;
			    		        found = false;
			    		    }
			    		}	
				    }
			    }
			    current_Tasks_Count = currentPah.length;
			    current_Cost = crit_Path_Cost(all_Components,FileName2);
	    		if(current_Cost > optimized_Max_Cost && current_Cost < maxCost && current_Tasks_Count >= maxCount)
	    		{
	    			
	    			optimized_Max_Cost = current_Cost;
	    			maxCount = current_Tasks_Count;
	    			optimized_Index = i;
	    			components_In_Crit = all_Components;
	    			finalEdge = currentPah;
	    		}
			    
			}

			NodeIterator<String> com_Iter = components_In_Crit.iterator();
			String comp_In_Crit = ""; 
			while(com_Iter.hasNext())
			{
				comp_In_Crit += com_Iter.next() + ",";
			}
			String tasks_In_Crit = "";
			int totalDuration = 0;
			if(finalEdge != null)
			{
				for(int i = 0; i < finalEdge.length; i++)
				{
					tasks_In_Crit += finalEdge[i].getV1().getData() + " = " +  (int)finalEdge[i].getWeight() + ",";
					totalDuration += (int)finalEdge[i].getWeight();
				}
				tasks_In_Crit += finalEdge[finalEdge.length - 1].getV2().getData() + " = 0";
				PrintWriter write3;
				write3 = new PrintWriter(new File(FileName3));
			    write3.println(comp_In_Crit);
			    write3.println(tasks_In_Crit);
			    write3.println("Total Cost = " + optimized_Max_Cost +","+"Total Duration = "+totalDuration);
			    write3.close();
			}
			else
			{
				showMessageDialog(null, "No Critical Path That cost less than 'R "+input+"' was found! \n\n\n NB: Try Changing the start task or increasing the project funds", "Critical Path Not found!", 1);
			}

		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			showMessageDialog(null, "Critical Path Computation Error! \n\n\n NB: Try Changing the start task or Increasing the project funds", "Critical Path Not found!", 1);

		}

	}
	
	public double crit_Path_Cost(DoublyLinkedList<String> all_Components,String fileName)
	{
		if(all_Components != null)
		{
			BufferedReader file = null;
			double cost = 0;
			try 
			{
				file = new BufferedReader(new FileReader(fileName));
			    int size = Integer.parseInt((file.readLine()).split("=")[1]);
			    String[] lines = new String[size];
			    for(int i = 0; i < size; i ++)
			    {
			    	lines[i] = file.readLine();
			    }
				NodeIterator<String> iterator = all_Components.iterator();
				while(iterator.hasNext())
				{
					String comp = iterator.next();
					
					for(int i = 0; i<lines.length; i++)
					{
						if(comp.equals(lines[i].split(" = ")[0]))
						{
							cost += Double.parseDouble(lines[i].split(" = ")[1]);
						}
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
		    return cost;
		}
		return 0;
	}
	
	public DoublyLinkedList<String> return_Components_In_Crit_Path(Edge<String,String>[] crit_Path,String FileName)
	{
		DoublyLinkedList<String> all_Components = new DoublyLinkedList<String>();
		if(crit_Path.length != 0)
		{
			for(int i = 0; i < crit_Path.length; i++)
			{
				String[] components_for_v1 = ReturnComponentsForTask(FileName,crit_Path[i].getV1().getData());
				String[] components_for_v2 = ReturnComponentsForTask(FileName,crit_Path[i].getV2().getData());
				if(components_for_v1 != null)
				{
					for(int j = 0; j < components_for_v1.length - 1; j++)
					{
						
						if(all_Components.search(components_for_v1[j+1]) == null)
						{
							all_Components.add(components_for_v1[j+1]);
						}
					
					}	
				}

				if(components_for_v2 != null)
				{
					for(int j = 0; j < components_for_v2.length - 1; j++)
					{
						if(all_Components.search(components_for_v2[j+1]) == null)
						{
							all_Components.add(components_for_v2[j+1]);
						}
					}
				}
			}
			
			return all_Components;
		}

		return null;
	
	}
	
	public String[] read_Crit_Path_Tasks(String FileName)
	{
		BufferedReader file = null;
		String[] cal = null;
		try 
		{
			file = new BufferedReader(new FileReader(FileName));
			if(file.readLine() != null)
			{
				
				String[] lineTokens =  (file.readLine()).split(",");
				cal = new String[lineTokens.length];
				for(int i = 0; i < lineTokens.length; i++)
				{
					String[] task = lineTokens[i].split(" = ");
					cal[i] = task[0];
				}
			}


		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;	
	}
	
	public int[] read_Crit_Path_Duration(String FileName)
	{
		BufferedReader file = null;
		int[] cal = null;
		try 
		{
			file = new BufferedReader(new FileReader(FileName));
			if(file.readLine() != null)
			{
				String[] lineTokens =  (file.readLine()).split(",");
				cal = new int[lineTokens.length];
				for(int i = 0; i < lineTokens.length; i++)
				{
					String[] task = lineTokens[i].split(" = ");
					cal[i] = Integer.parseInt(task[1]);
				}

			}
	

		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;	
	}
	
}
