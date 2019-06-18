package 界面;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class 聊天室主界面 extends Application {
	Button test1=new Button("Test Button 1");
	Button test2=new Button("Test Button 2");
	Label welcome=new Label("登陆成功！欢迎来到聊天室！");
	public void start(Stage primaryStage) {
		BorderPane mainPane=new BorderPane();//创建主面板
		
		//创建顶部菜单栏面板
		GridPane Menu=new GridPane();
		MenuBar Bar=new MenuBar();
		Menu one=new Menu("test1");
		Menu two=new Menu("test2");
		
		
				
		//创建聊天区的面板，下属聊天框和输入框
		BorderPane ChatWindow=new BorderPane();
		TextArea chat=new TextArea("text");
		TextArea input=new TextArea("input");
		
		//创建朋友列表面板
		GridPane friend_list=new GridPane(); 
		TabPane tp1=new TabPane();
		Tab tb1=new Tab();
		Tab tb2=new Tab();
		
		ChatWindow.setPrefSize(500,480);
		//ChatWindow.setPadding(new Insets(10,10,10,10));
		ChatWindow.setTop(chat);
		//ChatWindow.setBottom(input);
		chat.setPrefSize(500, 480);
		
		Menu.setPrefSize(700, 20);
		Bar.setPrefSize(700, 20);
		Menu.add(Bar, 0, 0);
		Bar.getMenus().addAll(one,two);
		
		friend_list.setPrefSize(190, 480);
		tp1.setPrefSize(190, 480);
		friend_list.add(tp1, 0, 0);
		tb1.setText("XTao Cao");
		tb2.setText("Yang YaQ");
		tp1.getTabs().addAll(tb1,tb2);
		
		
		mainPane.setTop(Menu);
		mainPane.setLeft(friend_list);
		mainPane.setRight(ChatWindow);
		
		
		Scene mainScene=new Scene(mainPane,700,500);
		primaryStage.setTitle("聊天室");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
