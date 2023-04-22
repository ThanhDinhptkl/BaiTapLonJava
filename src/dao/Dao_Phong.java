package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bus.Bus_Tang;
import connectDB.ConnectDB;
import entity.Phong;
import entity.Tang;
import interfaces.I_Phong;

public class Dao_Phong implements I_Phong {
	public Dao_Phong() {

	}

	@Override
	public ArrayList<Phong> getAllPhong() {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * from Phong";
			Statement sta = con.createStatement();

			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				String maPhong = rs.getString("MAPHONG");
				String tenPhong = rs.getString("TENPHONG");
				String loaiPhong = rs.getString("LOAIPHONG");
				boolean trangThai = rs.getBoolean("TRANGTHAI");
				Tang tang = new Tang(rs.getInt("MATANG"));
				double gia = rs.getDouble("GIA");

				Phong p = new Phong(maPhong, tenPhong, loaiPhong, trangThai, tang, gia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	@Override
	public Phong getPhongTheoMaPhong(String id) {
		Phong p = null;
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from Phong where MAPHONG = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, id);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString("MAPHONG");
				String tenPhong = rs.getString("TENPHONG");
				String loaiPhong = rs.getString("LOAIPHONG");
				boolean trangThai = rs.getBoolean("TRANGTHAI");
				Tang tang = new Tang(rs.getInt("MATANG"));
				double gia = rs.getDouble("GIA");

				p = new Phong(maPhong, tenPhong, loaiPhong, trangThai, tang, gia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	@Override
	public ArrayList<Phong> getPhongTheoLoaiPhong(String ten) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from Phong where LOAIPHONG = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, ten);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString("MAPHONG");
				String tenPhong = rs.getString("TENPHONG");
				String loaiPhong = rs.getString("LOAIPHONG");
				boolean trangThai = rs.getBoolean("TRANGTHAI");
				Tang tang = new Tang(rs.getInt("MATANG"));
				double gia = rs.getDouble("GIA");

				Phong p = new Phong(maPhong, tenPhong, loaiPhong, trangThai, tang, gia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsPhong;
	}

	@Override
	public ArrayList<Phong> getPhongTheoTrangThai(Boolean tt) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from Phong where TRANGTHAI = ?";
			sta = con.prepareStatement(sql);
			sta.setBoolean(1, tt);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString("MAPHONG");
				String tenPhong = rs.getString("TENPHONG");
				String loaiPhong = rs.getString("LOAIPHONG");
				boolean trangThai = rs.getBoolean("TRANGTHAI");
				Tang tang = new Tang(rs.getInt("MATANG"));
				double gia = rs.getDouble("GIA");

				Phong p = new Phong(maPhong, tenPhong, loaiPhong, trangThai, tang, gia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsPhong;
	}

	@Override
	public ArrayList<Phong> getPhongTheoMaTang(int idTang) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from Phong where MATANG = ?";
			sta = con.prepareStatement(sql);
			sta.setInt(1, idTang);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maPhong = rs.getString("MAPHONG");
				String tenPhong = rs.getString("TENPHONG");
				String loaiPhong = rs.getString("LOAIPHONG");
				boolean trangThai = rs.getBoolean("TRANGTHAI");
				Tang tang = new Tang(rs.getInt("MATANG"));
				double gia = rs.getDouble("GIA");

				Phong p = new Phong(maPhong, tenPhong, loaiPhong, trangThai, tang, gia);
				dsPhong.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dsPhong;
	}

	@Override
	public boolean them(Phong phong) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into Phong values(?,?,?,?,?,?)";
			sta = con.prepareStatement(sql);

			sta.setString(1, phong.getMaPhong());
			sta.setString(2, phong.getTenPhong());
			sta.setString(3, phong.getLoaiPhong());
			sta.setBoolean(4, phong.isTrangThai());
			sta.setInt(5, phong.getTang().getMaTang());
			sta.setDouble(6, phong.getGia());
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
	public boolean capNhat(Phong phong) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "update Phong set TENPHONG = ?, LOAIPHONG = ?, TRANGTHAI = ?, MATANG = ?, GIA = ? where MAPHONG = ?";
			sta = con.prepareStatement(sql);

			sta.setString(6, phong.getMaPhong());
			sta.setString(1, phong.getTenPhong());
			sta.setString(2, phong.getLoaiPhong());
			sta.setBoolean(3, phong.isTrangThai());
			sta.setInt(4, phong.getTang().getMaTang());
			sta.setDouble(5, phong.getGia());
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
	public boolean xoa(String idPhong) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "delete from Phong where MAPHONG = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, idPhong);
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
