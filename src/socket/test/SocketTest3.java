package socket.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class SocketTest3 {
	
	public static void test2(){
		Socket soc = new Socket();
	}
	
	public static void conn(){
		Socket s = new Socket();
		SocketAddress ad = new InetSocketAddress("localhost",8000);
		try {
			s.connect(ad, 60000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//conn();
		//conn2("www.baidu.com",80);
		getHTML("www.baidu.com",80);
	}
	
	public static void getHTML(String host,int port){
		try {
			Socket so = new Socket(host,port);
			StringBuffer sb = new StringBuffer("GET/index.html"+"\r\n");
			sb.append("Host:"+host+"\r\n");
			sb.append("Accept:text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01\r\n");
			sb.append("Accept-Language:	zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
			sb.append("Accept-Encoding:gzip, deflate, br\r\n");
			sb.append("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0\r\n");
			OutputStream ous = so.getOutputStream();
			ous.write(sb.toString().getBytes());
			so.shutdownOutput();
			InputStream ins = so.getInputStream();
			/*ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			byte[] bs = new byte[1024];
			int len=0;
			while((len=ins.read(bs))!=-1){
				buffer.write(bs,0,len);
			}
			System.out.println(new String(buffer.toByteArray()));*/
			BufferedReader bu = new BufferedReader(new InputStreamReader(ins));
			String data;
			while((data=bu.readLine())!=null){
				System.out.println(data);
			}
			so.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void conn2(String host,int port){
		InetAddress add=null;
		try {
			add = InetAddress.getByName(host);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Socket so = null;
		String result="";
		try {
			long start = System.currentTimeMillis();
			so= new Socket(add,port,InetAddress.getByName("192.168.50.93"),2345);
			//so.connect(add,1000);
			long end = System.currentTimeMillis();
			result = (end-start)+"millseconds";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(so!=null)
				try {
					so.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			System.out.println(add+"="+result);
		}
		
	}
	
	public static void conn1(String args[]){
		String add = "www.sina.com.cn";
		if(args.length>0)add = args[0];
		Socket s  = null;int i=0;
		for(i=1;i<1024;i++){
			try{
					s = new Socket(add,i);
					System.out.println("exist server port :"+i);
					if(s!=null){
						System.out.println(i+"="+s.getPort());
					}
			}catch(Exception e){
				//System.out.println("do not exist connect port:"+i);
			}finally{
				if(s!=null){
					try {
						s.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
