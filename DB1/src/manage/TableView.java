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

class TableView extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn;

	public TableView(Connection conn) {
	    this.conn = conn;
	    setTitle("View Data");
	    setSize(600, 400);
	    setLayout(new BorderLayout());

	    try {
	        String[] columnNames = {"영화번호", "영화명", "감독명"};
	        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT 영화번호, 영화명, 감독명 FROM 영화");
	        while (rs.next()) {
	            int id = rs.getInt("영화번호");
	            String title = rs.getString("영화명");
	            String director = rs.getString("감독명");
	            model.addRow(new Object[]{id, title, director});
	        }
	        JTable table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        add(scrollPane, BorderLayout.CENTER);
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
	    }

	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true);
	}
}