import javax.swing.*;

public class GuiTest {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JTextArea logArea;

    public GuiTest() {
        //mainPanel.setSize(800,800);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GuiTest");
        frame.setContentPane(new GuiTest().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
