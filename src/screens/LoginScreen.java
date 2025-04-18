package screens;

import components.FadeLabel;
import components.RoundedButton;
import components.RoundedField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginScreen extends JPanel {

    public LoginScreen() {
        setLayout(new BorderLayout());

        // 1. 전체 화면을 좌우 2분할하는 스플릿 패널
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(createImagePanel()); // 왼쪽: 이미지
        splitPane.setRightComponent(createRightLoginPanel()); // 오른쪽: 로그인 UI
        splitPane.setDividerSize(0);
        splitPane.setEnabled(false);

        // 💡 화면 크기에 따라 50:50 자동 분할되도록 설정
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                splitPane.setDividerLocation(width / 2);
            }
        });

        add(splitPane, BorderLayout.CENTER);
    }

    // 2. 왼쪽 이미지 패널
    private JPanel createImagePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        ImageIcon icon = new ImageIcon("src/assets/login_image.jpg"); // 이미지 경로 수정 필요
        Image img = icon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    // 3. 오른쪽 로그인 UI 패널
    private JPanel createRightLoginPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        Dimension wideSize = new Dimension(300, 40);

        FadeLabel label = new FadeLabel("도서 관리시스템(LMS) 시작하기", 100);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedField field1 = new RoundedField("👤", "아이디");
        field1.setMaximumSize(wideSize);
        field1.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedField field2 = new RoundedField("🔒", "비밀번호");
        field2.setMaximumSize(wideSize);
        field2.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton button = new RoundedButton("로그인");
        button.setMaximumSize(wideSize);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(label);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(field1);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(field2);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(button);

        // 가운데 정렬을 위한 래퍼 패널
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(centerPanel);

        // 실행 시 애니메이션 시작
        SwingUtilities.invokeLater(() -> label.startFadeIn());

        return rightPanel;
    }
}