package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
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
	private JButton btnHome, btnPhong, btnDichVu, btnDangXuat, btnDoiMK,btnNhanVien,btnBaoCao,selectedButton;
	public static JButton btnTaiKhoan;
	private JTextField txtMaNV, txtHoTenNV;
	private Color bgColor = new Color(255, 255, 255);
	
	private JPanel pnLeft;
	private JPanel pnBorder;
	private JPanel pnHome;
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
		pnLeft.setLayout(null);
		pnBorder.add(pnLeft, BorderLayout.WEST);
		pnLeft.setBackground(new Color(39, 60, 117));

		btnHome = new JButton("Home");
		btnPhong = new JButton("Sơ đồ phòng");
		btnNhanVien = new JButton("Nhân Viên");
		btnTaiKhoan = new JButton("Tài Khoản");
		btnDichVu = new JButton("Dịch Vụ");
		btnBaoCao=new JButton("Báo Cáo");

		// set button styles
		Font font = new Font("Arial", Font.PLAIN, 16);
		Dimension size = new Dimension(150, 40);
		
		Color fgColor = new Color(39, 60, 117);

		btnHome.setFont(font);
		btnHome.setPreferredSize(size);
		btnHome.setBackground(bgColor);
		btnHome.setForeground(fgColor);

		btnNhanVien.setFont(font);
		btnNhanVien.setPreferredSize(size);
		btnNhanVien.setBackground(bgColor);
		btnNhanVien.setForeground(fgColor);

		btnPhong.setFont(font);
		btnPhong.setPreferredSize(size);
		btnPhong.setBackground(bgColor);
		btnPhong.setForeground(fgColor);

		btnTaiKhoan.setFont(font);
		btnTaiKhoan.setPreferredSize(size);
		btnTaiKhoan.setBackground(bgColor);
		btnTaiKhoan.setForeground(fgColor);

		btnDichVu.setFont(font);
		btnDichVu.setPreferredSize(size);
		btnDichVu.setBackground(bgColor);
		btnDichVu.setForeground(fgColor);

		btnBaoCao.setFont(font);
		btnBaoCao.setPreferredSize(size);
		btnBaoCao.setBackground(bgColor);
		btnBaoCao.setForeground(fgColor);

		// add icons to buttons
		Icon homeIcon = new ImageIcon("img/home1.png");
		Image homeImage=((ImageIcon) homeIcon).getImage();
		Image newHomeImage=homeImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Icon newHomeIcon=new ImageIcon(newHomeImage);
		Icon nhanVienIcon = new ImageIcon("img/nhanvien.png");
		Image nvImage=((ImageIcon) nhanVienIcon).getImage();
		Image newNVImg=nvImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		Icon newNVIcon=new ImageIcon(newNVImg);
		Icon datPhongIcon = new ImageIcon("img/datphong.png");
		Image dpimg=((ImageIcon) datPhongIcon).getImage();
		Image newdpimg=dpimg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		Icon newdpicon=new ImageIcon(newdpimg);
		Icon phongIcon = new ImageIcon("img/phong.png");
		Image pimg=((ImageIcon) phongIcon).getImage();
		Image newpimg=pimg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		Icon newpicon=new ImageIcon(newpimg);
		Icon taiKhoanIcon = new ImageIcon("img/taikhoan.png");
		Image tkimg=((ImageIcon) taiKhoanIcon).getImage();
		Image newtkimg=tkimg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		Icon newtkicon=new ImageIcon(newtkimg);
		Icon dichVuIcon = new ImageIcon("img/dichvu.png");
		Image dvimg=((ImageIcon) dichVuIcon).getImage();
		Image newdvimg=dvimg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		Icon newdvicon=new ImageIcon(newdvimg);
		Icon baoCaoIcon = new ImageIcon("img/baocao.png");
		Image bcimg=((ImageIcon) baoCaoIcon).getImage();
		Image newbcimg=bcimg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Icon newbcicon=new ImageIcon(newbcimg);

		btnHome.setIcon(newHomeIcon);
		btnNhanVien.setIcon(newNVIcon);
		btnPhong.setIcon(newpicon);
		btnTaiKhoan.setIcon(newtkicon);
		btnDichVu.setIcon(newdvicon);
		btnBaoCao.setIcon(newbcicon);

		pnLeft.add(btnHome);
		pnLeft.add(btnPhong);
		pnLeft.add(btnNhanVien);
		pnLeft.add(btnTaiKhoan);
		pnLeft.add(btnDichVu);
		pnLeft.add(btnBaoCao);
		
		btnHome.setBounds(25,20,150,40);
		btnPhong.setBounds(25,70,150,40);
		btnNhanVien.setBounds(25,170,150,40);
		btnTaiKhoan.setBounds(25,220,150,40);
		btnDichVu.setBounds(25,270,150,40);
		btnBaoCao.setBounds(25,320,150,40);
		pnLeft.setPreferredSize(new Dimension(200,700));

	}

	private void taoPanelThongTinNhanVien() {
		Box bNhanVien = Box.createVerticalBox();
		pnLeft.add(bNhanVien);

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
		bNhanVien.setBounds(10,550,180,100);
	}

	public void taoPanelMain() {
		pnHome = new JPanel();
		JLabel lblHome = new JLabel(new ImageIcon("img/home3.png"));
		pnHome.add(lblHome);

		pnQLNhanVien = new JPanel();
		pnQLNhanVien.add(frmNhanVien.contentPane);
		
		pnTaiKhoan = new JPanel();
		pnTaiKhoan.add(frmTaiKhoan.contentPane);
		
		
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
				if(selectedButton!=null) {
					selectedButton.setBackground(bgColor);
				}
				selectedButton=btnHome;
				selectedButton.setBackground(Color.green);
			}
		});

		btnNhanVien.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnQLNhanVien.getParent().getLayout());
				c.show(pnQLNhanVien.getParent(), "pnQL NhanVien");
				if(selectedButton!=null) {
					selectedButton.setBackground(bgColor);
				}
				selectedButton=btnNhanVien;
				selectedButton.setBackground(Color.green);
			}
		});
		
		btnPhong.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnPhong.getParent().getLayout());
				c.show(pnPhong.getParent(), "Panel Phong");
				if(selectedButton!=null) {
					selectedButton.setBackground(bgColor);
				}
				selectedButton=btnPhong;
				selectedButton.setBackground(Color.green);
			}
		});
		
		btnDichVu.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnDichVu.getParent().getLayout());
				c.show(pnDichVu.getParent(), "Panel DichVu");
				if(selectedButton!=null) {
					selectedButton.setBackground(bgColor);
				}
				selectedButton=btnDichVu;
				selectedButton.setBackground(Color.green);
			}
		});
		
		btnTaiKhoan.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnTaiKhoan.getParent().getLayout());
				c.show(pnTaiKhoan.getParent(), "Panel TaiKhoan");
				if(selectedButton!=null) {
					selectedButton.setBackground(bgColor);
				}
				selectedButton=btnTaiKhoan;
				selectedButton.setBackground(Color.green);
			}
		});

		
		btnBaoCao.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				CardLayout c = (CardLayout) (pnBaoCao.getParent().getLayout());
				c.show(pnBaoCao.getParent(), "Panel BaoCao");
				if(selectedButton!=null) {
					selectedButton.setBackground(bgColor);
				}
				selectedButton=btnBaoCao;
				selectedButton.setBackground(Color.green);
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
