import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GUI gui = new GUI();
        frame.add(gui, BorderLayout.CENTER);
        frame.add(new JLabel("Click to set the points"), BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.setVisible(true);
    }
}
