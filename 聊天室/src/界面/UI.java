package ����;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.geometry.*;

@SuppressWarnings("restriction")
public class UI extends Application{
	Label ID=new Label("�û�����");
	Label code=new Label("��  �룺");
	Label title=new Label("Welcome!");
	Label ID1=new Label("�� �� ����");
	Label code1=new Label("��   �룺");
	Label code_confirm=new Label("ȷ�����룺");
	Label title1=new Label("ע�����û�");
	TextField UserID=new TextField();
	TextField UserID1=new TextField();
	PasswordField password=new PasswordField();
	PasswordField password1=new PasswordField();
	PasswordField password_confirm=new PasswordField();
	Button landing=new Button("��¼");
	Button confirm=new Button("�����˺�");
	
	public void start(Stage primaryStage) {
		GridPane Land=new GridPane();
		GridPane Register=new GridPane();
		GridPane button=new GridPane();
		GridPane titleA=new GridPane();
		GridPane titleB=new GridPane();
		Tab tab1=new Tab("��¼",Land);
		Tab tab2=new Tab("ע��",Register);
		TabPane tb1=new TabPane(tab1,tab2);
		Scene mainscene=new Scene(tb1,350,250);
		
		//Land.setGridLinesVisible(true);
		Land.setPadding(new Insets(20,20,20,20));
		Register.setPadding(new Insets(20,20,20,20));
		Land.setHgap(-20);
		Land.setVgap(5);//���ñ߿�߾��Լ��и�
		title.setStyle("-fx-font-size:20");
		title1.setStyle("-fx-font-size:20");//����������ʽ
		UserID.setPromptText("�����û���");
		password.setPromptText("��������");//�����ı���Ԥ��ֵ
		button.add(landing, 0, 0);
		titleA.add(title, 0, 0);
		Land.add(titleA, 0, 0);
		Land.add(ID, 0, 1);
		Land.add(UserID, 1, 1);
		Land.add(code, 0,2);
		Land.add(password, 1, 2);
		Land.add(button, 0, 3);//��GridPane��幹����¼����
		Register.add(titleB, 0, 0);
		titleB.add(title1,0,0);
		Register.add(ID1, 0, 1);
		Register.add(UserID1, 1, 1);
		Register.add(code1, 0, 2);
		Register.add(password1, 1, 2);
		Register.add(code_confirm, 0, 3);
		Register.add(password_confirm, 1, 3);
		Register.add(confirm, 0, 4);//��GridPane��幹��ע�����
		tab1.setClosable(false);
		tab2.setClosable(false);//����������ǩ�����ɹر�
		primaryStage.setResizable(false);//���ò������
		primaryStage.setTitle("ChatRoom");//���ñ���
		primaryStage.setScene(mainscene);//������scene�Ž�����̨
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
