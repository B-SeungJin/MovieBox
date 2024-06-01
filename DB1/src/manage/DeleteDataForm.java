package manage;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDataForm extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn;
    private String tableName;
    private JTextField conditionField;
    private JButton deleteButton;

    public DeleteDataForm(Connection conn, String tableName) {
        this.conn = conn;
        this.tableName = tableName;
        setTitle("Delete from " + tableName);
        setSize(400, 100);
        setLayout(new FlowLayout());

        conditionField = new JTextField(20);
        deleteButton = new JButton("Delete");

        add(new JLabel("Condition:"));
        add(conditionField);
        add(deleteButton);

        deleteButton.addActionListener(e -> executeDelete());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void executeDelete() {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM " + tableName + " WHERE " + conditionField.getText();
            int affectedRows = stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, affectedRows + " rows deleted.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting data: " + ex.getMessage());
        }
    }
}

