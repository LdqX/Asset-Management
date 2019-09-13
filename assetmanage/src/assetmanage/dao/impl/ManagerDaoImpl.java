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
	//由领用者决定***
			public boolean chuliqingqiu(ApplyServerImpl shenqing,String id,String dName) throws IOException, SQLException {          		//1 处理请求方法
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
						//判断是哪种申请
						flag1=1;
						int price=SQL_Manage.FindPrice("commonasset",name);
						StoreKeeperDaoImpl sk=new StoreKeeperDaoImpl();
						if(sk.storeDelete(name,num)) {
							inApplyRecord(id,name,num,1,1);
//							System.out.println("那功夫内疚12224");
							SQL_Manage.InsertAsset("dept_"+dName,name,num,price);																							
						    shenqing.deleApply(name);  //如果允许就从Apply表里删除这个请求
						}
						else {
							inApplyRecord(id,name,num,1,2);
							shenqing.deleApply(name);  //如果不允许就从Apply表里删除这个请求
							System.out.println("领用失败");
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
					System.out.println("领用成功");
				if(flag2==1)
					System.out.println("归还成功");*/
				return true;
			}

			public void inApplyRecord(String id,String name,int num,int flag1,int flag2) throws IOException {			//2 写入申请记录方法
				Date date=new Date();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String s1=format.format(date);
				String s2=null,s ;
				if(flag1==1) {						                                                                           						//判断是哪种申请
					if(flag2==1)
						s2="id："+id+"，领用成功： "+name+"，数量："+num+"，时间：";   
					else {
						s2="id："+id+"，领用失败： "+name+"，数量："+num+"，时间：";  
					}
					s=s2.concat(s1);
				}
				else{
					if(flag2==1)
						s2="id："+id+"，归还成功： "+name+"，数量："+num+"，时间：";  
					/*else {
						s2="id："+id+"，归还失败： "+name+"，数量："+num+"，时间：";  
						s=new String();	
					}*/
					s=s2.concat(s1);
				}
				// ./当前路径,  ../上一级路径  /根目录
				FileWriter fw;
				fw = new FileWriter("./ApplyRecord.txt" , true);
				fw.write(s);	//更新日志
				fw.write("\r\n");
				fw.close();
			}

			public void readApplyRecord() throws IOException {																					//3 读申请记录方法
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

			public void readAsset() {    //4 查看固定资产
				StoreKeeperDaoImpl sk=new StoreKeeperDaoImpl();
				sk.storeView();
			}

			public void readAppliAsset(UserDaoImpl u) {																//5 读领用者所有资产
					u.check();
				}

			public void buy(String s,int num,int price) throws IOException {												//6 买物品到固定资产
				StoreKeeperDaoImpl sk=new StoreKeeperDaoImpl();
				sk.storeAdd(s,num,price);
				Date date=new Date();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String s1=format.format(date);
				String s2="买入："+ s+"，数量："+num+"，价格："+price+"，时间：";
				String s3=s2.concat(s1);
				// ./当前路径,  ../上一级路径  /根目录
				FileWriter fw = new FileWriter("./ApplyRecord.txt" , true);
				fw.write(s3);	//更新日志
				fw.write("\r\n");
				fw.close();
			}
}
