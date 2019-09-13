package assetmanage.dao;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
public interface SQL_Manage {
////		public static String DRIVER; // ���ݿ�����
////		public static String URL ; // url
////		public static String DBNAME; // ���ݿ��û���
////		public static String DBPASS; // ���ݿ�����
		static Statement st = Connect();// �������Ӷ���
		
	public static Statement Connect() {
		String DRIVER;
		String URL;
		String DBNAME;
		String DBPASS;
		Properties params=new Properties();
		String configFile = "database.properties";//�����ļ�·��
		//���������ļ�����������
		InputStream is=SQL_Manage.class.getClassLoader().getResourceAsStream(configFile);
		try {
			//���������ж�ȡ�����б�
			params.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//����ָ���Ļ�ȡ��Ӧ��ֵ
		DRIVER=params.getProperty("driver");
		URL=params.getProperty("url");
		DBNAME=params.getProperty("user");
		DBPASS=params.getProperty("password");
		Connection conn = null;
		Statement St=null;
		try {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // ע������
			conn = DriverManager.getConnection(URL, DBNAME, DBPASS); // ������ݿ�����
			St=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return St;
	}
//	Statement st=Connect("my_assetManage");	
//	public static Statement Connect(String dbName) {
//		//������������
//		try {
//			//�������
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("�ɹ����ӵ�jar��");
//		}
//		catch (Exception e) {
//			System.out.println("����JAR��ʧ��");
//			e.printStackTrace();
//		}
//		try {
//			//����������ݿ�
//			String databaseName=dbName;
//			String url="jdbc:mysql://localhost:3306/"+databaseName
//					+"?useSSL=false&servertimezone=UTC&autoReconnect=true";
//			String user="root";
//			String password="MySQL";
//			Connection con=DriverManager.getConnection(url, user, password);
//			System.out.println("�������ݿ�ɹ�");
//			Statement st=con.createStatement();
//			return st;
//		}
//		catch (Exception e) {
//			System.out.println("�������ݿ�ʧ��");
//			e.printStackTrace();
//		}
//		return null;
//	}
//	public static boolean Query(String sql) {
//		//ִ��SQL��ѯ���,select 
//		String tempSql=sql;
//		try {
//			ResultSet rSet=st.executeQuery(tempSql);//����ִ�в�ѯ
//			//���ڻ�ȡ��ǰ�����Ϣ,�����������Ը����ȵ�(1~n)
//			ResultSetMetaData rsmd=rSet.getMetaData();
//			for(int i=1;i<=rsmd.getColumnCount();i++) {
//				System.out.print(rsmd.getColumnName(i)+"\t");
//			}
//			System.out.println();
//			while(rSet.next()) {
//				for(int j=1;j<=rsmd.getColumnCount();j++){
//					System.out.print(rSet.getString(j)+"\t");
//				}
//				System.out.println();
//			}
//		} catch (SQLException e) {
//			System.out.println("��ѯʧ��");
//			e.printStackTrace();
//		}
//		return true;
//	}
	public static boolean Query_Table(String TableName) {
		String tempSql="select * from "+TableName+";";
		try {
			ResultSet rSet=st.executeQuery(tempSql);//����ִ�в�ѯ
			//���ڻ�ȡ��ǰ�����Ϣ,�����������Ը����ȵ�(1~n)
			ResultSetMetaData rsmd=rSet.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				System.out.print(rsmd.getColumnName(i)+"\t");
			}
			System.out.println();
			while(rSet.next()) {
				for(int j=1;j<=rsmd.getColumnCount();j++){
					System.out.print(rSet.getString(j)+"\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("��ѯʧ��");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean QueryByName(String TableName,String name) {
		String tempSql="select * from "+TableName+"where name= '"+name+"';";
		try {
			ResultSet rSet=st.executeQuery(tempSql);//����ִ�в�ѯ
			//���ڻ�ȡ��ǰ�����Ϣ,�����������Ը����ȵ�(1~n)
			ResultSetMetaData rsmd=rSet.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				System.out.print(rsmd.getColumnName(i)+"\t");
			}
			System.out.println();
			while(rSet.next()) {
				for(int j=1;j<=rsmd.getColumnCount();j++){
					System.out.print(rSet.getString(j)+"\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("��ѯʧ��");
			e.printStackTrace();
		}
		return true;
	}
	public static String QueryNameByid(String TableName,String id) {
		String tempSql="select name from "+TableName+" where id= '"+id+"';";
		
		try {
			ResultSet rSet=st.executeQuery(tempSql);//����ִ�в�ѯ
			//���ڻ�ȡ��ǰ�����Ϣ,�����������Ը����ȵ�(1~n)
			ResultSetMetaData rsmd=rSet.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				System.out.print(rsmd.getColumnName(i)+"\t");
			}
			System.out.println();
			if(rSet.next()) {
				return rSet.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("��ѯʧ��");
			e.printStackTrace();
		}
		return null;
	}
	public static boolean Update(String sql) {
		//ִ�����ӡ�ɾ�����޸ĵ����,��create,drop,delete,insert,alter,Update��
		String tempSql=sql;
		try {
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("��"+rSet+"�б��޸�");
			}
			else {
				System.out.println("�޸�ʧ��!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("�޸�ʧ��");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean addAsset(String TableName,String Name,int number) {
		//ִ�������ʲ������,��create,drop,delete,insert,alter,Update��
		String tempSql="update "+TableName+" set number = number + " + number + " where name = '" + Name + "';";
		try {
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("��"+rSet+"�б��޸�");
			}
			else {
				System.out.println("�޸�ʧ��!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("�޸�ʧ��");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean decAsset(String TableName,String Name,int number) {
		//ִ�������ʲ������,��create,drop,delete,insert,alter,Update��
		String tempSql="update "+TableName+" set number = number - " + number + " where name = '" + Name + "';";
		try {
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("��"+rSet+"�б��޸�");
			}
			else {
				System.out.println("�޸�ʧ��!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("�޸�ʧ��");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean InsertAsset(String TableName,String Name,int num,int Sp) {
		String tempSql="insert into "+TableName+" value("+"'"+Name+"' ,"+num+","+Sp+") ;";
		try {
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("��"+rSet+"�б��޸�");
			}
			else {
				System.out.println("�޸�ʧ��!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("�޸�ʧ��");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean UserInsertAsset(String TableName,String Name,int num,String name) {
		String tempSql="insert into "+TableName+" value("+"'"+Name+"' ,"+num+",'"+name+"') ;";
//		System.out.println(tempSql);
		try {
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("��"+rSet+"�б��޸�");
			}
			else {
				System.out.println("�޸�ʧ��!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("�޸�ʧ��");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean DelAsset(String TableName,String Name) {
		String tempSql="delete from "+TableName+"  where name= '"+Name+"';";
//		System.out.println(tempSql+"19651681556");
		try {
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("��"+rSet+"�б��޸�");
			}
			else {
				System.out.println("�޸�ʧ��!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("�޸�ʧ��");
			e.printStackTrace();
		}
		return true;
	}
	public static int  FindNumber(String TableName,String Name) {
		//ƾ��name ���Ҹ���
		String tempName="'"+Name+"'";
		String sql="select *from "+TableName+" where name="+tempName;
		try {
			ResultSet rSet=st.executeQuery(sql);
			if(rSet.next())
			{
				int i=rSet.getInt(2);
				return i;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Ѱ�Ҵ���");
			e.printStackTrace();
			return -1;
		}
		return -1;

	}
	public static int  FindPrice(String TableName,String Name) {
		//ƾ��name ���Ҽ�Ǯ
		String tempName="'"+Name+"'";
		String sql="select *from "+TableName+" where name="+tempName;
		try {
			ResultSet rSet=st.executeQuery(sql);
			if(rSet.next())
			{
				int i=rSet.getInt(3);
				return i;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Ѱ�Ҵ���");
			e.printStackTrace();
		}
		return -1;

	}
	public static boolean InsertUser(boolean identity,String id,String pwd) {
		String tempSql=null;
		try {
			if(identity) {
				tempSql="insert into adminuser"+" value("+"'"+id+"','"+pwd+"' );";
			}
			else {
				tempSql="insert into empuser"+" value("+"'"+id+"','"+pwd+"' );";
			}
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("��"+rSet+"�б��޸�");
			}
			else {
				System.out.println("�޸�ʧ��!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("�޸�ʧ��");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean ChangePwd(boolean identity,String id,String pwd) {
		String tempSql=null;
		try {
			if(identity) {
				tempSql="update adminuser set pwd= '"+pwd+"' where id= '"+id+"' ;";
			}
			else {
				tempSql="update empuser set pwd= '"+pwd+"' where id= '"+id+"' ;";
			}
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("��"+rSet+"�б��޸�");
			}
			else {
				System.out.println("�޸�ʧ��!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("�޸�ʧ��");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean isExist(int role,String id,String pwd) throws SQLException
	{
		//��֤��¼�û���ݣ���֤�û��������Ƿ���ȷ;
		//identity : 0/false:��ͨ�û���1/true: ����Ա
		ResultSet rs=null;
		String sql=null;
		try {
			if(role==1) {
				sql="select * from empuser where id= '"+id+"' ;";
			}
			else if(role==2) {
				sql="select * from adminuser where id= '"+id+"' ;";
			}
			else if(role==3) {
				sql="select * from storekeeper where id= '"+id+"' ;";
			}
			else return false;
			rs=st.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(pwd.equals(rs.getString(2))){return true;}
		else 
			return false;
	}
}
