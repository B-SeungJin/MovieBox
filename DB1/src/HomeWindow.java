import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    public HomeWindow() {
        setTitle("HOME WINDOW");
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
        bookingInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // 현재 창 닫기
                new BookWindow();  // BookWindow 열기
            }
        });
        bookingInfoPanel.add(bookingInfoButton);

        // 검색 옵션 패널
        JPanel searchOptionsPanel = new JPanel();
        JButton searchButton = new JButton("검색");
        searchOptionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        String[] movieOptions = {"영화명", "영화명1", "영화명2"};
        String[] directorOptions = {"감독명", "감독1", "감독2"};
        String[] actorOptions = {"배우명", "배우1", "배우2"};
        String[] genreOptions = {"장르", "코미디", "드라마"};
        JComboBox<String> movieComboBox = new JComboBox<>(movieOptions);
        JComboBox<String> directorComboBox = new JComboBox<>(directorOptions);
        JComboBox<String> actorComboBox = new JComboBox<>(actorOptions);
        JComboBox<String> genreComboBox = new JComboBox<>(genreOptions);
        searchOptionsPanel.add(movieComboBox);
        searchOptionsPanel.add(directorComboBox);
        searchOptionsPanel.add(actorComboBox);
        searchOptionsPanel.add(genreComboBox);
        searchOptionsPanel.add(searchButton);

        // 카테고리 버튼 패널
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton currentMoviesButton = new JButton("현재 상영 영화");
        JButton upcomingMoviesButton = new JButton("개봉 예정 영화");
        currentMoviesButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        upcomingMoviesButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        categoryPanel.add(currentMoviesButton);
        categoryPanel.add(upcomingMoviesButton);

        // 영화 정보 패널
        JPanel moviePanel = new JPanel(new GridLayout(1, 3, 10, 10));
        moviePanel.add(createMovieCard("유인원의 킹덤", "새로운 시대", "영석", "145분", "7.72점"));
        moviePanel.add(createMovieCard("범죄도시 4", "곧 큰일 날거야", "영석", "109분", "7.58점"));
        moviePanel.add(createMovieCard("그녀가 죽었다", "미스터리", "영석", "103분", "8.83점"));

        // 레이아웃 구성
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(titlePanel, BorderLayout.CENTER);
        northPanel.add(bookingInfoPanel, BorderLayout.EAST);

        add(northPanel, BorderLayout.NORTH);
        add(searchOptionsPanel, BorderLayout.EAST);
        add(categoryPanel, BorderLayout.CENTER);
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
                dispose(); // 현재 창을 닫음
                new TimeWindow(); // TimeWindow를 열어 새 창을 표시
            }
        });
        
        return card;
    }

    public static void main(String[] args) {
        new HomeWindow();
    }
}