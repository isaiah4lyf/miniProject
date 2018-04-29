package Gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Gui.Components_Management_Panels.Components_Dependencies;
import Gui.Components_Management_Panels.Components_Management;
import Gui.Tasks_Management_Panels.Tasks_Dependencies;
import Gui.Tasks_Management_Panels.Tasks_Management;
import Gui.Time_Optimization_Panels.Components_Prices_Panel;
import Gui.Time_Optimization_Panels.Optimization_Graph_Panel;
import Gui.Time_Optimization_Panels.Optimization_Histo_Pan;
import Gui.Time_Optimization_Panels.Prices_Histogram_Panel;
import graph.*;

public class Jframe extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Jframe(String[] filesNames)
	{
		
		String[] files = filesNames;
		
		TabbedPane pane = new TabbedPane();

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Project");
		JMenu menu1 = new JMenu("Options");
		JMenu menu2 = new JMenu("Help");
		JButton menu3 = new JButton("Refresh");

		JMenuItem size = new JMenuItem("New Project");
		JMenuItem size2 = new JMenuItem("Open Project From:");
		menu.add(size);
		menu.add(size2);
		menubar.add(menu);
		menubar.add(menu1);
		menubar.add(menu2);
		menubar.add(menu3);
		
		
		Jframe fram = this;
		menu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
  
        		Jframe frame = new Jframe(files);

        		frame.pack();
        		frame.setTitle("Project Management System");
        		frame.setSize(1380,780);
        		frame.setLocation(-5,0);
        		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		frame.pack();
        		Image icon = null;
        		try 
        		{
        			icon = ImageIO.read(new File("pj.png"));}
        		catch (IOException ex)
        		{ex.printStackTrace();}
        		frame.setIconImage(icon);
        		frame.setVisible(true);
        		frame.setIconImage(icon);
            	fram.setVisible(false);
        		fram.dispose();

            }
        });

		/**
		 * Components management tab
		 */
		JPanel display_Graph_Panel = new Display_Graph(filesNames[1]);
		display_Graph_Panel.setOpaque(false);
		display_Graph_Panel.setBounds(50, 50, 100, 100);
		display_Graph_Panel.setPreferredSize(new Dimension( 1200,1200));
		display_Graph_Panel.setAutoscrolls(true);
		JScrollPane display_Graph_Panel_scroll = new JScrollPane(display_Graph_Panel);
		display_Graph_Panel_scroll.setPreferredSize(new Dimension( 800,800));
		
		JPanel dependency_Panel = new Components_Dependencies(files);
		dependency_Panel.setPreferredSize(new Dimension( 500,550));
		JScrollPane dependency_Panel_scroll = new JScrollPane(dependency_Panel);
		dependency_Panel.setAutoscrolls(true);
		dependency_Panel_scroll.setPreferredSize(new Dimension(800,800));
		
		JPanel Management_Panel = new Components_Management(pane,files);
		Management_Panel.setLayout(new GridLayout(15,1));
		Management_Panel.setPreferredSize(new Dimension( 500,100));
		JScrollPane Management_Panel_Scroll = new JScrollPane(Management_Panel);
		Management_Panel.setAutoscrolls(true);
		Management_Panel_Scroll.setPreferredSize(new Dimension( 800,100));
		
		JPanel dependency_and_Manage_P = new JPanel();
		dependency_and_Manage_P.setLayout(new GridLayout(1,2));
		dependency_and_Manage_P.add(dependency_Panel_scroll);
		dependency_and_Manage_P.add(Management_Panel_Scroll);
		
		
		JPanel components_Man_Panel = new JPanel();
		components_Man_Panel.setLayout(new GridLayout(2,1));
		components_Man_Panel.add(display_Graph_Panel_scroll);
		components_Man_Panel.add(dependency_and_Manage_P);
		pane.addTab("Components Management", null, components_Man_Panel, "");
		
		
		/**
		 * Tasks Management tab
		 */
		JPanel display_Graph_Panel_2 = new Display_Graph(filesNames[3]);
		display_Graph_Panel_2.setOpaque(false);
		display_Graph_Panel_2.setBounds(50, 50, 100, 100);
		display_Graph_Panel_2.setPreferredSize(new Dimension( 1200,550));
		display_Graph_Panel_2.setAutoscrolls(true);
		JScrollPane display_Graph_Panel_scroll_2 = new JScrollPane(display_Graph_Panel_2);
		display_Graph_Panel_scroll_2.setPreferredSize(new Dimension( 800,300));
		
		JPanel dependency_Panel_2 = new Tasks_Dependencies(files);
		dependency_Panel_2.setPreferredSize(new Dimension( 500,550));
		JScrollPane dependency_Panel_scroll_2 = new JScrollPane(dependency_Panel_2);
		dependency_Panel_scroll_2.setAutoscrolls(true);
		dependency_Panel_scroll_2.setPreferredSize(new Dimension( 800,800));
		
		JPanel Management_Panel_2 = new Tasks_Management(pane,files);
		Management_Panel_2.setLayout(new GridLayout(16,1));
		Management_Panel_2.setPreferredSize(new Dimension( 500,100));
		JScrollPane Management_Panel_Scroll_2 = new JScrollPane(Management_Panel_2);
		Management_Panel_Scroll_2.setAutoscrolls(true);
		Management_Panel_Scroll_2.setPreferredSize(new Dimension( 800,100));
		
		JPanel dependency_and_Manage_P_2 = new JPanel();
		dependency_and_Manage_P_2.setLayout(new GridLayout(1,2));
		dependency_and_Manage_P_2.add(dependency_Panel_scroll_2);
		dependency_and_Manage_P_2.add(Management_Panel_Scroll_2);
		
		JPanel tasks_Man_Panel = new JPanel();
		tasks_Man_Panel.setLayout(new GridLayout(2,1));
		tasks_Man_Panel.add(display_Graph_Panel_scroll_2);
		tasks_Man_Panel.add(dependency_and_Manage_P_2);
		pane.addTab("Tasks Management", null, tasks_Man_Panel, "");
		
		
		/**
		 * Cost Optimization tab
		 */
		Optimization_Graph_Panel optP = new Optimization_Graph_Panel();
		
		Prices_Histogram_Panel price_Histo = new Prices_Histogram_Panel(files);
		JScrollPane price_Histoscroll = new JScrollPane(price_Histo);
		price_Histoscroll.setPreferredSize(new Dimension( 1200,1200));
	
		

		

		Components_Prices_Panel price_Man = new Components_Prices_Panel(pane,files);
		price_Man.setLayout(new GridLayout(10,1));


		
		
		Optimization_Histo_Pan someGraph = new Optimization_Histo_Pan(files);
		JScrollPane someScrtoll = new JScrollPane(someGraph);
		someScrtoll.setPreferredSize(new Dimension( 1200,1200));
		
		
		JPanel DownPanel = new JPanel();
		DownPanel.setLayout(new GridLayout(1,2));
		DownPanel.add(someScrtoll);
		DownPanel.add(price_Man);

		
		JPanel cost_Main_Panel = new JPanel();
		cost_Main_Panel.setLayout(new GridLayout(2,1));
		cost_Main_Panel.add(price_Histoscroll);
		cost_Main_Panel.add(DownPanel);
		pane.addTab("Cost Optimization", null, cost_Main_Panel, "");
		
		
		
		/**
		 * Time Optimization tab
		 */
		JPanel panel5 = new JPanel();
		pane.addTab("Time Optimization", null, panel5, "");
		
		
		/**
		 * Overall project statistics
		 * View tasks with less cost
		 * View tasks with short completion time
		 * View components with less cost but with with many dependees
		 */
		JPanel panel6 = new JPanel();
		pane.addTab("Overall Project Optimization", null, panel6, "");
		

		this.add(pane);
		this.setLayout(new GridLayout(1,1));
		this.setJMenuBar(menubar);
	}
}
