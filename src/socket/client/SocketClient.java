package socket.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import socket.utils.JingDuBar;

public class SocketClient {

	private boolean isClose = true;

	public boolean isClose() {
		return isClose;
	}

	public void setClose(boolean isClose) {
		this.isClose = isClose;
	}
	public static void main(String[] args) {
		final SocketClient sc = new SocketClient();
		String filePath = "G:\\电影\\[电影天堂www.dy2018.com]美国队长3BD中英双字.rmvb";
		sc.setClose(true);
		File upload = new File(filePath);
		long fileLength = upload.length();
		String fileName = upload.getName();
		try{
			Socket client = null;
			client = new Socket("localhost",8888);
			OutputStream out = client.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeUTF("文件前缀_"+fileName);
			dos.writeLong(fileLength);
			FileInputStream fis = new FileInputStream(upload);
			byte[] buffer = new byte[2048];
			int c = -1;
			double sc1 = 0;
			int s = 0;
			//TODO进度面板设置
			final JingDuBar jdb = new JingDuBar("文件上传",System.getProperty("user.dir")+"\\bin\\image\\jingdu.gif");
			String[] uploadInfo = new String[]{"正在上传文件:"+filePath,
					"文件名称:"+fileName,"上传文件大小:"+fileLength+"字节",
					"已上传:0字节","上传比例:0%"};
			JLabel[] labels = jdb.getLabels();
			for(int i=0;i<labels.length;i++){
				labels[i].setText(uploadInfo[i]);
			}
			JProgressBar bar = jdb.getProgress();
			JButton closeBtn = jdb.getCloseBtn();
			closeBtn.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent ae) {
			     jdb.dispose();
			     sc.setClose(false);
			    }
			   });
			while((c=fis.read(buffer))!=-1&&sc.isClose){
				//Thread.sleep(100);
				s +=c;
				sc1 = (double)s/fileLength;
				int b = (int)(sc1*100);
				dos.write(buffer,0,c);
				bar.setValue(b);
				bar.setString(b+"%");
				labels[3].setText("已上传:"+s+"字节");
				labels[4].setText("上传比例:"+b+"%");
				String[] uploadScale = new String[]{"已上传"+s+"字节","上传比例:"+b+"%"};
			}
			jdb.dispose();
			out.close();
			fis.close();
			client.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
