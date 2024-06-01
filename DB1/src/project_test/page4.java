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

public class page4 extends JFrame{

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
	
	private int theaterNumber, seatNumber;
	public page4(int theaterNumber, int seatNumber) {
		connection(); // 데이터베이스 연결
		
		this.theaterNumber = theaterNumber;
		this.seatNumber = seatNumber;
		
		setTitle("예매하시겠습니까?");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel confirmLabel = new JLabel("예매하시겠습니까?");
        confirmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton yesButton = new JButton("예");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new page5(theaterNumber, seatNumber).setVisible(true);
            }
        });
        yesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton noButton = new JButton("아니요");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        noButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        buttonPanel.add(Box.createHorizontalGlue()); // 버튼 패널의 왼쪽에 여백 추가
        buttonPanel.add(yesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // 두 버튼 사이에 간격 추가
        buttonPanel.add(noButton);
        buttonPanel.add(Box.createHorizontalGlue()); // 버튼 패널의 오른쪽에 여백 추가

        panel.add(Box.createVerticalGlue()); // 패널 상단에 여백 추가
        panel.add(confirmLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // 라벨과 버튼 패널 사이에 간격 추가
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue()); // 패널 하단에 여백 추가
        
        
        add(panel);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		page4 page4 = new page4(1, 1);
		page4.setVisible(true);
	}

}
