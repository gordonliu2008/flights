package services.db;

import java.util.*;

import services.base.Base;
import services.pub.*;
import services.log.*;
import javax.servlet.http.*;
public class DBFuncs extends Base{
    
	private String tblnam="";//����
	private String user="000000";
	private String inst="000000";
	private DBAcc dba;
	
	public   DBFuncs()
	{
		  
		  dba = new DBAcc(); // ���ݿ����ʵ��
	}
	
	/**
	 * Method��public   DBFuncs(HttpServletRequest request,String tblNam)
	 * Function:
	 * 		ѡ���Ĭ�ϵ�JNDI
	 * ���������
	 * 			jndiNam��
	 */	
	
	/*public   DBFuncs(HttpServletRequest request,String tblNam)
	{
		  super(request,1); 
		  this.tblnam=tblNam;
		//if(this.getRspFlg()==false);
	}*/
	private HLog hlog = new HLog(); // 
	//private DBAcc dba = new DBAcc(); // ���ݿ����ʵ��
	
	/**
	 * Method��public Hashtable readSingleRecord(String sql)
	 * Function:
	 * 		��ȡ����sql
	 * ���������
	 * 			sql��
	 * �������:  �������н���ֶε�Hashtable
	 * 
	 */	
	public Hashtable readSingleRecord(String sql)
	{
		 hlog.wTransLog(1,this.getClass().getName(), "readSingleRecord",
		  		  sql, user, inst);
		 try
		  {
			  Vector tmpv = dba.doQuery(sql);//��ѯ�û���Ϣ
			  if(!dba.isSucc())//��ѯʧ��
			  {
				  setMsg(dba.getMsg());
				  setRspFlg(false);
				  return null;
			  }
			  else if(tmpv.isEmpty())//��ѯ������Ϣ
			  {
				  return null;
			  }
			  else
			  {
				  Hashtable tmph = (Hashtable) tmpv.elementAt(0);
				  return tmph;
			  }
			  
		  }
		  catch(Exception e)
		  {
			    hlog.wTransLog(1,this.getClass().getName(), "readSingleRecord:"+sql+":error",
						e.getMessage(), user, inst);
			    hlog.wExceptionLog(this.getClass().getName(),e, user, inst);
				e.printStackTrace();
				setMsg("ִ��"+sql+"ʱ����" + e.getMessage(),false);
				
		  }
		  return null;	     
		
	}
	
	/**
	 * Method��public Vector queryTableAll(String sql)
	 * Function:
	 * 		������ѯ
	 * ���������
	 * 			sql��
	 * �������:  �������м�¼��Vector
	 * 
	 */	
	public Vector queryTableAll(String sql)
	{
		 hlog.wTransLog(1,this.getClass().getName(), "queryTableAll",
		  		  sql, user, inst);
		 try
		  {
			  //long time = System.currentTimeMillis();
			  Vector tmpv = dba.doQuery(sql);//��ѯ�û���Ϣ
			  //System.out.println("xx1:"+(System.currentTimeMillis()-time));
			  if(!dba.isSucc())//��ѯʧ��
			  {
				  setMsg(dba.getMsg());
				  setRspFlg(false);
				  return null;
			  }
			  else if(tmpv.isEmpty())//��ѯ������Ϣ
			  {
				  return null;
			  }
			  else
			  {
				  return tmpv;
			  }
			  
		  }
		  catch(Exception e)
		  {
			    hlog.wTransLog(1,this.getClass().getName(), "queryTableAll:"+sql+":error",
						e.getMessage(), user, inst);
			    hlog.wExceptionLog(this.getClass().getName(),e, user, inst);
				e.printStackTrace();
				setMsg("ִ��"+sql+"ʱ����" + e.getMessage(),false);
				
		  }
		  return null;	     
		
	}
	
	/**
	 * Method��public Vector queryTableAll(String sql, int rowStart, int pagesize )
	 * Function:
	 * 		������ѯ(��ҳ)
	 * ���������
	 * 			sql��
	 * �������:  �������м�¼��Vector
	 * 
	 */	
	public Vector queryTableAll(String sql, int rowStart, int pagesize )
	{
		 hlog.wTransLog(1,this.getClass().getName(), "queryTableAll",
		  		  sql, user, inst);
		 try
		  {
			  Vector tmpv = dba.doQuery(sql,rowStart,pagesize);//��ѯ�û���Ϣ
			  if(!dba.isSucc())//��ѯʧ��
			  {
				  setMsg(dba.getMsg());
				  setRspFlg(false);
				  return null;
			  }
			  else if(tmpv.isEmpty())//��ѯ������Ϣ
			  {
				  return null;
			  }
			  else
			  {
				  return tmpv;
			  }
			  
		  }
		  catch(Exception e)
		  {
			    hlog.wTransLog(1,this.getClass().getName(), "queryTableAll:"+sql+":error",
						e.getMessage(), user, inst);
			    hlog.wExceptionLog(this.getClass().getName(),e, user, inst);
				e.printStackTrace();
				setMsg("ִ��"+sql+"ʱ����" + e.getMessage(),false);
				
		  }
		  return null;	     
		
	}
	
	/**
	 * Method��public String getSeqNo(String tblNam,String seqFld,int seqLen,String term)
	 * Function:
	 * 		������ȡ˳���
	 * ���������
	 * 			tblNam������;
	 *          seqFld: �ֶ���;
	 *          seqLen: ˳��ŵĳ���;
	 *          term: ��������
	 * �������:  seqLen���ȵ�seqNo
	 * 
	 */	
	public String getSeqNo(String tblNam,String seqFld,int seqLen,String term)
	{
		try
		  {
			  String sql="select max("+seqFld+") "+seqFld+" from "+tblNam+" ";
			  if(!term.equals(""))
				 {
					 sql = sql +" where " + term;
				 }
			  hlog.wTransLog(1,this.getClass().getName(), "getSeqNo",
				  		  sql, user, inst);
			  Vector tmpv = dba.doQuery(sql);//��ѯ�û���Ϣ
			  if(!dba.isSucc())//��ѯʧ��
			  {
				  setMsg(dba.getMsg());
				  setRspFlg(false);
				  return null;
			  }
			  else if(tmpv.isEmpty())//��ѯ������Ϣ
			  {
				  setMsg("��ѯ������Ϣ");
				  setRspFlg(false);
				  return null;
			  }
			  else
			  {
				  Hashtable tmph = (Hashtable) tmpv.elementAt(0);
				  String seqno = ((String) tmph.get(seqFld.toUpperCase())).trim();
				  if(seqno==null||seqno.equals("null")||seqno.equals(""))
				  {
					 String ret1="1";
					 while(ret1.length()<seqLen)ret1="0"+ret1;
					 return ret1;
				  }
				  int iseqno = Integer.parseInt(seqno.substring(seqno.length()-seqLen));
				  iseqno++;
				  
				  String sseqno=""+iseqno;
				  while(sseqno.length()<seqLen)sseqno="0"+sseqno;
				  return sseqno;
			  }
	 }
	catch(Exception e)
	  {
		    hlog.wTransLog(1,this.getClass().getName(), "getSeqNo error",
					e.getMessage(), user, inst);
		    hlog.wExceptionLog(this.getClass().getName(),e, user, inst);
		    e.printStackTrace();
			setMsg("ȡ"+seqFld+"ʱ�����쳣��" + e.getMessage(),false);
			
	  }
		return null;
	}
	
	/**
	 * Method��public boolean execSql(String sql)
	 * Function:
	 * 		ִ��sql
	 * ���������
	 * 			sql��
	 * �������:  ִ�н�� boolean
	 * 
	 */	
	public boolean execSql(String sql)
	{
			  try
			  {
				  
				  String updStr=sql;
				  hlog.wTransLog(1,this.getClass().getName(), "execSql",
				  		  sql, user, inst);
			    	dba.executeUpdate(updStr);
				      if(!dba.isSucc())//updateʧ��
				      {
					      setMsg(dba.getMsg());
					      setRspFlg(false);
					      return false;
				      }
				  
				      return true;
		 }
		catch(Exception e)
		  {
			    hlog.wTransLog(1,this.getClass().getName(), "execSql:"+sql+":error",
					e.getMessage(), user, inst);
		        hlog.wExceptionLog(this.getClass().getName(),e, user, inst);
			    e.printStackTrace();
			    setMsg("ִ��"+sql+"ʱ����" + e.getMessage(),false);
				
		  }
		  return false;
	}
	
	/**
	 * Method��public boolean execSql(String sql)
	 * Function:
	 * 		ִ��sql
	 * ���������
	 * 			sql��
	 * �������:  ִ�н�� boolean
	 * 
	 */	
	public boolean execDB2import(String sql)
	{
			  try
			  {
				  
				  String updStr=sql;
				  hlog.wTransLog(1,this.getClass().getName(), "execSysSql",
				  		  sql, user, inst);
			    	dba.execDB2import(updStr);
				      if(!dba.isSucc())//updateʧ��
				      {
					      setMsg(dba.getMsg());
					      setRspFlg(false);
					      return false;
				      }
				  
				      return true;
		 }
		catch(Exception e)
		  {
			    hlog.wTransLog(1,this.getClass().getName(), "execSql:"+sql+":error",
					e.getMessage(), user, inst);
		        hlog.wExceptionLog(this.getClass().getName(),e, user, inst);
			    e.printStackTrace();
			    setMsg("ִ��"+sql+"ʱ����" + e.getMessage(),false);
				
		  }
		  return false;
	}
	
	/**
	 * Method��public boolean execSqlBatch(Vector sqlVec)
	 * Function:
	 * 		����ִ��sql
	 * ���������
	 * 			sqlVec��������Ҫִ�е�sql��Vector
	 * �������:  ִ�н�� boolean
	 * 
	 */
	public boolean execSqlBatch(Vector sqlVec)
	{
	    try
		    {
	    	      if(sqlVec!=null||sqlVec.size()>0)
	    	      {
	    	    	  for(int i =0;i<sqlVec.size();i++)
	    				{
	    	    		    String sqlNow = (String)sqlVec.elementAt(i);  
	    	    		    hlog.wTransLog(1,this.getClass().getName(),"executeBatchUpdate",
	    							sqlNow, user, inst);
	    				}
	    	      }
	    	      else
	    	      { 
	    	    	  setMsg("����Ҫִ�е�sql");
				      setRspFlg(false);
				      return false;
	    	      }
	    	      //dba.executeBatchUpdate(sqlVec);
	    	      if(!dba.executeBatchUpdate(sqlVec)||!dba.isSucc())//updateʧ��
			      {
				      setMsg(dba.getMsg());
				      setRspFlg(false);
				      return false;
			      }
	    	      return true;
		     }
		catch(Exception e)
		     {
			    hlog.wTransLog(1,this.getClass().getName(), "execSqlBatch error",
					e.getMessage(), user, inst);
		        hlog.wExceptionLog(this.getClass().getName(),e, user, inst);
			    e.printStackTrace();
			    setMsg("����ִ�����ʱ����" + e.getMessage(),false);
		      }
		  return false;
	}
	
	/**
	 * Method��public String getInsertSql(String tblNam, HttpServletRequest request, Hashtable fieldHash)
	 * Function:
	 * 		�Զ���request��fldH��ȡֵ���update ���
	 * ���������
	 * 			tblNam: ��Ҫ����ı���
	 *          request: ���в���������
	 *          fieldHash:�ֹ����õĲ���ֵ
	 * �������:  ִ�н�� boolean
	 * 
	 */
	public String getInsertSql(String tblNam, HttpServletRequest request, Hashtable fieldHash)
	{
	    try
		    {
	    	    StringBuffer sfield = new StringBuffer();
			    StringBuffer svalue = new StringBuffer();
			    Vector vfields=dba.getFields("select * from "+tblNam);
			    for(int i=0;i<vfields.size();i++)
				  {
			    	  String fldInfoStr = (String) vfields.elementAt(i);
			    	  String fldNam=fldInfoStr.split("~")[0];
			    	  String fldTyp=fldInfoStr.split("~")[1];
					  //System.out.println("fields"+i+":"+(String) vfields.elementAt(i));
					  if(fieldHash.get(fldNam)!=null
							  &&!fieldHash.get(fldNam).equals(""))
					  {
						  sfield.append(",");
						  sfield.append(fldNam);
						  if(fldTyp!=null&&(fldTyp.equals("CHAR")||fldTyp.equals("VARCHAR")))
						  {
							  svalue.append(",'");
							  svalue.append(fieldHash.get(fldNam));
							  svalue.append("'");
						  }
						  else
						  {
							  svalue.append(",");
							  svalue.append(fieldHash.get(fldNam));
						  }
						  
					  }
					  else if(request!=null&&getReqFieldValue(fldNam,request)!=null
							  &&!getReqFieldValue(fldNam,request).equals(""))
					  {
						  //System.out.println("xxxgetReqFieldValue,field:"+(String) vfields.elementAt(i)+" ,value:");
						  sfield.append(",");
						  sfield.append(fldNam);
						  if(fldTyp!=null&&(fldTyp.equals("CHAR")||fldTyp.equals("VARCHAR")))
						  {
							  svalue.append(",'");
							  svalue.append(getReqFieldValue(fldNam,request));
							  svalue.append("'");
						  }
						  else
						  {
							  svalue.append(",");
							  svalue.append(getReqFieldValue(fldNam,request));
						  }
						  
					  }
					  
				  }
				  if(sfield.toString()==null||sfield.toString().equals(""))
				  {
					  setMsg("ȡ������:"+tblNam+"���ֶ�ֵ");
				      setRspFlg(false);
				      return null;
				  }
				  String insStr=" insert into "+ tblNam +" ("+ sfield.toString().substring(1) +")" +
				  		"  values ("+ svalue.toString().substring(1) +")";
				  return insStr;
		     }
		catch(Exception e)
		     {
			    hlog.wTransLog(1,this.getClass().getName(), "getInsertSql error",
					e.getMessage(), user, inst);
		        hlog.wExceptionLog(this.getClass().getName(),e, user, inst);
			    e.printStackTrace();
			    setMsg("����insert���ʱ����" + e.getMessage(),false);
		      }
		  return null;
	}
	
	/**
	 * Method��public String getUpdateSql(String tblNam, HttpServletRequest request, Hashtable fieldHash, Hashtable termHash)
	 * Function:
	 * 		�Զ���request��fldH��ȡֵ���update ���
	 * ���������
	 * 			tblNam: ��Ҫ����ı���
	 *          request: ���в���������
	 *          fieldHash,termHash:�ֹ����õĲ���ֵ
	 * �������:  ִ�н�� boolean
	 * 
	 */
	public String getUpdateSql(String tblNam, HttpServletRequest request, Hashtable fieldHash, Hashtable termHash)
	{
	    try
		    {
	    	    
	    	    StringBuffer sfield = new StringBuffer();
			    StringBuffer sterm = new StringBuffer();
			    Vector vfields=dba.getFields("select * from "+tblNam);
			    for(int i=0;i<vfields.size();i++)
				  {
			    	  String fldInfoStr = (String) vfields.elementAt(i);
			    	  String fldNam=fldInfoStr.split("~")[0];
			    	  String fldTyp=fldInfoStr.split("~")[1];
					  //System.out.println("fields"+i+":"+(String) vfields.elementAt(i));
			    	  if(termHash.get(fldNam)!=null
							  &&!termHash.get(fldNam).equals(""))
					  {
			    		  sterm.append(" and ");
			    		  sterm.append(fldNam);
						  if(fldTyp!=null&&(fldTyp.equals("CHAR")||fldTyp.equals("VARCHAR")))
						  {
							  sterm.append("='");
							  sterm.append(termHash.get(fldNam));
							  sterm.append("'");
						  }
						  else
						  {
							  sterm.append("=");
							  sterm.append(fieldHash.get(fldNam));
						  }
						  
					  }
					  if(fieldHash.get(fldNam)!=null
							  &&!fieldHash.get(fldNam).equals(""))
					  {
						  sfield.append("=");
						  sfield.append(fldNam);
						  if(fldTyp!=null&&(fldTyp.equals("CHAR")||fldTyp.equals("VARCHAR")))
						  {
							  sfield.append("='");
							  sfield.append(fieldHash.get(fldNam));
							  sfield.append("'");
						  }
						  else
						  {
							  sfield.append("=");
							  sfield.append(fieldHash.get(fldNam));
						  }
						  
					  }
					  else if(getReqFieldValue(fldNam,request)!=null
							  &&!getReqFieldValue(fldNam,request).equals(""))
					  {
						  //System.out.println("xxxgetReqFieldValue,field:"+(String) vfields.elementAt(i)+" ,value:");
						  sfield.append(",");
						  sfield.append(fldNam);
						  if(fldTyp!=null&&(fldTyp.equals("CHAR")||fldTyp.equals("VARCHAR")))
						  {
							  sfield.append("='");
							  sfield.append(getReqFieldValue(fldNam,request));
							  sfield.append("'");
						  }
						  else
						  {
							  sfield.append("=");
							  sfield.append(getReqFieldValue(fldNam,request));
						  }
						  
					  }
					  
				  }
				  if(sfield.toString()==null||sfield.toString().equals(""))
				  {
					  setMsg("ȡ������:"+tblNam+"���ֶ�ֵ");
				      setRspFlg(false);
				      return null;
				  }
				  if(sterm.toString()==null||sterm.toString().equals(""))
				  {
					  setMsg("ȡ������:"+tblNam+"���ֶ�ֵ");
				      setRspFlg(false);
				      return null;
				  }
				  String updStr=" update "+ tblNam +" set "+ sfield.toString().substring(1) +" " +
				  		"  where 1=1 "+ sterm.toString();
				  return updStr;
		     }
		catch(Exception e)
		     {
			    hlog.wTransLog(1,this.getClass().getName(), "getUpdateSql error",
					e.getMessage(), user, inst);
		        hlog.wExceptionLog(this.getClass().getName(),e, user, inst);
			    e.printStackTrace();
			    setMsg("����update���ʱ����" + e.getMessage(),false);
		      }
		  return null;
	}
	
	public String getReqFieldValue(String field,HttpServletRequest request)
	{
		String value = request.getParameter(field.toLowerCase());
		if(value==null||value.equals(""))
			 value = request.getParameter(field.toUpperCase());
		//System.out.println("getReqFieldValue,field:"+field+" ,value:"+value);
		return value;
	}
	
	
	
}
