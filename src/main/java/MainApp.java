import javax.swing.*;
import java.awt.*;

public class MainApp {

    public static void main(String[] args) {
//        BackGroundImage bgPanel = new BackGroundImage("C:\\Users\\pc\\Downloads\\Tech-learner.jpg");
        LoginFrame frame = new LoginFrame();
//        frame.setBackgroundImage(bgPanel);
        frame.setTitle("Employee Management Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBackground(Color.BLACK);

    }
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Swing Application");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.add(new JLabel("Hello, World"));
//            frame.pack();
//            frame.setVisible(true);
//        });
//    }
}
