package FileRead;
import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;
import javax.swing.text.html.HTMLDocument.Iterator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileRead {

	void makeFile(String key) throws IOException {
		File fileInput = new File("C:/Users/prave/Desktop/Data_mining/data.txt");
		FileInputStream fis = new FileInputStream(fileInput);
		if (fileInput.exists()) {
			BufferedReader br = new BufferedReader(new InputStreamReader((fis)));
			String line;
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("C:/Users/prave/Desktop/Data_mining/filtered file/" + key + ".txt"));
			while ((line = br.readLine()) != null) {
				if (line.contains(key)) {
					bw.write(line);
					bw.newLine();
				}
			}
			br.close();
			bw.close();
		}
	}
	
	static String simplify(String[] array)
	{int y=array.length;
	
		float[] intarray=new float[y];
		for(int i=0;i<array.length;i++)			
		{	
			intarray[i]=Float.parseFloat(array[i]);
			
		}
		float temp;
		for(int i=0;i<intarray.length-1;i++)
		{
			for(int j=0;j<(intarray.length)-1-i;j++)
			{
				if(intarray[j]>intarray[j+1])
				{
					temp=intarray[j];
					intarray[j]=intarray[j+1];
					intarray[j+1]=temp;
			   }					
			}		
		}
		String strrange=null;
		if(intarray[0]==intarray[intarray.length-1])
		{
			 strrange="["+intarray[0]+"]";
		}
		else{
		 strrange="["+intarray[0]+"-"+intarray[intarray.length-1]+"]";
		}
		
		return strrange;
	}
		static String rmdup(String[] array)
		{	
			Set<String> uniques= new HashSet<String>(Arrays.asList(array));
			int n;
			int x=array.length;
		     String[] check=new String[3];
		     int i=0;
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			for(String s : uniques) {
		        //check[i]=s;
			    builder.append(s);
			    builder.append(",");
			    //i++;
			}
			builder.append("]");
			return builder.toString();
		
		}
	
	
	/*public static void adddata(String values[],int x,int j,Connection connection, PreparedStatement preparedstatement) throws SQLException{
		preparedstatement=connection.prepareStatement("insert into rawdata values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		preparedstatement.setInt(1, j);
		for(int i=1;i<x;i++)
		{
		preparedstatement.setString(i+1,values[i-1]);
		}
		preparedstatement.executeUpdate();
	}*/
	public static void main(String[] args) throws IOException, SQLException  {
		
		/*PreparedStatement preparedstatement = null;
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/dm","root","");  
			 
		} catch (ClassNotFoundException e1) {
		
			e1.printStackTrace();
		} catch (SQLException e1) {
	
			e1.printStackTrace();
		}*/
		
		String key = "buffer_overflow";
		
		FileRead ob = new FileRead();
		
		/*String columnNames[] = {"Duration","Protcol Type","Service","Flag","Src bytes","Dst bytes","Land","Wrong fragment","Urgent","Hot",
				"Num failed login","Logged in","Num compromised","Root shell","Su attempted","Num root","Num file creations","Num shells","Num access files",
				"Num outbounds cmds","Is host login","Is guest login","Count","Srv count","Serror rate","Srv serror rate","Rerror rate","Srv rerror rate",
				"Same srv rate","Diff srv rate","Srv diff host rate","Dst host count","Dst host srv count","Dst host same srv rate","Dst host diff srv rate",
				"Dst host same srv port rate","Dst host srv diff host rate","Dst host serror rate","Dst host srv serror rate","Dst host rerror rate",
				"Dst host srv rerror rate"};
		
		System.out.println(columnNames.length);*/
		
		try {
			ob.makeFile(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		File fileInput = new File("C:/Users/prave/Desktop/Data_mining/filtered file/" + key + ".txt");
		
		FileInputStream fis = new FileInputStream(fileInput);
		
		BufferedReader br = new BufferedReader(new InputStreamReader((fis)));
		
		String line;
		int i=-1,j=0,k=0;
		String twod [][] = new String[500000][50];
		while ((line = br.readLine()) != null) {
			System.out.println();
			i++;
			j=0;
			String values[] = line.split(",");
			for (String string : values) {
				twod[i][j]=string;
				j++;
				System.out.print(string+"	");
			}
		
		}
		System.out.println();
		int l=0;
        String oneD []=new String[3];		      
		 j=0;
		 String range=null;
		for(int z=0;z<30;)
		{
		for(j=0;j<41;j++)
		{l=0;
		for( i=z;i<z+3;i++)
			
			{
				 oneD[l]=twod[i][j];
				 l++;
			}
		    if(j!=1&&j!=2&&j!=3&&j!=42)
		    { 
		    range=simplify(oneD);
			  System.out.println(""+range);
			}
		    
		    else{
		     range=rmdup(oneD);
		     System.out.println(""+range);
		    }
		    	
	
		    
		} System.out.println("\n\n\n\n");
	z=z+3;	}
			
		br.close();
		
		//bw1.close();	
	}

}
