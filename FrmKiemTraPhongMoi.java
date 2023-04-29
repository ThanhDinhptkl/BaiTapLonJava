package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrmKiemTraPhongMoi extends JFrame implements ActionListener {
	private JLabel lblTieuDe, lblMaPhong, lblLoaiPhong, lblTenPhong, lblTinhTrang;
	private JButton btnTroLai, btnPhieuYeuCauDoiPhong;
	private JTextField txtMaPhong, txtLoaiPhong, txtxTenPhong, txtTinhTrang, txtThongBao;

	public FrmKiemTraPhongMoi() {
		setTitle("Quản Lý Khách Sạn");
		setSize(600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		createGui();
	}

	public void createGui() {
		JPanel lbl_title = new JPanel();
		lbl_title.add(lblTieuDe = new JLabel("Kiểm Tra Phòng Mới"));
		this.add(lbl_title, BorderLayout.NORTH);
		lblTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		lblTieuDe.setForeground(Color.RED);

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
		box.add(b1);
		box.add(Box.createVerticalStrut(5));
		box.add(b2);
		box.add(Box.createVerticalStrut(5));
		box.add(b3);
		box.add(Box.createVerticalStrut(5));
		box.add(b4);
		box.add(Box.createVerticalStrut(5));

		b1.add(lblMaPhong = new JLabel("Mã Phòng"));
		b1.add(txtMaPhong = new JTextField());
		b1.add(lblTenPhong = new JLabel("Tên Phòng"));
		b1.add(txtxTenPhong = new JTextField());

		b2.add(lblLoaiPhong = new JLabel("Loại Phòng"));
		b2.add(txtLoaiPhong = new JTextField());

		b3.add(lblTinhTrang = new JLabel("Tình Trạng"));
		b3.add(txtTinhTrang = new JTextField());

		b4.add(txtThongBao = new JTextField());
		txtThongBao.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
		txtThongBao.setForeground(Color.RED);
		txtThongBao.setBorder(null);
		txtThongBao.setEditable(false);

		pn_center_top.add(box);

		pnCenter.add(pn_center_top);
		this.add(pnCenter, BorderLayout.CENTER);

		lblMaPhong.setPreferredSize(new Dimension(80, 5));
		lblTenPhong.setPreferredSize(new Dimension(80, 5));
		lblLoaiPhong.setPreferredSize(new Dimension(80, 5));
		lblTinhTrang.setPreferredSize(new Dimension(80, 5));

		JPanel pn_bottom = new JPanel();
		pn_bottom.add(btnPhieuYeuCauDoiPhong = new JButton("Phiếu Yêu Cầu Đổi Phòng"));
		pn_bottom.add(btnTroLai = new JButton("Trở Lại"));
		this.add(pn_bottom, BorderLayout.SOUTH);

		btnPhieuYeuCauDoiPhong.addActionListener(this);
		btnTroLai.addActionListener(this);
	}

	public static void main(String[] args) {
		new FrmKiemTraPhongMoi().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
