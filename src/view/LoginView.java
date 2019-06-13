package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import model.map.FileIO;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JDialog{
	
	
	private JLabel lblId;
	private JLabel lblPw;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton correctbutton;
	private JLabel lblLogin;
	private FileIO fileio=new FileIO();
	private JLabel annountext;
	private JTextArea checkpw;
	private boolean flag=false;
	public LoginView() {
		getContentPane().setLayout(null);
		this.setTitle("로그인 화면");

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
		
		annountext = new JLabel(" id\uC640 pw\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		annountext.setBounds(157, 61, 296, 18);
		getContentPane().add(annountext);
		checkpw= new JTextArea(6, 24);
		
		correctbutton = new JButton("\uD655\uC778");
		correctbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkpw.append(passwordField.getText());
				System.out.println(checkpw.getText());
				
				if(fileio.logIn(textField.getText(),checkpw.getText())) {
					flag=true;
					dispose();//해당 프레임을 종료하는 메소드
					  
					}else {
						textField.setText("");
						passwordField.setText("");
						annountext.setText("로그인에 실패하였습니다. 다시 입력해주세요.");
					}
					
				checkpw.setText("");
			}
		});
		correctbutton.setBounds(192, 260, 105, 27);
		getContentPane().add(correctbutton);
		
		lblLogin = new JLabel("* Login *");
		lblLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblLogin.setBounds(203, 26, 105, 30);
		getContentPane().add(lblLogin);
		
		
		this.setSize(500,400);
		this.setModal(true);
		this.setVisible(true);
	}
	public boolean getFlag() {
		return this.flag;
	}
}
