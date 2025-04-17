import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import screens.LoginScreen;

public class Main {

    private static boolean isFullScreen = true;
    private static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createFullScreenWindow);
    }

    public static void createFullScreenWindow() {
        frame = new JFrame("Library Management System");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new LoginScreen());

        // 🔥 포커스 상관없이 키 입력을 전역으로 처리
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_F11) {
                toggleFullScreen();
            }
            return false;
        });

        device.setFullScreenWindow(frame);
        frame.setVisible(true);
    }

    public static void toggleFullScreen() {
        frame.dispose();

        if (isFullScreen) {
            device.setFullScreenWindow(null);
            frame.setUndecorated(false);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
        } else {
            frame.setUndecorated(true);
            device.setFullScreenWindow(frame);
        }

        isFullScreen = !isFullScreen;
        frame.setVisible(true);
    }
}
