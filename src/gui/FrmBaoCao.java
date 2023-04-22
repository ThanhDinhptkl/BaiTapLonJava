package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;
import connectDB.ConnectDB;

public class FrmBaoCao extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private JLabel lblTu,lblDen,lblLocTheo,lblTongTien;
	private JTextField txtTongTien;
	private JButton btnThoat,btnXuatBaoCao,btnHienThiBieuDo,btnLoc;
	private JDateChooser csTu,csDen;
	private JTable table;
    private JComboBox<String> cbLoc;
    private DefaultTableModel model;
    private JCheckBox chkDichVu;
	public FrmBaoCao() {
		super("Báo cáo");
		setSize(1050, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		

		createGUI();
	}
	public void createGUI(){
		JPanel pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
//		pnBorder.setBackground(Color.red);
		pnBorder.setBorder(BorderFactory.createTitledBorder("Báo cáo - Thống kê"));
		
		JPanel pnNorth=new JPanel();
		pnNorth.setLayout(null);
		
		pnNorth.add(lblTu=new JLabel("Từ ngày:"));
		pnNorth.add(csTu=new JDateChooser());
		pnNorth.add(lblDen=new JLabel("Đến ngày:"));
		pnNorth.add(csDen=new JDateChooser());
		pnNorth.add(lblLocTheo=new JLabel("Lọc theo:"));
		pnNorth.add(cbLoc=new JComboBox<String>());
		cbLoc.setPreferredSize(new Dimension(150,30));
		cbLoc.addItem("Tuần qua");
		cbLoc.addItem("Tháng qua");
		cbLoc.addItem("Quý qua");
		pnNorth.add(btnLoc=new JButton("Lọc"));
		
		Icon iconBieuDo = new ImageIcon("img/schema_preport.png");
		Image imgBieuDo = ((ImageIcon)iconBieuDo).getImage();
		Image newImgBieuDo = imgBieuDo.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon newIconBieuDO = new ImageIcon(newImgBieuDo);
		pnNorth.add(btnHienThiBieuDo=new JButton("Hiển thị dưới dạng biểu đồ"));
		btnHienThiBieuDo.setIcon(newIconBieuDO);
		
		Icon iconXuatBaoCao = new ImageIcon("img/wrapper_report.png");
		Image imgXuatBaoCao = ((ImageIcon)iconXuatBaoCao).getImage();
		Image newImgXuatBaoCao = imgXuatBaoCao.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon newIconXuatBaoCao = new ImageIcon(newImgXuatBaoCao);
		pnNorth.add(btnXuatBaoCao=new JButton("Xuất báo cáo"));
		btnXuatBaoCao.setIcon(newIconXuatBaoCao);
		
		Icon iconThoat = new ImageIcon("img/exit.png");
		Image imgThoat = ((ImageIcon)iconThoat).getImage();
		Image newImgThoat = imgThoat.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon newIconThoat = new ImageIcon(newImgThoat);
		pnNorth.add(btnThoat=new JButton("Thoát"));
		btnThoat.setIcon(newIconThoat);
		pnNorth.add(chkDichVu=new JCheckBox("Xem dịch vụ"));
		
		lblTu.setBounds(20, 20, 60, 30);
		csTu.setBounds(100, 20, 150, 30);
		lblDen.setBounds(20, 70, 60, 30);
		csDen.setBounds(100, 70, 150, 30);
		lblLocTheo.setBounds(20, 120, 60, 30);
		cbLoc.setBounds(100, 120, 150, 30);
		btnLoc.setBounds(270,70,60,30);
		chkDichVu.setBounds(270,5,150,60);
		btnHienThiBieuDo.setBounds(440, 20, 200, 30);
		btnXuatBaoCao.setBounds(630, 20, 150, 30);
		btnThoat.setBounds(780, 20, 120, 30);
		pnNorth.setPreferredSize(new Dimension(900,170)); 

        String[] columnNames = {"Mã HD","Mã Phòng","Tên khách hàng","Ngày đặt","Ngày trả","Số người","Giá phòng","Ngày lập hóa đơn","Số lượng dịch vụ","Tên dịch vụ","Giá dịch vụ","Tổng tiền dịch vụ","Tổng tiền thanh toán"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        joinWithPhong();
        scrollPane.setPreferredSize(new Dimension(900,330));
        
        JPanel pnSouth=new JPanel();
        pnSouth.setLayout(null);
        pnSouth.add(lblTongTien=new JLabel("Tổng tiền thu của khách:"));
        pnSouth.add(txtTongTien=new JTextField());
        lblTongTien.setBounds(500,20,200,30);
        txtTongTien.setBounds(720,20,180,30);
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 16));
        lblTongTien.setForeground(Color.RED);
        txtTongTien.setFont(new Font("Arial", Font.BOLD, 16));
        txtTongTien.setForeground(Color.RED);
        txtTongTien.setText(0+"");
        tinhTongTien();
        
        cbLoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = cbLoc.getSelectedItem().toString();
                if (selectedOption.equals("Tuần qua")) {
                	try {
                	    Connection con = ConnectDB.getInstance().getConnection();
                	    
                	    // Tính ngày bắt đầu và kết thúc của khoảng thời gian cần tìm
                	    Calendar cal = Calendar.getInstance();
                	    cal.add(Calendar.WEEK_OF_YEAR, -1); // lùi về 1 tuần
                	    java.sql.Date fromDate = new java.sql.Date(cal.getTimeInMillis());
                	    java.sql.Date toDate = new java.sql.Date(System.currentTimeMillis());

                	    String sql = "SELECT c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD ,ct.SOLUONG ,dv.TENDV ,dv.GIA " +
                	            "FROM ChiTietDatPhong AS c " +
                	            "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
                	            "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
                	            "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
                	            "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
                	            "JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
                	            "WHERE h.NGAYLAPHD BETWEEN ? AND ?";
                	    PreparedStatement ps = con.prepareStatement(sql);
                	    ps.setDate(1, fromDate);
                	    ps.setDate(2, toDate);
                	    DefaultTableModel dm=(DefaultTableModel) table.getModel();
                	    dm.setRowCount(0);
                	    ResultSet rs = ps.executeQuery();
                	    Object obj[]=new Object[15];
                	    while (rs.next()) {
                	        obj[0]=rs.getString(1);
                	        obj[1]=rs.getInt(2);
                	        obj[2]=rs.getString(3);
                	        obj[3]=rs.getDate(4);
                	        obj[4]=rs.getDate(5);
                	        obj[5]=rs.getInt(6);
                	        obj[6]=rs.getDouble(7);
                	        obj[7]=rs.getDate(8);
                	        obj[8]=rs.getInt(9);
                	        obj[9]=rs.getString(10);
                	        obj[10]=rs.getDouble(11);
                	        obj[11]=rs.getDouble(9) * rs.getDouble(11); // tính tổng tiền dịch vụ
                	        obj[12]=rs.getDouble(7) + rs.getDouble(11) * rs.getDouble(9); // tính tổng tiền bao gồm phòng và dịch vụ
                	        dm.addRow(obj);
                	    }
                	} catch (SQLException ex) {
                	    ex.printStackTrace();
                	}

                } else if (selectedOption.equals("Tháng qua")) {
                	try {
                	    Connection con = ConnectDB.getInstance().getConnection();
                	    String sql = "SELECT c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD ,ct.SOLUONG ,dv.TENDV ,dv.GIA " +
                	            "FROM ChiTietDatPhong AS c " +
                	            "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
                	            "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
                	            "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
                	            "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
                	            "JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
                	            "WHERE h.NGAYLAPHD >= DATEADD(MONTH, -1, GETDATE())";
                	    PreparedStatement ps = con.prepareStatement(sql);
                	    DefaultTableModel dm=(DefaultTableModel) table.getModel();
                	    dm.setRowCount(0);
                	    ResultSet rs = ps.executeQuery();
                	    Object obj[]=new Object[15];
                	    while (rs.next()) {
                	        obj[0]=rs.getString(1);
                	        obj[1]=rs.getInt(2);
                	        obj[2]=rs.getString(3);
                	        obj[3]=rs.getDate(4);
                	        obj[4]=rs.getDate(5);
                	        obj[5]=rs.getInt(6);
                	        obj[6]=rs.getDouble(7);
                	        obj[7]=rs.getDate(8);
                	        obj[8]=rs.getInt(9);
                	        obj[9]=rs.getString(10);
                	        obj[10]=rs.getDouble(11);
                	        obj[11]=rs.getDouble(9) * rs.getDouble(11); // tính tổng tiền dịch vụ
                	        obj[12]=rs.getDouble(7) + rs.getDouble(11) * rs.getDouble(9); // tính tổng tiền bao gồm phòng và dịch vụ
                	        dm.addRow(obj);    
                	    }
                	} catch (SQLException ex) {
                	    ex.printStackTrace();
                	}


                } else if (selectedOption.equals("Quý qua")) {
                	Calendar cal = Calendar.getInstance();
                	int quarter = (cal.get(Calendar.MONTH) / 3) + 1;
                	int year = cal.get(Calendar.YEAR);

                	// Tính ngày bắt đầu của quý
                	cal.set(Calendar.MONTH, (quarter - 1) * 3);
                	cal.set(Calendar.DAY_OF_MONTH, 1);
                	Date fromDate = cal.getTime();

                	// Tính ngày kết thúc của quý
                	cal.add(Calendar.MONTH, 3);
                	cal.add(Calendar.DAY_OF_MONTH, -1);
                	Date toDate = cal.getTime();
                	
//                	Calendar cal = Calendar.getInstance();
//                	int currentQuarter = (cal.get(Calendar.MONTH) / 3) + 1;
//                	int currentYear = cal.get(Calendar.YEAR);
//
//                	// Tính quý trước
//                	int previousQuarter = currentQuarter - 1;
//                	int previousYear = currentYear;
//                	if (previousQuarter == 0) {
//                	    previousQuarter = 4;
//                	    previousYear--;
//                	}
//
//                	// Tính ngày bắt đầu của quý trước
//                	cal.set(Calendar.YEAR, previousYear);
//                	cal.set(Calendar.MONTH, (previousQuarter - 1) * 3);
//                	cal.set(Calendar.DAY_OF_MONTH, 1);
//                	Date fromDate = cal.getTime();
//
//                	// Tính ngày kết thúc của quý trước
//                	cal.add(Calendar.MONTH, 3);
//                	cal.add(Calendar.DAY_OF_MONTH, -1);
//                	Date toDate = cal.getTime();

                	try {
                	    Connection con = ConnectDB.getInstance().getConnection();
                	    String sql = "SELECT c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD ,ct.SOLUONG ,dv.TENDV ,dv.GIA " +
                	                "FROM ChiTietDatPhong AS c " +
                	                "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
                	                "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
                	                "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
                	                "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
                	                "JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
                	                "WHERE h.NGAYLAPHD BETWEEN ? AND ?";
                	    PreparedStatement ps = con.prepareStatement(sql);
                	    ps.setDate(1, new java.sql.Date(fromDate.getTime()));
                	    ps.setDate(2, new java.sql.Date(toDate.getTime()));
                	    DefaultTableModel dm=(DefaultTableModel) table.getModel();
                	    dm.setRowCount(0);
                	    ResultSet rs = ps.executeQuery();
                	    Object obj[]=new Object[15];
                	    while (rs.next()) {
                	        obj[0]=rs.getString(1);
                	        obj[1]=rs.getInt(2);
                	        obj[2]=rs.getString(3);
                	        obj[3]=rs.getDate(4);
                	        obj[4]=rs.getDate(5);
                	        obj[5]=rs.getInt(6);
                	        obj[6]=rs.getDouble(7);
                	        obj[7]=rs.getDate(8);
                	        obj[8]=rs.getInt(9);
                	        obj[9]=rs.getString(10);
                	        obj[10]=rs.getDouble(11);
                	        obj[11]=rs.getDouble(9) * rs.getDouble(11); // tính tổng tiền dịch vụ
                	        obj[12]=rs.getDouble(7) + rs.getDouble(11) * rs.getDouble(9); // tính tổng tiền bao gồm phòng và dịch vụ
                	        dm.addRow(obj);
                	    }
                	} catch (SQLException ex) {
                	    ex.printStackTrace();
                	}

                }
            }
        });
        
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE || e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.DELETE) {
                    tinhTongTien();
                }
            }
        });
        

        pnSouth.setPreferredSize(new Dimension(900,100));

        pnBorder.add(pnNorth,BorderLayout.NORTH);
        pnBorder.add(scrollPane,BorderLayout.CENTER);
        pnBorder.add(pnSouth,BorderLayout.SOUTH);
        
        contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);
		
		btnThoat.addActionListener(this);
		btnHienThiBieuDo.addActionListener(this);
		btnXuatBaoCao.addActionListener(this);
		btnLoc.addActionListener(this);
		
	}
	public static void main(String[] args) {
		new FrmBaoCao().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(btnXuatBaoCao)) {
			try {
        		JOptionPane.showMessageDialog(null, "Đã xuất dữ liệu ra file excel thành công.");
                ExcelExporter.exportTable(table, "report.xls");
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
		}else if(o.equals(btnLoc)) {
			 try {
		            Connection con = ConnectDB.getInstance().getConnection();
		            java.sql.Date fromDate = new java.sql.Date(csTu.getDate().getTime());
		            java.sql.Date toDate = new java.sql.Date(csDen.getDate().getTime());
		            String sql = "SELECT c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD ,ct.SOLUONG ,dv.TENDV ,dv.GIA " +
		                    "FROM ChiTietDatPhong AS c " +
		                    "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
		                    "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
		                    "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
		                    "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
		                    "JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
		                    "WHERE h.NGAYLAPHD BETWEEN ? AND ?";
		            PreparedStatement ps = con.prepareStatement(sql);
		            ps.setDate(1, fromDate);
		            ps.setDate(2, toDate);
		            DefaultTableModel dm=(DefaultTableModel) table.getModel();
		            dm.setRowCount(0);
		            ResultSet rs = ps.executeQuery();
		            Object obj[]=new Object[15];
		            while (rs.next()) {
		                obj[0]=rs.getString(1);
		                obj[1]=rs.getInt(2);
		                obj[2]=rs.getString(3);
		                obj[3]=rs.getDate(4);
		                obj[4]=rs.getDate(5);
		                obj[5]=rs.getInt(6);
		                obj[6]=rs.getDouble(7);
		                obj[7]=rs.getDate(8);
		                obj[8]=rs.getInt(9);
		                obj[9]=rs.getString(10);
		                obj[10]=rs.getDouble(11);
		                obj[11]=rs.getDouble(9) * rs.getDouble(11); // tính tổng tiền dịch vụ
		                obj[12]=rs.getDouble(7) + rs.getDouble(11) * rs.getDouble(9); // tính tổng tiền bao gồm phòng và dịch vụ
		                dm.addRow(obj);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		  }else if(o.equals(btnHienThiBieuDo)) {
			  try {
				    Connection con = ConnectDB.getInstance().getConnection();
				    String sql = "SELECT c.MAPHONG, SUM(p.GIA) AS TONGTIENPHONG, SUM(ct.SOLUONG * dv.GIA) AS TONGTIENDICHVU " +
				                 "FROM ChiTietDatPhong AS c " +
				                 "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
				                 "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
				                 "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
				                 "JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
				                 "GROUP BY c.MAPHONG";
				    Statement sta = con.createStatement();
				    ResultSet rs = sta.executeQuery(sql);

				    // Tạo dataset cho biểu đồ cột
				    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				    while (rs.next()) {
				        int maPhong = rs.getInt("MAPHONG");
				        double tongTienPhong = rs.getDouble("TONGTIENPHONG");
				        double tongTienDichVu = rs.getDouble("TONGTIENDICHVU");
				        double tongTien = tongTienPhong + tongTienDichVu;
				        dataset.addValue(tongTien, "Tổng tiền", "Phòng " + maPhong);
				    }

				    // Tạo biểu đồ cột từ dataset
				    JFreeChart chart = ChartFactory.createBarChart("Tổng tiền từng phòng", "Phòng", "Tổng tiền",
				                dataset, PlotOrientation.VERTICAL, false, true, false);

				    // Hiển thị biểu đồ cột trong cửa sổ mới
				    ChartFrame chartFrame = new ChartFrame("Báo cáo tổng tiền từng phòng", chart);
				    chartFrame.pack();
				    chartFrame.setVisible(true);
				} catch (SQLException ex) {
				    ex.printStackTrace();
				}

		  }else if(o.equals(btnThoat)) {
			  
		  }
		
		
	}
	public void joinWithPhong() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD ,ct.SOLUONG ,dv.TENDV ,dv.GIA " +
                    "FROM ChiTietDatPhong AS c " +
                    "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
                    "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
                    "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
                    "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
					"JOIN DichVu AS dv ON ct.MADV=dv.MADV";
			Statement sta = con.createStatement();
			DefaultTableModel dm=(DefaultTableModel) table.getModel();
			dm.getDataVector().removeAllElements();
			ResultSet rs = sta.executeQuery(sql);
			Object obj[]=new Object[15];
			while (rs.next()) {
				obj[0]=rs.getString(1);
				obj[1]=rs.getInt(2);
				obj[2]=rs.getString(3);
				obj[3]=rs.getDate(4);
				obj[4]=rs.getDate(5);
				obj[5]=rs.getInt(6);
				obj[6]=rs.getDouble(7);
				obj[7]=rs.getDate(8);
				obj[8]=rs.getInt(9);
				obj[9]=rs.getString(10);
				obj[10]=rs.getDouble(11);
				obj[11]=rs.getDouble(9) * rs.getDouble(11); // tính tổng tiền dịch vụ
		        obj[12]=rs.getDouble(7) + rs.getDouble(11) * rs.getDouble(9); // tính tổng tiền bao gồm phòng và dịch vụ
//				dm.addRow(obj);
		        model.addRow(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void tinhTongTien() {
		double tongTien=0;
        for(int i=0;i<model.getRowCount();i++) {
        	double thanhTien=Double.parseDouble(model.getValueAt(i, 12).toString());
        	tongTien+=thanhTien;
        }txtTongTien.setText(String.valueOf(tongTien));
	}
}
