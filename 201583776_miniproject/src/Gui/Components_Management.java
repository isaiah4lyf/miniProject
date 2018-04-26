package Gui;

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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.glass.events.KeyEvent;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
	public Components_Management(TabbedPane itemDisplay)
	{

		Object[] items = null;
		
		try {
			graph = Graph.inParser("MIT.txt", true);
			vert = graph.vertices_array();
			items = new Object[vert.length];
			for(int i = 0; i<vert.length; i++)
			{
				items[i] = vert[i].getData();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JComboBox jcb = new JComboBox(items);
		jcb.setForeground(Color.red);
		jcb.setBackground(Color.white);
		//jcb.setSelectedItem("rtg");
		jcb.setSize(50, 50);
		
		JComboBox jcb2 = new JComboBox(items);
		JComboBox jcb3 = new JComboBox(items);
		JComboBox jcb4 = new JComboBox(items);
		//TaskPanel pan5 = stPanel3;
		
		jcb2.setForeground(Color.red);
		jcb2.setBackground(Color.white);
		//jcb2.setSelectedItem("rtg");
		jcb2.setPreferredSize(new Dimension( 50,50));
		
		
		




		
		
		JTextField inputWeight = new HintTextField("Input Dependency");
		JTextField inputitem = new HintTextField("Input Component");
	    Font font = new Font("Serif", Font.ITALIC, 15);
	    inputWeight.setFont(font);
	    inputitem.setFont(font);
	    jcb4.setFont(font);
	    
		JButton butt = new JButton("Add Dependency");
		butt.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	//itemDisplay.revalidate();
	            	//itemDisplay.repaint();

	            	graph.AppendEdge("MIT.txt",jcb.getSelectedIndex(),jcb2.getSelectedIndex(),inputWeight.getText());
	            	itemDisplay.revalidate();
	            	itemDisplay.repaint();
	            	
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
            	else if(inputitem.getText() == "")
            	{
            		
            	}
            	else
            	{
                	graph.AppendVertex("MIT.txt",inputitem.getText());
                	itemDisplay.invalidate();
                	itemDisplay.revalidate();
                	itemDisplay.repaint();
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
            	showMessageDialog(null, "Please implement this function", "Input Required!", 0);
            	//graph.DeleteVertex("MIT.txt", 0);
            }
        });
		JLabel label00 = new label("TASK AND DEPEDENCIES MANAGEMENT");
		add(label00);
		JLabel label0 = new JLabel("");
		add(label0);
		
		JLabel label1 = new label("Select Tasks and press the butt to add new edge");
		add(label1);
		add(jcb);
		add(jcb2);
		
		

		JPanel combPanel = new JPanel();
		combPanel.setLayout(new GridLayout(1,2));
		combPanel.add(butt);
		combPanel.add(inputWeight);
		add(combPanel);
		
		
		JLabel label7 = new label("Add Item");
		add(label7);
		add(inputitem);
		add(butt4);
		

		JLabel label4 = new label("Select Edge to delete");
		add(label4);
		add(jcb3);
		add(butt2);

		JLabel label6 = new label("Select Task to delete");
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
