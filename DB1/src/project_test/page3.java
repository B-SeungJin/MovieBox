package project_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class page3 extends JFrame{
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
	
	private int theaterNumber;
	
	public page3(int theaterNumber) {
		this.theaterNumber = theaterNumber;
		
		connection(); // 데이터베이스 연결
		
		setTitle(theaterNumber + "관 좌석배치");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		int row = 0, col = 0;
		try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT 가로X세로 " +
                         "FROM 상영관 " +
                         "WHERE 상영관.상영관번호 = '" + theaterNumber + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String seats = rs.getString("가로X세로");
                String[] rowAndcol = seats.split("X"); // 가로와 세로 좌석 수 분리
                col = Integer.parseInt(rowAndcol[0]);
                row = Integer.parseInt(rowAndcol[1]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		JLabel screenLabel = new JLabel("Screen", JLabel.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 20)); // 폰트 설정
        JPanel screenPanel = new JPanel();
        screenPanel.add(screenLabel);

        // 중앙 패널 생성
        JPanel centerPanel = new JPanel(new GridLayout(row, col));
        for (int i = 0; i < row * col; i++) {
            JButton button = new JButton(Integer.toString(i + 1));
            int seatNumber = i + 1;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new page4(theaterNumber, seatNumber).setVisible(true);
                }
            });
            
            centerPanel.add(button);
        }
        
        
        JButton cancelButton = new JButton("취소");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 현재 페이지(page2)를 닫고
                dispose();
            }
        });
        
        
        
        // JFrame에 screenPanel을 북쪽에 추가하고, centerPanel을 중앙에 추가
        panel.add(screenPanel);
        panel.add(centerPanel);
        panel.add(cancelButton);
        add(panel);
        
        
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		page3 page3 = new page3(1);
		page3.setVisible(true);
	}

}
