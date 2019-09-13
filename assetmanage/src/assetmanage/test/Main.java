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
		String id;                     //�û���
		String pwd;                    //�û�����
		int choice;                    //�û�ѡ�������ֲ���
		boolean isExit1=false;          //�û��Ƿ��˳�
		boolean isExit2=false;          //�û��Ƿ��˳�
		boolean type = true;
		String num;
		while (type) {
			System.out.println("��ѡ�������¼ģʽ������1Ϊ�����ߵ�¼������2Ϊ����Ա��¼, ����3Ϊ��ܵ�¼");
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
				System.out.println("���������밴��ָ����������");
				System.out.println("��ѡ���¼ģʽ������1Ϊ�����ߵ�¼������2Ϊ����Ա��¼, ����3Ϊ��ܵ�¼");
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
			System.out.println("���ȵ�¼���������������ߵ�ID��");
			userId = input.nextLine().trim();
			System.out.println("�����������룺");
			userPassword = input.nextLine().trim();
			if(!SQL_Manage.isExist(1,userId,userPassword)) {
				System.out.println("��¼ʧ�ܣ���ȷ�������û������������������");
				reg = true;
			}else {
				reg = false;
				UserDaoImpl user=new UserDaoImpl(userId);//��Ҫ�����ߵĹ��캯��
				System.out.println("��¼�ɹ�����������������������1�������ɾ�������ʲ�������2, �������鿴�ʲ�������3");
				boolean type = true;
				while (type) {
					int num = input.nextInt();
					if (1 == num) {
						user.demand();
						type = false;
					} else if (2 == num) {
						System.out.println("����Ҫɾ������Ʒ����:");
						String cName=input.next();
						System.out.println("����Ҫɾ������Ʒ����:");
						int cNum=input.nextInt();
						user.delt(cName,cNum);
						type = false;
					}else if (3 == num) {
						user.check();
						type=false;
					}else {
						System.out.println("��������,����������");
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
			System.out.println("���ȵ�¼��������������ߵ�ID��");
			managerId = input.nextLine().trim();
			System.out.println("�����������룺");
			managerPassword = input.nextLine().trim();
			if(!SQL_Manage.isExist(2,managerId,managerPassword)) { //��һ����������ȷ��
				System.out.println("��¼ʧ�ܣ���ȷ�������û������������������");
				reg = true;
			}else {
				reg = false;
				ManagerDaoImpl scManager=new ManagerDaoImpl();//��Ҫ�����ߵĹ��캯��
				System.out.println("��¼�ɹ�");
				//System.out.println("������� ��������������1");
				//System.out.println("������� д�����¼������2");
				System.out.println("������� �������¼������1");
				System.out.println("������� �鿴�̶��ʲ�������2");
				System.out.println("������� �����ʲ�������3");
				boolean type = true;
				while (type) {
					int num = input.nextInt();
					/*if (1 == num) {
						scManager.chuliqingqiu(shenqing, id);//�����߲�����ʽ�ĵ����Լ��Ĵ�����������
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
						System.out.println("����Ҫ�������Ʒ����:");
						String cName=input.next();
						System.out.println("����Ҫ�������Ʒ����:");
						int cNum=input.nextInt();
						System.out.println("����Ҫ�������Ʒ����:");
						int cPrice=input.nextInt();
						scManager.buy(cName,cNum,cPrice);
						type=false;
					}else {
						System.out.println("��������,����������");
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
			System.out.println("���ȵ�¼�����������ܵ�ID��");
			skId = input.nextLine().trim();
			System.out.println("�����������룺");
			skPassword = input.nextLine().trim();
			if(!SQL_Manage.isExist(3,skId,skPassword)) {  //��һ����������ȷ��
				System.out.println("��¼ʧ�ܣ���ȷ�������û������������������");
				reg = true;
			}else {
				reg = false;
				StoreKeeperDaoImpl sk=new StoreKeeperDaoImpl();//��Ҫ��ܵĹ��캯��
				System.out.println("��¼�ɹ�");
				System.out.println("������� �鿴�ֿ�������1");
				System.out.println("������� �ӿ���ɾ��������2");
				System.out.println("������� ��������������3");
				System.out.println("������� �鿴��־������4");
				boolean type = true;
				while (type) {
					int num = input.nextInt();
					if (1 == num) {
						sk.storeView();
						type = false;
					} else if (2 == num) {
						System.out.println("����Ҫɾ������Ʒ����:");
						String cName=input.next();
						System.out.println("����Ҫɾ������Ʒ����:");
						int cNum=input.nextInt();
						sk.storeDelete(cName,cNum);  //��ԭ���������޸�
						type = false;
					}else if (3 == num) {
						System.out.println("����Ҫ��ӵ���Ʒ����:");
						String cName=input.next();
						System.out.println("����Ҫ��ӵ���Ʒ����:");
						int cNum=input.nextInt();
						System.out.println("����Ҫ��ӵ���Ʒ����:");
						int cPrice=input.nextInt();
						sk.storeAdd(cName,cNum,cPrice);
						type=false;
					}else if (4 == num) {
						String pString=sk.fileView();
						System.out.print(pString);
						type=false;
					}else {
						System.out.println("��������,����������");
						type = true;
					}
				}
			}
		}
	}

}
