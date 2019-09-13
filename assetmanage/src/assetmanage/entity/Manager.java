package assetmanage.entity;
import java.util.*;
import assetmanage.dao.*;
public class Manager implements SQL_Manage{
	String id;
	String password;
	String name;
	public Manager() {};
	public Manager(String ID,String NM) {
		Scanner input=new Scanner(System.in);
		id=ID;
		System.out.print("…Ë÷√√‹¬Î£∫");
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
