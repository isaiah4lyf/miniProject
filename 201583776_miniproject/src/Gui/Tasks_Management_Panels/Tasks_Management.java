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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import File_IO.Files_Management;
import Gui.Jframe;
import Gui.TabbedPane;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Tasks_Management extends JPanel{

	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private Graph<String,String> graph = null;
	private Vertex<String,String>[] vert = null;
	private Edge<String,String>[] edg = null;
	private Files_Management files_man;
	public Tasks_Management(TabbedPane itemDisplay,String[] files)
	{

		files_man = new Files_Management();
		Object[] items = null;
		Object[] Dependency = null;
		try {
			graph = files_man.graph_Reader(files[3], true);
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
	
		
		JComboBox jcb2 = new JComboBox(items);
		JComboBox jcb3 = new JComboBox(Dependency);
		JComboBox jcb4 = new JComboBox(items);

		jcb2.setPreferredSize(new Dimension( 50,50));
		
		
		JTextField inputWeight = new HintTextField("Task Duration");
		JTextField inputitem = new HintTextField("Task Name");
	    Font font = new Font("Serif", Font.ITALIC, 15);
	    inputWeight.setFont(font);
	    inputitem.setFont(font);
	    jcb4.setFont(font);
	    jcb2.setFont(font);
	    jcb3.setFont(font);
	    jcb.setFont(font);
	    
		JButton butt = new JButton("Add Dependency");
		butt.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {


	            	if(inputWeight.getText() == "")
	            	{
	            		showMessageDialog(null, "Enter the duration of the task in the 'Task Duration' textbox!", "Input Required!", 0);
	            	}
	            	else
	            	{
	            		try
	            		{
	            			int dep = Integer.parseInt(inputWeight.getText());
	            			files_man.AppendEdge(files[3],jcb.getSelectedIndex(),jcb2.getSelectedIndex(),inputWeight.getText());
			            	itemDisplay.revalidate();
			            	itemDisplay.repaint();	
	            		}
	            		catch(Exception ex)
	            		{
	            			showMessageDialog(null, "Dependency input should be a number!", "Input Required!", 0);
	            		}
            	}
	            	
	            }
	        });
		JButton butt4 = new JButton("Add Task");
		butt4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	if(inputitem.getText() == "")
            	{
            		showMessageDialog(null, "Enter the name of the task in the 'Task Name' textbox!", "Input Required!", 0);
            	}
            	else
            	{
            		files_man.AppendVertex(files[3],inputitem.getText());
                	itemDisplay.invalidate();
                	itemDisplay.revalidate();
                	itemDisplay.repaint();
                	showMessageDialog(null, "Task added successfully!", "Task Addition", 2);
            	}

            }
        });
		
		JButton butt2 = new JButton("Delete Dependency");
		butt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
  
            	int dialogButton = JOptionPane.YES_NO_OPTION;
            	int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete: "+jcb3.getSelectedItem() + "?", "Deletion confirmatoion", dialogButton);
            	if(dialogResult == 0) {
            		files_man.DeleteEdge(files[3],jcb3.getSelectedIndex());
                	itemDisplay.revalidate();
                	itemDisplay.repaint();
                		
            	} else {
            	  //No option
            	} 

            }
        });
		JButton butt3 = new JButton("Delete Task");
		butt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int dialogButton = JOptionPane.YES_NO_OPTION;
            	int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete: "+jcb4.getSelectedItem() + "? \n\n Note that all dependenc formed with this component will be deleted!", "Deletion confirmatoion", dialogButton);
            	if(dialogResult == 0) {
                	
            		files_man.DeleteVertex(files[3], jcb4.getSelectedIndex());
                	itemDisplay.revalidate();
                	itemDisplay.repaint();
                		
            	} else {
            	  //No option
            	} 

            }
        });
		JLabel label00 = new tableName("Tasks And Dependencies Management");
		JButton manageComponents = new JButton("Manage Components For a Specific Task");

		manageComponents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Manage_Tasks_Components_Frame frame = new Manage_Tasks_Components_Frame(0,files);
        		frame.setTitle("Manage Components For a Specific Task");
        		frame.setSize(350,450);
        		frame.setResizable(false);
        		frame.setLocation(550,200);
        		frame.setVisible(true);
        		Image icon = null;
        		try 
        		{
        			icon = ImageIO.read(new File("pj.png"));}
        		catch (IOException ex)
        		{ex.printStackTrace();}
        		
        		frame.setIconImage(icon);
            }
        });
		add(manageComponents);
		JLabel label0 = new JLabel("");
		
		add(label0);
		add(label00);
		
		JLabel label1 = new label("Dependency Addition (NB: Option 1 = Task & Option 2 = Required Task)");
		add(label1);
		add(jcb);
		add(jcb2);
		
		

		JPanel combPanel = new JPanel();
		combPanel.setLayout(new GridLayout(1,2));
		combPanel.add(butt);
		combPanel.add(inputWeight);
		add(combPanel);
		
		
		JLabel label7 = new label("Task Addition");
		add(label7);
		add(inputitem);
		add(butt4);
		

		JLabel label4 = new label("Dependency Deletion");
		add(label4);
		add(jcb3);
		add(butt2);

		JLabel label6 = new label("Task Deletion");
		add(label6);
		add(jcb4);
		add(butt3);
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
			g.drawString(label,180, 15);
			g.drawLine(180, 15, 180+dep.length()*10-30, 15);
			
		}	
	}
	class button extends JButton{
		private String label;
		public button(String label)
		{
			this.label = label;
		}
		protected void paintComponent(Graphics g)
		{
			try 
			{
				bgImage = ImageIO.read(new File("but.png"));
			    iWidth2 = bgImage.getWidth((ImageObserver) this)/2;
			    iHeight2 = bgImage.getHeight((ImageObserver) this)/2;
			}
			catch (IOException e)
			{e.printStackTrace();}
			
			g.drawImage(bgImage,0,0,(ImageObserver) this);
		    Font font = new Font("Serif", Font.BOLD, 18);
		    g.setFont(font);
		    g.setColor(Color.BLACK);
			g.drawString(label, 50, 20);
		}	
	}
	
	
	class label extends JLabel{
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
			    iWidth2 = bgImage.getWidth((ImageObserver) this)/2;
			    iHeight2 = bgImage.getHeight((ImageObserver) this)/2;
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
}
