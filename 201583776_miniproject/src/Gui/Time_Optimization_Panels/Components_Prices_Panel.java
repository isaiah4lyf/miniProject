package Gui.Time_Optimization_Panels;

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
	public Components_Prices_Panel(TabbedPane prices_Histo,String[] files)
	{
		Object[] items = null;
		String[] priced_Comp = Return_Priced_Components(files[2]);
		try {
			graph = Graph.inParser(files[1], true);
			vert = graph.vertices_array();
			items = new Object[priced_Comp.length];
			for(int i = 0; i<priced_Comp.length;i++)
			{
				System.out.println(priced_Comp[i]);
			}
			System.out.println(priced_Comp.length);
			
			for(int i = 0; i<priced_Comp.length; i++)
			{
				items[i] = priced_Comp[i];
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel label1 = new label("Add new Price");
		
		JPanel combPanel = new JPanel();
		combPanel.setLayout(new GridLayout(3,1));
		for(int i = 0; i< items.length; i++)
		{
			System.out.println(items[i] + " Hello");
		}
		JComboBox jcb = new JComboBox(items);
		JTextField inpiutPrice = new HintTextField("Input Price");
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
            		add_Price(jcb.getSelectedItem().toString(),inpiutPrice.getText(),files[0],files[2]);		
            		prices_Histo.revalidate();
            		prices_Histo.repaint();  

                	showMessageDialog(null, "Component price added successfully!", "Price Addition", 2);
            	
            	}

            }
        });
		
		//Checkout task costs
		JComboBox jcb2 = new JComboBox(items);
		JLabel label2 = new label("Check task cost");
		JButton check_cost = new JButton("Check Cost");
		check_cost.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	showMessageDialog(null, "Implement method", "Input Required!", 0);

            }
        });
		add(new label("Optimization"));
		add(new label(""));
		add(label1);
		add(jcb);
		add(inpiutPrice);
		add(addPrice);
		
		add(new label(""));
		add(label2);
		add(jcb2);
		add(check_cost);
		
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

		    String[] prices = Return_Priced_Components(requiredPricesFole);
		    PrintWriter write = new PrintWriter(new File(requiredPricesFole));
		    int new_size = prices.length - 1;
		    
		    write.println(new_size);
		    int count = 0; 
		    for(int i = 0; i<prices.length; i++)
		    {
		    	if(!component.equals(prices[i]))
		    	{
		    		write.println(prices[count]);
		    		count++;
		    	}
		    }
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
