package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;
import interfaces.I_TaiKhoan;

public class Dao_TaiKhoan implements I_TaiKhoan {

	public Dao_TaiKhoan() {

	}

	@Override
	public ArrayList<TaiKhoan> getAllTaiKhoan() {
		ArrayList<TaiKhoan> dsTK = new ArrayList<TaiKhoan>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * from TaiKhoan";
			Statement sta = con.createStatement();

			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				String idTK = rs.getString("MATK");
				NhanVien nv = new NhanVien(rs.getString("MANV"));
				String pass = rs.getString("PASS");
				String quyen = rs.getString("QUYEN");

				TaiKhoan tk = new TaiKhoan(idTK, nv, pass, quyen);
				dsTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}

	@Override
	public TaiKhoan getTheoMaTK(String maTK) {
		TaiKhoan tk = null;
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from TaiKhoan where MATK = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, maTK);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
			String idTK = rs.getString("MATK");
			NhanVien nv = new NhanVien(rs.getString("MANV"));
			String pass = rs.getString("PASS");
			String quyen = rs.getString("QUYEN");

			tk = new TaiKhoan(idTK, nv, pass, quyen);
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
		return tk;
	}

	@Override
	public ArrayList<TaiKhoan> getTheoMaNV(String maNV) {
		ArrayList<TaiKhoan> dsTK = new ArrayList<TaiKhoan>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from TaiKhoan where MANV = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, maNV);

			ResultSet rs = sta.executeQuery();

			while (rs.next()) {
				String idTK = rs.getString("MATK");
				NhanVien nv = new NhanVien(rs.getString("MANV"));
				String pass = rs.getString("PASS");
				String quyen = rs.getString("QUYEN");

				TaiKhoan tk = new TaiKhoan(idTK, nv, pass, quyen);
				dsTK.add(tk);
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
		return dsTK;
	}

	@Override
	public boolean them(TaiKhoan tk) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into TaiKhoan values(?,?,?,?)";
			sta = con.prepareStatement(sql);

			sta.setString(1, tk.getMaTK());
			sta.setString(2, tk.getNv().getMaNV());
			sta.setString(3, tk.getPass());
			sta.setString(4, tk.getQuyen());
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
	public boolean capNhat(TaiKhoan tk) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "update TaiKhoan set PASS = ?, QUYEN = ? where MATK = ? or MANV = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, tk.getPass());
			sta.setString(2, tk.getQuyen());
			sta.setString(3, tk.getMaTK());
			sta.setString(4, tk.getNv().getMaNV());
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
	public boolean xoa(String maTK) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "delete from TaiKhoan where MATK = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, maTK);
			n = sta.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n > 0;
	}

}
