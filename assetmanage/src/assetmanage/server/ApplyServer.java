package assetmanage.server;
import java.util.*;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import java.sql.*;
public interface ApplyServer {
	//public Map<String,Intergar> applyMap=new HashMap<String,Intergar>();    //�洢���������,��һ��������Ʒ���ƣ��ڶ�����������
	//public Map<String,Intergar> allowMap=new HashMap<String,Intergar>();    //�洢������׼���������ݣ���ʼΪ��;
	//public boolean AorE;         //��ע��������ǽ軹�ǻ�; true�ǽ裬false�ǻ�; ���������ֶ�����(ͨ�����������setaore����);

	public boolean getaore();

	public void setaore(boolean aore);

	public void addApply(String cName,int cNum,String id);  //�����ߵ�������뺯������ֱ�ӵ���(��Ҫ����ļӵ�applyMap��)
	
	public ArrayList<Pair<String, Integer>> readApply() throws SQLException;
	
	public void deleApply(String xcname);

	//public void addAllow(String cName,int cNum);  //����Ա���ܵĴ������뺯������ֱ�ӵ���(������׼����Щ��Ʒ�ӵ�allowMap��)

	//public int getcNum(int i);    //������һ��������ĵ�i����Ʒ������;��ܺ͹���Ա�����ж�(�Ƿ���׼)ʱ,���Ե���
}
