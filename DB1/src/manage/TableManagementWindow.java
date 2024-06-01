package manage;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

class TableManagementWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn;
    private String tableName;

    public TableManagementWindow(Connection conn, String tableName) {
        this.conn = conn;
        this.tableName = tableName;
        setTitle("Manage " + tableName);
        setSize(300, 200);
        setLayout(new GridLayout(3, 1));  // 세 버튼을 세로로 배열

        JButton btnAddOrUpdate = new JButton("Add or Update Data");
        JButton btnDelete = new JButton("Delete Data");
        JButton btnChange = new JButton("Change Data");

        btnAddOrUpdate.addActionListener(e -> new DataInputForm(conn, tableName));  // 기존 데이터 입력 폼 사용
        btnDelete.addActionListener(e -> new DeleteDataForm(conn, tableName));  // 새로 구현할 삭제 폼
        btnChange.addActionListener(e -> new ChangeDataForm(conn, tableName));  // 새로 구현할 변경 폼

        add(btnAddOrUpdate);
        add(btnDelete);
        add(btnChange);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
