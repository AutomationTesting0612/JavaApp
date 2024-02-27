import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame implements ActionListener {

    JButton signUpButton = new JButton("SIGN UP");

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");

    DbConnection dbConnection = new DbConnection();

    LoginFrame() {
        this.getContentPane().setBackground(Color.GRAY);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
        signUpButton.setBounds(200, 350, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(loginButton);
        container.add(resetButton);
        container.add(signUpButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        signUpButton.addActionListener(this);
    }

    public void setBackgroundImage(BackGroundImage bgPanel) {
        this.setContentPane(bgPanel); // Set the background panel as content pane
        bgPanel.setLayout(new BorderLayout()); // Use BorderLayout (or another as needed)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String username = userTextField.getText();
            String password = new String(passwordField.getPassword());

            // Add code to validate user against the database
            try (Connection connection = dbConnection.getConnection()) {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                ps.setString(1, username);
                ps.setString(2, password); // Again, storing plain text passwords is not secure
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful");
                    HomeFrame homePage = new HomeFrame();
                    homePage.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error during login");
            }
        }
        // Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == signUpButton) {
            SignUpFrame signUpFrame = new SignUpFrame();
            signUpFrame.setTitle("Sign Up Form");
            signUpFrame.setVisible(true);
            signUpFrame.setBounds(10, 10, 370, 600);
            signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            signUpFrame.setResizable(false);
        }
    }
}
