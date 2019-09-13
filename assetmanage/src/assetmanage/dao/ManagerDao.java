package assetmanage.dao;
import assetmanage.entity.*;

import java.io.IOException;
import java.sql.SQLException;

import assetmanage.dao.impl.*;
import assetmanage.server.impl.*;
public interface ManagerDao {
	public boolean chuliqingqiu(ApplyServerImpl shenqing,String id,String dName) throws IOException, SQLException;
	public void inApplyRecord(String id,String name,int num,int flag1,int flag2) throws IOException;
	public void readApplyRecord() throws IOException;
	public void readAsset();
	public void readAppliAsset(UserDaoImpl u);
	public void buy(String s,int num,int price) throws IOException ;
}
