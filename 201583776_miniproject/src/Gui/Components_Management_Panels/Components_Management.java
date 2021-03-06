package Gui.Components_Management_Panels;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import File_IO.Files_Management;
import Gui.TabbedPane;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import static javax.swing.JOptionPane.*;

public class Components_Management extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image bgImage = null;
	private Graph<String,String> graph = null;
	private Vertex<String,String>[] vert = null;
	private Edge<String,String>[] edg = null;
	private Files_Management files_man;
	public Components_Management(TabbedPane itemDisplay,String[] files)
	{
		files_man = new Files_Management();
		Object[] items = null;
		Object[] Dependency = null;
		try {
			graph = files_man.graph_Reader(files[1], true);
			vert = graph.return_Vertices_Array();
			items = new Object[vert.length];
			for(int i = 0; i<vert.length; i++)
			{
				items[i] = vert[i].getData();
			}
			
			edg = graph.return_Edges_Array();
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
		
		JTextField inputWeight = new HintTextField("Required Quantity");
		JTextField inputitem = new HintTextField("Component Name");
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
	            		showMessageDialog(null, "Enter the dependency in the 'Dependency component' textbox!", "Input Required!", 0);
	            	}
	            	else
	            	{
	            		try
	            		{
	            			int dep = Integer.parseInt(inputWeight.getText());
	            			files_man.AppendEdge(files[1],jcb.getSelectedIndex(),jcb2.getSelectedIndex(),inputWeight.getText());
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
		JButton butt4 = new JButton("Add Component");
		butt4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	if(inputitem.getText() == "")
            	{
            		showMessageDialog(null, "Enter the name of the component in the 'Input component' textbox!", "Input Required!", 0);
            	}
            	else
            	{
            		files_man.AppendVertex(files[1],inputitem.getText());
                	itemDisplay.invalidate();
                	itemDisplay.revalidate();
                	itemDisplay.repaint();
                	AddReuired_Price(inputitem.getText(),files[2]);
                	showMessageDialog(null, "Component added successfully!", "Component Addition", 2);
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
            		files_man.DeleteEdge(files[1],jcb3.getSelectedIndex());
                	itemDisplay.revalidate();
                	itemDisplay.repaint();
                		
            	} else {
            	  //No option
            	} 

            }
        });
		JButton butt3 = new JButton("Delete Component");
		butt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int dialogButton = JOptionPane.YES_NO_OPTION;
            	int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete: "+jcb4.getSelectedItem() + "? \n\n Note that all dependenc formed with this component will be deleted!", "Deletion confirmatoion", dialogButton);
            	if(dialogResult == 0) {
                	
            		files_man.DeleteVertex(files[1], jcb4.getSelectedIndex());
                	itemDisplay.revalidate();
                	itemDisplay.repaint();
                		
            	} else {
            	  //No option
            	} 

            }
        });
		JLabel label00 = new tableName("Components And Dependencies Management");
		add(label00);
		JLabel label0 = new JLabel("");
		add(label0);
		
		JLabel label1 = new label("Dependency Addition (NB: Option 1 = Component & Option 2 = Required Component)");
		add(label1);
		add(jcb);
		add(jcb2);
		
		

		JPanel combPanel = new JPanel();
		combPanel.setLayout(new GridLayout(1,2));
		combPanel.add(butt);
		combPanel.add(inputWeight);
		add(combPanel);
		
		
		JLabel label7 = new label("Component Addition");
		add(label7);
		add(inputitem);
		add(butt4);
		

		JLabel label4 = new label("Dependency Deletion");
		add(label4);
		add(jcb3);
		add(butt2);

		JLabel label6 = new label("Component Deletion");
		add(label6);
		add(jcb4);
		add(butt3);
	}
	public void AddReuired_Price(String component,String requiredPricesFole)
	{
	   
		try {

		    String[] prices = Return_Priced_Components(requiredPricesFole);
		    PrintWriter write = new PrintWriter(new File(requiredPricesFole));
		    int new_size = prices.length + 1;
		    write.println(new_size);
		    for(int i = 0; i<prices.length; i++)
		    {
		    	write.println(prices[i]);
		    	
		    }
		    write.println(component);
		    write.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	}
	
	public String[] Return_Priced_Components(String priceFileName)
	{
	    BufferedReader file = null;
	    String[] components = null;
		try {
			file = new BufferedReader(new FileReader(priceFileName));

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
	protected void paintComponent(Graphics g)
	{
		try 
		{
			bgImage = ImageIO.read(new File("Files/Images/df.jpg"));
		}
		catch (IOException e)
		{e.printStackTrace();}
		
		g.drawImage(bgImage,0,0,(ImageObserver) this);
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
				bgImage2 = ImageIO.read(new File("Files/Images/df.jpg"));
			}
			catch (IOException e)
			{e.printStackTrace();}
			
			g.drawImage(bgImage2,0,0,(ImageObserver) this);
		    Font font = new Font("TimesRoman", Font.PLAIN, 18);
		    g.setFont(font);
		    g.setColor(Color.BLACK);
		    String dep = label;
			g.drawString(label,180, 15);
			g.drawLine(180, 15, 180+dep.length()*10-50, 15);
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
				bgImage = ImageIO.read(new File("Files/Images/df.jpg"));
			}
			catch (IOException e)
			{e.printStackTrace();}
			
			g.drawImage(bgImage,0,0,(ImageObserver) this);
		    Font font = new Font("Serif", Font.ITALIC, 18);
		    g.setFont(font);
		    g.setColor(Color.BLACK);
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
}
