package project_test;

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

public class page1 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn;
	
	public void connection(){
		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL 드라이버 로드
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBTEST", "root","seungjin1031!"); // JDBC 연결
			System.out.println("DB 연결 완료");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
		}
	};
	
	
	public page1(){
		connection(); // 데이터베이스 연결
		
		setTitle("영화목록");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		JPanel containerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel moviePanel = new JPanel(new GridLayout(0, 3));
        moviePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        moviePanel.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        
        containerPanel.add(moviePanel, gbc);
        
        
        
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT 영화번호, 영화명 FROM 영화"; // 영화 목록 쿼리
            ResultSet rs = stmt.executeQuery(sql);
            
            // ResultSet에서 데이터 추출하여 테이블 모델에 추가
            while (rs.next()) {
            	int movieNumber = rs.getInt("영화번호");
                String movieTitle = rs.getString("영화명");
                JButton movieButton = new JButton(movieTitle);
                moviePanel.add(movieButton); // 행 추가
                
                movieButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    	saveSelect(movieNumber);
                    	printAllTable();
                        new page2(movieTitle).setVisible(true);
                        
                    }
                });      
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        add(containerPanel, BorderLayout.CENTER);
	}
	
	
	public void saveSelect(int movieNumber) {
        String sql = "UPDATE 회원선택정보 SET 영화번호 = ? WHERE 회원아이디 = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, movieNumber);
            pstmt.setString(2, "user1");
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Update successful.");
            } else {
                System.out.println("No matching record found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void printAllTable() {
        String sql = "SELECT * FROM 회원선택정보 WHERE 회원아이디 = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "user1");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
              
                String userId = rs.getString("회원아이디");
                int movieNumber = rs.getInt("영화번호");
                System.out.println("회원아이디: " + userId + ", 영화번호: " + movieNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		page1 frame = new page1();
		
		frame.setVisible(true);
	}

}

