package Management;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;


public class SubjectForm extends JFrame {
    UserDAO userDAO = new UserDAO();
    JPanel panel1, panel2, panel3, panel4, panel5;

    JTextField   subjectNameText, hourText, creditText;
    static DefaultTableModel tableModel;
    static JTable table;
    JScrollPane scrollPane1;
    JTextPane textPane1;
    static JComboBox categoryComboBox, yearComboBox, majorNameComboBox, gradeCombobox, semesterComboBox, profNameComboBox;
    public SubjectForm() throws BadLocationException, SQLException {
        super("학생관리");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 900);
        setLocationRelativeTo(null);

        this.setLayout(null);

        panel();
        label();
        button();
        userDAO.categorySearchBySubjectName();
        userDAO.categorySearchByProfName();

        setVisible(true);
    }

    void panel() {
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        panel1.setLayout(null); // 레이아웃 매니저 설정
        panel2.setLayout(null); // 레이아웃 매니저 설정
        panel3.setLayout(null); // 레이아웃 매니저 설정
        panel4.setLayout(null); // 레이아웃 매니저 설정
        panel5.setLayout(null); // 레이아웃 매니저 설정

        panel1.setBounds(0, 0, 650, 80);
        panel1.setBackground(Color.WHITE);
        panel2.setBounds(0, 80, 700, 300);
        panel2.setBackground(new Color(15,15,112));
        panel3.setBounds(0, 380, 650, 70);
        panel3.setBackground(Color.WHITE);
        panel4.setBounds(0, 450, 650, 250);
        panel4.setBackground(new Color(15,15,112));
        panel5.setBounds(0, 700, 650, 150);
        panel5.setBackground(Color.WHITE);

        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
    }

    void label() throws BadLocationException, SQLException {

        JLabel label0 = new JLabel("교과목 관리");
        label0.setForeground(Color.BLACK);
        label0.setFont(new Font("나눔고딕", Font.BOLD, 20));
        label0.setBounds(275, -10, 150, 100);

        JLabel label1 = new JLabel("개설강좌명");
        label1.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label1.setBounds(100, 55, 100, 20);

        JLabel label2 = new JLabel("개설년도");
        label2.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label2.setBounds(333, 53, 100, 20);

        JLabel label3 = new JLabel("개설학과");
        label3.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label3.setBounds(100, 100, 100, 20);

        JLabel label4 = new JLabel("개설학년");
        label4.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label4.setBounds(333, 98, 100, 20);

        JLabel label5 = new JLabel("개설학기");
        label5.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label5.setBounds(100, 150, 100, 20);

        JLabel label6 = new JLabel("수업시수");
        label6.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label6.setBounds(333, 148, 100, 20);

        JLabel label7 = new JLabel("담당교수");
        label7.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label7.setBounds(100, 200, 100, 20);

        JLabel label8 = new JLabel("개설학점");
        label8.setFont(new Font("나눔고딕", Font.BOLD, 15));
        label8.setBounds(333, 198, 100, 20);

        panel1.add(label0);
        panel2.add(label1);
        panel2.add(label2);
        panel2.add(label3);
        panel2.add(label4);
        panel2.add(label5);
        panel2.add(label6);
        panel2.add(label7);
        panel2.add(label8);

        subjectNameText = new JTextField();
        subjectNameText.setBounds(200, 53, 100, 24);
        hourText = new JTextField();
        hourText.setBounds(430, 150, 100, 24);
        creditText = new JTextField();
        creditText.setBounds(430, 200, 100, 24);

        panel2.add(subjectNameText);
        panel2.add(hourText);
        panel2.add(creditText);


        textPane1 = new JTextPane();
        textPane1.setBackground(Color.GRAY);
        textPane1.setBounds(15, 15, 600, 220);
        textPane1.setEditable(false);
        textPane1.setCursor(null); // 커서 비활성화
        textPane1.setOpaque(false); // 투명 배경 설정
        textPane1.setFocusable(false); // 포커스 비활성화

        JScrollPane scrollPane = new JScrollPane(textPane1);
        scrollPane.setBounds(15, 25, 600, 250);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);// 검은색 테두리, 두께 2
        scrollPane.setBorder(border);


        // 테이블 모델을 초기화
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // 모든 셀을 수정 불가능하게 설정
                return false;
            }
        };
        tableModel.addColumn("교과코드");
        tableModel.addColumn("교과명");
        tableModel.addColumn("개설학과");
        tableModel.addColumn("개설년도");
        tableModel.addColumn("개설학년");
        tableModel.addColumn("개설학기");
        tableModel.addColumn("수업시수");
        tableModel.addColumn("담당교수");
        tableModel.addColumn("학점");

        // JTable 생성 및 모델 설정
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("나눔고딕", Font.BOLD, 15); // Example: Bold and larger font
        header.setFont(headerFont);


        userDAO.subjectInfoAll();

        // JScrollPane에 JTable 추가
        scrollPane1 = new JScrollPane(table);
        scrollPane1.setBounds(15, 50, 600, 150);
        Border border2 = BorderFactory.createLineBorder(Color.GRAY, 2);// 검은색 테두리, 두께 2
        scrollPane1.setBorder(border2);

        String imagePath = "./image/resized.png";
        ImageIcon snuUi = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(snuUi);
        imageLabel.setBounds(80,-10, 100,100);
        panel1.add(imageLabel);

        panel2.add(scrollPane);
        panel4.add(scrollPane1);

    }

    void button() {

        categoryComboBox= new JComboBox<>();
        categoryComboBox.addItem("카테고리를 선택하세요");
        categoryComboBox.addItem("담당교수명");
        categoryComboBox.setBounds(70, 20, 150, 24);
        categoryComboBox.setFont(new Font("나눔고딕", Font.PLAIN, 12));

        majorNameComboBox = new JComboBox<>();
        majorNameComboBox.addItem("개설학과");
        majorNameComboBox.setBounds(186, 70, 100, 24);
        majorNameComboBox.setFont(new Font("나눔고딕", Font.PLAIN, 12));

        String[] semester = {"개설학기","1학기", "2학기"};
        semesterComboBox = new JComboBox<>(semester);
        semesterComboBox.setBounds(186, 120, 100, 24);
        semesterComboBox.setFont(new Font("나눔고딕", Font.PLAIN, 12));

        profNameComboBox = new JComboBox<>();
        profNameComboBox.addItem("담당교수");
        profNameComboBox.setBounds(186, 170, 100, 24);
        profNameComboBox.setFont(new Font("나눔고딕", Font.PLAIN, 12));

        yearComboBox = new JComboBox<>();
        yearComboBox.addItem("개설년도");
        yearComboBox.setBounds(412, 25, 100, 24);
        yearComboBox.setFont(new Font("나눔고딕", Font.PLAIN, 12));

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        // 당해년도부터 2년 전까지의 연도 추가
        for (int i = currentYear - 2; i <= currentYear; i++) {
            yearComboBox.addItem(String.valueOf(i));
        }

        // 2년 후의 연도 추가
        for (int i = currentYear + 1; i <= currentYear + 2; i++) {
            yearComboBox.addItem(String.valueOf(i));
        }

        String[] grade = {"개설학년","1학년", "2학년", "3학년", "4학년"};
        gradeCombobox = new JComboBox<>(grade);
        gradeCombobox.setBounds(412, 73, 100, 24);
        gradeCombobox.setFont(new Font("나눔고딕", Font.PLAIN, 12));

        textPane1.add(majorNameComboBox);
        textPane1.add(semesterComboBox);
        textPane1.add(profNameComboBox);
        textPane1.add(yearComboBox);
        textPane1.add(gradeCombobox);

        JTextField textField = new JTextField();
        textField.setBounds(260, 20, 150, 24);

        JButton btn1 = new JButton("조회");
        btn1.setBounds(450, 20, 100, 24);
        btn1.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedcategory = (String) categoryComboBox.getSelectedItem();
                String text = textField.getText();

                if (selectedcategory.equals("담당교수명")) {
                    userDAO.categorySearchByProfName(text);
                }

            }
        });

        JButton btn2 = new JButton("등록");
        btn2.setBounds(40, 30, 100, 50);
        btn2.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String subjectName = subjectNameText.getText();
                String majorName = (String) majorNameComboBox.getSelectedItem();
                String year = (String) yearComboBox.getSelectedItem();
                String grade = (String)  gradeCombobox.getSelectedItem();
                String semester = (String)  semesterComboBox.getSelectedItem();
                String hour = hourText.getText();
                String profName =  (String) profNameComboBox.getSelectedItem();
                String credit = creditText.getText();

                userDAO.subjectDataInsert(subjectName, majorName, year, grade, semester, hour, profName, credit);

            }
        });

        JButton btn3 = new JButton("수정");
        btn3.setBounds(200, 30, 100, 50);
        btn3.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String subjectName = subjectNameText.getText();
                String majorName = (String) majorNameComboBox.getSelectedItem();
                String year = (String) yearComboBox.getSelectedItem();
                String grade = (String)  gradeCombobox.getSelectedItem();
                String semester = (String)  semesterComboBox.getSelectedItem();
                String hour = hourText.getText();
                String profName =  (String) profNameComboBox.getSelectedItem();
                String credit = creditText.getText();

                userDAO.updateSubjectInfo(subjectName, majorName, year, grade, semester, hour, profName, credit);
            }
        });

        JButton btn4 = new JButton("삭제");
        btn4.setBounds(360, 30, 100, 50);
        btn4.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subjectName = subjectNameText.getText();

                userDAO.deleteSubjectInfo(subjectName);

            }
        });


        JButton btn5 = new JButton("종료");
        btn5.setBounds(500, 30, 100, 50);
        btn5.setFont(new Font("나눔고딕", Font.BOLD, 12));

        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel3.add(categoryComboBox);
        panel3.add(textField);
        panel3.add(btn1);
        panel5.add(btn2);
        panel5.add(btn3);
        panel5.add(btn4);
        panel5.add(btn5);
    }

    public static void main(String[] args) throws BadLocationException, SQLException {
        new SubjectForm();
    }
}
