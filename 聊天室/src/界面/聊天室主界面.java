package ����;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class ������������ extends Application {
	Button test1=new Button("Test Button 1");
	Button test2=new Button("Test Button 2");
	Label welcome=new Label("��½�ɹ�����ӭ���������ң�");
	public void start(Stage primaryStage) {
		BorderPane mainPane=new BorderPane();//���������
		
		//���������˵������
		GridPane Menu=new GridPane();
		MenuBar Bar=new MenuBar();
		Menu one=new Menu("test1");
		Menu two=new Menu("test2");
		
		
				
		//��������������壬���������������
		BorderPane ChatWindow=new BorderPane();
		TextArea chat=new TextArea("text");
		TextArea input=new TextArea("input");
		
		//���������б����
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
		primaryStage.setTitle("������");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
