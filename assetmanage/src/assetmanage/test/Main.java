package assetmanage.test;
import java.util.*;
import java.io.IOException;
import java.sql.*;
import assetmanage.dao.*;
import assetmanage.dao.impl.*;
import assetmanage.entity.*;
import assetmanage.server.impl.*;
public class Main implements SQL_Manage{

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Main.startserver();
	}
	public static void startserver() throws SQLException, IOException {
		Scanner input=new Scanner(System.in);
		String id;                     //用户名
		String pwd;                    //用户密码
		int choice;                    //用户选择了哪种操作
		boolean isExit1=false;          //用户是否退出
		boolean isExit2=false;          //用户是否退出
		boolean type = true;
		String num;
		while (type) {
			System.out.println("请选择输入登录模式，输入1为领用者登录，输入2为管理员登录, 输入3为库管登录");
			num = input.next();
			if ("1".equals(num)) {
				Main.userLogin();
				type = true;
			} else if ("2".equals(num)) {
				Main.mangLogin();
				type = true;
			}else if("3".equals(num)) {
				Main.skLogin();
			}
			else {
				System.out.println("输入有误，请按照指定规则输入");
				System.out.println("请选择登录模式，输入1为领用者登录，输入2为管理员登录, 输入3为库管登录");
				type = true;
			}
		}
	}
	
	public static void userLogin() throws SQLException, IOException {
		Scanner input = new Scanner(System.in);
		String userId;
		String userPassword;
		boolean reg = true;
		while(reg) {
			System.out.println("请先登录，请您输入领用者的ID：");
			userId = input.nextLine().trim();
			System.out.println("请您输入密码：");
			userPassword = input.nextLine().trim();
			if(!SQL_Manage.isExist(1,userId,userPassword)) {
				System.out.println("登录失败，请确认您的用户名和密码后重新输入");
				reg = true;
			}else {
				reg = false;
				UserDaoImpl user=new UserDaoImpl(userId);//需要领用者的构造函数
				System.out.println("登录成功，如果您想提出申请请输入1，如果想删除已有资产请输入2, 如果您想查看资产请输入3");
				boolean type = true;
				while (type) {
					int num = input.nextInt();
					if (1 == num) {
						user.demand();
						type = false;
					} else if (2 == num) {
						System.out.println("输入要删除的物品名称:");
						String cName=input.next();
						System.out.println("输入要删除的物品数量:");
						int cNum=input.nextInt();
						user.delt(cName,cNum);
						type = false;
					}else if (3 == num) {
						user.check();
						type=false;
					}else {
						System.out.println("输入有误,请重新输入");
						type = true;
					}
				}
			}
		}
	}
	
    public static void mangLogin() throws SQLException, IOException {
    	Scanner input = new Scanner(System.in);
		String managerId;
		String managerPassword;
		boolean reg = true;
		while(reg) {
			System.out.println("请先登录，请您输入管理者的ID：");
			managerId = input.nextLine().trim();
			System.out.println("请您输入密码：");
			managerPassword = input.nextLine().trim();
			if(!SQL_Manage.isExist(2,managerId,managerPassword)) { //第一个参数还需确认
				System.out.println("登录失败，请确认您的用户名和密码后重新输入");
				reg = true;
			}else {
				reg = false;
				ManagerDaoImpl scManager=new ManagerDaoImpl();//需要管理者的构造函数
				System.out.println("登录成功");
				//System.out.println("如果您想 处理申请请输入1");
				//System.out.println("如果您想 写请求记录请输入2");
				System.out.println("如果您想 读请求记录请输入1");
				System.out.println("如果您想 查看固定资产请输入2");
				System.out.println("如果您想 购买资产请输入3");
				boolean type = true;
				while (type) {
					int num = input.nextInt();
					/*if (1 == num) {
						scManager.chuliqingqiu(shenqing, id);//管理者不能显式的调用自己的处理请求函数？
						type = false;
					} else */
					/*if (2 == num) {
						scManager.inApplyRecord(id, name, num, flag1, flag2);
						type = false;
					}*/
					if (1 == num) {
						scManager.readApplyRecord();
						type=false;
					}else if (2 == num) {
						scManager.readAsset();;
						type=false;
					}else if (3 == num) {
						System.out.println("输入要购买的物品名称:");
						String cName=input.next();
						System.out.println("输入要购买的物品数量:");
						int cNum=input.nextInt();
						System.out.println("输入要购买的物品单价:");
						int cPrice=input.nextInt();
						scManager.buy(cName,cNum,cPrice);
						type=false;
					}else {
						System.out.println("输入有误,请重新输入");
						type = true;
					}
				}
			}
		}
	}
    
    public static  void skLogin() throws IOException, SQLException {
    	Scanner input = new Scanner(System.in);
		String skId;
		String skPassword;
		boolean reg = true;
		while(reg) {
			System.out.println("请先登录，请您输入库管的ID：");
			skId = input.nextLine().trim();
			System.out.println("请您输入密码：");
			skPassword = input.nextLine().trim();
			if(!SQL_Manage.isExist(3,skId,skPassword)) {  //第一个参数还需确认
				System.out.println("登录失败，请确认您的用户名和密码后重新输入");
				reg = true;
			}else {
				reg = false;
				StoreKeeperDaoImpl sk=new StoreKeeperDaoImpl();//需要库管的构造函数
				System.out.println("登录成功");
				System.out.println("如果您想 查看仓库请输入1");
				System.out.println("如果您想 从库中删除请输入2");
				System.out.println("如果您想 向库中添加请输入3");
				System.out.println("如果您想 查看日志请输入4");
				boolean type = true;
				while (type) {
					int num = input.nextInt();
					if (1 == num) {
						sk.storeView();
						type = false;
					} else if (2 == num) {
						System.out.println("输入要删除的物品名称:");
						String cName=input.next();
						System.out.println("输入要删除的物品数量:");
						int cNum=input.nextInt();
						sk.storeDelete(cName,cNum);  //对原函数进行修改
						type = false;
					}else if (3 == num) {
						System.out.println("输入要添加的物品名称:");
						String cName=input.next();
						System.out.println("输入要添加的物品数量:");
						int cNum=input.nextInt();
						System.out.println("输入要添加的物品单价:");
						int cPrice=input.nextInt();
						sk.storeAdd(cName,cNum,cPrice);
						type=false;
					}else if (4 == num) {
						String pString=sk.fileView();
						System.out.print(pString);
						type=false;
					}else {
						System.out.println("输入有误,请重新输入");
						type = true;
					}
				}
			}
		}
	}

}
