import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieBoxUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public MovieBoxUI() {
        setTitle("Movie Box");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel tiltedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                Color gray = new Color(200, 200, 200);
                g2d.setColor(gray);

                // 상단 기울어진 사각형
                g2d.fillPolygon(new int[]{0, getWidth(), getWidth(), 0}, new int[]{getHeight()/3, getHeight()/3 - 30, getHeight()/3, getHeight()/3 + 30}, 4);

                // 하단 기울어진 사각형
                g2d.fillPolygon(new int[]{0, getWidth(), getWidth(), 0}, new int[]{getHeight()/3, getHeight()/3 - 30, 2*getHeight()/3, 2*getHeight()/3 + 30}, 4);
            }
        };

        tiltedPanel.setLayout(new GridLayout(3, 1));
        tiltedPanel.setOpaque(false);

        JLabel labelKorean = new JLabel("영화 예매", SwingConstants.CENTER);
        labelKorean.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        labelKorean.setOpaque(false);

        JLabel labelEnglish = new JLabel("MOVIE BOX", SwingConstants.CENTER);
        labelEnglish.setFont(new Font("Arial", Font.BOLD, 40));
        labelEnglish.setOpaque(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        JButton button = new JButton("접속하기");
        button.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(200, 60));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToHomeWindow();

            }
        });

        buttonPanel.add(button);

        tiltedPanel.add(labelKorean);
        tiltedPanel.add(labelEnglish);
        tiltedPanel.add(buttonPanel);

        getContentPane().add(tiltedPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void switchToHomeWindow() {
        this.setVisible(false);  // 현재 창을 숨김
        this.dispose();  // 현재 창을 닫음
        HomeWindow homeWindow = new HomeWindow();  // 새 창 생성
        homeWindow.setVisible(true);  // 새 창 표시
    }

    public static void main(String[] args) {
        new MovieBoxUI();
    }
}