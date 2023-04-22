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
	private JButton btnHome, btnPhong, btnDichVu, btnDatPhong, btnDangXuat, btnDoiMK,btnNhanVien,btnBaoCao;

	public static JButton btnTaiKhoan;
	private JTextField txtMaNV, txtHoTenNV;

	private JPanel pnLeft;
	private JPanel pnBorder;
	private JPanel pnHome;
	private JPanel pnDatPhong;
	private JPanel pnDichVu;
	private JPanel pnPhong;
	private JPanel pnTaiKhoan;

	private JPanel pnBaoCao;


	private TaiKhoan TaiKhoanlogin;
	private Bus_NhanVien nhanVien_bus = new Bus_NhanVien();
	private Bus_TaiKhoan taiKhoan_bus = new Bus_TaiKhoan();
	private NhanVien nhanVienLogin;
	private JPanel pnQLNhanVien;

	private FrmNhanVien frmNhanVien = new FrmNhanVien();
	private FrmTaiKhoan frmTaiKhoan = new FrmTaiKhoan();
	private FrmDatPhong frmDatPhong = new FrmDatPhong();
	private FrmQLPhong frmQLPhong = new FrmQLPhong();
	private FrmDichVu frmDichVu = new FrmDichVu();

	private FrmBaoCao frmBaoCao=new FrmBaoCao();

	

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
		btnDatPhong = new JButton(" Đặt Phòng ");
		btnPhong = new JButton("Phòng");
		btnTaiKhoan = new JButton("Tài Khoản");
		btnDichVu = new JButton("Dich Vụ");

		btnBaoCao=new JButton("Báo cáo");


		pnLeft.add(btnHome, BorderLayout.CENTER);
		pnLeft.add(btnNhanVien, BorderLayout.CENTER);
		pnLeft.add(btnDatPhong, BorderLayout.CENTER);
		pnLeft.add(btnTaiKhoan, BorderLayout.CENTER);
		pnLeft.add(btnDichVu, BorderLayout.CENTER);

		pnLeft.add(btnBaoCao,BorderLayout.CENTER);


		Box b = Box.createVerticalBox();
		Box bHome = Box.createHorizontalBox();
		bHome.add(btnHome);
		b.add(bHome);
		b.add(Box.createVerticalStrut(5));

		Box bQLNhanVien = Box.createHorizontalBox();
		bQLNhanVien.add(btnNhanVien);
		b.add(bQLNhanVien);
		b.add(Box.createVerticalStrut(5));
		
		Box bQLPhong = Box.createHorizontalBox();
		bQLPhong.add(btnPhong);
		b.add(bQLPhong);
		b.add(Box.createVerticalStrut(5));

		Box bTaiKhoan = Box.createHorizontalBox();
		bTaiKhoan.add(btnTaiKhoan);
		b.add(bTaiKhoan);
		b.add(Box.createVerticalStrut(5));
		
		Box bDichVu = Box.createHorizontalBox();
		bDichVu.add(btnDichVu);
		b.add(bDichVu);
		b.add(Box.createVerticalStrut(5));

		Box bDatPhong = Box.createHorizontalBox();
		bDatPhong.add(btnDatPhong);
		b.add(bDatPhong);
		b.add(Box.createVerticalStrut(5));

		
		Box bBaoCao = Box.createHorizontalBox();
		bBaoCao.add(btnBaoCao);
		b.add(bBaoCao);
		b.add(Box.createVerticalStrut(5));


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

		pnQLNhanVien = new JPanel();
		pnQLNhanVien.add(frmNhanVien.contentPane);
		
		pnTaiKhoan = new JPanel();
		pnTaiKhoan.add(frmTaiKhoan.contentPane);
		
		pnDatPhong = new JPanel();
		pnDatPhong.add(frmDatPhong.contentPane);
		
		pnPhong = new JPanel();
		pnPhong.add(frmQLPhong.contentPane);
		
		pnDichVu = new JPanel();
		pnDichVu.add(frmDichVu.contentPane);

		
		pnBaoCao=new JPanel();
		pnBaoCao.add(frmBaoCao.contentPane);


		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new CardLayout());
		mainPanel.add(pnHome, "Panel Home");
		mainPanel.add(pnQLNhanVien, "pnQL NhanVien");
		mainPanel.add(pnTaiKhoan, "Panel TaiKhoan");
		mainPanel.add(pnDatPhong, "Panel DatPhong");
		mainPanel.add(pnPhong, "Panel Phong");
		mainPanel.add(pnDichVu, "Panel DichVu");

		mainPanel.add(pnBaoCao,"Panel BaoCao");



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

		
		btnDatPhong.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnDatPhong.getParent().getLayout());
				c.show(pnDatPhong.getParent(), "Panel DatPhong");
			}
		});
		
		btnPhong.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnPhong.getParent().getLayout());
				c.show(pnPhong.getParent(), "Panel Phong");
			}
		});
		
		btnDichVu.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnDichVu.getParent().getLayout());
				c.show(pnDichVu.getParent(), "Panel DichVu");
			}
		});
		
		btnTaiKhoan.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnTaiKhoan.getParent().getLayout());
				c.show(pnTaiKhoan.getParent(), "Panel TaiKhoan");
			}
		});

		
		btnBaoCao.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnBaoCao.getParent().getLayout());
				c.show(pnBaoCao.getParent(), "Panel BaoCao");
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
