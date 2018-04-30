package Gui.Overall_Project_Optimization_Panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Tasks_Crit_Histogram_Panel extends JPanel{

	Image bgImage = null;
	private int iWidth2;
	private int iHeight2;
	private String[] files;
	/**
	 * Components Histogram
	 */
	
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
		
		String[] tasks = read_Crit_Path_Tasks();
		int[] dur = read_Crit_Path_Duration();
		
		g.setColor(Color.CYAN);
		//g.drawString("Criticat path between '"+vert[Select_T_1].getData()+"' and '"+vert[Select_T_2].getData()+"'",0 ,10);
		g.drawLine(10, this.getHeight() - 45, this.getWidth() - 10, this.getHeight() - 45);
		int individualWidth = (int)(((this.getWidth() - 40)/24) * 0.90);
		
		if(tasks != null || dur != null)
		{
			int interval = (this.getWidth() - 40)/(tasks.length);
			
			int initialPos = 10;

		 
		    int maxDur = dur[0];

		    for(int i=0;i<dur.length;i++){
		        if(dur[i]>maxDur){
		        	maxDur=dur[i];
		        }

		    }

			for(int i = 0; i< dur.length; i++ )
			{
				int barHeight = (int)(((double)dur[i] / (double)maxDur) *(this.getHeight() - 65));
				
				g.fill3DRect(initialPos, this.getHeight() - 45 - barHeight, individualWidth, barHeight,true);
				g.drawString(tasks[i], initialPos, this.getHeight() - 30);
				String durString = String.valueOf((int)dur[i]);
				g.drawString(durString+" days", initialPos, this.getHeight() - 45 - barHeight);
				initialPos += interval;
			}
		}
	}
	
	public String[] read_Crit_Path_Tasks()
	{
		BufferedReader file = null;
		String[] cal = null;
		try 
		{
			file = new BufferedReader(new FileReader("Files/Project_Test/Project_Optimization.txt"));
			if(file.readLine() != null)
			{
				
				String[] lineTokens =  (file.readLine()).split(",");
				cal = new String[lineTokens.length];
				for(int i = 0; i < lineTokens.length; i++)
				{
					String[] task = lineTokens[i].split(" = ");
					cal[i] = task[0];
				}
			}


		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;	
	}
	
	public int[] read_Crit_Path_Duration()
	{
		BufferedReader file = null;
		int[] cal = null;
		try 
		{
			file = new BufferedReader(new FileReader("Files/Project_Test/Project_Optimization.txt"));
			if(file.readLine() != null)
			{
				String[] lineTokens =  (file.readLine()).split(",");
				cal = new int[lineTokens.length];
				for(int i = 0; i < lineTokens.length; i++)
				{
					String[] task = lineTokens[i].split(" = ");
					cal[i] = Integer.parseInt(task[1]);
				}

			}
	

		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;	
	}
}
