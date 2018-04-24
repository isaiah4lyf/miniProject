package Gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import graph.*;

public class Jframe extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Jframe()
	{
		

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Project");
		JMenu menu1 = new JMenu("Options");
		JMenu menu2 = new JMenu("Help");
		

		JMenuItem size = new JMenuItem("size");
		JMenuItem size2 = new JMenuItem("size");
		menu.add(size);
		menu.add(size2);
		menubar.add(menu);
		menubar.add(menu1);
		menubar.add(menu2);


		//stPanel2.setSize(400,300);
		JPanel stPanel3= new TaskPanel();
		stPanel3.setOpaque(false);
		
		
		JPanel stPanel2= new ItemPanel(stPanel3);
		JPanel stPanel4= new ItemPanel(stPanel3);
		stPanel4.setLayout(new GridLayout(14,1));
		//stPanel3.setBackground(Color.black);
		stPanel3.setBounds(50, 50, 100, 100);
		setLayout(new GridLayout(2,1));

		stPanel3.setPreferredSize(new Dimension( 1200,550));
		JScrollPane scrollFrame = new JScrollPane(stPanel3);
		stPanel3.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension( 800,300));
		this.add(scrollFrame);

		
		stPanel2.setPreferredSize(new Dimension( 500,550));
		JScrollPane scrollFrame2 = new JScrollPane(stPanel2);
		stPanel2.setAutoscrolls(true);
		scrollFrame2.setPreferredSize(new Dimension( 800,300));

		
		stPanel4.setPreferredSize(new Dimension( 500,100));
		JScrollPane scrollFrame3 = new JScrollPane(stPanel4);
		stPanel4.setAutoscrolls(true);
		scrollFrame3.setPreferredSize(new Dimension( 800,100));

		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(scrollFrame2);
		panel.add(stPanel4);
		this.add(panel);
		
		//add(stPanel3);
		this.setJMenuBar(menubar);
	}
}
