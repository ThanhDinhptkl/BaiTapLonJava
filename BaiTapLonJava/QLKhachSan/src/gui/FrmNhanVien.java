package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
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

import bus.Bus_NhanVien;
import entity.NhanVien;
import random.PhatSinhMa;

public class FrmNhanVien extends JFrame implements ActionListener, MouseListener {

	public static JPanel contentPane;
	private JPanel pnCenter;
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtManv, txtHoTen, txtSDT, txtTuoi, txtLuong, txtTimKiem;
	private JLabel lblManv, lblHoTen, lblSDT, lblTuoi, lblPhai, lblTienLuong;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnSua, btnReset, btnRanDom;
	private JRadioButton radNam, radNu, radMaNV, radSDT;

	private Bus_NhanVien nhanVien_bus = new Bus_NhanVien();

	public FrmNhanVien() {
		super("Thông tin nhân viên");
		setSize(1050, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		createGUI();
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnTim.addActionListener(this);
		table.addMouseListener(this);
		btnReset.addActionListener(this);
		btnRanDom.addActionListener(this);
	}

	public void createGUI() {
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		add(pnBorder);

		JPanel pnNorth = new JPanel();
		JLabel lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblTieuDe.setForeground(Color.blue);
		Font fTieuDe = new Font("Arial", Font.BOLD, 45);
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

		pnCenter.add(b);

		b.add(b1);
		b.add(Box.createVerticalStrut(5));
		b.add(b2);
		b.add(Box.createVerticalStrut(5));
		b.add(b3);
		b.add(Box.createVerticalStrut(5));
		b.add(b4);
		b.add(Box.createVerticalStrut(5));
		pnCenter.add(Box.createVerticalStrut(10));

		lblManv = new JLabel("Mã nhân viên: ");
		lblHoTen = new JLabel("Họ tên: ");
		lblSDT = new JLabel("Số ĐT: ");
		lblTuoi = new JLabel("Tuổi: ");
		lblPhai = new JLabel("Phái: ");
		lblTienLuong = new JLabel("Lương: ");
		btnRanDom = new JButton("Random");

		txtManv = new JTextField();
		txtManv.setEditable(false);
		b1.add(lblManv);
		b1.add(txtManv);
		b1.add(btnRanDom);

		txtHoTen = new JTextField();
		txtSDT = new JTextField();
		b2.add(lblHoTen);
		b2.add(txtHoTen);
		b2.add(lblSDT);
		b2.add(txtSDT);
		lblHoTen.setPreferredSize(lblManv.getPreferredSize());

		radNam = new JRadioButton("Nam", true);
		radNu = new JRadioButton("Nữ");
		radNam.setEnabled(true);
		ButtonGroup group = new ButtonGroup();
		group.add(radNam);
		group.add(radNu);
		txtTuoi = new JTextField();
		b3.add(lblTuoi);
		b3.add(txtTuoi);
		b3.add(lblPhai);
		b3.add(radNam);
		b3.add(radNu);
		lblTuoi.setPreferredSize(lblManv.getPreferredSize());
		lblPhai.setPreferredSize(lblManv.getPreferredSize());

		txtLuong = new JTextField();
		b4.add(lblTienLuong);
		b4.add(txtLuong);
		lblTienLuong.setPreferredSize(lblManv.getPreferredSize());

		taoBang();
		pnBorder.add(pnCenter, BorderLayout.CENTER);
		JSplitPane split;
		pnBorder.add(split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT), BorderLayout.SOUTH);
		split.setResizeWeight(0.5);

		JPanel pnTimKiem = new JPanel();
		JLabel lblTim = new JLabel("Thông tin tìm: ");
		radMaNV = new JRadioButton("Mã", true);
		radSDT = new JRadioButton("Số ĐT");
		ButtonGroup groupTim = new ButtonGroup();
		groupTim.add(radSDT);
		groupTim.add(radMaNV);
		txtTimKiem = new JTextField(15);
		txtTimKiem.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		pnTimKiem.add(lblTim);
		pnTimKiem.add(txtTimKiem);
		pnTimKiem.add(radMaNV);
		pnTimKiem.add(radSDT);
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

		napDuLieuTuCSDL(nhanVien_bus.getAllNhanVien());

		contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);
	}

	public void taoBang() {
		JPanel pnTable = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã NV");
		model.addColumn("Họ tên");
		model.addColumn("Số ĐT");
		model.addColumn("Phái");
		model.addColumn("Tuổi");
		model.addColumn("Tiền lương");

		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setPreferredSize(new Dimension(1000, 430));
		pnCenter.add(sp);
	}

	public void napDuLieuTuCSDL(ArrayList<NhanVien> ds) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
		for (NhanVien nv : ds) {
			model.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSdt(), nv.isPhai() ? "Nam" : "Nữ",
					nv.getTuoi(), formatLuong(nv.getLuong()) });
		}
	}

	public void xoaTrang() {
		txtManv.setText("");
		txtHoTen.setText("");
		txtSDT.setText("");
		txtTuoi.setText("");
		txtLuong.setText("");
		radNam.setSelected(true);
		txtManv.requestFocus();
	}

	public boolean validDate() {

		return true;
	}

	public NhanVien revertNhanVienFromFields() {
		String maNV = txtManv.getText().trim();
		String hoTen = txtHoTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		boolean phai = radNam.isSelected();
		int tuoi = Integer.parseInt(txtTuoi.getText());
		float luong = Float.parseFloat(txtLuong.getText());
		NhanVien nv = new NhanVien(maNV, hoTen, sdt, phai, tuoi, luong);
		return nv;
	}

	public void sua() {
		int r = table.getSelectedRow();
		if (r == -1) {
			JOptionPane.showMessageDialog(null, "Ban chua chon dong de sửa");
		} else {
			if (validDate()) {
				NhanVien nvMoi = revertNhanVienFromFields();
				if (!nhanVien_bus.capNhat(nvMoi)) {
					JOptionPane.showMessageDialog(this, "Lỗi không thể cập nhật");
					table.clearSelection();
				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				}
			}
		}
	}

	private void deleteRowSelected() {
		String listMaNV = "";
		String maNV = "";

		if (table.getSelectedRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa");
		} else {

//			lay ra danh sach xoa tren GUI
			int[] listIndex = table.getSelectedRows();
			listMaNV += table.getValueAt(listIndex[0], 0);
			for (int i = 1; i < table.getSelectedRowCount(); i++) {
				listMaNV += ", " + table.getValueAt(listIndex[i], 0);
			}

//			Hien thi thong tin xoa
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên " + listMaNV + " ?",
					"Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					while (table.getSelectedRowCount() > 0) {
						maNV = (String) table.getValueAt(table.getSelectedRow(), 0);
						if (!nhanVien_bus.xoa(maNV)) {
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
		txtManv.setEditable(false);
		txtManv.setText(table.getValueAt(r, 0).toString());
		txtHoTen.setText(table.getValueAt(r, 1).toString());
		txtSDT.setText(table.getValueAt(r, 2).toString());
		String gt = table.getValueAt(r, 3).toString();
		if (gt.equals("Nam")) {
			radNam.setSelected(true);
		} else {
			radNu.setSelected(true);
		}
		txtTuoi.setText(table.getValueAt(r, 4).toString());
		txtLuong.setText(table.getValueAt(r, 5).toString());
	}

	public void timTheoMaNV() {
		String ma = txtTimKiem.getText().trim();
		NhanVien nv = nhanVien_bus.getTheoMaNV(ma);
		if (nv != null) {
			DefaultTableModel dm = (DefaultTableModel) table.getModel();
			dm.getDataVector().removeAllElements();
			model.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSdt(), nv.isPhai() ? "Nam" : "Nữ",
					nv.getTuoi(), formatLuong(nv.getLuong()) });
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy!");
		}
		xoaTrang();
	}

	public void timTheoSDT() {
		String sdt = txtTimKiem.getText().trim();

		if (sdt.isEmpty() || sdt.trim() == "") {
			napDuLieuTuCSDL(nhanVien_bus.getAllNhanVien());
			xoaTrang();
		} else {
			ArrayList<NhanVien> nvList = nhanVien_bus.getTheoSDT(sdt);
			if (nvList.size() > 0) {
				napDuLieuTuCSDL(nvList);
				xoaTrang();

			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			}
		}

	}
	public String formatLuong(float luong) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		String tien = df.format(luong);
		return tien;
	}

	public static void main(String[] args) {
		new FrmNhanVien().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		}
		if (o.equals(btnThem)) {
			if (validDate()) {
				NhanVien nv = revertNhanVienFromFields();
				if (!nhanVien_bus.them(nv)) {
					JOptionPane.showMessageDialog(this, "Trùng mã");
				} else {
					napDuLieuTuCSDL(nhanVien_bus.getAllNhanVien());
				}
			}
		}
		if (o.equals(btnXoa)) {
			deleteRowSelected();
			napDuLieuTuCSDL(nhanVien_bus.getAllNhanVien());
		}
		if (o.equals(btnSua)) {
			sua();
			napDuLieuTuCSDL(nhanVien_bus.getAllNhanVien());
		}
		if (o.equals(btnReset)) {
			napDuLieuTuCSDL(nhanVien_bus.getAllNhanVien());
		}
		if (o.equals(btnRanDom)) {
			PhatSinhMa random = new PhatSinhMa();
			txtManv.setText("" + random.randomMaNV());
		}
		if (o.equals(btnTim)) {
			if (radMaNV.isSelected() == true) {
				timTheoMaNV();
			}
			if (radSDT.isSelected() == true) {
				timTheoSDT();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int r = table.getSelectedRow();
		fillForm(r);

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
