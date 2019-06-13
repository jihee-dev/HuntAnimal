package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinView extends JDialog{
	
	
	private JLabel lblId;
	private JLabel lblPw;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton correctbutton;
	private JLabel lblJoinUs;

	public JoinView() {
		getContentPane().setLayout(null);
		this.setTitle("회원가입 화면");
		lblId = new JLabel("ID");
		lblId.setFont(new Font("굴림", Font.BOLD, 25));
		lblId.setBounds(94, 84, 41, 30);
		getContentPane().add(lblId);
		
		lblPw = new JLabel("PW");
		lblPw.setFont(new Font("굴림", Font.BOLD, 25));
		lblPw.setBounds(81, 169, 62, 30);
		getContentPane().add(lblPw);
		
		textField = new JTextField();
		textField.setBounds(157, 91, 219, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(157, 169, 219, 24);
		getContentPane().add(passwordField);
		
		correctbutton = new JButton("\uD655\uC778");
		correctbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();//해당 프레임을 종료하는 메소드
			}
		});
		correctbutton.setBounds(192, 260, 105, 27);
		getContentPane().add(correctbutton);
		
		lblJoinUs = new JLabel("* Join Us *");
		lblJoinUs.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblJoinUs.setBounds(167, 29, 113, 18);
		getContentPane().add(lblJoinUs);
		
		this.setSize(500,400);
		this.setModal(true);
		this.setVisible(true);
	}

	
}

