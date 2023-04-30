package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bus.Bus_KhachHang;
import entity.KhachHang;
import entity.NhanVien;
import random.PhatSinhMa;

public class FrmKhachHang extends JFrame implements ActionListener, MouseListener {
	public static JPanel contentPane;
	private JPanel pnCenter;
	private JLabel lblMaKH;
	private JLabel lblHoTen;
	private JLabel lblSDT;
	private JLabel lblCCCD;
	private JLabel lblPhai;
	private JLabel lblEmail;
	private JButton btnRanDom;
	private JLabel lblDiaChi;
	private JTextField txtMaKH;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private JTextField txtCCCD;
	private JTextField txtEmail;
	private JTextField txtDiaChi;

	private DefaultTableModel model;
	private JTable table;
	private JRadioButton radMaKH;
	private JRadioButton radSDT;
	private JTextField txtTimKiem;
	private JButton btnTim;
	private JButton btnReset;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JButton btnSua;

	private Bus_KhachHang khachHang_bus = new Bus_KhachHang();
	private JTextField txtMess;
	private JRadioButton radCCCD;

	public FrmKhachHang() {
		setTitle("Thông tin khách hàng");
		setSize(1050, 700);
		setLocationRelativeTo(null);
		setResizable(false);

		createGUI();
		btnXoaTrang.addActionListener(this);
		table.addMouseListener(this);
		btnRanDom.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnReset.addActionListener(this);
		btnSua.addActionListener(this);
		btnTim.addActionListener(this);
	}
	public FrmKhachHang(String ma) {
		setTitle("Thông tin khách hàng");
		setSize(1050, 700);
		setLocationRelativeTo(null);
		setResizable(false);

		createGUI();
		timKHDangThuePhong(ma);
		
		btnXoaTrang.addActionListener(this);
		table.addMouseListener(this);
		btnRanDom.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnReset.addActionListener(this);
		btnSua.addActionListener(this);
		btnTim.addActionListener(this);
	}

	public void createGUI() {
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		add(pnBorder);

		JPanel pnNorth = new JPanel();
		JLabel lblTieuDe = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblTieuDe.setForeground(Color.blue);
		Font fTieuDe = new Font("Arial", Font.BOLD, 25);
		lblTieuDe.setFont(fTieuDe);
		pnNorth.add(lblTieuDe);
		pnBorder.add(pnNorth, BorderLayout.NORTH);

		pnCenter = new JPanel();
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
		pnCenter.add(Box.createVerticalStrut(10));

		lblMaKH = new JLabel("Mã KH: ");
		lblHoTen = new JLabel("Họ tên: ");
		lblSDT = new JLabel("Số ĐT: ");
		lblCCCD = new JLabel("CCCD: ");
		lblPhai = new JLabel("Phái: ");
		lblEmail = new JLabel("Email: ");
		lblDiaChi = new JLabel("Địa chỉ: ");
		btnRanDom = new JButton("Random");

		pnCenter.add(b);

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
		txtCCCD = new JTextField();
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

		taoBang();
		pnBorder.add(pnCenter, BorderLayout.CENTER);

		JSplitPane split;
		pnBorder.add(split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT), BorderLayout.SOUTH);
		split.setResizeWeight(0.5);

		JPanel pnTimKiem = new JPanel();
		JLabel lblTim = new JLabel("Thông tin tìm: ");
		radMaKH = new JRadioButton("Mã", true);
		radSDT = new JRadioButton("Số ĐT");
		radCCCD = new JRadioButton("CCCD");
		ButtonGroup groupTim = new ButtonGroup();
		groupTim.add(radSDT);
		groupTim.add(radMaKH);
		groupTim.add(radCCCD);
		txtTimKiem = new JTextField(15);
		txtTimKiem.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		pnTimKiem.add(lblTim);
		pnTimKiem.add(txtTimKiem);
		pnTimKiem.add(radMaKH);
		pnTimKiem.add(radSDT);
		pnTimKiem.add(radCCCD);
		btnTim = new JButton("Tìm");
		pnTimKiem.add(btnTim);
		btnReset = new JButton("Reset");
		pnTimKiem.add(btnReset);
		split.add(pnTimKiem);

		JPanel pnChucNang = new JPanel();
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnXoaTrang = new JButton("Xóa trắng");
		btnSua = new JButton("Sửa");

		pnChucNang.add(btnThem);
		pnChucNang.add(btnXoa);
		pnChucNang.add(btnXoaTrang);
		pnChucNang.add(btnSua);
		split.add(pnChucNang);

		napDuLieuTuCSDL(khachHang_bus.getAllKhachHang());

		contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);

	}

	public void taoBang() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã KH");
		model.addColumn("Họ tên");
		model.addColumn("Số ĐT");
		model.addColumn("CCCD");
		model.addColumn("Email");
		model.addColumn("Phái");
		model.addColumn("Địa chỉ");

		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setPreferredSize(new Dimension(1000, 390));
		pnCenter.add(sp);
	}

	public void fillForm(int r) {
		txtMaKH.setText(table.getValueAt(r, 0).toString());
		txtHoTen.setText(table.getValueAt(r, 1).toString());
		txtSDT.setText(table.getValueAt(r, 2).toString());
		txtCCCD.setText(table.getValueAt(r, 3).toString());
		txtEmail.setText(table.getValueAt(r, 4).toString());
		String gt = table.getValueAt(r, 5).toString();
		if (gt.equals("Nam")) {
			radNam.setSelected(true);
		} else {
			radNu.setSelected(true);
		}
		txtDiaChi.setText(table.getValueAt(r, 6).toString());
	}

	public boolean validDate() {
		String maKH = txtMaKH.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		String cccd = txtCCCD.getText().trim();
		String email = txtEmail.getText().trim();

		
		if(maKH.equals("")) {
			txtMess.setText("Mã KH không được rỗng!! Hãy nhấn random mã");
			return false;
		}
		if (!hoTen.matches("^[A-Z][a-z]+((\s[A-Z][a-z]*)+)$")) {
			txtMess.setText("Tên chỉ chứa các ký tự chữ cái có thể gồm nhiều từ ngăn cách bởi dấu khoảng trắng");
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
		if (!email.matches(
				"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
			txtMess.setText("Email sai cú pháp!");
			return false;
		}

		txtMess.setText("");
		return true;
	}

	public KhachHang revertKhachHangFromFields() {
		String maKH = txtMaKH.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		String cccd = txtCCCD.getText().trim();
		String email = txtEmail.getText().trim();
		boolean phai = radNam.isSelected();
		String diaChi = txtDiaChi.getText().trim();
		KhachHang kh = new KhachHang(maKH, hoTen, sdt, cccd, email, phai, diaChi);
		return kh;
	}

	public void sua() {
		int r = table.getSelectedRow();
		if (r == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để sửa");
		} else {
			if (validDate()) {
				KhachHang khMoi = revertKhachHangFromFields();
				if (!khachHang_bus.capNhat(khMoi)) {
					JOptionPane.showMessageDialog(this, "Lỗi không thể cập nhật");
					table.clearSelection();
				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				}
			}
		}
	}

	private void deleteRowSelected() {
		String listMaKH = "";
		String maKH = "";

		if (table.getSelectedRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa");
		} else {

//			lay ra danh sach xoa tren GUI
			int[] listIndex = table.getSelectedRows();
			listMaKH += table.getValueAt(listIndex[0], 0);
			for (int i = 1; i < table.getSelectedRowCount(); i++) {
				listMaKH += ", " + table.getValueAt(listIndex[i], 0);
			}

//			Hien thi thong tin xoa
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khách hàng " + listMaKH + " ?",
					"Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					while (table.getSelectedRowCount() > 0) {
						maKH = (String) table.getValueAt(table.getSelectedRow(), 0);
						if (!khachHang_bus.xoa(maKH)) {
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

	public void napDuLieuTuCSDL(ArrayList<KhachHang> ds) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
		for (KhachHang kh : ds) {
			model.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getsDT(), kh.getCccd(), kh.getEmail(),
					kh.isPhai() ? "Nam" : "Nữ", kh.getDiaChi() });
		}
	}

	public void xoaTrang() {
		txtMaKH.setText("");
		txtHoTen.setText("");
		txtSDT.setText("");
		txtCCCD.setText("");
		txtEmail.setText("");
		txtDiaChi.setText("");
		radNam.setSelected(true);
		txtMaKH.requestFocus();
	}

	public void timTheoMaKH() {
		String ma = txtTimKiem.getText().trim();
		KhachHang kh = khachHang_bus.getKhachHangTheoMaKH(ma);
		if (kh != null) {
			DefaultTableModel dm = (DefaultTableModel) table.getModel();
			dm.getDataVector().removeAllElements();
			model.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getsDT(), kh.getCccd(), kh.getEmail(),
					kh.isPhai() ? "Nam" : "Nữ", kh.getDiaChi() });
			table.clearSelection();
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy!");
		}
		xoaTrang();
	}
	
	public void timKHDangThuePhong(String ma) {
		KhachHang kh = khachHang_bus.getKhachHangTheoMaKH(ma);
		if (kh != null) {
			DefaultTableModel dm = (DefaultTableModel) table.getModel();
			dm.getDataVector().removeAllElements();
			model.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getsDT(), kh.getCccd(), kh.getEmail(),
					kh.isPhai() ? "Nam" : "Nữ", kh.getDiaChi() });
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy!");
		}
		xoaTrang();
	}

	public void timTheoCCCD() {
		String ma = txtTimKiem.getText().trim();
		KhachHang kh = khachHang_bus.getKhachHangTheoCCCD(ma);
		if (kh != null) {
			DefaultTableModel dm = (DefaultTableModel) table.getModel();
			dm.getDataVector().removeAllElements();
			model.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getsDT(), kh.getCccd(), kh.getEmail(),
					kh.isPhai() ? "Nam" : "Nữ", kh.getDiaChi() });
			table.clearSelection();
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy!");
		}
		xoaTrang();
	}

	public void timTheoSDT() {
		String sdt = txtTimKiem.getText().trim();

		if (sdt.isEmpty() || sdt.trim() == "") {
			napDuLieuTuCSDL(khachHang_bus.getAllKhachHang());
			xoaTrang();
		} else {
			ArrayList<KhachHang> khList = khachHang_bus.getKhachHangTheoSDT(sdt);
			if (khList.size() > 0) {
				napDuLieuTuCSDL(khList);
				xoaTrang();
				table.clearSelection();
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			}
		}

	}

	public static void main(String[] args) {
		new FrmKhachHang().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		}
		if (o.equals(btnRanDom)) {
			PhatSinhMa random = new PhatSinhMa();
			txtMaKH.setText("KH" + random.randomMaKH());
		}
		if (o.equals(btnThem)) {
			if (validDate()) {
				KhachHang kh = revertKhachHangFromFields();
				if (!khachHang_bus.them(kh)) {
					JOptionPane.showMessageDialog(this, "Trùng mã");
				} else {
					napDuLieuTuCSDL(khachHang_bus.getAllKhachHang());
					table.clearSelection();
				}
			}
		}
		if (o.equals(btnXoa)) {
			deleteRowSelected();
		}
		if (o.equals(btnReset)) {
			napDuLieuTuCSDL(khachHang_bus.getAllKhachHang());
		}
		if (o.equals(btnSua)) {
			sua();
			napDuLieuTuCSDL(khachHang_bus.getAllKhachHang());
		}
		if (o.equals(btnTim)) {
			if (radMaKH.isSelected() == true) {
				timTheoMaKH();
			}
			if (radSDT.isSelected() == true) {
				timTheoSDT();
			}
			if (radCCCD.isSelected() == true) {
				timTheoCCCD();
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		fillForm(table.getSelectedRow());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
