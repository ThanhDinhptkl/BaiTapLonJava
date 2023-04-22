package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.Dao_Phong;
import entity.Phong;

public class FrmTraPhong extends JFrame implements WindowListener,ActionListener,ItemListener{
	private static final long serialVersionUID = 1L;
	private JLabel lblMaPH,lblTenPH,lblLoaiPH,lblMaTang,lblGhiChu,lblTimeNhan,lblTimeTra,lblTienDV,lblTienPH,lblChuyenPH,lblPhuThu,lblGiamTru,lblTongTien,lblDatcoc,lblKhachThanhToan,lblKhachNo;
	private JTextField txtMaPH,txtTenPH,txtGhiChu,txtTienDV,txtTienPH,txtChuyenPH,txtPhuThu,txtGiamTru,txtTongTien,txtDatcoc,txtKhachThanhToan,txtKhachNo;
	private JButton bnTraPhong,bnTraPhongvsIn,bnHuy;
	private JDateChooser dateNgayNhan,dateNgayTra;
	private JTable tableKhachHang;
	private DefaultTableModel modelKhachHang;
	private JTable tableDichVu;
	private DefaultTableModel modelDichVu;
	private static JPanel pnContent;
	private JComboBox<String> cbTang,cbLoaiPhong,cbMaPH;
	private static final int NUM_ROOMS = 30;
	private Dao_Phong phong_dao = new Dao_Phong();
	
	public FrmTraPhong() {
		setTitle("Trả Phòng");
		setSize(1050, 780);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		createGui();
		addWindowListener(this);
	}
	public void createGui() {
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		pnBorder.setBorder(BorderFactory.createTitledBorder("Quản lý trả phòng"));
		//
		JPanel pnNorth = new JPanel(new BorderLayout());
		pnNorth.setPreferredSize(new Dimension(950, 300));
		//Thông tin phòng thuê
		JPanel pnPhongThue = new JPanel(new GridLayout(7,2,5,5));
		pnPhongThue.setBorder(BorderFactory.createTitledBorder("Thông tin phòng thuê"));
		
		pnPhongThue.add(lblMaPH = new JLabel("Mã phòng"));
		pnPhongThue.add(cbMaPH = new JComboBox<String>());
		getDSphong();
		pnPhongThue.add(lblTenPH = new JLabel("Tên Phòng"));
		pnPhongThue.add(txtTenPH = new JTextField());
		pnPhongThue.add(lblLoaiPH = new JLabel("Loại Phòng"));
		pnPhongThue.add(cbLoaiPhong = new JComboBox<>());
		cbLoaiPhong.addItem("Chọn loại Phòng");
		cbLoaiPhong.addItem("1 giường đơn");
		cbLoaiPhong.addItem("2 giường đơn");
		cbLoaiPhong.addItem("1 giường đôi");
		pnPhongThue.add(lblMaTang = new JLabel("Tầng"));
		pnPhongThue.add(cbTang = new JComboBox<>());
		cbTang.addItem("Chọn tầng");
		cbTang.addItem("Tầng 1");
		cbTang.addItem("Tầng 2");
		cbTang.addItem("Tầng 3");
		cbTang.addItem("Tầng 4");
		cbTang.addItem("Tầng 5");
		pnPhongThue.add(lblTimeNhan = new JLabel("Ngày nhận phòng"));
		pnPhongThue.add(dateNgayNhan = new JDateChooser());
		pnPhongThue.add(lblTimeTra = new JLabel("Ngày trả phòng"));
		pnPhongThue.add(dateNgayTra = new JDateChooser());
		pnPhongThue.add(lblGhiChu = new JLabel("Ghi chú"));
		pnPhongThue.add(txtGhiChu = new JTextField());
		pnPhongThue.setPreferredSize(new Dimension(450,300));
		pnNorth.add(pnPhongThue,BorderLayout.WEST);
		//Thông tin trả tiền
		JPanel pnTienTra = new JPanel(new GridLayout(9, 2, 5,5));
		pnTienTra.setBorder(BorderFactory.createTitledBorder("Chi phí thuê phòng"));
		pnTienTra.add(lblTienDV = new JLabel("Tiền dịch vụ"));
		pnTienTra.add(txtTienDV = new JTextField());
		pnTienTra.add(lblTienDV = new JLabel("Tiền phòng"));
		pnTienTra.add(txtTienPH = new JTextField());
		pnTienTra.add(lblChuyenPH = new JLabel("Chuyển phòng"));
		pnTienTra.add(txtChuyenPH = new JTextField());
		pnTienTra.add(lblPhuThu = new JLabel("Phụ thu"));
		pnTienTra.add(txtPhuThu = new JTextField());
		pnTienTra.add(lblGiamTru = new JLabel("Giảm trừ"));
		pnTienTra.add(txtGiamTru = new JTextField());
		pnTienTra.add(lblTongTien = new JLabel("Tổng tiền"));
		pnTienTra.add(txtTongTien = new JTextField());
		pnTienTra.add(lblDatcoc = new JLabel("Đã trả trước"));
		pnTienTra.add(txtDatcoc = new JTextField());
		pnTienTra.add(lblKhachThanhToan = new JLabel("Khách thanh toán"));
		pnTienTra.add(txtKhachThanhToan = new JTextField());
		pnTienTra.add(lblKhachNo = new JLabel("Khách còn thiếu"));
		pnTienTra.add(txtKhachNo = new JTextField());
		pnTienTra.setPreferredSize(new Dimension(500, 300));
		pnNorth.add(pnTienTra,BorderLayout.CENTER);
		pnBorder.add(pnNorth,BorderLayout.NORTH);
		//pnCenter
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		pnBorder.add(pnCenter,BorderLayout.CENTER);
		JSplitPane slitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		//Table KhachHang
		JPanel pnDSKhachHang = new JPanel();
		pnDSKhachHang.setLayout(new BorderLayout());
		pnDSKhachHang.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		String colsKH[]= {"Mã khách hàng","Họ Tên","SĐT","CCCD","Email","Phai","Địa chỉ"};
		modelKhachHang = new DefaultTableModel(colsKH,0);
		tableKhachHang = new JTable(modelKhachHang);
		JScrollPane paneKH = new JScrollPane(tableKhachHang);
		paneKH.setPreferredSize(new Dimension(300,150));
		pnDSKhachHang.add(paneKH);
		slitpane.setTopComponent(pnDSKhachHang);
		//Table DichVu
		JPanel pnDichVu = new JPanel();
		pnDichVu.setLayout(new BorderLayout());
		pnDichVu.setBorder(BorderFactory.createTitledBorder("Thông tin sử dụng dịch vụ"));
		String colsDichVu[] = {"Mã HD","Tên dịch vụ","Số lượng","Đơn giá","Tổng tiền dịch vụ"};
		modelDichVu = new DefaultTableModel(colsDichVu,0);
		tableDichVu = new JTable(modelDichVu);
		JScrollPane paneDichvu = new JScrollPane(tableDichVu);
		paneDichvu.setPreferredSize(new Dimension(300,150));
		pnDichVu.add(paneDichvu);
		slitpane.setBottomComponent(pnDichVu);
		pnCenter.add(slitpane);
		//pnChucNang
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new FlowLayout());
		pnBorder.add(pnBottom,BorderLayout.SOUTH);
		pnBottom.add(bnTraPhong = new JButton("Trả phòng"));
		bnTraPhong.setBackground(Color.BLUE);
		bnTraPhong.setForeground(Color.WHITE);
		pnBottom.add(bnTraPhongvsIn = new JButton("Trả phòng và In"));
		bnTraPhongvsIn.setBackground(Color.GREEN);
		bnTraPhongvsIn.setForeground(Color.WHITE);
		pnBottom.add(bnHuy = new JButton("Hủy"));
		bnHuy.setBackground(Color.RED);
		bnHuy.setForeground(Color.WHITE);
	
		bnTraPhong.addActionListener(this);
		bnTraPhongvsIn.addActionListener(this);
		bnHuy.addActionListener(this);
		cbMaPH.addItemListener(this);
		pnContent = new JPanel();
		pnContent.add(pnBorder);
		add(pnContent);
		cbLoaiPhong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected =cbMaPH.getSelectedItem().toString();
				if(selected.equals("100")) {
				try {
					Connection connec =ConnectDB.getInstance().getConnection(); 
					String sql = "SELECT kh.MAKH ,kh.HOTEN,kh.SDT,kh.CCCD,kh.EMAIL,kh.PHAI,kh.DIACHI,";
				}catch(Exception ex) {
					
				}
				
			}
			}});
	}
	public static void main(String[] args) {
		new FrmTraPhong().setVisible(true);
	}
	public void getDSphong() {
		int n = phong_dao.getAllPhong().size();
		String [] item =new String [n];
		int i =0;
		for(Phong ph : phong_dao.getAllPhong() ) {
			item[i] =ph.getMaPhong();
			i++;
		}
		cbMaPH.setModel(new DefaultComboBoxModel<>(item));
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
		Object o =e.getSource();
		if(o.equals(bnHuy)) {
			int tb =JOptionPane.showConfirmDialog(this,"Bạn có muốn hủy?","Delete",JOptionPane.YES_NO_CANCEL_OPTION);
			if(tb == JOptionPane.YES_OPTION) {
				System.exit(tb);
			}
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		
	}

}
