package screens;

import components.GoBackButtonFactory;
import components.RoundedButton;
import http.BookHttp;
import vo.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookInfoScreen extends JPanel {

    private final Book book;
    private final CardLayout cardLayout;
    private final JPanel container;

    public BookInfoScreen(CardLayout cardLayout, JPanel container, Book book) {
        this.cardLayout = cardLayout;
        this.container = container;
        this.book = book;
        setLayout(new BorderLayout());

        System.out.println("📄 BookInfoScreen 새로 생성됨 (bookId=" + book.getBookId() + ")");

        // 1. 전체 화면을 좌우 2분할하는 스플릿 패널
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(leftView(cardLayout, container)); // 왼쪽: 이미지
        splitPane.setRightComponent(rightView(cardLayout, container)); // 오른쪽: 로그인 UI
        splitPane.setDividerSize(0);
        splitPane.setEnabled(false);

        // 💡 화면 크기에 따라 50:50 자동 분할되도록 설정
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                splitPane.setDividerLocation(width / 4);
            }
        });

        add(splitPane, BorderLayout.CENTER);

    }

    private JPanel leftView(CardLayout cardLayout, JPanel container) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(80, 170, 48));

        // 1-1. 뒤로 가기 프레임
        JPanel goBackPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        goBackPanel.setOpaque(false);
        JButton goBackButton = GoBackButtonFactory.createGoBackButton(true);
        goBackPanel.add(goBackButton);
        panel.add(goBackPanel, BorderLayout.NORTH);

        // 1-2. 뒤로 가기 리스너
        goBackButton.addActionListener(e -> {
            cardLayout.show(container, "MenuScreen");
            System.out.println("버튼 클릭됨 - MenuScreen으로 이동");
        });

        // 그 외 컴포넌트들 담는 컨테이너
        JPanel anotherPanel = new JPanel();
        anotherPanel.setLayout(new BoxLayout(anotherPanel, BoxLayout.Y_AXIS));
        anotherPanel.setBackground(new Color(80, 170, 48));

        // 프로필 사진 (디폴트 부엉이 고정)
        ImageIcon icon = new ImageIcon("src/assets/profile.png");
        Image img = icon.getImage().getScaledInstance(117, 167, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        anotherPanel.add(label);

        // 유저 이름 표시
        anotherPanel.add(Box.createVerticalStrut(20)); // 공간 여백
        JLabel name = new JLabel("안명근"); // DB에서 연결해서 변경 필요 (디폴트는 안명근으로 설정)
        name.setFont(new Font("SansSerif", Font.BOLD, 35));
        name.setForeground(Color.WHITE);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        anotherPanel.add(name);
        anotherPanel.add(Box.createVerticalStrut(35)); // 공간 여백

        // 첫번째 버튼
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(200, 240, 200)); // 배경색 RGB(80, 170, 48)
        panel1.setOpaque(true);
        panel1.setPreferredSize(new Dimension(330, 60)); // 가로 200px, 세로 50px
        panel1.setMinimumSize(new Dimension(330, 60));
        panel1.setMaximumSize(new Dimension(330, 60));
        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.setLayout(new BorderLayout()); // JLabel 중앙 배치를 위해

        JLabel labelInside1 = new JLabel("\uD83D\uDCD6\n 도서 대출", SwingConstants.LEFT);
        labelInside1.setFont(new Font("SansSerif", Font.BOLD, 25));
        labelInside1.setForeground(Color.BLACK);
        labelInside1.setOpaque(false); // JLabel 배경 투명
        labelInside1.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // 상, 좌, 하, 우

        panel1.add(labelInside1, BorderLayout.CENTER);
        anotherPanel.add(panel1);

        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(container, "BookLendingScreen1");
                System.out.println("버튼 클릭됨 - BookLendingScreen1으로 이동");
            }
        });

        // 두번째 버튼
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(200, 240, 200)); // 배경색 RGB(80, 170, 48)
        panel2.setOpaque(true);
        panel2.setPreferredSize(new Dimension(330, 60)); // 가로 200px, 세로 50px
        panel2.setMinimumSize(new Dimension(330, 60));
        panel2.setMaximumSize(new Dimension(330, 60));
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.setLayout(new BorderLayout()); // JLabel 중앙 배치를 위해

        JLabel labelInside2 = new JLabel("\uD83D\uDCDA\n 도서 반납", SwingConstants.LEFT);
        labelInside2.setFont(new Font("SansSerif", Font.BOLD, 25));
        labelInside2.setForeground(Color.BLACK);
        labelInside2.setOpaque(false); // JLabel 배경 투명
        labelInside2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // 상, 좌, 하, 우

        panel2.add(labelInside2, BorderLayout.CENTER);
        anotherPanel.add(panel2);

        panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(container, "BookReturnScreen");
                System.out.println("버튼 클릭됨 - BookReturnScreen으로 이동");
            }
        });

        // 세번째 버튼
        JPanel panel3 = new JPanel();
        panel3.setBackground(new Color(200, 240, 200)); // 배경색 RGB(80, 170, 48)
        panel3.setOpaque(true);
        panel3.setPreferredSize(new Dimension(330, 60)); // 가로 200px, 세로 50px
        panel3.setMinimumSize(new Dimension(330, 60));
        panel3.setMaximumSize(new Dimension(330, 60));
        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.setLayout(new BorderLayout()); // JLabel 중앙 배치를 위해

        JLabel labelInside3 = new JLabel("\uD83D\uDED2\n 도서 대출 관리", SwingConstants.LEFT);
        labelInside3.setFont(new Font("SansSerif", Font.BOLD, 25));
        labelInside3.setForeground(Color.BLACK);
        labelInside3.setOpaque(false); // JLabel 배경 투명
        labelInside3.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // 상, 좌, 하, 우

        panel3.add(labelInside3, BorderLayout.CENTER);
        anotherPanel.add(panel3);

        // 네번째 버튼
        JPanel panel4 = new JPanel();
        panel4.setBackground(new Color(200, 240, 200)); // 배경색 RGB(80, 170, 48)
        panel4.setOpaque(true);
        panel4.setPreferredSize(new Dimension(330, 60)); // 가로 200px, 세로 50px
        panel4.setMinimumSize(new Dimension(330, 60));
        panel4.setMaximumSize(new Dimension(330, 60));
        panel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel4.setLayout(new BorderLayout()); // JLabel 중앙 배치를 위해

        JLabel labelInside4 = new JLabel("\uD83D\uDCE5\n 도서 반납 관리", SwingConstants.LEFT);
        labelInside4.setFont(new Font("SansSerif", Font.BOLD, 25));
        labelInside4.setForeground(Color.BLACK);
        labelInside4.setOpaque(false); // JLabel 배경 투명
        labelInside4.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // 상, 좌, 하, 우

        panel4.add(labelInside4, BorderLayout.CENTER);
        anotherPanel.add(panel4);

        // 다섯번째 버튼
        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.WHITE); // 배경색 RGB(80, 170, 48)
        panel5.setOpaque(true);
        panel5.setPreferredSize(new Dimension(330, 60)); // 가로 200px, 세로 50px
        panel5.setMinimumSize(new Dimension(330, 60));
        panel5.setMaximumSize(new Dimension(330, 60));
        panel5.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel5.setLayout(new BorderLayout()); // JLabel 중앙 배치를 위해

        JLabel labelInside5 = new JLabel("\uD83D\uDD0D  도서 관리", SwingConstants.LEFT);
        labelInside5.setFont(new Font("SansSerif", Font.BOLD, 25));
        labelInside5.setForeground(Color.BLACK);
        labelInside5.setOpaque(false); // JLabel 배경 투명
        labelInside5.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // 상, 좌, 하, 우

        panel5.add(labelInside5, BorderLayout.CENTER);
        anotherPanel.add(panel5);

        panel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 기존 BookMngScreen 제거 (있다면)
                Component[] components = container.getComponents();
                for (Component comp : components) {
                    if (comp instanceof BookMngScreen) {
                        container.remove(comp);
                        break;
                    }
                }

                // 새로 생성한 BookMngScreen 추가
                BookMngScreen refreshedScreen = new BookMngScreen(cardLayout, container);
                container.add(refreshedScreen, "BookMngScreen");

                // 화면 이동
                cardLayout.show(container, "BookMngScreen");
                System.out.println("🔄 BookMngScreen으로 이동 및 새로고침");
            }
        });



        panel.add(anotherPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel rightView(CardLayout cardLayout, JPanel container) {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.getColor(String.valueOf(new Color(200,200,200))));

        // 전체 세로 정렬 박스
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(BorderFactory.createEmptyBorder(60, 50, 50, 60)); // 전체 여백

        // 1. 제목
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.setOpaque(false); // 배경 투명
        JLabel titleLabel = new JLabel("도서 관리");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        titlePanel.add(titleLabel);
        mainBox.add(titlePanel);

        // 2. 제목 소개
        JPanel indicatorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        indicatorPanel.setOpaque(false);

        JLabel indicatorLabel = new JLabel("모든 도서정보를 조회하세요");
        indicatorLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        indicatorPanel.add(indicatorLabel);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(indicatorPanel);

        // 3. 구분선 (커스텀 굵은 회색 선으로 직접 그림)
        mainBox.add(Box.createVerticalStrut(10));

        JPanel thickLine = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(192, 192, 192));  // 선 색 (연회색)
                g.fillRect(0, 0, getWidth(), 4);       // 💡 두께: 3px
            }
        };
        thickLine.setPreferredSize(new Dimension(Integer.MAX_VALUE, 4));
        thickLine.setMaximumSize(new Dimension(Integer.MAX_VALUE, 4));
        thickLine.setOpaque(false);

        mainBox.add(thickLine);
        mainBox.add(Box.createVerticalStrut(20));

        // 4. 이미지 + 폼 라벨 전체 컨테이너
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setOpaque(false);

        // ✅ 이미지 + 폼을 나란히 배치할 박스 컨테이너 (가로 박스)
        JPanel imageAndFormPanel = new JPanel();
        imageAndFormPanel.setLayout(new BoxLayout(imageAndFormPanel, BoxLayout.X_AXIS));
        imageAndFormPanel.setOpaque(false);

        // 4-1. 이미지
        ImageIcon icon = new ImageIcon("src/assets/book_image.png");
        Image scaled = icon.getImage().getScaledInstance(162, 220, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setPreferredSize(new Dimension(162, 218));
        imageLabel.setMinimumSize(new Dimension(162, 218));
        imageLabel.setMaximumSize(new Dimension(162, 218));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setAlignmentY(Component.TOP_ALIGNMENT); // 위쪽 정렬

        imageAndFormPanel.add(imageLabel);

        // ✅ 이미지와 폼 사이 간격
        imageAndFormPanel.add(Box.createHorizontalStrut(20)); // 간격 조절

        // 4-2. 도서 폼 정보 (표 형태로 정리)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        formPanel.setOpaque(true);
        formPanel.setBackground(new Color(250, 250, 250));
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        String[] labels = {"고유번호", "도서 제목", "저자", "출판사", "청구기호", "대출 여부"};
        String[] values = {
                book.getBookId(),
                book.getBookTitle(),
                book.getBookWriter(),
                book.getBookPublisher(),
                book.getBookCNum(),
                book.getLendNY() == 1 ? "Y" : "N"
        };

        for (int i = 0; i < labels.length; i++) {
            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
            row.setPreferredSize(new Dimension(500, 36));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
            row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220))); // 아래 테두리
            row.setBackground(Color.WHITE);

            // 라벨
            JLabel label = new JLabel(labels[i]);
            label.setPreferredSize(new Dimension(100, 28));
            label.setFont(new Font("SansSerif", Font.PLAIN, 15));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            row.add(label);

            // ✅ 세로 구분선 추가 (1px 너비)
            JPanel verticalLine = new JPanel();
            verticalLine.setPreferredSize(new Dimension(1, 28));
            verticalLine.setMaximumSize(new Dimension(1, 28));
            verticalLine.setBackground(new Color(200, 200, 200)); // 연한 회색
            row.add(verticalLine);

            // 입력 필드
            JTextField tf = new JTextField(values[i]);
            tf.setPreferredSize(new Dimension(300, 28));
            tf.setFont(new Font("SansSerif", Font.PLAIN, 16));
            tf.setBorder(BorderFactory.createCompoundBorder(
                    tf.getBorder(),
                    BorderFactory.createEmptyBorder(0, 8, 0, 0)  // 왼쪽 8px 여백
            ));
            tf.setFont(new Font("SansSerif", Font.PLAIN, 15));

            if (i == 5) {
                tf.setEditable(false);
                tf.setBackground(new Color(240, 240, 240)); // 읽기 전용임을 시각적으로 구분
            }

            row.add(Box.createHorizontalStrut(10)); // 간격
            row.add(tf);

            formPanel.add(row);
        }


        // ✅ 이미지 + 폼을 contentPanel에 통째로 붙임
        imageAndFormPanel.add(formPanel);
        contentPanel.add(imageAndFormPanel, BorderLayout.CENTER);
        mainBox.add(contentPanel);


        // 2. 책 소개
        JPanel descTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        descTitlePanel.setOpaque(false);

        JLabel descLabel = new JLabel("책 소개");
        descLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        descTitlePanel.add(descLabel);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(descTitlePanel);


        descTitlePanel.add(Box.createVerticalStrut(35)); // 간격 조절


        JTextArea descArea = new JTextArea(5, 60);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setFont(new Font("SansSerif", Font.PLAIN, 15));

        descArea.setText(
                book.getBookIntrd() != null && !book.getBookIntrd().trim().isEmpty()
                        ? book.getBookIntrd()
                        : "책 소개 정보가 없습니다."
        );

        // ✅ 통일된 테두리 + 안쪽 여백
        descArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));

        JScrollPane scrollPane = new JScrollPane(descArea);
        scrollPane.setPreferredSize(new Dimension(300, 190)); // 적절한 크기 조정 가능
        scrollPane.setBorder(null); // ✅ 스크롤 외곽 테두리는 제거 (descArea 테두리만 보이게)

        mainBox.add(scrollPane);

        // 버튼 영역 전체 수정 코드
        mainBox.add(Box.createVerticalStrut(20));

        // 버튼 수평 정렬을 위한 패널
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setOpaque(false);

        // 삭제 버튼
        RoundedButton deleteBtn = new RoundedButton("삭제");
        deleteBtn.setFont(new Font("SansSerif", Font.PLAIN, 19));
        deleteBtn.setPreferredSize(new Dimension(120, 38));
        deleteBtn.setMaximumSize(deleteBtn.getPreferredSize());
        deleteBtn.setMinimumSize(deleteBtn.getPreferredSize());
        deleteBtn.setNewColor(new Color(253, 46, 14), new Color(255, 90, 60));
        deleteBtn.enableGradient(new Color(0xFF, 0x6D, 0x6D), new Color(0xFF, 0x00, 0x00));
        deleteBtn.setBorderColor(new Color(0xFF, 0x00, 0x00));
        deleteBtn.setTextColor(Color.WHITE);
        deleteBtn.addActionListener(e -> {
            String bookId = "";
            String bookTitle = "";

            // 🔍 formPanel의 컴포넌트 순회해서 텍스트 추출
            Component[] rows = formPanel.getComponents();
            if (rows.length >= 2) {
                JPanel row1 = (JPanel) rows[0];
                JTextField idField = (JTextField) row1.getComponent(3);
                bookId = idField.getText();

                JPanel row2 = (JPanel) rows[1];
                JTextField titleField = (JTextField) row2.getComponent(3);
                bookTitle = titleField.getText();
            }

            // 2. 삭제 확인 다이얼로그
            int result = JOptionPane.showConfirmDialog(
                    BookInfoScreen.this,
                    "고유번호: " + bookId + "\n도서 제목: " + bookTitle + "\n\n이 도서 정보를 삭제하시겠습니까?",
                    "도서 삭제 확인",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                    boolean success = BookHttp.deleteBook(bookId);  // ✅ 실제 API 호출
                    if (success) {
                        JOptionPane.showMessageDialog(BookInfoScreen.this, "삭제가 완료되었습니다.");

                        // 새로고침용 BookMngScreen 생성
                        container.remove(BookInfoScreen.this);
                        BookMngScreen refreshed = new BookMngScreen(cardLayout, container);
                        container.add(refreshed, "BookMngScreen");

                        cardLayout.show(container, "BookMngScreen");
                        System.out.println("🗑 BookMngScreen으로 이동 및 새로고침 완료");
                    } else {
                        JOptionPane.showMessageDialog(BookInfoScreen.this, "삭제에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(BookInfoScreen.this, "서버 통신 중 오류가 발생했습니다:\n" + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // 수정 버튼
        RoundedButton updateBtn = new RoundedButton("수정");
        updateBtn.setFont(new Font("SansSerif", Font.PLAIN, 19));
        updateBtn.setPreferredSize(new Dimension(120, 38));
        updateBtn.setMaximumSize(updateBtn.getPreferredSize());
        updateBtn.setMinimumSize(updateBtn.getPreferredSize());
        updateBtn.setNewColor(new Color(10, 141, 254), new Color(50, 170, 255));
        updateBtn.enableGradient(new Color(0x3D, 0xA5, 0xFF), new Color(0x00, 0x89, 0xFF));
        updateBtn.setBorderColor(new Color(0x00, 0x6F, 0xFF));
        updateBtn.setTextColor(Color.WHITE);
        updateBtn.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    BookInfoScreen.this,
                    "이 도서 정보를 수정하시겠습니까?",
                    "수정 확인",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                    // 🔍 폼에서 사용자 입력 값 가져오기
                    Component[] rows = formPanel.getComponents();

                    String bookId = ((JTextField)((JPanel) rows[0]).getComponent(3)).getText().trim();
                    String bookTitle = ((JTextField)((JPanel) rows[1]).getComponent(3)).getText().trim();
                    String bookWriter = ((JTextField)((JPanel) rows[2]).getComponent(3)).getText().trim();
                    String bookPublisher = ((JTextField)((JPanel) rows[3]).getComponent(3)).getText().trim();
                    String bookCNum = ((JTextField)((JPanel) rows[4]).getComponent(3)).getText().trim();
                    String bookIntrd = descArea.getText().trim();

                    // Book 객체 업데이트
                    Book updated = new Book();
                    updated.setBookId(bookId);
                    updated.setBookTitle(bookTitle);
                    updated.setBookWriter(bookWriter);
                    updated.setBookPublisher(bookPublisher);
                    updated.setBookCNum(bookCNum);
                    updated.setBookIntrd(bookIntrd);

                    boolean success = BookHttp.updateBook(updated);

                    if (success) {
                        JOptionPane.showMessageDialog(BookInfoScreen.this, "도서 정보 수정이 완료되었습니다.");

                        // 화면 새로고침
                        container.remove(BookInfoScreen.this);
                        BookInfoScreen refreshed = new BookInfoScreen(cardLayout, container, updated);
                        container.add(refreshed, "BookInfoScreen");
                        cardLayout.show(container, "BookInfoScreen");
                    } else {
                        JOptionPane.showMessageDialog(BookInfoScreen.this, "도서 수정에 실패했습니다.", "수정 실패", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(BookInfoScreen.this, "서버 오류: " + ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(BookInfoScreen.this, "알 수 없는 오류가 발생했습니다.", "에러", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 버튼 수평으로 추가
        buttons.add(updateBtn);                         // 왼쪽
        buttons.add(Box.createHorizontalStrut(12));     // 간격
        buttons.add(deleteBtn);                         // 오른쪽

        // 오른쪽 정렬을 위한 외부 Box
        Box outerBox = Box.createHorizontalBox();
        outerBox.add(Box.createHorizontalGlue()); // 왼쪽 여백 밀기
        outerBox.add(buttons);

        mainBox.add(outerBox);

        rightPanel.add(mainBox, BorderLayout.NORTH);
        return rightPanel;
    }

}