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
import java.io.File;
import java.io.FileNotFoundException;
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
import graph.Graph;
import graph.Vertex;

public class Project_Optimization_Panel extends JPanel{
	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private String[] files;
	private Files_Management files_man;
	private Vertex<String,String>[] vert;
	public Project_Optimization_Panel(String[] files,TabbedPane pane)
	{
		files_man = new Files_Management();
		Graph<String, String> graph;
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
		HintTextField available_Funds = new HintTextField("Available Funds");

	    available_Funds.setFont(font);
		JButton butt = new JButton("Optimize Project");
		butt.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	add_Optimized_Info(available_Funds.getText());
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
	
	public void add_Optimized_Info(String input)
	{
	   
		try 
		{
			PrintWriter write3;
			write3 = new PrintWriter(new File("Files/Project_Test/Project_Optimization.txt"));
		    write3.println(input);
		    write3.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
