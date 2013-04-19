package services.pub;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.http.*;

public class HPub {
	/**
	 * Method��public String trimNull(String source)
	 * Function:
	 * 		���ַ����Ŀ�ֵ��ȡ
	 * ���������
	 * 			source��Դ�ַ���
	 * �������: ���������Ľڵ�
	 * 
	 */		
	public String trimNull(String source)
	{
		String resStr = source;
		if(resStr==null)resStr="";
		return resStr.trim();
	}
	
	/**
	 * Method��public String objToJson(Object source)
	 * Function:
	 * 		��sourceתΪjson��ʽ��String
	 * ���������
	 * 			source��Դ�ַ���
	 * �������: ���������Ľڵ�
	 * 
	 */		
	public String objToJson(Object source)
	{
		StringBuffer s1=new StringBuffer();
		if(source.getClass().getName().equals("java.util.Vector"))
		{
			s1.append("[");
			if(source!=null)
			{
				Vector tmpV = (Vector)source;
				for(int i=0;i<tmpV.size();i++)
				{
					Hashtable tmph = (Hashtable) tmpV.elementAt(i);
					s1.append("{");
					s1.append(objToJson(tmph));
					s1.append("}");
					if(i!=tmpV.size()-1)s1.append(",");
				}
			}
			s1.append("]");
			return s1.toString();
		}
		else if(source.getClass().getName().equals("java.util.Hashtable"))
		{
			if(source!=null)
			{
				java.util.Hashtable tmpH = (java.util.Hashtable)source;
				for(Iterator it = tmpH.keySet().iterator(); it.hasNext();)   
				  {   
			          String fldNam   =   (String)it.next();   
			          Object fldObj   =   tmpH.get(fldNam); 
			          String fldVal   =   objToJson(fldObj);
			          //if(fldNam.equals("children"))s1.append(","+fldNam+":["+fldVal+"]");
			          //else 
			        	  s1.append(",\""+fldNam+"\":"+fldVal+"");
				  }
				return s1.toString().substring(1);
			}
			else
			{
				return "null";
			}
		}
		else if(source.getClass().getName().equals("java.lang.String"))
		{
			if(source!=null)
			{
				String tmpS = (java.lang.String)source;
				return "\""+tmpS+"\"";
			}
			else
			{
				return "null";
			}
		}
		
		return s1.toString();
	}
	
	/**
	 * Method��public String GETDATE(String format,int n)
	 * Function:
	 * 		ȡָ����ʽ��ʱ��
	 * ���������
	 * 			format:ʱ���ʽ,n:�Ե�ǰ��������
	 * �������: ʱ��
	 * 
	 */	
	  public String getDate(String format,int n)/*yyyyMMddHHmmss*/
	  {
		  Calendar   cal   =   Calendar.getInstance();
	      cal.setTime(new   java.util.Date());     //ע��
	      cal.add(java.util.Calendar.DAY_OF_YEAR,   n);
	      java.util.Date   date1   =   cal.getTime();

	      SimpleDateFormat df1 = new SimpleDateFormat(format);
	      String CurDate=(String)df1.format(date1);
	      return CurDate;
	  }
	  
	  public String getIpAddr(HttpServletRequest request) {
	        String ip = request.getHeader("x-forwarded-for");
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("Proxy-Client-IP");
	        }
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getRemoteAddr();
	        }
	        return ip;
	    }
	  public String getSessionID(HttpServletRequest request) {
	        String ip = request.getSession().getId();
	        return ip;
	    }
}
