package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import bus.Bus_Phong;
import bus.Bus_Tang;
import connectDB.ConnectDB;
import entity.NhanVien;
import entity.Phong;
import entity.TaiKhoan;
import entity.Tang;

public class FrmQLPhong extends JFrame implements ActionListener, MouseListener {
	public static JPanel contentPane;
	private JPanel pnCenter;
	private JLabel lblMaP;
	private JLabel lblTenP;
	private JLabel lblLoai;
	private JLabel lblTrangThai;
	private JLabel lblMaTang;
	private JLabel lblGia;
	private JTextField txtMaP;
	private JTextField txtTenP;
	private JComboBox<String> cboLoaiPhong;
	private JRadioButton radTrong;
	private JRadioButton radDangDung;
	private JComboBox<String> cboMaTang;
	private JTextField txtGia;
	private JButton btnReset;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JButton btnSua;
	private DefaultTableModel model;
	private JTable table;

	private Bus_Phong phong_bus = new Bus_Phong();
	private Bus_Tang tang_bus = new Bus_Tang();
	private JPopupMenu popupMenu;
	private JMenuItem itDatPhong;
	private JMenuItem itTraPhong;
	private JMenuItem itDoiPhong;
	private JMenuItem itXemThongTinKhach;
	private JMenuItem itCapNhatDichVu;
	private JTextField txtTimKiem;
	private JButton btnTim;
	private JCheckBox chkLoc;
	private JButton btnLoc;
	private String maPhongDeTimKhachHang;
	private JTextField txtMess;
	private JButton btnThemTang;

	public FrmQLPhong() {
		super("Thông tin nhân viên");
		setSize(1050, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		createGUI();
	}

	public void createGUI() {
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		pnBorder.setPreferredSize(new Dimension(900, 600));
		add(pnBorder);

		JPanel pnNorth = new JPanel();
		JLabel lblTieuDe = new JLabel("THÔNG TIN PHÒNG");
		lblTieuDe.setForeground(Color.blue);
		Font fTieuDe = new Font("Arial", Font.BOLD, 25);
		lblTieuDe.setFont(fTieuDe);
		pnNorth.add(lblTieuDe);
		pnBorder.add(pnNorth, BorderLayout.NORTH);

		pnCenter = new JPanel();
		pnBorder.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
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


		lblMaP = new JLabel("Mã phòng: ");
		lblTenP = new JLabel("Tên phòng: ");
		lblLoai = new JLabel("Loại phòng: ");
		lblTrangThai = new JLabel("Trạng thái: ");
		lblMaTang = new JLabel("Mã tầng: ");
		lblGia = new JLabel("Giá: ");

		txtMaP = new JTextField();
		b1.add(lblMaP);
		b1.add(txtMaP);

		txtTenP = new JTextField();
		cboMaTang = new JComboBox<String>();
		cboMaTang.setPreferredSize(new Dimension(150, 25));
		b2.add(lblTenP);
		b2.add(txtTenP);

		radTrong = new JRadioButton("Trống", true);
		radDangDung = new JRadioButton("Đang sử dụng");
		ButtonGroup group = new ButtonGroup();
		group.add(radTrong);
		group.add(radDangDung);

		cboLoaiPhong = new JComboBox<String>();
		b3.add(lblLoai);
		b3.add(cboLoaiPhong);
		b3.add(lblMaTang);
		b3.add(cboMaTang);
		b3.add(radTrong);
		b3.add(radDangDung);

		txtGia = new JTextField();
		b4.add(lblGia);
		b4.add(txtGia);
		
		txtMess = new JTextField();
		txtMess.setEditable(false);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		b5.add(txtMess);

		lblMaP.setPreferredSize(lblLoai.getPreferredSize());
		lblTenP.setPreferredSize(lblLoai.getPreferredSize());
		lblGia.setPreferredSize(lblLoai.getPreferredSize());
		lblMaTang.setPreferredSize(lblLoai.getPreferredSize());

		pnCenter.add(b);

		JPanel pnTable = new JPanel();
		pnTable.setLayout(new BorderLayout());

		JSplitPane split;
		pnTable.add(split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT), BorderLayout.NORTH);
		split.setResizeWeight(0.5);

		JPanel pnTimKiem = new JPanel();
		JLabel lblTim = new JLabel("Nhập Mã: ");
		txtTimKiem = new JTextField(15);
		txtTimKiem.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		pnTimKiem.add(lblTim);
		pnTimKiem.add(txtTimKiem);
		btnTim = new JButton("Tìm");
		pnTimKiem.add(btnTim);
		chkLoc = new JCheckBox("Phòng trống");
		btnLoc = new JButton("Lọc");
		pnTimKiem.add(chkLoc);
		pnTimKiem.add(btnLoc);
		split.add(pnTimKiem);

		JPanel pnChucNang = new JPanel();
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnXoaTrang = new JButton("Xóa trắng");
		btnSua = new JButton("Sửa");
		btnReset = new JButton("Reset");

		pnChucNang.add(btnThem);
		pnChucNang.add(btnXoa);
		pnChucNang.add(btnXoaTrang);
		pnChucNang.add(btnSua);
		pnChucNang.add(btnReset);
		split.add(pnChucNang);

		String[] cols = { "Mã phòng", "Tên phòng", "Loại phòng", "Trạng thái", "Mã tầng", "Giá" };
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		table.setRowHeight(25);
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setPreferredSize(new Dimension(1000, 350));
		pnTable.add(sp, BorderLayout.CENTER);
		JPanel pnThemTang = new JPanel();
		
		pnTable.add(pnThemTang, BorderLayout.SOUTH);
		btnThemTang = new JButton("Thêm tầng");
		pnThemTang.add(btnThemTang);
		pnBorder.add(pnTable, BorderLayout.SOUTH);

		napDuLieuPhongTuCSDL(phong_bus.getPhongTheoTrangThai(true));
		napComboBoxTang(tang_bus.getAllTang());
		napComboBoxLoaiPhong();

		contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);

		createMenuPopup();
		table.addMouseListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
		btnReset.addActionListener(this);
		btnLoc.addActionListener(this);
		btnTim.addActionListener(this);
		btnThemTang.addActionListener(this);

		itDatPhong.addActionListener(this);
		itTraPhong.addActionListener(this);
		itDoiPhong.addActionListener(this);
		itCapNhatDichVu.addActionListener(this);
		itXemThongTinKhach.addActionListener(this);

	}

	public void napDuLieuPhongTuCSDL(ArrayList<Phong> ds) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
		for (Phong p : ds) {
			model.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong(),
					p.isTrangThai() ? "Trống" : "Đang sử dụng", p.getTang().getMaTang(), formatGia(p.getGia()) });
		}
	}

	public void napComboBoxTang(ArrayList<Tang> ds) {
		int n = ds.size();
		String items[] = new String[n];
		int i = 0;
		for (Tang t : ds) {
			items[i] = t.getMaTang() + "";
			i++;
		}
		cboMaTang.setModel(new DefaultComboBoxModel<String>(items));
	}

	public void napComboBoxLoaiPhong() {
		cboLoaiPhong.addItem("1 giường đơn");
		cboLoaiPhong.addItem("1 giường đôi");
		cboLoaiPhong.addItem("2 giường đơn");
	}

	public String formatGia(double gia) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		String tien = df.format(gia);
		return tien;
	}

	public void createMenuPopup() {
		popupMenu = new JPopupMenu();
		itDatPhong = new JMenuItem("Đặt phòng");
		ImageIcon iconDatPhong = new ImageIcon("img/booking.png");
		Image img = iconDatPhong.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH);
		iconDatPhong.setImage(img);
		itDatPhong.setIcon(iconDatPhong);
		itTraPhong = new JMenuItem("Trả phòng");
		ImageIcon iconTraPhong = new ImageIcon("img/traphong.jpg");
		iconTraPhong.setImage(iconTraPhong.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH));
		itTraPhong.setIcon(iconTraPhong);
		itDoiPhong = new JMenuItem("Đổi phòng");
		ImageIcon iconDoiPhong = new ImageIcon("img/doiphong.jpg");
		iconDoiPhong.setImage(iconDoiPhong.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH));
		itDoiPhong.setIcon(iconDoiPhong);
		itXemThongTinKhach = new JMenuItem("Thông tin khách");
		ImageIcon iconXem = new ImageIcon("img/xem.png");
		iconXem.setImage(iconXem.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH));
		itXemThongTinKhach.setIcon(iconXem);
		itCapNhatDichVu = new JMenuItem("Cập nhật dịch vụ");
		ImageIcon iconDichVu = new ImageIcon("img/dichvu.png");
		iconDichVu.setImage(iconDichVu.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH));
		itCapNhatDichVu.setIcon(iconDichVu);

		popupMenu.add(itDatPhong);
		popupMenu.add(itTraPhong);
		popupMenu.add(itDoiPhong);
		popupMenu.add(itXemThongTinKhach);
		popupMenu.add(itCapNhatDichVu);

	}

	public boolean valiDate() {
		String maP = txtMaP.getText().trim();
		String tenP = txtTenP.getText().trim();
		String gia = txtGia.getText().trim();
		
		
		
		if(!maP.matches("^\\d{3,4}$")) {
			txtMess.setText("Mã có từ 3 - 4 kí số!");
			txtMaP.requestFocus();
			return false;
		}else {
			String maTang = cboMaTang.getSelectedItem().toString();
			if(maTang.charAt(0) != maP.charAt(0)){
				txtMess.setText("Mã phòng có số đầu tiên là mã tầng");
				txtMaP.requestFocus();
				return false;
			}else {
				txtMess.setText("");
			}
		}
		if(tenP.equals("")) {
			txtMess.setText("Tên phòng không được rỗng");
			txtTenP.requestFocus();
			return false;
		}
		if(gia.equals("")) {
			txtMess.setText("Giá không được rỗng");
			txtGia.requestFocus();
			return false;
		}else {
			if(!isDouble(gia)) {
				txtMess.setText("Giá phải là số");
				txtGia.requestFocus();
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public Phong revertPhongFromFIelds() {
		String maPhong = txtMaP.getText().trim();
		String tenPhong = txtTenP.getText().trim();
		String loaiPhong = cboLoaiPhong.getSelectedItem().toString();
		boolean trangThai = radTrong.isSelected();
		String maTang = cboMaTang.getSelectedItem().toString();
		Tang tang = new Tang(Integer.parseInt(maTang));
		double gia = Double.parseDouble(txtGia.getText());
		Phong p = new Phong(maPhong, tenPhong, loaiPhong, trangThai, tang, gia);
		return p;
	}

	private void deleteRowSelected() {
		String listMaP = "";
		String maP = "";

		if (table.getSelectedRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa");
		} else {

//			lay ra danh sach xoa tren GUI
			int[] listIndex = table.getSelectedRows();
			listMaP += table.getValueAt(listIndex[0], 0);
			for (int i = 1; i < table.getSelectedRowCount(); i++) {
				listMaP += ", " + table.getValueAt(listIndex[i], 0);
			}

//			Hien thi thong tin xoa
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn phòng " + listMaP + " ?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					while (table.getSelectedRowCount() > 0) {
						maP = (String) table.getValueAt(table.getSelectedRow(), 0);
						if (!phong_bus.xoa(maP)) {
							JOptionPane.showMessageDialog(null, "Xóa thất bại, đã xảy ra lỗi");
							table.clearSelection();
						} else {
							model.removeRow(table.getSelectedRow());
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());

				}
			}
		}
	}

	public void fillForm(int r) {
		txtMaP.setText(table.getValueAt(r, 0).toString());
		txtTenP.setText(table.getValueAt(r, 1).toString());
		cboLoaiPhong.setSelectedItem(table.getValueAt(r, 2));
		boolean tt = table.getValueAt(r, 3) == "Trống" ? true : false;
		if (tt == true) {
			radTrong.setSelected(true);
		} else {
			radDangDung.setSelected(true);
		}
		cboMaTang.setSelectedItem(table.getValueAt(r, 4).toString());

		String stringNumber = table.getValueAt(r, 5).toString();
		stringNumber = stringNumber.replace(",", "");

		double floatValue = 0.0f;
		try {
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
			floatValue = format.parse(stringNumber).floatValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtGia.setText(floatValue + "");
	}

	public void sua() {
		int r = table.getSelectedRow();
		if (r == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để sửa");
		} else {
			if (valiDate()) {
				Phong p = revertPhongFromFIelds();
				if (!phong_bus.capNhat(p)) {
					JOptionPane.showMessageDialog(this, "Lỗi, không thể cập nhật thông tin");
				} else {
					napDuLieuPhongTuCSDL(phong_bus.getAllPhong());
				}
			}
		}
	}

	public static void main(String[] args) {
		new FrmQLPhong().setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		fillForm(table.getSelectedRow());

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			int r = table.rowAtPoint(e.getPoint());
			if (r >= 0 && r < table.getRowCount()) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
				maPhongDeTimKhachHang = table.getValueAt(r, 0).toString();
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (valiDate()) {
				Phong p = revertPhongFromFIelds();
				if (!phong_bus.them(p)) {
					JOptionPane.showMessageDialog(this, "Trùng mã");
				} else {
					napDuLieuPhongTuCSDL(phong_bus.getAllPhong());
				}
			}
		}
		if (o.equals(btnXoaTrang)) {
			txtMaP.setText("");
			txtTenP.setText("");
			txtGia.setText("");
			cboMaTang.setSelectedIndex(0);
			cboLoaiPhong.setSelectedIndex(0);
			radTrong.setSelected(true);
			table.clearSelection();
		}
		if (o.equals(btnXoa)) {
			deleteRowSelected();
		}
		if (o.equals(btnReset)) {
			napDuLieuPhongTuCSDL(phong_bus.getAllPhong());
		}
		if (o.equals(btnSua)) {
			sua();
		}
		if (o.equals(btnLoc)) {
			if (chkLoc.isSelected() == true) {
				napDuLieuPhongTuCSDL(phong_bus.getPhongTheoTrangThai(true));
			} else {
				napDuLieuPhongTuCSDL(phong_bus.getPhongTheoTrangThai(false));
			}
		}
		if (o.equals(btnTim)) {
			String ma = txtTimKiem.getText().trim();
			if (ma.equals("")) {
				JOptionPane.showMessageDialog(this, "Mời nhập mã!!!");
			} else {
				Phong p = phong_bus.getPhongTheoMaPhong(ma);
				if (p != null) {
					DefaultTableModel dm = (DefaultTableModel) table.getModel();
					dm.getDataVector().removeAllElements();
					model.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong(),
							p.isTrangThai() ? "Trống" : "Đang sử dụng", p.getTang().getMaTang(),
							formatGia(p.getGia()) });
				} else {
					JOptionPane.showMessageDialog(this, "Không tim thấy");
				}
			}

		}
		if (o.equals(itDatPhong)) {
			setVisible(false);
			String maP = maPhongDeTimKhachHang;
			new FrmDatPhong(maP).setVisible(true);
		}
		if (o.equals(itTraPhong)) {
			new FrmTraPhong().setVisible(true);
		}
		if (o.equals(itDoiPhong)) {
			new FrmDoiPhong().setVisible(true);
		}
		if(o.equals(btnThemTang)) {
			new FrmTang().setVisible(true);
		}
		if (o.equals(itXemThongTinKhach)) {
			String maKH = "";
			PreparedStatement sta = null;
			try {
				Connection con = ConnectDB.getInstance().getConnection();
				String sql = "Select * from Phong p " + "join ChiTietDatPhong ctdp on p.MAPHONG = ctdp.MAPHONG "
						+ "join HoaDon hd on hd.MAHD = ctdp.MAHD " + "where p.MAPHONG = ?";
				sta = con.prepareStatement(sql);
				sta.setString(1, maPhongDeTimKhachHang);

				ResultSet rs = sta.executeQuery();

				while (rs.next()) {
					maKH += rs.getString("MAKH");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			if (maKH.equals("")) {
				new FrmKhachHang().setVisible(true);
			} else {
				new FrmKhachHang(maKH).setVisible(true);
			}
		}
	}

}
