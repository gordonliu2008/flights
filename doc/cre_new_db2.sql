
--FTP���ñ�
drop table pub_ftpcfg;
create table pub_ftpcfg (
FTPCFG_ID      VARCHAR(10)       not null,
--FTPCFGID   
FTPCFGNAME      VARCHAR(20),
--����   
IPADR      VARCHAR(20),
--IP   
PORT      VARCHAR(5),
--�˿�   
USRNAM      VARCHAR(20),
--�û�   
USRPWD      VARCHAR(20),
--����   
OBJDIR      VARCHAR(60),
--Ŀ��·��   
OBJFIL      VARCHAR(60),
--Ŀ���ļ�   
LCLDIR      VARCHAR(60),
--����·��   
LCLFIL      VARCHAR(60),
--�����ļ�   
MODE      VARCHAR(10)  default 'bin',
--ģʽ   bin or ascii
RMK      VARCHAR(100),
--��ע   
TMPFLAG      VARCHAR(1)  default '0'
--��ʱ��־   
);
ALTER TABLE pub_ftpcfg ADD PRIMARY KEY (FTPCFG_ID);


--���Ŷ���
drop table pub_textmsgque;
create table pub_textmsgque (
TEXTMSG_ID      VARCHAR(30)       not null,
--����ID   
MSG_DATE      VARCHAR(8),
--��������   
BATCH_ID      VARCHAR(24),
--���κ�   
MOBILE      VARCHAR(15)  default '0',
--�ֻ���   
TEXTCONTENT      VARCHAR(200)  default '0',
--����   
EXPIREDATE      VARCHAR(14),
--��������
MSG_TYPE      VARCHAR(2)  default '0',
--����   0,��ʱ;1,�û�����,2,����
FLAG      VARCHAR(2)  default '0',
--���ͱ�־   0������;1��ͨ��;2���ѷ���
TMPFLAG      VARCHAR(1)  default '0'
--��ʱ��־   
receivetype char(10) default '';
--���������� mng,����;cus,�ͻ�
receiver char(60) default '';
--�����˱�ʶ
);
ALTER TABLE pub_textmsgque ADD PRIMARY KEY (TEXTMSG_ID);


--��ʱ������ˮ��
drop table pub_schedulejnl;
create table pub_schedulejnl (
SCHEDULE_ID      VARCHAR(10)       not null,
--����ID   
TIME      VARCHAR(14)       not null,
--ʱ��   
YEAR      VARCHAR(4)  default '0',
--���   
MONTH      VARCHAR(2)  default '0',
--�·�   
DAY      VARCHAR(2)  default '0',
--����   
HOUR      VARCHAR(2)  default '0',
--Сʱ   
MINUTE      VARCHAR(2)  default '0',
--����   
FINISH_FLAG      VARCHAR(2)  default '0',
--��ɱ�־   0��δ��ʼ;1��ִ����;2���ɹ�;3��ʧ��
REMARK      VARCHAR(200)  default '0',
--���� 
TMPFLAG      VARCHAR(1)  default '0'
--��ʱ��־   
);
ALTER TABLE pub_schedulejnl ADD PRIMARY KEY (SCHEDULE_ID,TIME);


--��ʱ�����
drop table pub_scheduleinfo;
create table pub_scheduleinfo (
SCHEDULE_ID      VARCHAR(10)       not null,
--����ID   
SCHEDULE_DESC      VARCHAR(60),
--����˵��   
SCHEDULE_CLASS      VARCHAR(60),
--����   
SCHEDULE_METHOD      VARCHAR(20),
--������   
MONTH      VARCHAR(30)  default '0',
--ִ���·�   
DAY      VARCHAR(30)  default '0',
--����   
WEEKDAY      VARCHAR(30)  default '0',
--���ڼ�   
HOUR      VARCHAR(30)  default '0',
--Сʱ   
MINUTE      VARCHAR(30)  default '0',
--����   
FREQUENCY      VARCHAR(20)  default '0',
--Ƶ��   
FLAG      VARCHAR(2)  default '0',
--��Ч��־   1����Ч;2����Ч
FINISH_FLAG      VARCHAR(2)  default '0',
--��ɱ�־   0��δ��ʼ;1��ִ����;2���ɹ�;3��ʧ��
USERID      VARCHAR(64)  default 'huangchi',
--��¼��   
CRE_DATE      VARCHAR(8)  default '20000101',
--��������   
END_DATE      VARCHAR(8)  default '30000101',
--��������   
ADDINFO      VARCHAR(250)  default '30000101',
--��Ϣ 
TMPFLAG      VARCHAR(1)  default '0'
--��ʱ��־ 
monflg       char(1) default '0';
--��ر�־  0��Ĭ�ϲ����;1����� 
montyp       char(10) default 'day';
--��ʱ���� 
manager      char(60) default '';  
--����Ա
);
ALTER TABLE pub_scheduleinfo ADD PRIMARY KEY (SCHEDULE_ID);


--ͨѶ¼��Ϣ��
drop table contactsinfo;
create table contactsinfo (
OWNER_EMP      VARCHAR(20)       not null,
--Ա�����   
PK_EMP      VARCHAR(30)       not null,
--ͨѶ¼Ա�����   
USERID      VARCHAR(64),
--��¼��   
EMPNAME      VARCHAR(30)  default '0',
--����   
BUSINESS_PHONE      VARCHAR(30)  default '0',
--�칫�绰   
MOBILE      VARCHAR(40)  default '0',
--�ֻ���   
DEPT_NAME      VARCHAR(60)  default '0',
--������������   
JOB_DESC      VARCHAR(100)  default '0',
--ְ������   
GENDER_DESC      VARCHAR(10)  default '0',
--�Ա�����   
LAST_VISIT_DATE      VARCHAR(8),
--�ϴη�������   
VISIT_TIMES      VARCHAR(10),
--���ʴ���   
CONSTACTS_FLAG      VARCHAR(1)  default '0',
--��־   0������;1����Ч;2����Ч
CONSTACTS_DESC      VARCHAR(200)  default '0',
--��ע   
TMPFLAG      VARCHAR(1)  default '0'
--��ʱ��־   
);
ALTER TABLE contactsinfo ADD PRIMARY KEY (OWNER_EMP,PK_EMP);


--ƴ�����ձ���Ϣ��
drop table para_pinyin;
create table para_pinyin (
CHNCODE      VARCHAR(2)       not null,
--�����ַ�   
PINYINCODE      VARCHAR(30),
--ƴ��   
PINYINFIRST      VARCHAR(2)
--ƴ������ĸ   
);
ALTER TABLE para_pinyin ADD PRIMARY KEY (CHNCODE);


--������Ϣ��
drop table branchinfo;
create table branchinfo (
PK_CORP      VARCHAR(10)       not null,
--���к�   
PK_FATHER_CORP      VARCHAR(10),
--�ϼ����к�   
PK_CORPTYPE      VARCHAR(20),
--��������   
UNITCODE      VARCHAR(20)  default '0',
--������   
UNITNAME      VARCHAR(60)  default '0',
--��������   
UNITSHORTNAME      VARCHAR(60)  default '0',
--���м��   
BRANCH_NO      VARCHAR(6)  default '0',
--���к�   
BRANCH_FLAG      VARCHAR(1)  default '0',
--��Ч��־   0������;1����Ч;2����Ч
CRE_DATE      VARCHAR(8)  default '20000101',
--��������   
END_DATE      VARCHAR(8)  default '30000101',
--��������   
TMPFLAG      VARCHAR(1)  default '0'
--��ʱ��־   
);
ALTER TABLE branchinfo ADD PRIMARY KEY (PK_CORP);


--������Ϣ��
drop table deptinfo;
create table deptinfo (
PK_DEPT      VARCHAR(20)       not null,
--����   
PK_CORP      VARCHAR(10),
--���к�   
DEPT_CODE      VARCHAR(10),
--���ű��   
DEPT_NAME      VARCHAR(60)  default '0',
--��������   
DEPT_SHORT_NAME      VARCHAR(10)  default '0',
--���ż��   
PK_FATHER_DEPT      VARCHAR(20)  default '0',
--�ϼ����Ŵ���   
DEPT_SEQ      VARCHAR(500)  default '0',
--�������   
DEPT_KIND      VARCHAR(20)  default '0',
--����   
DEPT_NO      VARCHAR(6)  default '0',
--���ź�   
BELONG_DEPT_NO      VARCHAR(6)  default '0',
--�ϼ����ź�   
BRANCH_NO      VARCHAR(6)  default '0',
--���к�   
DEPT_FLAG      VARCHAR(1)  default '0',
--��Ч��־   0������;1����Ч;2����Ч
CRE_DATE      VARCHAR(8)  default '20000101',
--��������   
END_DATE      VARCHAR(8)  default '30000101',
--��������   
TMPFLAG      VARCHAR(1)  default '0'
--��ʱ��־   
);
ALTER TABLE deptinfo ADD PRIMARY KEY (PK_DEPT);


--Ա����Ϣ��
drop table staffinfo;
create table staffinfo (
PK_EMP      VARCHAR(20)       not null,
--Ա�����   
USERID      VARCHAR(64),
--��¼��   
PK_CORP      VARCHAR(10),
--���к�   
PK_PSNCL      VARCHAR(20),
--Ա�����   
EMPCODE      VARCHAR(20)  default '0',
--����   
EMPNAME      VARCHAR(30)  default '0',
--����   
PID      VARCHAR(18)  default '0',
--���֤��   
BUSINESS_PHONE      VARCHAR(40)  default '0',
--�칫�绰   
MOBILE      VARCHAR(40)  default '0',
--�ֻ���   
EMAIL      VARCHAR(60)  default '0',
--����   
DEPT_ID      VARCHAR(20)  default '0',
--��������   
DEPT_NAME      VARCHAR(60)  default '0',
--������������   
DEPT_NO      VARCHAR(6)  default '0',
--���ź�   
BRANCH_NO      VARCHAR(6)  default '0',
--���к�   
JOB_ID      VARCHAR(20)  default '0',
--ְ����   
JOB_DESC      VARCHAR(100)  default '0',
--ְ������   
PK_JOB_LEVEL      VARCHAR(20)  default '0',
--ְ�񼶱�   
GENDER_ID      VARCHAR(20)  default '0',
--�Ա���   
GENDER_DESC      VARCHAR(10)  default '0',
--�Ա�����   
BIRTHDAY      VARCHAR(10),
--����   
WORK_DATE      VARCHAR(10),
--��������   
BOC_STARTDATE      VARCHAR(10),
--��������   
BOCCORP_STARTDATE      VARCHAR(10),
--����������   
OUT_DATE      VARCHAR(10),
--��ְ����   
USER_FLAG      VARCHAR(1)  default '0',
--��Ч��־   0������;1����Ч;2����Ч
USER_DESC      VARCHAR(500)  default '0',
--����˵��   
TMPFLAG      VARCHAR(1)  default '0'
--��ʱ��־   
);
ALTER TABLE staffinfo ADD PRIMARY KEY (PK_EMP);


--�Ѻ����ʲ�С���
drop table AssDetTypTbl;
create table AssDetTypTbl (
AssTyp      CHAR(4),
--�����ʲ�����   
AssNam      VARCHAR(100),
--�����ʲ�����   
AssDetTyp      CHAR(4)       not null,
--�����ʲ�С��   
AssDetNam      VARCHAR(100),
--�����ʲ�����   
BankNo      CHAR(6)       not null,
--���к�   
TmpFlg      CHAR(1)  default '0'
--���ñ�־   ���ñ�־
);
ALTER TABLE AssDetTypTbl ADD PRIMARY KEY (AssDetTyp,BankNo);


--�����������ݱ�
drop table fin_pro_bigjnl;
create table fin_pro_bigjnl (
Logno       CHAR(14)       not null,
--��ˮ�� 
Tckno       CHAR(12)       not null,
--��Ʊ��
ActDat      CHAR(8)       not null,
--��������  
en_name1      VARCHAR(100)  default '',
--һ��Ԥ�㵥λ   
en_name       VARCHAR(100)  default '',
--����Ԥ�㵥λ   
mk_name       VARCHAR(100)  default '',
--�ʽ�����  
payee_account_no      VARCHAR(42)  default '',
--�տ����˺� 
payee_account_name      VARCHAR(100)  default '',
--�տ���   
payee_account_bank      VARCHAR(100)  default '',
--�տ�������   
txnamt      int  default '0',
--���׽��
pay_summary_name      VARCHAR(300)  default '',
--����   
TMPFLAG      VARCHAR(1)  default '0'
--��ʱ��־   
);
ALTER TABLE fin_pro_bigjnl ADD PRIMARY KEY (Logno);
create index ni1_fin_pro_bigjnl on fin_pro_bigjnl(ActDat,TxnAmt);