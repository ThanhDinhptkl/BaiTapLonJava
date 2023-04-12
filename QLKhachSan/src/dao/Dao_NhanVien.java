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
import interfaces.I_NhanVien;

public class Dao_NhanVien implements I_NhanVien {

	public Dao_NhanVien() {

	}

	@Override
	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from NhanVien";
			Statement sta = con.createStatement();
			
			ResultSet rs = sta.executeQuery(sql);
			
			while(rs.next()) {
				String idNV = rs.getString("MANV");
				String hoTen = rs.getString("HOTEN");
				String sdt = rs.getString("SDT");
				boolean phai = rs.getBoolean("PHAI");
				int tuoi = rs.getInt("TUOI");
				float luong = rs.getFloat("LUONG");

				NhanVien nv = new NhanVien(idNV, hoTen, sdt, phai, tuoi, luong);
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}

	@Override
	public NhanVien getTheoMaNV(String maNV) {
		NhanVien nv = null;
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from NhanVien where MANV = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, maNV);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String idNV = rs.getString("MANV");
				String hoTen = rs.getString("HOTEN");
				String sdt = rs.getString("SDT");
				boolean phai = rs.getBoolean("PHAI");
				int tuoi = rs.getInt("TUOI");
				float luong = rs.getFloat("LUONG");
	
				nv = new NhanVien(idNV, hoTen, sdt, phai, tuoi, luong);
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
		return nv;
	}

	@Override
	public ArrayList<NhanVien> getTheoHoTen(String hoTen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoSDT(String sdt) {
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from NhanVien where SDT = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, sdt);

			ResultSet rs = sta.executeQuery();

			while (rs.next()) {
				String idNV = rs.getString("MANV");
				String hoTen = rs.getString("HOTEN");
				String sDT = rs.getString("SDT");
				boolean phai = rs.getBoolean("PHAI");
				int tuoi = rs.getInt("TUOI");
				float luong = rs.getFloat("LUONG");
	
				NhanVien nv = new NhanVien(idNV, hoTen, sDT, phai, tuoi, luong);
				dsNV.add(nv);
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
		return dsNV;
	}

	@Override
	public ArrayList<NhanVien> getTheoPhai(Boolean phai) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoTuoi(int tuoi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoLuong(float luong) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean them(NhanVien nv) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into NhanVien values(?,?,?,?,?,?)";
			sta = con.prepareStatement(sql);

			sta.setString(1, nv.getMaNV());
			sta.setString(2, nv.getHoTen());
			sta.setString(3, nv.getSdt());
			sta.setBoolean(4, nv.isPhai());
			sta.setInt(5, nv.getTuoi());
			sta.setDouble(6, nv.getLuong());
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
	public boolean capNhat(NhanVien nv) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "update NhanVien set HOTEN = ?, SDT = ?, PHAI = ?, TUOI = ?, LUONG = ? where MANV = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, nv.getHoTen());
			sta.setString(2, nv.getSdt());
			sta.setBoolean(3, nv.isPhai());
			sta.setInt(4, nv.getTuoi());
			sta.setDouble(5, nv.getLuong());
			sta.setString(6, nv.getMaNV());
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
	public boolean xoa(String maNV) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "delete from NhanVien where MANV = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, maNV);
			n = sta.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

}
