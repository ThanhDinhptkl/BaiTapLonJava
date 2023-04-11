package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import interfaces.I_NhanVien;

public class Dao_NhanVien implements I_NhanVien {
	
	public Dao_NhanVien() {
		
	}

	@Override
	public ArrayList<NhanVien> getAllNhanVien() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoMaNV(String maNV) {
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
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
				
				NhanVien nv = new NhanVien(maNV, hoTen, sdt, phai, tuoi, luong);
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dsNV;
	}

	@Override
	public ArrayList<NhanVien> getTheoHoTen(String hoTen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoSDT(String sdt) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean capNhat(NhanVien nv) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xoa(String maNV) {
		// TODO Auto-generated method stub
		return false;
	}

}
