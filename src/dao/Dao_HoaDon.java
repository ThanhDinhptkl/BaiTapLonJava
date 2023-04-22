package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


import org.apache.poi.ss.formula.functions.Today;


import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import interfaces.I_HoaDon;

public class Dao_HoaDon implements I_HoaDon {
	public Dao_HoaDon() {

	}

	@Override
	public ArrayList<HoaDon> getAllHoaDon() {
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from HoaDon";
			Statement sta = con.createStatement();

			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				String maHD = rs.getString("MAHD");
				NhanVien nv = new NhanVien(rs.getString("MANV"));
				KhachHang kh = new KhachHang(rs.getString("MAKH"));
				Date date = rs.getDate("NGAYLAPHD");
				LocalDate ngayLapHD = date.toLocalDate();
				HoaDon hd = new HoaDon(maHD, nv, kh, ngayLapHD);
				dsHD.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHD;
	}

	@Override
	public HoaDon getHoaDonTheoMa(String id) {
		HoaDon hd = null;
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from HoaDon where MAHD = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, id);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString("MAHD");
				NhanVien nv = new NhanVien(rs.getString("MANV"));
				KhachHang kh = new KhachHang(rs.getString("MAKH"));
				Date date = rs.getDate("NGAYLAPHD");
				LocalDate ngayLapHD = date.toLocalDate();

				hd = new HoaDon(maHD, nv, kh, ngayLapHD);
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
		return hd;
	}

	@Override
	public ArrayList<HoaDon> getHoaDonTheoMaNV(String id) {
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from HoaDon where MANV = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, id);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString("MAHD");
				NhanVien nv = new NhanVien(rs.getString("MANV"));
				KhachHang kh = new KhachHang(rs.getString("MAKH"));
				Date date = rs.getDate("NGAYLAPHD");
				LocalDate ngayLapHD = date.toLocalDate();

				HoaDon hd = new HoaDon(maHD, nv, kh, ngayLapHD);
				dsHD.add(hd);
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
		return dsHD;
	}

	@Override
	public ArrayList<HoaDon> getHoaDonTheoMaKH(String id) {
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from HoaDon where MAKH = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, id);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString("MAHD");
				NhanVien nv = new NhanVien(rs.getString("MANV"));
				KhachHang kh = new KhachHang(rs.getString("MAKH"));
				Date date = rs.getDate("NGAYLAPHD");
				LocalDate ngayLapHD = date.toLocalDate();
				HoaDon hd = new HoaDon(maHD, nv, kh, ngayLapHD);
				dsHD.add(hd);
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
		return dsHD;
	}

	@Override
	public boolean them(HoaDon hd) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into HoaDon values(?,?,?,?)";
			sta = con.prepareStatement(sql);

			sta.setString(1, hd.getMaHD());
			sta.setString(2, hd.getNv().getMaNV());
			sta.setString(3, hd.getKh().getMaKH());
			Date date = Date.valueOf(hd.getNgayLapHD());
			sta.setDate(4, date);
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
	public boolean capNhat(HoaDon hd) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "update HoaDon set MANV = ?, MAKH = ?, NGAYLAPHD = ? where MAHD = ?";
			sta = con.prepareStatement(sql);

			sta.setString(4, hd.getMaHD());
			sta.setString(1, hd.getNv().getMaNV());
			sta.setString(2, hd.getKh().getMaKH());
			Date date = Date.valueOf(hd.getNgayLapHD());
			sta.setDate(3, date);
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
	public boolean xoa(String idHD) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "delete from HoaDon where MAHD = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, idHD);
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

	@Override
	public ArrayList<HoaDon> getHoaDonByDateRange(Date fromDate, Date toDate) {
		ArrayList<HoaDon> dsHD=new ArrayList<HoaDon>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from HoaDon where MAKH = ?";
			sta = con.prepareStatement(sql);
			sta.setDate(1, new java.sql.Date(fromDate.getTime()));
			sta.setDate(2, new java.sql.Date(toDate.getTime()));

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString("MAHD");
				NhanVien nv = new NhanVien(rs.getString("MANV"));
				KhachHang kh = new KhachHang(rs.getString("MAKH"));
				Date date = rs.getDate("NGAYLAPHD");
				LocalDate ngayLapHD = date.toLocalDate();
				HoaDon hd = new HoaDon(maHD, nv, kh, ngayLapHD);
				dsHD.add(hd);
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
		return dsHD;
	}

}
