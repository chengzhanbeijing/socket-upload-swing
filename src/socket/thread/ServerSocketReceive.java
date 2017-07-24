package socket.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketReceive {

	public void accept(){
		try {
			ServerSocket sc = new ServerSocket(10086);
			Socket socket = null;
			int count = 0;
			while(true){
				socket = sc.accept();
				ServerThread st = new ServerThread(socket);
				st.start();
				count++;
				System.out.println("客户端连接的数量是:"+count);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ServerSocketReceive().accept();
	}
}
