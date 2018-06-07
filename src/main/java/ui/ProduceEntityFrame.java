package ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import utils.Constants;

/**
 * 生成实体类界面
 * 
 * @author ma
 *
 */
public class ProduceEntityFrame extends JFrame {
	private int rows = 9; // 填写实体属性的行数
	private Font font;
	private GridLayout gridLayout;
	private JPanel [] jps = new JPanel[rows + 4];

	public ProduceEntityFrame() {
		// 实体填写窗口
		// 1.GridBoder布局
		gridLayout = new GridLayout(rows + 4, 4);
		// 2.flow
		this.setTitle("填写实体对象");
		this.setLayout(gridLayout);
		this.setBounds(Constants.DEFAULT_X, Constants.DEFAULT_Y, Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		for (int i = 0; i < jps.length; i++) {
			jps[i] = new JPanel();
		}

		// 添加到pojo填写窗口
		// 添加表名
		JLabel pojoTabelLabel = new JLabel("实体名:");
		JLabel pojoTabelComLabel = new JLabel("注释:");
		JLabel isGenDbLabel = new JLabel("是否生成数据库对应的表:");
		JLabel isGenAllLabel = new JLabel("是否生成对应的(持久层/业务层/控制层):");
		JTextField pojoTabelField = new JTextField();
		JTextField commentField = new JTextField();
		// 扩展按钮
		JButton addRowBut = new JButton("取消");
		JButton commitBut = new JButton("确定");
		JRadioButton isGenDb = new JRadioButton();
		JRadioButton isGenAll = new JRadioButton();
		font = new Font(null, 1, 24);
		// 字体
		pojoTabelLabel.setFont(font);
		pojoTabelField.setFont(font);
		pojoTabelComLabel.setFont(font);
		commentField.setFont(font);
		pojoTabelField.setColumns(13);
		commentField.setColumns(18);
		commitBut.setFont(font);
		addRowBut.setFont(font);
		isGenDbLabel.setFont(font);
		isGenAllLabel.setFont(font);
		// 添加列
		jps[0].add(pojoTabelLabel);
		jps[0].add(pojoTabelField);
		jps[0].add(pojoTabelComLabel);
		jps[0].add(commentField);
		// 先加5行
		int i = 1;
		for (; i <= rows; i++) {
			addRows(i);
		}
		// 加入扩展按钮
		jps[jps.length - 3].add(isGenDbLabel);
		jps[jps.length - 3].add(isGenDb);
		jps[jps.length - 2].add(isGenAllLabel);
		jps[jps.length - 2].add(isGenAll);
		jps[jps.length - 1].add(commitBut);
		jps[jps.length - 1].add(addRowBut);
		
		for (i = 0; i < jps.length; i++) {
			this.add(jps[i]);
		}
	}

	private void addRows(int index) {
		JLabel typeLabel = new JLabel("类型:");
		JLabel defLabel = new JLabel("含义:");
		JComboBox box = new JComboBox();
		// 属性
		JLabel fieldLabel = new JLabel("属性:");
		// 属性文本
		JTextField field = new JTextField();
		// 注释文本
		JTextField comField = new JTextField();
		// 字体
		if (font != null) {
			fieldLabel.setFont(font);
			typeLabel.setFont(font);
			defLabel.setFont(font);
			field.setFont(font);
			comField.setFont(font);
			box.setFont(font);
			field.setColumns(10);
			comField.setColumns(10);
		}
		// 下拉框
		final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
		defaultComboBoxModel1.addElement("Integer");
		defaultComboBoxModel1.addElement("Long");
		defaultComboBoxModel1.addElement("Double");
		defaultComboBoxModel1.addElement("Float");
		defaultComboBoxModel1.addElement("String");
		defaultComboBoxModel1.addElement("Date");
		defaultComboBoxModel1.addElement("List");
		defaultComboBoxModel1.addElement("Set");
		defaultComboBoxModel1.addElement("Map");
		box.setModel(defaultComboBoxModel1);
		// 添加至窗口
		jps[index].add(typeLabel);
		jps[index].add(box);
		jps[index].add(fieldLabel);
		jps[index].add(field);
		jps[index].add(defLabel);
		jps[index].add(comField);

	}
}
