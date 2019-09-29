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
	VBox vb_friend=new VBox();//上线列表
	
	//用于创建输入框的控件
	static TextField tf_input=new TextField();
	Button bt_confirm=new Button("发送");
	HBox hb_Input=new HBox(tf_input,bt_confirm);
	
	//用于创建聊天窗口的控件
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







//用于发送消息的线程
class send implements Runnable{
	Socket clientSocket;
	String ID;
	public send(Socket s) {
		clientSocket=s;
	}
	//从登陆界面中获得对应ID
	public void getID(String ID) {
		this.ID=ID;
	}

	
	
	@Override
	public void run() {
		try {
			OutputStream os=clientSocket.getOutputStream();//为Socket创建输出流
			DataOutputStream cout=new DataOutputStream(os);
			
			client1.tf_input.setOnAction(e->//当按下ENTER的时候
				{	
					String line;
					line=ID+"说:\n"+client1.tf_input.getText()+'\n';//将TextField中的文本赋给line
					client1.tf_input.clear();//清除TextField中的文本
					//client1.ta_chat.appendText(line);//将line中的内容显示在TextArea聊天框中
					try {
						cout.write(line.getBytes());;//将line字符串转换为字节数组后写入数据流中
						cout.flush();//强制清空缓冲区
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
