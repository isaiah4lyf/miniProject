package Gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
		menu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
  
            	try {
          		   Runtime.getRuntime().exec("java -jar Project.jar");
          		   TimeUnit.SECONDS.sleep(2);
          		   System.exit(0);
          		} catch (Exception ex) {
          		}
            }
        });

		/**
		 * Components management tab
		 */
		JPanel display_Graph_Panel = new Display_Graph();
		display_Graph_Panel.setOpaque(false);
		display_Graph_Panel.setBounds(50, 50, 100, 100);
		display_Graph_Panel.setPreferredSize(new Dimension( 1200,1200));
		display_Graph_Panel.setAutoscrolls(true);
		JScrollPane display_Graph_Panel_scroll = new JScrollPane(display_Graph_Panel);
		display_Graph_Panel_scroll.setPreferredSize(new Dimension( 800,800));
		
		JPanel dependency_Panel = new Components_Dependencies();
		dependency_Panel.setPreferredSize(new Dimension( 1500,1500));
		JScrollPane dependency_Panel_scroll = new JScrollPane(dependency_Panel);
		dependency_Panel.setAutoscrolls(true);
		dependency_Panel_scroll.setPreferredSize(new Dimension(1500,1500));
		
		JPanel Management_Panel = new Components_Management(pane);
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
		JPanel display_Graph_Panel_2 = new Display_Graph();
		display_Graph_Panel_2.setOpaque(false);
		display_Graph_Panel_2.setBounds(50, 50, 100, 100);
		display_Graph_Panel_2.setPreferredSize(new Dimension( 1200,550));
		display_Graph_Panel_2.setAutoscrolls(true);
		JScrollPane display_Graph_Panel_scroll_2 = new JScrollPane(display_Graph_Panel_2);
		display_Graph_Panel_scroll_2.setPreferredSize(new Dimension( 800,300));
		
		JPanel dependency_Panel_2 = new Components_Dependencies();
		dependency_Panel_2.setPreferredSize(new Dimension( 500,550));
		JScrollPane dependency_Panel_scroll_2 = new JScrollPane(dependency_Panel_2);
		dependency_Panel_scroll_2.setAutoscrolls(true);
		dependency_Panel_scroll_2.setPreferredSize(new Dimension( 800,800));
		
		JPanel Management_Panel_2 = new Tasks_Management(pane);
		Management_Panel_2.setLayout(new GridLayout(15,1));
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
		pane.addTab("Components Management", null, tasks_Man_Panel, "");
		
		
		/**
		 * Time Optimization tab
		 */
		JPanel panel4 = new JPanel();
		pane.addTab("Time Optimization", null, panel4, "");
		
		
		/**
		 * Cost Optimization tab
		 */
		JPanel panel5 = new JPanel();
		pane.addTab("Cost Optimization", null, panel5, "");
		

		this.add(pane);
		this.setLayout(new GridLayout(1,1));
		this.setJMenuBar(menubar);
	}
}
