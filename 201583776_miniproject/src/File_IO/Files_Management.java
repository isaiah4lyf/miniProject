package File_IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Files_Management {
	public Files_Management()
	{
		
	}

	
	/**
	 * Main I/O Methods
	 */
	public String[] Load_Last_Project()
	{
		 BufferedReader file = null;
		 String[] files = null;
			try {
				file = new BufferedReader(new FileReader("Files/Last_Project.txt"));
			    String project_Name =  file.readLine();
			    files = new String[5];
			    files[0] = "Files/" + project_Name + "/Components_Prices.txt";
			    files[1] = "Files/" + project_Name + "/Components.txt";
			    files[2] = "Files/" + project_Name + "/Required_Components_Prices.txt";
			    files[3] = "Files/" + project_Name + "/Tasks.txt";
			    files[4] = "Files/" + project_Name + "/Tasks_Components.txt";
			    
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		        
		    try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    
		return files;
	}
	
	/**
	 * Graph ADT I/O  Methods
	 */
	
	
	/**
	 * Gui I/O Methods
	 */
	
	/**
	 * Components Management I/O Methods
	 */
	
	
	/**
	 * Tasks Management I/O Methods
	 */
	
	
	/**
	 * Time Optimization I/O Methods
	 */
	
	
	/**
	 * Cost Optimization I/O Methods
	 */
	public double[] Read_Prices(String priceFileName)
	{
	    BufferedReader file = null;
	    double[] prices_ = null;
		try {
			file = new BufferedReader(new FileReader(priceFileName));
		    String line;

		    String[] size = (file.readLine()).split("=");
		    prices_ = new double[Integer.parseInt(size[1])];
		    
		    for(int i = 0; i < prices_.length; i++)
		    {
		    	prices_[i] = Double.parseDouble((file.readLine()).split("=")[1]);
		    
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
	    
	    return prices_;
	}
	
	public String[] Return_Priced_Components(String priceFileName)
	{
	    BufferedReader file = null;
	    String[] components = null;
		try {
			file = new BufferedReader(new FileReader(priceFileName));
		    String line;

		    String[] size = (file.readLine()).split("=");
		    components = new String[Integer.parseInt(size[1])];
		    
		    for(int i = 0; i < components.length; i++)
		    {
		    	components[i] = (file.readLine()).split("=")[0];
		    
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
	
	
}
