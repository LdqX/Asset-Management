package assetmanage.entity;
import java.util.*;
import assetmanage.dao.*;
public class StoreKeeper implements SQL_Manage{
	public String keeperName;	//�������
	public String keeper_id;	//���ID
	public String key;			//����
	public StoreKeeper() {};
	public StoreKeeper(String ID,String NM){
		Scanner input=new Scanner(System.in);
		keeper_id=ID;
		System.out.print("�������룺");
		key=input.next();
		keeperName=NM;
		//String creatTable="CREAT TABLE dept_"+name;
	}
	public String getKeeperName() {
		return keeperName;
	}
	public void setKeeperName(String keeperName) {
		this.keeperName = keeperName;
	}
	public String getKeeper_id() {
		return keeper_id;
	}
	public void setKeeper_id(String keeper_id) {
		this.keeper_id = keeper_id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
