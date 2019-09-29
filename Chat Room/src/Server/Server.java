package Server;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;

public class Server extends Application{
	public static void main(String[] args) {
		server1 server=new server1();//运行server
		Thread th=new Thread(server);
		th.start();
		Application.launch(args);
	}
	
	
	
	static TextArea ta_chat=new TextArea();
	public void start(Stage stage) throws Exception {
		
		VBox hb_list=new VBox();
		HBox hb_main=new HBox(hb_list,ta_chat);
		
		ta_chat.setPrefSize(300, 400);
		hb_list.setPrefSize(100, 400);
		ta_chat.setEditable(false);
		Scene scene=new Scene(hb_main,400,400);
		stage.setTitle("服务端");
		stage.setScene(scene);
		stage.show();
	}
	
}


class server1 implements Runnable{
	ServerSocket server=null;//创建ServerSocket对象
	Socket clientSocket;
	boolean flag=true;//用于判断聊天是否结束
	public static List<Socket> socketList=Collections.synchronizedList(new ArrayList<>());//定义保存socket的数组
	String ID;
	
	
	@Override
	public void run() {
		try {
			server=new ServerSocket(8080);//以指定端口建立ServerSocket
			System.out.println("端口号为："+server.getLocalPort());
			
			while(flag) {
				clientSocket=server.accept();//监听客户端发来的连接请求，并且返回一个和客户端Socket连接的Socket对象
				System.out.println("有一个客户端连接："+clientSocket.getRemoteSocketAddress().toString());
				socketList.add(clientSocket);
				serverThread st=new serverThread(clientSocket);
				Thread thr=new Thread(st);
				thr.start();
				//sout.close();
				//os.close();
				//sin.close();
				//is.close();//关闭各种流
				//clientSocket.close();//关闭Socket
				//System.exit(0);//关闭程序
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}








//负责处理每个线程通信
class serverThread implements Runnable{
	Socket s=null;
	BufferedReader sin;
	DataOutputStream sout;
	public serverThread(Socket socket) {
		s=socket;
	}
	
	
	
	@Override
	public void run() {
		try {
			InputStream is=s.getInputStream();//为Socket创建输入流
			sin=new BufferedReader(new InputStreamReader(is));//创建缓冲类字符流，缓冲输入的内容
			OutputStream os=s.getOutputStream();//为Socket创建输出流
			sout=new DataOutputStream(os);//以数据流的方式输
			String line=null;
			while((line=readClient())!=null) {
				//遍历socketList中的每个Socket，将所读到的内容向每个socket发送一次
				for(Socket s:server1.socketList) {
					PrintStream ps=new PrintStream(s.getOutputStream());
					ps.println(line);
				}
				Server.ta_chat.appendText(line+'\n');
			}
		}
		catch(IOException e) {
			System.out.println(e+"  1");
		}
	}
	
	
	
	private String readClient() {
		try {
			return sin.readLine();
		}
		catch(IOException e) {
			server1.socketList.remove(s);//当线程连接丢失的时候删除socket
		}
		return null;
	}
}

