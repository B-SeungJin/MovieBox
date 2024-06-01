package manage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DataInputForm extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn;
    private String tableName;
    private JPanel formPanel = new JPanel(new GridLayout(0, 2));

    public DataInputForm(Connection conn, String tableName) {
        this.conn = conn;
        this.tableName = tableName;

        setTitle("Data Input for " + tableName);
        setSize(800, 600);
        setLayout(new BorderLayout());

        initializeFormFields(tableName);
        add(formPanel, BorderLayout.CENTER);

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");
        btnSave.addActionListener(this::saveData);
        btnCancel.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFormFields(String tableName) {
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getColumns(null, null, tableName, null);
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                JTextField textField = new JTextField(20);
                formPanel.add(new JLabel(columnName));
                formPanel.add(textField);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveData(ActionEvent e) {
        Component[] components = formPanel.getComponents();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        StringBuilder updateSet = new StringBuilder();
        ArrayList<String> valueList = new ArrayList<>();

        for (int i = 0; i < components.length; i += 2) {
            JLabel label = (JLabel) components[i];
            JTextField textField = (JTextField) components[i + 1];
            if (!textField.getText().isEmpty()) {
                if (columns.length() > 0) {
                    columns.append(", ");
                    values.append(", ");
                    updateSet.append(", ");
                }
                columns.append(label.getText());
                values.append("?");
                updateSet.append(label.getText()).append("=?");
                valueList.add(textField.getText());
            }
        }

        String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ") ON DUPLICATE KEY UPDATE " + updateSet;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < valueList.size(); i++) {
                ps.setString(i + 1, valueList.get(i));
                ps.setString(i + 1 + valueList.size(), valueList.get(i));
            }
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data saved successfully.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage());
        }
    }
}