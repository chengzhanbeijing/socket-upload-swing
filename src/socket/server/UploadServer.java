package socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import socket.thread.UploadThread;

public class UploadServer {

	public static final String FILEDIR = "E:\\wtb";
	
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		try {
			server = new ServerSocket(8888);
			while(true){
					client = server.accept();
					UploadThread thread = new UploadThread(client,FILEDIR);
					new Thread(thread).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
