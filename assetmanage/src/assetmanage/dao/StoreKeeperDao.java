package assetmanage.dao;
import assetmanage.entity.*;
import java.util.*;
import java.io.*;
public interface StoreKeeperDao {
	public void storeView();	//�鿴�ֿ�	//�������ݿ�
	
	public boolean storeDelete(String n , int i) throws IOException; 	//�ӿ���ɾ��
	
	public void storeAdd(String n , int number , int singleprice) throws IOException;		//���뵽����
	
	public String fileView() throws IOException;		//��ȡ��־

}
