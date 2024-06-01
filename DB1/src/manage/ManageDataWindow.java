package manage;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

class ManageDataWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn;

    public ManageDataWindow(Connection conn) {
        this.conn = conn;
        setTitle("Manage Data");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1)); // One column, variable number of rows

        String[] tableNames = {"회원고객", "영화", "평점정보", "상영관종류", "상영관", "상영일정", "좌석", "예매정보", "티켓", "회원선택정보"};
        for (String tableName : tableNames) {
            JButton button = new JButton("Manage " + tableName);
            button.addActionListener(e -> new DataInputForm(conn, tableName));
            add(button);
        }

        setVisible(true);
    }
}