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
	
	
	
	public void start(Stage primaryStage) throws IOException {
		GridPane Land=new GridPane();
		GridPane Register=new GridPane();
		GridPane button=new GridPane();
		GridPane titleA=new GridPane();
		GridPane titleB=new GridPane();
		
		Tab tab1=new Tab("��¼",Land);
		Tab tab2=new Tab("ע��",Register);
		TabPane tb1=new TabPane(tab1,tab2);
		Scene mainscene=new Scene(tb1,350,250);
		
		
		Land.setPadding(new Insets(20,20,20,20));
		Register.setPadding(new Insets(20,20,20,20));
		Land.setHgap(-20);
		Land.setVgap(5);			//���ñ߿�߾��Լ��и�
		
		
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
		Land.add(button, 0, 3);	//��GridPane��幹����¼����
		
		
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
		primaryStage.setScene(mainscene);//������scene�Ž�����̨
		primaryStage.setTitle("ChatRoom");//���ñ���
		primaryStage.show();
		
		
		
		
		
		//���������˺ź�������ļ���
		File file=new File("Data");
		if(!file.exists()){//����ļ��в�����
			file.mkdir();//�����ļ���
		}
		File f_admin=new File("Data","admin.txt");
		if(f_admin.exists()==false) {
			f_admin.createNewFile();
		}
		
		/*�˺����봢�淽ʽ��
		 * ÿ���½�һ���˺�����Data�ļ������½�һ�����˺�Ϊ����.txt�ļ�
		 * ���ı��ļ��ڴ����Ÿ��˺�����
		 */
		
		
		
		//�������¼�
		
		//ע�ᰴť����¼������°�ť��ע�ᣩ
		confirm.setOnAction(e->
			{
				String ID=UserID1.getText();
				String password=password1.getText();
				String password_con=password_confirm.getText();
				
				
				if(password.equals(password_con)==false) //���������������벻һ��
					JOptionPane.showMessageDialog( null, "�����������벻һ��", "����",JOptionPane.WARNING_MESSAGE, null);
				else {
					
					
					
					if(password.length()<6) //������볤��С��6
						JOptionPane.showMessageDialog( null, "������������λ����", "����",JOptionPane.WARNING_MESSAGE, null);
					else {
						
						
						
						for(int i=0;i<file.list().length;i++) {//�������ڴ����û���Ϣ���ļ���
							
			
							
							if(file.list()[i].equals(ID)) //���������ͬ���û�
								JOptionPane.showMessageDialog( null, "���û����ѱ�ע�ᣡ", "����",JOptionPane.WARNING_MESSAGE, null);
							else {

								try {
									File user=new File("Data",ID+".txt");
									user.createNewFile();//���û����������ļ���
									FileWriter fw=new FileWriter("Data/"+ID+".txt");
									fw.write(password);//������д���ı��ļ���
									fw.close();
									JOptionPane.showMessageDialog(null, "�˺Ŵ����ɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE); 
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
	
	
		
		
		
		
		
		//��¼��ť����¼�
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
							JOptionPane.showMessageDialog(null, "��½�ɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE); 
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
							JOptionPane.showMessageDialog( null, "�������", "����",JOptionPane.WARNING_MESSAGE, null);
						}
					}
					
					
					catch(IOException err){
						System.out.println(err);
					}
						JOptionPane.showMessageDialog( null, "�˺Ų�����!", "����",JOptionPane.WARNING_MESSAGE, null);
				}
				else 
					continue;
			}
		}
	);
	}
}



