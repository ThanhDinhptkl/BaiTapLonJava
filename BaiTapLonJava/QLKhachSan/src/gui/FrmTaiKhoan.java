package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class FrmTaiKhoan extends JFrame implements ActionListener{

	public static JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtPass;
	private JButton btnReset;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JButton btnSua;
	
	private JComboBox<String> cboMaNV;
	private JComboBox<String> cboQuyen;
	
	private Bus_NhanVien nhanVien_bus = new Bus_NhanVien();
	
	public FrmTaiKhoan() {
		super("Thông tin tài khoản");
		setSize(1050, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		createGUI();
		
		btnReset.addActionListener(this);
	}
	
	public void createGUI() {
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		add(pnBorder);
		
		JPanel pnNorth = new JPanel();
		pnBorder.add(pnNorth, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("THÔNG TIN TÀI KHOẢN");
		lblTieuDe.setForeground(Color.blue);
		Font fTieuDe = new Font("Arial", Font.BOLD, 45);
		lblTieuDe.setFont(fTieuDe);
		pnNorth.add(lblTieuDe);
		
		String cols[] = {"Mã NV", "Mật Khẩu", "Quyền"};
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		JScrollPane js = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setPreferredSize(new Dimension(750, 540));
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
		b.add(Box.createVerticalStrut(25));
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
		
		b4.add(btnThem);
		b4.add(btnXoa);
		btnXoaTrang = new JButton("Xóa trắng");
		btnSua = new JButton("Sửa");
		b5.add(btnXoaTrang);
		b5.add(btnSua);

		
		napComboBox(nhanVien_bus.getAllNhanVien());
		
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

	public static void main(String[] args) {
		new FrmTaiKhoan().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnReset)) {
			napComboBox(nhanVien_bus.getAllNhanVien());
		}
		
	}

}
