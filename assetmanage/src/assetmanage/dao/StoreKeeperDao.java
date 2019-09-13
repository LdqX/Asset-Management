package assetmanage.dao;
import assetmanage.entity.*;
import java.util.*;
import java.io.*;
public interface StoreKeeperDao {
	public void storeView();	//查看仓库	//遍历数据库
	
	public boolean storeDelete(String n , int i) throws IOException; 	//从库中删除
	
	public void storeAdd(String n , int number , int singleprice) throws IOException;		//加入到库中
	
	public String fileView() throws IOException;		//读取日志

}
