package visual.gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {

    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        
        mainFrame.setTitle("Visual GUI");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info.getClassName());
        }
    }
}
