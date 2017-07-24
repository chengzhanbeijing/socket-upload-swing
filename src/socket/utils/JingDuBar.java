package socket.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class JingDuBar extends JFrame{

	private JProgressBar progress;
	private JButton btn;
	private JLabel[] labels = new JLabel[5];
	private JButton closeBtn;
	public JProgressBar getProgress() {
		return progress;
	}
	public void setProgress(JProgressBar progress) {
		this.progress = progress;
	}
	public JButton getBtn() {
		return btn;
	}
	public void setBtn(JButton btn) {
		this.btn = btn;
	}
	public JLabel[] getLabels() {
		return labels;
	}
	public void setLabels(JLabel[] labels) {
		this.labels = labels;
	}
	public JButton getCloseBtn() {
		return closeBtn;
	}
	public void setCloseBtn(JButton closeBtn) {
		this.closeBtn = closeBtn;
	}
	public JingDuBar(String title,String bgImage) {
		super();
		setAlwaysOnTop(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		JPanel panel = new JPanel(new BorderLayout());
		JPanel top = new JPanel();
		top.setBackground(new Color(255,153,204));
		BorderLayout bor = new BorderLayout();
		top.setLayout(bor);
		JLabel tt = new JLabel(title);
		ImageIcon backgroundImage = new ImageIcon(System.getProperty("user.dir")+"\\bin\\image\\close.png");//关闭图片
		closeBtn = new JButton("close",backgroundImage);
		closeBtn.setBackground(new Color(255,153,204));
		top.add(tt,BorderLayout.CENTER);
		top.add(closeBtn, BorderLayout.EAST);
		top.add(new JLabel(""),BorderLayout.WEST);
		panel.add(top,BorderLayout.NORTH);
		ImageIcon icon = new ImageIcon(bgImage);
		btn = new JButton(icon);
		getLayeredPane().add(btn,new Integer(Integer.MIN_VALUE));
		btn.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		GridLayout gl = new GridLayout(this.getLabels().length,1);
		btn.setLayout(gl);
		for(int i=0;i<this.labels.length;i++){
			this.getLabels()[i] = new JLabel("000"+i);
			btn.add(labels[i]);
		}
		panel.add(btn,BorderLayout.CENTER);
		progress = new JProgressBar(1,100);
		progress.setStringPainted(true);
		progress.setBorderPainted(false);
		progress.setString("0%");
		progress.setBackground(Color.WHITE);
		panel.add(progress,BorderLayout.SOUTH);
		setContentPane(panel);
		Dimension screen = getToolkit().getScreenSize();
		setSize(icon.getIconWidth()+600,icon.getIconWidth()+600);
		setLocation((screen.width-getSize().width)/2,(screen.height-getSize().height)/2);
		new DragJWindow(this,panel);
		setVisible(true);
	}
}
