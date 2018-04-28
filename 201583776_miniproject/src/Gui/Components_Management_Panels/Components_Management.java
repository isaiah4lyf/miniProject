package Gui.Components_Management_Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

import com.sun.glass.events.KeyEvent;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import Gui.TabbedPane;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import static javax.swing.JOptionPane.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Components_Management extends JPanel{

	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private Graph<String,String> graph = null;
	private Vertex<String,String>[] vert = null;
	private Edge<String,String>[] edg = null;
	public Components_Management(TabbedPane itemDisplay)
	{

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
			            	graph.AppendEdge("MIT.txt",jcb.getSelectedIndex(),jcb2.getSelectedIndex(),inputWeight.getText());
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
                	graph.AppendVertex("MIT.txt",inputitem.getText());
                	itemDisplay.invalidate();
                	itemDisplay.revalidate();
                	itemDisplay.repaint();
                	AddReuired_Price(inputitem.getText(),"Reuired_Prices.txt");
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
                	graph.DeleteEdge("MIT.txt",jcb3.getSelectedIndex());
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
                	
                	graph.DeleteVertex("MIT.txt", jcb4.getSelectedIndex());
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
