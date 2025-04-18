package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JoinScreen extends JPanel {

    public JoinScreen(CardLayout cardLayout, JPanel container) {
        setLayout(new BorderLayout());

        // 이미지 경로 설정
        String imagePath = "src/assets/arrow_back_icon.png";
        ImageIcon originalIcon = new ImageIcon(imagePath); // 원본 이미지 아이콘 생성

        // 이미지 크기 축소
        Image image = originalIcon.getImage();
        Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // 원하는 크기로 조정 (30x30으로 예시)
        ImageIcon icon = new ImageIcon(scaledImage); // 축소된 이미지 아이콘 생성
        JLabel label = new JLabel(icon); // JLabel에 아이콘 추가

        // 이미지를 왼쪽 상단에 배치할 JPanel 생성
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        imagePanel.add(label);

        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(container, "LoginScreen");  // "LoginScreen" 카드로 전환
                System.out.println("아이콘 클릭됨 - LoginScreen으로 이동");
            }
        });

        // 텍스트를 화면 중앙에 배치
        JLabel textLabel = new JLabel("Welcome to the Join Screen!");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.add(textLabel);

        // 패널에 이미지와 텍스트 패널 추가
        add(imagePanel, BorderLayout.NORTH); // 이미지를 상단에 배치
        add(textPanel, BorderLayout.CENTER); // 텍스트를 중앙에 배치
    }
}
