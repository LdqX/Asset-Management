
package assetmanage.server.impl;
import assetmanage.server.*;
import assetmanage.dao.*;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
public class ApplyServerImpl implements ApplyServer,SQL_Manage{
	public boolean AorE;
	public boolean getaore() {
		return AorE;
	}

	public void setaore(boolean xcaore) {
		AorE=xcaore;
	}
	public void addApply(String cName,int cNum,String name) {
		SQL_Manage.UserInsertAsset("Apply", cName, cNum,name);
	}
	
	public ArrayList<Pair<String, Integer>> readApply() throws SQLException {
		String tempSql="select * from Apply;";
		ResultSet rSet=st.executeQuery(tempSql);//用于执行查询
		//用于获取当前表格信息,如列名，属性个数等等(1~n)
		ResultSetMetaData rsmd=rSet.getMetaData();
		for(int i=1;i<=rsmd.getColumnCount();i++) {
			System.out.print(rsmd.getColumnName(i)+"\t");
		}
		System.out.println();
		ArrayList<Pair<String, Integer>> lrSet=new ArrayList<Pair<String,Integer>>() ;
		if(rSet!=null) {
			while(rSet.next()) {
				for(int j=1;j<=rsmd.getColumnCount();j++){
					System.out.print(rSet.getString(j)+"\t");
					}
					lrSet.add(new Pair<String,Integer>(rSet.getString(1),rSet.getInt(2)));
				System.out.println();
			}
		}
//		System.out.println("劳苦功高情感IQ概念股");
		return lrSet;
	}
	
	public void deleApply(String xcname) {
		SQL_Manage.DelAsset("Apply",xcname);
	}
}
