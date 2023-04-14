package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bus.Bus_NhanVien;
import bus.Bus_TaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;

public class FrmManHinhChinh extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnHome, btnPhong, btnTang, btnDichVu, btnDatPhong, btnThanhToan, btnDangXuat, btnDoiMK,
			btnNhanVien;
	public static JButton btnTaiKhoan;
	private JTextField txtMaNV, txtHoTenNV;

	private JPanel pnLeft;
	private JPanel pnBorder;
	private JPanel pnHome;
	private JPanel pnPhong;
	private JPanel pnTaiKhoan;

	private TaiKhoan TaiKhoanlogin;
	private Bus_NhanVien nhanVien_bus = new Bus_NhanVien();
	private Bus_TaiKhoan taiKhoan_bus = new Bus_TaiKhoan();
	private NhanVien nhanVienLogin;
	private JPanel pnQLNhanVien;

	private FrmNhanVien frmNhanVien = new FrmNhanVien();
	private FrmTaiKhoan frmTaiKhoan = new FrmTaiKhoan();

	public FrmManHinhChinh(TaiKhoan taiKhoan) {
		TaiKhoanlogin = taiKhoan;
		setTitle("QUẢN LÝ KHÁCH SẠN");
		setSize(1200, 700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon("img/logo.png");
		setIconImage(icon.getImage());

		taoMenuTrai();
		taoPanelThongTinNhanVien();
		taoSuKienChoButtonMenuTrai();
		taoPanelMain();

		btnDangXuat.addActionListener(this);
		btnDoiMK.addActionListener(this);
	}

	public void taoMenuTrai() {
		pnBorder = new JPanel();
		add(pnBorder, BorderLayout.CENTER);
		pnBorder.setLayout(new BorderLayout());

		pnLeft = new JPanel();
		pnLeft.setLayout(new BorderLayout());
		pnBorder.add(pnLeft, BorderLayout.WEST);
		pnLeft.setBackground(new Color(39, 60, 117));

		btnHome = new JButton("     Home     ");
		btnNhanVien = new JButton("QL Nhân Viên");
		btnPhong = new JButton("     Phòng    ");
		btnTang = new JButton("      Tầng      ");
		btnDichVu = new JButton("   Dịch Vụ    ");
		btnDatPhong = new JButton(" Đặt Phòng ");
		btnTaiKhoan = new JButton("Tài Khoản");
		btnThanhToan = new JButton("Thanh Toán");

		pnLeft.add(btnHome, BorderLayout.CENTER);
		pnLeft.add(btnNhanVien, BorderLayout.CENTER);
		pnLeft.add(btnDatPhong, BorderLayout.CENTER);
		pnLeft.add(btnTaiKhoan, BorderLayout.CENTER);
		pnLeft.add(btnThanhToan, BorderLayout.CENTER);

		Box b = Box.createVerticalBox();
		Box bHome = Box.createHorizontalBox();
		bHome.add(btnHome);
		b.add(bHome);
		b.add(Box.createVerticalStrut(5));

		Box bQLNhanVien = Box.createHorizontalBox();
		bQLNhanVien.add(btnNhanVien);
		b.add(bQLNhanVien);
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

		Box bTaiKhoan = Box.createHorizontalBox();
		bTaiKhoan.add(btnTaiKhoan);
		b.add(bTaiKhoan);
		b.add(Box.createVerticalStrut(5));

		Box bDatPhong = Box.createHorizontalBox();
		bDatPhong.add(btnDatPhong);
		b.add(bDatPhong);
		b.add(Box.createVerticalStrut(5));

		Box bThanhToan = Box.createHorizontalBox();
		bThanhToan.add(btnThanhToan);
		b.add(bThanhToan);
		pnLeft.add(b, BorderLayout.NORTH);
	}

	private void taoPanelThongTinNhanVien() {
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

		nhanVienLogin = nhanVien_bus.getTheoMaNV(TaiKhoanlogin.getMaTK());
		txtMaNV.setText(nhanVienLogin.getMaNV());
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
		txtHoTenNV.setText(nhanVienLogin.getHoTen());
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
	}

	public void taoPanelMain() {
		pnHome = new JPanel();
		JLabel lblHome = new JLabel(new ImageIcon("img/home.png"));
		pnHome.add(lblHome);

		pnPhong = new JPanel();
		JLabel lblPhong = new JLabel(new ImageIcon("img/bg.png"));
		pnPhong.add(lblPhong);

		pnQLNhanVien = new JPanel();
		pnQLNhanVien.add(frmNhanVien.contentPane);
		
		pnTaiKhoan = new JPanel();
		pnTaiKhoan.add(frmTaiKhoan.contentPane);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new CardLayout());
		mainPanel.add(pnHome, "Panel Home");
		mainPanel.add(pnPhong, "Panel Phong");
		mainPanel.add(pnQLNhanVien, "pnQL NhanVien");
		mainPanel.add(pnTaiKhoan, "Panel TaiKhoan");

		pnBorder.add(mainPanel, BorderLayout.CENTER);
	}

	public void taoSuKienChoButtonMenuTrai() {
		btnHome.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnHome.getParent().getLayout());
				c.show(pnHome.getParent(), "Panel Home");
			}
		});

		btnNhanVien.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnQLNhanVien.getParent().getLayout());
				c.show(pnQLNhanVien.getParent(), "pnQL NhanVien");
			}
		});

		btnPhong.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnPhong.getParent().getLayout());
				c.show(pnPhong.getParent(), "Panel Phong");
			}
		});
		
		btnTaiKhoan.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnTaiKhoan.getParent().getLayout());
				c.show(pnTaiKhoan.getParent(), "Panel TaiKhoan");
			}
		});

	}

	public static void main(String[] args) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDangXuat)) {
			if (JOptionPane.showConfirmDialog(this, "Đăng xuất?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				setVisible(false);
				new FrmLogin().setVisible(true);
			}
		}
		if (o.equals(btnDoiMK)) {
			String matKhauCu = JOptionPane.showInputDialog("Nhập mật khẩu hiện tại");
			if (matKhauCu.equals(TaiKhoanlogin.getPass())) {
				String matKhauMoi = JOptionPane.showInputDialog("Nhập mật khẩu mới");
				if (JOptionPane.showConfirmDialog(this, "Bạn muốn đổi mật khẩu mới", "Thông báo",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					TaiKhoan tkMoi = new TaiKhoan(TaiKhoanlogin.getMaTK(), matKhauMoi,
							TaiKhoanlogin.getQuyen());
					taiKhoan_bus.capNhat(tkMoi);
				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Sai mật khẩu!!!");
			}

		}

	}
}
