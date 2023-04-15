package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import entity.NhanVien;

public class GUI_QLKhachSan extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnHome, btnPhong, btnTang, btnDichVu, btnDatPhong, btnThanhToan, btnDangXuat, btnDoiMK;
	private JTextField txtMaNV, txtHoTenNV;

	private NhanVien nvlogin;

	public GUI_QLKhachSan(NhanVien nv) {
		nvlogin = nv;
		setTitle("Quan ly khach san");
		setSize(1000, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		createGUI();

		btnHome.addActionListener(this);
		btnDangXuat.addActionListener(this);
	}

	private void createGUI() {
		JPanel pnBorder = new JPanel();
		add(pnBorder, BorderLayout.CENTER);
		pnBorder.setLayout(new BorderLayout());

		JPanel pnLeft = new JPanel();
		pnLeft.setLayout(new BorderLayout());
		pnBorder.add(pnLeft, BorderLayout.WEST);
		pnLeft.setBackground(new Color(19, 15, 64));

		btnHome = new JButton("     Home     ");
		btnPhong = new JButton("     Phòng    ");
		btnTang = new JButton("      Tầng      ");
		btnDichVu = new JButton("   Dịch Vụ    ");
		btnDatPhong = new JButton(" Đặt Phòng ");
		btnThanhToan = new JButton("Thanh Toán");

		pnLeft.add(btnHome, BorderLayout.CENTER);

		pnLeft.add(btnDatPhong, BorderLayout.CENTER);
		pnLeft.add(btnThanhToan, BorderLayout.CENTER);

		Box b = Box.createVerticalBox();
		Box bHome = Box.createHorizontalBox();
		bHome.add(btnHome);
		b.add(bHome);
		b.add(Box.createVerticalStrut(5));

		Box bPhong = Box.createHorizontalBox();
		bPhong.add(btnPhong);
		b.add(bPhong);
		b.add(Box.createVerticalStrut(5));

		Box bTang = Box.createHorizontalBox();
		bTang.add(btnTang);
		b.add(bTang);
		b.add(Box.createVerticalStrut(5));

		Box bDV = Box.createHorizontalBox();
		bDV.add(btnDichVu);
		b.add(bDV);
		b.add(Box.createVerticalStrut(5));

		Box bDatPhong = Box.createHorizontalBox();
		bDatPhong.add(btnDatPhong);
		b.add(bDatPhong);
		b.add(Box.createVerticalStrut(5));

		Box bThanhToan = Box.createHorizontalBox();
		bThanhToan.add(btnThanhToan);
		b.add(bThanhToan);
		pnLeft.add(b, BorderLayout.NORTH);

		Box bNhanVien = Box.createVerticalBox();
		pnLeft.add(bNhanVien, BorderLayout.SOUTH);

		Box bTitle = Box.createHorizontalBox();
		bNhanVien.add(bTitle);
		JLabel lbltile = new JLabel("Nhân viên");
		lbltile.setForeground(Color.white);
		lbltile.setFont(new Font("Arial", Font.BOLD, 18));
		bTitle.add(lbltile);

		JLabel lblMaNV = new JLabel("Mã NV: ");
		lblMaNV.setForeground(Color.white);
		txtMaNV = new JTextField();
		txtMaNV.setPreferredSize(new Dimension(100, 25));
		txtMaNV.setText(nvlogin.getMaNV());
		txtMaNV.setEditable(false);
		txtMaNV.setBorder(null);

		Box bMaNV = Box.createHorizontalBox();
		bNhanVien.add(bMaNV);
		bMaNV.add(lblMaNV);
		bMaNV.add(txtMaNV);

		bNhanVien.add(Box.createVerticalStrut(2));

		JLabel lblHoTenNV = new JLabel("Họ tên: ");
		lblHoTenNV.setForeground(Color.white);
		txtHoTenNV = new JTextField();
		txtHoTenNV.setPreferredSize(new Dimension(100, 25));
		txtHoTenNV.setText(nvlogin.getHoTen());
		txtHoTenNV.setEditable(false);
		txtHoTenNV.setBorder(null);

		Box bHoTenNV = Box.createHorizontalBox();
		bNhanVien.add(bHoTenNV);
		bHoTenNV.add(lblHoTenNV);
		bHoTenNV.add(txtHoTenNV);
		lblMaNV.setPreferredSize(lblHoTenNV.getPreferredSize());

		bNhanVien.add(Box.createVerticalStrut(2));

		btnDangXuat = new JButton("Đăng xuất");
		btnDoiMK = new JButton("Đổi MK");
		Box bDangXuat = Box.createHorizontalBox();
		bNhanVien.add(bDangXuat);
		bDangXuat.add(btnDangXuat);
		bDangXuat.add(btnDoiMK);

		JLabel lblCenter = new JLabel();
		pnBorder.add(lblCenter, BorderLayout.CENTER);
		lblCenter.setIcon(new ImageIcon("img/home.png"));

	}

	public static void main(String[] args) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDangXuat)) {
			if(JOptionPane.showConfirmDialog(this, "Đăng xuất?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				setVisible(false);
				
				new GUI_Login().setVisible(true);
			}
		}
		if (o.equals(btnHome)) {
			new GUI_QLKhachSan(nvlogin).setVisible(true);
			setVisible(false);
		}

	}
}
