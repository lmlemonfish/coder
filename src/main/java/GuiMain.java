import javax.swing.*;

public class GuiMain {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JTextArea logArea;

    public GuiMain() {
        //mainPanel.setSize(800,800);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GuiMain");
        frame.setContentPane(new GuiMain().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
