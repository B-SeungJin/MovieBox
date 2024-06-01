import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeatWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel seatPanel; // 좌석을 담을 패널

    public SeatWindow() {
        setTitle("SEAT Selection");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        createSeatPanel(); // 좌석 패널 생성 및 구성

        // 타이틀 패널
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("MOVIE BOX", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 48));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // 예매정보 버튼 패널
        JPanel bookingInfoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton bookingInfoButton = new JButton("나의 예매정보");
        bookingInfoButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        bookingInfoPanel.add(bookingInfoButton);

        // 영화이름 패널    
        JPanel alarmPanel = new JPanel(new BorderLayout());
        JLabel alarmLabel = new JLabel("좌석 선택", SwingConstants.CENTER);
        alarmLabel.setFont(new Font("맑은 고딕", Font.BOLD, 48));
        alarmPanel.add(alarmLabel, BorderLayout.CENTER);


        // 레이아웃 구성
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(titlePanel, BorderLayout.CENTER);
        northPanel.add(bookingInfoPanel, BorderLayout.EAST);

        add(northPanel, BorderLayout.NORTH);
        add(alarmLabel, BorderLayout.CENTER);
        add(seatPanel, BorderLayout.CENTER); // 좌석 패널을 창의 중앙에 추가

        setVisible(true);
    }
    

    private void createSeatPanel() {
        seatPanel = new JPanel(new GridLayout(5, 4, 10, 10)); // 5행 4열 그리드, 간격 10
        // 좌석 버튼 생성 및 패널에 추가
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 4; col++) {
                JButton seatButton = new JButton((char) ('A' + row) + String.valueOf(col + 1));
                seatButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
                seatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 클릭 시 색상 변경을 통해 선택 상태 표시
                        JButton clickedButton = (JButton) e.getSource();
                        clickedButton.setBackground(Color.GREEN);
                    }
                });
                seatPanel.add(seatButton);
            }
        }
    }

    public static void main(String[] args) {
        new TimeWindow();
    }
}