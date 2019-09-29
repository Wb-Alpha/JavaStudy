package MyClient;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class UI extends Application{
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	
	//Node
	Label ID=new Label("用户名：");
	Label code=new Label("密  码：");
	Label title=new Label("Welcome!");
	Label ID1=new Label("用 户 名：");
	Label code1=new Label("密   码：");
	Label code_confirm=new Label("确认密码：");
	Label title1=new Label("注册新用户");
	
	TextField UserID=new TextField();
	TextField UserID1=new TextField();
	
	PasswordField password=new PasswordField();
	PasswordField password1=new PasswordField();
	PasswordField password_confirm=new PasswordField();
	
	Button landing=new Button("登录");
	Button confirm=new Button("创建账号");
	
	
	
	public void start(Stage primaryStage) throws IOException {
		GridPane Land=new GridPane();
		GridPane Register=new GridPane();
		GridPane button=new GridPane();
		GridPane titleA=new GridPane();
		GridPane titleB=new GridPane();
		
		Tab tab1=new Tab("登录",Land);
		Tab tab2=new Tab("注册",Register);
		TabPane tb1=new TabPane(tab1,tab2);
		Scene mainscene=new Scene(tb1,350,250);
		
		
		Land.setPadding(new Insets(20,20,20,20));
		Register.setPadding(new Insets(20,20,20,20));
		Land.setHgap(-20);
		Land.setVgap(5);			//设置边框边距以及行高
		
		
		title.setStyle("-fx-font-size:20");
		title1.setStyle("-fx-font-size:20");//设置字体样式
		UserID.setPromptText("输入用户名");
		password.setPromptText("输入密码");//设置文本框预设值
		
		
		button.add(landing, 0, 0);
		titleA.add(title, 0, 0);
		Land.add(titleA, 0, 0);
		Land.add(ID, 0, 1);
		Land.add(UserID, 1, 1);
		Land.add(code, 0,2);
		Land.add(password, 1, 2);
		Land.add(button, 0, 3);	//以GridPane面板构建登录界面
		
		
		Register.add(titleB, 0, 0);
		titleB.add(title1,0,0);
		Register.add(ID1, 0, 1);
		Register.add(UserID1, 1, 1);
		Register.add(code1, 0, 2);
		Register.add(password1, 1, 2);
		Register.add(code_confirm, 0, 3);
		Register.add(password_confirm, 1, 3);
		Register.add(confirm, 0, 4);//以GridPane面板构建注册界面
		
		
		tab1.setClosable(false);
		tab2.setClosable(false);//设置两个标签卡不可关闭
		
		
		primaryStage.setResizable(false);//设置不可最大化
		primaryStage.setScene(mainscene);//将场景scene放进主舞台
		primaryStage.setTitle("ChatRoom");//设置标题
		primaryStage.show();
		
		
		
		
		
		//创建储存账号和密码的文件夹
		File file=new File("Data");
		if(!file.exists()){//如果文件夹不存在
			file.mkdir();//创建文件夹
		}
		File f_admin=new File("Data","admin.txt");
		if(f_admin.exists()==false) {
			f_admin.createNewFile();
		}
		
		/*账号密码储存方式：
		 * 每个新建一个账号则在Data文件夹中新建一个以账号为名的.txt文件
		 * 该文本文件内储存着该账号密码
		 */
		
		
		
		//鼠标操作事件
		
		//注册按钮鼠标事件（按下按钮后注册）
		confirm.setOnAction(e->
			{
				String ID=UserID1.getText();
				String password=password1.getText();
				String password_con=password_confirm.getText();
				
				
				if(password.equals(password_con)==false) //如果两次输入的密码不一致
					JOptionPane.showMessageDialog( null, "两次密码输入不一致", "警告",JOptionPane.WARNING_MESSAGE, null);
				else {
					
					
					
					if(password.length()<6) //如果密码长度小于6
						JOptionPane.showMessageDialog( null, "密码必须包含六位以上", "警告",JOptionPane.WARNING_MESSAGE, null);
					else {
						
						
						
						for(int i=0;i<file.list().length;i++) {//遍历用于储存用户信息的文件夹
							
			
							
							if(file.list()[i].equals(ID)) //如果存在相同的用户
								JOptionPane.showMessageDialog( null, "该用户名已被注册！", "警告",JOptionPane.WARNING_MESSAGE, null);
							else {

								try {
									File user=new File("Data",ID+".txt");
									user.createNewFile();//以用户名创建新文件夹
									FileWriter fw=new FileWriter("Data/"+ID+".txt");
									fw.write(password);//将密码写入文本文件中
									fw.close();
									JOptionPane.showMessageDialog(null, "账号创建成功","提示",JOptionPane.INFORMATION_MESSAGE); 
									UserID1.clear();
									password1.clear();
									password_confirm.clear();
									} 
								
								catch (IOException error) {
									System.out.println(error);
								}
							}
						}
					}
				}
			}
		);
	
	
		
		
		
		
		
		//登录按钮鼠标事件
	landing.setOnAction(e->{
			String ID=UserID.getText();
			String pass=password.getText();
			System.out.println("run");

			
			for(int i=0;i<file.list().length;i++) {
				
				if(file.list()[i].equals(ID+".txt")) {
					char[] c=new char[500];
					
					try(FileReader fr=new FileReader("Data/"+ID+".txt")){
						int num=fr.read(c);
						String str=new String(c,0,num);
		
						
						if(str.equals(pass)) {
							JOptionPane.showMessageDialog(null, "登陆成功","提示",JOptionPane.INFORMATION_MESSAGE); 
							UserID.clear();
							password.clear();
							client1 cet=new client1();
							Thread client=new Thread(cet);
							cet.Window();
							cet.getID(ID);
							client.start();
							primaryStage.setScene(cet.scene);
							break;
						}
						
						else {
							JOptionPane.showMessageDialog( null, "密码错误！", "警告",JOptionPane.WARNING_MESSAGE, null);
						}
					}
					
					
					catch(IOException err){
						System.out.println(err);
					}
						JOptionPane.showMessageDialog( null, "账号不存在!", "警告",JOptionPane.WARNING_MESSAGE, null);
				}
				else 
					continue;
			}
		}
	);
	}
}



