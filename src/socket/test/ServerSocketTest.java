package socket.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

	public void conn(){
		try {
			ServerSocket sc = new ServerSocket(10086);
			Socket sk = sc.accept();
			InputStream is = sk.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String say=null;
			while((say=br.readLine())!=null){
				System.out.println("客户端说"+say);
			}
			sk.shutdownInput();
			OutputStream os = sk.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("欢迎你!");
			pw.flush();
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			sk.close();
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ServerSocketTest().conn();
	}
}
