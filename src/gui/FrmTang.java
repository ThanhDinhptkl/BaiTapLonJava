package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bus.Bus_Tang;
import entity.Tang;

public class FrmTang extends JFrame implements ActionListener, WindowListener{
	
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private JPanel pnCenter;
	private JLabel lblMaT;
	private JLabel lblTenT;
	private JTextField txtMaT;
	private JTextField txtTenT;
	private JTextField txtTimKiem;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoa;
	private Bus_Tang tang_bus = new Bus_Tang();
	public FrmTang() {
		super("Thông tin tầng");
		setSize(700, 500);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);

		createGUI();
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTim.addActionListener(this);
		addWindowListener(this);
	}
	
	public void createGUI() {
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		pnBorder.setPreferredSize(new Dimension(600, 450));
		add(pnBorder);

		JPanel pnNorth = new JPanel();
		JLabel lblTieuDe = new JLabel("THÔNG TIN TẦNG");
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

		pnCenter.add(b);

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

		pnCenter.add(Box.createVerticalStrut(10));
		
		lblMaT = new JLabel("Mã tầng: ");
		lblTenT = new JLabel("Tên tầng: ");

		txtMaT = new JTextField();
		b1.add(lblMaT);
		b1.add(txtMaT);
		txtMaT.setEditable(false);

		txtTenT = new JTextField();
		txtTenT.setEditable(false);
		b2.add(lblTenT);
		b2.add(txtTenT);
		lblMaT.setPreferredSize(lblTenT.getPreferredSize());
		
		pnBorder.add(pnCenter, BorderLayout.CENTER);
		
		model = new DefaultTableModel();
		table = new JTable(model);
		table.setRowHeight(25);
		model.addColumn("Mã Tầng");
		model.addColumn("Tên tầng");

		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setPreferredSize(new Dimension(400, 300));
		pnCenter.add(sp);
		
		JSplitPane split;
		pnBorder.add(split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT), BorderLayout.SOUTH);
		split.setResizeWeight(0.5);

		JPanel pnTimKiem = new JPanel();
		JLabel lblTim = new JLabel("Nhập mã tầng: ");
		txtTimKiem = new JTextField(10);
		txtTimKiem.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		pnTimKiem.add(lblTim);
		pnTimKiem.add(txtTimKiem);
		btnTim = new JButton("Tìm");
		pnTimKiem.add(btnTim);
		split.add(pnTimKiem);

		JPanel pnChucNang = new JPanel();
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");

		pnChucNang.add(btnThem);
		pnChucNang.add(btnXoa);
		split.add(pnChucNang);
		
		contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);
		
		napDuLieuTuCSDL(tang_bus.getAllTang());
		txtMaT.setText((tang_bus.count()+1)+"");
		txtTenT.setText("Tầng "+(tang_bus.count()+1));
	}
	
	public void napDuLieuTuCSDL(ArrayList<Tang> ds) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
		for (Tang tk : ds) {
			model.addRow(new Object[] { tk.getMaTang(), tk.getTenTang()});
		}
	}
	
	public Tang reverTangFromFields() {
		int maTang = Integer.parseInt(txtMaT.getText().trim());
		String tenTang = txtTenT.getText().trim();
		return new Tang(maTang, tenTang);
	}
	
	private void deleteRowSelected() {
		String listMaT = "";
		String maT = "";

		if (table.getSelectedRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa");
		} else {

//			lay ra danh sach xoa tren GUI
			int[] listIndex = table.getSelectedRows();
			listMaT += table.getValueAt(listIndex[0], 0);
			for (int i = 1; i < table.getSelectedRowCount(); i++) {
				listMaT += ", " + table.getValueAt(listIndex[i], 0);
			}

//			Hien thi thong tin xoa
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tầng " + listMaT + " ?",
					"Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					while (table.getSelectedRowCount() > 0) {
						maT = table.getValueAt(table.getSelectedRow(), 0).toString();
						int id = Integer.parseInt(maT);
						if (!tang_bus.xoa(id)) {
							JOptionPane.showMessageDialog(null, "Xóa thất bại, đã xảy ra lỗi");
							table.clearSelection();
						} else {
							model.removeRow(table.getSelectedRow());
							txtMaT.setText((tang_bus.count()+1)+"");
							txtTenT.setText("Tầng "+(tang_bus.count()+1));
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());

				}
			}
		}
	}

	public static void main(String[] args) {
		new FrmTang().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem)) {
			Tang tangNew = reverTangFromFields();
			if(!tang_bus.them(tangNew)) {
				JOptionPane.showMessageDialog(this, "Trùng mã");
			}else {
				napDuLieuTuCSDL(tang_bus.getAllTang());
				txtMaT.setText((tang_bus.count()+1)+"");
				txtTenT.setText("Tầng "+(tang_bus.count()+1));
			}
		}
		if(o.equals(btnXoa)) {
			deleteRowSelected();
		}
		if(o.equals(btnTim)) {
			String id = txtTimKiem.getText().trim();
			if(id.equals("")) {
				napDuLieuTuCSDL(tang_bus.getAllTang());
			}else {
				Tang t = tang_bus.getTangTheoMaTang(Integer.parseInt(id));
				if(t != null) {
					DefaultTableModel dm = (DefaultTableModel) table.getModel();
					dm.getDataVector().removeAllElements();
					model.addRow(new Object[] { t.getMaTang(), t.getTenTang()});
				}
				else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
				}
			}
		}
		
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


}
