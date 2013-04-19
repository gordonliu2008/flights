package services.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

/*
 * ����������Ϣcfg.txt ��������鿴�ļ���˵��
 * */
public class ReadCFG {
	private static Hashtable tmph1;
	public ReadCFG()
	{
		getcfg();
	}
	public  static boolean getcfg()
	  {
		  try
		    {
			   tmph1=new Hashtable();
			  String s="";
			  StringBuffer rntstrbuf=new StringBuffer();
		      FileInputStream   is;   
	          InputStreamReader   ir;   
	          BufferedReader   in;   
	          is=new   FileInputStream("cfg.txt");   
	          ir=new   InputStreamReader(is);   
	          in=new   BufferedReader(ir);   
	          while((s=in.readLine())!=null)   
	          {       
	        	  if(s==null||s.equals("")||s.substring(0,1).equals("#"))continue;
	        	  String[] s1=s.split("=",2);
	        	  tmph1.put(s1[0].trim(), s1[1].trim());
	  	          
	          }   
	          //System.out.println("The   total   rows   are:"+rntstrbuf.toString());    
	          in.close();
	          ir.close();
	          is.close();
		      return true;
		    }
			catch (Exception e)
		    {
			  e.printStackTrace();
	          return false;   // System.err.println ("Error  read CONFIG:  " + e.getMessage());
	        }
			
	  }
	
	public String getvalue(String paraname)
	{
		return (String)tmph1.get(paraname);
	}
	public int getintvalue(String paraname)
	{
		return Integer.parseInt((String)tmph1.get(paraname));
	}
	public boolean getboolvalue(String paraname)
	{
		String tmps1=(String)tmph1.get(paraname);
		if(tmps1.trim().equals("true"))
			return true;
		else return false;
	}
	
}
