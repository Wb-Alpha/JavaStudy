package TDP;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class server{
	public void main(String[] args) {
		MyServer MS=new MyServer();
		MS.serverStart();
	}
}




class MyServer implements Runnable{
	ServerSocket server=null;//����һ��ServerSocket
	Socket connectSocket;//����ǰ�߳�ͨѶ��Socket����
	boolean judge=true; //����Ƿ����
	Thread connenThread;//���ͻ��˷�����Ϣ���߳�
	BufferedReader sin;//����������
	DataOutputStream sout;//�������������
	public void serverStart() {
		
	}
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
