package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RoundedField extends JTextField implements FocusListener {

    private final String hint;
    private boolean showingHint = true;

    public RoundedField(String hint) {
        super(hint);
        this.hint = hint;

        setOpaque(false); // 배경 직접 그릴 거니까
        setForeground(Color.GRAY);
        setBorder(null);
        addFocusListener(this);

        setMargin(new Insets(10, 16, 10, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // 부드럽게 둥근 모서리 처리
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 배경색 채우기
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

        // 텍스트 입력 영역
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // 외곽선 없음
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (showingHint) {
            setText("");
            setForeground(Color.BLACK);
            showingHint = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (getText().isEmpty()) {
            setText(hint);
            setForeground(Color.GRAY);
            showingHint = true;
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
}
