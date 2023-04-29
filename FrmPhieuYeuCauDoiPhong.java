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

public class FrmPhieuYeuCauDoiPhong extends JFrame implements ActionListener {
	private JLabel lblTieuDe, lblTenKhachHang, lblCCCD, lblSDT, lblMaPhongCu, lblMaPhongMoi, lblChiTietLyDoDoi,
			lblPhiPhuThem;
	private JTextField txtThongBao, txtTenKhachHang, txtCCCD, txtSDT, txtMaPhongCu, txtMaPhongMoi, txtChiTietLyDoDoi,
			txtPhiPhuThem;
	private JButton btnXacNhanDoiPhong, btnHuyDoiPhong;

	public FrmPhieuYeuCauDoiPhong() {
		setTitle("Quản Lý Khách Sạn");
		setSize(700, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		createGui();
	}

	public void createGui() {
		JPanel lbl_title = new JPanel();
		lbl_title.add(lblTieuDe = new JLabel("Phiếu Yêu Cầu Đổi Phòng"));
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
		box.add(b6);
		box.add(Box.createVerticalStrut(5));

		b1.add(lblTenKhachHang = new JLabel("Họ Tên Khách Hàng"));
		b1.add(txtTenKhachHang = new JTextField());

		b2.add(lblCCCD = new JLabel("CCCD"));
		b2.add(txtCCCD = new JTextField());
		b2.add(lblSDT = new JLabel("SĐT"));
		b2.add(txtSDT = new JTextField());

		b3.add(lblMaPhongCu = new JLabel("Mã Phòng Cũ"));
		b3.add(txtMaPhongCu = new JTextField());
		b3.add(lblMaPhongMoi = new JLabel("Mã Phòng Mới"));
		b3.add(txtMaPhongMoi = new JTextField());

		b4.add(lblChiTietLyDoDoi = new JLabel("Chi Tiết Lý Do Đổi"));
		b4.add(txtChiTietLyDoDoi = new JTextField());

		b5.add(lblPhiPhuThem = new JLabel("Phí Phụ Thêm"));
		b5.add(txtPhiPhuThem = new JTextField());

		b6.add(txtThongBao = new JTextField());
		txtThongBao.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
		txtThongBao.setForeground(Color.RED);
		txtThongBao.setBorder(null);
		txtThongBao.setEditable(false);

		pn_center_top.add(box);

		pnCenter.add(pn_center_top);
		this.add(pnCenter, BorderLayout.CENTER);

		lblTenKhachHang.setPreferredSize(new Dimension(120, 5));
		lblCCCD.setPreferredSize(new Dimension(120, 5));
		lblSDT.setPreferredSize(new Dimension(120, 5));
		lblMaPhongCu.setPreferredSize(new Dimension(120, 5));
		lblMaPhongMoi.setPreferredSize(new Dimension(120, 5));
		lblChiTietLyDoDoi.setPreferredSize(new Dimension(120, 5));
		lblPhiPhuThem.setPreferredSize(new Dimension(120, 5));

		JPanel pn_bottom = new JPanel();
		pn_bottom.add(btnXacNhanDoiPhong = new JButton("Xác Nhận Đổi Phòng"));
		pn_bottom.add(btnHuyDoiPhong = new JButton("Hủy Đổi Phòng"));
		this.add(pn_bottom, BorderLayout.SOUTH);

		btnXacNhanDoiPhong.addActionListener(this);
		btnHuyDoiPhong.addActionListener(this);
	}

	public static void main(String[] args) {
		new FrmPhieuYeuCauDoiPhong().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
