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

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBTEST", "root", "seungjin1031!");
                new ManageDataWindow(conn);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Unable to connect to database: " + e.getMessage());
            }
        });
    }
}