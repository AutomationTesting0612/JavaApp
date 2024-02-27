import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class HomeFrame extends JFrame {

    DbConnection dbConnection = new DbConnection();
    String jsonAddress = null;

    private DefaultTableModel model;

    HomeFrame() {

        setTitle("Employee Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize and add components (e.g., panels, buttons)
        initUI();

        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Add a panel with form fields
        add(createInputPanel(), BorderLayout.NORTH);

        // Add a panel or table to display employee data
        add(createDisplayPanel(), BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        JTextField idField = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Full Time", "Part Time"});
        JTextField hourField = new JTextField(10);

        // Address fields
        JTextField streetField = new JTextField(10);
        JTextField zipField = new JTextField(10);
        JTextField stateField = new JTextField(10);
        JTextField countryField = new JTextField(10);

        JButton addButton = new JButton("Add Employee");

        addButton.addActionListener(e -> {
            try {
                int empId = Integer.parseInt(idField.getText());
                double hours = Double.parseDouble(hourField.getText());
                Address address = new Address(streetField.getText(), zipField.getText(),
                        stateField.getText(), countryField.getText());

                // Assuming Employee constructors are appropriately defined
                Employee empp = "Part Time".equals(typeComboBox.getSelectedItem()) ?
                        new PartTimeEmployee(empId, nameField.getText(), typeComboBox.getName(), hours, address) :
                        new FullTimeEmployee(empId, nameField.getText(), typeComboBox.getName(), hours, address);

                jsonAddress = new Gson().toJson(address);

                try (Connection connection = dbConnection.getConnection()) {
                    //  String createQuery="CREATE TABLE employee (id INT NOT NULL, name VARCHAR(50) NOT NULL)";
                    Statement statement = connection.createStatement();
                    //  statement.execute(createQuery);
                    String query = "INSERT INTO employee (id, NAME,TYPEOFEMPLOYEE,SALARY,ADDRESS)"
                            + "values('" + empp.getId() + "','" + empp.getName() + "','" + empp.getTypeOfEmployee() + "','" + empp.calculateSalary() + "','" + jsonAddress
                            + "')";

                    statement.executeUpdate(query);
                    System.out.println("Successfully connected to the H2 database.");

                    String selectQuery = "Select * from employee";
                    //  ResultSet result= statement.executeQuery(selectQuery);
                    //System.out.println(result);
                    // Add employee to table
                    addEmployeeToTable(empp);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Type:"));
        panel.add(typeComboBox);
        panel.add(new JLabel("Hour:"));
        panel.add(hourField);

        panel.add(new JLabel("Street:"));
        panel.add(streetField);
        panel.add(new JLabel("Zip Code:"));
        panel.add(zipField);
        panel.add(new JLabel("State:"));
        panel.add(stateField);
        panel.add(new JLabel("Country:"));
        panel.add(countryField);

        panel.add(addButton);

        return panel;
    }

    private void handleAddEmployee(String id, String name, String type, Double hour, Address addressStr) {
        try {
            int empId = Integer.parseInt(id);

            // Assuming address is a simple string for this example
            // In a real application, you might want to parse it into a proper Address object

            Employee empp;
            if ("Part Time".equals(type)) {
                empp = new PartTimeEmployee(empId, name, type, hour, addressStr); // Modify as per your constructor
            } else {
                empp = new FullTimeEmployee(empId, name, type, hour, addressStr); // Modify as per your constructor
            }

            jsonAddress = new Gson().toJson(addressStr);

            try (Connection connection = dbConnection.getConnection()) {
                //  String createQuery="CREATE TABLE employee (id INT NOT NULL, name VARCHAR(50) NOT NULL)";
                Statement statement = connection.createStatement();
                //  statement.execute(createQuery);
                String query = "INSERT INTO employee (id, NAME,TYPEOFEMPLOYEE,SALARY,ADDRESS)"
                        + "values('" + empp.getId() + "','" + empp.getName() + "','" + empp.getTypeOfEmployee() + "','" + empp.calculateSalary() + "','" + jsonAddress
                        + "')";

                statement.executeUpdate(query);
                System.out.println("Successfully connected to the H2 database.");

                String selectQuery = "Select * from employee";
                //  ResultSet result= statement.executeQuery(selectQuery);
                //System.out.println(result);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
//        JTable employeeTable = new JTable(); // Replace with actual table model
        String[] columnNames = {"ID", "Name", "Type", "Hours", "Address"};

        // Initialize Table Model
        model = new DefaultTableModel(columnNames, 0);

        // Initialize JTable with the model
        JTable employeeTable = new JTable(model);

        panel.add(new JScrollPane(employeeTable), BorderLayout.CENTER);
        return panel;
    }
    public void addEmployeeToTable(Employee employee) {
        Gson gson = new Gson();
        String addressJson = gson.toJson(employee.getAddress());

        model.addRow(new Object[]{
                employee.getId(),
                employee.getName(),
                employee.getTypeOfEmployee(),
                employee.getSalary(), // Assuming you have a getSalary or similar method
                addressJson // Or format the address in a more readable form
        });
    }

}
