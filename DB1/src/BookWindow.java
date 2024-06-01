import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    public BookWindow() {
        setTitle("BOOK WINDOW");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

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

        // 유저환영 알람
        JPanel userAlarmPanel = new JPanel(new BorderLayout());
        userAlarmPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        JLabel userAlarmLabel = new JLabel("000고객님, 환영합니다", SwingConstants.CENTER);
        userAlarmLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 27));
        userAlarmPanel.add(userAlarmLabel, BorderLayout.CENTER);
        
        
        // 예매목록 알람 패널
        JPanel bookAlarmPanel = new JPanel(new BorderLayout());
        bookAlarmPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 65, 40));
        JLabel bookAlarmLabel = new JLabel("예매목록", SwingConstants.CENTER);
        bookAlarmLabel.setFont(new Font("맑은 고딕", Font.BOLD, 37));
        bookAlarmPanel.add(bookAlarmLabel, BorderLayout.CENTER);

        // 영화 정보 패널
        JPanel moviePanel = new JPanel(new GridLayout(1, 3, 10, 10));
        moviePanel.add(createMovieCard("유인원의 킹덤", "#1213132134", "2024.06.01", "7관 A4", "13,500원"));
        moviePanel.add(createMovieCard("범죄도시 4", "#320312223", "2024.06.07", "2관 B1", "15,000원"));
        moviePanel.add(createMovieCard("그녀가 죽었다", "#2113003", "2024.06.11", "1관 C3", "15,000원"));

        // 레이아웃 구성
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(titlePanel, BorderLayout.CENTER);
        northPanel.add(bookingInfoPanel, BorderLayout.EAST);

        add(northPanel, BorderLayout.NORTH);
        add(userAlarmPanel, BorderLayout.EAST);
        add(bookAlarmPanel, BorderLayout.CENTER);
        add(moviePanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createMovieCard(String title, String subtitle, String director, String duration, String rating) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.LIGHT_GRAY);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        JLabel directorLabel = new JLabel("감독: " + director);
        directorLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        JLabel durationLabel = new JLabel("상영시간: " + duration);
        durationLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        JLabel ratingLabel = new JLabel("평점: " + rating);
        ratingLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

        card.add(titleLabel);
        card.add(subtitleLabel);
        card.add(directorLabel);
        card.add(durationLabel);
        card.add(ratingLabel);

        // 마우스 클릭 이벤트 추가
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new TicketWindow(); // TimeWindow를 열어 새 창을 표시
            }
        });
        
        return card;
    }

    public static void main(String[] args) {
        new BookWindow();
    }
}