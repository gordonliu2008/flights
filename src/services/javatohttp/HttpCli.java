package services.javatohttp;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import services.db.ReadCFG;

public class HttpCli {
	private static ReadCFG cfg = new ReadCFG();
	private static String charset = cfg.getvalue("charset");
	
	
	public  String get1(String url) {
		  String resp="";
		  //����HttpClient��ʵ��
		  HttpClient httpClient = new HttpClient();
		  //����GET������ʵ��
		  //GetMethod getMethod = new GetMethod("http://www.ceair.com/mu/front/reservation/flight-search!doFlightSearch.shtml?cond.tripType=OW&cond.depCode=WUH&cond.depDate=2013-03-13&cond.arrCode=PEK&cond.arrDate=2013-03-13&cond.depCodeInt=&cond.arrCodeInt=&cond.depDateInt=2013-03-13&cond.depCodeIntRt=&cond.arrCodeIntRt=&cond.depDateIntRt=2013-03-13&cond.cabinRank=ECONOMY&cond.adultNumber=1&cond.childNumber=0&cond.sortType=1&cond.isInternationalFlight=331&cond.region=CN");
		  //GetMethod getMethod = new GetMethod("http://flight.qunar.com/twell/longwell?&http%3A%2F%2Fwww.travelco.com%2FsearchArrivalAirport=%E6%AD%A6%E6%B1%89&http%3A%2F%2Fwww.travelco.com%2FsearchDepartureAirport=%E5%8C%97%E4%BA%AC&http%3A%2F%2Fwww.travelco.com%2FsearchDepartureTime=2013-03-31&http%3A%2F%2Fwww.travelco.com%2FsearchReturnTime=2013-03-31&locale=zh&nextNDays=0&searchLangs=zh&searchType=OneWayFlight&tags=1&from=fi_ont_search&_token=42747");
		  
		  System.out.println(url);
		  GetMethod getMethod = new GetMethod(url);
		  //ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����
		  getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
		    new DefaultHttpMethodRetryHandler());
		  try {
		   //ִ��getMethod
		   int statusCode = httpClient.executeMethod(getMethod);
		   if (statusCode != HttpStatus.SC_OK) {
		    System.err.println("Method failed: "
		      + getMethod.getStatusLine());
		   }
		   //��ȡ���� 
		   byte[] responseBody = getMethod.getResponseBody();
		   //��������
		   resp = new String(responseBody,charset);
		   //System.out.println(new String(responseBody,charset));
		  } catch (HttpException e) {
		   //�����������쳣��������Э�鲻�Ի��߷��ص�����������
		   System.out.println("Please check your provided http address!");
		   e.printStackTrace();
		  } catch (IOException e) {
		   //���������쳣
		   e.printStackTrace();
		  } catch (Exception e) {
			  e.printStackTrace();
		  } finally {
		   //�ͷ�����
		   getMethod.releaseConnection();
		   System.out.println("getMethod released");
		   //
		  }
		  return resp;
		 }
	
	public  static void post1(String url) {
		  //����HttpClient��ʵ��
		  HttpClient httpClient = new HttpClient();
		  //String url = "http://flight.mangocity.com/flights-search.shtml";
		  PostMethod postMethod = new PostMethod(url);
		  //GetMethod getMethod = new GetMethod("http://flight.mangocity.com/oneway-Cheapest.shtml?
		  //id_dpt=WUH&id_arr=PEK&startDate=2013-03-31&id_lineType1=ow");
		  
		  try {
		  // ������������ֵ
		  NameValuePair[] data = { new NameValuePair("id_dpt", "WUH"),new NameValuePair("id_arr", "PEK"),				
		  new NameValuePair("startDate", "2013-03-31"),new NameValuePair("id_lineType1", "ow") };
		  // ������ֵ����postMethod��
		  postMethod.setRequestBody(data);
		  // ִ��postMethod
		  int statusCode = httpClient.executeMethod(postMethod);
		  // HttpClient����Ҫ����ܺ�̷����������POST��PUT�Ȳ����Զ�����ת��
		  // 301����302
		  System.out.println("statusCode:" + statusCode);
		  if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || 
		  statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
		      // ��ͷ��ȡ��ת��ĵ�ַ
		      Header locationHeader = postMethod.getResponseHeader("location");
		      String location = null;
		      if (locationHeader != null) {
		       location = locationHeader.getValue();
		       System.out.println("The page was redirected to:" + location);
		      } else {
		       System.err.println("Location field value is null.");
		      }
		      return;
		  }
		//��ȡ���� 
		   byte[] responseBody = postMethod.getResponseBody();
		   //��������
		   System.out.println(new String(responseBody,"UTF-8"));
		  } catch (HttpException e) {
			   //�����������쳣��������Э�鲻�Ի��߷��ص�����������
			   System.out.println("Please check your provided http address!");
			   e.printStackTrace();
			  } catch (IOException e) {
			   //���������쳣
			   e.printStackTrace();
			  } finally {
			   //�ͷ�����
				  postMethod.releaseConnection();
			  }
		 }

}
