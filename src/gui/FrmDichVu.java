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
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bus.Bus_DichVu;
import entity.DichVu;



public class FrmDichVu extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private JPanel pnCenter;
	private JLabel lblMa,lblTen,lblGia;
	private JTextField txtMa,txtTen,txtGia;
	
	private JComboBox<String> cboDichVu;
	private JButton btnReset;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JButton btnSua;
	private DefaultTableModel model;
	private JTable table;

	private Bus_DichVu dichvu_bus=new Bus_DichVu();

	public FrmDichVu() {
		super("Thông tin dịch vụ");
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
		JLabel lblTieuDe = new JLabel("THÔNG TIN DỊCH VỤ");
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
		

		b.add(b1);
		b.add(Box.createVerticalStrut(5));
		b.add(b2);
		b.add(Box.createVerticalStrut(5));
		b.add(b3);
		b.add(Box.createVerticalStrut(5));
		b.add(b4);
		b.add(Box.createVerticalStrut(5));

		lblMa = new JLabel("Mã dịch vụ: ");
		lblTen = new JLabel("Tên dịch vụ: ");
		lblGia = new JLabel("Giá: ");
		lblMa.setPreferredSize(lblTen.getPreferredSize());
		lblGia.setPreferredSize(lblTen.getPreferredSize());

		txtMa= new JTextField();
		txtTen = new JTextField();
		txtGia= new JTextField();
//		txtMa.setEditable(false);
		b1.add(lblMa);
		b1.add(txtMa);
		b2.add(lblTen);
		b2.add(txtTen);
		b3.add(lblGia);
		b3.add(txtGia);
		b4.add(lblGia);
		b4.add(txtGia);
		pnCenter.add(b);

		JPanel pnTable = new JPanel();
		pnTable.setLayout(new BorderLayout());

		JSplitPane split;
		pnTable.add(split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT), BorderLayout.NORTH);
		split.setResizeWeight(0.5);

		JPanel pnTimKiem = new JPanel();
		JLabel lblTim = new JLabel("Tìm theo tên dịch vụ: ");
		pnTimKiem.add(lblTim);
		cboDichVu=new JComboBox<String>();
		pnTimKiem.add(cboDichVu);
		

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

		String[] cols = { "Mã dịch vụ","Tên dịch vụ", "Giá" };
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setPreferredSize(new Dimension(1000, 400));
		pnTable.add(sp, BorderLayout.CENTER);
		pnBorder.add(pnTable, BorderLayout.SOUTH);

		napDuLieuDichVuTuCSDL(dichvu_bus.getAllDichVu());
		napComboBoxDichVu(dichvu_bus.getAllDichVu());
		contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);

		table.addMouseListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
		btnReset.addActionListener(this);
		cboDichVu.addActionListener(this);

		


	}

	public void napDuLieuDichVuTuCSDL(ArrayList<DichVu> ds) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
		for (DichVu dv : ds) {
			model.addRow(new Object[] {dv.getMaDV(),dv.getTenDV(),dv.getGia()});
		}
	}

	public void napComboBoxDichVu(ArrayList<DichVu> ds) {
		int n = ds.size();
		String items[] = new String[n];
		int i = 0;
		for (DichVu t : ds) {
			items[i] = t.getTenDV() + "";
			i++;
		}
		cboDichVu.setModel(new DefaultComboBoxModel<String>(items));
	}

	

	public String formatGia(double gia) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		String tien = df.format(gia);
		return tien;
	}

	public boolean valiDate() {
		return true;
	}

	public DichVu revertDichVuFromFIelds() {
		String ma = txtMa.getText().trim();
		String ten = txtTen.getText().trim();
		double gia = Double.parseDouble(txtGia.getText());
		DichVu dv=new DichVu(ma, ten,gia);
		return dv;
	}

	private void deleteRowSelected() {
		String listMaDV = "";
		String maDV = "";

		if (table.getSelectedRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa");
		} else {

//			lay ra danh sach xoa tren GUI
			int[] listIndex = table.getSelectedRows();
			listMaDV += table.getValueAt(listIndex[0], 0);
			for (int i = 1; i < table.getSelectedRowCount(); i++) {
				listMaDV += ", " + table.getValueAt(listIndex[i], 0);
			}

//			Hien thi thong tin xoa
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa dịch vụ " + listMaDV + " ?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					while (table.getSelectedRowCount() > 0) {
						maDV = (String) table.getValueAt(table.getSelectedRow(), 0);
						if (!dichvu_bus.xoa(maDV)) {
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
		txtMa.setText(table.getValueAt(r, 0).toString());
		txtTen.setText(table.getValueAt(r, 1).toString());
		String stringNumber = table.getValueAt(r, 2).toString();
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
				DichVu dv = revertDichVuFromFIelds();
				if (!dichvu_bus.capNhat(dv)) {
					JOptionPane.showMessageDialog(this, "Lỗi, không thể cập nhật thông tin");
				} else {
					napDuLieuDichVuTuCSDL(dichvu_bus.getAllDichVu());
				}
			}
		}
	}

	public static void main(String[] args) {
		new FrmDichVu().setVisible(true);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (valiDate()) {
				DichVu dv = revertDichVuFromFIelds();
				if (!dichvu_bus.them(dv)) {
					JOptionPane.showMessageDialog(this, "Trùng mã");
				} else {
					napDuLieuDichVuTuCSDL(dichvu_bus.getAllDichVu());
				}
			}
		}
		else if (o.equals(btnXoaTrang)) {
			txtMa.setText("");
			txtTen.setText("");
			txtGia.setText("");
			table.clearSelection();
		}
		else if (o.equals(btnXoa)) {
			deleteRowSelected();
		}
		else if (o.equals(btnReset)) {
			napDuLieuDichVuTuCSDL(dichvu_bus.getAllDichVu());
		}
		else if (o.equals(btnSua)) {
			sua();
		}
		else if(o.equals(cboDichVu )) {
			String selectedService = cboDichVu.getSelectedItem().toString();
	        int rowCount = table.getRowCount();
	        for (int i = 0; i < rowCount; i++) {
	            if (table.getValueAt(i, 1).equals(selectedService)) {
	                table.getSelectionModel().setSelectionInterval(i, i);
	                table.scrollRectToVisible(table.getCellRect(i, 0, true));
	                fillForm(table.getSelectedRow());
	                break;
	            }
	        }
		}
	}

}