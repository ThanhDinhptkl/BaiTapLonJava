package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmDichVu extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private JTextField txtNhap,txtTienKhachDua,txtTienTraLai,txtTongTien;
	private JComboBox<String> cbPhongMua;
	private JLabel lblNhap,lblPhongMua,lblTienKhachDua,lblTienTraLai,lblTongTien;
	private JButton btnTim,btnLuu,btnThoat;
	private JTable tblDichVu,tblGioHang;
	private DefaultTableModel modelDichVu,modelGioHang;
	
	public FrmDichVu() {
		super("Quản lý dịch vụ");
		setSize(1050, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		createGUI();
	}

	public void createGUI() {
		
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
//		pnBorder.setBackground(Color.red);
		pnBorder.setBorder(BorderFactory.createTitledBorder("Danh sách dịch vụ"));
		JPanel pnCenter=new JPanel(new BorderLayout());
		pnCenter.setPreferredSize(new Dimension(900,400));
		
		JPanel pnLeft=new JPanel(new BorderLayout());
		pnLeft.setBorder(BorderFactory.createTitledBorder("Danh sách dịch vụ"));
		JPanel pnLeftNorth=new JPanel();
		pnLeftNorth.add(lblNhap=new JLabel("Nhập tên dịch vụ cần tìm:"));
		pnLeftNorth.add(txtNhap=new JTextField(10));
		pnLeftNorth.add(btnTim=new JButton("Tìm"));
		pnLeft.add(pnLeftNorth,BorderLayout.NORTH);
		
		String[]colsDichVu= {"Tên dịch vụ","Giá","Thêm"};
		modelDichVu=new DefaultTableModel(colsDichVu, 0);
		tblDichVu=new JTable(modelDichVu);
		JScrollPane paneDichVu=new JScrollPane(tblDichVu);
		pnLeft.add(paneDichVu,BorderLayout.CENTER);
		pnLeft.setPreferredSize(new Dimension(400,400));
		pnCenter.add(pnLeft,BorderLayout.WEST);
		
		JPanel pnRight=new JPanel();
		pnRight.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));
		String [] colsGioHang= {"Tên dịch vụ","Số lượng","Đơn vị","Đơn giá","Thành tiền","Số lượng khách đã mua","Xóa"};
		modelGioHang=new DefaultTableModel(colsGioHang,0);
		tblGioHang=new JTable(modelGioHang);
		JScrollPane paneGioHang=new JScrollPane(tblGioHang);
		paneGioHang.setPreferredSize(new Dimension(470,370));
		pnRight.add(paneGioHang);
		pnCenter.add(pnRight,BorderLayout.CENTER);
		
		JPanel pnSouth=new JPanel();
		pnSouth.setLayout(null);
		pnSouth.add(lblPhongMua=new JLabel("Phòng mua:"));
		pnSouth.add(cbPhongMua=new JComboBox<String>());
		cbPhongMua.setPreferredSize(new Dimension(150,30));
		pnSouth.add(lblTongTien=new JLabel("Tổng tiền:"));
		pnSouth.add(txtTongTien=new JTextField(10));
		pnSouth.add(lblTienKhachDua=new JLabel("Tiền khách đưa:"));
		pnSouth.add(txtTienKhachDua=new JTextField(10));
		pnSouth.add(lblTienTraLai=new JLabel("Tiền trả lại:"));
		pnSouth.add(txtTienTraLai=new JTextField(10));
		
		Icon iconLuu = new ImageIcon("img/save.png");
		Image imgLuu = ((ImageIcon)iconLuu).getImage();
		Image newImgLuu = imgLuu.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon newIconLuu = new ImageIcon(newImgLuu);
		pnSouth.add(btnThoat=new JButton("Thoát"));
		pnSouth.add(btnLuu=new JButton("Lưu"));
		btnLuu.setIcon(newIconLuu);
		
		Icon iconThoat = new ImageIcon("img/exit.png");
		Image imgThoat = ((ImageIcon)iconThoat).getImage();
		Image newImgThoat = imgThoat.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon newIconThoat = new ImageIcon(newImgThoat);
		pnSouth.add(btnThoat=new JButton("Thoát"));
		btnThoat.setIcon(newIconThoat);
		pnSouth.setBorder(BorderFactory.createTitledBorder("South"));
		
		lblPhongMua.setBounds(10,10,100,30);
		cbPhongMua.setBounds(120,10,150,30);
		lblTongTien.setBounds(10,50,100,30);
		txtTongTien.setBounds(120,50,150,30);
		lblTienKhachDua.setBounds(10,90,100,30);
		txtTienKhachDua.setBounds(120,90,150,30);
		lblTienTraLai.setBounds(10,130,100,30);
		txtTienTraLai.setBounds(120,130,150,30);
		btnLuu.setBounds(120,170,100,30);
		btnThoat.setBounds(230,170,100,30);
		
		
		pnBorder.add(pnCenter,BorderLayout.NORTH);
		pnBorder.add(pnSouth,BorderLayout.SOUTH);
		pnSouth.setPreferredSize(new Dimension(0, 210));
		
		contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);
	}
	public static void main(String[] args) {
		new FrmDichVu().setVisible(true);
	}
}