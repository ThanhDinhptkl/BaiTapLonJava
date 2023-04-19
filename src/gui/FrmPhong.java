package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class FrmPhong extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private static final int NUM_ROOMS = 30;
	private JPopupMenu popupMenu;
	private JPanel roomPanel;
	private JMenuItem itDatPhong,itTraPhong,itDoiPhong,itDonPhong,itSuaPhong,itXemThongTinKhach,itCapNhatDichVu;
	private JComboBox<String> cboTang;
	public FrmPhong() {
		super("Quản lý phòng");
		setSize(1050, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		createGUI();
	}
	public void createGUI() {
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
//		pnBorder.setBackground(Color.red);
		pnBorder.setBorder(BorderFactory.createTitledBorder("Danh sách phòng"));
		
		JPanel pnNorth = new JPanel();
		JLabel lblLocTang = new JLabel("Lọc theo tầng");
		pnNorth.add(lblLocTang);
		cboTang = new JComboBox<String>();
		cboTang.addItem("Tầng 1");
		cboTang.addItem("Tầng 2");
		cboTang.addItem("Tầng 3");
		pnNorth.add(cboTang);
		pnBorder.add(pnNorth, BorderLayout.NORTH);
		
		
		// Tạo panel chứa danh sách phòng
        JPanel roomListPanel = new JPanel(new GridLayout(6, 5,10,10));
        roomListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        roomListPanel.setPreferredSize(new Dimension(900, 600));

        // Tạo các phòng và chèn vào panel
        for (int i = 1; i <= NUM_ROOMS; i++) {
            roomPanel = createRoomPanel(i);
            roomListPanel.add(roomPanel);
            
         // Thêm MouseListener vào roomPanel
            roomPanel.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            });
         
        }
        pnBorder.add(roomListPanel,BorderLayout.CENTER);
        
        
        
        contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);
		
		itDatPhong.addActionListener(this);
		itTraPhong.addActionListener(this);
		itDoiPhong.addActionListener(this);
		itDonPhong.addActionListener(this);
		itCapNhatDichVu.addActionListener(this);
		itXemThongTinKhach.addActionListener(this);
		itSuaPhong.addActionListener(this);
	}
	
	public static void main(String[] args) {
		new FrmPhong().setVisible(true);
	}
	private JPanel createRoomPanel(int roomNumber) {
		JLabel roomImageLabel = new JLabel();
		ImageIcon icon = new ImageIcon("img/img_hotel2.png");
		Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		icon.setImage(image);
		roomImageLabel.setIcon(icon);

		// Thiết lập màu green cho roomPanel
		JPanel roomPanel = new JPanel(new BorderLayout());
		roomPanel.setBackground(Color.GREEN);
		
        roomPanel.add(roomImageLabel, BorderLayout.CENTER);
        
        Border roomBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        roomPanel.setBorder(roomBorder);

        // Tạo text area hiển thị tên khách hàng và thời gian phòng hoạt động
        JTextArea roomInfoTextArea = new JTextArea();
        roomInfoTextArea.setEditable(false);
        roomInfoTextArea.setLineWrap(true);
        roomInfoTextArea.setWrapStyleWord(true);
        roomInfoTextArea.setText("Room " + roomNumber + "\nAvailable");
        roomInfoTextArea.setBackground(Color.green);
        roomPanel.add(roomInfoTextArea, BorderLayout.SOUTH);
        
        popupMenu = new JPopupMenu();
        itDatPhong = new JMenuItem("Đặt phòng");
        ImageIcon iconDatPhong=new ImageIcon("img/booking.png");
        Image img=iconDatPhong.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH);
        iconDatPhong.setImage(img);
        itDatPhong.setIcon(iconDatPhong);
        itTraPhong = new JMenuItem("Trả phòng");
        ImageIcon iconTraPhong=new ImageIcon("img/traphong.jpg");
        iconTraPhong.setImage(iconTraPhong.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH));
        itTraPhong.setIcon(iconTraPhong);
        itDoiPhong=new JMenuItem("Đổi phòng");
        ImageIcon iconDoiPhong=new ImageIcon("img/doiphong.jpg");
        iconDoiPhong.setImage(iconDoiPhong.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH));
        itDoiPhong.setIcon(iconDoiPhong);
        itXemThongTinKhach=new JMenuItem("Thông tin khách");
        ImageIcon iconXem=new ImageIcon("img/xem.png");
        iconXem.setImage(iconXem.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH));
        itXemThongTinKhach.setIcon(iconXem);
        itCapNhatDichVu=new JMenuItem("Cập nhật dịch vụ");
        ImageIcon iconDichVu=new ImageIcon("img/dichvu.png");
        itCapNhatDichVu.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.black));
        iconDichVu.setImage(iconDichVu.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH));
        itCapNhatDichVu.setIcon(iconDichVu);
        itDonPhong=new JMenuItem("Dọn phòng");
        ImageIcon iconDonPhong=new ImageIcon("img/don.jpg");
        iconDonPhong.setImage(iconDonPhong.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH));
        itDonPhong.setIcon(iconDonPhong);
        itSuaPhong=new JMenuItem("Sửa phòng");
        ImageIcon iconSuaPhong=new ImageIcon("img/suachua.jpg");
        iconSuaPhong.setImage(iconSuaPhong.getImage().getScaledInstance(20, 15, Image.SCALE_SMOOTH));
        itSuaPhong.setIcon(iconSuaPhong);
        
        popupMenu.add(itDatPhong);
        popupMenu.add(itTraPhong);
        popupMenu.add(itDoiPhong);
        popupMenu.add(itXemThongTinKhach);
        popupMenu.add(itCapNhatDichVu);
        popupMenu.add(itDonPhong);
        popupMenu.add(itSuaPhong);
        
        
        
     // Tạo action listener cho text area của phòng
        roomInfoTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Cập nhật tình trạng phòng
                roomInfoTextArea.setText("Room " + roomNumber + "\nOccupied");
                roomInfoTextArea.setBackground(Color.red);
                roomPanel.setBackground(Color.red);
            }
        });

        return roomPanel;
}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(itDatPhong)) {
			setVisible(false);
			new FrmDatPhong().setVisible(true);
		}else if(o.equals(itTraPhong)) {
			
		}else if(o.equals(itDoiPhong)) {
			
		}else if(o.equals(itXemThongTinKhach)) {

			new FrmKhachHang().setVisible(true);

		}else if(o.equals(itCapNhatDichVu)) {
			
		}else if(o.equals(itDonPhong)) {
			
		}else if(o.equals(itSuaPhong)) {
			
		}
		
	}
}