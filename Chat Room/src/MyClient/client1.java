package MyClient;
import java.net.*;
import java.io.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class client1 implements Runnable{
	
	BorderPane mainPane=new BorderPane();
	VBox vb_friend=new VBox();//�����б�
	
	//���ڴ��������Ŀؼ�
	static TextField tf_input=new TextField();
	Button bt_confirm=new Button("����");
	HBox hb_Input=new HBox(tf_input,bt_confirm);
	
	//���ڴ������촰�ڵĿؼ�
	GridPane gp_chatWindow=new GridPane();
	static TextArea ta_chat=new TextArea();
	Scene scene=new Scene(mainPane);
	
	public void Window() {
		ta_chat.setEditable(false);
		vb_friend.setPrefSize(80, 400);
		
		mainPane.setPrefSize(400, 400);
		mainPane.setLeft(vb_friend);
		mainPane.setCenter(ta_chat);
		mainPane.setBottom(hb_Input);
	}
	
	static String ID;
	public void getID(String user) {
		ID=user;
	}
	
	public static String sendID() {
		return ID;
	}
	
	
	
	@Override
	public void run() {
		try {
			Socket clientSocket;
			boolean flag=true;
			clientSocket=new Socket("localhost",8080);
			receive rc=new receive(clientSocket, flag);
			send sd=new send(clientSocket);
			Thread receive=new Thread(rc);
			Thread send=new Thread(sd);
			sd.getID(ID);
			receive.start();
			send.start();
		}
		catch(Exception e) {
			System.out.println(e+"1");
		}
	}
}







class receive implements Runnable{
	Socket clientSocket;
	boolean flag;
	public receive(Socket s,boolean b) {
		super();
		clientSocket=s;
		flag=b;
	}
	
	
	@Override
	public void run() {
		try {
			InputStream is=clientSocket.getInputStream();
			BufferedReader cin=new BufferedReader(new InputStreamReader(is));
			String line;
			
			
			while((line=cin.readLine())!=null) {
				System.out.println(line);
				client1.ta_chat.appendText(line+'\n');
				
				
				if(line.equals("bye")) {
					flag=false;
					Thread.interrupted();
					break;
				}
			}
			//is.close();
			//cin.close();
		
		} catch (IOException e) {
			System.out.println(e+"2");
		}
	}
}







//���ڷ�����Ϣ���߳�
class send implements Runnable{
	Socket clientSocket;
	String ID;
	public send(Socket s) {
		clientSocket=s;
	}
	//�ӵ�½�����л�ö�ӦID
	public void getID(String ID) {
		this.ID=ID;
	}

	
	
	@Override
	public void run() {
		try {
			OutputStream os=clientSocket.getOutputStream();//ΪSocket���������
			DataOutputStream cout=new DataOutputStream(os);
			
			client1.tf_input.setOnAction(e->//������ENTER��ʱ��
				{	
					String line;
					line=ID+"˵:\n"+client1.tf_input.getText()+'\n';//��TextField�е��ı�����line
					client1.tf_input.clear();//���TextField�е��ı�
					//client1.ta_chat.appendText(line);//��line�е�������ʾ��TextArea�������
					try {
						cout.write(line.getBytes());;//��line�ַ���ת��Ϊ�ֽ������д����������
						cout.flush();//ǿ����ջ�����
					} catch (IOException e1) {
						System.out.println(e1);
					}
				}
			);
		
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
