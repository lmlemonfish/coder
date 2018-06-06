package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 要求用户输入表名以及文件输出路径界面
 * @author ma
 *
 */
public class ConfirmInfoFrame extends JFrame {

	public static final int DEFAULT_X = 300;
	public static final int DEFAULT_Y = 300;
	public static final int DEFAULT_WIDTH = 550;
	public static final int DEFAULT_HEIGHT = 400;
	
	public ConfirmInfoFrame() {
		init(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
	}

	public ConfirmInfoFrame(int x, int y, int width, int height) {
		init(x, y, width, height);
	}
	
	private JLabel [] jls; 
	private JButton confirmButton;
	private JButton cancleButton;
	private JButton fileChooseButton;
	private JTextField tableName;
	private JFileChooser fileChooser;
	private JPanel [] jps;
	
	/**
	 * 初始化界面
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	private void init(int x, int y, int width, int height) {
		// 设置界面坐标和大小
		this.setBounds(x, y, width, height);
		this.setTitle("填写确认信息");
		this.setLayout(new GridLayout(6, 1));
		
		jls = new JLabel[3];
		jps = new JPanel[3];
		
		for (int i = 0; i < jps.length; i++) {
			jps[i] = new JPanel();
		}
		
		jls[0] = new JLabel("请输入表名称: ");
		jls[1] = new JLabel("请选择文件输出路径: ");
		jls[2] = new JLabel();
		
		tableName = new JTextField(15);
		
		confirmButton = new JButton("确定");
		cancleButton = new JButton("取消");
		fileChooseButton = new JButton("选择文件输出位置");
		fileChooseButton.addActionListener(new ButtonClick());
		
		jps[0].add(jls[0]);
		jps[0].add(tableName);

		jps[1].add(jls[1]);
		jps[1].add(jls[2]);
		jps[1].add(fileChooseButton);
		
		jps[2].add(confirmButton);
		jps[2].add(cancleButton);
		
		for (int i = 0; i < jps.length; i++) {
			this.add(jps[i]);
		}
		
		this.setVisible(true);
	}
	
	private static StringBuilder sb = new StringBuilder(System.getProperty("user.dir")); // 项目路径
	private static JFrame fileFrame; // 文件选择界面
	
	/**
	 * 按钮点击事件
	 * @author ma
	 *
	 */
	private class ButtonClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(fileChooseButton)) {
				 
				if (fileChooser == null) {
					fileChooser = new JFileChooser(); // 文件选择框

					sb.append(File.separator);
					sb.append("src");
					sb.append(File.separator);
					sb.append("main");
					sb.append(File.separator);
					sb.append("java");
					
					String path = sb.toString();
					
					fileFrame = new JFrame("选择文件输出位置");
					fileFrame.setBounds(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
					fileFrame.add(fileChooser);
					
					fileFrame.setVisible(true);
					
					fileChooser.setCurrentDirectory(new File(path)); // 设置文件选择框初始目录
					fileChooser.setFileSelectionMode(1); // 设置只能选择文件夹
					fileChooser.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							File file = fileChooser.getSelectedFile();
							jls[2].setText(file.getPath());
							System.out.println(file.getPath());
							fileFrame.setVisible(false);
						}
					});
					
				} else {
					fileFrame.setVisible(true);
					
				}
				
			}
		}
		
	}
}
