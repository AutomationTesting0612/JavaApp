import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel emailLabel = new JLabel("EMAIL");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField emailTextField = new JTextField();
    JButton signUpButton = new JButton("SIGN UP");

    DbConnection dbConnection = new DbConnection();

    SignUpFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 100, 100, 30);
        passwordLabel.setBounds(50, 150, 100, 30);
        emailLabel.setBounds(50, 200, 100, 30);
        userTextField.setBounds(150, 100, 150, 30);
        passwordField.setBounds(150, 150, 150, 30);
        emailTextField.setBounds(150, 200, 150, 30);
        signUpButton.setBounds(150, 250, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(emailLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(emailTextField);
        container.add(signUpButton);
    }

    public void addActionEvent() {
        signUpButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Add action for sign up button (e.g., form validation, save data)
        if (e.getSource() == signUpButton) {
            String username = userTextField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailTextField.getText();

            // Add code to insert new user into the database
            try (Connection connection = dbConnection.getConnection()) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO users (username, password, email) VALUES (?, ?, ?)");
                ps.setString(1, username);
                ps.setString(2, password); // Note: Storing plain text passwords is a bad practice
                ps.setString(3, email);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Sign Up Successful");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error during sign up");
            }
        }
    }
}
