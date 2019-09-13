package assetmanage.dao.impl;
import assetmanage.entity.*;
import assetmanage.dao.*;
import assetmanage.server.impl.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class StoreKeeperDaoImpl extends StoreKeeper implements StoreKeeperDao,SQL_Manage{
	public void storeView() {	//�鿴�ֿ�
		SQL_Manage.Query_Table("commonasset");	//�������ݿ�
	}
	
	public boolean storeDelete(String n , int i) throws IOException  {	//�ӿ���ɾ��
		int num  = SQL_Manage.FindNumber("commonasset" , n);
		if(i <= num) {		//�ж������Ƿ����
			if(i < num)
				SQL_Manage.decAsset("commonasset", n, i);	//�������ϼ�ȥȡ���ĸ���
			else//������ǡ������ʱ
				SQL_Manage.DelAsset("commonasset", n);//ɾ������
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			FileWriter writer = new FileWriter("log.txt" , true);
			writer.write(df.format(new Date()) + "	�Ӳֿ���ȡ����"+i+"��" + n + "\r\n");	//������־
			writer.close();
			return true;
		}
		else {		//����������
			System.out.println("����ʧ��");
			return false;
		}
	}
	
	public void storeAdd(String n , int number , int singleprice) throws IOException {		//���뵽����
		if(SQL_Manage.FindNumber("commonasset", n)>0)
			SQL_Manage.addAsset("commonasset",n,number );	//�������ϼ��������ĸ���
		else		//���ֿ�û�иòƲ�
			SQL_Manage.InsertAsset("commonasset", n,number,singleprice);		//��������
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		FileWriter writer = new FileWriter("log.txt" , true);
		writer.write(df.format(new Date()) + "	�ڲֿ��������"+number+"��" + n + "\r\n");	//������־
		writer.close();
	}
	
	public String fileView() throws IOException {		//��ȡ��־
        StringBuilder result = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader("log.txt"));//��ȡ�ļ�
            String s = null;
            while((s = br.readLine())!=null){
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        return result.toString();
    }
}
