package 界面;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class UI extends Application{
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
	
	public void start(Stage primaryStage) {
		GridPane Land=new GridPane();
		GridPane Register=new GridPane();
		GridPane button=new GridPane();
		GridPane titleA=new GridPane();
		GridPane titleB=new GridPane();
		Tab tab1=new Tab("登录",Land);
		Tab tab2=new Tab("注册",Register);
		TabPane tb1=new TabPane(tab1,tab2);
		Scene mainscene=new Scene(tb1,350,250);
		
		//Land.setGridLinesVisible(true);
		Land.setPadding(new Insets(20,20,20,20));
		Register.setPadding(new Insets(20,20,20,20));
		Land.setHgap(-20);
		Land.setVgap(5);//设置边框边距以及行高
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
		Land.add(button, 0, 3);//以GridPane面板构建登录界面
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
		primaryStage.setTitle("ChatRoom");//设置标题
		primaryStage.setScene(mainscene);//将场景scene放进主舞台
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
