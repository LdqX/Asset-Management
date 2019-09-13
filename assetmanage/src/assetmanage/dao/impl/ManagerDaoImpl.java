package assetmanage.dao.impl;
import assetmanage.entity.*;
import assetmanage.dao.*;
import assetmanage.server.impl.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import java.sql.*;
public class ManagerDaoImpl extends Manager implements ManagerDao,SQL_Manage{
	//�������߾���***
			public boolean chuliqingqiu(ApplyServerImpl shenqing,String id,String dName) throws IOException, SQLException {          		//1 �������󷽷�
				int flag1=0,flag2=0;
				/*Set<String> names=shenqing.applyMap.keySet();
				Iterator<String> it=names.iterator();*/
				ArrayList<Pair<String, Integer>> rs=shenqing.readApply();
				int i=0;
//				while(i<rs.size()) {
//					System.out.println(rs.get(i).left+"\t\t"+rs.get(i).right);
//					i++;
//				}
//				return false;
//				
				while(i<rs.size()) {
					String name=rs.get(i).left;
					Integer num=rs.get(i).right;
//					System.out.println(shenqing.getaore()+"13513");	
					if(shenqing.getaore()) {
//						System.out.println(shenqing.getaore()+"135134246735686358");
						//�ж�����������
						flag1=1;
						int price=SQL_Manage.FindPrice("commonasset",name);
						StoreKeeperDaoImpl sk=new StoreKeeperDaoImpl();
						if(sk.storeDelete(name,num)) {
							inApplyRecord(id,name,num,1,1);
//							System.out.println("�ǹ����ھ�12224");
							SQL_Manage.InsertAsset("dept_"+dName,name,num,price);																							
						    shenqing.deleApply(name);  //�������ʹ�Apply����ɾ���������
						}
						else {
							inApplyRecord(id,name,num,1,2);
							shenqing.deleApply(name);  //���������ʹ�Apply����ɾ���������
							System.out.println("����ʧ��");
							//return false;
						}
					}
					else if(!shenqing.getaore()){
						flag2=1;
						StoreKeeperDaoImpl sk=new StoreKeeperDaoImpl();
						sk.storeAdd(name,num,SQL_Manage.FindPrice("dept_"+dName,name));								
						inApplyRecord(id,name,num,2,1);
					}
					i++;
				}
				/*if(flag1==1)
					System.out.println("���óɹ�");
				if(flag2==1)
					System.out.println("�黹�ɹ�");*/
				return true;
			}

			public void inApplyRecord(String id,String name,int num,int flag1,int flag2) throws IOException {			//2 д�������¼����
				Date date=new Date();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String s1=format.format(date);
				String s2=null,s ;
				if(flag1==1) {						                                                                           						//�ж�����������
					if(flag2==1)
						s2="id��"+id+"�����óɹ��� "+name+"��������"+num+"��ʱ�䣺";   
					else {
						s2="id��"+id+"������ʧ�ܣ� "+name+"��������"+num+"��ʱ�䣺";  
					}
					s=s2.concat(s1);
				}
				else{
					if(flag2==1)
						s2="id��"+id+"���黹�ɹ��� "+name+"��������"+num+"��ʱ�䣺";  
					/*else {
						s2="id��"+id+"���黹ʧ�ܣ� "+name+"��������"+num+"��ʱ�䣺";  
						s=new String();	
					}*/
					s=s2.concat(s1);
				}
				// ./��ǰ·��,  ../��һ��·��  /��Ŀ¼
				FileWriter fw;
				fw = new FileWriter("./ApplyRecord.txt" , true);
				fw.write(s);	//������־
				fw.write("\r\n");
				fw.close();
			}

			public void readApplyRecord() throws IOException {																					//3 �������¼����
				Reader fr;
				fr = new FileReader("./ApplyRecord.txt");
				BufferedReader br=new BufferedReader(fr);
				String temp=null;
				String bout=null;
				temp=br.readLine();
				while(temp!=null) {
					bout=bout+temp+"\n";
					temp=br.readLine();
				}

				System.out.println(bout);

				br.close();
				fr.close();
			}

			public void readAsset() {    //4 �鿴�̶��ʲ�
				StoreKeeperDaoImpl sk=new StoreKeeperDaoImpl();
				sk.storeView();
			}

			public void readAppliAsset(UserDaoImpl u) {																//5 �������������ʲ�
					u.check();
				}

			public void buy(String s,int num,int price) throws IOException {												//6 ����Ʒ���̶��ʲ�
				StoreKeeperDaoImpl sk=new StoreKeeperDaoImpl();
				sk.storeAdd(s,num,price);
				Date date=new Date();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String s1=format.format(date);
				String s2="���룺"+ s+"��������"+num+"���۸�"+price+"��ʱ�䣺";
				String s3=s2.concat(s1);
				// ./��ǰ·��,  ../��һ��·��  /��Ŀ¼
				FileWriter fw = new FileWriter("./ApplyRecord.txt" , true);
				fw.write(s3);	//������־
				fw.write("\r\n");
				fw.close();
			}
}
