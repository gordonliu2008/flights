package services.pub;
import java.io.*; 
public class ChineseMoney {

	
	private String number[]= 

	{"","Ҽ","��","��","��","��","½","��","��","��"}; 
	private String unit[]={"","ʰ","��","Ǫ"}; 
	private String small[]={"��","��"}; 
	//private String strNumber,strUnit,strAll; 

	//�Ƿ���number�� 
	private boolean IsInNumber(String strNumber) 
	{ 
	boolean inNumber=false; 
	for (int i=0;i<9;i++) 
	{ 
	if (strNumber.compareTo (number[i])==0) inNumber=true; 
	} 
	return inNumber; 
	} 


	private String SplitChineseNumber(int intUnit,String strInt) 
	{ 
	int l=strInt.length (); 
	int j,k,zeorCountTemp=0; 
	String strUnit="",strNumber="",strAll=""; 

	//�ж���ǧ����λ �Ƿ�ȫΪ0���ǵĻ��������ء��򡱣����ء����� 
	boolean temp=false; 
	for (k=0;k<l;k++) 
	{ 
	String strTemp=strInt.substring(k,k+1); 
	int intTemp=Integer.parseInt(strTemp); 

	if (intTemp!=0) temp=true; 
	} 
	if (temp==false) 
	{ 
	if (intUnit==5)return ""; 
	} 


	int checkK=0; 
	//��ʽ��ʼת�� 
	for (k=0;k<l;k++) 
	{ 
	String strTemp=strInt.substring(k,k+1); 
	int intTemp=Integer.parseInt(strTemp); 
	strNumber= number[intTemp]; 

	//j �� 
	j=l-1-k; 

	strUnit=unit[j]; 


	//��ֵ����λ 
	//�����ֵ��0����ֵ������ 
	if (intTemp==0) 
	{ 
	// 
	if (zeorCountTemp==0) 
	{ 
	//��λ���� 
	strUnit=strUnit.replace('ʰ','��'); 
	strUnit=strUnit.replace('��','��'); 
	strUnit=strUnit.replace('Ǫ','��'); 
	} 
	else 
	{ 
	//��������£���λ������ 
	strUnit=strUnit.replaceAll("ʰ",""); 
	strUnit=strUnit.replaceAll("��",""); 
	strUnit=strUnit.replaceAll("Ǫ",""); 
	} 
	zeorCountTemp++; 
	} 
	checkK=k; 
	strAll+=strNumber+strUnit; 
	} 

	return strAll; 
	} 

	private String onlyInt(int intInt) 
	{ 
	String strInt; 
	strInt=String.valueOf(intInt); 
	int l=strInt.length(); 

	String strAll=""; //������λ һ�ָ� ������ 
	if (l>8)//�� 


	{ 
	strAll+=this.SplitChineseNumber(9,strInt.substring(0,l-8))+"��"; 
	strAll+=this.SplitChineseNumber(5,strInt.substring(l-8,l-4))+"��"; 
	strAll+=this.SplitChineseNumber(1,strInt.substring(l-4,l))+"Ԫ"; 
	} 
	else if (l>4)//�� { 
	{ strAll+=this.SplitChineseNumber(5,strInt.substring(0,l-4))+"��"; 
	strAll+=this.SplitChineseNumber(1,strInt.substring(l-4,l))+"Ԫ"; 

	} 
	else if (l>0) 
	{ 
	strAll+=this.SplitChineseNumber(1,strInt)+"Ԫ"; 
	}// 
	// 
	// 
	// 
	// 100101000 
	int checkL=strAll.length(); 

	char strTemp2; 
	for (int k=1;k<checkL;k++) 
	{ 
	strTemp2=strAll.charAt(k); 
	if (strTemp2=='��') 
	{ 
	//�ж����ǰ���Ƿ������֣���������ɾ������� 
	String strBeforeTemp=strAll.substring(k-1,k); 
	String strAfterTemp=strAll.substring(k+1,k+2); 
	if (!this.IsInNumber(strBeforeTemp)&&!this.IsInNumber 

	(strAfterTemp)) 
	{ 
	strBeforeTemp=strAll.substring(0,k); 
	strAfterTemp=strAll.substring(k+1,checkL); 
	strAll= strBeforeTemp+strAfterTemp; 
	break; 
	} 

	} 
	} 

	return strAll; 

	} 

	private String onlySmall(int intSmall) 
	{ 
	  String strNumber,strUnit,strAll; 
	  strNumber="";strUnit="";strAll=""; 
	  String strSmall,strTemp; 
	  strSmall=String.valueOf(intSmall); 
	  int i; 
	  if (intSmall>=10) 
	  { 
	    for (i=0;i<strSmall.length();i++) 
	    { 
	    strTemp=String.valueOf(intSmall).substring(i,i+1); 
	      if (Integer.parseInt(strTemp)!=0) 
	      { 
	      strNumber=number[Integer.parseInt(strTemp)]; 
	      strUnit=small[i]; 
	      strAll+=strNumber+strUnit; 
	      } 
	    } 
	  } 
	  else 
	  { 
	    if (intSmall!=0) 
	    { 
	    strNumber=number[intSmall]; 
	    strUnit=small[1]; 
	    strAll+=strNumber+strUnit; 
	    } 
	  } 

	return strAll; 
	} 

	public String getChineseMoney(double number) 
	{ 
	//�������� 
	number=(number*100+0.5)/100; 

	String strAll,strChineseInt,strChineseSmall,strZheng;; 
	int intInt,intSmall; 
	strChineseInt="";strChineseSmall="";strZheng=""; 

	//�������� 
	intInt=(int)( number*100/100); 
	if (intInt!=0) 
	{ 
	strChineseInt=onlyInt(intInt); 
	} 
	//С������ 
	double temp=(number-intInt)*100*100/100; 
	//��С�������������� 
	intSmall=(int)(temp*100+0.5)/100; 
	if (intSmall!=0) 
	{ 
	strChineseSmall=onlySmall(intSmall); 
	} 
	else 
	{ 
	strZheng="��"; 
	} 
	strAll=strChineseInt+strChineseSmall+strZheng; 
	return strAll; 
	} 
	public static void main(String args[]) throws IOException 
	{ 
	ChineseMoney cm=new ChineseMoney(); 
	double money; 
	String strMoney,strChineseMoney; 
	strMoney=""; 
	//��ȡ 
	System.out.println("�������(��������):"); 
	BufferedReader cin = new BufferedReader(new InputStreamReader 

	(System.in)); 
	strMoney = cin.readLine(); 
	money=Double.parseDouble(strMoney); 
	//money=12346.465;//Double.parseDouble(strMoney); 
	strChineseMoney=cm.getChineseMoney(money); 
	System.out.println(strChineseMoney); 
	} 
}
