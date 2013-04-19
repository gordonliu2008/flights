package services.db;

import java.util.*;

import services.pub.*;
import services.log.*;
import services.base.*;
public class DBFuncs extends Base{
    
	private String tblnam="";//����
	private String user="000000";
	private String inst="000000";
	private DBAcc dba;
	/*public   DBFuncs(HttpServletRequest request)
	{
		  super(request,1); 
		  //user = this.getUserInfoStr(request,"USERID");
		  //inst = this.getUserInfoStr(request,"CRPCOD");
		//if(this.getRspFlg()==false);
		  dba = new DBAcc(); // ���ݿ����ʵ��
	}*/
	public   DBFuncs()
	{
		  
		  dba = new DBAcc(); // ���ݿ����ʵ��
	}
	/*public   DBFuncs(String jndiNam)
    {
          
          dba = new DBAcc(jndiNam); // ���ݿ����ʵ��
    }*/
	/**
	 * Method��public   DBFuncs(HttpServletRequest request,String tblNam)
	 * Function:
	 * 		ѡ���Ĭ�ϵ�JNDI
	 * ���������
	 * 			jndiNam��
	 */	
	/*public   DBFuncs(HttpServletRequest request,String jndiNam)
	{
		  super(request,1); 
		  dba = new DBAcc(jndiNam); // ���ݿ����ʵ��
		//if(this.getRspFlg()==false);
	}*/
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
					 return null;
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

	
	
	
}
