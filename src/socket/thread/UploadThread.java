package socket.thread;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class UploadThread implements Runnable {
	
	private Socket client;
	private String fileDir;
	private String fileName;
	
	public UploadThread(Socket client, String fileDir) {
		super();
		this.client = client;
		this.fileDir = fileDir;
	}



	@Override
	public void run() {
		InputStream ins = null;
		FileOutputStream ous = null;
		try{
			ins = client.getInputStream();
			DataInputStream da = new DataInputStream(ins);
			fileName = da.readUTF();
			long fileSize = da.readLong();
			int c = -1;
			long already = 0;
			byte[] buffer = new byte[1024];
			ous = new FileOutputStream(fileDir+File.separator+fileName);
			while((c=da.read(buffer))!=-1){
				ous.write(buffer,0,c);
				already +=c;
			}
			ous.flush();
			ous.close();
			ins.close();
			client.close();
			if(already!=fileSize){
				File f = new File(fileDir+File.separator+fileName);
			    f.delete();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
