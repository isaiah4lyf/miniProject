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
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.glass.events.KeyEvent;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class ItemPanel extends JPanel{

	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private Graph<String,String> graph = null;
	public ItemPanel(JPanel itemDisplay)
	{

		Object[] items = null;
		try {
			graph = Graph.inParser("MIT.txt", true);
			Vertex<String,String>[] vert = graph.vertices_array();
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
		
		
		
		JButton butt = new JButton("Select");
		JButton butt2 = new JButton("Select");
		JButton butt3 = new JButton("Select");
		
		JTextField inputWeight = new HintTextField("Input the weight");
	    Font font = new Font("Serif", Font.ITALIC, 15);
	    inputWeight.setFont(font);
	    jcb4.setFont(font);
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
		JLabel label00 = new JLabel("                                                                 TASK AND DEPEDENCIES MANAGEMENT");
		add(label00);
		JLabel label0 = new JLabel("");
		add(label0);
		
		JLabel label1 = new label();
		label1.setText("Select Tasks and press the butt to add new edge");
		add(label1);
		add(jcb);
		add(jcb2);
		
		

		JPanel combPanel = new JPanel();
		combPanel.setLayout(new GridLayout(1,2));
		combPanel.add(butt);
		combPanel.add(inputWeight);
		add(combPanel);
		
		
		
		JLabel label2 = new JLabel("");
		add(label2);
		JLabel label4 = new JLabel("Select Edge to delete");
		add(label4);
		add(jcb3);
		add(butt2);
		JLabel label5 = new JLabel("");
		add(label5);
		JLabel label6 = new JLabel("Select Task to delete");
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
	class label extends JLabel{
		protected void paintComponent(Graphics g)
		{
			try 
			{
				bgImage = ImageIO.read(new File("bg.jpg"));
			    iWidth2 = bgImage.getWidth((ImageObserver) this)/2;
			    iHeight2 = bgImage.getHeight((ImageObserver) this)/2;
			}
			catch (IOException e)
			{e.printStackTrace();}
			
			g.drawImage(bgImage,0,0,(ImageObserver) this);
		    Font font = new Font("Serif", Font.ITALIC, 18);
		    g.setFont(font);
		    g.setColor(Color.BLUE);
			g.drawString("Select Tasks and press the butt to add new edge", 80, 20);
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
