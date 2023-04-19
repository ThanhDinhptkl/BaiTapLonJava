package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietDatPhong;
import entity.HoaDon;
import entity.Phong;
import interfaces.I_ChiTietDatPhong;

public class Dao_ChiTietDatPhong implements I_ChiTietDatPhong {
	public Dao_ChiTietDatPhong() {

	}

	@Override
	public ArrayList<ChiTietDatPhong> getAllCTDatPhong() {
		ArrayList<ChiTietDatPhong> dsCT = new ArrayList<ChiTietDatPhong>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from ChiTietDatPhong";
			Statement sta = con.createStatement();

			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				HoaDon hoaDon = new HoaDon(rs.getString("MAHD"));
				Phong phong = new Phong(rs.getString("MAPHONG"));
				Date date1 = rs.getDate("NGAYDAT");
				LocalDate ngayDat = date1.toLocalDate(); 
				Date date2 = rs.getDate("NGAYTRA");
				LocalDate ngayTra = date2.toLocalDate();
				int soNguoi = rs.getInt("SONGUOI");
				String ghiChu = rs.getString("GHICHU");

				ChiTietDatPhong ct = new ChiTietDatPhong(hoaDon, phong, ngayDat, ngayTra, soNguoi, ghiChu);
				dsCT.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCT;
	}

	@Override
	public ArrayList<ChiTietDatPhong> getCTDatPhongTheoMaHD(String idHD) {
		ArrayList<ChiTietDatPhong> dsCT = new ArrayList<ChiTietDatPhong>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from ChiTietDatPhong where MAHD = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, idHD);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				HoaDon hoaDon = new HoaDon(rs.getString("MAHD"));
				Phong phong = new Phong(rs.getString("MAPHONG"));
				Date date1 = rs.getDate("NGAYDAT");
				LocalDate ngayDat = date1.toLocalDate(); 
				Date date2 = rs.getDate("NGAYTRA");
				LocalDate ngayTra = date2.toLocalDate();
				int soNguoi = rs.getInt("SONGUOI");
				String ghiChu = rs.getString("GHICHU");

				ChiTietDatPhong ct = new ChiTietDatPhong(hoaDon, phong, ngayDat, ngayTra, soNguoi, ghiChu);
				dsCT.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dsCT;
	}

	@Override
	public ArrayList<ChiTietDatPhong> getCTDatPhongTheoMaPhong(String idPhong) {
		ArrayList<ChiTietDatPhong> dsCT = new ArrayList<ChiTietDatPhong>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from ChiTietDatPhong where MAPHONG = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, idPhong);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				HoaDon hoaDon = new HoaDon(rs.getString("MAHD"));
				Phong phong = new Phong(rs.getString("MAPHONG"));
				Date date1 = rs.getDate("NGAYDAT");
				LocalDate ngayDat = date1.toLocalDate(); 
				Date date2 = rs.getDate("NGAYTRA");
				LocalDate ngayTra = date2.toLocalDate();
				int soNguoi = rs.getInt("SONGUOI");
				String ghiChu = rs.getString("GHICHU");

				ChiTietDatPhong ct = new ChiTietDatPhong(hoaDon, phong, ngayDat, ngayTra, soNguoi, ghiChu);
				dsCT.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dsCT;
	}

	@Override
	public boolean them(ChiTietDatPhong dp) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into ChiTietDatPhong values(?,?,?,?,?,?)";
			sta = con.prepareStatement(sql);

			sta.setString(1, dp.getHoaDon().getMaHD());
			sta.setString(2, dp.getPhong().getMaPhong());
			Date date1 = Date.valueOf(dp.getNgayDat());
			sta.setDate(3, date1);
			Date date2 = Date.valueOf(dp.getNgayTra());
			sta.setDate(4, date2);
			sta.setInt(5, dp.getSoNguoi());
			sta.setString(6, dp.getGhiChu());
			n = sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	@Override
	public boolean capNhat(ChiTietDatPhong dp) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "update ChiTietDatPhong set NGAYDAT = ?, NGAYTRA = ?, SONGUOI = ?, GHICHU = ? where MAHD = ? and MAPHONG = ?";
			sta = con.prepareStatement(sql);

			Date date1 = Date.valueOf(dp.getNgayDat());
			sta.setDate(1, date1);
			Date date2 = Date.valueOf(dp.getNgayTra());
			sta.setDate(2, date2);
			
			sta.setInt(3, dp.getSoNguoi());
			sta.setString(4, dp.getGhiChu());
			sta.setString(5, dp.getHoaDon().getMaHD());
			sta.setString(6, dp.getPhong().getMaPhong());
			n = sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	@Override
	public boolean xoa(String idHD, String idMP) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "delete from ChiTietDatPhong where MAHD = ? and MAPHONG = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, idHD);
			sta.setString(2, idMP);
			n = sta.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	

}
