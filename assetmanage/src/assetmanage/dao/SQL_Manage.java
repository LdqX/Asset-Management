package assetmanage.dao;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
public interface SQL_Manage {
////		public static String DRIVER; // 数据库驱动
////		public static String URL ; // url
////		public static String DBNAME; // 数据库用户名
////		public static String DBPASS; // 数据库密码
		static Statement st = Connect();// 数据连接对象
		
	public static Statement Connect() {
		String DRIVER;
		String URL;
		String DBNAME;
		String DBPASS;
		Properties params=new Properties();
		String configFile = "database.properties";//配置文件路径
		//加载配置文件到输入流中
		InputStream is=SQL_Manage.class.getClassLoader().getResourceAsStream(configFile);
		try {
			//从输入流中读取属性列表
			params.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//根据指定的获取对应的值
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
			} // 注册驱动
			conn = DriverManager.getConnection(URL, DBNAME, DBPASS); // 获得数据库连接
			St=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return St;
	}
//	Statement st=Connect("my_assetManage");	
//	public static Statement Connect(String dbName) {
//		//单纯创建连接
//		try {
//			//监测驱动
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("成功连接到jar包");
//		}
//		catch (Exception e) {
//			System.out.println("连接JAR包失败");
//			e.printStackTrace();
//		}
//		try {
//			//监测连接数据库
//			String databaseName=dbName;
//			String url="jdbc:mysql://localhost:3306/"+databaseName
//					+"?useSSL=false&servertimezone=UTC&autoReconnect=true";
//			String user="root";
//			String password="MySQL";
//			Connection con=DriverManager.getConnection(url, user, password);
//			System.out.println("连接数据库成功");
//			Statement st=con.createStatement();
//			return st;
//		}
//		catch (Exception e) {
//			System.out.println("连接数据库失败");
//			e.printStackTrace();
//		}
//		return null;
//	}
//	public static boolean Query(String sql) {
//		//执行SQL查询语句,select 
//		String tempSql=sql;
//		try {
//			ResultSet rSet=st.executeQuery(tempSql);//用于执行查询
//			//用于获取当前表格信息,如列名，属性个数等等(1~n)
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
//			System.out.println("查询失败");
//			e.printStackTrace();
//		}
//		return true;
//	}
	public static boolean Query_Table(String TableName) {
		String tempSql="select * from "+TableName+";";
		try {
			ResultSet rSet=st.executeQuery(tempSql);//用于执行查询
			//用于获取当前表格信息,如列名，属性个数等等(1~n)
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
			System.out.println("查询失败");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean QueryByName(String TableName,String name) {
		String tempSql="select * from "+TableName+"where name= '"+name+"';";
		try {
			ResultSet rSet=st.executeQuery(tempSql);//用于执行查询
			//用于获取当前表格信息,如列名，属性个数等等(1~n)
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
			System.out.println("查询失败");
			e.printStackTrace();
		}
		return true;
	}
	public static String QueryNameByid(String TableName,String id) {
		String tempSql="select name from "+TableName+" where id= '"+id+"';";
		
		try {
			ResultSet rSet=st.executeQuery(tempSql);//用于执行查询
			//用于获取当前表格信息,如列名，属性个数等等(1~n)
			ResultSetMetaData rsmd=rSet.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				System.out.print(rsmd.getColumnName(i)+"\t");
			}
			System.out.println();
			if(rSet.next()) {
				return rSet.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("查询失败");
			e.printStackTrace();
		}
		return null;
	}
	public static boolean Update(String sql) {
		//执行增加、删除、修改等语句,如create,drop,delete,insert,alter,Update等
		String tempSql=sql;
		try {
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("有"+rSet+"行被修改");
			}
			else {
				System.out.println("修改失败!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("修改失败");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean addAsset(String TableName,String Name,int number) {
		//执行增加资产的语句,如create,drop,delete,insert,alter,Update等
		String tempSql="update "+TableName+" set number = number + " + number + " where name = '" + Name + "';";
		try {
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("有"+rSet+"行被修改");
			}
			else {
				System.out.println("修改失败!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("修改失败");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean decAsset(String TableName,String Name,int number) {
		//执行增加资产的语句,如create,drop,delete,insert,alter,Update等
		String tempSql="update "+TableName+" set number = number - " + number + " where name = '" + Name + "';";
		try {
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("有"+rSet+"行被修改");
			}
			else {
				System.out.println("修改失败!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("修改失败");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean InsertAsset(String TableName,String Name,int num,int Sp) {
		String tempSql="insert into "+TableName+" value("+"'"+Name+"' ,"+num+","+Sp+") ;";
		try {
			int rSet=st.executeUpdate(tempSql);
			if(rSet>=0) {
				System.out.println("有"+rSet+"行被修改");
			}
			else {
				System.out.println("修改失败!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("修改失败");
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
				System.out.println("有"+rSet+"行被修改");
			}
			else {
				System.out.println("修改失败!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("修改失败");
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
				System.out.println("有"+rSet+"行被修改");
			}
			else {
				System.out.println("修改失败!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("修改失败");
			e.printStackTrace();
		}
		return true;
	}
	public static int  FindNumber(String TableName,String Name) {
		//凭借name 查找个数
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
			System.out.println("寻找错误");
			e.printStackTrace();
			return -1;
		}
		return -1;

	}
	public static int  FindPrice(String TableName,String Name) {
		//凭借name 查找价钱
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
			System.out.println("寻找错误");
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
				System.out.println("有"+rSet+"行被修改");
			}
			else {
				System.out.println("修改失败!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("修改失败");
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
				System.out.println("有"+rSet+"行被修改");
			}
			else {
				System.out.println("修改失败!");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("修改失败");
			e.printStackTrace();
		}
		return true;
	}
	public static boolean isExist(int role,String id,String pwd) throws SQLException
	{
		//验证登录用户身份，验证用户名密码是否正确;
		//identity : 0/false:普通用户，1/true: 管理员
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
