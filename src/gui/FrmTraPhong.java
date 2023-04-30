package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Table;

import com.toedter.calendar.JDateChooser;

import bus.Bus_Phong;
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
	private Bus_Phong phong_dao = new Bus_Phong();
	
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

		JLabel lblTieuDe = new JLabel("TRẢ PHÒNG");
		lblTieuDe.setForeground(Color.blue);
		Font fTieuDe = new Font("Arial", Font.BOLD, 25);
		lblTieuDe.setFont(fTieuDe);
		
		JPanel pnNorth = new JPanel(new BorderLayout());
		pnNorth.add(lblTieuDe, BorderLayout.NORTH);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
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
		String colsKH[]= {"Mã HD","Mã khách hàng","Họ Tên","SĐT","CCCD","Email","Phái","Địa chỉ"};
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
		pnBorder.add(pnBottom,BorderLayout.SOUTH);
		pnBottom.add(bnTraPhong = new JButton("Trả phòng"));
		Icon iconThoat = new ImageIcon("img/random.png");
		Image imgThoat = ((ImageIcon)iconThoat).getImage();
		Image newImgThoat = imgThoat.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon newIconThoat = new ImageIcon(newImgThoat);
		bnTraPhong.setIcon(newIconThoat);
		pnBottom.add(bnTraPhongvsIn = new JButton("Trả phòng và In"));
		Icon iconIn = new ImageIcon("img/wrapper_report.png");
		Image imgIn=((ImageIcon)iconIn).getImage();
		Image newIn=imgIn.getScaledInstance(20, 20,java.awt.Image.SCALE_SMOOTH );
		Icon IconIn= new ImageIcon(newIn);
		bnTraPhongvsIn.setIcon(IconIn);
		pnBottom.add(bnHuy = new JButton("Hủy"));
		Icon iconHuy = new ImageIcon("img/exit.png");
		Image imaHuy=((ImageIcon)iconHuy).getImage();
		Image newIconHuy =imaHuy.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
		Icon newHuy=new ImageIcon(newIconHuy);
		bnHuy.setIcon(newHuy);
	
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
					String sql = "SELECT kh.MAKH ,kh.HOTEN,kh.SDT,kh.CCCD,kh.EMAIL,kh.PHAI,kh.DIACHI,c.MAHD,dv.TENDV,ct.SOLUONG,dv.GIA," +
							"FROM ChiTietDatPhong AS c " +
            	            "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
            	            "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
            	            "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
            	            "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
            	            "JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
            	            "WHERE h.NGAYLAPHD BETWEEN ? AND ?";
					PreparedStatement ps = connec.prepareStatement(sql);
					DefaultTableModel dmKH=(DefaultTableModel) tableKhachHang.getModel();
//					DefaultTableModel dmDV =(DefaultTableModel) tableDichVu.getModel();
            	    dmKH.setRowCount(0);
//            	    dmDV.setRowCount(0);
            	    ResultSet rs = ps.executeQuery();
            	    Object obj[]=new Object[15];
            	    while (rs.next()) {
            	    	obj[0]=rs.getString(1);
            	    	obj[1]=rs.getString(2);
            	    	obj[2]=rs.getString(3);
            	    	obj[3]=rs.getString(4);
            	    	obj[4] =rs.getString(5);
            	    	obj[5] =rs.getString(6);
            	    	obj[6]=rs.getBoolean(7);
            	    	obj[7]=rs.getString(8);
            	    	obj[8]=rs.getString(9);
            	    	obj[9]=rs.getString(10);
            	    	obj[10]=rs.getInt(11);
            	    	obj[11]=rs.getDouble(12);
            	    	obj[12]=rs.getInt(11)*rs.getDouble(12);
            	    }
				}catch (SQLException ex) {
            	    ex.printStackTrace();
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