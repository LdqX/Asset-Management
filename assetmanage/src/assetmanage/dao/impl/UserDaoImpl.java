package assetmanage.dao.impl;
import assetmanage.dao.*;
import java.util.*;
import assetmanage.entity.*;
import assetmanage.server.impl.*;

import java.io.IOException;
import java.lang.*;
import java.sql.SQLException;
public class UserDaoImpl extends User implements UserDao,SQL_Manage{
	public UserDaoImpl() {}
	public UserDaoImpl(String id) {
//		String name=SQL_Manage.QueryNameByid("empuser", this.id);
//		this.name=name;
		this.id=id;
	}
	public void demand() throws IOException, SQLException {
		Scanner input=new Scanner(System.in);
		System.out.print("ѡ���������1.������� 2.�������");
		int choose=input.nextInt();
		String cName;//��¼����
		int cNum;//��¼����
		ApplyServerImpl myApply =new ApplyServerImpl();
		ManagerDaoImpl m=new ManagerDaoImpl();
		System.out.println("���ƣ�");
		cName=input.next();
		System.out.println("������");
		cNum=input.nextInt();
		String name=SQL_Manage.QueryNameByid("empuser", this.id);
		switch (choose) {
		case 1:
			myApply.setaore(true);
			myApply.addApply(cName,cNum,name);
//			System.out.println("�Ǳ��������");
			//m.inApplyRecord(id, cName, cNum, 1,1);
			break;
		case 2:
			myApply.setaore(false);
			myApply.addApply(cName,cNum,this.id);
			//m.inApplyRecord(id, cName, cNum, 0,1);
			break;
		
		default:
			System.out.println("����");
		}

//		System.out.println("�Ǳ��������132135515");
		m.chuliqingqiu(myApply,this.id,name);
	}
	public void delt(String cName,int cNum) {
		String name=SQL_Manage.QueryNameByid("empuser", this.id);
		SQL_Manage.decAsset("dept_"+name,cName,cNum);
	}
	public void check() {
		String name=SQL_Manage.QueryNameByid("empuser", this.id);
		SQL_Manage.Query_Table("dept_"+name);
	}
}
