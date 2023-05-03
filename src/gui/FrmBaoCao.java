package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
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

public class FrmBaoCao extends JFrame implements ActionListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private JLabel lblTu,lblDen,lblLocTheo,lblTongTien;
	private JTextField txtTongTien;
	private JButton btnXuatBaoCao,btnHienThiBieuDo,btnLoc;
	private JDateChooser csTu,csDen;
	private JTable table,tableDichVu;
    private JComboBox<String> cbLoc;
    private DefaultTableModel model,modelDichVu;
    private JPopupMenu popupMenu;
    private JMenuItem itXemdichVu;
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
		pnBorder.setBorder(BorderFactory.createTitledBorder("Báo cáo - Thống kê"));
		
		JPanel pnNorth=new JPanel();
		pnNorth.setLayout(null);
		
		pnNorth.add(lblTu=new JLabel("Từ ngày:"));
		pnNorth.add(csTu=new JDateChooser());
		csTu.setDateFormatString("dd/MM/yyyy");
		csTu.setDate(Calendar.getInstance().getTime());
		pnNorth.add(lblDen=new JLabel("Đến ngày:"));
		pnNorth.add(csDen=new JDateChooser());
		csDen.setDateFormatString("dd/MM/yyyy");
		csDen.setDate(Calendar.getInstance().getTime());
		
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
		
		
		lblTu.setBounds(20, 20, 60, 30);
		csTu.setBounds(100, 20, 150, 30);
		lblDen.setBounds(20, 70, 60, 30);
		csDen.setBounds(100, 70, 150, 30);
		lblLocTheo.setBounds(20, 120, 60, 30);
		cbLoc.setBounds(100, 120, 150, 30);
		btnLoc.setBounds(270,70,60,30);
		btnHienThiBieuDo.setBounds(440, 20, 250, 30);
		btnXuatBaoCao.setBounds(700, 20, 150, 30);
		pnNorth.setPreferredSize(new Dimension(900,170)); 
		
		String[] columnNames = {"Mã HD","Mã Phòng","Tên khách hàng","Ngày đặt","Ngày trả","Số người","Tiền phòng","Ngày lập hóa đơn","Tiền dịch vụ","Tổng tiền"};
		model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		joinWithPhong();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900, 165));
		
		String [] column2= {"Mã hóa đơn","Tên dịch vụ","Số lượng","Giá"};
		modelDichVu=new DefaultTableModel(column2,0);
		tableDichVu=new JTable(modelDichVu);
		JScrollPane scrollPane2=new JScrollPane(tableDichVu);
		scrollPane2.setPreferredSize(new Dimension(900,165));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, scrollPane2);
		splitPane.setDividerLocation(0.5);
		splitPane.setEnabled(false);
		
		popupMenu=new JPopupMenu();
		itXemdichVu=new JMenuItem("Xem chi tiết dịch vụ");
		popupMenu.add(itXemdichVu);
		
        
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
    					DefaultTableModel dm=(DefaultTableModel) table.getModel();
    					dm.setRowCount(0);
    					Calendar cal = Calendar.getInstance();
                	    cal.add(Calendar.WEEK_OF_YEAR, -1); // lùi về 1 tuần
                	    java.sql.Date fromDate = new java.sql.Date(cal.getTimeInMillis());
                	    java.sql.Date toDate = new java.sql.Date(System.currentTimeMillis());
    					String sql = "SELECT c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD, SUM(ct.SOLUONG * dv.GIA) AS TONGTIENDICHVU, p.GIA + SUM(ct.SOLUONG * dv.GIA) AS TONGTIEN " +
    		                    "FROM ChiTietDatPhong AS c " +
    		                    "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
    		                    "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
    		                    "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
    		                    "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
    							"JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
    							"WHERE h.NGAYLAPHD BETWEEN ? AND ? " +
    		                    "GROUP BY c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD ";
    					
    					PreparedStatement ps = con.prepareStatement(sql);
                	    ps.setDate(1, fromDate);
                	    ps.setDate(2, toDate);
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
    						obj[8]=rs.getDouble(9);
    						obj[9]=rs.getDouble(10);
    				        dm.addRow(obj);
    					}
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}

                } else if (selectedOption.equals("Tháng qua")) {             	
                	try {
    					Connection con = ConnectDB.getInstance().getConnection();
    					Calendar cal = Calendar.getInstance();
                	    cal.add(Calendar.WEEK_OF_YEAR, -1); // lùi về 1 tuần
                	    java.sql.Date fromDate = new java.sql.Date(cal.getTimeInMillis());
                	    java.sql.Date toDate = new java.sql.Date(System.currentTimeMillis());
    					String sql = "SELECT c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD, SUM(ct.SOLUONG * dv.GIA) AS TONGTIENDICHVU, p.GIA + SUM(ct.SOLUONG * dv.GIA) AS TONGTIEN " +
    		                    "FROM ChiTietDatPhong AS c " +
    		                    "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
    		                    "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
    		                    "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
    		                    "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
    							"JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
    							"WHERE h.NGAYLAPHD >= DATEADD(MONTH, -1, GETDATE())" +
    		                    "GROUP BY c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD ";
    					
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
    						obj[8]=rs.getDouble(9);
    						obj[9]=rs.getDouble(10);
    						dm.addRow(obj); 
    					}
    				} catch (SQLException e1) {
    					e1.printStackTrace();
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
                	try {
    					Connection con = ConnectDB.getInstance().getConnection();
    					DefaultTableModel dm=(DefaultTableModel) table.getModel();
    					dm.setRowCount(0);
    					String sql = "SELECT c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD, SUM(ct.SOLUONG * dv.GIA) AS TONGTIENDICHVU, p.GIA + SUM(ct.SOLUONG * dv.GIA) AS TONGTIEN " +
    		                    "FROM ChiTietDatPhong AS c " +
    		                    "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
    		                    "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
    		                    "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
    		                    "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
    							"JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
    							"WHERE h.NGAYLAPHD BETWEEN ? AND ? " +
    		                    "GROUP BY c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD ";
    					
    					PreparedStatement ps = con.prepareStatement(sql);
    					ps.setDate(1, new java.sql.Date(fromDate.getTime()));
    					ps.setDate(2, new java.sql.Date(toDate.getTime()));
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
    						obj[8]=rs.getDouble(9);
    						obj[9]=rs.getDouble(10);
    				        dm.addRow(obj);
    					}
    				} catch (SQLException e1) {
    					e1.printStackTrace();
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
        pnBorder.add(splitPane,BorderLayout.CENTER);
        pnBorder.add(pnSouth,BorderLayout.SOUTH);
        
        contentPane = new JPanel();
		contentPane.add(pnBorder);
		add(contentPane);
		
		btnHienThiBieuDo.addActionListener(this);
		btnXuatBaoCao.addActionListener(this);
		btnLoc.addActionListener(this);
		table.addMouseListener(this);
		itXemdichVu.addActionListener(this);
		
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
					String sql = "SELECT c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD, SUM(ct.SOLUONG * dv.GIA) AS TONGTIENDICHVU, p.GIA + SUM(ct.SOLUONG * dv.GIA) AS TONGTIEN " +
		                    "FROM ChiTietDatPhong AS c " +
		                    "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
		                    "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
		                    "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
		                    "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
							"JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
							"WHERE h.NGAYLAPHD BETWEEN ? AND ? " +
		                    "GROUP BY c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD ";
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
						obj[8]=rs.getDouble(9);
						obj[9]=rs.getDouble(10);
				        dm.addRow(obj);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			 
		  }else if(o.equals(btnHienThiBieuDo)) {
			  try {
				    Connection con = ConnectDB.getInstance().getConnection();
				    String sql = "SELECT c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD, SUM(ct.SOLUONG * dv.GIA) AS TONGTIENDICHVU, p.GIA + SUM(ct.SOLUONG * dv.GIA) AS TONGTIEN " +
		                    "FROM ChiTietDatPhong AS c " +
		                    "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
		                    "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
		                    "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
		                    "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
							"JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
		                    "GROUP BY c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD";
				    Statement sta = con.createStatement();
				    ResultSet rs = sta.executeQuery(sql);

				    // Tạo dataset cho biểu đồ cột
				    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				    while (rs.next()) {
				        int maPhong = rs.getInt("MAPHONG");
				        double tongTienPhong = rs.getDouble("GIA");
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

		  }else if(o.equals(itXemdichVu)) {
			  int rowIndex=table.getSelectedRow();
			  if(rowIndex>=0) {
				  String maHD=table.getValueAt(rowIndex, 0).toString();
				  PreparedStatement sta=null;
				  try {
					  Connection con=ConnectDB.getInstance().getConnection();
					  DefaultTableModel dm=(DefaultTableModel) tableDichVu.getModel();
			          dm.setRowCount(0);
					  String sql ="Select ctdv.MAHD, dv.TENDV, ctdv.SOLUONG, dv.GIA from DichVu dv join ChiTietSuDungDichVu ctdv on dv.MADV = ctdv.MADV where ctdv.MAHD= ?";				  
					  sta=con.prepareStatement(sql);
					  sta.setString(1, maHD);
					  ResultSet rs=sta.executeQuery();
					  Object obj[]=new Object[15];
					  while(rs.next()) {
						  obj[0]=rs.getString(1);
						  obj[1]=rs.getString(2);
						  obj[2]=rs.getInt(3);
						  obj[3]=rs.getDouble(4);
						  dm.addRow(obj);
					  }
				  }catch (Exception e1) {
					// TODO: handle exception
					  e1.printStackTrace();
				}
			  }
		  }
		
		
	}
	public void joinWithPhong() {
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD, SUM(ct.SOLUONG * dv.GIA) AS TONGTIENDICHVU, p.GIA + SUM(ct.SOLUONG * dv.GIA) AS TONGTIEN " +
                    "FROM ChiTietDatPhong AS c " +
                    "JOIN Phong AS p ON c.MAPHONG = p.MAPHONG " +
                    "JOIN HoaDon AS h ON c.MAHD=h.MAHD " +
                    "JOIN ChiTietSuDungDichVu ct ON h.MAHD=ct.MAHD " +
                    "JOIN KhachHang AS kh ON h.MAKH=kh.MAKH " +
					"JOIN DichVu AS dv ON ct.MADV=dv.MADV " +
                    "GROUP BY c.MAHD, c.MAPHONG, kh.HOTEN, c.NGAYDAT, c.NGAYTRA, c.SONGUOI, p.GIA, h.NGAYLAPHD";
			Statement sta = con.createStatement();
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
				obj[8]=rs.getDouble(9);
				obj[9]=rs.getDouble(10);
		        model.addRow(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void tinhTongTien() {
		double tongTien=0;
        for(int i=0;i<model.getRowCount();i++) {
        	double thanhTien=Double.parseDouble(model.getValueAt(i, 9).toString());
        	tongTien+=thanhTien;
        }txtTongTien.setText(formatTien(tongTien));
	}

	public String formatTien(double tien) {
		DecimalFormat df = new DecimalFormat("#,##0VND");
		String s = df.format(tien);
		return s;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			int r = table.rowAtPoint(e.getPoint());
			if (r >= 0 && r < table.getRowCount()) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
		
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
