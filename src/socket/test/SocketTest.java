package socket.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class SocketTest {

	public String getAdd(){
		InetAddress add;
		try {
			add = InetAddress.getLocalHost();
			byte[] b = add.getAddress();
			System.out.println(add.getHostName()+"===="+add.getLocalHost()+"==="+b[0]+"="+b[1]+"="+b[2]+"="+b[3]);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		//new SocketTest().getAdd();
		//new SocketTest().getUrl();
		//new SocketTest().readUrl();
		new SocketTest().sendData();
	}
	
	public String getUrl(){
		try {
			URL url = new URL("http://www.baidu.com");
			url = new URL(url,"/index.html?username=zhangsan#test");
			System.out.println(url.getProtocol()+"==="+url.getHost()+"==="+url.getPort());
			System.out.println(url.getPath()+"==="+url.getFile()+"==="+url.getRef()+"==="+url.getQuery());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void readUrl(){
		try{
			URL url = new URL("http://www.baidu.com");
			InputStream is = url.openStream();
			InputStreamReader re = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(re);
			String data = br.readLine();
			while(data!=null){
				System.out.println(data);
				data = br.readLine();
			}
			br.close();
			re.close();
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendData(){
		try {
			Socket sc = new Socket("localhost",10086);
			OutputStream os = sc.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("username:admin;密码:123");
			pw.flush();
			sc.shutdownOutput();
			InputStream is = sc.getInputStream();
			InputStreamReader ir = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(ir);
			String info = null;
			while((info=br.readLine())!=null){
				System.out.println("我是客户端：服务器说"+info);
			}
			br.close();
			ir.close();
			is.close();
			pw.close();
			os.close();
			sc.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
