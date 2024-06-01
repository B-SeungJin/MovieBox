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

public class page2 extends JFrame{
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
	
	private String movieTitle;
	
	public page2(String movieTitle) {
		this.movieTitle = movieTitle;
		
		connection(); // 데이터베이스 연결
		
		setTitle(movieTitle + " 상영일정");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // 라벨 생성
        JLabel titleLabel = new JLabel("MOVIE BOX", SwingConstants.CENTER);

        
        JPanel sessionPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT 상영일정.상영요일, 상영일정.상영시작시간, 상영일정.상영관번호 " +
                         "FROM 상영일정 " +
                         "JOIN 영화 ON 상영일정.영화번호 = 영화.영화번호 " +
                         "WHERE 영화.영화명 = '" + movieTitle + "'";
                         

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String date = rs.getString("상영요일");
                String startTime = rs.getString("상영시작시간");
                int theaterNumber = rs.getInt("상영관번호");
                String session = date + "\n" + startTime + "\n" + theaterNumber + "관";

                JButton button = new JButton("<html>" + session.replaceAll("\n", "<br>") + "</html>");
                
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new page3(theaterNumber).setVisible(true);;
                    }
                });
                
                sessionPanel.add(button);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        JButton cancelButton = new JButton("취소");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 현재 페이지(page2)를 닫고
                dispose();
                
                
            }
        });
        
        panel.add(titleLabel);
        panel.add(sessionPanel);
        panel.add(cancelButton);
        // 프레임에 패널 추가
        add(panel);
        
        
	}
	
	public static void main(String[] args) {
			
			// TODO Auto-generated method stub
			page2 frame = new page2("movie");
			frame.setVisible(true);
		}

}

