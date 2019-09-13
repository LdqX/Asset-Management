package assetmanage.dao.impl;
import assetmanage.entity.*;
import assetmanage.dao.*;
import assetmanage.server.impl.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class StoreKeeperDaoImpl extends StoreKeeper implements StoreKeeperDao,SQL_Manage{
	public void storeView() {	//查看仓库
		SQL_Manage.Query_Table("commonasset");	//遍历数据库
	}
	
	public boolean storeDelete(String n , int i) throws IOException  {	//从库中删除
		int num  = SQL_Manage.FindNumber("commonasset" , n);
		if(i <= num) {		//判断条件是否符合
			if(i < num)
				SQL_Manage.decAsset("commonasset", n, i);	//在总数上减去取出的个数
			else//当总数恰好满足时
				SQL_Manage.DelAsset("commonasset", n);//删除该行
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			FileWriter writer = new FileWriter("log.txt" , true);
			writer.write(df.format(new Date()) + "	从仓库中取出了"+i+"个" + n + "\r\n");	//更新日志
			writer.close();
			return true;
		}
		else {		//条件不满足
			System.out.println("请求失败");
			return false;
		}
	}
	
	public void storeAdd(String n , int number , int singleprice) throws IOException {		//加入到库中
		if(SQL_Manage.FindNumber("commonasset", n)>0)
			SQL_Manage.addAsset("commonasset",n,number );	//在总数上加上新增的个数
		else		//若仓库没有该财产
			SQL_Manage.InsertAsset("commonasset", n,number,singleprice);		//新增该列
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		FileWriter writer = new FileWriter("log.txt" , true);
		writer.write(df.format(new Date()) + "	在仓库中添加了"+number+"个" + n + "\r\n");	//更新日志
		writer.close();
	}
	
	public String fileView() throws IOException {		//读取日志
        StringBuilder result = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader("log.txt"));//读取文件
            String s = null;
            while((s = br.readLine())!=null){
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        return result.toString();
    }
}
