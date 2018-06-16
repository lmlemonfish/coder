package ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Constants;
import utils.StringUtils;

/**
 * 要求用户输入表名以及文件输出路径界面
 * @author ma
 *
 */
public class ConfirmInfoFrame extends JFrame {
	
	public static ConfirmInfoFrame getInstance() {
		return StaticClassLazy.frame;
	}
	
	private static class StaticClassLazy {
		private static ConfirmInfoFrame frame = new ConfirmInfoFrame();
	}
	
	private ConfirmInfoFrame() {
		init(Constants.DEFAULT_X, Constants.DEFAULT_Y, Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT);
		
	}

/*	public ConfirmInfoFrame(int x, int y, int width, int height) {
		init(x, y, width, height);
	}*/
	
	private JLabel [] jls; 
	private JButton confirmButton;
	private JButton cancleButton;
	private JButton fileChooseButton;
	private JTextField tableName;
	private JFileChooser fileChooser;
	private JPanel [] jps;
	
	private Font font = new Font(null, 1, 18);
	
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
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				shutDown = true;
				super.windowClosing(e);
			}
			
		});
		
		jls = new JLabel[3];
		jps = new JPanel[3];
		
		for (int i = 0; i < jps.length; i++) {
			jps[i] = new JPanel();
		}
		
		jls[0] = new JLabel("请输入表名称: ");
		jls[1] = new JLabel("请选择文件输出路径: ");
		jls[2] = new JLabel();
		
		for (int i = 0; i < jls.length; i++) {
			jls[i].setFont(font);
		}
		
		tableName = new JTextField(15);
		
		confirmButton = new JButton("确定");
		confirmButton.setFont(font);
		cancleButton = new JButton("取消");
		cancleButton.setFont(font);
		fileChooseButton = new JButton("选择文件输出位置");
		fileChooseButton.setFont(font);
		fileChooseButton.addActionListener(new ButtonClick());
		confirmButton.addActionListener(new ButtonClick());
		
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
	
	/**
	 * 获取输入的表名
	 * @return
	 */
	public String getTableName() {
		return tableName.getText();
	}
	
	/**
	 * 获取文件生成路径
	 * @return
	 */
	public String getFilePath() {
		if (fileChooser == null || fileChooser.getSelectedFile() == null) return null;
		
		return fileChooser.getSelectedFile().getPath();
	}
	
	private static StringBuilder sb = new StringBuilder(System.getProperty("user.dir")); // 项目路径
	private static JFrame fileFrame; // 文件选择界面
	private volatile boolean shutDown = false;
	
	public boolean isShutDown() {
		return shutDown;
	}

	/**
	 * 按钮点击事件
	 * @author ma
	 *
	 */
	private class ButtonClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(fileChooseButton)) { // 选择文件输出位置
				 
				if (fileChooser == null) {
					fileChooser = new JFileChooser(); // 文件选择框
					
					String path = sb.toString();
					
					fileFrame = new JFrame("选择文件输出位置");
					fileFrame.setBounds(Constants.DEFAULT_X, Constants.DEFAULT_Y, Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT);
					fileFrame.add(fileChooser);
					
					fileFrame.setVisible(true);
					
					fileChooser.setCurrentDirectory(new File(path)); // 设置文件选择框初始目录
					fileChooser.setFileSelectionMode(1); // 设置只能选择文件夹
					fileChooser.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							File file = fileChooser.getSelectedFile();
							jls[2].setText(file.getPath());
							fileFrame.setVisible(false);
						}
					});
					
				} else {
					fileFrame.setVisible(true);
					
				}
				
			} else if (e.getSource().equals(confirmButton)) { // 确定
				if (StringUtils.isNull(jls[2].getText())) {
					JOptionPane.showMessageDialog(ConfirmInfoFrame.this, "生成文件路径不能为空!");
					return;
				}
				shutDown = true;
				ConfirmInfoFrame.this.setVisible(false);
				
			}
		}
		
	}
}
