package edu.ajan.view.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {

    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;

    public static void main(String[] args) throws IOException {
        JFrame main = new JFrame();

        main.setTitle("Visual GUI");
        main.setSize(WIDTH, HEIGHT);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        main.setVisible(true);

        BufferedImage bImg = ImageIO.read(Main.class.getResourceAsStream("/edu/ajan/assets/logo.png"));
        Image imgScaled = bImg.getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        main.setIconImage(imgScaled);

        // for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        // System.out.println(info.getName());
        // }
    }
}
