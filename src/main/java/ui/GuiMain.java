package ui;

import com.google.common.base.Strings;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import db.service.CommonDBService;
import engine.CoderEngine;
import entity.ConInfo;
import entity.FileFromDbData;
import entity.TableInfo;
import entity.basic.ClassBasicData;
import entity.basic.MethodBasicData;
import utils.ThreadPoolUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class GuiMain {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton codeButton;
    private JTextArea logArea;
    private JTextField cg_packNameText;
    private JTextField cg_authorText;
    private JTextField cg_entityText;
    private JTextField cg_daoText;
    private JComboBox db_type;
    private JTextField db_localIpText;
    private JButton testCon;
    private JLabel db_localIpLab;
    private JLabel db_DbNameLab;
    private JLabel db_postLab;
    private JLabel db_userNameLab;
    private JLabel db_passwordLab;
    private JTextField db_DbNameText;
    private JTextField db_postText;
    private JTextField db_userNameText;
    private JTextField db_passwordText;
    private JLabel cg_packNameLab;
    private JLabel cg_authorLab;
    private JLabel cg_entityLab;
    private JLabel cg_daoLab;
    private JLabel cg_serviceLab;
    private JTextField cg_serviceText;
    private JLabel cg_serviceImplLab;
    private JTextField cg_serviceImplText;
    private JLabel cg_controllerLab;
    private JLabel cg_mapperLab;
    private JTextField cg_controllerText;
    private JTextField cg_mapperText;
    private JTextField cg_addText;
    private JTextField cg_updateText;
    private JTextField cd_deleteText;
    private JTextField cg_getText;
    private JTextField cg_batchDelText;
    private JTextField cg_batchUpdateText;
    private JTextField cg_listText;
    private JLabel cg_addLab;
    private JPanel cg_panel;
    private JLabel cg_batchDelLab;
    private JLabel cg_listLab;
    private JLabel cg_updateLab;
    private JLabel cg_getLab;
    private JLabel cg_batchUpdateLab;
    private JLabel cd_deleteLab;
    private JRadioButton radioButton1;
    private JLabel cg_batchAddLab;
    private JTextField cg_batchAddText;
    private JButton cg_okButton;
    private JButton genByPojoButton;
    private JButton genByDbButton;
    private JFrame chooseFrame;
    private Font font;

    static String packNameText;
    static String authorText;
    static String cgEntityText;
    static String cgDaoText;
    static String cgServiceText;
    static String cgServiceImplText;
    static String cgControllerText;
    static String cgMapperText;
    static String cgAddText;
    static String cgUpdateText;
    static String cdDeleteText;
    static String cgGetText;
    static String cgBatchDelText;
    static String cgBatchUpdateText;
    static String cgListText;
    static String cgBatchAddText;

    //拿到Coder引擎
    CoderEngine engine = new CoderEngine();
    static CommonDBService dbService = null;

    public GuiMain() {
        //mainPanel.setSize(800,800);

        codeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (codeButton.isEnabled()) {
                    //弹出一个选择界面
                    //1.该选择界面为 -- 根据用户输入实体生成(可勾选 one.是否生成数据库表 two.是否生成dao,db.service,controller)
                    chooseFrame = new JFrame("选择生成方式");
                    chooseFrame.setSize(300, 300);
                    //chooseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //chooseFrame.pack();
                    chooseFrame.setVisible(true);
                    chooseFrame.setLocationRelativeTo(null);
                    //设置布局
                    chooseFrame.setLayout(new FlowLayout());
                    genByPojoButton = new JButton("根据实体生成");
                    genByDbButton = new JButton("根据数据库表生成");
                    //genByDbButton.setPreferredSize(new Dimension(100, 100));

                    //添加监听事件
                    addListenerToGenButton();
                    //设置到面板
                    chooseFrame.add(genByPojoButton);
                    chooseFrame.add(genByDbButton);
                    //设置字体
                    font = $$$getFont$$$(null, -1, 24, genByPojoButton.getFont());
                    if (font != null) {
                        genByPojoButton.setFont(font);
                        genByDbButton.setFont(font);
                    }

                }
            }
        });

        //连接测试 鼠标事件
        testCon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //获取类类型
                String dbType = db_type.getSelectedItem().toString();
                //获得文本框的值
                String localIpText = db_localIpText.getText();
                String dbNameText = db_DbNameText.getText();
                String dbPostText = db_postText.getText();
                String dbUserNameText = db_userNameText.getText();
                String dbPasswordText = db_passwordText.getText();
                // 拿到service
                dbService = engine.getCommonDBService(dbType.trim());
                //连接数据库
                boolean isSuccess = dbService.checkConnection("localhost", "music", "3306", "root", "root");
                if (isSuccess) {
                    JOptionPane.showMessageDialog(null, "连接成功!!", "提示", JOptionPane.QUESTION_MESSAGE);
                }
            }
        });
    }

    private void addListenerToGenButton() {
        // 根据数据库表生成代码按钮点击事件
        genByDbButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //父窗口不可见
                chooseFrame.setVisible(false);

                codeButton.setEnabled(false);// 设置不可点击

                if (!ConInfo.isConSuccess()) {
                    JOptionPane.showMessageDialog(null, "数据库未连接成功!", "错误", JOptionPane.INFORMATION_MESSAGE);
                    codeButton.setEnabled(true);// 设置可点击
                    return;
                }

                //包名
                String packNameText = cg_packNameText.getText();
                if (Strings.isNullOrEmpty(packNameText)) {
                    JOptionPane.showMessageDialog(null, "命名规范(必填项)未填!", "错误", JOptionPane.INFORMATION_MESSAGE);
                    codeButton.setEnabled(true);// 设置可点击
                    return;
                }
                ConfirmInfoFrame confirmInfoFrame = ConfirmInfoFrame.getInstance(GuiMain.this);

                ThreadPoolUtils.execute(new Runnable() {
                    @Override
                    public void run() {
                        while (!confirmInfoFrame.isShutDown()) {

                        }
                        codeButton.setEnabled(true);
                    }
                });
            }

        });


        genByPojoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                chooseFrame.setVisible(false);
                codeButton.setEnabled(false);// 设置不可点击
                ProduceEntityFrame produceEntityFrame = new ProduceEntityFrame();

                ThreadPoolUtils.execute(new Runnable() {

                    @Override
                    public void run() {
                        while (!produceEntityFrame.isShutDown()) {

                        }

                        codeButton.setEnabled(true);

                    }
                });

            }

            private void addRows(JFrame frame) {
                JLabel typeLabel = new JLabel("类型:");
                JLabel defLabel = new JLabel("含义:");
                JComboBox box = new JComboBox();
                //属性
                JLabel fieldLabel = new JLabel("属性:");
                //属性文本
                JTextField field = new JTextField();
                //注释文本
                JTextField comField = new JTextField();
                //字体
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
                //下拉框
                final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
                defaultComboBoxModel1.addElement("Integer");
                defaultComboBoxModel1.addElement("Long");
                defaultComboBoxModel1.addElement("Double");
                defaultComboBoxModel1.addElement("Float");
                defaultComboBoxModel1.addElement("String");
                defaultComboBoxModel1.addElement("Date");
                defaultComboBoxModel1.addElement("List");
                defaultComboBoxModel1.addElement("Set");
                box.setModel(defaultComboBoxModel1);
                //添加至窗口
                frame.add(typeLabel);
                frame.add(box);
                frame.add(fieldLabel);
                frame.add(field);
                frame.add(defLabel);
                frame.add(comField);
            }
        });
    }

    public FileFromDbData setBuildFileDateInit(TableInfo info, String tableName, String path) {

        FileFromDbData fileFromDbData = new FileFromDbData();

        //包名
        packNameText = cg_packNameText.getText();
        //作者
        authorText = cg_authorText.getText();
        // entity后缀
        cgEntityText = cg_entityText.getText();
        // dao
        cgDaoText = cg_daoText.getText();
        // service
        cgServiceText = cg_serviceText.getText();
        // serviceImpl
        cgServiceImplText = cg_serviceImplText.getText();
        // controller
        cgControllerText = cg_controllerText.getText();
        // mapper
        cgMapperText = cg_mapperText.getText();
        // save
        cgAddText = cg_addText.getText();
        // update
        cgUpdateText = cg_updateText.getText();
        //del one
        cdDeleteText = cd_deleteText.getText();
        // get one
        cgGetText = cg_getText.getText();
        // del batch
        cgBatchDelText = cg_batchDelText.getText();
        // update batch
        cgBatchUpdateText = cg_batchUpdateText.getText();
        // list
        cgListText = cg_listText.getText();
        // add batch
        cgBatchAddText = cg_batchAddText.getText();

        //设置类基本信息 (命名规范等)

        String convertTableName = info.convertTableName();

        ClassBasicData basicData = new ClassBasicData();
        basicData.setClassName(convertTableName + cgEntityText);
        basicData.setPackageName(packNameText);
        basicData.setDate(new Date());
        basicData.setAuthor(authorText);
        basicData.setDescription(info.getNote());
        //设置dao
        MethodBasicData dao = new MethodBasicData();
        dao.setPackageName(packNameText);
        dao.setClassName(convertTableName + cgDaoText);
        dao.setDescription(convertTableName + " dao层");
        dao.setAuthor(authorText);
        dao.setAddMethod(cgAddText);
        dao.setUpdateMethod(cgUpdateText);
        dao.setDelMethod(cdDeleteText);
        dao.setFindByIdMethod(cgGetText);
        dao.setDelBatchMethod(cgBatchDelText);
        dao.setUpdateBatchMethod(cgBatchUpdateText);
        dao.setFindAllMethod(cgListText);
        dao.setAddBatchMethod(cgBatchAddText);
        //Mapper
        MethodBasicData mapper = new MethodBasicData();
        mapper.setPackageName(packNameText);
        mapper.setClassName(convertTableName + cgMapperText);
        mapper.setAuthor(authorText);
        //Service
        MethodBasicData service = new MethodBasicData();
        service.setPackageName(packNameText);
        service.setClassName(convertTableName + cgServiceText);
        service.setDescription(convertTableName + " service层");
        service.setAuthor(authorText);
        service.setAddMethod(cgAddText);
        service.setUpdateMethod(cgUpdateText);
        service.setDelMethod(cdDeleteText);
        service.setFindByIdMethod(cgGetText);
        service.setDelBatchMethod(cgBatchDelText);
        service.setUpdateBatchMethod(cgBatchUpdateText);
        service.setFindAllMethod(cgListText);
        service.setAddBatchMethod(cgBatchAddText);
        //ServiceImpl
        MethodBasicData serviceImpl = new MethodBasicData();
        serviceImpl.setPackageName(packNameText);
        serviceImpl.setClassName(convertTableName + cgServiceImplText);
        serviceImpl.setDescription(convertTableName + " serviceImpl层");
        serviceImpl.setAuthor(authorText);
        // Controller
        MethodBasicData controller = new MethodBasicData();
        controller.setPackageName(packNameText);
        controller.setClassName(convertTableName + cgControllerText);
        controller.setDescription(convertTableName + " controller层");
        controller.setAuthor(authorText);
        controller.setAddMethod(cgAddText);
        controller.setUpdateMethod(cgUpdateText);
        controller.setDelMethod(cdDeleteText);
        controller.setFindByIdMethod(cgGetText);
        controller.setDelBatchMethod(cgBatchDelText);
        controller.setUpdateBatchMethod(cgBatchUpdateText);
        controller.setFindAllMethod(cgListText);
        controller.setAddBatchMethod(cgBatchAddText);

        fileFromDbData.setTableInfo(info);
        fileFromDbData.setClassInfo(basicData);
        fileFromDbData.setDao(dao);
        fileFromDbData.setMapper(mapper);
        fileFromDbData.setServiceImpl(serviceImpl);
        fileFromDbData.setService(service);
        fileFromDbData.setControllor(controller);
        //TODO 需要改为配置文件解析
        fileFromDbData.setPackagePre("com.lm");
        return fileFromDbData;
    }

    /**
     * 根据布局对象和窗口名称生成窗口对象
     *
     * @param manager
     */
    private JFrame newInstanceJFrame(String name, LayoutManager manager, Integer height, Integer width) {
        JFrame frame = new JFrame(name);
        frame.setLayout(manager);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        return frame;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("主界面");
        GuiMain guiMain = new GuiMain();
        frame.setContentPane(guiMain.mainPanel);
        guiMain.setFrameOnView(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setFrameOnView(JFrame frame) {
        int windowWidth = frame.getWidth(); // 获得窗口宽
        int windowHeight = frame.getHeight(); // 获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width; // 获取屏幕的宽
        int screenHeight = screenSize.height; // 获取屏幕的高
        frame.setLocation(screenWidth / 5 - windowWidth / 3, screenHeight / 5 - windowHeight / 3);// 设置窗口居中显示
    }

    public CommonDBService getDbService() {
        return this.dbService;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        tabbedPane1 = new JTabbedPane();
        Font tabbedPane1Font = this.$$$getFont$$$(null, -1, 18, tabbedPane1.getFont());
        if (tabbedPane1Font != null) tabbedPane1.setFont(tabbedPane1Font);
        mainPanel.add(tabbedPane1, BorderLayout.NORTH);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.putClientProperty("html.disable", Boolean.FALSE);
        tabbedPane1.addTab("主界面", panel1);
        codeButton = new JButton();
        codeButton.setEnabled(true);
        Font codeButtonFont = this.$$$getFont$$$(null, -1, 28, codeButton.getFont());
        if (codeButtonFont != null) codeButton.setFont(codeButtonFont);
        codeButton.setHorizontalAlignment(0);
        codeButton.setText("生成代码");
        panel1.add(codeButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        Font scrollPane1Font = this.$$$getFont$$$(null, -1, 18, scrollPane1.getFont());
        if (scrollPane1Font != null) scrollPane1.setFont(scrollPane1Font);
        scrollPane1.setHorizontalScrollBarPolicy(31);
        scrollPane1.setVerticalScrollBarPolicy(22);
        panel1.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setBorder(BorderFactory.createTitledBorder(null, "系统消息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, -1, 20, scrollPane1.getFont())));
        logArea = new JTextArea();
        logArea.setColumns(100);
        Font logAreaFont = this.$$$getFont$$$(null, -1, 16, logArea.getFont());
        if (logAreaFont != null) logArea.setFont(logAreaFont);
        logArea.setRows(25);
        logArea.setText("");
        scrollPane1.setViewportView(logArea);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        tabbedPane1.addTab("命名规范", panel2);
        cg_panel = new JPanel();
        cg_panel.setLayout(new GridLayoutManager(9, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(cg_panel, BorderLayout.NORTH);
        cg_packNameLab = new JLabel();
        Font cg_packNameLabFont = this.$$$getFont$$$(null, -1, 24, cg_packNameLab.getFont());
        if (cg_packNameLabFont != null) cg_packNameLab.setFont(cg_packNameLabFont);
        cg_packNameLab.setText("包名(必填)");
        cg_panel.add(cg_packNameLab, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        cg_authorLab = new JLabel();
        Font cg_authorLabFont = this.$$$getFont$$$(null, -1, 24, cg_authorLab.getFont());
        if (cg_authorLabFont != null) cg_authorLab.setFont(cg_authorLabFont);
        cg_authorLab.setText("作者");
        cg_panel.add(cg_authorLab, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        cg_entityLab = new JLabel();
        Font cg_entityLabFont = this.$$$getFont$$$(null, -1, 24, cg_entityLab.getFont());
        if (cg_entityLabFont != null) cg_entityLab.setFont(cg_entityLabFont);
        cg_entityLab.setText("Entity后缀");
        cg_panel.add(cg_entityLab, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cg_packNameText = new JTextField();
        Font cg_packNameTextFont = this.$$$getFont$$$(null, -1, 24, cg_packNameText.getFont());
        if (cg_packNameTextFont != null) cg_packNameText.setFont(cg_packNameTextFont);
        cg_packNameText.setText("");
        cg_panel.add(cg_packNameText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 30), null, 0, false));
        cg_authorText = new JTextField();
        Font cg_authorTextFont = this.$$$getFont$$$(null, -1, 24, cg_authorText.getFont());
        if (cg_authorTextFont != null) cg_authorText.setFont(cg_authorTextFont);
        cg_panel.add(cg_authorText, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 30), null, 0, false));
        cg_entityText = new JTextField();
        Font cg_entityTextFont = this.$$$getFont$$$(null, -1, 24, cg_entityText.getFont());
        if (cg_entityTextFont != null) cg_entityText.setFont(cg_entityTextFont);
        cg_entityText.setText("Entity");
        cg_panel.add(cg_entityText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_daoLab = new JLabel();
        Font cg_daoLabFont = this.$$$getFont$$$(null, -1, 24, cg_daoLab.getFont());
        if (cg_daoLabFont != null) cg_daoLab.setFont(cg_daoLabFont);
        cg_daoLab.setText("DAO后缀");
        cg_panel.add(cg_daoLab, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cg_daoText = new JTextField();
        Font cg_daoTextFont = this.$$$getFont$$$(null, -1, 24, cg_daoText.getFont());
        if (cg_daoTextFont != null) cg_daoText.setFont(cg_daoTextFont);
        cg_daoText.setText("Dao");
        cg_panel.add(cg_daoText, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_serviceLab = new JLabel();
        Font cg_serviceLabFont = this.$$$getFont$$$(null, -1, 24, cg_serviceLab.getFont());
        if (cg_serviceLabFont != null) cg_serviceLab.setFont(cg_serviceLabFont);
        cg_serviceLab.setText("Service后缀");
        cg_panel.add(cg_serviceLab, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cg_serviceText = new JTextField();
        Font cg_serviceTextFont = this.$$$getFont$$$(null, -1, 24, cg_serviceText.getFont());
        if (cg_serviceTextFont != null) cg_serviceText.setFont(cg_serviceTextFont);
        cg_serviceText.setText("Service");
        cg_panel.add(cg_serviceText, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_serviceImplLab = new JLabel();
        Font cg_serviceImplLabFont = this.$$$getFont$$$(null, -1, 24, cg_serviceImplLab.getFont());
        if (cg_serviceImplLabFont != null) cg_serviceImplLab.setFont(cg_serviceImplLabFont);
        cg_serviceImplLab.setText("ServiceImpl后缀");
        cg_panel.add(cg_serviceImplLab, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cg_serviceImplText = new JTextField();
        Font cg_serviceImplTextFont = this.$$$getFont$$$(null, -1, 24, cg_serviceImplText.getFont());
        if (cg_serviceImplTextFont != null) cg_serviceImplText.setFont(cg_serviceImplTextFont);
        cg_serviceImplText.setText("ServiceImpl");
        cg_panel.add(cg_serviceImplText, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_controllerLab = new JLabel();
        Font cg_controllerLabFont = this.$$$getFont$$$(null, -1, 24, cg_controllerLab.getFont());
        if (cg_controllerLabFont != null) cg_controllerLab.setFont(cg_controllerLabFont);
        cg_controllerLab.setText("Controller后缀");
        cg_panel.add(cg_controllerLab, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cg_mapperLab = new JLabel();
        Font cg_mapperLabFont = this.$$$getFont$$$(null, -1, 24, cg_mapperLab.getFont());
        if (cg_mapperLabFont != null) cg_mapperLab.setFont(cg_mapperLabFont);
        cg_mapperLab.setText("Mapper(xml)后缀");
        cg_panel.add(cg_mapperLab, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cg_controllerText = new JTextField();
        Font cg_controllerTextFont = this.$$$getFont$$$(null, -1, 24, cg_controllerText.getFont());
        if (cg_controllerTextFont != null) cg_controllerText.setFont(cg_controllerTextFont);
        cg_controllerText.setText("Controller");
        cg_panel.add(cg_controllerText, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_mapperText = new JTextField();
        Font cg_mapperTextFont = this.$$$getFont$$$(null, -1, 24, cg_mapperText.getFont());
        if (cg_mapperTextFont != null) cg_mapperText.setFont(cg_mapperTextFont);
        cg_mapperText.setText("Mapper");
        cg_panel.add(cg_mapperText, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_addText = new JTextField();
        Font cg_addTextFont = this.$$$getFont$$$(null, -1, 24, cg_addText.getFont());
        if (cg_addTextFont != null) cg_addText.setFont(cg_addTextFont);
        cg_addText.setText("save");
        cg_panel.add(cg_addText, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_updateText = new JTextField();
        Font cg_updateTextFont = this.$$$getFont$$$(null, -1, 24, cg_updateText.getFont());
        if (cg_updateTextFont != null) cg_updateText.setFont(cg_updateTextFont);
        cg_updateText.setText("update");
        cg_panel.add(cg_updateText, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cd_deleteText = new JTextField();
        Font cd_deleteTextFont = this.$$$getFont$$$(null, -1, 24, cd_deleteText.getFont());
        if (cd_deleteTextFont != null) cd_deleteText.setFont(cd_deleteTextFont);
        cd_deleteText.setText("delById");
        cg_panel.add(cd_deleteText, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_getText = new JTextField();
        Font cg_getTextFont = this.$$$getFont$$$(null, -1, 24, cg_getText.getFont());
        if (cg_getTextFont != null) cg_getText.setFont(cg_getTextFont);
        cg_getText.setText("loadById");
        cg_panel.add(cg_getText, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_batchDelText = new JTextField();
        Font cg_batchDelTextFont = this.$$$getFont$$$(null, -1, 24, cg_batchDelText.getFont());
        if (cg_batchDelTextFont != null) cg_batchDelText.setFont(cg_batchDelTextFont);
        cg_batchDelText.setText("delBatch");
        cg_panel.add(cg_batchDelText, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_batchUpdateText = new JTextField();
        Font cg_batchUpdateTextFont = this.$$$getFont$$$(null, -1, 24, cg_batchUpdateText.getFont());
        if (cg_batchUpdateTextFont != null) cg_batchUpdateText.setFont(cg_batchUpdateTextFont);
        cg_batchUpdateText.setText("updateBatch");
        cg_panel.add(cg_batchUpdateText, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_listText = new JTextField();
        Font cg_listTextFont = this.$$$getFont$$$(null, -1, 24, cg_listText.getFont());
        if (cg_listTextFont != null) cg_listText.setFont(cg_listTextFont);
        cg_listText.setText("list");
        cg_panel.add(cg_listText, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField1 = new JTextField();
        Font textField1Font = this.$$$getFont$$$(null, -1, 24, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        cg_panel.add(textField1, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField2 = new JTextField();
        Font textField2Font = this.$$$getFont$$$(null, -1, 24, textField2.getFont());
        if (textField2Font != null) textField2.setFont(textField2Font);
        cg_panel.add(textField2, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_addLab = new JLabel();
        Font cg_addLabFont = this.$$$getFont$$$(null, -1, 24, cg_addLab.getFont());
        if (cg_addLabFont != null) cg_addLab.setFont(cg_addLabFont);
        cg_addLab.setText("新增方法名");
        cg_panel.add(cg_addLab, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        cd_deleteLab = new JLabel();
        Font cd_deleteLabFont = this.$$$getFont$$$(null, -1, 24, cd_deleteLab.getFont());
        if (cd_deleteLabFont != null) cd_deleteLab.setFont(cd_deleteLabFont);
        cd_deleteLab.setText("删除方法名");
        cg_panel.add(cd_deleteLab, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        cg_batchDelLab = new JLabel();
        Font cg_batchDelLabFont = this.$$$getFont$$$(null, -1, 24, cg_batchDelLab.getFont());
        if (cg_batchDelLabFont != null) cg_batchDelLab.setFont(cg_batchDelLabFont);
        cg_batchDelLab.setText("批量删除方法名");
        cg_panel.add(cg_batchDelLab, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        cg_listLab = new JLabel();
        Font cg_listLabFont = this.$$$getFont$$$(null, -1, 24, cg_listLab.getFont());
        if (cg_listLabFont != null) cg_listLab.setFont(cg_listLabFont);
        cg_listLab.setText("查询列表方法名");
        cg_panel.add(cg_listLab, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 24, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("备用");
        cg_panel.add(label1, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        cg_updateLab = new JLabel();
        Font cg_updateLabFont = this.$$$getFont$$$(null, -1, 24, cg_updateLab.getFont());
        if (cg_updateLabFont != null) cg_updateLab.setFont(cg_updateLabFont);
        cg_updateLab.setText("修改方法名");
        cg_panel.add(cg_updateLab, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        cg_getLab = new JLabel();
        Font cg_getLabFont = this.$$$getFont$$$(null, -1, 24, cg_getLab.getFont());
        if (cg_getLabFont != null) cg_getLab.setFont(cg_getLabFont);
        cg_getLab.setText("查询方法名(根据ID)");
        cg_panel.add(cg_getLab, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        cg_batchUpdateLab = new JLabel();
        Font cg_batchUpdateLabFont = this.$$$getFont$$$(null, -1, 24, cg_batchUpdateLab.getFont());
        if (cg_batchUpdateLabFont != null) cg_batchUpdateLab.setFont(cg_batchUpdateLabFont);
        cg_batchUpdateLab.setText("批量更新方法名");
        cg_panel.add(cg_batchUpdateLab, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        cg_batchAddLab = new JLabel();
        Font cg_batchAddLabFont = this.$$$getFont$$$(null, -1, 24, cg_batchAddLab.getFont());
        if (cg_batchAddLabFont != null) cg_batchAddLab.setFont(cg_batchAddLabFont);
        cg_batchAddLab.setText("批量新增");
        cg_panel.add(cg_batchAddLab, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 24, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("备用");
        cg_panel.add(label2, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(39, 30), null, 0, false));
        cg_batchAddText = new JTextField();
        Font cg_batchAddTextFont = this.$$$getFont$$$(null, -1, 24, cg_batchAddText.getFont());
        if (cg_batchAddTextFont != null) cg_batchAddText.setFont(cg_batchAddTextFont);
        cg_batchAddText.setText("addBatch");
        cg_panel.add(cg_batchAddText, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cg_okButton = new JButton();
        Font cg_okButtonFont = this.$$$getFont$$$(null, -1, 36, cg_okButton.getFont());
        if (cg_okButtonFont != null) cg_okButton.setFont(cg_okButtonFont);
        cg_okButton.setHorizontalAlignment(0);
        cg_okButton.setHorizontalTextPosition(0);
        cg_okButton.setText("确定");
        panel2.add(cg_okButton, BorderLayout.SOUTH);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        tabbedPane1.addTab("数据库配置", panel3);
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, -1, 22, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("");
        panel3.add(label3, BorderLayout.WEST);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        Font panel4Font = this.$$$getFont$$$("Franklin Gothic Heavy", -1, -1, panel4.getFont());
        if (panel4Font != null) panel4.setFont(panel4Font);
        panel3.add(panel4, BorderLayout.NORTH);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$(null, -1, 22, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("选择数据库");
        panel5.add(label4);
        db_type = new JComboBox();
        Font db_typeFont = this.$$$getFont$$$(null, -1, 22, db_type.getFont());
        if (db_typeFont != null) db_type.setFont(db_typeFont);
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Mysql");
        defaultComboBoxModel1.addElement("Oracle");
        db_type.setModel(defaultComboBoxModel1);
        panel5.add(db_type);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        db_localIpLab = new JLabel();
        Font db_localIpLabFont = this.$$$getFont$$$(null, -1, 20, db_localIpLab.getFont());
        if (db_localIpLabFont != null) db_localIpLab.setFont(db_localIpLabFont);
        db_localIpLab.setText("本机IP地址");
        panel6.add(db_localIpLab, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        db_localIpText = new JTextField();
        Font db_localIpTextFont = this.$$$getFont$$$(null, -1, 20, db_localIpText.getFont());
        if (db_localIpTextFont != null) db_localIpText.setFont(db_localIpTextFont);
        db_localIpText.setText("");
        panel6.add(db_localIpText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        db_DbNameLab = new JLabel();
        Font db_DbNameLabFont = this.$$$getFont$$$(null, -1, 20, db_DbNameLab.getFont());
        if (db_DbNameLabFont != null) db_DbNameLab.setFont(db_DbNameLabFont);
        db_DbNameLab.setText("数据库名称");
        panel6.add(db_DbNameLab, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        db_postLab = new JLabel();
        Font db_postLabFont = this.$$$getFont$$$(null, -1, 20, db_postLab.getFont());
        if (db_postLabFont != null) db_postLab.setFont(db_postLabFont);
        db_postLab.setText("端口号");
        panel6.add(db_postLab, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        db_userNameLab = new JLabel();
        Font db_userNameLabFont = this.$$$getFont$$$(null, -1, 20, db_userNameLab.getFont());
        if (db_userNameLabFont != null) db_userNameLab.setFont(db_userNameLabFont);
        db_userNameLab.setText("用户名");
        panel6.add(db_userNameLab, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        db_passwordLab = new JLabel();
        Font db_passwordLabFont = this.$$$getFont$$$(null, -1, 20, db_passwordLab.getFont());
        if (db_passwordLabFont != null) db_passwordLab.setFont(db_passwordLabFont);
        db_passwordLab.setText("密码");
        panel6.add(db_passwordLab, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        db_DbNameText = new JTextField();
        Font db_DbNameTextFont = this.$$$getFont$$$(null, -1, 20, db_DbNameText.getFont());
        if (db_DbNameTextFont != null) db_DbNameText.setFont(db_DbNameTextFont);
        db_DbNameText.setText("");
        panel6.add(db_DbNameText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        db_postText = new JTextField();
        Font db_postTextFont = this.$$$getFont$$$(null, -1, 20, db_postText.getFont());
        if (db_postTextFont != null) db_postText.setFont(db_postTextFont);
        db_postText.setText("");
        panel6.add(db_postText, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        db_userNameText = new JTextField();
        Font db_userNameTextFont = this.$$$getFont$$$(null, -1, 20, db_userNameText.getFont());
        if (db_userNameTextFont != null) db_userNameText.setFont(db_userNameTextFont);
        db_userNameText.setText("");
        panel6.add(db_userNameText, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        db_passwordText = new JTextField();
        Font db_passwordTextFont = this.$$$getFont$$$(null, -1, 20, db_passwordText.getFont());
        if (db_passwordTextFont != null) db_passwordText.setFont(db_passwordTextFont);
        db_passwordText.setText("");
        panel6.add(db_passwordText, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        testCon = new JButton();
        Font testConFont = this.$$$getFont$$$(null, -1, 20, testCon.getFont());
        if (testConFont != null) testCon.setFont(testConFont);
        testCon.setForeground(new Color(-14936035));
        testCon.setText("连接测试");
        panel4.add(testCon, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Untitled", panel7);
        radioButton1 = new JRadioButton();
        radioButton1.setText("RadioButton");
        panel7.add(radioButton1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel7.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel7.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
