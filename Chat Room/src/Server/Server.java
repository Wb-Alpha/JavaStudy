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
		server1 server=new server1();//����server
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
		stage.setTitle("�����");
		stage.setScene(scene);
		stage.show();
	}
	
}


class server1 implements Runnable{
	ServerSocket server=null;//����ServerSocket����
	Socket clientSocket;
	boolean flag=true;//�����ж������Ƿ����
	public static List<Socket> socketList=Collections.synchronizedList(new ArrayList<>());//���屣��socket������
	String ID;
	
	
	@Override
	public void run() {
		try {
			server=new ServerSocket(8080);//��ָ���˿ڽ���ServerSocket
			System.out.println("�˿ں�Ϊ��"+server.getLocalPort());
			
			while(flag) {
				clientSocket=server.accept();//�����ͻ��˷������������󣬲��ҷ���һ���Ϳͻ���Socket���ӵ�Socket����
				System.out.println("��һ���ͻ������ӣ�"+clientSocket.getRemoteSocketAddress().toString());
				socketList.add(clientSocket);
				serverThread st=new serverThread(clientSocket);
				Thread thr=new Thread(st);
				thr.start();
				//sout.close();
				//os.close();
				//sin.close();
				//is.close();//�رո�����
				//clientSocket.close();//�ر�Socket
				//System.exit(0);//�رճ���
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}








//������ÿ���߳�ͨ��
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
			InputStream is=s.getInputStream();//ΪSocket����������
			sin=new BufferedReader(new InputStreamReader(is));//�����������ַ������������������
			OutputStream os=s.getOutputStream();//ΪSocket���������
			sout=new DataOutputStream(os);//���������ķ�ʽ��
			String line=null;
			while((line=readClient())!=null) {
				//����socketList�е�ÿ��Socket������������������ÿ��socket����һ��
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
			server1.socketList.remove(s);//���߳����Ӷ�ʧ��ʱ��ɾ��socket
		}
		return null;
	}
}

