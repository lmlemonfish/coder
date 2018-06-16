package ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import builder.TemplateBuilder;
import builder.concrete.TemplateFromPojoBuilder;
import builder.director.TemplateDirector;
import entity.EntityData;
import entity.FileFromPojoData;
import utils.Constants;
import utils.StringUtils;
import utils.ThreadPoolUtils;

/**
 * 生成实体类界面
 * 
 * @author ma
 *
 */
public class ProduceEntityFrame extends JFrame {
	private int rows = 10; // 填写实体属性的行数
	private Font font;
	private GridLayout gridLayout;
	private JPanel [] jps = new JPanel[rows + 5];
	private static List<String> typeList;
	
	static {
		typeList = new ArrayList<>();
		typeList.add(Constants.INTEGER);
		typeList.add(Constants.DOUBLE);
		typeList.add(Constants.LONG);
		typeList.add(Constants.DATE);
		typeList.add(Constants.STRING);
		typeList.add(Constants.FLOAT);
		typeList.add(Constants.SET);
		typeList.add(Constants.LIST);
		typeList.add(Constants.MAP);
	}
	
	private JButton cancleBut;
	private JButton commitBut;
	private JRadioButton isGenDb;
	private JRadioButton isGenAll;
	private JTextField pojoTabelField;
	private JTextField commentField;
	private JTextField pojoPackageField;

	public ProduceEntityFrame() {
		// 实体填写窗口
		// 1.GridBoder布局
		gridLayout = new GridLayout(rows + 5, 4);
		// 2.flow
		this.setTitle("填写实体对象");
		this.setLayout(gridLayout);
		this.setBounds(Constants.DEFAULT_X, Constants.DEFAULT_Y, Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				shutDown = true;
				super.windowClosing(e);
			}
			
		});
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		for (int i = 0; i < jps.length; i++) {
			jps[i] = new JPanel();
		}

		// 添加到pojo填写窗口
		// 添加表名
		JLabel pojoPackageLabel = new JLabel("包名:");
		JLabel pojoTabelLabel = new JLabel("实体名:");
		JLabel pojoTabelComLabel = new JLabel("注释:");
		JLabel isGenDbLabel = new JLabel("是否生成数据库对应的表:");
		JLabel isGenAllLabel = new JLabel("是否生成对应的(持久层/业务层/控制层):");
		pojoTabelField = new JTextField();
		commentField = new JTextField();
		pojoPackageField = new JTextField();
		JLabel info = new JLabel("多个属性和注释之间使用逗号或空格隔开");
		
		// 扩展按钮
		cancleBut = new JButton("取消");
		commitBut = new JButton("确定");
		isGenDb = new JRadioButton();
		isGenAll = new JRadioButton();
		font = new Font(null, 1, 17);
		// 字体
		info.setFont(font);
		pojoTabelLabel.setFont(font);
		pojoPackageLabel.setFont(font);
		pojoTabelField.setFont(font);
		pojoTabelComLabel.setFont(font);
		pojoPackageField.setFont(font);
		commentField.setFont(font);
		pojoTabelField.setColumns(13);
		pojoPackageField.setColumns(12);
		commentField.setColumns(15);
		commitBut.setFont(font);
		cancleBut.setFont(font);
		commitBut.addActionListener(new ButtonClicked());
		cancleBut.addActionListener(new ButtonClicked());
		isGenDbLabel.setFont(font);
		isGenAllLabel.setFont(font);
		// 添加列
		jps[0].add(pojoPackageLabel);
		jps[0].add(pojoPackageField);
		jps[0].add(pojoTabelLabel);
		jps[0].add(pojoTabelField);
		jps[0].add(pojoTabelComLabel);
		jps[0].add(commentField);
		jps[1].add(info);
		// 先加5行
		int i = 2;
		for (; i <= rows; i++) {
			addRows(i);
		}
		// 加入扩展按钮
		jps[jps.length - 3].add(isGenDbLabel);
		jps[jps.length - 3].add(isGenDb);
		jps[jps.length - 2].add(isGenAllLabel);
		jps[jps.length - 2].add(isGenAll);
		jps[jps.length - 1].add(commitBut);
		jps[jps.length - 1].add(cancleBut);
		
		for (i = 0; i < jps.length; i++) {
			this.add(jps[i]);
		}
	}
	
	private JComboBox<String> [] boxes = new JComboBox[typeList.size()];
	private JTextField[] fields = new JTextField[typeList.size()];
	private JTextField[] comFields = new JTextField[typeList.size()];

	private void addRows(int index) {
		JLabel typeLabel = new JLabel("类型:");
		JLabel defLabel = new JLabel("含义");
		/*JComboBox box = new JComboBox();*/
		boxes[index - 2] = new JComboBox<String>();
		// 属性
		JLabel fieldLabel = new JLabel("属性");
		// 属性文本
		fields[index - 2] = new JTextField();
		// 注释文本
		comFields[index - 2] = new JTextField();
		// 字体
		if (font != null) {
			fieldLabel.setFont(font);
			typeLabel.setFont(font);
			defLabel.setFont(font);
			fields[index - 2].setFont(font);
			comFields[index - 2].setFont(font);
			boxes[index - 2].setFont(font);
			fields[index - 2].setColumns(15);
			comFields[index - 2].setColumns(15);
		}
		// 下拉框
		final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
		for (String type : typeList) {
			defaultComboBoxModel1.addElement(type);
		}
		boxes[index - 2].setModel(defaultComboBoxModel1);
		// 添加至窗口
		jps[index].add(typeLabel);
		jps[index].add(boxes[index - 2]);
		jps[index].add(fieldLabel);
		jps[index].add(fields[index - 2]);
		jps[index].add(defLabel);
		jps[index].add(comFields[index - 2]);

	}
	
	private volatile boolean shutDown = false;
	
	public boolean isShutDown() {
		return shutDown;
	}
	
	private class ButtonClicked implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(commitBut)) {
				if (commitBut.isEnabled()) {
					if (StringUtils.isNull(pojoTabelField.getText())) {
						JOptionPane.showMessageDialog(ProduceEntityFrame.this, "实体名称不能为空!");
						return;
					}
					
					FileFromPojoData data = new FileFromPojoData();
					EntityData entity = new EntityData();
					
					boolean flag = false;
					
					for (int i = 0; i < fields.length; i++) {
						String text = fields[i].getText();
						if (!StringUtils.isNull(text)) { // 填写了属性值
							flag = true;
							// 获取填写的属性类型
							Object type = boxes[i].getSelectedItem();
							// 具体的属性列表
							String[] fields = StringUtils.defaultSplit(text);
							// 属性注释
							String note = comFields[i].getText();
							// 具体的属性注释列表
							String[] notes = StringUtils.defaultSplit(note);
							if (notes == null || notes.length == 0) {
								notes = new String[fields.length];
							}
							
							putFieldToEntity(entity, type, fields, notes);
							
						}
						
					}
					
					if (flag) {
						if (!StringUtils.isNull(pojoPackageField.getText())) {
							entity.setPackageName(pojoPackageField.getText());
						}
						entity.setClassName(pojoTabelField.getText());
						entity.setDescription(commentField.getText());
						data.setEntity(entity);
						
						HashMap<String, Object> valueMap = new HashMap<>();
						commitBut.setEnabled(false); // 确定按钮不能再点击
						ConfirmInfoFrame confirmInfoFrame = ConfirmInfoFrame.getInstance();
						
						ThreadPoolUtils.execute(new Runnable() {
							
							@Override
							public void run() {
								while (!confirmInfoFrame.isShutDown()) {
									if (shutDown) {
										break;
									}
									
								}
								
								if (!StringUtils.isNull(confirmInfoFrame.getFilePath())) {
									valueMap.put(Constants.TEMPLATE, data);
									
									StringBuilder sb = new StringBuilder(confirmInfoFrame.getFilePath());
									sb.append(File.separator);
									sb.append(pojoTabelField.getText());
									sb.append(".java");
									
									TemplateBuilder builder = new TemplateFromPojoBuilder(valueMap, sb.toString());
									TemplateDirector td = new TemplateDirector(builder);
									
									td.buildEntityFile();
								
								}
								
								//commitBut.setEnabled(true);
								
								if (isGenDb.isSelected()) { // 选择生成数据库表
									System.out.println(confirmInfoFrame.getTableName());
								}
								
								if (isGenAll.isSelected()) { // 选择生成服务类
									
								}
								
								shutDown = true;
								ProduceEntityFrame.this.dispose();
							}
						});
						
					}
					
				}
				
				
			} else if (e.getSource().equals(cancleBut)) {
				shutDown = true;
				ProduceEntityFrame.this.dispose();
			}
		}

		/**
		 * 将属性以及注释放入EntityData
		 * @param eData
		 * @param type
		 * @param fields
		 * @param notes
		 */
		private void putFieldToEntity(EntityData eData, Object type, String[] fields, String[] notes) {
			Map<String, String> map = new HashMap<>();

			String [] newNotes = null;
			if (notes.length < fields.length) {
				newNotes = new String[fields.length];
				System.arraycopy(notes, 0, newNotes, 0, notes.length);
			}
			
			if (newNotes != null) notes = newNotes;
			
			for (int j = 0; j < fields.length; j++) {
				if (notes[j] == null) {
					notes[j] = "";
				}
				map.put(fields[j], notes[j]);
				
			}
			if (type.equals(Constants.INTEGER)) {  // int类型
				if (eData.getIntegerMap() != null) { // 已经有过int型的属性
					eData.getIntegerMap().putAll(map);
					
				} else {
					eData.setIntegerMap(map);
					
				}
				
			} else if (type.equals(Constants.STRING)) {  // string类型
				if (eData.getStrMap() != null) {
					eData.getStrMap().putAll(map);
					
				} else {
					eData.setStrMap(map);
					
				}
				
			} else if (type.equals(Constants.DOUBLE)) { 
				if (eData.getDoubleMap() != null) {
					eData.getDoubleMap().putAll(map);
					
				} else {
					eData.setDoubleMap(map);
				}
				
			} else if (type.equals(Constants.FLOAT)) {
				if (eData.getFloatMap() != null) {
					eData.getFloatMap().putAll(map);
					
				} else {
					eData.setFloatMap(map);
				}
				
			} else if (type.equals(Constants.DATE)) { 
				if (eData.getDateMap() != null) {
					eData.getDateMap().putAll(map);
					
				} else {
					eData.setDateMap(map);
				}
			
			} else if (type.equals(Constants.LONG)) {
				if (eData.getLongMap() != null) {
					eData.getLongMap().putAll(map);
					
				} else {
					eData.setDateMap(map);
				}
				
			} else if (type.equals(Constants.LIST)) {
				if (eData.getListMap() != null) {
					eData.getListMap().putAll(map);
					
				} else {
					eData.setDateMap(map);
				}
				
			} else if (type.equals(Constants.SET)) {
				if (eData.getSetMap() != null) {
					eData.getSetMap().putAll(map);
					
				} else {
					eData.setDateMap(map);
				}
				
			} else if (type.equals(Constants.MAP)) {
				if (eData.getmMap() != null) {
					eData.getSetMap().putAll(map);
					
				} else {
					eData.setDateMap(map);
				}
			}
		}
		
	}
}
