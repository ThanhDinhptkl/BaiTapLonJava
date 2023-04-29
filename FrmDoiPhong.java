package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FrmDoiPhong extends JFrame implements ActionListener {
	private JLabel lblTieuDe, lblHoTen, lblCCCD, lblDiaChi, lblSDT, lblMaPhongCu, lblMaPhongMoi, lblLyDoDoi,
			lblChiTietLyDo;
	private JTextField txtHoTen, txtCCCD, txtDiaChi, txtSDT, txtMaPhongCu, txtMaPhongMoi, txtChiTietLyDo, txtThongBao;
	private JButton btnDoi, btnHuy;
	private JComboBox comBoLyDoDoi, comBoMaPhongCu, comBoMaPhongMoi;
	private JTable table;
	private DefaultTableModel model;
	private TableColumn demoColumn;
	private JScrollPane scroll;

	public FrmDoiPhong() {
		setTitle("Quản Lý Khách Sạn");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		createGui();
	}

	public void createGui() {
		// Phần North
		JPanel lbl_title = new JPanel();
		lbl_title.add(lblTieuDe = new JLabel("ĐỔI PHÒNG"));
		this.add(lbl_title, BorderLayout.NORTH);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.blue);

		// Phần Center
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));

		JPanel pn_center_top = new JPanel();
		pn_center_top.setLayout(new BoxLayout(pn_center_top, BoxLayout.Y_AXIS));
		Box box = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();

		box.add(b1);
		box.add(Box.createVerticalStrut(5));
		box.add(b2);
		box.add(Box.createVerticalStrut(5));
		box.add(b3);
		box.add(Box.createVerticalStrut(5));
		box.add(b4);
		box.add(Box.createVerticalStrut(5));
		box.add(b5);
		box.add(Box.createVerticalStrut(5));

		b1.add(lblHoTen = new JLabel("Họ Tên Khách Hàng "));
		b1.add(txtHoTen = new JTextField());
		b1.add(lblCCCD = new JLabel("CCCD "));
		b1.add(txtCCCD = new JTextField());

		b2.add(lblDiaChi = new JLabel("Địa Chỉ "));
		b2.add(txtDiaChi = new JTextField());
		b2.add(lblSDT = new JLabel("SĐT "));
		b2.add(txtSDT = new JTextField());

		b3.add(lblMaPhongCu = new JLabel("Mã Phòng Cũ "));
		b3.add(comBoMaPhongCu = new JComboBox());
		comBoMaPhongCu.addItem("100");
		comBoMaPhongCu.addItem("101");
		comBoMaPhongCu.addItem("200");
		comBoMaPhongCu.addItem("300");
		comBoMaPhongCu.addItem("301");
		b3.add(lblMaPhongMoi = new JLabel("Mã Phòng Mới "));
		b3.add(comBoMaPhongMoi = new JComboBox());
		comBoMaPhongMoi.addItem("100");
		comBoMaPhongMoi.addItem("101");
		comBoMaPhongMoi.addItem("200");
		comBoMaPhongMoi.addItem("300");
		comBoMaPhongMoi.addItem("301");

		b4.add(lblLyDoDoi = new JLabel("Lý Do Đổi "));
		b4.add(comBoLyDoDoi = new JComboBox());
		comBoLyDoDoi.addItem("Vấn đề về an ninh và an toàn");
		comBoLyDoDoi.addItem("Vấn đề về tiện nghi");
		comBoLyDoDoi.addItem("Vấn đề về sạch sẽ");
		comBoLyDoDoi.addItem("Vấn đề về tiếng ồn");
		comBoLyDoDoi.addItem("Sự cố về kỹ thuật");
		comBoLyDoDoi.addItem("Không hài lòng với phòng của mình");

		b5.add(lblChiTietLyDo = new JLabel("Chi Tiết Lý Do "));
		b5.add(txtChiTietLyDo = new JTextField());

		b4.add(txtThongBao = new JTextField());
		txtThongBao.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
		txtThongBao.setForeground(Color.RED);
		txtThongBao.setBorder(null);
		txtThongBao.setEditable(false);

		pn_center_top.add(box);

		pnCenter.add(pn_center_top);
		this.add(pnCenter, BorderLayout.CENTER);

		lblHoTen.setPreferredSize(new Dimension(120, 5));
		lblCCCD.setPreferredSize(new Dimension(120, 5));
		lblDiaChi.setPreferredSize(new Dimension(120, 5));
		lblSDT.setPreferredSize(new Dimension(120, 5));
		lblMaPhongCu.setPreferredSize(new Dimension(120, 5));
		lblMaPhongMoi.setPreferredSize(new Dimension(120, 5));
		lblLyDoDoi.setPreferredSize(new Dimension(120, 5));
		lblChiTietLyDo.setPreferredSize(new Dimension(120, 5));

		String col[] = { "Họ Tên Khách Hàng", "CCCD", "Địa Chỉ", "SĐT", "Mã Phòng Cũ", "Mã Phòng Mới", "Lý Do Đổi",
				"Chi Tiết Lý Do" };
		model = new DefaultTableModel(col, 0);

		table = new JTable(model);
		scroll = new JScrollPane(table);
		pnCenter.add(scroll);

		JPanel pn_bottom = new JPanel();
		pn_bottom.add(btnDoi = new JButton("Đổi Phòng"));
		pn_bottom.add(btnHuy = new JButton("Hủy"));
		this.add(pn_bottom, BorderLayout.SOUTH);

		btnDoi.addActionListener(this);
		btnHuy.addActionListener(this);
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtHoTen.setText((String) model.getValueAt(row, 0));
				txtCCCD.setText((String) model.getValueAt(row, 1));
				txtDiaChi.setText((String) model.getValueAt(row, 2));
				txtSDT.setText((String) model.getValueAt(row, 4));
				if (model.getValueAt(row, 3).equals("100")) {
					comBoMaPhongCu.setSelectedItem("100");
				} else if (model.getValueAt(row, 3).equals("101")) {
					comBoMaPhongCu.setSelectedItem("101");
				} else if (model.getValueAt(row, 3).equals("200")) {
					comBoMaPhongCu.setSelectedItem("200");
				} else if (model.getValueAt(row, 3).equals("300")) {
					comBoMaPhongCu.setSelectedItem("300");
				} else if (model.getValueAt(row, 3).equals("301")) {
					comBoMaPhongCu.setSelectedItem("301");
				}
				if (model.getValueAt(row, 3).equals("100")) {
					comBoMaPhongMoi.setSelectedItem("100");
				} else if (model.getValueAt(row, 3).equals("101")) {
					comBoMaPhongMoi.setSelectedItem("101");
				} else if (model.getValueAt(row, 3).equals("200")) {
					comBoMaPhongMoi.setSelectedItem("200");
				} else if (model.getValueAt(row, 3).equals("300")) {
					comBoMaPhongMoi.setSelectedItem("300");
				} else if (model.getValueAt(row, 3).equals("301")) {
					comBoMaPhongMoi.setSelectedItem("301");
				}
				if (model.getValueAt(row, 3).equals("Vấn đề về an ninh và an toàn")) {
					comBoLyDoDoi.setSelectedItem("Vấn đề về an ninh và an toàn");
				} else if (model.getValueAt(row, 3).equals("Vấn đề về tiện nghi")) {
					comBoLyDoDoi.setSelectedItem("Vấn đề về tiện nghi");
				} else if (model.getValueAt(row, 3).equals("Vấn đề về sạch sẽ")) {
					comBoLyDoDoi.setSelectedItem("Vấn đề về sạch sẽ");
				} else if (model.getValueAt(row, 3).equals("Vấn đề về tiếng ồn")) {
					comBoLyDoDoi.setSelectedItem("Vấn đề về tiếng ồn");
				} else if (model.getValueAt(row, 3).equals("Sự cố về kỹ thuật")) {
					comBoLyDoDoi.setSelectedItem("Sự cố về kỹ thuật");
				} else {
					comBoLyDoDoi.setSelectedItem("Không hài lòng với phòng của mình");
				}
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnHuy)) {
			huy();
		} else if (e.getSource().equals(btnDoi)) {
			doi();
		}
	}

	private void huy() {
		txtHoTen.setText("");
		txtCCCD.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");
		txtMaPhongCu.setText("");
		txtMaPhongMoi.setText("");
		table.clearSelection();
		txtThongBao.setText("");
		comBoLyDoDoi.setSelectedIndex(0);
		txtChiTietLyDo.setText("");
	}

	private void doi() {

		String hoTen = txtHoTen.getText();
		String cCCD = txtCCCD.getText();
		String diaChi = txtDiaChi.getText();
		String sDT = txtSDT.getText();
		String maPhongCu = txtMaPhongCu.getText();
		String maPhongMoi = txtMaPhongMoi.getText();
		String lyDoDoi = comBoLyDoDoi.getSelectedItem().toString();
		String chiTietLyDo = txtChiTietLyDo.getText();
	}

	public static void main(String[] args) {
		new FrmDoiPhong().setVisible(true);
	}

}
