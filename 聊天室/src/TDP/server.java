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
	ServerSocket server=null;//创建一个ServerSocket
	Socket connectSocket;//负责当前线程通讯的Socket对象
	boolean judge=true; //标记是否结束
	Thread connenThread;//给客户端发送信息的线程
	BufferedReader sin;//输入流对象
	DataOutputStream sout;//数据输出流对象
	public void serverStart() {
		
	}
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
