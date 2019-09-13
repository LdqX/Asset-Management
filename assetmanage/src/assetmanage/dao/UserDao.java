package assetmanage.dao;
import java.io.IOException;
import java.sql.SQLException;

import assetmanage.entity.*;
public interface UserDao {
	public void demand() throws IOException, SQLException;
	public void delt(String cname,int cnum);
	public void check();
}
