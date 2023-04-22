package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class FrmDatPhong extends JFrame implements WindowListener,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private JLabel lblMaKH,lblHoTen,lblSdt,lblCCCD,lblEmail,lblPhai,lblDiaChi,lblNhapMa,lblNgayDatPhong,lblNgayNhanPhong,lblNgayTraPhong,lblSoNguoi,lblGhiChu,lblSoLuong;
	private JTextField txtMaKH,txtHoTen,txtSdt,txtCCCD,txtEmail,txtPhai,txtDiaChi,txtNhapMa,txtSoNguoi,txtGhiChu,txtAnUong,txtGiuXe,txtDonPhong,txtGiatUi,txtMess;
	private JRadioButton rdNam,rdNu;
	private JCheckBox chkAnUong,chkGiuXe,chkDonPhong,chkGiatUi;
	private ButtonGroup genderGroup;
	private JButton btnDatPhong,btnHuy,btnHoanTat,btnXacNhanThongTin,btnRanDom;
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
		pnCustomer.add(lblMaKH=new JLabel("Mã khách hàng:"));
		JPanel jpMaKH = new JPanel(new GridLayout(1,2));
		jpMaKH.add(txtMaKH=new JTextField(12));
		jpMaKH.add(btnRanDom=new JButton("Random"));
		pnCustomer.add(jpMaKH);
		pnCustomer.add(lblHoTen=new JLabel("Họ tên khách hàng:"));
		pnCustomer.add(txtHoTen=new JTextField());
		pnCustomer.add(lblSdt=new JLabel("Số điện thoại:"));
		pnCustomer.add(txtSdt=new JTextField());
		pnCustomer.add(lblCCCD=new JLabel("Căn cước công dân:"));
		pnCustomer.add(txtCCCD=new JTextField());
		pnCustomer.add(lblPhai=new JLabel("Phái:"));
		JPanel jpPhai=new JPanel(new GridLayout(1,2));
		jpPhai.setOpaque(false);
		jpPhai.add(rdNam=new JRadioButton("Nam"));
		jpPhai.add(rdNu=new JRadioButton("Nữ"));
		rdNam.setHorizontalAlignment(SwingConstants.CENTER);
		rdNu.setHorizontalAlignment(SwingConstants.CENTER);
		genderGroup=new ButtonGroup();
		genderGroup.add(rdNam);
		genderGroup.add(rdNu);
		pnCustomer.add(jpPhai);
		pnCustomer.add(lblEmail=new JLabel("Email:"));
		pnCustomer.add(txtEmail=new JTextField());
		pnCustomer.add(lblDiaChi=new JLabel("Địa chỉ:"));
		pnCustomer.add(txtDiaChi=new JTextField());
		//pnCustomer.add(txtMess=new JTextField());
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
		txtMess = new JTextField();
		txtMess.setEditable(false);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 14));
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		pnNorth.add(txtMess,BorderLayout.SOUTH);
		pnBorder.add(pnNorth,BorderLayout.NORTH);
		
		JPanel pnCenter=new JPanel();
		pnCenter.setLayout(new BorderLayout());
		JSplitPane splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		JPanel pnDichVu=new JPanel();
		pnDichVu.setLayout(null);
		pnDichVu.add(chkAnUong=new JCheckBox("Ăn & uống (100.000đ/lượt)"));
		pnDichVu.add(chkGiuXe=new JCheckBox("Giữ xe (10.000đ/lượt)"));
		pnDichVu.add(chkDonPhong=new JCheckBox("Dọn phòng (50.000đ/lượt)"));
		pnDichVu.add(chkGiatUi=new JCheckBox("Giặt ủi (30.000đ/lượt)"));
		pnDichVu.add(lblSoLuong=new JLabel("Nhập số lượng tương ứng:"));
		pnDichVu.add(txtAnUong=new JTextField());
		pnDichVu.add(txtGiuXe=new JTextField());
		pnDichVu.add(txtDonPhong=new JTextField());
		pnDichVu.add(txtGiatUi=new JTextField());
		pnDichVu.add(btnXacNhanThongTin=new JButton("Xác nhận thông tin"));
		
		chkAnUong.setBounds(20,35,200,30);
		chkGiuXe.setBounds(20,70,160,30);
		chkDonPhong.setBounds(340,35,200,30);
		chkGiatUi.setBounds(340,70,160,30);
		lblSoLuong.setBounds(190,05,200,30);
		txtAnUong.setBounds(220,35,100,30);
		txtGiuXe.setBounds(220,70,100,30);
		txtDonPhong.setBounds(530,35,100,30);
		txtGiatUi.setBounds(530,70,100,30);
		btnXacNhanThongTin.setBounds(650,70,200,30);
		pnDichVu.setPreferredSize(new Dimension(900,110));
		pnDichVu.setBorder(BorderFactory.createTitledBorder("Dịch vụ"));
		splitPane.setTopComponent(pnDichVu);
		
		
		
		String [] cols= {"Mã phòng","Tên phòng","Số giường","Trạng thái","Mã loại phòng","Mã tầng"};
		tableModel=new DefaultTableModel(cols, 0);
		table=new JTable(tableModel);
		JScrollPane pane=new JScrollPane(table);
		splitPane.setBottomComponent(pane);
		pane.setPreferredSize(new Dimension(pane.getPreferredSize().width,200));
		pnCenter.add(splitPane);
		pnBorder.add(pnCenter,BorderLayout.CENTER);
		
		JPanel pnSouth=new JPanel();
		pnSouth.add(btnDatPhong=new JButton("Đặt phòng và thanh toán"));
		pnSouth.add(btnHoanTat=new JButton("Hoàn tất đặt phòng"));
		pnSouth.add(btnHuy=new JButton("Hủy"));
		pnBorder.add(pnSouth,BorderLayout.SOUTH);
		
		contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);
		
		btnDatPhong.addActionListener(this);
		btnHoanTat.addActionListener(this);
		btnHuy.addActionListener(this);
		btnXacNhanThongTin.addActionListener(this);
		

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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(btnDatPhong)) {
			
		}else if(o.equals(btnHoanTat)) {
			
		}else if(o.equals(btnHuy)) {
			
		}else if(o.equals(btnXacNhanThongTin)) {
			
		}
		
	}
	
	public boolean validateInput() {
	    // Kiểm tra dữ liệu khách hàng
		String maKH=txtMaKH.getText().trim();
	    String hoTen = txtHoTen.getText().trim();
	    String cccd = txtCCCD.getText().trim();
	    String sdt = txtSdt.getText().trim();
	    if (hoTen.isEmpty() || cccd.isEmpty() || sdt.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin khách hàng.");
	        return false;
	    }
	    
	    // Kiểm tra dữ liệu đặt phòng
	    Date ngayDat = dateNgayDat.getDate();
	    Date ngayNhan = dateNgayNhan.getDate();
	    Date ngayTra = dateNgayTra.getDate();
	    if (ngayDat == null || ngayNhan == null || ngayTra == null) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày đặt, ngày nhận và ngày trả phòng.");
	        return false;
	    }
	    if (ngayNhan.before(ngayDat)) {
	        JOptionPane.showMessageDialog(this, "Ngày nhận phòng phải sau ngày đặt phòng.");
	        return false;
	    }
	    if (ngayTra.before(ngayNhan)) {
	        JOptionPane.showMessageDialog(this, "Ngày trả phòng phải sau ngày nhận phòng.");
	        return false;
	    }
	    
	    // Kiểm tra số người và ghi chú
	    String soNguoiStr = txtSoNguoi.getText().trim();
	    String ghiChu = txtGhiChu.getText().trim();
	    try {
	        int soNguoi = Integer.parseInt(soNguoiStr);
	        if (soNguoi <= 0) {
	            JOptionPane.showMessageDialog(this, "Số người phải là số nguyên dương.");
	            return false;
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Số người phải là số nguyên dương.");
	        return false;
	    }
	    if (ghiChu.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập ghi chú.");
	        return false;
	    }
	    
	    return true;
	}

	    
}