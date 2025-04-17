package screens;

import components.HintTextField;
import components.RoundedField;
import components.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel {

    public LoginScreen() {
        Dimension wideSize = new Dimension(500, 40);
        Dimension fieldSize = new Dimension(500, 40);
        setLayout(new BorderLayout());

        // 1. 요소들을 세로로 정렬할 패널 (Y_AXIS)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("도서 관리시스템(LMS) 시작하기");
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // HintTextField field1 = new HintTextField("아이디를 입력하세요");
        RoundedField field1 = new RoundedField("아이디");
        field1.setMaximumSize(wideSize);
        field1.setPreferredSize(wideSize);
        field1.setMinimumSize(wideSize);
        field1.setAlignmentX(Component.CENTER_ALIGNMENT);

        // HintTextField field2 = new HintTextField("비밀번호를 입력하세요");
        RoundedField field2 = new RoundedField("비밀번호");
        field2.setMaximumSize(wideSize);
        field2.setPreferredSize(wideSize);
        field2.setMinimumSize(wideSize);
        field2.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton button = new RoundedButton("로그인");
        button.setMaximumSize(wideSize);
        button.setPreferredSize(wideSize);
        button.setMinimumSize(wideSize);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 요소 간 간격 추가
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(label);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(field1);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(field2);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(button);

        // 2. 요소 묶음을 화면 중앙에 배치하기 위한 래퍼 패널
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.add(centerPanel);

        add(centerWrapper, BorderLayout.CENTER);

        // 3. 포커스를 가지지 않는 dummy 컴포넌트
        JPanel dummyFocusPanel = new JPanel();
        dummyFocusPanel.setFocusable(true);
        dummyFocusPanel.setPreferredSize(new Dimension(0, 0));
        dummyFocusPanel.setOpaque(false); // 완전 투명
        add(dummyFocusPanel, BorderLayout.SOUTH); // 아무데나 붙이기만 하면 됨

        // 4. 실행 직후 dummy 에 포커스 줘서 커서 깜빡임 방지
        SwingUtilities.invokeLater(() -> {
            dummyFocusPanel.requestFocusInWindow();
        });
    }
}
