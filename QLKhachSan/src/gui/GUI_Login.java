package gui;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bus.Bus_TaiKhoan;
import entity.TaiKhoan;

public class GUI_Login extends JFrame implements ActionListener {
	private Bus_TaiKhoan bus_tk = new Bus_TaiKhoan();
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnReset;

	public GUI_Login() {
		setTitle("System Login");
		setSize(400, 300);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		createFormLogin();

	}

	private void createFormLogin() {
		JPanel pnBorder = new JPanel();
		add(pnBorder, BorderLayout.CENTER);

		JLabel background = new JLabel(new ImageIcon("img/bg.png"));
		pnBorder.add(background);
		background.setLayout(null);
		JLabel lblLogin = new JLabel("System Login");
		background.add(lblLogin);
		lblLogin.setFont(new Font("Arial", Font.BOLD, 30));
		lblLogin.setBounds(150, 10, 300, 30);

		JLabel lblUser, lblPass;
		lblUser = new JLabel("UserName:");
		lblUser.setFont(new Font("Arial", Font.BOLD, 20));
		lblPass = new JLabel("Password:");
		lblPass.setFont(new Font("Arial", Font.BOLD, 20));
		txtUserName = new JTextField();
		txtPassword = new JPasswordField();

		btnLogin = new JButton("Đăng nhập");
		btnReset = new JButton("Reset");

		background.add(lblUser);
		background.add(lblPass);
		background.add(txtUserName);
		background.add(txtPassword);
		background.add(btnLogin);
		background.add(btnReset);

		lblUser.setBounds(70, 80, 110, 30);
		txtUserName.setBounds(180, 80, 250, 30);
		txtUserName.setBorder(BorderFactory.createBevelBorder(10));
		lblPass.setBounds(70, 125, 110, 30);
		txtPassword.setBounds(180, 125, 250, 30);
		txtPassword.setBorder(BorderFactory.createBevelBorder(10));
		btnLogin.setBounds(180, 170, 100, 30);
		btnReset.setBounds(330, 170, 100, 30);

		btnLogin.addActionListener(this);
		btnReset.addActionListener(this);
		
		setVisible(true);
	}

	private boolean checkLogin() {
		bus_tk = new Bus_TaiKhoan();
		ArrayList<TaiKhoan> dsTK = bus_tk.getAllTaiKhoan();

		String user = txtUserName.getText().trim();
		String pass = new String(txtPassword.getPassword());

		StringBuilder sb = new StringBuilder();
		if (user.equals("")) {
			sb.append("Username is empty \n");
		}

		if (pass.equals("")) {
			sb.append("Password is empty \n");
		}

		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(this, sb.toString(), "Cảnh báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		for (TaiKhoan tk : dsTK) {
			if (user.equals(tk.getMaNV())) {
				if (pass.equals(tk.getPass())) {
					setVisible(false);
					new GUI_QLKhachSan().setVisible(true);
					return true;
				} else {
					JOptionPane.showMessageDialog(this, "Nhập sai mật khẩu", "Nhập lại", JOptionPane.ERROR_MESSAGE);
					txtPassword.requestFocus();
					return false;
				}
			}
		}
		JOptionPane.showMessageDialog(this, "Nhập sai thông tin", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
		txtUserName.requestFocus();
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLogin)) {
			checkLogin();
		}
		if (o.equals(btnReset)) {
			txtUserName.setText("");
			txtPassword.setText("");
			txtUserName.requestFocus();
		}

	}
	
	public static void main(String[] args) {
		new GUI_Login();
	}

}