package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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

import bus.Bus_ChiTietDatPhong;
import bus.Bus_ChiTietSuDungDichVu;
import bus.Bus_DichVu;
import bus.Bus_HoaDon;
import bus.Bus_KhachHang;
import bus.Bus_Phong;
import entity.ChiTietDatPhong;
import entity.ChiTietSuDungDichVu;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Phong;
import random.PhatSinhMa;

public class FrmDatPhong extends JFrame implements WindowListener, ActionListener, ItemListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private JLabel lblMaPH, lblHoTen, lblSdt, lblCCCD, lblEmail, lblPhai, lblDiaChi, lblNhapMa, lblNgayDatPhong,
			lblNgayNhanPhong, lblNgayTraPhong, lblSoNguoi, lblGhiChu;
	private JTextField txtMaPH, txtHoTen, txtCCCD, txtEmail, txtPhai, txtDiaChi, txtNhapMa, txtSoNguoi,
			txtGhiChu;
	private JRadioButton rdNam, rdNu, rdKhac;
	private ButtonGroup genderGroup;
	private JButton btnTim, btnDatPhong, btnHuy, btnCoc;
	private JComboBox<String> cbLoaiPhong, cbTang;
	private JTable tableKH, tableDV;
	private DefaultTableModel modelKH, modelDV;
	private JDateChooser dateNgayDat, dateNgayNhan, dateNgayTra;
	private JLabel lblMaKH;
	private JLabel lblSDT;
	private JButton btnRanDom;
	private Component pnCenter;
	private JTextField txtMaKH;
	private JTextField txtSDT;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private JTextField txtMess;
	private JButton btnTaoHoaDon;
	private JTextField txtMaHD;
	private JButton btnTaoMaHD;
	private JTextField txtTenPH;
	private String maPH;
	ArrayList<DichVu> dsDV;
	private Bus_Phong phong_bus = new Bus_Phong();
	private Bus_DichVu dichVu_bus = new Bus_DichVu();
	private Bus_KhachHang khachHang_bus = new Bus_KhachHang();
	private Bus_HoaDon hoaDon_bus = new Bus_HoaDon();
	private Bus_ChiTietDatPhong chiTietDatPhong_bus = new Bus_ChiTietDatPhong();
	private Bus_ChiTietSuDungDichVu chiTietSuDungDichVu_bus = new Bus_ChiTietSuDungDichVu();
	private JComboBox<String> cboDichVu;
	private JTextField txtGiaDV;
	private KhachHang kh;
	private HoaDon hd;
	private JTextField txtTang;
	private JButton btnThemDV;
	private JTextField txtTongTien;
	private JTextField txtSoLuong;

	public FrmDatPhong() {
		super("Đặt Phòng");
		setSize(1050, 700);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);

		createGUI();
		fillForm();
		
		addWindowListener(this);
		btnRanDom.addActionListener(this);
		btnTaoHoaDon.addActionListener(this);
		btnTaoMaHD.addActionListener(this);
		cboDichVu.addItemListener(this);
		btnThemDV.addActionListener(this);
	}

	public FrmDatPhong(String maP) {
		super("Đặt Phòng "+maP);
		maPH = maP;
		setSize(1050, 700);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);

		createGUI();
		fillForm();

		addWindowListener(this);
		btnRanDom.addActionListener(this);
		btnTaoHoaDon.addActionListener(this);
		btnTaoMaHD.addActionListener(this);
		cboDichVu.addItemListener(this);
		btnThemDV.addActionListener(this);
		btnDatPhong.addActionListener(this);
		btnHuy.addActionListener(this);
	}

	public void createGUI() {
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		JPanel pnNorth = new JPanel(new BorderLayout());
		JLabel lblTieuDe = new JLabel("ĐẶT PHÒNG");
		lblTieuDe.setForeground(Color.blue);
		Font fTieuDe = new Font("Arial", Font.BOLD, 25);
		lblTieuDe.setFont(fTieuDe);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);

		pnBorder.add(pnNorth, BorderLayout.NORTH);

		JPanel pnCustomer = new JPanel();
		pnCustomer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Thông tin khách hàng"));

		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();

		b.add(b1);
		b.add(Box.createVerticalStrut(5));
		b.add(b2);
		b.add(Box.createVerticalStrut(5));
		b.add(b3);
		b.add(Box.createVerticalStrut(5));
		b.add(b4);
		b.add(Box.createVerticalStrut(5));
		b.add(b5);
		b.add(Box.createVerticalStrut(5));
		b.add(b6);
		b.add(Box.createVerticalStrut(5));
		pnCustomer.add(b);

		lblMaKH = new JLabel("Mã KH: ");
		lblHoTen = new JLabel("Họ tên: ");
		lblSDT = new JLabel("Số ĐT: ");
		lblCCCD = new JLabel("CCCD: ");
		lblPhai = new JLabel("Phái: ");
		lblEmail = new JLabel("Email: ");
		lblDiaChi = new JLabel("Địa chỉ: ");
		btnRanDom = new JButton("Random");

		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		b1.add(lblMaKH);
		b1.add(txtMaKH);
		b1.add(btnRanDom);

		txtHoTen = new JTextField();
		txtSDT = new JTextField();
		b2.add(lblHoTen);
		b2.add(txtHoTen);
		b2.add(lblSDT);
		b2.add(txtSDT);

		radNam = new JRadioButton("Nam", true);
		radNu = new JRadioButton("Nữ");
		radNam.setEnabled(true);
		ButtonGroup group = new ButtonGroup();
		group.add(radNam);
		group.add(radNu);
		txtCCCD = new JTextField(40);
		lblCCCD.setPreferredSize(lblDiaChi.getPreferredSize());
		b3.add(lblCCCD);
		b3.add(txtCCCD);
		b3.add(lblPhai);
		b3.add(radNam);
		b3.add(radNu);
		lblCCCD.setPreferredSize(lblMaKH.getPreferredSize());
		lblPhai.setPreferredSize(lblDiaChi.getPreferredSize());

		txtEmail = new JTextField();
		b4.add(lblEmail);
		b4.add(txtEmail);
		lblEmail.setPreferredSize(lblDiaChi.getPreferredSize());

		txtDiaChi = new JTextField();
		b5.add(lblDiaChi);
		b5.add(txtDiaChi);
		
		txtMess = new JTextField();
		txtMess.setEditable(false);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		b6.add(txtMess);

		// Thêm JPanel chứa thông tin đặt phòng bên phải
		JPanel pnBooking = new JPanel();
		pnBooking.setBorder(BorderFactory.createLoweredBevelBorder());
		
		
		Box bbk = Box.createVerticalBox();
		Box b7 = Box.createHorizontalBox();
		Box b8 = Box.createHorizontalBox();
		Box b9 = Box.createHorizontalBox();
		Box b10 = Box.createHorizontalBox();
		Box b11 = Box.createHorizontalBox();
		Box b12 = Box.createHorizontalBox();
		pnBooking.add(bbk);
		
		bbk.add(b7);
		bbk.add(Box.createVerticalStrut(5));
		bbk.add(b8);
		bbk.add(Box.createVerticalStrut(5));
		bbk.add(b9);
		bbk.add(Box.createVerticalStrut(5));
		bbk.add(b10);
		bbk.add(Box.createVerticalStrut(5));
		bbk.add(b11);
		bbk.add(Box.createVerticalStrut(5));
		bbk.add(b12);
		
		b7.add(lblNgayDatPhong = new JLabel("Ngày đặt: "));
		b7.add(dateNgayDat = new JDateChooser());
		dateNgayDat.setDateFormatString("dd/MM/yyyy");
		dateNgayDat.setDate(Calendar.getInstance().getTime());
		dateNgayDat.setPreferredSize(new Dimension(200, 20));

		b8.add(lblNgayTraPhong = new JLabel("Ngày trả phòng: "));
		b8.add(dateNgayTra = new JDateChooser());
		dateNgayTra.setDateFormatString("dd/MM/yyyy");
		dateNgayTra.setDate(Calendar.getInstance().getTime());	
		b9.add(lblSoNguoi = new JLabel("Số người: "));
		b9.add(txtSoNguoi = new JTextField());
		b10.add(lblGhiChu = new JLabel("Ghi chú: "));
		b10.add(txtGhiChu = new JTextField());
		b11.add(new JLabel("Mã HD: "));
		b11.add(txtMaHD = new JTextField());
		txtMaHD.setEditable(false);
		b11.add(btnTaoMaHD = new JButton("Random"));
		btnTaoHoaDon = new JButton("Tạo Hóa Đơn");
		b12.add(btnTaoHoaDon);
		lblNgayDatPhong.setPreferredSize(lblNgayTraPhong.getPreferredSize());
		lblSoNguoi.setPreferredSize(lblNgayTraPhong.getPreferredSize());
		lblGhiChu.setPreferredSize(lblNgayTraPhong.getPreferredSize());
		pnNorth.add(pnCustomer, BorderLayout.WEST);
		pnNorth.add(pnBooking, BorderLayout.CENTER);
		pnNorth.add(lblTieuDe, BorderLayout.NORTH);

		pnBorder.add(pnNorth, BorderLayout.NORTH);

		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		pnBorder.add(pnCenter, BorderLayout.CENTER);


		JPanel pnThongTin = new JPanel();
		pnThongTin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Thông tin phòng-dịch vụ"));
		pnThongTin.add(new JLabel("Mã Phòng: "));
		pnThongTin.add(txtMaPH = new JTextField(6));
		txtMaPH.setEditable(false);
		Phong pThue = phong_bus.getPhongTheoMaPhong(maPH);
		if(pThue != null) {
			txtMaPH.setText(pThue.getMaPhong());
		}
		txtTang = new JTextField(10);
		txtTang.setEditable(false);
		pnThongTin.add(new JLabel("Tầng: "));
		pnThongTin.add(txtTang);
		if(pThue != null) {
			txtTang.setText(pThue.getTang().getMaTang()+"");
		}
		
		pnThongTin.add(new JLabel("Dịch vụ"));
		pnThongTin.add(cboDichVu = new JComboBox<String>());
		cboDichVu.setPreferredSize(new Dimension(150, 25));
		
		dsDV = new ArrayList<DichVu>();
		dsDV = dichVu_bus.getAllDichVu();
		napComboBoxDichVu(dsDV);
		
		
		txtGiaDV = new JTextField(15);
		txtGiaDV.setEditable(false);
		btnThemDV = new JButton("Thêm");
		pnThongTin.add(txtGiaDV);
		
		txtSoLuong = new JTextField(5);
		pnThongTin.add(new JLabel("Số lượng: "));
		pnThongTin.add(txtSoLuong);
		pnThongTin.add(btnThemDV);
		
		JSplitPane split;
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		split.setResizeWeight(0.5);
		pnCenter.add(split);
		
		split.add(pnThongTin);
		

		JPanel pnTable = new JPanel();
		pnTable.setLayout(new BorderLayout());
		split.add(pnTable);

		String[] cols = { "Mã KH", "Họ tên", "Mã phòng", "Tầng", "Ngày lập HĐ", "Số ngày ở", "Tổng tiền" };
		modelKH = new DefaultTableModel(cols, 0);
		tableKH = new JTable(modelKH);
		JScrollPane jscroll = new JScrollPane(tableKH, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jscroll.setPreferredSize(new Dimension(600, 300));
		jscroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Thông tin đặt phòng"));

		String[] cols1 = {"Mã Phòng", "Mã DV", "Tên DV", "Số lượng", "Tổng tiền"};
		modelDV = new DefaultTableModel(cols1, 0);
		tableDV = new JTable(modelDV);
		
		JScrollPane jscroll1 = new JScrollPane(tableDV, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jscroll1.setPreferredSize(new Dimension(400, 300));
		jscroll1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Thông tin dịch vụ"));
		pnTable.add(jscroll, BorderLayout.WEST);
		pnTable.add(jscroll1, BorderLayout.CENTER);


		JPanel pnSouth = new JPanel();
		pnSouth.add(btnDatPhong = new JButton("Đặt phòng"));
//		pnSouth.add(btnCoc = new JButton("Cọc phòng"));
		pnSouth.add(btnHuy = new JButton("Hủy"));
		txtTongTien = new JTextField(12);
		txtTongTien.setEditable(false);
		txtTongTien.setBorder(null);
		txtTongTien.setAlignmentX(RIGHT_ALIGNMENT);
		txtTongTien.setFont(new Font("Arial", Font.BOLD, 30));
		JLabel lblTongTien = new JLabel("Tổng tiền: ");
		lblTongTien.setForeground(Color.red);
		lblTongTien.setFont(new Font("Arial", Font.BOLD, 28));
		pnSouth.add(lblTongTien);
		pnSouth.add(txtTongTien);
		txtTongTien.setForeground(Color.red);
		pnBorder.add(pnSouth, BorderLayout.SOUTH);

		contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);

	}
	
	public void napComboBoxDichVu(ArrayList<DichVu> ds) {
		int n = ds.size();
		String items[] = new String[n];
		int i = 0;
		for (DichVu dv : ds) {
			items[i] = dv.getTenDV();
			i++;
		}
		cboDichVu.setModel(new DefaultComboBoxModel<>(items));
	}
	
	public void fillForm() {
//		txtHoTen.setText("Tran Thanh An");
//		txtSDT.setText("0347854213");
//		txtCCCD.setText("035647854256");
//		txtSoNguoi.setText("1");
	}
	
	public boolean validDate() {
		String maKH = txtMaKH.getText().trim();
		String ten = txtHoTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		String cccd = txtCCCD.getText().trim();
//		String email = txtEmail.getText().trim();
		String soNGuoiO = txtSoNguoi.getText().trim();
		String maHD = txtMaHD.getText().trim();
//		String diaChi = txtDiaChi.getText().trim();
//		String ghichu = txtGhiChu.getText().trim();

		
		if(maKH.equals("")) {
			txtMess.setText("Mã KH không được rỗng!! Hãy nhấn random mã");
			return false;
		}
		if(ten.equals("")) {
			txtMess.setText("Tên không được rỗng!!");
			return false;
		}
	
		if (!sdt.matches("^0\\d{9}")) {
			txtMess.setText("SDT bắt đầu bằng 0 và có tổng cộng 10 số");
			return false;
		}
		if (!cccd.matches("^0\\d{11}")) {
			txtMess.setText("CCCD phải bắt đầu bằng 0 và có 12 chữ số");
			return false;
		}
		if(soNGuoiO.equals("")) {
			txtMess.setText("Nhập số người ở");
			return false;
		}
		if(!isInt(soNGuoiO)) {
			txtMess.setText("Số người ở phải nhập chữ số!!");
			return false;
		}
		if(maHD.equals("")) {
			txtMess.setText("Mã HD không được rỗng!! Hãy nhấn random mã");
			return false;
		}
//		if(email.equals("")) {
//			txtEmail.setText("null");
//		}else {
//			if (!email.matches(
//					"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
//				txtMess.setText("Email sai cú pháp!");
//				return false;
//			}
//		}
//		if(diaChi.equals("")) {
//			txtDiaChi.setText("null");
//		}
//		if(ghichu.equals("")) {
//			txtGhiChu.setText("null");
//		}
		txtMess.setText("");
		return true;
	}
	
	public long tinhSoNgayO() {
		long soNgay = 0;
		Date ngayDat = (Date) dateNgayDat.getDate();
		Date ngayTra = (Date) dateNgayTra.getDate();
		
		LocalDate localNgayDat = ngayDat.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localNgayTra = ngayTra.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		soNgay = ChronoUnit.DAYS.between(localNgayDat, localNgayTra);
		if(soNgay < 1) {
			soNgay = 1;
		}
		return soNgay;
	}
	public void revertKhachHangFromFields() {
		String maKH = txtMaKH.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		String cccd = txtCCCD.getText().trim();
		String email = txtEmail.getText().trim();
		if(email.equals("")) {
			email = "null";
		}
		boolean phai = radNam.isSelected();
		String diaChi = txtDiaChi.getText().trim();
		if(diaChi.equals("")) {
			diaChi = "null";
		}
		kh = new KhachHang(maKH, hoTen, sdt, cccd, email, phai, diaChi);
		
	}
	
	public void lapHoaDon() {
		String maHD = txtMaHD.getText().trim();
		String maKH = txtMaKH.getText().trim();
		String maNV = FrmManHinhChinh.TaiKhoanlogin.getMaTK();
		LocalDate ngayLapHD = LocalDate.now();
		hd = new HoaDon(maHD, new NhanVien(maNV), new KhachHang(maKH), ngayLapHD);
	}
	
	public boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public void taoKhachHangVaHoaDon() {
		if(validDate()) {
			revertKhachHangFromFields();
			lapHoaDon();
			Phong p = phong_bus.getPhongTheoMaPhong(maPH);
			modelKH.addRow(new Object[] {kh.getMaKH(),kh.getHoTen(), maPH, txtTang.getText(), hd.getNgayLapHD(), tinhSoNgayO(), formatTien(tinhTongTienPhong())});
		}
	}
	
	public double tinhTongTienPhong() {
		double tt = 0;
		Phong p = phong_bus.getPhongTheoMaPhong(maPH);
		tt += p.getGia()*tinhSoNgayO();
		return tt;
	}
	
	public double tinhTongTienDV() {
		double tt = 0;
		tt += Double.parseDouble(txtGiaDV.getText()) *Integer.parseInt(txtSoLuong.getText());
		return tt;
	}
	
	public void tinhTongTien() {
		double giaPhong = 0;
		double giaDV = 0;
		String stringNumber = tableKH.getValueAt(0, 6).toString();
		stringNumber = stringNumber.replace(",", "");

		double doubleValue = 0.0f;
		try {
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
			doubleValue = format.parse(stringNumber).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		giaPhong = doubleValue;
		int r = tableDV.getRowCount();
		if(r >= 1) {
			for(int i = 0; i < r; i++) {
				String stringNumber1 = tableDV.getValueAt(i, 4).toString();
				stringNumber1 = stringNumber1.replace(",", "");

				double doubleValue1 = 0.0f;
				try {
					NumberFormat format1 = NumberFormat.getInstance(Locale.FRANCE);
					doubleValue1 = format1.parse(stringNumber1).doubleValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				giaDV += doubleValue1;
			}
		}
		
		double tt = giaDV + giaPhong;
		txtTongTien.setText(formatTien(tt));
	}
	
	public String formatTien(double tien) {
		DecimalFormat df = new DecimalFormat("#,##0VND");
		String s = df.format(tien);
		return s;
	}
	
	public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }
	

	public static void main(String[] args) {
		new FrmDatPhong().setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		String tenDV = cboDichVu.getSelectedItem().toString();
		for (DichVu dv : dsDV) {
			if(dv.getTenDV().equalsIgnoreCase(tenDV)) {
				txtGiaDV.setText(dv.getGia()+"");
			}
		}
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
		Object o = e.getSource();
		if(o.equals(btnRanDom)) {
			PhatSinhMa ran = new PhatSinhMa();
			txtMaKH.setText("KH"+ran.randomMaKH());
		}
		if(o.equals(btnHuy)){
			if(JOptionPane.showConfirmDialog(this, "Bạn muốn hủy quá trình tạo hóa đơn đặt phòng", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				setVisible(false);
			}
		}
		if(o.equals(btnTaoHoaDon)) {
			taoKhachHangVaHoaDon();
			tinhTongTien();
		}
		if(o.equals(btnTaoMaHD)) {
			PhatSinhMa ran = new PhatSinhMa();
			txtMaHD.setText("HD"+ran.randomMaKH());
		}
		if(o.equals(btnThemDV)) {
			if(txtSoLuong.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Nhập số lượng sản phẩm-dịch vụ");
			}
			else {
				DichVu dv = null;
				for (DichVu dichVu : dsDV) {
					if(dichVu.getTenDV().equalsIgnoreCase(cboDichVu.getSelectedItem().toString())) {
						dv = dichVu;
					}
				}
				modelDV.addRow(new Object[] {maPH, dv.getMaDV(), dv.getTenDV(), txtSoLuong.getText(), formatTien(tinhTongTienDV())});
				tinhTongTien();
			}	
		}
		if(o.equals(btnDatPhong)) {
			if(tableKH.getRowCount() < 1) {
				JOptionPane.showMessageDialog(this, "Bạn chưa tạo hóa đơn");
			}else {
				if(!khachHang_bus.them(kh)) {
					JOptionPane.showMessageDialog(this, "Trùng mã KH, vui lòng tạo lại mã khác");
				}else if(!hoaDon_bus.them(hd)) {
					JOptionPane.showMessageDialog(this, "Trùng mã HĐ, vui lòng tạo lại mã khác");
				}else {
					Phong p = new Phong(maPH);
					Date txtNgayDat = dateNgayDat.getDate();
					LocalDate dateDat = convertToLocalDate(txtNgayDat);
					Date txtNgayTra = dateNgayTra.getDate();
					LocalDate dateTra = convertToLocalDate(txtNgayTra);
					String ghiChu = txtGhiChu.getText().trim();
					if(ghiChu.equals("")) {
						ghiChu = "null";
					}
					if(!chiTietDatPhong_bus.them(new ChiTietDatPhong(hd, p, dateDat, dateTra, Integer.parseInt(txtSoNguoi.getText()), ghiChu))) {
						JOptionPane.showMessageDialog(this, "Lỗi không thể lưu chi tiết đặt phòng!!");
						return;
					}else {
						JOptionPane.showMessageDialog(this, "Đã lưu chi tiết đặt phòng");
					}
					if(tableDV.getRowCount() > 0) {
						int count = 0;
						for(int i = 0; i < tableDV.getRowCount(); i++) {
							DichVu dv = new DichVu(tableDV.getValueAt(i, 1).toString());
							int soLuong = Integer.parseInt(tableDV.getValueAt(i, 3).toString());
							if(chiTietSuDungDichVu_bus.them(new ChiTietSuDungDichVu(hd, dv, soLuong))) {
								count++;
							}
						}
						if(count == tableDV.getRowCount()) {
							JOptionPane.showMessageDialog(this, "Đã thêm tất cả dịch vụ");
						}else {
							JOptionPane.showMessageDialog(this, "Chỉ thêm được vài dịch vụ phù hợp!!");
						}
					}
					JOptionPane.showMessageDialog(this, "Đặt phòng thành công");
					if(phong_bus.capNhatTrangThai(false, maPH)) {
						setVisible(false);
					}
					
				}
			}
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();
		if(o.equals(cboDichVu)) {
			String tenDV = cboDichVu.getSelectedItem().toString();
			for (DichVu dv : dsDV) {
				if(dv.getTenDV().equalsIgnoreCase(tenDV)) {
					txtGiaDV.setText(dv.getGia()+"");
				}
			}
		}
		
	}
}