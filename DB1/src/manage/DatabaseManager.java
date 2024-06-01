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

public class DatabaseManager extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn;

    public DatabaseManager() {
        setTitle("Database Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton btnReset = new JButton("Reset Database");
        JButton btnInput = new JButton("Manage Data");
        JButton btnView = new JButton("View Data");

        JPanel panel = new JPanel();
        panel.add(btnReset);
        panel.add(btnInput);
        panel.add(btnView);

        add(panel, BorderLayout.NORTH);

        btnReset.addActionListener(e -> resetDatabase());
        btnInput.addActionListener(e -> manageData());
        btnView.addActionListener(e -> viewData());

        connectDatabase();
        setVisible(true);
    }

    private void connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBTEST", "root", "seungjin1031!");
            System.out.println("DB 연결 완료");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, "DB 연결 오류: " + e.getMessage());
        }
    }

    private void resetDatabase() {
        try (Statement stmt = conn.createStatement()) {
            // 데이터베이스 초기화 로직
            JOptionPane.showMessageDialog(this, "Database has been reset");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error resetting database: " + e.getMessage());
        }
    }

    private void manageData() {
        // ManageDataWindow 클래스 인스턴스 생성하면서 현재의 데이터베이스 연결(conn)을 넘깁니다.
        new ManageDataWindow(conn);
    }

    private void viewData() {
        new TableView(conn);
    }

    public static void main(String[] args) {
        new DatabaseManager();
    }
}