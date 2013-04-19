package services.log;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.TimeZone;
import java.io.*;
import java.util.*;
import services.db.ReadCFG;

//ר��д��־����
public class HLog {
    private String logPath;
    private StringWriter sWriter = new StringWriter();
	private PrintWriter pWriter = new PrintWriter(sWriter);
	private static ReadCFG cfg = new ReadCFG();
	private static String workdir = cfg.getvalue("workdir");
	private static String logdir = workdir+"\\log";
	//д��������־
	public void wTransLog(int logType, String ClassMethodName, String Describe, String Content, String User, String Inst)
    {
		try
        {
         if(logPath == null)
         	logPath = logdir;//��־·��������jndi.properties����
         String curdat=getDate("yyyyMMdd");
         String logDir = logPath + File.separator + curdat;
         File myFile = new File(logDir);
         if(logType==1)
         {
        	 String tempClassMethodName=getUpClassMethodName(ClassMethodName);
        	 if(tempClassMethodName!=null)ClassMethodName=tempClassMethodName;
         }
         if(User==null||User.equals(""))User="000000";
         String fileName = logDir + File.separator + Inst+".txt";
         if(!myFile.exists())
             myFile.mkdirs();
         	FileOutputStream fileStream = null;
         	try
	         {
	             fileStream = new FileOutputStream(fileName, true);
	             String currentTime = getDate("HH:mm:ss");;
	             String contents = "[Time:" + currentTime + "]["+ClassMethodName+"][User:" + User + "][Dept:" + Inst + "]" + Describe + " | " + Content + " | " + "\n";
	             fileStream.write(contents.getBytes());
	         }
	         catch(Exception e)
	         {
	             System.out.println("[wTransLog���ִ���]" + e.toString());
	         }finally{
	         	try{
	         		if(fileStream!=null){
	         			fileStream.close();
	         		}         		
	         	}catch(Exception e1){
	         		System.out.println("�ر�Translog.txt fileStreamʧ��");
	         	}
	         }
        }
	         catch(Exception e)
	         {
	             System.out.println("[wTransLog���ִ���]" + e.toString());
	         }
               	
    }	
	
	//д������־
	public void wExceptionLog(String ClassMethodName, String Describe, Exception e, String User, String Inst){
   	    if(logPath == null)
   		logPath = cfg.getvalue("LogDir");//��־·��������jndi.properties����
   	 String curdat=getDate("yyyyMMdd");
     String logDir = logPath + File.separator + curdat;
     String fileName = logDir + File.separator + "Exceptionlog.txt";
     File myFile = new File(logDir);
     if(!myFile.exists())
         myFile.mkdirs();
        FileOutputStream fileStream = null;
        try {
			fileStream = new FileOutputStream(fileName, true);
			String currentTime = getDate("HH:mm:ss");;
            String contents = "[Time:" + currentTime + "]["+ClassMethodName+"][User:" + User + "][Dept:" + Inst + "]" + Describe + " | \n";
			e.printStackTrace(pWriter);
			fileStream.write(contents.toString().getBytes());
			fileStream.write(sWriter.toString().getBytes());
		} catch (Exception e1) {
			System.out.println("[wExceptionLog��]" + e.toString());
		}finally{
        	try{
        		if(fileStream!=null){
        			fileStream.close();
        		}         		
        	}catch(Exception e1){
        		System.out.println("�ر�Exceptionlog fileStreamʧ��");
        	}
        }        	  	
   }
	
	//д������־
	public void wExceptionLog(String ClassMethodName, Exception e, String User, String Inst){
   	    if(logPath == null)
   		logPath = cfg.getvalue("LogDir");//��־·��������jndi.properties����
   	 String curdat=getDate("yyyyMMdd");
     String logDir = logPath + File.separator + curdat;
     String fileName = logDir + File.separator + "Exceptionlog.txt";
     File myFile = new File(logDir);
     if(!myFile.exists())
         myFile.mkdirs();
        FileOutputStream fileStream = null;
        try {
			fileStream = new FileOutputStream(fileName, true);
			String currentTime = getDate("HH:mm:ss");;
            String contents = "[Time:" + currentTime + "]["+ClassMethodName+"][User:" + User + "][Dept:" + Inst + "]\n";
			e.printStackTrace(pWriter);
			fileStream.write(contents.toString().getBytes());
			fileStream.write(sWriter.toString().getBytes());
		} catch (Exception e1) {
			System.out.println("[wExceptionLog��]" + e.toString());
		}finally{
        	try{
        		if(fileStream!=null){
        			fileStream.close();
        		}         		
        	}catch(Exception e1){
        		System.out.println("�ر�Exceptionlog fileStreamʧ��");
        	}
        }        	  	
   }
	
	//����ָ����ʽȡ��ǰ����
	public String getDate(String formatstr){ 

	       TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");   	
		   SimpleDateFormat fm = new SimpleDateFormat(formatstr);
		   fm.setTimeZone(timeZone);
	    	Date myDate = new Date();
	      	
	       String datenow = fm.format(myDate);


	        return datenow;
	    }
	
	//logTypeΪ1ʱ��Ҫͨ���˺���ȡ��ԭ�����ĵ����������������Ǹ�����д����־
	public String getUpClassMethodName(String ClassMethodName)
	{
		try
		{
			String fullClassName = ClassMethodName;
	    	StackTraceElement stack[] = (new Throwable()).getStackTrace();
	    	int ix = 0;
	        while (ix < stack.length) 
	        {
	            StackTraceElement frame = stack[ix];
	            String cname = frame.getClassName();
	            //System.out.println("��"+cname+"�е��ôη����ķ�������"+frame.getMethodName());
	            if (cname.equals(fullClassName)) 
	            {
	                break;
	            }
	            ix++;
	        }
	        
	        while (ix < stack.length) 
	        {
	            StackTraceElement frame = stack[ix];
	            String cname = frame.getClassName();
	            //System.out.println("��"+cname+"�е��ôη����ķ�������"+frame.getMethodName());
	            if (!cname.equals(fullClassName)) 
	            {
	            	return cname+":"+frame.getMethodName();
	            }
	            ix++;
	        }
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
