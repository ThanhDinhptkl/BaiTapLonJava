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
import javax.swing.table.DefaultTableModel;

import bus.Bus_NhanVien;
import bus.Bus_TaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;

public class FrmTaiKhoan extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtPass, txtTim;
	private JButton btnReset;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JButton btnSua;
	private JButton btnTim;

	private JComboBox<String> cboMaNV;
	private JComboBox<String> cboQuyen;

	private Bus_NhanVien nhanVien_bus = new Bus_NhanVien();
	private Bus_TaiKhoan taiKhoan_bus = new Bus_TaiKhoan();

	public FrmTaiKhoan() {
		super("Thông tin tài khoản");
		setSize(1050, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		createGUI();
		napComboBox(nhanVien_bus.getAllNhanVien());
		napDuLieuTuCSDL(taiKhoan_bus.getAllTaiKhoan());

		btnReset.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
		btnTim.addActionListener(this);
		table.addMouseListener(this);
	}

	public void createGUI() {
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		pnBorder.setPreferredSize(new Dimension(900, 600));
		add(pnBorder);

		JPanel pnNorth = new JPanel();
		pnBorder.add(pnNorth, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("THÔNG TIN TÀI KHOẢN");
		lblTieuDe.setForeground(Color.blue);
		Font fTieuDe = new Font("Arial", Font.BOLD, 25);
		lblTieuDe.setFont(fTieuDe);
		pnNorth.add(lblTieuDe);

		String cols[] = { "Mã NV", "Mật Khẩu", "Quyền" };
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		table.setRowHeight(25);
		JScrollPane js = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setPreferredSize(new Dimension(600, 450));
		js.setBorder(BorderFactory.createEtchedBorder());
		pnBorder.add(js, BorderLayout.WEST);

		JPanel pnText = new JPanel();
		pnText.setLayout(new BorderLayout());
		pnBorder.add(pnText, BorderLayout.CENTER);

		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();

		pnText.add(b, BorderLayout.NORTH);

		b.add(b1);
		b.add(Box.createVerticalStrut(5));
		b.add(b2);
		b.add(Box.createVerticalStrut(5));
		b.add(b3);
		b.add(Box.createVerticalStrut(15));
		b.add(b4);
		b.add(Box.createVerticalStrut(5));
		b.add(b5);
		b.add(Box.createVerticalStrut(5));

		JLabel lblMaNV = new JLabel("Mã NV: ");
		JLabel lblPass = new JLabel("Mật khẩu: ");
		JLabel lblQuyen = new JLabel("Quyền: ");

		cboMaNV = new JComboBox<String>();
		btnReset = new JButton("Reset");
		b1.add(lblMaNV);
		b1.add(cboMaNV);
		b1.add(btnReset);

		txtPass = new JTextField(20);
		b2.add(lblPass);
		b2.add(txtPass);

		cboQuyen = new JComboBox<String>();
		cboQuyen.addItem("Admin");
		cboQuyen.addItem("Nhân viên");
		b3.add(lblQuyen);
		b3.add(cboQuyen);

		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnXoaTrang = new JButton("Xóa trắng");
		btnSua = new JButton("Sửa");

		JPanel pnTacVu = new JPanel();
		pnTacVu.setLayout(null);
		pnTacVu.setPreferredSize(new Dimension(150, 90));
		pnTacVu.add(btnThem);
		pnTacVu.add(btnXoa);
		pnTacVu.add(btnXoaTrang);
		pnTacVu.add(btnSua);
		btnThem.setBounds(10, 2, 100, 30);
		btnXoa.setBounds(160, 2, 100, 30);
		btnXoaTrang.setBounds(10, 42, 100, 30);
		btnSua.setBounds(160, 42, 100, 30);
		b4.add(pnTacVu);

		txtTim = new JTextField();
		btnTim = new JButton("Tìm Mã NV");
		b5.add(txtTim);
		b5.add(btnTim);

		contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);
	}

	public void napComboBox(ArrayList<NhanVien> ds) {
		int n = ds.size();
		String items[] = new String[n];
		int i = 0;
		for (NhanVien nv : ds) {
			items[i] = nv.getMaNV();
			i++;
		}
		cboMaNV.setModel(new DefaultComboBoxModel<String>(items));
	}

	public void napDuLieuTuCSDL(ArrayList<TaiKhoan> ds) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
		for (TaiKhoan tk : ds) {
			model.addRow(new Object[] { tk.getMaTK(), tk.getPass(), tk.getQuyen() });
		}
	}

	private void deleteRowSelected() {
		String listMaTK = "";
		String maTK = "";

		if (table.getSelectedRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa");
		} else {

//			lay ra danh sach xoa tren GUI
			int[] listIndex = table.getSelectedRows();
			listMaTK += table.getValueAt(listIndex[0], 0);
			for (int i = 1; i < table.getSelectedRowCount(); i++) {
				listMaTK += ", " + table.getValueAt(listIndex[i], 0);
			}

//			Hien thi thong tin xoa
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tài khoản " + listMaTK + " ?",
					"Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					while (table.getSelectedRowCount() > 0) {
						maTK = (String) table.getValueAt(table.getSelectedRow(), 0);
						if (!taiKhoan_bus.xoa(maTK)) {
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
		cboMaNV.setSelectedItem(table.getValueAt(r, 0));
		txtPass.setText(table.getValueAt(r, 1).toString());
		cboQuyen.setSelectedItem(table.getValueAt(r, 2));
	}

	public boolean validDate() {
		String pass = txtPass.getText().trim();
		if (!pass.matches("[\\d[@#$%^&+=]*]{4,6}")) {
			String tb = "Mật khẩu có 4-6 kí tự hoặc kí số có thể sử dụng kí tự đặt biệt";
			JOptionPane.showMessageDialog(this, tb);
			return false;
		}
		return true;
	}

	public TaiKhoan revertTaiKhoanFromFields() {
		String maTK = cboMaNV.getSelectedItem().toString();
		String pass = txtPass.getText().trim();
		String quyen = cboQuyen.getSelectedItem().toString();
		TaiKhoan tk = new TaiKhoan(maTK, pass, quyen);
		return tk;

	}

	public static void main(String[] args) {
		new FrmTaiKhoan().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnReset)) {
			napComboBox(nhanVien_bus.getAllNhanVien());
		}
		if (o.equals(btnXoaTrang)) {
			txtPass.setText("");
		}
		if (o.equals(btnThem)) {
			if (validDate()) {
				TaiKhoan tk = revertTaiKhoanFromFields();
				if (!taiKhoan_bus.them(tk)) {
					JOptionPane.showMessageDialog(this, "Trùng mã");
				} else {
					napDuLieuTuCSDL(taiKhoan_bus.getAllTaiKhoan());
				}
			}
		}
		if (o.equals(btnSua)) {
			int r = table.getSelectedRow();
			if (r == -1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để sửa");
			} else {
				if (validDate()) {
					TaiKhoan tkMoi = revertTaiKhoanFromFields();
					if (!taiKhoan_bus.capNhat(tkMoi)) {
						JOptionPane.showMessageDialog(this, "Lỗi không thể cập nhật");
						table.clearSelection();
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật thành công");
						napDuLieuTuCSDL(taiKhoan_bus.getAllTaiKhoan());
						table.clearSelection();
					}
				}
			}
		}
		if (o.equals(btnTim)) {
			String ma = txtTim.getText().trim();
			if (ma.equals("")) {
				napDuLieuTuCSDL(taiKhoan_bus.getAllTaiKhoan());
			} else {
				TaiKhoan tk = taiKhoan_bus.getTheoMaTK(ma);
				if (tk != null) {
					DefaultTableModel dm = (DefaultTableModel) table.getModel();
					dm.getDataVector().removeAllElements();
					model.addRow(new Object[] { tk.getMaTK(), tk.getPass(), tk.getQuyen() });
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy!");
					napDuLieuTuCSDL(taiKhoan_bus.getAllTaiKhoan());
				}
			}
		}
		if(o.equals(btnXoa)) {
			deleteRowSelected();
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
