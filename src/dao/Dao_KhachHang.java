package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import interfaces.I_KhachHang;

public class Dao_KhachHang implements I_KhachHang {
	public Dao_KhachHang() {

	}

	@Override
	public ArrayList<KhachHang> getAllKhachHang() {
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from KhachHang";
			Statement sta = con.createStatement();

			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				String maKH = rs.getString("MAKH");
				String hoTen = rs.getString("HOTEN");
				String sDT = rs.getString("SDT");
				String cccd = rs.getString("CCCD");
				String email = rs.getString("EMAIL");
				boolean phai = rs.getBoolean("PHAI");
				String diaChi = rs.getString("DIACHI");

				KhachHang kh = new KhachHang(maKH, hoTen, sDT, cccd, email, phai, diaChi);
				dsKH.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKH;
	}

	@Override
	public KhachHang getKhachHangTheoMaKH(String id) {
		KhachHang kh = null;
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from KhachHang where MAKH = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, id);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString("MAKH");
				String hoTen = rs.getString("HOTEN");
				String sDT = rs.getString("SDT");
				String cccd = rs.getString("CCCD");
				String email = rs.getString("EMAIL");
				boolean phai = rs.getBoolean("PHAI");
				String diaChi = rs.getString("DIACHI");

				kh = new KhachHang(maKH, hoTen, sDT, cccd, email, phai, diaChi);
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
		return kh;
	}

	@Override
	public ArrayList<KhachHang> getKhachHangTheoSDT(String sdt) {
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from KhachHang where SDT = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, sdt);

			ResultSet rs = sta.executeQuery();

			while (rs.next()) {
				String maKH = rs.getString("MAKH");
				String hoTen = rs.getString("HOTEN");
				String sDT = rs.getString("SDT");
				String cccd = rs.getString("CCCD");
				String email = rs.getString("EMAIL");
				boolean phai = rs.getBoolean("PHAI");
				String diaChi = rs.getString("DIACHI");

				KhachHang kh = new KhachHang(maKH, hoTen, sDT, cccd, email, phai, diaChi);
				dsKH.add(kh);
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
		return dsKH;
	}

	@Override
	public KhachHang getKhachHangTheoCCCD(String cccd) {
		KhachHang kh = null;
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from KhachHang where CCCD = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, cccd);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString("MAKH");
				String hoTen = rs.getString("HOTEN");
				String sDT = rs.getString("SDT");
				String cCCD = rs.getString("CCCD");
				String email = rs.getString("EMAIL");
				boolean phai = rs.getBoolean("PHAI");
				String diaChi = rs.getString("DIACHI");

				kh = new KhachHang(maKH, hoTen, sDT, cCCD, email, phai, diaChi);
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
		return kh;
	}

	@Override
	public boolean them(KhachHang kh) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into KhachHang values(?,?,?,?,?,?,?)";
			sta = con.prepareStatement(sql);

			sta.setString(1, kh.getMaKH());
			sta.setString(2, kh.getHoTen());
			sta.setString(3, kh.getsDT());
			sta.setString(4, kh.getCccd());
			sta.setString(5, kh.getEmail());
			sta.setBoolean(6, kh.isPhai());
			sta.setString(7, kh.getDiaChi());
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
	public boolean capNhat(KhachHang kh) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "update KhachHang set HOTEN = ?, SDT = ?, CCCD = ?, EMAIL = ?, PHAI = ?, DIACHI = ? where MAKH = ?";
			sta = con.prepareStatement(sql);

			sta.setString(7, kh.getMaKH());
			sta.setString(1, kh.getHoTen());
			sta.setString(2, kh.getsDT());
			sta.setString(3, kh.getCccd());
			sta.setString(4, kh.getEmail());
			sta.setBoolean(5, kh.isPhai());
			sta.setString(6, kh.getDiaChi());
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
	public boolean xoa(String idKH) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "delete from KhachHang where MAKH = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, idKH);
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
