package assetmanage.server;
import java.util.*;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import java.sql.*;
public interface ApplyServer {
	//public Map<String,Intergar> applyMap=new HashMap<String,Intergar>();    //存储申请的内容,第一个参数物品名称，第二个参数数量
	//public Map<String,Intergar> allowMap=new HashMap<String,Intergar>();    //存储可以批准的申请内容，初始为空;
	//public boolean AorE;         //标注这次申请是借还是还; true是借，false是还; 领用者来手动设置(通过调用下面的setaore函数);

	public boolean getaore();

	public void setaore(boolean aore);

	public void addApply(String cName,int cNum,String id);  //领用者的提出申请函数可以直接调用(把要申请的加到applyMap里)
	
	public ArrayList<Pair<String, Integer>> readApply() throws SQLException;
	
	public void deleApply(String xcname);

	//public void addAllow(String cName,int cNum);  //管理员或库管的处理申请函数可以直接调用(把能批准的那些物品加到allowMap里)

	//public int getcNum(int i);    //返回这一次申请里的第i个物品的数量;库管和管理员进行判断(是否批准)时,可以调用
}
