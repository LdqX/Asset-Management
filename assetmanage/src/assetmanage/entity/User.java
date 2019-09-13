package assetmanage.entity;
import java.util.*;
import java.lang.*;
import assetmanage.dao.*;

public class User implements SQL_Manage{
	public String id;
	public String password;
	public String name;//部门名称
	public User() {};
	public User(String ID,String NM){
		Scanner input=new Scanner(System.in);
		id=ID;
		System.out.print("设置密码：");
		password=input.next();
		name=NM;
		//String creatTable="CREAT TABLE dept_"+name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
