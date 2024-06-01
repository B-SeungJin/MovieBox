import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TimeWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    public TimeWindow() {
        setTitle("TIME WINDOW");
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

        // 영화이름 패널    
        JPanel movieTitlePanel = new JPanel(new BorderLayout());
        JLabel movieTitleLabel = new JLabel("혹성탈출:새로운 시대", SwingConstants.CENTER);
        movieTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 48));
        movieTitlePanel.add(movieTitleLabel, BorderLayout.CENTER);

        // 시간 정보 패널
        JPanel movieTimePanel = new JPanel(new GridLayout(1, 3, 10, 10));
        movieTimePanel.add(createMovieTimeCard("2024.06.01", "10:20~12:40", "7관"));
        movieTimePanel.add(createMovieTimeCard("2024.06.07", "18:20~20:40", "2관"));
        movieTimePanel.add(createMovieTimeCard("2024.06.10", "08:20~10:40", "4관"));
        movieTimePanel.add(createMovieTimeCard("2024.06.21", "11:20~13:40", "1관"));

        // 레이아웃 구성
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(titlePanel, BorderLayout.CENTER);
        northPanel.add(bookingInfoPanel, BorderLayout.EAST);

        add(northPanel, BorderLayout.NORTH);
        add(movieTitleLabel, BorderLayout.CENTER);
        add(movieTimePanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createMovieTimeCard(String day, String time, String place) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.LIGHT_GRAY);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel dayLabel = new JLabel(day);
        dayLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        JLabel placeLabel = new JLabel(place);
        placeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

        card.add(dayLabel);
        card.add(timeLabel);
        card.add(placeLabel);

        // Click event to open TimeWindow
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SeatWindow(); // Assuming TimeWindow has a public constructor and it sets itself visible
            }
        });
        
        return card;
    }

    public static void main(String[] args) {
        new TimeWindow();
    }
}