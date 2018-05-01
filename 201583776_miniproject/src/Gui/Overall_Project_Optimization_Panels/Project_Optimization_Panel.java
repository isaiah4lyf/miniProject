package Gui.Overall_Project_Optimization_Panels;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import File_IO.Files_Management;
import Gui.TabbedPane;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.doublyLinkedList.DLLNode;
import graph.doublyLinkedList.DoublyLinkedList;
import graph.doublyLinkedList.NodeIterator;


public class Project_Optimization_Panel extends JPanel{
	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private String[] files;
	private Files_Management files_man;
	private Vertex<String,String>[] vert;
	private Edge<String,String>[] edgFinal;
	private Graph<String, String> graph;
	public Project_Optimization_Panel(String[] files,TabbedPane pane)
	{
		this.files = files;
		files_man = new Files_Management();
		
		Object[] tasks = null;
		try 
		{
			graph = files_man.graph_Reader(files[3], true);
			vert = graph.vertices_array();
			tasks = new Object[vert.length];
			for(int i = 0; i <vert.length; i++)
			{
				tasks[i] = vert[i].getData();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Font font = new Font("Serif", Font.ITALIC, 18);
		JComboBox jcb = new JComboBox(tasks);
		jcb.setFont(font);
		JComboBox jcb2 = new JComboBox(tasks);
		jcb2.setFont(font);
		HintTextField available_Funds = new HintTextField("Available Funds");

	    available_Funds.setFont(font);
		JButton butt = new JButton("Optimize Project");
		butt.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	add_Optimized_Info(available_Funds.getText(),jcb.getSelectedIndex(),jcb2.getSelectedIndex(),files[4],files[0],files[5]);
	            	pane.revalidate();
	            	pane.repaint();	
	            	
	            }
	     });
		this.setLayout(new GridLayout(8,1));
		this.add(new label(""));
		this.add(new tableName("Project Optimization"));
		this.add(new label(""));
		this.add(new label("Select a Project Start Task and Enter the available funds"));
		this.add(jcb);
		this.add(available_Funds);
		this.add(butt);
		this.add(new label(""));
		
	}
	
	public void add_Optimized_Info(String input,int startTaskIndex,int endTaskIndex,String FileName1,String FileName2,String FileName3)
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
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				String[] components_for_v1 = files_man.ReturnComponentsForTask(FileName,crit_Path[i].getV1().getData());
				String[] components_for_v2 = files_man.ReturnComponentsForTask(FileName,crit_Path[i].getV2().getData());
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
			g.drawString(label,200, 15);
			g.drawLine(200, 15, 200+dep.length()*10-35, 15);
		}	
	}
	class label extends JLabel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String label;
		public label(String label)
		{
			this.label = label;
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
		    Font font = new Font("Serif", Font.ITALIC, 18);
		    g.setFont(font);
		    g.setColor(Color.BLUE);
			g.drawString(label, 0, 20);
		}	
	}
	class HintTextField extends JTextField implements FocusListener {

		 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String hint;
		  private boolean showingHint;

		  public HintTextField(final String hint) {
		    super(hint);
		    this.hint = hint;
		    this.showingHint = true;
		    super.addFocusListener(this);
		  }


		  @Override
		  public void focusGained(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText("");
		      showingHint = false;
		    }
		  }
		  @Override
		  public void focusLost(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText(hint);
		      showingHint = true;
		    }
		  }

		  @Override
		  public String getText() {
		    return showingHint ? "" : super.getText();
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
