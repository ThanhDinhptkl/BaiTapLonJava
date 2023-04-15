package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class FrmDatPhong extends JFrame implements WindowListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private JLabel lblMaPH,lblHoTen,lblSdt,lblCCCD,lblEmail,lblPhai,lblDiaChi,lblNhapMa,lblNgayDatPhong,lblNgayNhanPhong,lblNgayTraPhong,lblSoNguoi,lblGhiChu;
	private JTextField txtMaPH,txtHoTen,txtSdt,txtCCCD,txtEmail,txtPhai,txtDiaChi,txtNhapMa,txtSoNguoi,txtGhiChu;
	private JRadioButton rdNam,rdNu,rdKhac;
	private ButtonGroup genderGroup;
	private JButton btnTim,btnDatPhong,btnHuy,btnCoc;
	private JComboBox<String> cbLoaiPhong,cbTang;
	private JTable table;
	private DefaultTableModel tableModel;
	private JDateChooser dateNgayDat,dateNgayNhan,dateNgayTra;
	public FrmDatPhong() {
		super("Đặt Phòng");
		setSize(1050, 700);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);

		createGUI();
		
		addWindowListener(this);
	}
	public void createGUI(){
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
//		pnBorder.setBackground(Color.red);
		pnBorder.setBorder(BorderFactory.createTitledBorder("Quản lý đặt phòng"));
		//add(pnBorder);
		JPanel pnNorth = new JPanel(new BorderLayout());
		pnNorth.setPreferredSize(new Dimension(900, 220));

		// Thêm JPanel chứa thông tin khách hàng bên trái
		JPanel pnCustomer = new JPanel(new GridLayout(7,2,5,5));
		pnCustomer.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		pnCustomer.add(lblMaPH=new JLabel("Mã khách hàng:"));
		pnCustomer.add(txtMaPH=new JTextField());
		pnCustomer.add(lblHoTen=new JLabel("Họ tên khách hàng:"));
		pnCustomer.add(txtHoTen=new JTextField());
		pnCustomer.add(lblSdt=new JLabel("Số điện thoại:"));
		pnCustomer.add(txtSdt=new JTextField());
		pnCustomer.add(lblCCCD=new JLabel("Căn cước công dân:"));
		pnCustomer.add(txtCCCD=new JTextField());
		pnCustomer.add(lblPhai=new JLabel("Phái"));
		JPanel jpPhai=new JPanel();
		jpPhai.add(rdNam=new JRadioButton("Nam"));
		jpPhai.add(rdNu=new JRadioButton("Nữ"));
		genderGroup=new ButtonGroup();
		genderGroup.add(rdNam);
		genderGroup.add(rdNu);
		pnCustomer.add(jpPhai);
		pnCustomer.add(lblEmail=new JLabel("Email:"));
		pnCustomer.add(txtEmail=new JTextField());
		pnCustomer.add(lblDiaChi=new JLabel("Địa chỉ:"));
		pnCustomer.add(txtDiaChi=new JTextField());
		pnCustomer.setPreferredSize(new Dimension(500,220));

		// Thêm JPanel chứa thông tin đặt phòng bên phải
		JPanel pnBooking = new JPanel(new GridLayout(5, 2,10,10));
		pnBooking.setBorder(BorderFactory.createTitledBorder("Thông tin đặt phòng"));
		pnBooking.add(lblNgayDatPhong=new JLabel("Ngày đặt phòng:"));
		pnBooking.add(dateNgayDat=new JDateChooser());
		pnBooking.add(lblNgayNhanPhong=new JLabel("Ngày nhận phòng:"));
		pnBooking.add(dateNgayNhan=new JDateChooser());
		pnBooking.add(lblNgayTraPhong=new JLabel("Ngày trả phòng:"));
		pnBooking.add(dateNgayTra=new JDateChooser());
		pnBooking.add(lblSoNguoi=new JLabel("Số người"));
		pnBooking.add(txtSoNguoi=new JTextField());
		pnBooking.add(lblSoNguoi=new JLabel("Ghi chú"));
		pnBooking.add(txtGhiChu=new JTextField());
		pnNorth.add(pnCustomer, BorderLayout.WEST);
		pnNorth.add(pnBooking, BorderLayout.CENTER);
		
		//JPanel pnDichVu=new JPanel()
		
		pnBorder.add(pnNorth,BorderLayout.NORTH);
		
		JPanel pnCenter=new JPanel();
		pnCenter.setLayout(new BorderLayout());
		JSplitPane splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		JPanel pnTimPhong=new JPanel();
		pnTimPhong.add(cbLoaiPhong=new JComboBox<String>());
		cbLoaiPhong.setPreferredSize(new Dimension(150,20));
		cbLoaiPhong.addItem("Loại phòng");
		pnTimPhong.add(cbTang=new JComboBox<String>());
		cbTang.setPreferredSize(new Dimension(150,20));
		cbTang.addItem("Tầng");
		pnTimPhong.add(lblNhapMa=new JLabel("Nhập mã phòng"));
		pnTimPhong.add(txtNhapMa=new JTextField(20));
		pnTimPhong.add(btnTim=new JButton("Tìm kiếm"));
		//pnCenter.add(pnTimPhong);
		pnTimPhong.setBorder(BorderFactory.createTitledBorder("Tìm phòng trống"));
		splitPane.setTopComponent(pnTimPhong);
		
		String [] cols= {"Mã phòng","Tên phòng","Số giường","Trạng thái","Mã loại phòng","Mã tầng"};
		tableModel=new DefaultTableModel(cols, 0);
		table=new JTable(tableModel);
		JScrollPane pane=new JScrollPane(table);
		splitPane.setBottomComponent(pane);
		pane.setPreferredSize(new Dimension(pane.getPreferredSize().width,300));
		pnCenter.add(splitPane);
		pnBorder.add(pnCenter,BorderLayout.CENTER);
		
		JPanel pnSouth=new JPanel();
		pnSouth.add(btnDatPhong=new JButton("Đặt phòng và thanh toán"));
		pnSouth.add(btnCoc=new JButton("Cọc phòng"));
		pnSouth.add(btnHuy=new JButton("Hủy"));
		pnBorder.add(pnSouth,BorderLayout.SOUTH);
		
		contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);
		
		

	}
	public static void main(String[] args) {
		new FrmDatPhong().setVisible(true);
	}
	@Override
	public void windowOpened(WindowEvent e) {
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		setVisible(false);
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}