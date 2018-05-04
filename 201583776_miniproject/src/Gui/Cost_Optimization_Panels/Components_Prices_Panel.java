package Gui.Cost_Optimization_Panels;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import File_IO.Files_Management;
import Gui.Jframe;
import Gui.TabbedPane;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class Components_Prices_Panel extends JPanel{

	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private Graph<String,String> graph = null;
	private Vertex<String,String>[] vert = null;
	private Graph<String,String> graph2 = null;
	private Vertex<String,String>[] vert2 = null;
	
	private Files_Management files_man;
	
	private String Selected_Task;
	public String getSelected_Task() {
		return Selected_Task;
	}
	public Components_Prices_Panel(TabbedPane prices_Histo,String[] files,int selected_Task,JFrame fram)
	{
		files_man = new Files_Management();
		Object[] items = null;
		Object[] items2 = null;
		String[] priced_Comp = files_man.Return_Priced_Components_Panel(files[2]);
		try {
			graph = files_man.graph_Reader(files[1], true);
			vert = graph.return_Vertices_Array();
			items = new Object[priced_Comp.length];

			for(int i = 0; i<priced_Comp.length; i++)
			{
				items[i] = priced_Comp[i];
			}
			
			graph2 = files_man.graph_Reader(files[3], true);
			vert2 = graph2.return_Vertices_Array();
			items2 = new Object[vert2.length];

			for(int i = 0; i<vert2.length; i++)
			{
				items2[i] = vert2[i].getData();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel label1 = new label("New Component Price Addition");
		
		JPanel combPanel = new JPanel();
		combPanel.setLayout(new GridLayout(3,1));
		JComboBox jcb = new JComboBox(items);
	    Font font = new Font("Serif", Font.ITALIC, 18);
	    jcb.setFont(font);
		JTextField inpiutPrice = new HintTextField("Input Price");
		inpiutPrice.setFont(font);
		JButton addPrice = new JButton("Add Price");
		addPrice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	if(inpiutPrice.getText() == "")
            	{
            		showMessageDialog(null, "Enter the name of the component in the 'Input component' textbox!", "Input Required!", 0);
            	}
            	else if(inpiutPrice.getText() == "")
            	{
            		
            	}
            	else
            	{
            		files_man.add_Price(jcb.getSelectedItem().toString(),inpiutPrice.getText(),files[0],files[2]);		
            		prices_Histo.revalidate();
            		prices_Histo.repaint();  

                	showMessageDialog(null, "Component price added successfully!", "Price Addition", 2);
            	
            	}

            }
        });
		
		//Checkout task costs
		JComboBox jcb2 = new JComboBox(items2);
	    jcb2.setFont(font);
	    if(items2.length != 0)
	    {
	    	jcb2.setSelectedIndex(selected_Task);
	    }
		
		JLabel label2 = new label("Check Specific Task Cost");
		JButton check_cost = new JButton("Change Task");
		check_cost.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            	Selected_Task = jcb2.getSelectedItem().toString();
        		Jframe frame = new Jframe(files,jcb2.getSelectedIndex(),0,0);
        		String ProjectName = files[0].split("/")[1];
        		frame.pack();
        		frame.setTitle("Project Management System - "+ProjectName);
        		frame.setSize(1380,780);
        		frame.setLocation(-5,0);
        		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		frame.pack();
        		
        		Image icon = null;
        		try 
        		{
        			icon = ImageIO.read(new File("Files/Images/pj.png"));}
        		catch (IOException ex)
        		{ex.printStackTrace();}
        		
        		frame.setIconImage(icon);
            	fram.setVisible(false);
        		fram.dispose();
        		
        		frame.setVisible(true);
        		
            	fram.setVisible(false);
        		fram.dispose();

            }
        });
		add(new tableName("Cost Management"));
		add(new label(""));
		add(label1);
		add(jcb);
		add(inpiutPrice);
		add(addPrice);
		
		add(new label(""));
		add(label2);
		add(jcb2);
		add(check_cost);
		if(items2.length != 0)
		{
			this.Selected_Task = jcb2.getSelectedItem().toString();
		}
		
	}
	

	
	class tableName extends JLabel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Image bgImage2 = null;
		
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
		    Font font = new Font("Serif", Font.PLAIN, 18);
		    g.setFont(font);
		    g.setColor(Color.BLACK);
		    String dep = label;
			g.drawString(label,250, 15);
			g.drawLine(250, 15, 250+dep.length()*10-15, 15);
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
				bgImage = ImageIO.read(new File("Files/Images/df.jpg"));
			    iWidth2 = bgImage.getWidth((ImageObserver) this)/2;
			    iHeight2 = bgImage.getHeight((ImageObserver) this)/2;
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
